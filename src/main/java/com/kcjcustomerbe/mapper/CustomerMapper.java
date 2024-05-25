package com.kcjcustomerbe.mapper;

import com.kcjcustomerbe.dto.CartDto;
import com.kcjcustomerbe.dto.CartProductDto;
import com.kcjcustomerbe.dto.customer.*;
import com.kcjcustomerbe.entity.Cart;
import com.kcjcustomerbe.entity.CartProduct;
import com.kcjcustomerbe.entity.Customer;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * This mapper assists in converting between `Customer` entity and its respective DTO objects.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
      unmappedTargetPolicy = ReportingPolicy.IGNORE,
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
      uses = {ProductMapper.class, OrderMapper.class},
      imports = {Objects.class})
public interface CustomerMapper {

   @Mappings({
      @Mapping(target = "firstName", source = "firstName"),
      @Mapping(target = "lastName", source = "lastName"),
      @Mapping(target = "email", source = "email"),
      @Mapping(target = "username", source = "username"),
      @Mapping(target = "password", source = "password"),
      @Mapping(target = "phoneNumber", source = "phoneNumber"),
      @Mapping(target = "address", source = "address"),
      @Mapping(target = "postalCode", source = "postalCode"),
      @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),
      @Mapping(target = "isBlocked", defaultValue = "false")
   })
   Customer mapCustomerFromCustomerCreateDto(CustomerCreateDto dto);

   @AfterMapping
   default void createCart(@MappingTarget Customer entity) {
      Cart cart = new Cart();
      cart.setCustomer(entity);
      entity.setCart(cart);
   }

   @Mapping(target = "id", source = "id")
   CustomerAfterCreateDto mapToCustomerAfterCreateDto(Customer entity);

// =====================================================================================================================

   @Mappings({
      @Mapping(target = "id", source = "id"),
      @Mapping(target = "firstName", source = "firstName"),
      @Mapping(target = "lastName", source = "lastName"),
      @Mapping(target = "email", source = "email"),
      @Mapping(target = "username", source = "username"),
//      @Mapping(target = "password", ignore = true),
      @Mapping(target = "phoneNumber", source = "phoneNumber"),
      @Mapping(target = "address", source = "address"),
      @Mapping(target = "postalCode", source = "postalCode"),
      @Mapping(target = "createdAt", ignore = true),
      @Mapping(target = "updatedAt", ignore = true),
      @Mapping(target = "role", source = "role"),
      @Mapping(target = "isBlocked", source = "isBlocked"),
      @Mapping(target = "cartDto", source = "cart"),
      @Mapping(target = "ordersDto", source = "orders"),
      @Mapping(target = "reviewsDto", source = "reviews")
   })
   CustomerDto mapToCustomerDto(Customer entity);

   @Mappings({
      @Mapping(target = "id", source = "id"),
      @Mapping(target = "customerDto", ignore = true),
      @Mapping(target = "cartProductsDto", source = "cartProducts")
   })
   CartDto mapToCartDto(Cart entity);

   @Mappings({
      @Mapping(target = "id", source = "id"),
      @Mapping(target = "cartDto", ignore = true),
      @Mapping(target = "quantity", source = "quantity"),
      @Mapping(target = "productDto", source = "product"),
      @Mapping(target = "cratedAt", source = "cratedAt")
   })
   List<CartProductDto> mapToCartProductsDto(List<CartProduct> dtos);

   // ==================================================================================================================

   @Mappings({
      @Mapping(target = "id", ignore = true),
      @Mapping(target = "firstName", expression = "java(Objects.isNull(dto.getFirstName()) || dto.getFirstName().isEmpty() ? entity.getFirstName() : dto.getFirstName())"),
      @Mapping(target = "lastName", expression = "java(Objects.isNull(dto.getLastName()) || dto.getLastName().isEmpty() ? entity.getLastName() : dto.getLastName())"),
//      @Mapping(target = "birthday", source = "birthday", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE),
      @Mapping(target = "email", expression = "java(Objects.isNull(dto.getEmail()) || dto.getEmail().isEmpty() ? entity.getEmail() : dto.getEmail())"),
      @Mapping(target = "username", expression = "java(Objects.isNull(dto.getUsername()) || dto.getUsername().isEmpty() ? entity.getUsername() : dto.getUsername())"),
      @Mapping(target = "password", ignore = true),
      @Mapping(target = "phoneNumber", expression = "java(Objects.isNull(dto.getPhoneNumber()) || dto.getPhoneNumber().isEmpty() ? entity.getPhoneNumber() : dto.getPhoneNumber())"),
      @Mapping(target = "address", expression = "java(Objects.isNull(dto.getAddress()) || dto.getAddress().isEmpty() ? entity.getAddress() : dto.getAddress())"),
      @Mapping(target = "postalCode", expression = "java(Objects.isNull(dto.getPostalCode()) || dto.getPostalCode().isEmpty() ? entity.getPostalCode() : dto.getPostalCode())"),
      @Mapping(target = "createdAt", ignore = true),
      @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())"),
      @Mapping(target = "role", ignore = true),
      @Mapping(target = "isBlocked", ignore = true),
      @Mapping(target = "cart", ignore = true),
      @Mapping(target = "orders", ignore = true),
      @Mapping(target = "reviews", ignore = true),
   })
   Customer mapCustomerFromCustomerUpdateDto(CustomerUpdateDto dto, @MappingTarget Customer entity);

   @Mapping(target = "id", source = "id")
   CustomerAfterUpdateDto mapToCustomerAfterUpdateDto(Customer entity);
}
