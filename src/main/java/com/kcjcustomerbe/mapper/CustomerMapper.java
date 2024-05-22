package com.kcjcustomerbe.mapper;

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

/**
 * This mapper assists in converting between `Customer` entity and its respective DTO objects.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
      unmappedTargetPolicy = ReportingPolicy.IGNORE,
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
      uses = {ProductMapper.class, OrderMapper.class})
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
   Customer mapCustomerFromDto(CustomerCreateDto dto);

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
         @Mapping(target = "id", source = "entity.id"),
         @Mapping(target = "firstName", source = "entity.firstName"),
         @Mapping(target = "lastName", source = "entity.lastName"),
         @Mapping(target = "email", source = "entity.email"),
         @Mapping(target = "username", source = "entity.username"),
         @Mapping(target = "phoneNumber", source = "entity.phoneNumber"),
         @Mapping(target = "address", source = "entity.address"),
         @Mapping(target = "postalCode", source = "entity.postalCode"),
         @Mapping(target = "createdAt", ignore = true),
         @Mapping(target = "updatedAt", ignore = true),
         @Mapping(target = "role", source = "entity.role"),
         @Mapping(target = "isBlocked", source = "entity.isBlocked"),
         @Mapping(target = "ordersDto", source = "entity.orders"),
         @Mapping(target = "cartDto", source = "entity.cart"),
         @Mapping(target = "reviewsDto", source = "entity.reviews")
   })
   CustomerDto mapToCustomerDto(Customer entity);

   @Mappings({
         @Mapping(target = "id", source = "id"),
         @Mapping(target = "customerDto", ignore = true),
         @Mapping(target = "cartProductsDto", source = "cartProducts")
         })
   CartDto mapToCartDto(Cart entity);

   @Mapping(target = "id", source = "id")
   @Mapping(target = "cartDto", ignore = true)
   @Mapping(target = "quantity", source = "quantity")
   @Mapping(target = "productDto", source = "product")
   @Mapping(target = "cratedAt", source = "cratedAt")
   List<CartProductDto> mapToCartProductsDto(List<CartProduct> dtos);
}
