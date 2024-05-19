package com.kcjcustomerbe.mapper;

import com.kcjcustomerbe.dto.CartDto;
import com.kcjcustomerbe.dto.CartProductDto;
import com.kcjcustomerbe.dto.CustomerDto;
import com.kcjcustomerbe.dto.ProductDto;
import com.kcjcustomerbe.entity.Cart;
import com.kcjcustomerbe.entity.CartProduct;
import com.kcjcustomerbe.entity.Customer;
import com.kcjcustomerbe.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

   @Autowired
   private ModelMapper mapper;

   // convert to DTO
   public ProductDto convertToProductDto(Product product) {
      return mapper.map(product, ProductDto.class);
   }

   public CartProductDto convertToCartProductDto(CartProduct cartProduct) {
      CartProductDto cartProductDto = mapper.map(cartProduct, CartProductDto.class);

      cartProductDto.setProductDto(convertToProductDto(cartProduct.getProduct()));

      return cartProductDto;
   }

   public CartDto convertToCartDto(Cart cart) {
      CartDto cartDto = mapper.map(cart, CartDto.class);

      cartDto.setCartProductsDto(convertToCartProductsDto(cart.getCartProducts()));

      return cartDto;
   }

   public CustomerDto convertToCustomerDto(Customer customer) {
      CustomerDto customerDto = mapper.map(customer, CustomerDto.class);

      customerDto.setCartDto(convertToCartDto(customer.getCart()));

      return customerDto;
   }

   public CustomerDto shortCustomerDto(Customer customer) {
//      "id"
//      "firstName"
//      "lastName"
      mapper.typeMap(Customer.class, CustomerDto.class)
              .addMappings(m -> m.skip(CustomerDto::setPassword))
              .addMappings(m -> m.skip(CustomerDto::setPhoneNumber))
              .addMappings(m -> m.skip(CustomerDto::setAddress))
              .addMappings(m -> m.skip(CustomerDto::setPostalCode))
              .addMappings(m -> m.skip(CustomerDto::setCreatedAt))
              .addMappings(m -> m.skip(CustomerDto::setIsBlocked))
              .addMappings(m -> m.skip(CustomerDto::setRole))
              .addMappings(m -> m.skip(CustomerDto::setCartDto))
              .addMappings(m -> m.skip(CustomerDto::setOrdersDto))
              .addMappings(m -> m.skip(CustomerDto::setReviewsDto));

      CustomerDto customerDto = mapper.map(customer, CustomerDto.class);

      return customerDto;
   }

   public CustomerDto customerInfoDelivery(Customer customer) {
//      "id"
//      "firstName"
//      "lastName"
//      "email"
//      "phoneNumber"
//      "address"
//      "postalCode"
//      "cart"
      mapper.typeMap(Customer.class, CustomerDto.class)
              .addMappings(m -> m.skip(CustomerDto::setPassword))
              .addMappings(m -> m.skip(CustomerDto::setCreatedAt))
              .addMappings(m -> m.skip(CustomerDto::setRole))
              .addMappings(m -> m.skip(CustomerDto::setIsBlocked))
              .addMappings(m -> m.skip(CustomerDto::setOrdersDto))
              .addMappings(m -> m.skip(CustomerDto::setReviewsDto));

      CustomerDto customerDto = mapper.map(customer, CustomerDto.class);
      customerDto.setCartDto(convertToCartDto(customer.getCart()));

      return customerDto;
   }

   public List<CartProductDto> convertToCartProductsDto(List<CartProduct> cartProducts) {
      return cartProducts.stream()
              .map(this::convertToCartProductDto)
              .collect(Collectors.toList());
   }

   public List<ProductDto> convertToProductsDto(List<Product> products) {
      return products.stream()
              .map(this::convertToProductDto)
              .collect(Collectors.toList());
   }

   // convert to entity
   public Product convertToProduct(ProductDto productDto) {
      return mapper.map(productDto, Product.class);
   }

   public CartProduct convertToCartProduct(CartProductDto cartProductDto) {
      CartProduct cartProduct = mapper.map(cartProductDto, CartProduct.class);

      cartProduct.setProduct(convertToProduct(cartProductDto.getProductDto()));

      return cartProduct;
   }

   public Cart convertToCart(CartDto cartDto) {
      Cart cart = mapper.map(cartDto, Cart.class);

      cart.setCartProducts(convertToCartProducts(cartDto.getCartProductsDto()));

      return mapper.map(cartDto, Cart.class);
   }

   public Customer convertToCustomer(CustomerDto customerDto) {
      return mapper.map(customerDto, Customer.class);
   }

   public List<Cart> convertToCart(List<CartDto> cartsDto) {
      return cartsDto.stream()
              .map(this::convertToCart)
              .collect(Collectors.toList());
   }

   public List<CartProduct> convertToCartProducts(List<CartProductDto> cartProductsDto) {
      return cartProductsDto.stream()
              .map(this::convertToCartProduct)
              .collect(Collectors.toList());
   }

   public List<Product> convertToProducts(List<ProductDto> productsDto) {
      return productsDto.stream()
              .map(this::convertToProduct)
              .collect(Collectors.toList());
   }
}