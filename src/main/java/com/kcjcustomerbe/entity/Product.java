package com.kcjcustomerbe.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "product_id")
   private Long id;

   @Column(name = "name")
   private String name;

   @Column(name = "description")
   private String description;

   @Column(name = "price", precision = 8, scale = 2)
   private BigDecimal price;

   @Column(name = "image_url")
   private String imageUrl;

   @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime createdAt;

   @Column(name = "is_available")
   private Boolean isAvailable;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "restaurant_id")
   private Restaurant restaurant;

   @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
   private List<CartProduct> cartProducts;

   @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
   private List<OrderProduct> orderProducts;

   public Product() {
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

   public Boolean getAvailable() {
      return isAvailable;
   }

   public void setAvailable(Boolean available) {
      isAvailable = available;
   }

   public Restaurant getRestaurant() {
      return restaurant;
   }

   public void setRestaurant(Restaurant restaurant) {
      this.restaurant = restaurant;
   }

   public List<CartProduct> getCartProducts() {
      return cartProducts;
   }

   public void setCartProducts(List<CartProduct> cartProducts) {
      this.cartProducts = cartProducts;
   }

   public List<OrderProduct> getOrderProducts() {
      return orderProducts;
   }

   public void setOrderProducts(List<OrderProduct> orderProducts) {
      this.orderProducts = orderProducts;
   }

   // Builder class
   public static class Builder {
      private Product product = new Product();

      public Builder id(Long id) {
         product.id = id;
         return this;
      }

      public Builder name(String name) {
         product.name = name;
         return this;
      }

      public Builder description(String description) {
         product.description = description;
         return this;
      }

      public Builder price(BigDecimal price) {
         product.price = price;
         return this;
      }

      public Builder imageUrl(String imageUrl) {
         product.imageUrl = imageUrl;
         return this;
      }

      public Builder createdAt(LocalDateTime createdAt) {
         product.createdAt = createdAt;
         return this;
      }

      public Builder isAvailable(Boolean isAvailable) {
         product.isAvailable = isAvailable;
         return this;
      }

      public Builder restaurant(Restaurant restaurant) {
         product.restaurant = restaurant;
         return this;
      }

      public Product build() {
         return product;
      }
   }

   public static Builder builder() {
      return new Builder();
   }
}

