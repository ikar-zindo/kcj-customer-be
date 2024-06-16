package com.kcjcustomerbe.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcjcustomerbe.constant.GlobalConstant;
import com.kcjcustomerbe.dto.customer.CustomerCreateDto;
import com.kcjcustomerbe.dto.customer.CustomerResponseDto;
import com.kcjcustomerbe.dto.customer.CustomerUpdateDto;
import com.kcjcustomerbe.exception.ErrorMessage;
import com.kcjcustomerbe.exception.list.CustomerIsExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/db/drop.sql", "/db/schema.sql", "/db/data-test.sql"})
public class CustomerControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private ObjectMapper objectMapper;

   @Test
   @WithAnonymousUser
   void registration_positive_test() throws Exception, CustomerIsExistException {
      CustomerCreateDto dto = new CustomerCreateDto(
            "John",
            "Snow",
            "john@gmail.com",
            "QwErTy123!",
            "+490134567899",
            "Alexanderplz. 1",
            "09874",
            false,
            LocalDateTime.now()
      );
      String jsonRequest = objectMapper.writeValueAsString(dto);

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .post("/registration")
                  .content(jsonRequest)
                  .contentType(MediaType.APPLICATION_JSON))
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      CustomerResponseDto customerResponseDto;
      customerResponseDto = objectMapper.readValue(jsonResponse, CustomerResponseDto.class);

      Assertions.assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
      Assertions.assertNotNull(customerResponseDto.getId());
      Assertions.assertEquals(GlobalConstant.CUSTOMER_CREATED_SUCCESS_MESSAGE, customerResponseDto.status);
   }


   @Test
   @WithAnonymousUser
   void registration_email_exist_negative_test() throws Exception, CustomerIsExistException {
      CustomerCreateDto dto = new CustomerCreateDto(
            "Maria",
            "Anders",
            "maria@mail.com",
            "QwErTy123!",
            "+490134567899",
            "Obere Str. 57",
            "09874",
            false,
            LocalDateTime.now()
      );
      String jsonRequest = objectMapper.writeValueAsString(dto);

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .post("/registration")
                  .content(jsonRequest)
                  .contentType(MediaType.APPLICATION_JSON))
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();

      Assertions.assertEquals(HttpStatus.CONFLICT.value(), result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains(ErrorMessage.EMAIL_ALREADY_EXISTS));
   }


   @Test
   @WithMockUser(username = "maria@mail.com", password = "1qaz", roles = {"CUSTOMER"})
   void get_customer_positive_test() throws Exception {

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/customer")
                  .contentType(MediaType.APPLICATION_JSON))
            .andReturn();


      String jsonResponse = result.getResponse().getContentAsString();
      System.out.println(jsonResponse);

      Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
   }


   @Test
   @WithAnonymousUser
   void get_customer_negative_test() throws Exception {
      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/customer")
                  .contentType(MediaType.APPLICATION_JSON))
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonNode = objectMapper.readTree(jsonResponse);

      Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), result.getResponse().getStatus());
      Assertions.assertEquals("Unauthorized", jsonNode.get("error").asText());
   }


   @Test
   @WithMockUser(username = "maria@mail.com", password = "1qaz", roles = {"CUSTOMER"})
   void update_customer_info_positive_test() throws Exception {
      CustomerUpdateDto dto = new CustomerUpdateDto(
            "Maria",
            "Anders",
            "janifer@mail.com",
            "QwErTy123!",
            "+490134567899",
            "Obere Str. 57",
            "09874",
            LocalDateTime.now(),
            false,
            null
      );
      String jsonRequest = objectMapper.writeValueAsString(dto);

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .put("/customer", jsonRequest)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(jsonRequest))
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      System.out.println(jsonResponse);

      CustomerResponseDto customerResponseDto = objectMapper.readValue(jsonResponse, CustomerResponseDto.class);
      Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
      Assertions.assertNotNull(customerResponseDto.getId());
      Assertions.assertEquals(GlobalConstant.CUSTOMER_UPDATED_SUCCESS_MESSAGE, customerResponseDto.status);
   }


   @Test
   void update_customer_info_unauthorized_negative_test() throws Exception {
      CustomerUpdateDto dto = new CustomerUpdateDto(
            "Maria",
            "Anders",
            "ana@mail.com",
            "QwErTy123!",
            "+490134567899",
            "Obere Str. 57",
            "09874",
            LocalDateTime.now(),
            false,
            null
      );
      String jsonRequest = objectMapper.writeValueAsString(dto);

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .put("/customer", jsonRequest)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(jsonRequest))
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      System.out.println(jsonResponse);

      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonNode = objectMapper.readTree(jsonResponse);

      Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), result.getResponse().getStatus());
      Assertions.assertEquals("Unauthorized", jsonNode.get("error").asText());
   }


   @Test
   @WithMockUser(username = "maria@mail.com", password = "1qaz", roles = {"CUSTOMER"})
   void update_customer_info_email_exists_negative_test() throws Exception {
      CustomerUpdateDto dto = new CustomerUpdateDto(
            "Maria",
            "Anders",
            "ana@mail.com",
            "QwErTy123!",
            "+490134567899",
            "Obere Str. 57",
            "09874",
            LocalDateTime.now(),
            false,
            null
      );
      String jsonRequest = objectMapper.writeValueAsString(dto);

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .put("/customer", jsonRequest)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(jsonRequest))
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      System.out.println(jsonResponse);

      Assertions.assertEquals(HttpStatus.CONFLICT.value(), result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains(ErrorMessage.EMAIL_ALREADY_EXISTS));
   }


   @Test
   @WithMockUser(username = "maria@mail.com", password = "1qaz", roles = {"CUSTOMER"})
   void block_customer_positive_test() throws Exception {
      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .delete("/customer")
                  .contentType(MediaType.APPLICATION_JSON))
            .andReturn();

      Assertions.assertEquals(200, result.getResponse().getStatus());
   }


   @Test
   void block_customer_unauthorized_negative_test() throws Exception {
      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .delete("/customer")
                  .accept(MediaType.APPLICATION_JSON))
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonNode = objectMapper.readTree(jsonResponse);

      Assertions.assertEquals(HttpStatus.UNAUTHORIZED.value(), result.getResponse().getStatus());
      Assertions.assertEquals("Unauthorized", jsonNode.get("error").asText());
   }
}
