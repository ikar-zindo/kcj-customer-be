package com.kcjcustomerbe.repo;

import com.kcjcustomerbe.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;


public interface CustomerRepository extends JpaRepository<Customer, UUID> {

   UserDetails findByUsername(String username);

   Customer findByEmail(String email);

   Optional<Customer> findCustomerByCartId(UUID cartId);
}
