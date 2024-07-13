package com.kcj_customer_be.repo;

import com.kcj_customer_be.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface CustomerRepository extends JpaRepository<Customer, UUID> {

   Optional<Customer> findByEmail(String email);

   Optional<Customer> findCustomerByCartId(UUID cartId);
}
