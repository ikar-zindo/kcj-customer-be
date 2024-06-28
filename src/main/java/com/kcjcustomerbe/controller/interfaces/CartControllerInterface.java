package com.kcjcustomerbe.controller.interfaces;

import com.kcjcustomerbe.config.SwaggerConfig;
import com.kcjcustomerbe.dto.CartProductDto;
import com.kcjcustomerbe.dto.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = SwaggerConfig.CART, description = "allows you to receive information about customer cart")
public interface CartControllerInterface {


   @Operation(
         summary = "Get customer cart",
         description = "Allows you to retrieve the cart information for the authenticated customer. Requires authorization."
   )
   String getCustomerCart();


   @Operation(
         summary = "Add product to cart",
         description = "Allows you to add a product to the cart with the specified cart ID and product ID. Requires authorization."
   )
   ResponseEntity<CartProductDto> addProductToCart(@RequestParam Long productId);

   @Operation(
         summary = "Pay for items in the cart",
         description = "Allows you to pay for the items in the cart with the specified cart ID. Requires authorization."
   )
   ResponseEntity<OrderDto> payCart();


   @Operation(
         summary = "Clean cart by ID",
         description = "Retrieve an cart by its unique identifier"
   )
   ResponseEntity<HttpStatus> clearCart();
}
