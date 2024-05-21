package com.kcjcustomerbe.controller;

import com.kcjcustomerbe.annotation.*;
import com.kcjcustomerbe.dto.customer.CustomerAfterCreateDto;
import com.kcjcustomerbe.dto.customer.CustomerCreateDto;
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

   @CreateCustomer(path = "/creat")
   public CustomerAfterCreateDto registrationCustomer(
           @Valid @RequestBody CustomerCreateDto customerCreateDto) throws NullPointerException {

      return customerService.registrationCustomer(customerCreateDto);
   }

   @GetCustomer(path = "/{id}")
   public Customer getCustomerById(@UuidFormatChecker @PathVariable("id") String customerId) {
      return customerService.getCustomerById(UUID.fromString(customerId));
   }
}
