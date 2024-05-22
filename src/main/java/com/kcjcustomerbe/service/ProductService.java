package com.kcjcustomerbe.service;

import com.kcjcustomerbe.dto.ProductDto;

import java.util.List;

public interface ProductService {

   ProductDto getProductById(Long id);

   List<ProductDto> getAllProducts();
}
