package com.kcurryjibcustomer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


public class ProductDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Length(max = 25, message = "{validation.length.max.25}")
   private String name;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Length(max = 1000, message = "{validation.length.max.1000}")
   private String description;

   @NotNull(message = "{validation.value.null}")
   @DecimalMin(value = "0.01", message = "{validation.product.price}")
   @DecimalMax(value = "10000", message = "{validation.product.price.value}")
   private BigDecimal price;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private String imageUrl;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime createdAt;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private boolean isAvailable;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("restaurant")
   @NotNull(message = "{validation.value.null}")
   private RestaurantDto restaurantDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("cartProducts")
   private List<CartProductDto> cartProductsDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("orderProducts")
   private List<OrderProductDto> orderProductsDto;

   public ProductDto() {
   }

   // Getters & Setters
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

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public BigDecimal getPrice() {
      return price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public String getImageUrl() {
      return imageUrl;
   }

   public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }

   public boolean isAvailable() {
      return isAvailable;
   }

   public void setAvailable(boolean available) {
      isAvailable = available;
   }

   public RestaurantDto getRestaurantDto() {
      return restaurantDto;
   }

   public void setRestaurantDto(RestaurantDto restaurantDto) {
      this.restaurantDto = restaurantDto;
   }

   public List<CartProductDto> getCartProductsDto() {
      return cartProductsDto;
   }

   public void setCartProductsDto(List<CartProductDto> cartProductsDto) {
      this.cartProductsDto = cartProductsDto;
   }

   public List<OrderProductDto> getOrderProductsDto() {
      return orderProductsDto;
   }

   public void setOrderProductsDto(List<OrderProductDto> orderProductsDto) {
      this.orderProductsDto = orderProductsDto;
   }

   // Builder class
   public static class Builder {
      private ProductDto productDto = new ProductDto();

      public Builder id(Long id) {
         productDto.id = id;
         return this;
      }

      public Builder name(String name) {
         productDto.name = name;
         return this;
      }

      public Builder description(String description) {
         productDto.description = description;
         return this;
      }

      public Builder price(BigDecimal price) {
         productDto.price = price;
         return this;
      }

      public Builder imageUrl(String imageUrl) {
         productDto.imageUrl = imageUrl;
         return this;
      }

      public Builder createdAt(LocalDateTime createdAt) {
         productDto.createdAt = createdAt;
         return this;
      }

      public Builder isAvailable(Boolean isAvailable) {
         productDto.isAvailable = isAvailable;
         return this;
      }

      public Builder restaurantDto(RestaurantDto restaurantDto) {
         productDto.restaurantDto = restaurantDto;
         return this;
      }

      public ProductDto build() {
         return productDto;
      }
   }

   public static Builder builder() {
      return new Builder();
   }

   // Equals
   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ProductDto that = (ProductDto) o;
      return isAvailable == that.isAvailable && Objects.equals(id, that.id) &&
              Objects.equals(name, that.name) && Objects.equals(description, that.description) &&
              Objects.equals(price, that.price) && Objects.equals(imageUrl, that.imageUrl) &&
              Objects.equals(createdAt, that.createdAt) && Objects.equals(restaurantDto, that.restaurantDto) &&
              Objects.equals(cartProductsDto, that.cartProductsDto) &&
              Objects.equals(orderProductsDto, that.orderProductsDto);
   }

   // HashCode
   @Override
   public int hashCode() {
      return Objects.hash(id, name, description, price,
              imageUrl, createdAt, isAvailable, restaurantDto,
              cartProductsDto, orderProductsDto);
   }


   // ToString
   @Override
   public String toString() {
      return "ProductDto{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", description='" + description + '\'' +
              ", price=" + price +
              ", imageUrl='" + imageUrl + '\'' +
              ", createdAt=" + createdAt +
              ", isAvailable=" + isAvailable +
              ", restaurantDto=" + restaurantDto +
              ", cartProductsDto=" + cartProductsDto +
              ", orderProductsDto=" + orderProductsDto +
              '}';
   }
}
