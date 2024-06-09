package com.kcjcustomerbe.service;

import com.kcjcustomerbe.dto.CartDto;
import com.kcjcustomerbe.dto.CartProductDto;
import com.kcjcustomerbe.dto.OrderDto;
import com.kcjcustomerbe.dto.customer.CustomerDto;
import com.kcjcustomerbe.validation.UuidFormatChecker;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface CartService {

   CartDto getCartById(@UuidFormatChecker UUID cartId);

   CustomerDto getCustomerByCartId(@UuidFormatChecker UUID cartId);

   List<CartProductDto> getCartProductsByCartId(@UuidFormatChecker UUID cartId);

   CartProductDto addProductToCart(@UuidFormatChecker UUID cartId, Long productId);

   BigDecimal getTotalCartById(@UuidFormatChecker UUID cartId);

   Integer getCartProductsSize(@UuidFormatChecker UUID catId);

   OrderDto createOrder(@UuidFormatChecker UUID cartId);

   void clearCart(@UuidFormatChecker UUID cartId);
}
