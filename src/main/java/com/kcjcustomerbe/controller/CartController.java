package com.kcjcustomerbe.controller;

import com.kcjcustomerbe.controller.interfaces.CartControllerInterface;
import com.kcjcustomerbe.dto.CartProductDto;
import com.kcjcustomerbe.dto.OrderDto;
import com.kcjcustomerbe.service.CartService;
import com.kcjcustomerbe.validation.UuidFormatChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController implements CartControllerInterface {

   private final CartService cartService;

   // READ - CUSTOMER
   @GetMapping
   public String getCustomerCart() {
      return null;
   }

   // CREATE - ADD PRODUCT TO CART
   @PutMapping("/addToCart")
   public ResponseEntity<CartProductDto> addProductToCart(@UuidFormatChecker @RequestParam String cartId,
                                                          @RequestParam Long productId) {

      return ResponseEntity.ok(cartService.addProductToCart(UUID.fromString(cartId), productId));
   }

   // CREATE NEW ORDER
   @PostMapping("/payCart")
   public ResponseEntity<OrderDto> payCart(@UuidFormatChecker @RequestParam String cartId) {
      OrderDto dto = cartService.createOrder(UUID.fromString(cartId));
      cartService.clearCart(UUID.fromString(cartId));
      return ResponseEntity.created(URI.create("/cart")).body(dto);
   }

   // DELETE - CLEAR CART
   @DeleteMapping("/clearCart")
   public ResponseEntity<HttpStatus> clearCart(@UuidFormatChecker @RequestParam String cartId) {
      cartService.clearCart(UUID.fromString(cartId));
      return ResponseEntity.ok(HttpStatus.OK);
   }
}
