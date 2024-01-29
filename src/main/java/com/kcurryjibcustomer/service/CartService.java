package com.kcurryjibcustomer.service;

import com.kcurryjibcustomer.dto.*;
import com.kcurryjibcustomer.entity.*;
import com.kcurryjibcustomer.entity.enums.OrderStatus;
import com.kcurryjibcustomer.exception.list.*;
import com.kcurryjibcustomer.mapper.CartMapper;
import com.kcurryjibcustomer.mapper.CustomerMapper;
import com.kcurryjibcustomer.mapper.OrderMapper;
import com.kcurryjibcustomer.repo.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

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

   private final MenuService menuService;

   private final CustomerService customerService;

   @Autowired
   public CartService(CartMapper cartMapper,
                      OrderMapper orderMapper,
                      CustomerMapper customerMapper,
                      CustomerRepository customerRepository,
                      CartRepository cartRepository,
                      CartProductRepository cartProductRepository,
                      ProductRepository productRepository,
                      RestaurantRepository restaurantRepository,
                      OrderRepository orderRepository,
                      OrderProductRepository orderProductRepository,
                      MenuService menuService,
                      CustomerService customerService) {

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

      this.menuService = menuService;
      this.customerService = customerService;
   }

   // READ - CUSTOMER
   public CustomerDto getCustomerById(Long customerId) throws CustomerException {
      CustomerDto customerDto = null;

      if (customerId != null) {
         Optional<Customer> customerOptional = customerRepository.findById(customerId);

         if (customerOptional.isPresent()) {
            customerDto = cartMapper.convertToCustomerDto(customerOptional.get());

         } else {
            throw new CustomerException(
                    String.format("Customer not found in database with id=%d",
                            customerId));
         }

      } else {
         throw new CustomerException("There is no customer ID to search for!");
      }

      return customerDto;
   }

   // READ - CUSTOMER
   public CustomerDto getCustomerByCartId(Long cartId) throws CustomerException {

      CustomerDto customerDto = null;

      if (cartId != null) {
         Optional<Cart> cartOptional = cartRepository.findById(cartId);
         Long cartOptionalId = cartOptional.get().getId();
         Optional<Customer> customerOptional = customerRepository.findCustomerByCartId(cartOptionalId);

         if (customerOptional.isPresent() && cartOptionalId != null) {
            customerDto = cartMapper.convertToCustomerDto(customerOptional.get());

         } else {
            throw new CustomerException(
                    String.format("Customer not found in database with cart id=%d",
                            cartId));
         }

      } else {
         throw new CustomerException("There is no customer ID to search for!");
      }

      return customerDto;
   }

   // DELETE - CLEAR CART
   public void clearCart(Long cartId) {
      if (cartId != null) {
         cartProductRepository.deleteByCartId(cartId);
      } else {
         throw new CartException("Cart not passed to method");
      }
   }

   // CREATE - ADD PRODUCT TO CART
   public CartProductDto addProductToCustomerCart(Long cartId, Long productId) {

      if (cartId != null && productId != null) {
         CustomerDto customerDto = customerService.getCustomerByCartId(cartId);
         ProductDto productDto = menuService.getProductById(productId);

         if (productDto != null && productDto.getId() != null) {
         RestaurantDto restaurantDto = productDto.getRestaurantDto();

               if (restaurantDto != null) {

                  Customer customer = customerRepository.findById(customerDto.getId()).orElse(null);
                  Product product = productRepository.findById(productDto.getId()).orElse(null);

                  if (customer != null && product != null) {
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
                        Long idResponse = cartProductResponse.getId();

                        if (idResponse != null && idResponse > 0) {
                           return cartMapper.convertToCartProductDto(cartProductResponse);

                        } else {
                           throw new CartException("Unable to add item to cart");
                        }
                     }
                  } else {
                     throw new CartException("Customer or product not found");
                  }
               } else {
                  throw new RestaurantException(
                          String.format("Restaurant not found in the database with Id=%d!", +
                                  productDto.getRestaurantDto().getId()));
               }
         } else {
               throw new CartException("Product not found");
         }
      } else {
         throw new CartException("Cart ID or Product ID not provided");
      }
   }

   // GET ALL PRODUCTS IN CUSTOMER CART
   public List<CartProductDto> getCartProductsByCartId(Long cartId) {
      CustomerDto customerDto = getCustomerByCartId(cartId);
      return customerDto.getCartDto().getCartProductsDto();
   }


   // CUSTOMER CART SIZE
   public int getCartProductsSize(Long cartId) {

      if (cartId != null) {
         List<CartProductDto> cartProductsDto = getCartProductsByCartId(cartId);

         return cartProductsDto.stream()
                 .mapToInt(CartProductDto::getQuantity)
                 .sum();
      } else {
         throw new CartException("Cart not found!");
      }
   }

   // TOTAL CART PRICE
   public BigDecimal getTotalCartById(Long cartId) {
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

                        throw new CartException("Cart contains products from different restaurants. " +
                                "Place an order from 1 restaurant");
                     }
                  }

                  if (restaurantId != null) {
                     Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

                     if (restaurantOptional.isPresent()) {
                        Restaurant restaurant = restaurantOptional.get();

                        if (restaurant.getOpen()) { // check is open restaurant

                           if (isPay(customer.getId(), cart.getId())) {
                              Order order = new Order();

                              order.setCustomer(customerOptional.get());
                              order.setRestaurant(restaurant);
                              order.setCreatedAt(LocalDateTime.now());
                              order.setDeliveryAddress(customerMapper.convertToCustomer(customerDto).getAddress());
                              order.setPostalCode(customerMapper.convertToCustomer(customerDto).getPostalCode());
                              order.setTotalAmount(getTotalCartById(cart.getId()));
                              order.setOrderStatus(OrderStatus.CREATED);

                              Order orderResponse = orderRepository.save(order); // ====================================
                              Long orderResponseId = orderResponse.getId();

                              if (orderResponse != null && orderResponseId > 0) {
                                 // CREATE LIST ORDER PRODUCTS
                                 List<OrderProduct> orderProducts = new ArrayList<>();

                                 for (CartProduct cartProduct : cartProducts) {
                                    OrderProduct orderProduct = new OrderProduct();

                                    orderProduct.setOrder(orderResponse);
                                    orderProduct.setQuantity(cartProduct.getQuantity());

                                    if (cartProduct.getProduct() != null) {
                                       orderProduct.setProduct(cartProduct.getProduct());
                                    } else {
                                       throw new ProductException("Product not found to add to cart");
                                    }

                                    orderProducts.add(orderProduct);
                                 }

                                 clearCart(cart.getId());
                                 orderProductRepository.saveAll(orderProducts); // =====================================
                                 return orderMapper.cnovertToOrderDto(orderResponse);

                              } else {
                                 throw new OrderException("The order was not saved to the database");
                              }
                           } else {
                              throw new OrderException("Payment for the order did not go through");
                           }
                        } else {
                           throw new RestaurantException( // check is open restaurant
                                   String.format("Sorry, We are closed, try during opening hours.%n«%s» - %s",
                                           restaurant.getName(), restaurant.getOpeningHours()));
                        }
                     } else {
                        throw new RestaurantException(
                                String.format("Restaurant not found with ID=%d",
                                        restaurantId));
                     }
                  } else {
                     throw new RestaurantException("Restaurant not FOUND!");
                  }
               } else {
                  throw new CartException("Cart is EMPTY! First add products to cart");
               }
            } else {
               throw new CartException(
                       String.format("Cart is missing for the client with ID=%d",
                               customer.getId()));
            }
         } else {
            throw new CartException("The customer was not found!!");
         }
      } else {
         throw new CustomerException("Customer not passed to method");
      }
   }

   // PAY VALIDATION
   public Boolean isPay(Long customerId, Long cartId) {
      return true;
   }
}
