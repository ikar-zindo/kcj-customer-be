package com.kcj_customer_be.security.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "deactivated_tokens")
public class DeactivatedToken {

   @Id
   @Column(name = "token_id")
   private UUID id;

   @Column(name = "keep_until")
   private Timestamp keepUntil;
}
