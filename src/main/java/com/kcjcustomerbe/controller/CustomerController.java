package com.kcjcustomerbe.controller;

import com.kcjcustomerbe.annotation.*;
import com.kcjcustomerbe.annotation.customer.BlockCustomer;
import com.kcjcustomerbe.annotation.customer.CreateCustomer;
import com.kcjcustomerbe.annotation.customer.GetCustomer;
import com.kcjcustomerbe.annotation.customer.UpdateCustomer;
import com.kcjcustomerbe.dto.customer.*;
import com.kcjcustomerbe.service.impl.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

   private final CustomerServiceImpl customerService;

   // CREATE - REGISTRATION NEW CUSTOMER
   @CreateCustomer
   public ResponseEntity<CustomerAfterCreateDto> registrationCustomer(
           @Valid @RequestBody CustomerCreateDto customerCreateDto) {

      CustomerAfterCreateDto customerAfterCreateDto =
            customerService.registrationCustomer(customerCreateDto);

      return ResponseEntity.ok(customerAfterCreateDto);
   }

   // READ - GET CUSTOMER BY ID
   @GetCustomer(path = "/{id}")
   public ResponseEntity<CustomerDto> getCustomerById(@UuidFormatChecker @PathVariable("id") String customerId) {
      CustomerDto customerDto =
            customerService.getCustomerById(UUID.fromString(customerId));

      return ResponseEntity.ok(customerDto);
   }

   // UPDATE - CUSTOMER DETAILS
   @UpdateCustomer(path = "/{id}")
   public ResponseEntity<CustomerAfterUpdateDto> updateCustomerInfo(@UuidFormatChecker @PathVariable("id") String customerId,
                                            @Valid @RequestBody CustomerUpdateDto customerUpdateDto) {

      CustomerAfterUpdateDto customerAfterUpdateDto =
            customerService.updateCustomerInfo(UUID.fromString(customerId), customerUpdateDto);

      return ResponseEntity.ok(customerAfterUpdateDto);
   }

   // DELETE - BLOCK CUSTOMER
   @BlockCustomer(path = "/{id}")
   public ResponseEntity<CustomerAfterUpdateDto> blockCustomerById(@UuidFormatChecker @PathVariable("id") String customerId) {
      CustomerAfterUpdateDto customerAfterUpdateDto =
            customerService.blockCustomerById(UUID.fromString(customerId));

      return ResponseEntity.ok(customerAfterUpdateDto);
   }
}
