package com.kcj_customer_be.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcj_customer_be.dto.RestaurantDto;
import com.kcj_customer_be.dto.ReviewDto;
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
import org.springframework.security.test.context.support.WithMockUser;
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
   @WithMockUser(username = "maria@mail.com", password = "1qaz", roles = {"CUSTOMER"})
   void getRestaurantPositiveTest() throws Exception {
      restaurantId = 1L;

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/restaurant/{id}", Long.toString(restaurantId))
                  .contentType(MediaType.APPLICATION_JSON))
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      System.out.println(jsonResponse);

      Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains(Long.toString(restaurantId)));
   }

   @Test
   @WithMockUser(username = "maria@mail.com", password = "1qaz", roles = {"CUSTOMER"})
   void getRestaurantNegativeTest() throws Exception {
      restaurantId = 0L;

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/restaurant/{id}", restaurantId)
                  .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();

      Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains(ErrorMessage.RESTAURANT_ID_NOT_FOUND + restaurantId));
   }

   @Test
   @WithMockUser(username = "maria@mail.com", password = "1qaz", roles = {"CUSTOMER"})
   void addReviewPositiveTest() throws Exception {
      ReviewDto reviewDto = new ReviewDto(
            null,
            null,
            null,
            BigDecimal.valueOf(5.0),
            "Good food!",
            null,
            null
      );
      String jsonRequest = objectMapper.writeValueAsString(reviewDto);

      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .post("/restaurant/add-review")
                  .param("restaurantId", "1")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(jsonRequest))
            .andExpect(status().isCreated())
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      ReviewDto reviewResponseDto;
      reviewResponseDto = objectMapper.readValue(jsonResponse, ReviewDto.class);

      Assertions.assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
      Assertions.assertNotNull(reviewResponseDto.getId());
      Assertions.assertEquals(reviewDto.getComment(), reviewResponseDto.getComment());
      Assertions.assertEquals(reviewDto.getRating(), reviewResponseDto.getRating());
   }

   @Test
   @WithMockUser(username = "maria@mail.com", password = "1qaz", roles = {"CUSTOMER"})
   void addReviewNegativeTest() throws Exception {
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
                  .post("/restaurant/add-review")
                  .param("restaurantId", "1")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(jsonRequest))
            .andExpect(status().isBadRequest())
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();

      Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains("Field cannot be null"));
   }

   @Test
   @WithMockUser(username = "maria@mail.com", password = "1qaz", roles = {"CUSTOMER"})
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

      Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
      Assertions.assertFalse(restaurants.isEmpty());
   }

   @Test
   @Sql("/db/clear.sql")
   @WithMockUser(username = "maria@mail.com", password = "1qaz", roles = {"CUSTOMER"})
   void getAllRestaurantsNegativeTest() throws Exception {
      MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                  .get("/restaurant")
                  .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andReturn();

      String jsonResponse = result.getResponse().getContentAsString();
      System.out.println(jsonResponse);

      Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
      Assertions.assertTrue(jsonResponse.contains(ErrorMessage.RESTAURANTS_LIST_IS_EMPTY));
   }
}