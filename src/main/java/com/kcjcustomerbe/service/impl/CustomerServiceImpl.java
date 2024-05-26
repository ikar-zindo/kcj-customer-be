package com.kcjcustomerbe.service.impl;

import com.kcjcustomerbe.dto.customer.*;
import com.kcjcustomerbe.entity.Customer;
import com.kcjcustomerbe.entity.enums.Role;
import com.kcjcustomerbe.exception.ErrorMessage;
import com.kcjcustomerbe.exception.list.CustomerIdNotFound;
import com.kcjcustomerbe.exception.list.CustomerIsExistException;
import com.kcjcustomerbe.exception.list.CustomerNotFoundException;
import com.kcjcustomerbe.exception.list.IdNullException;
import com.kcjcustomerbe.mapper.CustomerMapper;
import com.kcjcustomerbe.repo.CustomerRepository;
import com.kcjcustomerbe.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

   private final CustomerRepository customerRepository;

   private final CustomerMapper customerMapper;

   private final PasswordEncoder encoder;

   @Override
   @Transactional
   public CustomerResponseDto registrationCustomer(CustomerCreateDto customerCreateDto) throws CustomerNotFoundException {
      Optional<Customer> optionalCustomerByUsername = customerRepository.findByUsername(customerCreateDto.getUsername());
      Optional<Customer> optionalCustomerByEmail = customerRepository.findByEmail(customerCreateDto.getEmail());

      if (optionalCustomerByUsername.isPresent()) {
         throw new CustomerIsExistException(ErrorMessage.USERNAME_ALREADY_EXISTS);
      }

      if (optionalCustomerByEmail.isPresent()) {
         throw new CustomerIsExistException(ErrorMessage.EMAIL_ALREADY_EXISTS);
      }

      Customer customer = customerMapper.mapCustomerFromCustomerCreateDto(customerCreateDto);
      customer.setPassword(encoder.encode(customerCreateDto.getPassword()));
      customer.setRole(Role.ROLE_CUSTOMER);
      Customer afterCreate = customerRepository.save(customer);

      return customerMapper.mapToCustomerAfterCreateDto(afterCreate);
   }

   @Override
   @Transactional
   public CustomerDto getCustomerById(UUID customerId) throws IdNullException {
      Optional<Customer> customerOptional = customerRepository.findById(customerId);

      if (customerOptional.isPresent()) {
         Customer expextedCustomer = customerOptional.get();
         if (!expextedCustomer.getIsBlocked()) {
            return customerMapper.mapToCustomerDto(customerOptional.get());

         } else {
            throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_ID_NOT_FOUND + customerId);
         }
      } else {
         throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_ID_NOT_FOUND + customerId);
      }
   }

   @Override
   @Transactional
   public CustomerResponseDto updateCustomerInfo(UUID customerId, CustomerUpdateDto customerUpdateDto) {
      if (customerId == null) {
         throw new IllegalArgumentException(ErrorMessage.INVALID_CUSTOMER_ID);
      }

      if (customerUpdateDto == null) {
         throw new IllegalArgumentException(ErrorMessage.INVALID_CUSTOMER_UPDATE_DTO);
      }

      Customer existingCustomer = customerRepository.findById(customerId)
            .orElseThrow(() -> new CustomerIdNotFound(customerId));

      Optional<Customer> optionalCustomerByUsername = customerRepository.findByUsername(customerUpdateDto.getUsername());
      if (optionalCustomerByUsername.isPresent() && !optionalCustomerByUsername.get().getId().equals(customerId)) {
         throw new CustomerIsExistException(ErrorMessage.USERNAME_ALREADY_EXISTS);
      }

      Optional<Customer> optionalCustomerByEmail = customerRepository.findByEmail(customerUpdateDto.getUsername());
      if (optionalCustomerByEmail.isPresent() && !optionalCustomerByEmail.get().getId().equals(customerId)) {
         throw new CustomerIsExistException(ErrorMessage.EMAIL_ALREADY_EXISTS);
      }

      customerMapper.mapCustomerFromCustomerUpdateDto(customerUpdateDto, existingCustomer);

      if (customerUpdateDto.getPassword() != null && !customerUpdateDto.getPassword().isEmpty()) {
         existingCustomer.setPassword(encoder.encode(customerUpdateDto.getPassword()));
      }

      Customer afterUpdate = customerRepository.save(existingCustomer);

      return customerMapper.mapToCustomerAfterUpdateDto(afterUpdate);
   }

   @Override
   @Transactional
   public CustomerResponseDto blockCustomerById(UUID customerId) throws IdNullException {
      if (customerId == null) {
         throw new IdNullException(ErrorMessage.INVALID_CUSTOMER_ID);
      }

      Customer existingCustomer = customerRepository.findById(customerId)
            .orElseThrow(() -> new CustomerIdNotFound(customerId));

      existingCustomer.setIsBlocked(true);
      existingCustomer.setUpdatedAt(LocalDateTime.now());

      return customerMapper.mapToCustomerAfterBlockedDto(customerRepository.save(existingCustomer));
   }
}
