package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Objects;


public class RestaurantDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotBlank(message = "{validation.length.empty}")
   @Length(max = 30, message = "{validation.length.max.30}")
   private String name;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotBlank(message = "{validation.length.empty}")
   @Length(max = 30, message = "{validation.length.max.30}")
   private String address;

   @JsonInclude(JsonInclude.Include.NON_NULL)
//   @Pattern(regexp = "\\\\+49\\\\d{10}", message = "{validation.restaurant.phoneNumber.length}")
   @Length(max = 20, message = "{validation.length.max.20}")
   private String phoneNumber;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 30, message = "{validation.length.max.30}")
   private String openingHours;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 30, message = "{validation.length.max.30}")
   private String cuisineType;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 1000, message = "{validation.length.max.1000}")
   private String description;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @Length(max = 200, message = "{validation.length.max.200}")
   private String socialMediaLinks;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private boolean isOpen;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("products")
   private List<ProductDto> productsDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("reviews")
   private List<ReviewDto> reviewsDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("orders")
   private List<OrderDto> ordersDto;

   public RestaurantDto() {
   }

   public RestaurantDto(Long id) {
      this.id = id;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getOpeningHours() {
      return openingHours;
   }

   public void setOpeningHours(String openingHours) {
      this.openingHours = openingHours;
   }

   public String getCuisineType() {
      return cuisineType;
   }

   public void setCuisineType(String cuisineType) {
      this.cuisineType = cuisineType;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getSocialMediaLinks() {
      return socialMediaLinks;
   }

   public void setSocialMediaLinks(String socialMediaLinks) {
      this.socialMediaLinks = socialMediaLinks;
   }

   public boolean isOpen() {
      return isOpen;
   }

   public void setOpen(boolean open) {
      isOpen = open;
   }

   public List<ProductDto> getProductsDto() {
      return productsDto;
   }

   public void setProductsDto(List<ProductDto> productsDto) {
      this.productsDto = productsDto;
   }

   public List<ReviewDto> getReviewsDto() {
      return reviewsDto;
   }

   public void setReviewsDto(List<ReviewDto> reviewsDto) {
      this.reviewsDto = reviewsDto;
   }

   public List<OrderDto> getOrdersDto() {
      return ordersDto;
   }

   public void setOrdersDto(List<OrderDto> ordersDto) {
      this.ordersDto = ordersDto;
   }

   // Builder
   public static class Builder {
      private RestaurantDto restaurantDto = new RestaurantDto();

      public Builder id(Long id) {
         restaurantDto.id = id;
         return this;
      }

      public Builder name(String name) {
         restaurantDto.name = name;
         return this;
      }

      public Builder address(String address) {
         restaurantDto.address = address;
         return this;
      }

      public Builder phoneNumber(String phoneNumber) {
         restaurantDto.phoneNumber = phoneNumber;
         return this;
      }

      public Builder openingHours(String openingHours) {
         restaurantDto.openingHours = openingHours;
         return this;
      }

      public Builder cuisineType(String cuisineType) {
         restaurantDto.cuisineType = cuisineType;
         return this;
      }

      public Builder description(String description) {
         restaurantDto.description = description;
         return this;
      }

      public Builder socialMediaLinks(String socialMediaLinks) {
         restaurantDto.socialMediaLinks = socialMediaLinks;
         return this;
      }

      public Builder isOpen(Boolean isOpen) {
         restaurantDto.isOpen = isOpen;
         return this;
      }

      public RestaurantDto build() {
         return restaurantDto;
      }
   }

   public static Builder builder() {
      return new Builder();
   }

   // toString

   // Equals & HashCode
   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      RestaurantDto that = (RestaurantDto) o;
      return isOpen == that.isOpen && Objects.equals(id, that.id) && Objects.equals(name, that.name) &&
              Objects.equals(address, that.address) && Objects.equals(phoneNumber, that.phoneNumber) &&
              Objects.equals(openingHours, that.openingHours) && Objects.equals(cuisineType, that.cuisineType) &&
              Objects.equals(description, that.description) &&
              Objects.equals(socialMediaLinks, that.socialMediaLinks) && Objects.equals(productsDto, that.productsDto) &&
              Objects.equals(reviewsDto, that.reviewsDto) && Objects.equals(ordersDto, that.ordersDto);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, name, address, phoneNumber, openingHours, cuisineType, description,
              socialMediaLinks, isOpen, productsDto, reviewsDto, ordersDto);
   }
}
