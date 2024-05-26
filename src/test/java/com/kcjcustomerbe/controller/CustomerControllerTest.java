package com.kcjcustomerbe.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcjcustomerbe.constant.GlobalConstant;
import com.kcjcustomerbe.dto.customer.CustomerAfterCreateDto;
import com.kcjcustomerbe.dto.customer.CustomerAfterUpdateDto;
import com.kcjcustomerbe.dto.customer.CustomerCreateDto;
import com.kcjcustomerbe.dto.customer.CustomerUpdateDto;
import com.kcjcustomerbe.exception.ErrorMessage;
import com.kcjcustomerbe.exception.list.customer.CustomerIsExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/init-db-test.sql")
@Sql("/db/data-test.sql")
public class CustomerControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private ObjectMapper objectMapper;

   private final UUID id = UUID.randomUUID();


   @Test
   void createCustomerPositiveTest() throws Exception, CustomerIsExistException {
      CustomerCreateDto dto = new CustomerCreateDto(
            "John",
            "Snow",
            "john@gmail.com",
            "johnname",
            "QwErTy123!",
            "+490134567899",
            "Alexanderplz. 1",
            "09874",
            false,
            LocalDateTime.now()
      );
      String json = objectMapper.writeValueAsString(dto);

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .post("/customer")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(json))
            .andReturn();

      String jsonResult = result.getResponse().getContentAsString();
      CustomerAfterCreateDto customerAfterCreateDto;
      customerAfterCreateDto = objectMapper.readValue(jsonResult, CustomerAfterCreateDto.class);

      Assertions.assertEquals(201, result.getResponse().getStatus());
      Assertions.assertNotNull(customerAfterCreateDto.getId());
      Assertions.assertEquals(GlobalConstant.CUSTOMER_CREATED_SUCCESS_MESSAGE, customerAfterCreateDto.status);
   }


   @Test
   void createCustomerNegativeTest() throws Exception, CustomerIsExistException {
      CustomerCreateDto dto = new CustomerCreateDto(
            "Maria",
            "Anders",
            "janifer@mail.com",
            "maria",
            "QwErTy123!",
            "+490134567899",
            "Obere Str. 57",
            "09874",
            false,
            LocalDateTime.now()
      );
      String json = objectMapper.writeValueAsString(dto);

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .post("/customer")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(json))
            .andReturn();

      String jsonResult = result.getResponse().getContentAsString();

      Assertions.assertEquals(409, result.getResponse().getStatus());
      Assertions.assertTrue(jsonResult.contains(ErrorMessage.USERNAME_ALREADY_EXISTS));
   }


   @Test
   void getCustomerPositiveTest() throws Exception {
      UUID id = UUID.fromString("d234d99d-170e-42f7-b6ae-435ee56f49a1");

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/customer/{id}", id.toString())
                  .contentType(MediaType.APPLICATION_JSON))
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      System.out.println(jsonResponse);

      Assertions.assertEquals(200, result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains(id.toString()));
   }


   @Test
   void getCustomerNegativeTest() throws Exception {
      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/customer/{id}", id.toString())
                  .contentType(MediaType.APPLICATION_JSON))
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();

      Assertions.assertEquals(404, result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains(ErrorMessage.CUSTOMER_ID_NOT_FOUND + id));
   }


   @Test
   void updateCustomerInfoPositiveTest() throws Exception {
      UUID id = UUID.fromString("d234d99d-170e-42f7-b6ae-435ee56f49a1");
      CustomerUpdateDto dto = new CustomerUpdateDto(
            "Maria",
            "Anders",
            "janifer@mail.com",
            "maria",
            "QwErTy123!",
            "+490134567899",
            "Obere Str. 57",
            "09874",
            LocalDateTime.now(),
            false
      );
      String jsonData = objectMapper.writeValueAsString(dto);

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .put("/customer/{id}", id.toString(), jsonData)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(jsonData))
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      System.out.println(jsonResponse);

      CustomerAfterUpdateDto customerAfterUpdateDto = objectMapper.readValue(jsonResponse, CustomerAfterUpdateDto.class);
      Assertions.assertEquals(200, result.getResponse().getStatus());
      Assertions.assertNotNull(customerAfterUpdateDto.getId());
      Assertions.assertEquals(GlobalConstant.CUSTOMER_UPDATED_SUCCESS_MESSAGE, customerAfterUpdateDto.status);
   }


   @Test
   void updateCustomerInfoNegativeTest() throws Exception {
      CustomerUpdateDto dto = new CustomerUpdateDto(
            "Maria",
            "Anders",
            "janifer@mail.com",
            "maria",
            "QwErTy123!",
            "+490134567899",
            "Obere Str. 57",
            "09874",
            LocalDateTime.now(),
            false
      );
      String jsonData = objectMapper.writeValueAsString(dto);

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .put("/customer/{id}", id.toString(), jsonData)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(jsonData))
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      System.out.println(jsonResponse);

      Assertions.assertEquals(404, result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains(ErrorMessage.CUSTOMER_ID_NOT_FOUND + id));
   }


   @Test
   void blockCustomerPositiveTest() throws Exception {
      UUID id = UUID.fromString("d234d99d-170e-42f7-b6ae-435ee56f49a1");
      CustomerUpdateDto dto = new CustomerUpdateDto(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            LocalDateTime.now(),
            true
      );
      String jsonData = objectMapper.writeValueAsString(dto);

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .put("/customer/{id}", id.toString(), jsonData)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(jsonData))
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      System.out.println(jsonResponse);

      CustomerAfterUpdateDto customerAfterUpdateDto = objectMapper.readValue(jsonResponse, CustomerAfterUpdateDto.class);
      Assertions.assertEquals(200, result.getResponse().getStatus());
      Assertions.assertNotNull(customerAfterUpdateDto.getId());
      Assertions.assertEquals(GlobalConstant.CUSTOMER_UPDATED_SUCCESS_MESSAGE, customerAfterUpdateDto.status);
   }

   @Test
   void blockCustomerNegativeTest() throws Exception {
      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .delete("/customer/{id}", id.toString())
                  .accept(MediaType.APPLICATION_JSON))
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();

      Assertions.assertEquals(404, result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains(ErrorMessage.CUSTOMER_ID_NOT_FOUND + id));
   }
}
