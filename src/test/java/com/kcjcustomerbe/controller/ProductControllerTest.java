package com.kcjcustomerbe.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcjcustomerbe.dto.RestaurantDto;
import com.kcjcustomerbe.exception.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/db/drop.sql", "/db/schema.sql", "/db/data-test.sql"})
public class ProductControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private ObjectMapper objectMapper;

   Long productId;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this);
   }


   @Test
   void getProductByIdPositiveTest() throws Exception {
      productId = 1L;

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/product/{id}", Long.toString(productId))
                  .contentType(MediaType.APPLICATION_JSON))
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      System.out.println(jsonResponse);

      Assertions.assertEquals(200, result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains(Long.toString(productId)));
   }


   @Test
   void getProductByIdNegativeTest() throws Exception {
      productId = 0L;

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/product/{id}", productId)
                  .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();

      Assertions.assertEquals(404, result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains(ErrorMessage.PRODUCT_ID_NOT_FOUND + productId));
   }


   @Test
   void getAllProductsPositiveTest() throws Exception {
      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/product")
                  .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      System.out.println(jsonResponse);

      List<RestaurantDto> restaurants = objectMapper.readValue(jsonResponse, new TypeReference<>() {
      });

      Assertions.assertEquals(200, result.getResponse().getStatus());
      Assertions.assertFalse(restaurants.isEmpty());
   }


   @Test
   @Sql("/db/clear.sql")
   void getAllProductsNegativeTest() throws Exception {
      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/product")
                  .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      System.out.println(jsonResponse);

      Assertions.assertEquals(404, result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains(ErrorMessage.PRODUCTS_NOT_FOUND));
   }
}
