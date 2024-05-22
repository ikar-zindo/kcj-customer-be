package com.kcjcustomerbe.mapper;

import com.kcjcustomerbe.dto.RestaurantDto;
import com.kcjcustomerbe.entity.Restaurant;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
      unmappedTargetPolicy = ReportingPolicy.IGNORE,
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
      uses = ProductMapper.class)
public interface RestaurantMapper {

   RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

   @Mappings({
         @Mapping(target = "id", source = "entity.id"),
         @Mapping(target = "name", source = "entity.name"),
         @Mapping(target = "address", source = "entity.address"),
         @Mapping(target = "phoneNumber", source = "entity.phoneNumber"),
         @Mapping(target = "openingHours", source = "entity.openingHours"),
         @Mapping(target = "cuisineType", source = "entity.cuisineType"),
         @Mapping(target = "description", source = "entity.description"),
         @Mapping(target = "socialMediaLinks", source = "entity.socialMediaLinks"),
//         @Mapping(target = "IsOpen", source = "entity.IsOpen"),
         @Mapping(target = "productsDto", source = "entity.products"),
         @Mapping(target = "reviewsDto", source = "entity.reviews"),
   })
   RestaurantDto restaurantToRestaurantDto(Restaurant entity);

   List<RestaurantDto> restaurantsToRestaurantsDto(List<Restaurant> entities);

   @Mappings({
         @Mapping(target = "id", source = "dto.id"),
         @Mapping(target = "name", source = "dto.name"),
         @Mapping(target = "address", source = "dto.address"),
         @Mapping(target = "phoneNumber", source = "dto.phoneNumber"),
         @Mapping(target = "openingHours", source = "dto.openingHours"),
         @Mapping(target = "cuisineType", source = "dto.cuisineType"),
         @Mapping(target = "description", source = "dto.description"),
         @Mapping(target = "socialMediaLinks", source = "dto.socialMediaLinks"),
//         @Mapping(target = "IsOpen", source = "dto.IsOpen"),
         @Mapping(target = "products", source = "dto.productsDto"),
         @Mapping(target = "reviews", source = "dto.reviewsDto"),
   })
   Restaurant restaurantDtoToRestaurant(RestaurantDto dto);

   List<Restaurant> restaurantsDtoToRestaurants(List<RestaurantDto> dtos);
}
