package com.kcjcustomerbe.service;

import com.kcjcustomerbe.dto.CartDto;
import com.kcjcustomerbe.dto.CartProductDto;
import com.kcjcustomerbe.dto.OrderDto;
import com.kcjcustomerbe.dto.customer.CustomerDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface CartService {

   CartDto getCartById(UUID cartId);

   CustomerDto getCustomerByCartId(UUID cartId);

   List<CartProductDto> getCartProductsByCartId(UUID cartId);

   CartProductDto addProductToCart(UUID cartId, Long productId);

   BigDecimal getTotalCartById(UUID cartId);

   Integer getCartProductsSize(UUID catId);

   OrderDto createOrder(UUID cartId);

   Boolean isPay(UUID customerId, UUID cartId);

   void clearCart(UUID cartId);
}
