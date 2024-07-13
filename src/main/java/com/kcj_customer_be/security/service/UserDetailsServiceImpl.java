package com.kcj_customer_be.security.service;

import com.kcj_customer_be.entity.Customer;
import com.kcj_customer_be.exception.ErrorMessage;
import com.kcj_customer_be.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

   private final CustomerRepository customerRepository;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<Customer> customerOptional = customerRepository.findByEmail(username);

      if (customerOptional.isEmpty()) {
         throw new UsernameNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND_WITH_EMAIL + username);
      }

      Customer customer = customerOptional.get();
      return withUsername(customer.getEmail())
            .username(customer.getEmail())
            .password(customer.getPassword())
            .authorities(AuthorityUtils.createAuthorityList(customer.getRole().toString()))
            .build();
   }
}
