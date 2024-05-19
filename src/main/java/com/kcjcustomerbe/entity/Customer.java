package com.kcjcustomerbe.entity;

import com.kcjcustomerbe.entity.enums.Role;
import com.kcjcustomerbe.generatorUuid.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customer")
public class Customer implements UserDetails {

   @Id
   @GeneratedValue(generator = "UUID")
   @GenericGenerator(name = "UUID", type = UuidTimeSequenceGenerator.class)
   @Column(name = "customer_id")
   private UUID id;

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
      return email; // authorization by email
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
}
