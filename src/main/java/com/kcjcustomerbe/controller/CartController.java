package com.kcjcustomerbe.controller;

import com.kcjcustomerbe.controller.interfaces.CartControllerInterface;
import com.kcjcustomerbe.dto.CartDto;
import com.kcjcustomerbe.dto.CartProductDto;
import com.kcjcustomerbe.dto.OrderDto;
import com.kcjcustomerbe.dto.customer.CustomerDto;
import com.kcjcustomerbe.service.interfaces.CartService;
import com.kcjcustomerbe.service.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController implements CartControllerInterface {

   private final CartService cartService;

   private final CustomerService customerService;

   // READ - CUSTOMER
   @GetMapping
   public ResponseEntity<List<CartProductDto>> getCartProducts() {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      if (principal instanceof UserDetails userDetails) {
         String email = userDetails.getUsername();
         CustomerDto customerDto = customerService.getCustomerByEmail(email);
         UUID cartId = customerDto.getCartDto().getId();

         return ResponseEntity.ok(cartService.getCartProductsByCartId(cartId));
      }
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
   }

   // CREATE - ADD PRODUCT TO CART
   @PutMapping("/addToCart")
   public ResponseEntity<CartProductDto> addProductToCart(@RequestParam Long productId) {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      if (principal instanceof UserDetails userDetails) {
         String email = userDetails.getUsername();
         CustomerDto customerDto = customerService.getCustomerByEmail(email);
         UUID cartId = customerDto.getCartDto().getId();

         return ResponseEntity.ok(cartService.addProductToCart(cartId, productId));
      }
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
   }

   // CREATE NEW ORDER
   @PostMapping("/payCart")
   public ResponseEntity<OrderDto> payCart() {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      if (principal instanceof UserDetails userDetails) {
         String email = userDetails.getUsername();
         CustomerDto customerDto = customerService.getCustomerByEmail(email);
         UUID cartId = customerDto.getCartDto().getId();

         OrderDto dto = cartService.createOrder((cartId));
         cartService.clearCart((cartId));
         return ResponseEntity.created(URI.create("/cart")).body(dto);
      }
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
   }

   // DELETE - CLEAR CART
   @DeleteMapping("/clearCart")
   public ResponseEntity<HttpStatus> clearCart() {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      if (principal instanceof UserDetails userDetails) {
         String email = userDetails.getUsername();
         CustomerDto customerDto = customerService.getCustomerByEmail(email);
         UUID cartId = customerDto.getCartDto().getId();

         cartService.clearCart((cartId));
         return ResponseEntity.ok(HttpStatus.OK);
      }
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
   }

   // GET - TOTAL CART
   @GetMapping("/getTotal")
   public ResponseEntity<BigDecimal> getTotalCart() {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      if (principal instanceof UserDetails userDetails) {
         String email = userDetails.getUsername();
         CustomerDto customerDto = customerService.getCustomerByEmail(email);
         UUID cartId = customerDto.getCartDto().getId();

         return ResponseEntity.ok(cartService.getTotalCartById(cartId));
      }
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
   }

   // GET - CART SIZE
   @GetMapping("/getSize")
   public ResponseEntity<Integer> getCartProductsSize() {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      if (principal instanceof UserDetails userDetails) {
         String email = userDetails.getUsername();
         CustomerDto customerDto = customerService.getCustomerByEmail(email);
         UUID cartId = customerDto.getCartDto().getId();

         return ResponseEntity.ok(cartService.getCartProductsSize(cartId));
      }
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
   }
}
