package com.kcj_customer_be.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/db/drop.sql", "/db/schema.sql", "/db/data-test.sql"})
public class CartControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Test
   @WithMockUser(username = "maria@mail.com", password = "1qaz", roles = {"CUSTOMER"})
   void addProductToCartPositiveTest() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders
                  .put("/cart/addToCart")
                  .param("productId", "1")
                  .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
   }

   @Test
   @WithAnonymousUser
   void addProductToCartUnauthorizedNegativeTest() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders
                  .put("/cart/addToCart")
                  .param("productId", "1")
                  .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized());
   }

   @Test
   @WithMockUser(username = "maria@mail.com", password = "1qaz", roles = {"CUSTOMER"})
   public void addProductToCartWithoutProductIdNegativeTest() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders
                  .put("/cart/addToCart")
                  .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
   }

   @Test
   @WithMockUser(username = "maria@mail.com", password = "1qaz", roles = {"CUSTOMER"})
   public void addNonExistentProductToCartNegativeTest() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders
                  .put("/cart/addToCart")
                  .param("productId", "9999")
                  .contentType("application/x-www-form-urlencoded")
                  .with(csrf()))
            .andExpect(status().isNotFound());
   }
}