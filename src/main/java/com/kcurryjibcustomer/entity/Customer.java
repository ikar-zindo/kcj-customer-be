package com.kcurryjibcustomer.entity;

import com.kcurryjibcustomer.entity.enums.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "customer_id")
   private Long id;

   @Column(name = "first_name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "email")
   private String email;

   @Column(name = "username")
   private String username;

   @Column(name = "password")
   private String password;

   @Column(name = "phone_number")
   private String phoneNumber;

   @Column(name = "address")
   private String address;

   @Column(name = "postal_code")
   private String postalCode;

   @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime createdAt;

   @Enumerated(EnumType.STRING)
   @Column(name = "role")
   private Role role;

   @Column(name = "is_blocked")
   private Boolean isBlocked;

   @OneToOne(mappedBy = "customer")
   private Cart cart;

   @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
   private List<Order> orders;

   @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
   private List<Review> reviews;

   public Customer() {
   }

   // Getters & Setters
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getPostalCode() {
      return postalCode;
   }

   public void setPostalCode(String postalCode) {
      this.postalCode = postalCode;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }

   public Role getRole() {
      return role;
   }

   public void setRole(Role role) {
      this.role = role;
   }

   public Boolean getBlocked() {
      return isBlocked;
   }

   public void setBlocked(Boolean blocked) {
      isBlocked = blocked;
   }

   public Cart getCart() {
      return cart;
   }

   public void setCart(Cart cart) {
      this.cart = cart;
   }

   public List<Order> getOrders() {
      return orders;
   }

   public void setOrders(List<Order> orders) {
      this.orders = orders;
   }

   public List<Review> getReviews() {
      return reviews;
   }

   public void setReviews(List<Review> reviews) {
      this.reviews = reviews;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return AuthorityUtils.createAuthorityList(String.valueOf(this.role));
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return username;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return !isBlocked;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   // Builder class
   public static class Builder {

      private Customer customer = new Customer();

      public Builder id(Long id) {
         customer.id = id;
         return this;
      }

      public Builder firstName(String firstName) {
         customer.firstName = firstName;
         return this;
      }

      public Builder lastName(String lastName) {
         customer.lastName = lastName;
         return this;
      }

      public Builder email(String email) {
         customer.username = email;
         return this;
      }

      public Builder password(String password) {
         customer.password = password;
         return this;
      }

      public Builder phoneNumber(String phoneNumber) {
         customer.phoneNumber = phoneNumber;
         return this;
      }

      public Builder address(String address) {
         customer.address = address;
         return this;
      }

      public Builder postalCode(String postalCode) {
         customer.postalCode = postalCode;
         return this;
      }

      public Builder createdAt(LocalDateTime createdAt) {
         customer.createdAt = createdAt;
         return this;
      }

      public Builder isBlocked(Boolean isBlocked) {
         customer.isBlocked = isBlocked;
         return this;
      }

      public Builder role(Role role) {
         customer.role = role;
         return this;
      }

      public Builder username(String username) {
         customer.username = username;
         return this;
      }

      public Customer build() {
         return customer;
      }
   }

   public static Builder builder() {
      return new Builder();
   }
}
