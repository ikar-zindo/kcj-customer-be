package com.kcjcustomerbe.mapper.modelmapper;

import com.kcjcustomerbe.dto.ProductDto;
import com.kcjcustomerbe.dto.RestaurantDto;
import com.kcjcustomerbe.entity.Product;
import com.kcjcustomerbe.entity.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

   @Autowired
   private ModelMapper mapper;

   // convert to DTO
   public ProductDto convertToProductDto(Product product) {
      return mapper.map(product, ProductDto.class);
   }

   public RestaurantDto showRestaurantDetails(Restaurant restaurant) {
      return mapper.map(restaurant, RestaurantDto.class);
   }

   public ProductDto showProductDetails(Product product) {
      ProductDto productDto = mapper.map(product, ProductDto.class);

      productDto.setRestaurantDto(showRestaurantDetails(product.getRestaurant()));

      return productDto;
   }

   public List<ProductDto> convertToProductsDto(List<Product> products) {
      return products.stream()
              .map(this::showProductDetails)
              .collect(Collectors.toList());
   }

   // convert to entity
   public Product convertToProduct(ProductDto productDto) {
      return mapper.map(productDto, Product.class);
   }

   public List<Product> convertToProducts(List<ProductDto> productsDto) {
      return productsDto.stream()
              .map(this::convertToProduct)
              .collect(Collectors.toList());
   }
}
