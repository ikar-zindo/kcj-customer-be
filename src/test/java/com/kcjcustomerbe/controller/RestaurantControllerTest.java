package com.kcjcustomerbe.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcjcustomerbe.dto.RestaurantDto;
import com.kcjcustomerbe.dto.ReviewDto;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/db/drop.sql", "/db/schema.sql", "/db/data-test.sql"})
public class RestaurantControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private ObjectMapper objectMapper;

   Long restaurantId;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this);
   }


   @Test
   void getRestaurantPositiveTest() throws Exception {
      restaurantId = 1L;

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/restaurant/{id}", Long.toString(restaurantId))
                  .contentType(MediaType.APPLICATION_JSON))
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      System.out.println(jsonResponse);

      Assertions.assertEquals(200, result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains(Long.toString(restaurantId)));
   }


   @Test
   void getRestaurantNegativeTest() throws Exception {
      restaurantId = 0L;

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/restaurant/{id}", restaurantId)
                  .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();

      Assertions.assertEquals(404, result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains(ErrorMessage.RESTAURANT_ID_NOT_FOUND + restaurantId));
   }


   @Test
   void addReviewfPositiveTest() throws Exception {
      String customerId = "d234d99d-170e-42f7-b6ae-435ee56f49a1";
      restaurantId = 1L;

      ReviewDto reviewDto = new ReviewDto(
            null,
            null,
            null,
            BigDecimal.valueOf(5.0),
            "Good food!",
            LocalDateTime.now(),
            null
      );
      String jsonRequest = objectMapper.writeValueAsString(reviewDto);

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .post("/restaurant/" + restaurantId + "/reviews")
                  .param("customerId", customerId)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(jsonRequest))
            .andExpect(status().isCreated())
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      ReviewDto reviewResponseDto;
      reviewResponseDto = objectMapper.readValue(jsonResponse, ReviewDto.class);

      Assertions.assertEquals(201, result.getResponse().getStatus());
      Assertions.assertNotNull(reviewResponseDto.getId());
      Assertions.assertEquals(reviewDto.getComment(), reviewResponseDto.getComment());
      Assertions.assertEquals(reviewDto.getRating(), reviewResponseDto.getRating());
   }


   @Test
   void addReviewNegativeTest() throws Exception {
      String customerId = "d234d99d-170e-42f7-b6ae-435ee56f49a1";
      restaurantId = 1L;

      ReviewDto reviewDto = new ReviewDto(
            null,
            null,
            null,
            null,
            "Good food!",
            LocalDateTime.now(),
            null
      );
      String jsonRequest = objectMapper.writeValueAsString(reviewDto);

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .post("/restaurant/" + restaurantId + "/reviews")
                  .param("customerId", customerId)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(jsonRequest))
            .andExpect(status().isBadRequest())
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();

      Assertions.assertEquals(400, result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains("Field cannot be null"));
   }


   @Test
   void getAllRestaurantsPositiveTest() throws Exception {
      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/restaurant")
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
   void getAllRestaurantsNegativeTest() throws Exception {
      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/restaurant")
                  .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      System.out.println(jsonResponse);

      Assertions.assertEquals(404, result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains(ErrorMessage.RESTAURANTS_LIST_IS_EMPTY));
   }
}
