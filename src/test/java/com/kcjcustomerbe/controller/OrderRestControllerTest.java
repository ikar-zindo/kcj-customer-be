package com.kcjcustomerbe.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/db/drop.sql", "/db/schema.sql", "/db/data-test.sql"})
public class OrderRestControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @BeforeEach
   void set_up(final ApplicationContext applicationContext) {
      this.mockMvc = applicationContext.getBean(MockMvc.class);
   }


   @Test
   void permit_all_api_restaurant_anonymous_test() throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders
                  .get("/restaurant"))
            .andExpect(status().isOk());
   }


   @Test
   void permit_all_api_product_anonymous_test() throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders
                  .get("/product"))
            .andExpect(status().isUnauthorized());
   }
}
