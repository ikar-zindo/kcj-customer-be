package com.kcjcustomerbe.service;

import com.kcjcustomerbe.dto.customer.*;
import com.kcjcustomerbe.validation.UuidFormatChecker;
import jakarta.transaction.Transactional;

import java.util.UUID;

public interface CustomerService {

   CustomerResponseDto registrationCustomer(CustomerCreateDto userCreateDto);

   CustomerDto getCustomerById(@UuidFormatChecker UUID customerId);

   CustomerDto getCustomerByEmail(String email);

   CustomerResponseDto updateCustomerInfo(@UuidFormatChecker UUID customerId, CustomerUpdateDto userUpdateDto);

   CustomerResponseDto blockCustomerById(@UuidFormatChecker UUID customerId);
}
