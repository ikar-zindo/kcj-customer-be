package com.kcjcustomerbe.mapper.mapsruct;

import com.kcjcustomerbe.dto.CartDto;
import com.kcjcustomerbe.dto.CartProductDto;
import com.kcjcustomerbe.dto.customer.CustomerAfterCreateDto;
import com.kcjcustomerbe.dto.customer.CustomerCreateDto;
import com.kcjcustomerbe.dto.customer.CustomerDto;
import com.kcjcustomerbe.entity.Cart;
import com.kcjcustomerbe.entity.CartProduct;
import com.kcjcustomerbe.entity.Customer;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

   @Mapping(target = "firstName", source = "firstName")
   @Mapping(target = "lastName", source = "lastName")
   @Mapping(target = "email", source = "email")
   @Mapping(target = "username", source = "username")
   @Mapping(target = "password", source = "password")
   @Mapping(target = "phoneNumber", source = "phoneNumber")
   @Mapping(target = "address", source = "address")
   @Mapping(target = "postalCode", source = "postalCode")
   @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
   @Mapping(target = "isBlocked", defaultValue = "false")
   Customer createCustomerFromDto(CustomerCreateDto customerCreateDto);

   @AfterMapping
   default void createCart(@MappingTarget Customer customer) {
      Cart cart = new Cart();
      cart.setCustomer(customer);
      customer.setCart(cart);
   }

   @Mapping(target = "id", source = "id")
   CustomerAfterCreateDto convertToCustomerAfterCreateDto(Customer customerAfterCreation);

   // ==================================================================================================================

   @Mapping(target = "firstName", source = "customer.firstName")
   @Mapping(target = "lastName", source = "customer.lastName")
   @Mapping(target = "email", source = "customer.email")
   @Mapping(target = "username", source = "customer.username")
   @Mapping(target = "phoneNumber", source = "customer.phoneNumber")
   @Mapping(target = "address", source = "customer.address")
   @Mapping(target = "postalCode", source = "customer.postalCode")
   @Mapping(target = "cartDto", source = "customer.cart")
   @Mapping(target = "isBlocked", source = "customer.isBlocked")
   CustomerDto convertToCustomerDto(Customer customer);

   @Mapping(target = "id", source = "id")
   @Mapping(target = "cartProductsDto", source = "cartProducts")
   CartDto convertToCartDto(Cart cart);

   List<CartProductDto> convertToCartProductsDto(List<CartProduct> cartProducts);
}
