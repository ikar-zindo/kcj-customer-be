package com.kcjcustomerbe.mapper;

import com.kcjcustomerbe.dto.RestaurantDto;
import com.kcjcustomerbe.entity.Restaurant;
import org.mapstruct.*;

import java.util.List;

/**
 * This mapper helps in the conversion between `Restaurant` entity and `RestaurantDto`
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
      unmappedTargetPolicy = ReportingPolicy.IGNORE,
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
      uses = ReviewMapper.class)
public interface RestaurantMapper {

   @Mappings({
         @Mapping(target = "id", source = "id"),
         @Mapping(target = "name", source = "name"),
         @Mapping(target = "address", source = "address"),
         @Mapping(target = "phoneNumber", source = "phoneNumber"),
         @Mapping(target = "openingHours", source = "openingHours"),
         @Mapping(target = "cuisineType", source = "cuisineType"),
         @Mapping(target = "description", source = "description"),
         @Mapping(target = "socialMediaLinks", source = "socialMediaLinks"),
//         @Mapping(target = "IsOpen", source = "IsOpen"),
         @Mapping(target = "productsDto", source = "products"),
         @Mapping(target = "reviewsDto", source = "reviews"),
   })
   RestaurantDto mapRestaurantToRestaurantDto(Restaurant entity);

   List<RestaurantDto> mapRestaurantsToRestaurantsDto(List<Restaurant> entities);
}