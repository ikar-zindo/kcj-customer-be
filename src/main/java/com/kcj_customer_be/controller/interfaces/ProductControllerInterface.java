package com.kcj_customer_be.controller.interfaces;

import com.kcj_customer_be.config.SwaggerConfig;
import com.kcj_customer_be.dto.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = SwaggerConfig.PRODUCT, description = "allows you to receive information about products")
public interface ProductControllerInterface {

   // READ - GET ALL AVAILABLE PRODUCTS
   @Operation(
         summary = "Information about all products",
         description = "allows you to get information about all products, not require authorization"
   )
   ResponseEntity<List<ProductDto>> getAllProductsDto();

   // READ - GET AVAILABLE PRODUCT BY ID
   @Operation(
         summary = "Information about product with selected id",
         description = "allows you to get information about product with selected id, , not require authorization"
   )
   ResponseEntity<ProductDto> getProductDtoById(@PathVariable(name = "id") Long id);
}