package com.kcj_customer_be.service.impl;

import com.kcj_customer_be.dto.ProductDto;
import com.kcj_customer_be.entity.Product;
import com.kcj_customer_be.exception.ErrorMessage;
import com.kcj_customer_be.exception.list.ProductIdNotFoundException;
import com.kcj_customer_be.exception.list.ProductsNotFoundException;
import com.kcj_customer_be.mapper.ProductMapper;
import com.kcj_customer_be.repo.ProductRepository;
import com.kcj_customer_be.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

   private final ProductRepository productRepository;

   private final ProductMapper productMapper;

   @Override
   public ProductDto getProductById(Long productId) {
      Optional<Product> productOptional = productRepository.findByIdAndIsAvailableTrue(productId);
      if (productOptional.isEmpty()) {
         throw new ProductIdNotFoundException(productId);
      }

      Product product = productOptional.get();
      return productMapper.mapToProductDto(product);
   }

   @Override
   public List<ProductDto> getAllProducts() {
      Optional<List<Product>> optionalProducts = productRepository.findAllByIsAvailableTrue();
      if (optionalProducts.isEmpty()) {
         throw new ProductsNotFoundException(ErrorMessage.PRODUCTS_NOT_FOUND);
      }

      List<Product> products = optionalProducts.get();
      if (products.isEmpty()) {
         throw new ProductsNotFoundException(ErrorMessage.PRODUCTS_NOT_FOUND);
      }
      return productMapper.mapToProductsDto(products);
   }
}
