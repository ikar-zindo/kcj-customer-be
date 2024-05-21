package com.kcjcustomerbe.service.old;

import com.kcjcustomerbe.dto.*;
import com.kcjcustomerbe.entity.*;
import com.kcjcustomerbe.entity.enums.OrderStatus;
import com.kcjcustomerbe.exception.ErrorMessage;
import com.kcjcustomerbe.exception.list.*;
import com.kcjcustomerbe.mapper.modelmapper.CartMapper;
import com.kcjcustomerbe.mapper.modelmapper.CustomerMapper;
import com.kcjcustomerbe.mapper.modelmapper.OrderMapper;
import com.kcjcustomerbe.repo.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartServiceOld {

   private final CartMapper cartMapper;

   private final OrderMapper orderMapper;

   private final CustomerMapper customerMapper;

   private final CustomerRepository customerRepository;

   private final CartRepository cartRepository;

   private final CartProductRepository cartProductRepository;

   private final ProductRepository productRepository;

   private final RestaurantRepository restaurantRepository;

   private final OrderRepository orderRepository;

   private final OrderProductRepository orderProductRepository;

   private final MenuServiceOld menuServiceOld;

   private final CustomerServiceOld customerServiceOld;

   @Autowired
   public CartServiceOld(CartMapper cartMapper,
                         OrderMapper orderMapper,
                         CustomerMapper customerMapper,
                         CustomerRepository customerRepository,
                         CartRepository cartRepository,
                         CartProductRepository cartProductRepository,
                         ProductRepository productRepository,
                         RestaurantRepository restaurantRepository,
                         OrderRepository orderRepository,
                         OrderProductRepository orderProductRepository,
                         MenuServiceOld menuServiceOld,
                         CustomerServiceOld customerServiceOld) {

      this.cartMapper = cartMapper;
      this.customerMapper = customerMapper;
      this.orderMapper = orderMapper;

      this.customerRepository = customerRepository;
      this.cartRepository = cartRepository;
      this.cartProductRepository = cartProductRepository;
      this.productRepository = productRepository;
      this.restaurantRepository = restaurantRepository;
      this.orderRepository = orderRepository;
      this.orderProductRepository = orderProductRepository;

      this.menuServiceOld = menuServiceOld;
      this.customerServiceOld = customerServiceOld;
   }

   // READ - CUSTOMER
   public CustomerDto getCustomerById(UUID customerId) {
      CustomerDto customerDto = null;

      if (customerId != null) {
         Optional<Customer> customerOptional = customerRepository.findById(customerId);

         if (customerOptional.isPresent()) {
            customerDto = cartMapper.convertToCustomerDto(customerOptional.get());

         } else {
            throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_ID_NOT_FOUND + customerId);
         }
      } else {
         throw new IdNullException(ErrorMessage.NULL_ID);
      }

      return customerDto;
   }

   // READ - CUSTOMER
   public CustomerDto getCustomerByCartId(UUID cartId) {
      CustomerDto customerDto = null;

      if (cartId != null) {
         Optional<Cart> cartOptional = cartRepository.findById(cartId);

         if (cartOptional.isPresent()) {
            UUID cartOptionalId = cartOptional.get().getId();
            Optional<Customer> customerOptional = customerRepository.findCustomerByCartId(cartOptionalId);

            if (customerOptional.isPresent() && cartOptionalId != null) {
               customerDto = cartMapper.convertToCustomerDto(customerOptional.get());

            } else {
               throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_CART_NOT_FOUND + cartId);
            }
         } else {
            throw new CartNotFoundException(ErrorMessage.CART_ID_NOT_FOUND + cartId);
         }
      } else {
         throw new IdNotFoundException(ErrorMessage.NULL_ID);
      }

      return customerDto;
   }

   // DELETE - CLEAR CART
   @Transactional
   public void clearCart(UUID cartId) {
      if (cartId != null) {
         cartProductRepository.deleteByCartId(cartId);
      } else {
         throw new IdNullException(ErrorMessage.NULL_ID);
      }
   }

   // CREATE - ADD PRODUCT TO CART
   public CartProductDto addProductToCustomerCart(UUID cartId, Long productId) {

      if (cartId != null && productId != null) {
         CustomerDto customerDto = customerServiceOld.getCustomerByCartId(cartId);
         ProductDto productDto = menuServiceOld.getProductById(productId);

         if (productDto != null && productDto.getId() != null) {
            RestaurantDto restaurantDto = productDto.getRestaurantDto();

            if (restaurantDto != null) {

               Customer customer = customerRepository.findById(customerDto.getId()).orElse(null);
               Product product = productRepository.findById(productDto.getId()).orElse(null);

               if (customer != null) {
                  if (product != null) {
                     Optional<CartProduct> existingCartProductOptional = cartProductRepository
                             .findByCartIdAndProductId(customer.getCart().getId(), product.getId());

                     if (existingCartProductOptional.isPresent()) {
                        CartProduct existingCartProduct = existingCartProductOptional.get();

                        existingCartProduct.setQuantity(existingCartProduct.getQuantity() + 1);

                        cartProductRepository.save(existingCartProduct);
                        return cartMapper.convertToCartProductDto(existingCartProduct);

                     } else {
                        CartProduct cartProduct = new CartProduct();

                        cartProduct.setCart(customer.getCart());
                        cartProduct.setProduct(product);
                        cartProduct.setCratedAt(LocalDateTime.now());
                        cartProduct.setQuantity(1);

                        CartProduct cartProductResponse = cartProductRepository.save(cartProduct);
                        UUID idResponse = cartProductResponse.getId();

                        if (idResponse != null) { // TODO: реализовать проверку UUID
                           return cartMapper.convertToCartProductDto(cartProductResponse);

                        } else {
                           throw new CartException(ErrorMessage.CART_EXCEPTION);
                        }
                     }
                  } else {
                     throw new ProductNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
                  }
               } else {
                  throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
               }
            } else {
               throw new RestaurantNotFoundException(ErrorMessage.RESTAURANT_ID_NOT_FOUND + productDto.getRestaurantDto().getId());
            }
         } else {
            throw new ProductNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
         }
      } else {
         throw new IdNullException(ErrorMessage.NULL_ID);
      }
   }

   // GET ALL PRODUCTS IN CUSTOMER CART
   public List<CartProductDto> getCartProductsByCartId(UUID cartId) {
      CustomerDto customerDto = getCustomerByCartId(cartId);
      return customerDto.getCartDto().getCartProductsDto();
   }


   // CUSTOMER CART SIZE
   public int getCartProductsSize(UUID cartId) {

      if (cartId != null) {
         List<CartProductDto> cartProductsDto = getCartProductsByCartId(cartId);

         return cartProductsDto.stream()
                 .mapToInt(CartProductDto::getQuantity)
                 .sum();
      } else {
         throw new IdNullException(ErrorMessage.NULL_ID);
      }
   }

   // TOTAL CART PRICE
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

   // CREATE NEW ORDER
   @Transactional
   public OrderDto createOrder(CustomerDto customerDto) {

      if (customerDto.getId() != null) {
         Optional<Customer> customerOptional = customerRepository.findById(customerDto.getId());

         if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Optional<Cart> cartOptional = cartRepository.findById(customer.getCart().getId());

            if (cartOptional.isPresent()) {
               Cart cart = cartOptional.get();
               List<CartProduct> cartProducts = cart.getCartProducts();

               if (!cartProducts.isEmpty()) {

                  Long restaurantId = null; // check products in from 1 restaurant
                  for (CartProduct cartProduct : cartProducts) {
                     if (restaurantId == null) {
                        restaurantId = cartProduct.getProduct().getRestaurant().getId();

                     } else if (!restaurantId.equals(cartProduct.getProduct().getRestaurant().getId())) {

                        throw new DifferentRestaurantException(ErrorMessage.PRODUCTS_CANNOT_BE_ADD_TO_CART);
                     }
                  }

                  if (restaurantId != null) {
                     Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

                     if (restaurantOptional.isPresent()) {
                        Restaurant restaurant = restaurantOptional.get();

                        if (restaurant.getIsOpen()) { // check is open restaurant

                           if (isPay(customer.getId(), cart.getId())) {
                              Order order = new Order();

                              order.setCustomer(customer);
                              order.setRestaurant(restaurant);
                              order.setCreatedAt(LocalDateTime.now());
                              order.setDeliveryAddress(customerMapper.convertToCustomer(customerDto).getAddress());
                              order.setPostalCode(customerMapper.convertToCustomer(customerDto).getPostalCode());
                              order.setTotalAmount(getTotalCartById(cart.getId()));
                              order.setOrderStatus(OrderStatus.CREATED);

                              Order orderResponse = orderRepository.save(order); // ====================================
                              UUID orderResponseId = orderResponse.getId();

                              if (orderResponse != null && !orderResponseId.toString().isEmpty()) { // TODO: реализовать проверку UUID
                                 // CREATE LIST ORDER PRODUCTS
                                 List<OrderProduct> orderProducts = new ArrayList<>();

                                 for (CartProduct cartProduct : cartProducts) {
                                    OrderProduct orderProduct = new OrderProduct();

                                    orderProduct.setOrder(orderResponse);
                                    orderProduct.setQuantity(cartProduct.getQuantity());

                                    if (cartProduct.getProduct() != null) {
                                       orderProduct.setProduct(cartProduct.getProduct());
                                    } else {
                                       throw new ProductNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
                                    }

                                    orderProducts.add(orderProduct);
                                 }

                                 clearCart(cart.getId());
                                 orderProductRepository.saveAll(orderProducts); // =====================================
                                 return orderMapper.cnovertToOrderDto(orderResponse);

                              } else {
                                 throw new OrderException(ErrorMessage.ORDER_NOT_SAVED);
                              }
                           } else {
                              throw new PaymentException(ErrorMessage.PAYMENT_NOT_THROUGH);
                           }
                        } else {
                           throw new RestaurantNotFoundException( // check is open restaurant
                                   String.format(ErrorMessage.RESTAURANTS_IS_CLOSE,
                                           restaurant.getName(), restaurant.getOpeningHours()));
                        }
                     } else {
                        throw new RestaurantNotFoundException(ErrorMessage.RESTAURANT_ID_NOT_FOUND + restaurantId);
                     }
                  } else {
                     throw new RestaurantNotFoundException(ErrorMessage.RESTAURANT_NOT_FOUND);
                  }
               } else {
                  throw new CartNotFoundException(ErrorMessage.CART_IS_EMPTY);
               }
            } else {
               throw new CartNotFoundException(ErrorMessage.CART_ID_NOT_FOUND + customer.getId());
            }
         } else {
            throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_ID_NOT_FOUND + customerDto.getId());
         }
      } else {
         throw new CustomerNotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND);
      }
   }

   // PAY VALIDATION
   public Boolean isPay(UUID customerId, UUID cartId) {
      return true;
   }
}
