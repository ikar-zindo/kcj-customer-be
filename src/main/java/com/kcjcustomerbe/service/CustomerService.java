package com.kcjcustomerbe.service;

import com.kcjcustomerbe.dto.customer.CustomerAfterCreateDto;
import com.kcjcustomerbe.dto.customer.CustomerAfterUpdateDto;
import com.kcjcustomerbe.dto.customer.CustomerCreateDto;
import com.kcjcustomerbe.dto.customer.CustomerUpdateDto;
import com.kcjcustomerbe.entity.Customer;

import java.util.UUID;

public interface CustomerService {

   CustomerAfterCreateDto registrationCustomer(CustomerCreateDto userCreateDto);

   Customer getCustomerById(UUID id);

   CustomerAfterUpdateDto updateCustomerInfo(UUID id, CustomerUpdateDto userUpdateDto);

   void deleteCustomerById(UUID id);
}
