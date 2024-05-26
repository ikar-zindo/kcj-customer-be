package com.kcjcustomerbe.service;

import com.kcjcustomerbe.dto.customer.*;

import java.util.UUID;

public interface CustomerService {

   CustomerResponseDto registrationCustomer(CustomerCreateDto userCreateDto);

   CustomerDto getCustomerById(UUID customerId);

   CustomerResponseDto updateCustomerInfo(UUID customerId, CustomerUpdateDto userUpdateDto);

   CustomerResponseDto blockCustomerById(UUID customerId);
}
