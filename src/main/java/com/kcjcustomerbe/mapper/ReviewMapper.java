package com.kcjcustomerbe.mapper;

import com.kcjcustomerbe.dto.ReviewDto;
import com.kcjcustomerbe.dto.customer.CustomerDto;
import com.kcjcustomerbe.entity.Customer;
import com.kcjcustomerbe.entity.Review;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * This mapper assists in converting between `Review` entity and its respective DTO objects.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
      unmappedTargetPolicy = ReportingPolicy.IGNORE,
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReviewMapper {

   ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

   @Mappings({
         @Mapping(target = "id", source = "id"),
         @Mapping(target = "restaurantDto", ignore = true),
         @Mapping(target = "customerDto", source = "customer"),
         @Mapping(target = "rating", source = "rating"),
         @Mapping(target = "comment", source = "comment"),
         @Mapping(target = "createdAt", source = "createdAt")
   })
   ReviewDto mapReviewToReviewDto(Review entity);

   @Mappings({
         @Mapping(target = "id", source = "id"),
         @Mapping(target = "firstName", source = "firstName"),
         @Mapping(target = "lastName", source = "lastName"),
         @Mapping(target = "email", ignore = true),
         @Mapping(target = "username", ignore = true),
         @Mapping(target = "phoneNumber", ignore = true),
         @Mapping(target = "address", ignore = true),
         @Mapping(target = "postalCode", ignore = true),
         @Mapping(target = "createdAt", ignore = true),
         @Mapping(target = "updatedAt", ignore = true),
         @Mapping(target = "role", ignore = true),
         @Mapping(target = "isBlocked", ignore = true),
         @Mapping(target = "ordersDto", ignore = true),
         @Mapping(target = "cartDto", ignore = true),
         @Mapping(target = "reviewsDto", ignore = true)
   })
   CustomerDto mapToCustomerDto(Customer entity);

   List<ReviewDto> mapReviewsToReviewsDto(List<Review> reviews);

   @Mappings({
         @Mapping(target = "customer.id", source = "customerDto.id"),
         @Mapping(target = "restaurant.id", source = "restaurantDto.id"),
         @Mapping(target = "rating", source = "rating"),
         @Mapping(target = "comment", source = "comment"),
         @Mapping(target = "createdAt", source = "createdAt")
   })
   Review mapRreviewDtoToReview(ReviewDto dto);
}
