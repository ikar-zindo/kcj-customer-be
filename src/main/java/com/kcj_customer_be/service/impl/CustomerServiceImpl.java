package com.kcj_customer_be.service.impl;

import com.kcj_customer_be.dto.customer.CustomerCreateDto;
import com.kcj_customer_be.dto.customer.CustomerDto;
import com.kcj_customer_be.dto.customer.CustomerResponseDto;
import com.kcj_customer_be.dto.customer.CustomerUpdateDto;
import com.kcj_customer_be.entity.Cart;
import com.kcj_customer_be.entity.Customer;
import com.kcj_customer_be.entity.enums.RolesName;
import com.kcj_customer_be.exception.ErrorMessage;
import com.kcj_customer_be.exception.list.CustomerIdNotFound;
import com.kcj_customer_be.exception.list.CustomerIsExistException;
import com.kcj_customer_be.exception.list.CustomerNotFoundException;
import com.kcj_customer_be.exception.list.IdNullException;
import com.kcj_customer_be.mapper.CustomerMapper;
import com.kcj_customer_be.repo.CartRepository;
import com.kcj_customer_be.repo.CustomerRepository;
import com.kcj_customer_be.service.interfaces.CustomerService;
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

   private final CartRepository cartRepository;

   private final CustomerMapper customerMapper;

   private final PasswordEncoder encoder;

   // CREATE - REGISTRATION NEW CUSTOMER
   @Override
   @Transactional
   public CustomerResponseDto registrationCustomer(CustomerCreateDto customerCreateDto) throws CustomerNotFoundException {
      Optional<Customer> optionalCustomerByEmail = customerRepository.findByEmail(customerCreateDto.getEmail());

      if (optionalCustomerByEmail.isPresent()) {
         throw new CustomerIsExistException(ErrorMessage.EMAIL_ALREADY_EXISTS);
      }

      Customer customer = customerMapper.mapToCustomerFromCustomerCreateDto(customerCreateDto);
      customer.setPassword(encoder.encode(customerCreateDto.getPassword()));
      customer.setRole(RolesName.ROLE_CUSTOMER);

      Cart cart = new Cart();
      cart.setCustomer(customer);
      cartRepository.save(cart);

      Customer afterCreate = customerRepository.save(customer);
      return customerMapper.mapToCustomerAfterCreateDto(afterCreate);
   }

   // READ - GET CUSTOMER BY ID
   @Override
   @Transactional
   public CustomerDto getCustomerById(UUID customerId) throws IdNullException {
      Optional<Customer> customerOptional = customerRepository.findById(customerId);

      if (customerOptional.isEmpty()) {
         throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_ID_NOT_FOUND + customerId);
      }

      Customer expextedCustomer = customerOptional.get();
      if (expextedCustomer.getIsBlocked()) {
         throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_ID_NOT_FOUND + customerId);
      }
      return customerMapper.mapToCustomerDto(customerOptional.get());
   }

   // READ - GET CUSTOMER BY EMAIL
   @Override
   @Transactional
   public CustomerDto getCustomerByEmail(String email) throws IdNullException {
      Optional<Customer> customerOptional = customerRepository.findByEmail(email);

      if (customerOptional.isEmpty()) {
         throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND_WITH_EMAIL + email);
      }

      Customer expextedCustomer = customerOptional.get();
      if (expextedCustomer.getIsBlocked()) {
         throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND_WITH_EMAIL + email);
      }
      return customerMapper.mapToCustomerDto(customerOptional.get());
   }

   // UPDATE - CUSTOMER DETAILS
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

      Optional<Customer> optionalCustomerByEmail = customerRepository.findByEmail(customerUpdateDto.getEmail());
      if (optionalCustomerByEmail.isPresent() && !optionalCustomerByEmail.get().getId().equals(customerId)) {
         throw new CustomerIsExistException(ErrorMessage.EMAIL_ALREADY_EXISTS);
      }

      existingCustomer = customerMapper.mapToCustomerFromCustomerUpdateDto(customerUpdateDto, existingCustomer);

      if (customerUpdateDto.getPassword() != null && !customerUpdateDto.getPassword().isEmpty()) {
         existingCustomer.setPassword(encoder.encode(customerUpdateDto.getPassword()));
      }

      Customer afterUpdate = customerRepository.save(existingCustomer);
      return customerMapper.mapToCustomerAfterUpdateDto(afterUpdate);
   }

   // DELETE - BLOCK CUSTOMER
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
