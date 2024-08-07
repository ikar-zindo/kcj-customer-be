package com.kcj_customer_be.controller;

import com.kcj_customer_be.controller.interfaces.ProductControllerInterface;
import com.kcj_customer_be.dto.ProductDto;
import com.kcj_customer_be.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController implements ProductControllerInterface {

   private final ProductService productService;

   // READ - GET ALL AVAILABLE PRODUCTS
   @GetMapping
   public ResponseEntity<List<ProductDto>> getAllProductsDto() {
      return ResponseEntity.ok(productService.getAllProducts());
   }

   // READ - GET AVAILABLE PRODUCT BY ID
   @GetMapping("/{id}")
   public ResponseEntity<ProductDto> getProductDtoById(@PathVariable("id") Long productId) {
      ProductDto productDto = productService.getProductById(productId);
      return ResponseEntity.ok(productDto);
   }
}
