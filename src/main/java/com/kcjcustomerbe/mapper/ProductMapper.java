package com.kcjcustomerbe.mapper;

import com.kcjcustomerbe.dto.ProductDto;
import com.kcjcustomerbe.entity.Product;
import org.mapstruct.*;

import java.util.List;

/**
 * This mapper helps in the conversion between `Product` entity and `ProductDto`.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

   /**
    * This method takes a `Product` object and maps it to a `ProductDto`.
    *
    * It maps:
    * - id, name, description, price, imageUrl, isAvailable from the entity to the DTO
    * - It also maps the associated `RestaurantDto`
    *
    * @param product The `Product` entity to convert.
    * @return The converted `ProductDto`.
    */
   @Mapping(target = "id", source = "id")
   @Mapping(target = "name", source = "name")
   @Mapping(target = "description", source = "description")
   @Mapping(target = "price", source = "price")
   @Mapping(target = "imageUrl", source = "imageUrl")
   @Mapping(target = "isAvailable", source = "isAvailable")
   @Mapping(target = "restaurantDto", source = "restaurantDto")
   ProductDto convertToProductDto(Product product);

   /**
    * This method maps the list of `Product` entities to a list of `ProductDto`.
    *
    * It leverages the `convertToProductDto` method for the mapping of individual `Product` entities.
    *
    * @param products The list of `Product` entities to convert.
    * @return The converted list of `ProductDto`.
    */
   List<ProductDto> convertToProductsDto(List<Product> products);
}
