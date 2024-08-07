package com.kcj_customer_be.controller;

import com.kcj_customer_be.controller.interfaces.CustomerControllerInterface;
import com.kcj_customer_be.dto.customer.CustomerDto;
import com.kcj_customer_be.dto.customer.CustomerResponseDto;
import com.kcj_customer_be.dto.customer.CustomerUpdateDto;
import com.kcj_customer_be.service.interfaces.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController implements CustomerControllerInterface {

   private final CustomerService customerService;

   // READ - GET CUSTOMER BY ID
   @GetMapping
   public ResponseEntity<CustomerDto> getCustomerByEmail() {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      if (principal instanceof UserDetails userDetails) {
         String email = userDetails.getUsername();
         CustomerDto customerDto = customerService.getCustomerByEmail(email);

         return ResponseEntity.ok(customerDto);
      }
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
   }

   // UPDATE - CUSTOMER DETAILS
   @PutMapping
   public ResponseEntity<CustomerResponseDto> updateCustomerInfo(@Valid @RequestBody CustomerUpdateDto customerUpdateDto) {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      if (principal instanceof UserDetails userDetails) {
         String email = userDetails.getUsername();
         CustomerDto customerDto = customerService.getCustomerByEmail(email);

         CustomerResponseDto customerResponseDto =
               customerService.updateCustomerInfo(customerDto.getId(), customerUpdateDto);

         return ResponseEntity.ok(customerResponseDto);
      }
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
   }

   // DELETE - BLOCK CUSTOMER
   @DeleteMapping
   public ResponseEntity<CustomerResponseDto> blockCustomerById() {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      if (principal instanceof UserDetails userDetails) {
         String email = userDetails.getUsername();
         CustomerDto customerDto = customerService.getCustomerByEmail(email);

         CustomerResponseDto customerResponseDto =
               customerService.blockCustomerById(customerDto.getId());

         return ResponseEntity.ok(customerResponseDto);
      }
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
   }
}
