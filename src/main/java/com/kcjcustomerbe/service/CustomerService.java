package com.kcjcustomerbe.service;

import com.kcjcustomerbe.dto.customer.*;

import java.util.UUID;

public interface CustomerService {

   CustomerAfterCreateDto registrationCustomer(CustomerCreateDto userCreateDto);

   CustomerDto getCustomerById(UUID id);

   CustomerAfterUpdateDto updateCustomerInfo(UUID id, CustomerUpdateDto userUpdateDto);

   void deleteCustomerById(UUID id);
}
