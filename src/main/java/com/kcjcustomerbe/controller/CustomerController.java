package com.kcjcustomerbe.controller;

import com.kcjcustomerbe.controller.interfaces.CustomerControllerInterface;
import com.kcjcustomerbe.dto.customer.CustomerCreateDto;
import com.kcjcustomerbe.dto.customer.CustomerDto;
import com.kcjcustomerbe.dto.customer.CustomerResponseDto;
import com.kcjcustomerbe.dto.customer.CustomerUpdateDto;
import com.kcjcustomerbe.service.impl.CustomerServiceImpl;
import com.kcjcustomerbe.validation.UuidFormatChecker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController implements CustomerControllerInterface {

   private final CustomerServiceImpl customerService;

   // CREATE - REGISTRATION NEW CUSTOMER
   @PostMapping
   public ResponseEntity<CustomerResponseDto> registrationCustomer(
           @Valid @RequestBody CustomerCreateDto customerCreateDto) {

      CustomerResponseDto dto = customerService.registrationCustomer(customerCreateDto);

      return ResponseEntity.created(URI.create("customer/" + dto.getId())).body(dto);
   }

   // READ - GET CUSTOMER BY ID
   @GetMapping("/{id}")
   public ResponseEntity<CustomerDto> getCustomerById(@UuidFormatChecker @PathVariable("id") String customerId) {
      CustomerDto customerDto =
            customerService.getCustomerById(UUID.fromString(customerId));

      return ResponseEntity.ok(customerDto);
   }

   // UPDATE - CUSTOMER DETAILS
   @PutMapping("/{id}")
   public ResponseEntity<CustomerResponseDto> updateCustomerInfo(@UuidFormatChecker @PathVariable("id") String customerId,
                                                                 @Valid @RequestBody CustomerUpdateDto customerUpdateDto) {

      CustomerResponseDto customerResponseDto =
            customerService.updateCustomerInfo(UUID.fromString(customerId), customerUpdateDto);

      return ResponseEntity.ok(customerResponseDto);
   }

   // DELETE - BLOCK CUSTOMER
   @DeleteMapping("/{id}")
   public ResponseEntity<CustomerResponseDto> blockCustomerById(@UuidFormatChecker @PathVariable("id") String customerId) {
      CustomerResponseDto customerResponseDto =
            customerService.blockCustomerById(UUID.fromString(customerId));

      return ResponseEntity.ok(customerResponseDto);
   }
}
