package com.kcjcustomerbe.service.interfaces;

import com.kcjcustomerbe.dto.customer.CustomerCreateDto;
import com.kcjcustomerbe.dto.customer.CustomerDto;
import com.kcjcustomerbe.dto.customer.CustomerResponseDto;
import com.kcjcustomerbe.dto.customer.CustomerUpdateDto;
import com.kcjcustomerbe.validation.UuidFormatChecker;

import java.util.UUID;

public interface CustomerService {

   /**
    * Registers a new customer based on the provided user information.
    *
    * @param userCreateDto The CustomerCreateDto containing user registration details.
    * @return The CustomerResponseDto representing the result of customer registration.
    */
   CustomerResponseDto registrationCustomer(CustomerCreateDto userCreateDto);

   /**
    * Retrieves the customer details by customer ID.
    *
    * @param customerId The UUID of the customer to retrieve.
    * @return The CustomerDto containing customer details.
    */
   CustomerDto getCustomerById(@UuidFormatChecker UUID customerId);

   /**
    * Retrieves the customer details by email.
    *
    * @param email The email address of the customer to retrieve.
    * @return The CustomerDto containing customer details.
    */
   CustomerDto getCustomerByEmail(String email);

   /**
    * Updates the information of an existing customer identified by customer ID.
    *
    * @param customerId    The UUID of the customer to update.
    * @param userUpdateDto The CustomerUpdateDto containing updated user information.
    * @return The CustomerResponseDto representing the result of customer information update.
    */
   CustomerResponseDto updateCustomerInfo(@UuidFormatChecker UUID customerId, CustomerUpdateDto userUpdateDto);

   /**
    * Blocks a customer account identified by customer ID.
    *
    * @param customerId The UUID of the customer to block.
    * @return The CustomerResponseDto representing the result of customer blocking operation.
    */
   CustomerResponseDto blockCustomerById(@UuidFormatChecker UUID customerId);
}
