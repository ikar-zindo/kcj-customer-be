package com.kcurryjibcustomer.service;

import com.kcurryjibcustomer.dto.ProductDto;
import com.kcurryjibcustomer.entity.Product;
import com.kcurryjibcustomer.exception.list.ProductException;
import com.kcurryjibcustomer.mapper.ProductMapper;
import com.kcurryjibcustomer.repo.ProductRepository;
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
                      ProductMapper productMapper) throws ProductException {

      this.productRepository = productRepository;
      this.productMapper = productMapper;
   }

   // READ
   public List<ProductDto> getAvailableProducts() throws ProductException {
      List<Product> products = new ArrayList<>(productRepository.findAll()).stream()
              .filter(Product::getAvailable)
              .sorted(Comparator.comparing(Product::getCreatedAt).reversed())
              .collect(Collectors.toList());
      // TODO .collect(Collectors.toList()); can be replaced with .toList()

      return productMapper.convertToProductsDto(products);
   }

   // READ
   public ProductDto getProductById(Long id) throws ProductException {
      ProductDto productDto = null;

      if (id != null) {
         Optional<Product> productOptional = productRepository.findById(id);

         if (productOptional.isPresent()) {
            productDto = productMapper.showProductDetails(productOptional.get());

         } else {
            throw new ProductException(
                    String.format("Product not found in database with id=%d",
                            id));
         }
      } else {
         throw new ProductException("There is no product ID to search for!");
      }

      return productDto;
   }
}
