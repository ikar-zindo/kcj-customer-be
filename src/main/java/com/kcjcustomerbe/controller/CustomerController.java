package com.kcjcustomerbe.controller;

import com.kcjcustomerbe.annotation.*;
import com.kcjcustomerbe.dto.customer.*;
import com.kcjcustomerbe.entity.Customer;
import com.kcjcustomerbe.service.impl.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

   private final CustomerServiceImpl customerService;

   @CreateCustomer
   public CustomerAfterCreateDto registrationCustomer(
           @Valid @RequestBody CustomerCreateDto customerCreateDto) {

      return customerService.registrationCustomer(customerCreateDto);
   }

   @GetCustomer(path = "/{id}")
   public CustomerDto getCustomerById(@UuidFormatChecker @PathVariable("id") String customerId) {
      return customerService.getCustomerById(UUID.fromString(customerId));
   }

   @PutMapping("/{id}")
   public CustomerAfterUpdateDto updateUser(@UuidFormatChecker @PathVariable("id") String id,
                                            @RequestBody CustomerUpdateDto customerUpdateDto) {

      return customerService.updateCustomerInfo(UUID.fromString(id), customerUpdateDto);
   }
}
