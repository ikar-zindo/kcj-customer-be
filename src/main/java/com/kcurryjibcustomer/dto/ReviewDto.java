package com.kcurryjibcustomer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class ReviewDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("restaurant")
   private RestaurantDto restaurantDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("customer")
   private CustomerDto customerDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotNull(message = "{validation.value.null}")
   @DecimalMin(value = "1", message = "{validation.review.rating.min}")
   @DecimalMax(value = "5", message = "{validation.review.rating.max}")
   private BigDecimal rating;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   private String comment;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime createdAt;

   public ReviewDto() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public RestaurantDto getRestaurantDto() {
      return restaurantDto;
   }

   public void setRestaurantDto(RestaurantDto restaurantDto) {
      this.restaurantDto = restaurantDto;
   }

   public CustomerDto getCustomerDto() {
      return customerDto;
   }

   public void setCustomerDto(CustomerDto customerDto) {
      this.customerDto = customerDto;
   }

   public BigDecimal getRating() {
      return rating;
   }

   public void setRating(BigDecimal rating) {
      this.rating = rating;
   }

   public String getComment() {
      return comment;
   }

   public void setComment(String comment) {
      this.comment = comment;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }

   // ToString
   @Override
   public String toString() {
      return "ReviewDto{" +
              "id=" + id +
              ", restaurantDto=" + restaurantDto +
              ", customerDto=" + customerDto +
              ", rating=" + rating +
              ", comment='" + comment + '\'' +
              ", createdAt=" + createdAt +
              '}';
   }

   // Builder class
   public static class Builder {
      private ReviewDto reviewDto = new ReviewDto();

      public Builder id(Long id) {
         reviewDto.id = id;
         return this;
      }
      public Builder restaurantDto(RestaurantDto restaurantDto) {
         reviewDto.restaurantDto = restaurantDto;
         return this;
      }
      public Builder customerDto(CustomerDto customerDto) {
         reviewDto.customerDto = customerDto;
         return this;
      }
      public Builder rating(BigDecimal rating) {
         reviewDto.rating = rating;
         return this;
      }
      public Builder comment(String comment) {
         reviewDto.comment = comment;
         return this;
      }
      public Builder createdAt(LocalDateTime createdAt) {
         reviewDto.createdAt = createdAt;
         return this;
      }

      public ReviewDto build() {
         return reviewDto;
      }
   }

   public static Builder builder() {
      return new Builder();
   }
}
