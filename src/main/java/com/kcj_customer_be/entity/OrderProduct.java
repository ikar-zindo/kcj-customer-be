package com.kcj_customer_be.entity;

import com.kcj_customer_be.util.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_products")
public class OrderProduct {

   @Id
   @GeneratedValue(generator = "UUID")
   @GenericGenerator(name = "UUID", type = UuidTimeSequenceGenerator.class)
   @Column(name = "order_product_id")
   private UUID id;

   @Column(name = "quantity")
   private int quantity;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "order_id")
   private Order order;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "product_id")
   private Product product;
}
