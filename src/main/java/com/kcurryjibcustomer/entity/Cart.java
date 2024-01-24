package com.kcurryjibcustomer.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "cart_id")
   private Long id;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
   private Customer customer;

   @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
   private List<CartProduct> cartProducts;

   public Cart() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Customer getCustomer() {
      return customer;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   public List<CartProduct> getCartProducts() {
      return cartProducts;
   }

   public void setCartProducts(List<CartProduct> cartProducts) {
      this.cartProducts = cartProducts;
   }

   // Builder class
   public static class Builder {

      private Cart cart = new Cart();

      public Builder id(Long id) {
         cart.id = id;
         return this;
      }

      public Builder customer(Customer customer) {
         cart.customer = customer;
         return this;
      }

      public Cart build() {
         return cart;
      }
   }

   public static Builder builder() {
      return new Builder();
   }
}
