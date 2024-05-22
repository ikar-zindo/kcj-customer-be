package com.kcjcustomerbe.controller;

import com.kcjcustomerbe.dto.ProductDto;
import com.kcjcustomerbe.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

   private final ProductService productService;

   // READ - GET ALL AVAILABLE PRODUCTS
   @GetMapping
   public ResponseEntity<List<ProductDto>> getAllProduct() {
      return ResponseEntity.ok(productService.getAllProducts());
   }

   // READ - GET AVAILABLE PRODUCT BY ID
   @GetMapping("/{id}")
   public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
      ProductDto productDto = productService.getProductById(productId);
      return ResponseEntity.ok(productDto);
   }
}
