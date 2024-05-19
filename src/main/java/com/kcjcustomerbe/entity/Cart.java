package com.kcjcustomerbe.entity;

import com.kcjcustomerbe.generatorUuid.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cart")
public class Cart {

   @Id
   @GeneratedValue(generator = "UUID")
   @GenericGenerator(name = "UUID", type = UuidTimeSequenceGenerator.class)
   @Column(name = "cart_id")
   private UUID id;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
   private Customer customer;

   @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
   private List<CartProduct> cartProducts;

   public Cart() {
   }

   public UUID getId() {
      return id;
   }

   public void setId(UUID id) {
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

      public Builder id(UUID id) {
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
