package com.kcjcustomerbe.service.impl;

import com.kcjcustomerbe.dto.customer.*;
import com.kcjcustomerbe.entity.Customer;
import com.kcjcustomerbe.entity.enums.Role;
import com.kcjcustomerbe.exception.ErrorMessage;
import com.kcjcustomerbe.exception.list.CustomerIsExistException;
import com.kcjcustomerbe.exception.list.CustomerNotFoundException;
import com.kcjcustomerbe.exception.list.IdNullException;
import com.kcjcustomerbe.mapper.CustomerMapper;
import com.kcjcustomerbe.repo.CartRepository;
import com.kcjcustomerbe.repo.CustomerRepository;
import com.kcjcustomerbe.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

   private final CustomerRepository customerRepository;

   private final CartRepository cartRepository;

   private final CustomerMapper customerMapper;

   @Override
   @Transactional
   public CustomerAfterCreateDto registrationCustomer(CustomerCreateDto customerCreateDto) throws CustomerNotFoundException {
      Optional<Customer> optionalCustomerByUsername = customerRepository.findByUsername(customerCreateDto.getUsername());
      Optional<Customer> optionalCustomerByEmail = customerRepository.findByEmail(customerCreateDto.getUsername());

      if (optionalCustomerByUsername.isEmpty()) {
         if (optionalCustomerByEmail.isEmpty()) {

            Customer customer = customerMapper.mapCustomerFromDto(customerCreateDto);
            customer.setRole(Role.ROLE_CUSTOMER);
            Customer afterCreate = customerRepository.save(customer);

            return customerMapper.mapToCustomerAfterCreateDto(afterCreate);

         } else {
            throw new CustomerIsExistException(ErrorMessage.EMAIL_ALREADY_EXISTS);
         }
      } else {
         throw new CustomerIsExistException(ErrorMessage.USERNAME_ALREADY_EXISTS);
      }
   }

   @Override
   @Transactional
   public CustomerDto getCustomerById(UUID customerId) throws IdNullException {
      Optional<Customer> customerOptional = customerRepository.findById(customerId);

      if (customerOptional.isPresent()) {
         return customerMapper.mapToCustomerDto(customerOptional.get());

      } else {
         throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_ID_NOT_FOUND + customerId);
      }
   }

   @Override
   public CustomerAfterUpdateDto updateCustomerInfo(UUID id, CustomerUpdateDto customerUpdateDto) {
      return null;
   }

   @Override
   @Transactional
   public void deleteCustomerById(UUID id) throws IdNullException {
      Optional<Customer> customer = customerRepository.findById(id);

      if (customer.isPresent()) {
         customerRepository.deleteById(id);
      } else {
         throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
      }
   }
}