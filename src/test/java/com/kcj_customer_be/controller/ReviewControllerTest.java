package com.kcj_customer_be.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcj_customer_be.dto.RestaurantDto;
import com.kcj_customer_be.exception.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
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
public class ReviewControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private ObjectMapper objectMapper;

   Long reviewId;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this);
   }


   @Test
   void get_review_by_id_positive_test() throws Exception {
      reviewId = 1L;

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/review/{id}", Long.toString(reviewId))
                  .contentType(MediaType.APPLICATION_JSON))
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      System.out.println(jsonResponse);

      Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains(Long.toString(reviewId)));
   }


   @Test
   void get_review_by_id_negative_test() throws Exception {
      reviewId = 0L;

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/review/{id}", reviewId)
                  .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();

      Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains(ErrorMessage.REVIEW_ID_NOT_FOUND + reviewId));
   }


   @Test
   void get_all_reviews_positive_test() throws Exception {
      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/review")
                  .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      System.out.println(jsonResponse);

      List<RestaurantDto> restaurants = objectMapper.readValue(jsonResponse, new TypeReference<>() {
      });

      Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
      Assertions.assertFalse(restaurants.isEmpty());
   }


   @Test
   @Sql("/db/clear.sql")
   void get_all_reviews_negative_test() throws Exception {
      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/review")
                  .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      System.out.println(jsonResponse);

      Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains(ErrorMessage.REVIEWS_NOT_FOUND));
   }
}
