package com.kcjcustomerbe.service.impl;

import com.kcjcustomerbe.dto.*;
import com.kcjcustomerbe.dto.customer.CustomerDto;
import com.kcjcustomerbe.entity.Cart;
import com.kcjcustomerbe.entity.CartProduct;
import com.kcjcustomerbe.entity.Order;
import com.kcjcustomerbe.entity.OrderProduct;
import com.kcjcustomerbe.entity.enums.OrderStatus;
import com.kcjcustomerbe.exception.ErrorMessage;
import com.kcjcustomerbe.exception.list.*;
import com.kcjcustomerbe.mapper.*;
import com.kcjcustomerbe.repo.CartProductRepository;
import com.kcjcustomerbe.repo.CartRepository;
import com.kcjcustomerbe.repo.OrderProductRepository;
import com.kcjcustomerbe.repo.OrderRepository;
import com.kcjcustomerbe.service.interfaces.CartService;
import com.kcjcustomerbe.service.interfaces.ProductService;
import com.kcjcustomerbe.service.interfaces.RestaurantService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

   private final CartRepository cartRepository;

   private final CartProductRepository cartProductRepository;

   private final OrderRepository orderRepository;

   private final OrderProductRepository orderProductRepository;

   private final CustomerMapper customerMapper;

   private final CartMapper cartMapper;

   public final ProductMapper productMapper;

   public final RestaurantMapper restaurantMapper;

   private final OrderMapper orderMapper;

   private final ProductService productService;

   private final RestaurantService restaurantService;

   // READ - CART BY CUSTOMER ID
   @Override
   public CartDto getCartById(UUID cartId) {

      if (cartId == null) {
         throw new CartNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
      }

      Optional<Cart> cartOptional = cartRepository.findById(cartId);
      if (cartOptional.isEmpty()) {
         throw new CartIdNotFoundException(cartId);
      }
      return cartMapper.mapToCartDtoWithCustomer(cartOptional.get());
   }

   // READ - CUSTOMER BY CART ID
   @Override
   public CustomerDto getCustomerByCartId(UUID cartId) {
      CustomerDto customerDto = getCartById(cartId).getCustomerDto();

      if (customerDto == null) {
         throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
      }
      return customerDto;
   }

   // GET - ALL PRODUCTS IN CART
   @Override
   public List<CartProductDto> getCartProductsByCartId(UUID cartId) {
      List<CartProductDto> cartProductsDto = getCartById(cartId).getCartProductsDto();

      if (cartProductsDto.isEmpty()) {
         throw new CartIsEmptyException(ErrorMessage.CART_IS_EMPTY);
      }
      return cartProductsDto;
   }

   // GET - TOTAL CART PRICE
   @Override
   public BigDecimal getTotalCartById(UUID cartId) {
      List<CartProductDto> cartProductsDto = getCartProductsByCartId(cartId);
      BigDecimal sum = BigDecimal.ZERO;

      if (cartId != null && !cartProductsDto.isEmpty()) {
         for (CartProductDto cartProductDto : cartProductsDto) {
            BigDecimal productPrice = cartProductDto.getProductDto().getPrice();
            int quantity = cartProductDto.getQuantity();

            BigDecimal productTotal = productPrice.multiply(BigDecimal.valueOf(quantity));
            sum = sum.add(productTotal);
         }
      }
      return sum;
   }

   // GET - CUSTOMER CART SIZE
   @Override
   public Integer getCartProductsSize(UUID cartId) {

      if (cartId == null) {
         throw new CartNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
      }

      List<CartProductDto> cartProductsDto = getCartProductsByCartId(cartId);
      return cartProductsDto.stream()
            .mapToInt(CartProductDto::getQuantity)
            .sum();
   }

   // CREATE - ADD PRODUCT TO CART
   @Override
   @Transactional
   public CartProductDto addProductToCart(UUID cartId, Long productId) {

      if (cartId == null && productId == null) {
         throw new IdNullException(ErrorMessage.NULL_ID);
      }

      CartDto cartDto = getCartById(cartId);
      ProductDto productDto = productService.getProductById(productId);

      CartProduct cartProduct =
            cartProductRepository.findByCartIdAndProductId(cartId, productId)
                  .orElseGet(() -> buildCartProduct(cartDto, productDto));

      if (cartProduct.getId() != null) {
         cartProduct.setQuantity(cartProduct.getQuantity() + 1);
      }

      CartProduct cartProductResponse = cartProductRepository.save(cartProduct);
      UUID idResponse = cartProductResponse.getId();

      if (idResponse == null) {
         throw new CartException(ErrorMessage.PRODUCT_WAS_NOT_ADDED_TO_YOUR_CART);
      }
      return cartMapper.mapToCartProductDto(cartProduct);
   }

   // BUILD - CART PRODUCT
   private CartProduct buildCartProduct(CartDto cartDto, ProductDto productDto) {
      CartProduct cartProduct = new CartProduct();

      cartProduct.setCart(cartMapper.mapToCart(cartDto));
      cartProduct.setProduct(productMapper.mapToProduct(productDto));
      cartProduct.setQuantity(1);
      cartProduct.setCreatedAt(LocalDateTime.now());

      return cartProduct;
   }

   // CREATE - ORDER
   @Override
   @Transactional
   public OrderDto createOrder(UUID cartId) {
      CartDto cartDto = getCartById(cartId);
      List<CartProductDto> cartProductsDto = cartDto.getCartProductsDto();
      CustomerDto customerDto = cartDto.getCustomerDto();

      if (cartProductsDto.isEmpty()) {
         throw new CartIsEmptyException(ErrorMessage.CART_IS_EMPTY);
      }

      Long restaurantId = validateSingleRestaurantInCart(cartDto);
      RestaurantDto restaurantDto = restaurantService.getRestaurantById(restaurantId);

      if (!restaurantDto.getIsOpen()) {
         throw new RestaurantException(ErrorMessage.RESTAURANTS_IS_CLOSE
               .formatted(restaurantDto.getName(), restaurantDto.getOpeningHours()));
      }

      if (!isPay(getCustomerByCartId(cartId).getId(), cartId)) {
         throw new OrderException(ErrorMessage.PAYMENT_NOT_THROUGH);
      }
      Order order = buildOrderFromCart(cartDto, customerDto, restaurantDto);

      Order orderResponse = orderRepository.save(order);
      UUID orderResponseId = orderResponse.getId();

      if (orderResponseId == null) {
         throw new OrderException(ErrorMessage.ORDER_NOT_SAVED);
      }

      List<OrderProduct> orderProducts = buildOrderProductsFromCartProducts(order, cartProductsDto);
      orderProductRepository.saveAll(orderProducts);

      clearCart(cartId); // TODO: не очищается корзина после оплаты
      return orderMapper.mapToOrderDto(orderResponse);
   }

   // BUILD - ORDER FROM CART DATA
   private Order buildOrderFromCart(CartDto cartDto, CustomerDto customerDto, RestaurantDto restaurantDto) {
      Order order = new Order();

      order.setCustomer(customerMapper.mapToCustomer(customerDto));
      order.setRestaurant(restaurantMapper.mapToRestaurant(restaurantDto));
      order.setCreatedAt(LocalDateTime.now());
      order.setDeliveryAddress(customerDto.getAddress());
      order.setPostalCode(customerDto.getPostalCode());
      order.setTotalAmount(getTotalCartById(cartDto.getId()));
      order.setOrderStatus(OrderStatus.CREATED);

      return order;
   }

   // BUILD - ORDER PRODUCT LIST FROM CART PRODUCT DATA
   private List<OrderProduct> buildOrderProductsFromCartProducts(Order order, List<CartProductDto> cartProductsDto) {
      List<OrderProduct> orderProducts = new ArrayList<>();

      for (CartProductDto cartProductDto : cartProductsDto) {
         OrderProduct orderProduct = new OrderProduct();
         orderProduct.setOrder(order);
         orderProduct.setQuantity(cartProductDto.getQuantity());

         ProductDto productDto = cartProductDto.getProductDto();
         if (productDto == null) {
            throw new ProductsNotFoundException(ErrorMessage.PRODUCTS_NOT_FOUND);
         }
         orderProduct.setProduct(productMapper.mapToProduct(productDto));
         orderProducts.add(orderProduct);
      }
      return orderProducts;
   }

   // VALIDATION - ALL PRODUCTS ARE FROM THE SAME RESTAURANT
   private Long validateSingleRestaurantInCart(CartDto cartDto) {

      if (cartDto == null || cartDto.getCartProductsDto() == null || cartDto.getCartProductsDto().isEmpty()) {
         throw new CartIsEmptyException(ErrorMessage.CART_IS_EMPTY);
      }

      Long restaurantId = null;
      for (CartProductDto cartProductDto : cartDto.getCartProductsDto()) {
         ProductDto productDto = cartProductDto.getProductDto();

         if (productDto == null || productDto.getRestaurantDto() == null) {
            continue;
         }
         if (restaurantId == null) {
            restaurantId = cartProductDto.getProductDto().getRestaurantDto().getId();
         } else if (!restaurantId.equals(productDto.getRestaurantDto().getId())) {
            throw new ProductsFromDifferentRestaurantsException(ErrorMessage.ORDER_DIFFERENT_RESTAURANT);
         }
      }

      if (restaurantId == null) {
         throw new RestaurantNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
      }
      return restaurantId;
   }

   // VALIDATION - PAY
   private Boolean isPay(UUID customerId, UUID cartId) {
      if (customerId == null || cartId == null) {
         throw new PaymentException(ErrorMessage.PAYMENT_NOT_THROUGH);
      }
      return true;
   }

   // DELETE - CLEAR CART
   @Override
   @Transactional
   public void clearCart(UUID cartId) {
      if (cartId == null) {
         throw new CartNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
      }
      cartProductRepository.deleteByCartId(cartId);
   }
}
