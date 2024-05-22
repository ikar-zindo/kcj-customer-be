package com.kcjcustomerbe.mapper;

import com.kcjcustomerbe.dto.ProductDto;
import com.kcjcustomerbe.entity.Product;
import org.mapstruct.*;

import java.util.List;

/**
 * This mapper helps in the conversion between `Product` entity and `ProductDto`.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
      unmappedTargetPolicy = ReportingPolicy.IGNORE,
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
      uses = RestaurantMapper.class)
public interface ProductMapper {

   /**
    * This method takes a `Product` object and maps it to a `ProductDto`.
    * <p>
    * It maps:
    * - id, name, description, price, imageUrl, isAvailable from the entity to the DTO
    * - It also maps the associated `RestaurantDto`
    *
    * @param entity The `Product` entity to convert.
    * @return The converted `ProductDto`.
    */
   @Mapping(target = "id", source = "id")
   @Mapping(target = "name", source = "name")
   @Mapping(target = "description", source = "description")
   @Mapping(target = "price", source = "price")
   @Mapping(target = "imageUrl", source = "imageUrl")
   @Mapping(target = "isAvailable", source = "isAvailable")
   @Mapping(target = "restaurantDto", source = "restaurant")
   ProductDto mapToProductDto(Product entity);

   /**
    * This method maps the list of `Product` entities to a list of `ProductDto`.
    * <p>
    * It leverages the `convertToProductDto` method for the mapping of individual `Product` entities.
    *
    * @param products The list of `Product` entities to convert.
    * @return The converted list of `ProductDto`.
    */
   List<ProductDto> mapToProductsDto(List<Product> products);
}
