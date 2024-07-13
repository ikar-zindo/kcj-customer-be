package com.kcj_customer_be.controller;

import com.kcj_customer_be.controller.interfaces.RegistrationControllerInterface;
import com.kcj_customer_be.dto.customer.CustomerCreateDto;
import com.kcj_customer_be.dto.customer.CustomerResponseDto;
import com.kcj_customer_be.service.interfaces.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController implements RegistrationControllerInterface {

   private final CustomerService customerService;

   // CREATE - REGISTRATION NEW CUSTOMER
   @PostMapping
   public ResponseEntity<CustomerResponseDto> registrationCustomer(
         @Valid @RequestBody CustomerCreateDto customerCreateDto) {

      CustomerResponseDto dto = customerService.registrationCustomer(customerCreateDto);
      return ResponseEntity.created(URI.create("customer")).body(dto);
   }
}
