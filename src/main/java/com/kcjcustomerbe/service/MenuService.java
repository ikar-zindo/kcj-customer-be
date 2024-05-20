package com.kcjcustomerbe.service;

import com.kcjcustomerbe.dto.ProductDto;
import com.kcjcustomerbe.entity.Product;
import com.kcjcustomerbe.exception.ErrorMessage;
import com.kcjcustomerbe.exception.list.ProductNotFoundException;
import com.kcjcustomerbe.mapper.ProductMapper;
import com.kcjcustomerbe.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {

   private final ProductRepository productRepository;

   private final ProductMapper productMapper;

   @Autowired
   public MenuService(ProductRepository productRepository,
                      ProductMapper productMapper) throws ProductNotFoundException {

      this.productRepository = productRepository;
      this.productMapper = productMapper;
   }

   // READ
   public List<ProductDto> getAvailableProducts() throws ProductNotFoundException {
      List<Product> products = productRepository.findAll();

      if (!products.isEmpty()) {
         List<Product> availableProducts = new ArrayList<>(products).stream()
                 .filter(Product::getIsAvailable)
                 .sorted(Comparator.comparing(Product::getCreatedAt).reversed())
                 .collect(Collectors.toList());

         return productMapper.convertToProductsDto(availableProducts);

      } else {
         throw new ProductNotFoundException(ErrorMessage.PRODUCTS_NOT_FOUND);
      }
   }

   // READ
   public ProductDto getProductById(Long id) throws ProductNotFoundException {
      ProductDto productDto = null;

      if (id != null) {
         Optional<Product> productOptional = productRepository.findById(id);

         if (productOptional.isPresent()) {
            productDto = productMapper.showProductDetails(productOptional.get());

         } else {
            throw new ProductNotFoundException(ErrorMessage.PRODUCT_ID_NOT_FOUND + id);
         }
      } else {
         throw new ProductNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
      }

      return productDto;
   }
}
