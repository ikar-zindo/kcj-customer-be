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
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

   /**
    * This method converts a `CustomerCreateDto` to a `Customer` entity.
    *
    * It maps:
    * - firstName, lastName, email, username, password, phoneNumber, address, and postalCode from DTO to entity.
    * - Sets the `createdAt` field to the current LocalDateTime.
    * - Sets the `isBlocked` field to false, if not provided in DTO.
    *
    * @param customerCreateDto The `CustomerCreateDto` to convert.
    * @return The converted `Customer` entity.
    */
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

   /**
    * This method is executes after the `createCustomerFromDto` method.
    *
    * It creates a new Cart for the customer, and sets the customer-cart association in both directions.
    *
    * @param customer The `Customer` that was created, to associate with a new Cart.
    */
   @AfterMapping
   default void createCart(@MappingTarget Customer customer) {
      Cart cart = new Cart();
      cart.setCustomer(customer);
      customer.setCart(cart);
   }

   /**
    * This method converts a `Customer` to a `CustomerAfterCreateDto`.
    *
    * It simply maps `id` field from entity to DTO.
    *
    * @param customerAfterCreation The `Customer` to convert.
    * @return The converted `CustomerAfterCreateDto`.
    */
   @Mapping(target = "id", source = "id")
   CustomerAfterCreateDto convertToCustomerAfterCreateDto(Customer customerAfterCreation);

// =====================================================================================================================

   /**
    * This method converts a `Customer` entity to a `CustomerDto`.
    *
    * It maps:
    * - firstName, lastName, email, username, phoneNumber, address, postalCode, isBlocked from entity to DTO.
    * - Additionally, it includes mapping from `Customer`'s `Cart` to `CartDto`.
    *
    * @param customer The `Customer` entity to convert.
    * @return The converted `CustomerDto`.
    */
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

   /**
    * This method converts a `Cart` entity to a `CartDto`.
    *
    * It maps:
    * - id from entity to DTO.
    * - Includes mapping from `CartProduct` list in the Cart entity to `CartProductDto` list in the DTO
    *
    * @param cart The `Cart` entity to convert.
    * @return The converted `CartDto`.
    */
   @Mapping(target = "id", source = "id")
   @Mapping(target = "cartProductsDto", source = "cartProducts")
   CartDto convertToCartDto(Cart cart);

   /**
    * This method converts a list of `CartProduct` entities to a list of `CartProductDto`.
    *
    * @param cartProducts The list of `CartProduct` entities to convert.
    * @return The converted list of `CartProductDto`.
    */
   List<CartProductDto> convertToCartProductsDto(List<CartProduct> cartProducts);
}
