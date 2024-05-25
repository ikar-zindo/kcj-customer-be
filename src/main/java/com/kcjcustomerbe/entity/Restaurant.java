package com.kcjcustomerbe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "restaurants")
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

   @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private List<Product> products;

   @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private List<Review> reviews;

   @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private List<Order> orders;
}
