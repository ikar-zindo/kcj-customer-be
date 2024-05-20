package com.kcjcustomerbe.service;

import com.kcjcustomerbe.dto.CustomerDto;
import com.kcjcustomerbe.entity.Customer;
import com.kcjcustomerbe.exception.ErrorMessage;
import com.kcjcustomerbe.exception.list.CustomerNotFoundException;
import com.kcjcustomerbe.exception.list.IdNullException;
import com.kcjcustomerbe.mapper.CustomerMapper;
import com.kcjcustomerbe.mapper.ProductMapper;
import com.kcjcustomerbe.repo.CartProductRepository;
import com.kcjcustomerbe.repo.CartRepository;
import com.kcjcustomerbe.repo.CustomerRepository;
import com.kcjcustomerbe.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

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

   public CustomerDto getCustomerById(UUID customerId) throws CustomerNotFoundException {
      CustomerDto customerDto = null;

      if (customerId != null) {
         Optional<Customer> customerOptional = customerRepository.findById(customerId);

         if (customerOptional.isPresent()) {
            customerDto = mapper.customerInfoDelivery(customerOptional.get());

         } else {
            throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_ID_NOT_FOUND + customerId);
         }
      } else {
         throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
      }

      return customerDto;
   }

   public CustomerDto getCustomerByCartId(UUID cartId) throws CustomerNotFoundException {
      CustomerDto customerDto = null;

      if (cartId != null) {
         Optional<Customer> customerOptional = customerRepository.findCustomerByCartId(cartId);

         if (customerOptional.isPresent()) {
            customerDto = mapper.customerInfoDelivery(customerOptional.get());

         } else {
            throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
         }
      } else {
         throw new IdNullException(ErrorMessage.CART_NOT_FOUND);
      }

      return customerDto;
   }
}
