package com.kcjcustomerbe.service.impl;

import com.kcjcustomerbe.dto.ProductDto;
import com.kcjcustomerbe.entity.Product;
import com.kcjcustomerbe.exception.ErrorMessage;
import com.kcjcustomerbe.exception.list.ProductIdNotFoundException;
import com.kcjcustomerbe.exception.list.ProductNotAvailableException;
import com.kcjcustomerbe.exception.list.ProductsNotFoundException;
import com.kcjcustomerbe.mapper.ProductMapper;
import com.kcjcustomerbe.repo.ProductRepository;
import com.kcjcustomerbe.service.ProductService;
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
      if (!products.isEmpty()) {
         return productMapper.mapToProductsDto(products);

      } else {
         throw new ProductsNotFoundException(ErrorMessage.PRODUCTS_NOT_FOUND);
      }
   }
}
