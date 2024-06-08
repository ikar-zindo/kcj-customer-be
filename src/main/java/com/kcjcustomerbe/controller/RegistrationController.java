package com.kcjcustomerbe.controller;

import com.kcjcustomerbe.controller.interfaces.RegistrationControllerInterface;
import com.kcjcustomerbe.dto.customer.CustomerCreateDto;
import com.kcjcustomerbe.dto.customer.CustomerResponseDto;
import com.kcjcustomerbe.service.CustomerService;
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
      return ResponseEntity.created(URI.create("customer/" + dto.getId())).body(dto);
   }
}
