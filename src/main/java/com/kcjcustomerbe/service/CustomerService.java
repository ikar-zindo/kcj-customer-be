package com.kcjcustomerbe.service;

import com.kcjcustomerbe.dto.customer.*;

import java.util.UUID;

public interface CustomerService {

   CustomerAfterCreateDto registrationCustomer(CustomerCreateDto userCreateDto);

   CustomerDto getCustomerById(UUID customerId);

   CustomerAfterUpdateDto updateCustomerInfo(UUID customerId, CustomerUpdateDto userUpdateDto);

   void deleteCustomerById(UUID customerId);
}
