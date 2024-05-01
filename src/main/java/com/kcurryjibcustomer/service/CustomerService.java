package com.kcurryjibcustomer.service;

import com.kcurryjibcustomer.dto.CustomerDto;
import com.kcurryjibcustomer.entity.Customer;
import com.kcurryjibcustomer.exception.list.CustomerException;
import com.kcurryjibcustomer.mapper.CustomerMapper;
import com.kcurryjibcustomer.mapper.ProductMapper;
import com.kcurryjibcustomer.repo.CartProductRepository;
import com.kcurryjibcustomer.repo.CartRepository;
import com.kcurryjibcustomer.repo.CustomerRepository;
import com.kcurryjibcustomer.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements UserDetailsService {

   private final CustomerMapper mapper;

   private final ProductMapper productMapper;

   private final CustomerRepository customerRepository;

   private final CartRepository cartRepository;

   private final CartProductRepository cartProductRepository;

   private final ProductRepository productRepository;

   private final MenuService menuService;

   @Autowired
   public CustomerService(CustomerMapper mapper,
                          ProductMapper productMapper,
                          CustomerRepository customerRepository,
                          CartRepository cartRepository,
                          CartProductRepository cartProductRepository,
                          ProductRepository productRepository,
                          MenuService menuService) {

      this.mapper = mapper;
      this.productMapper = productMapper;
      this.customerRepository = customerRepository;
      this.cartRepository = cartRepository;
      this.cartProductRepository = cartProductRepository;
      this.productRepository = productRepository;
      this.menuService = menuService;
   }

   // READ
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//      UserDetails customer = customerRepository.findByUsername(username);
      UserDetails customer = customerRepository.findByEmail(username);

      if (customer == null) {
         throw new UsernameNotFoundException("Customer not found!");
      }

      return customer;
   }

   // TODO don;t keep unused code
   public CustomerDto getCustomerById(Long customerId) throws CustomerException {
      CustomerDto customerDto = null;

      if (customerId != null) {
         Optional<Customer> customerOptional = customerRepository.findById(customerId);

         if (customerOptional.isPresent()) {
            customerDto = mapper.customerInfoDelivery(customerOptional.get());

         } else {
            throw new CustomerException(
                    String.format("Customer not found in database with id=%d",
                            customerId));
         }
      } else {
         throw new CustomerException("There is no customer ID to search for!");
      }

      return customerDto;
   }

   public CustomerDto getCustomerByCartId(Long cartId) throws CustomerException {
      CustomerDto customerDto = null;

      if (cartId != null) {
         Optional<Customer> customerOptional = customerRepository.findCustomerByCartId(cartId);

         if (customerOptional.isPresent()) {
            customerDto = mapper.customerInfoDelivery(customerOptional.get());

         } else {
            throw new CustomerException(
                    String.format("Customer not found in database with id=%d",
                            cartId));
         }
      } else {
         throw new CustomerException("There is no customer ID to search for!");
      }

      return customerDto;
   }
}
