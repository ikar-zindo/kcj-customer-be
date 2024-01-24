package com.kcurryjibcustomer.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "restaurant")
public class Restaurant {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "restaurant_id")
   private Long id;

   @Column(name = "name")
   private String name;

   @Column(name = "address")
   private String address;

   @Column(name = "phone_number")
   private String phoneNumber;

   @Column(name = "opening_hours")
   private String openingHours;

   @Column(name = "cuisine_type")
   private String cuisineType;

   @Column(name = "description")
   private String description;

   @Column(name = "social_media_links")
   private String socialMediaLinks;

   @Column(name = "is_open")
   private Boolean isOpen;

   @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
   private List<Product> products;

   @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
   private List<Review> reviews;

   @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
   private List<Order> orders;

   @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
   private List<Employee> employees;

   public Restaurant() {
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

   public Boolean getOpen() {
      return isOpen;
   }

   public void setOpen(Boolean open) {
      isOpen = open;
   }

   public List<Product> getProducts() {
      return products;
   }

   public void setProducts(List<Product> products) {
      this.products = products;
   }

   public List<Review> getReviews() {
      return reviews;
   }

   public void setReviews(List<Review> reviews) {
      this.reviews = reviews;
   }

   public List<Order> getOrders() {
      return orders;
   }

   public void setOrders(List<Order> orders) {
      this.orders = orders;
   }

   public List<Employee> getEmployees() {
      return employees;
   }

   public void setEmployees(List<Employee> employees) {
      this.employees = employees;
   }

   // Builder class
   public static class Builder {
      private Restaurant restaurant = new Restaurant();

      public Builder id(Long id) {
         restaurant.id = id;
         return this;
      }
      public Builder name(String name) {
         restaurant.name = name;
         return this;
      }
      public Builder address(String address) {
         restaurant.address = address;
         return this;
      }
      public Builder phoneNumber(String phoneNumber) {
         restaurant.phoneNumber = phoneNumber;
         return this;
      }
      public Builder openingHours(String openingHours) {
         restaurant.openingHours = openingHours;
         return this;
      }
      public Builder cuisineType(String cuisineType) {
         restaurant.cuisineType = cuisineType;
         return this;
      }
      public Builder description(String description) {
         restaurant.description = description;
         return this;
      }
      public Builder socialMediaLinks(String socialMediaLinks) {
         restaurant.socialMediaLinks = socialMediaLinks;
         return this;
      }
      public Builder isOpen(Boolean isOpen) {
         restaurant.isOpen = isOpen;
         return this;
      }

      public Restaurant build() {
         return restaurant;
      }
   }

   public static Builder builder() {
      return new Builder();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Restaurant that = (Restaurant) o;
      return Objects.equals(id, that.id) && Objects.equals(name, that.name) &&
              Objects.equals(address, that.address) && Objects.equals(phoneNumber, that.phoneNumber) &&
              Objects.equals(openingHours, that.openingHours) && Objects.equals(cuisineType, that.cuisineType) &&
              Objects.equals(description, that.description) &&
              Objects.equals(socialMediaLinks, that.socialMediaLinks) && Objects.equals(isOpen, that.isOpen) &&
              Objects.equals(products, that.products) && Objects.equals(reviews, that.reviews) &&
              Objects.equals(orders, that.orders) && Objects.equals(employees, that.employees);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, name, address, phoneNumber, openingHours, cuisineType,
              description, socialMediaLinks, isOpen, products, reviews, orders, employees);
   }

   @Override
   public String toString() {
      return "Restaurant{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", address='" + address + '\'' +
              ", phoneNumber='" + phoneNumber + '\'' +
              ", openingHours='" + openingHours + '\'' +
              ", cuisineType='" + cuisineType + '\'' +
              ", description='" + description + '\'' +
              ", socialMediaLinks='" + socialMediaLinks + '\'' +
              ", isOpen=" + isOpen +
              ", products=" + products +
              ", reviews=" + reviews +
              ", orders=" + orders +
              ", employees=" + employees +
              '}';
   }
}
