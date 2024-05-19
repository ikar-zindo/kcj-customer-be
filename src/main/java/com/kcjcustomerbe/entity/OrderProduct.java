package com.kcjcustomerbe.entity;

import com.kcjcustomerbe.generatorUuid.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_product")
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
