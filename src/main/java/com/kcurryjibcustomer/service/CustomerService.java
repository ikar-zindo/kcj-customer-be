package com.kcurryjibcustomer.service;

import com.kcurryjibcustomer.dto.CartProductDto;
import com.kcurryjibcustomer.dto.CustomerDto;
import com.kcurryjibcustomer.dto.ProductDto;
import com.kcurryjibcustomer.entity.CartProduct;
import com.kcurryjibcustomer.entity.Customer;
import com.kcurryjibcustomer.entity.Product;
import com.kcurryjibcustomer.exception.list.CartException;
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

import java.time.LocalDateTime;
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
      UserDetails employee = customerRepository.findByUsername(username);

      if (employee == null) {
         throw new UsernameNotFoundException("Customer not found!");
      }

      return employee;
   }

   public CustomerDto getCustomerById(Long customerId) throws CustomerException {
      CustomerDto customerDto = null;

      if (customerId != null) {
         Optional<Customer> customerOptional = customerRepository.findById(customerId);

         if (customerOptional.isPresent()) {
            customerDto = mapper.convertToCustomerDto(customerOptional.get());

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

   public CartProductDto addProductToCustomerCart(Long customerId, Long productId) {

      if (customerId != null && productId != null) {
         CustomerDto customerDto = getCustomerById(customerId);
         ProductDto productDto = menuService.getProductById(productId);

         CartProductDto cartProductDto = new CartProductDto();

         cartProductDto.setCartDto(customerDto.getCartDto());
         cartProductDto.setProductDto(productDto);

         if (productDto != null && productDto.getId() != null) {
            Customer customer = customerRepository.findById(customerDto.getId()).orElse(null);
            Product product = productRepository.findById(productDto.getId()).orElse(null);

            if (customer != null && product != null) {
               CartProduct cartProduct = mapper.convertToCartProduct(cartProductDto);

               cartProduct.setCart(customer.getCart());
               cartProduct.setProduct(product);
               cartProduct.setCratedAt(LocalDateTime.now());
               cartProduct.setQuantity(1);

               CartProduct cartProductResponse = cartProductRepository.save(cartProduct);
               Long idResponse = cartProductResponse.getId();

               if (idResponse != null && idResponse > 0) {
                  return mapper.convertToCartProductDto(cartProductResponse);

               } else {
                  throw new CartException("");
               }
            } else {
               throw new CartException("");
            }
         } else {
            throw new CartException("");
         }
      } else {
         throw new CartException("");
      }
   }
}
