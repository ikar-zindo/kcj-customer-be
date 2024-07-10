package com.kcjcustomerbe.service.interfaces;

import com.kcjcustomerbe.dto.ProductDto;

import java.util.List;

public interface ProductService {

   /**
    * Retrieves product information based on the provided product ID.
    *
    * @param id The ID of the product to retrieve.
    * @return The ProductDto containing product details.
    */
   ProductDto getProductById(Long id);

   /**
    * Retrieves a list of all products available in the system.
    *
    * @return List of ProductDto objects representing all products.
    */
   List<ProductDto> getAllProducts();
}
