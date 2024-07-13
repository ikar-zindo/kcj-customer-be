package com.kcj_customer_be.controller.interfaces;

import com.kcj_customer_be.config.SwaggerConfig;
import com.kcj_customer_be.dto.CartProductDto;
import com.kcj_customer_be.dto.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = SwaggerConfig.CART, description = "allows you to receive information about customer cart")
public interface CartControllerInterface {

   // READ - CUSTOMER
   @Operation(
         summary = "Get customer cart",
         description = "Allows you to retrieve the cart information for the authenticated customer. Requires authorization."
   )
   ResponseEntity<List<CartProductDto>> getCartProducts();

   // CREATE - ADD PRODUCT TO CART
   @Operation(
         summary = "Add product to cart",
         description = "Allows you to add a product to the cart with the specified cart ID and product ID. Requires authorization."
   )
   ResponseEntity<CartProductDto> addProductToCart(@RequestParam Long productId);

   // CREATE NEW ORDER
   @Operation(
         summary = "Pay for items in the cart",
         description = "Allows you to pay for the items in the cart with the specified cart ID. Requires authorization."
   )
   ResponseEntity<OrderDto> payCart();

   // DELETE - CLEAR CART
   @Operation(
         summary = "Clean cart",
         description = "Allows you to clear items in the cart with the specified cart ID. Authorization required."
   )
   ResponseEntity<HttpStatus> clearCart();
}