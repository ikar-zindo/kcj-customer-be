package com.kcjcustomerbe.entity;

import com.kcjcustomerbe.generatorUuid.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "carts")
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
}
