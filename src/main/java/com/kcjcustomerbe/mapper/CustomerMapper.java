package com.kcjcustomerbe.mapper;

import com.kcjcustomerbe.constant.GlobalConstant;
import com.kcjcustomerbe.dto.customer.*;
import com.kcjcustomerbe.entity.Cart;
import com.kcjcustomerbe.entity.Customer;
import com.kcjcustomerbe.util.MappingUtils;
import org.mapstruct.*;

import java.util.Objects;

/**
 * This mapper assists in converting between `Customer` entity and its respective DTO objects.
 * It also handles the conversion between `Cart`, `CartProduct` entities and their respective DTOs.
 * <p>
 * Uses MapStruct library for mapping, which generates the implementation at compile time.
 * <p>
 * Mapping is performed by matching the `source` and `target` fields as specified in each `@Mapping`.
 * <p>
 * Unmapped fields are ignored due to `unmappedTargetPolicy = ReportingPolicy.IGNORE`.
 * Mapping for null properties are ignored due to `nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE`.
 * <p>
 * This Mapper is automatically deployed by Spring, using components scanning.
 * The implementation of the methods in this interface is generated by MapStruct and should not be manually implemented.
 * <p>
 * `ProductMapper` and `OrderMapper` are used for nested mappings.
 * <p>
 * Additionally, `MappingUtils` is used to handle specific mapping logic for null or empty fields.
 *
 * @author Ivan Bukrieiev
 * @see CartMapper
 * @see OrderMapper
 * @see Objects
 * @see MappingUtils
 * @see GlobalConstant
 * @since v1.0.0
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
      unmappedTargetPolicy = ReportingPolicy.IGNORE,
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
      uses = {OrderMapper.class, CartMapper.class},
      imports = {Objects.class, MappingUtils.class, GlobalConstant.class})
public interface CustomerMapper {

   /**
    * Maps a `CustomerCreateDto` to a `Customer` entity.
    *
    * @param dto The `CustomerCreateDto` to be mapped.
    * @return The mapped `Customer` entity. If the source `CustomerCreateDto` is null, return null.
    */
   @Mappings({
         @Mapping(target = "firstName", source = "firstName"),
         @Mapping(target = "lastName", source = "lastName"),
         @Mapping(target = "email", source = "email"),
         @Mapping(target = "password", source = "password"),
         @Mapping(target = "phoneNumber", source = "phoneNumber"),
         @Mapping(target = "address", source = "address"),
         @Mapping(target = "postalCode", source = "postalCode"),
         @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),
         @Mapping(target = "isBlocked", defaultValue = "false")
   })
   Customer mapCustomerFromCustomerCreateDto(CustomerCreateDto dto);

   /**
    * Creates a `Cart` entity and sets it to the `Customer` entity after mapping.
    *
    * @param entity The `Customer` entity to which the `Cart` is to be set.
    */
   @AfterMapping
   default void createCart(@MappingTarget Customer entity) {
      Cart cart = new Cart();
      cart.setCustomer(entity);
      entity.setCart(cart);
   }

   /**
    * Maps a `Customer` entity to a `CustomerAfterCreateDto`.
    *
    * @param entity The `Customer` entity to be mapped.
    * @return The mapped `CustomerAfterCreateDto`. If the source `Customer` entity is null, return null.
    */
   @Mapping(target = "id", source = "id")
   @Mapping(target = "status", expression = "java(com.kcjcustomerbe.constant.GlobalConstant.CUSTOMER_CREATED_SUCCESS_MESSAGE)")
   CustomerResponseDto mapToCustomerAfterCreateDto(Customer entity);

// =====================================================================================================================

   /**
    * Maps a `Customer` entity to a `CustomerDto`.
    *
    * @param entity The `Customer` entity to be mapped.
    * @return The mapped `CustomerDto`. If the source `Customer` entity is null, return null.
    */
   @Named("mapToCustomerDto")
   @Mappings({
         @Mapping(target = "id", source = "id"),
         @Mapping(target = "firstName", source = "firstName"),
         @Mapping(target = "lastName", source = "lastName"),
         @Mapping(target = "email", source = "email"),
         @Mapping(target = "phoneNumber", source = "phoneNumber"),
         @Mapping(target = "address", source = "address"),
         @Mapping(target = "postalCode", source = "postalCode"),
         @Mapping(target = "createdAt", ignore = true),
         @Mapping(target = "updatedAt", ignore = true),
         @Mapping(target = "role", source = "role"),
         @Mapping(target = "isBlocked", source = "isBlocked"),
         @Mapping(target = "cartDto", source = "cart", qualifiedByName = "mapToCartDto"),
         @Mapping(target = "ordersDto", source = "orders"),
         @Mapping(target = "reviewsDto", source = "reviews")
   })
   CustomerDto mapToCustomerDto(Customer entity);

   @Named("mapToCustomerDtoWithoutCart")
   @Mappings({
         @Mapping(target = "id", source = "id"),
         @Mapping(target = "firstName", source = "firstName"),
         @Mapping(target = "lastName", source = "lastName"),
         @Mapping(target = "email", source = "email"),
         @Mapping(target = "phoneNumber", source = "phoneNumber"),
         @Mapping(target = "address", source = "address"),
         @Mapping(target = "postalCode", source = "postalCode"),
         @Mapping(target = "createdAt", ignore = true),
         @Mapping(target = "updatedAt", ignore = true),
         @Mapping(target = "role", source = "role"),
         @Mapping(target = "isBlocked", source = "isBlocked"),
         @Mapping(target = "cartDto", source = "cart", qualifiedByName = "mapToCartDto"),
         @Mapping(target = "ordersDto", source = "orders"),
         @Mapping(target = "reviewsDto", source = "reviews")
   })
   CustomerDto mapToCustomerDtoWithoutCart(Customer entity);

   @Mappings({
         @Mapping(target = "id", source = "id"),
         @Mapping(target = "firstName", source = "firstName"),
         @Mapping(target = "lastName", source = "lastName"),
         @Mapping(target = "email", source = "email"),
         @Mapping(target = "password", ignore = true),
         @Mapping(target = "phoneNumber", source = "phoneNumber"),
         @Mapping(target = "address", source = "address"),
         @Mapping(target = "postalCode", source = "postalCode"),
         @Mapping(target = "createdAt", ignore = true),
         @Mapping(target = "updatedAt", ignore = true),
         @Mapping(target = "role", source = "role"),
         @Mapping(target = "isBlocked", source = "isBlocked"),
         @Mapping(target = "cart", source = "cartDto"),
         @Mapping(target = "orders", source = "ordersDto"),
         @Mapping(target = "reviews", source = "reviewsDto")
   })
   Customer mapToCustomer(CustomerDto dto);

   // ==================================================================================================================

   /**
    * Maps a `CustomerUpdateDto` to a `Customer` entity, updating only non-null and non-empty fields.
    *
    * @param dto    The `CustomerUpdateDto` to be mapped.
    * @param entity The existing `Customer` entity to be updated.
    * @return The updated `Customer` entity. If the source `CustomerUpdateDto` is null, return the existing entity.
    */
   @Mappings({
         @Mapping(target = "firstName", expression = "java(MappingUtils.mapNullOrEmpty(dto.getFirstName(), entity.getFirstName()))"),
         @Mapping(target = "lastName", expression = "java(MappingUtils.mapNullOrEmpty(dto.getLastName(), entity.getLastName()))"),
         @Mapping(target = "email", expression = "java(MappingUtils.mapNullOrEmpty(dto.getEmail(), entity.getEmail()))"),
         @Mapping(target = "password", ignore = true),
         @Mapping(target = "phoneNumber", expression = "java(MappingUtils.mapNullOrEmpty(dto.getPhoneNumber(), entity.getPhoneNumber()))"),
         @Mapping(target = "address", expression = "java(MappingUtils.mapNullOrEmpty(dto.getAddress(), entity.getAddress()))"),
         @Mapping(target = "postalCode", expression = "java(MappingUtils.mapNullOrEmpty(dto.getPostalCode(), entity.getPostalCode()))"),
         @Mapping(target = "createdAt", ignore = true),
         @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())"),
         @Mapping(target = "role", ignore = true),
         @Mapping(target = "entity.isBlocked", source = "dto.isBlocked"),
         @Mapping(target = "cart", ignore = true),
         @Mapping(target = "orders", ignore = true),
         @Mapping(target = "reviews", ignore = true),
   })
   Customer mapCustomerFromCustomerUpdateDto(CustomerUpdateDto dto, @MappingTarget Customer entity);

   /**
    * Maps a `Customer` entity to a `CustomerAfterUpdateDto`.
    *
    * @param entity The `Customer` entity to be mapped.
    * @return The mapped `CustomerAfterUpdateDto`. If the source `Customer` entity is null, return null.
    */
   @Mapping(target = "id", source = "id")
   @Mapping(target = "status", expression = "java(com.kcjcustomerbe.constant.GlobalConstant.CUSTOMER_UPDATED_SUCCESS_MESSAGE)")
   CustomerResponseDto mapToCustomerAfterUpdateDto(Customer entity);

   @Mapping(target = "id", source = "id")
   @Mapping(target = "status", expression = "java(com.kcjcustomerbe.constant.GlobalConstant.CUSTOMER_BLOCKED_SUCCESS_MESSAGE)")
   CustomerResponseDto mapToCustomerAfterBlockedDto(Customer entity);
}
