package com.kcjcustomerbe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;


public class CartProductDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private UUID id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("cart")
   private CartDto cartDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("product")
   private ProductDto productDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private int quantity;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime cratedAt;

   public CartProductDto() {
   }

   public UUID getId() {
      return id;
   }

   public void setId(UUID id) {
      this.id = id;
   }

   public CartDto getCartDto() {
      return cartDto;
   }

   public void setCartDto(CartDto cartDto) {
      this.cartDto = cartDto;
   }

   public ProductDto getProductDto() {
      return productDto;
   }

   public void setProductDto(ProductDto productDto) {
      this.productDto = productDto;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public LocalDateTime getCratedAt() {
      return cratedAt;
   }

   public void setCratedAt(LocalDateTime cratedAt) {
      this.cratedAt = cratedAt;
   }

   // Builder class
   public static class Builder {

      private CartProductDto cartProductDto = new CartProductDto();

      public Builder id(UUID id) {
         cartProductDto.id = id;
         return this;
      }

      public Builder cartDto(CartDto cartDto) {
         cartProductDto.cartDto = cartDto;
         return this;
      }

      public Builder productDto(ProductDto productDto) {
         cartProductDto.productDto = productDto;
         return this;
      }

      public Builder quantity(int quantity) {
         cartProductDto.quantity = quantity;
         return this;
      }

      public Builder cratedAt(LocalDateTime cratedAt) {
         cartProductDto.cratedAt = cratedAt;
         return this;
      }

      public CartProductDto build() {
         return cartProductDto;
      }
   }

   public static Builder builder() {
      return new Builder();
   }
}
