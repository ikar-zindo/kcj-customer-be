package com.kcurryjibcustomer.repo;

import com.kcurryjibcustomer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

   UserDetails findByUsername(String username);

   Optional<Customer> findCustomerByCartId(Long cartId);
}
