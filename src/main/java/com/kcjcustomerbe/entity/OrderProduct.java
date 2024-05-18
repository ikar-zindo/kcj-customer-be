package com.kcjcustomerbe.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_product")
public class OrderProduct {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "order_product_id")
   private Long id;

   @Column(name = "quantity")
   private int quantity;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "order_id")
   private Order order;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "product_id")
   private Product product;

   public OrderProduct() {
   }

   // Getters & Setters
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public Order getOrder() {
      return order;
   }

   public void setOrder(Order order) {
      this.order = order;
   }

   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   // Builder class
   public static class Builder {

      private OrderProduct orderProduct = new OrderProduct();

      public Builder id(Long id) {
         orderProduct.id = id;
         return this;
      }

      public Builder quantity(int quantity) {
         orderProduct.quantity = quantity;
         return this;
      }

      public Builder order(Order order) {
         orderProduct.order = order;
         return this;
      }

      public Builder product(Product product) {
         orderProduct.product = product;
         return this;
      }

      public OrderProduct build() {
         return orderProduct;
      }
   }

   public static Builder builder() {
      return new Builder();
   }

   // ToString
   @Override
   public String toString() {
      return "OrderProduct{" +
              "id=" + id +
              ", quantity=" + quantity +
              ", order=" + order +
              ", product=" + product +
              '}';
   }
}
