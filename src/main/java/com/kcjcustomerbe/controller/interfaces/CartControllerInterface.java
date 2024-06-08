package com.kcjcustomerbe.controller.interfaces;

import com.kcjcustomerbe.config.SwaggerConfig;
import com.kcjcustomerbe.dto.CartDto;
import com.kcjcustomerbe.dto.CartProductDto;
import com.kcjcustomerbe.dto.OrderDto;
import com.kcjcustomerbe.dto.customer.CustomerDto;
import com.kcjcustomerbe.exception.handler.ResponseExceptionHandler;
import com.kcjcustomerbe.validation.UuidFormatChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
   ResponseEntity<CartProductDto> addProductToCart(@UuidFormatChecker @RequestParam String cartId,
                                                   @RequestParam Long productId);

   @Operation(
         summary = "Pay for items in the cart",
         description = "Allows you to pay for the items in the cart with the specified cart ID. Requires authorization.",
         parameters = {
               @Parameter(
                     name = "cartId",
                     description = "The unique identifier of the cart",
                     required = true,
                     in = ParameterIn.QUERY,
                     schema = @Schema(type = "string", format = "string"),
                     examples = {
                           @ExampleObject(
                                 name = "Example request with correct ID",
                                 value = "d234d99d-170e-42f7-b6ae-435ee56f49a1"
                           ),
                           @ExampleObject(
                                 name = "Example request with non-exist ID",
                                 value = "12345678-170e-42f7-b6ae-435ee56f49a1"
                           ),
                           @ExampleObject(
                                 name = "Example request with invalid ID",
                                 value = "d234d99d-170e-42f7-b6ae-435ee56f49b1!"
                           )
                     }
               )
         },
         responses = {
               @ApiResponse(
                     responseCode = "201",
                     description = "The cart has been payed",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = OrderDto.class)
                     )
               ),
               @ApiResponse(
                     responseCode = "400",
                     description = "Invalid ID",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = ResponseExceptionHandler.class)
                     )
               ),
               @ApiResponse(
                     responseCode = "404",
                     description = "Cart not found",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = ResponseExceptionHandler.class)
                     )
               )
         }
   )
   ResponseEntity<OrderDto> payCart(@UuidFormatChecker @RequestParam String cartId);


   @Operation(
         summary = "Clean cart by ID",
         description = "Retrieve an cart by its unique identifier",
         parameters = {
               @Parameter(
                     name = "cartId",
                     description = "The unique identifier of the cart",
                     required = true,
                     in = ParameterIn.QUERY,
                     schema = @Schema(type = "string", format = "string"),
                     examples = {
                           @ExampleObject(
                                 name = "Example request with correct ID",
                                 value = "d234d99d-170e-42f7-b6ae-435ee56f49b1"
                           ),
                           @ExampleObject(
                                 name = "Example request with non-exist ID",
                                 value = "12345678-170e-42f7-b6ae-435ee56f49a1"
                           ),
                           @ExampleObject(
                                 name = "Example request with invalid ID",
                                 value = "d234d99d-170e-42f7-b6ae-435ee56f49b1!"
                           )
                     }
               )
         },
         responses = {
               @ApiResponse(
                     responseCode = "200",
                     description = "The cart has been cleaned"
               ),
               @ApiResponse(
                     responseCode = "400",
                     description = "Invalid ID",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = ResponseExceptionHandler.class)
                     )
               ),
               @ApiResponse(
                     responseCode = "404",
                     description = "Cart not found",
                     content = @Content(
                           mediaType = "application/json",
                           schema = @Schema(implementation = ResponseExceptionHandler.class)
                     )
               )
         }
   )
   ResponseEntity<HttpStatus> clearCart(@UuidFormatChecker @RequestParam String cartId);
}
