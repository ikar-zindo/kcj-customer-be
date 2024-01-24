package com.kcurryjibcustomer.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "review_id")
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "restaurant_id")
   private Restaurant restaurant;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "customer_id")
   private Customer customer;

   @Column(name = "rating", precision = 5, scale = 2)
   private BigDecimal rating;

   @Column(name = "comment")
   private String comment;

   @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime createdAt;

   public Review() {
   }

   // Getters & Setters
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Restaurant getRestaurant() {
      return restaurant;
   }

   public void setRestaurant(Restaurant restaurant) {
      this.restaurant = restaurant;
   }

   public Customer getCustomer() {
      return customer;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
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

   // Builder class
   public static class Builder {
      private Review review = new Review();

      public Builder id(Long id) {
         review.id = id;
         return this;
      }
      public Builder restaurant(Restaurant restaurant) {
         review.restaurant = restaurant;
         return this;
      }
      public Builder customer(Customer customer) {
         review.customer = customer;
         return this;
      }
      public Builder rating(BigDecimal rating) {
         review.rating = rating;
         return this;
      }
      public Builder comment(String comment) {
         review.comment = comment;
         return this;
      }
      public Builder createdAt(LocalDateTime createdAt) {
         review.createdAt = createdAt;
         return this;
      }

      public Review build() {
         return review;
      }
   }

   public static Builder builder() {
      return new Builder();
   }
}
