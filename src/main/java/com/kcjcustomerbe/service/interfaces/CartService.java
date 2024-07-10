package com.kcjcustomerbe.service.interfaces;

import com.kcjcustomerbe.dto.CartDto;
import com.kcjcustomerbe.dto.CartProductDto;
import com.kcjcustomerbe.dto.OrderDto;
import com.kcjcustomerbe.dto.customer.CustomerDto;
import com.kcjcustomerbe.validation.UuidFormatChecker;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface CartService {

   /**
    * Retrieves the cart details by its unique identifier.
    *
    * @param cartId The UUID of the cart to retrieve.
    * @return The CartDto containing cart details.
    */
   CartDto getCartById(@UuidFormatChecker UUID cartId);

   /**
    * Retrieves the customer details associated with the cart.
    *
    * @param cartId The UUID of the cart to retrieve customer details.
    * @return The CustomerDto containing customer details.
    */
   CustomerDto getCustomerByCartId(@UuidFormatChecker UUID cartId);

   /**
    * Retrieves the list of products in the cart identified by cartId.
    *
    * @param cartId The UUID of the cart to retrieve products from.
    * @return List of CartProductDto representing products in the cart.
    */
   List<CartProductDto> getCartProductsByCartId(@UuidFormatChecker UUID cartId);

   /**
    * Adds a product to the cart specified by cartId.
    *
    * @param cartId    The UUID of the cart to add the product to.
    * @param productId The ID of the product to add to the cart.
    * @return The CartProductDto representing the added product in the cart.
    */
   CartProductDto addProductToCart(@UuidFormatChecker UUID cartId, Long productId);

   /**
    * Calculates the total price of the cart identified by cartId.
    *
    * @param cartId The UUID of the cart to calculate the total price.
    * @return The total price of the cart as a BigDecimal.
    */
   BigDecimal getTotalCartById(@UuidFormatChecker UUID cartId);

   /**
    * Retrieves the number of products in the cart identified by cartId.
    *
    * @param cartId The UUID of the cart to count the products.
    * @return The number of products in the cart as an Integer.
    */
   Integer getCartProductsSize(@UuidFormatChecker UUID cartId);

   /**
    * Creates an order from the cart identified by cartId.
    *
    * @param cartId The UUID of the cart to create an order from.
    * @return The OrderDto representing the created order.
    */
   OrderDto createOrder(@UuidFormatChecker UUID cartId);

   /**
    * Clears all products from the cart identified by cartId.
    *
    * @param cartId The UUID of the cart to clear.
    */
   void clearCart(@UuidFormatChecker UUID cartId);
}
