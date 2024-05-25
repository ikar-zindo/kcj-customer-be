package com.kcjcustomerbe.mapper;

import com.kcjcustomerbe.dto.CartDto;
import com.kcjcustomerbe.dto.CartProductDto;
import com.kcjcustomerbe.entity.Cart;
import com.kcjcustomerbe.entity.CartProduct;
import org.mapstruct.*;

import java.util.List;

/**
 * Interface for mapping `Cart` and `CartProduct` entities to their respective DTOs and vice versa.
 * Uses MapStruct library for mapping, which generates the implementation at compile time.
 *
 * Mapping is performed by matching the `source` and `target` fields as specified in each `@Mapping`.
 *
 * Unmapped fields are ignored due to `unmappedTargetPolicy = ReportingPolicy.IGNORE`.
 * Mapping for null properties are ignored due to `nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE`.
 *
 * This Mapper is automatically deployed by Spring, using components scanning.
 * The implementation of the methods in this interface is generated by MapStruct and should not be manually implemented.
 *
 * @author Ivan Bukrieiev
 * @since v1.0.0
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
      unmappedTargetPolicy = ReportingPolicy.IGNORE,
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
      uses = ProductMapper.class)
public interface CartMapper {

   /**
    * Maps a `Cart` entity to a `CartDto`.
    *
    * @param entity The `Cart` entity to be mapped.
    * @return The mapped `CartDto`. If the source `Cart` entity is null, return null.
    */
   @Mapping(target = "id", source = "id")
   @Mapping(target = "customerDto", ignore = true)
   @Mapping(target = "cartProductsDto", source = "cartProducts")
   CartDto mapToCartDto(Cart entity);

   /**
    * Maps a `CartProduct` entity to a `CartProductDto`.
    *
    * @param entity The `CartProduct` entity to be mapped.
    * @return The mapped `CartProductDto`. If the source `CartProduct` entity is null, return null.
    */
   @Mapping(target = "id", source = "id")
   @Mapping(target = "cartDto", ignore = true)
   @Mapping(target = "productDto", source = "product")
   @Mapping(target = "quantity", source = "quantity")
   @Mapping(target = "createdAt", source = "createdAt")
   CartProductDto mapToCartProductDto(CartProduct entity);

   /**
    * Maps a `CartDto` to a `Cart` entity.
    *
    * @param dto The `CartDto` to be mapped.
    * @return The mapped `Cart` entity. If the source `CartDto` is null, return null.
    */
   @Mapping(target = "id", ignore = true)
   @Mapping(target = "customer", ignore = true)
   @Mapping(target = "cartProducts", source = "cartProductsDto")
   Cart mapToCart(CartDto dto);

   /**
    * Maps a `CartProductDto` to a `CartProduct` entity.
    *
    * @param dto The `CartProductDto` entity to be mapped.
    * @return The mapped `CartProduct` entity. If the source `CartProductDto` is null, return null.
    */
   @Mapping(target = "id", source = "id")
   @Mapping(target = "cart", ignore = true)
   @Mapping(target = "product", source = "productDto")
   @Mapping(target = "quantity", source = "quantity")
   @Mapping(target = "createdAt", source = "createdAt")
   CartProduct mapToCartProduct(CartProductDto dto);

   /**
    * Maps a list of `CartProduct` entities to a list of `CartProductDto`.
    *
    * @param entities The list of `CartProduct` entities to be mapped.
    * @return The list of mapped `CartProductDto`. If the source list is null or empty, return an empty list.
    */
   List<CartProductDto> mapToCartProductDtoList(List<CartProduct> entities);

   /**
    * Maps a list of `CartProductDto` to a list of `CartProduct` entities.
    *
    * @param dtos The list of `CartProductDto` to be mapped.
    * @return The list of mapped `CartProduct` entities. If the source list is null or empty, return an empty list.
    */
   List<CartProduct> mapToCartProductList(List<CartProductDto> dtos);
}
