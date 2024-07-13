package com.kcj_customer_be.entity;

import com.kcj_customer_be.util.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cart_products")
public class CartProduct {

   @Id
   @GeneratedValue(generator = "UUID")
   @GenericGenerator(name = "UUID", type = UuidTimeSequenceGenerator.class)
   @Column(name = "cart_product_id")
   private UUID id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "cart_id")
   @NotNull(message = "{validation.cart.cart.null}")
   private Cart cart;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "product_id")
   @NotNull(message = "{validation.cart.product.null}")
   private Product product;

   @Column(name = "quantity")
   @NotNull(message = "{validation.cart.quantity.null}")
   private int quantity;

   @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private LocalDateTime createdAt;
}
