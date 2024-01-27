package com.kcurryjibcustomer.service;

import com.kcurryjibcustomer.dto.*;
import com.kcurryjibcustomer.entity.Cart;
import com.kcurryjibcustomer.entity.CartProduct;
import com.kcurryjibcustomer.entity.Customer;
import com.kcurryjibcustomer.entity.Product;
import com.kcurryjibcustomer.exception.list.CartException;
import com.kcurryjibcustomer.exception.list.CustomerException;
import com.kcurryjibcustomer.exception.list.RestaurantException;
import com.kcurryjibcustomer.mapper.CartMapper;
import com.kcurryjibcustomer.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CartService {

   private final CartMapper cartMapper;

   private final CustomerRepository customerRepository;

   private final CartRepository cartRepository;

   private final CartProductRepository cartProductRepository;

   private final ProductRepository productRepository;

   private final MenuService menuService;

   private final CustomerService customerService;

   @Autowired
   public CartService(CartMapper cartMapper,
                      CustomerRepository customerRepository,
                      CartRepository cartRepository,
                      CartProductRepository cartProductRepository,
                      ProductRepository productRepository,
                      MenuService menuService,
                      CustomerService customerService) {

      this.cartMapper = cartMapper;
      this.customerRepository = customerRepository;
      this.cartRepository = cartRepository;
      this.cartProductRepository = cartProductRepository;
      this.productRepository = productRepository;
      this.menuService = menuService;
      this.customerService = customerService;
   }

   // READ - CUSTOMER
   public CustomerDto getCustomerById(Long customerId) throws CustomerException {
      CustomerDto customerDto = null;

      if (customerId != null) {
         Optional<Customer> customerOptional = customerRepository.findById(customerId);

         if (customerOptional.isPresent()) {
            customerDto = cartMapper.convertToCustomerDto(customerOptional.get());

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

   // CREATE - ADD PRODUCT TO CART
   public CartProductDto addProductToCustomerCart(Long cartId, Long productId) {

      if (cartId != null && productId != null) {
         CustomerDto customerDto = customerService.getCustomerByCartId(cartId);
         ProductDto productDto = menuService.getProductById(productId);

         RestaurantDto restaurantDto = productDto.getRestaurantDto();

         if (restaurantDto != null) {
            if (restaurantDto.isOpen()) { // check is open restaurant
               if (productDto != null && productDto.getId() != null) {

                  Customer customer = customerRepository.findById(customerDto.getId()).orElse(null);
                  Product product = productRepository.findById(productDto.getId()).orElse(null);

                  if (customer != null && product != null) {
                     Optional<CartProduct> existingCartProductOptional = cartProductRepository
                             .findByCartIdAndProductId(customer.getCart().getId(), product.getId());

                     if (existingCartProductOptional.isPresent()) {
                        CartProduct existingCartProduct = existingCartProductOptional.get();
                        existingCartProduct.setQuantity(existingCartProduct.getQuantity() + 1);
                        cartProductRepository.save(existingCartProduct);

                        return cartMapper.convertToCartProductDto(existingCartProduct);

                     } else {
                        CartProduct cartProduct = new CartProduct();

                        cartProduct.setCart(customer.getCart());
                        cartProduct.setProduct(product);
                        cartProduct.setCratedAt(LocalDateTime.now());
                        cartProduct.setQuantity(1);

                        CartProduct cartProductResponse = cartProductRepository.save(cartProduct);
                        Long idResponse = cartProductResponse.getId();

                        if (idResponse != null && idResponse > 0) {
                           return cartMapper.convertToCartProductDto(cartProductResponse);

                        } else {
                           throw new CartException("Unable to add item to cart");
                        }
                     }
                  } else {
                     throw new CartException("Customer or product not found");
                  }
               } else {
                  throw new CartException("Product not found");
               }
            } else {
               throw new RestaurantException( // check is open restaurant
                       String.format("Sorry, We are closed, try during opening hours.%n«%s» - %s",
                               restaurantDto.getName(), restaurantDto.getOpeningHours()));
            }
         } else {
            throw new RestaurantException(
                    String.format("Restaurant not found in the database with Id=%d!", +
                            productDto.getRestaurantDto().getId()));
         }
      } else {
         throw new CartException("Cart ID or Product ID not provided");
      }
   }
}
