package com.kcurryjibcustomer.controller;

import com.kcurryjibcustomer.dto.CartProductDto;
import com.kcurryjibcustomer.dto.CustomerDto;
import com.kcurryjibcustomer.entity.Customer;
import com.kcurryjibcustomer.exception.list.CartException;
import com.kcurryjibcustomer.exception.list.OrderException;
import com.kcurryjibcustomer.service.CartService;
import com.kcurryjibcustomer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/cart")
@SessionAttributes("editCart")
@PreAuthorize("hasRole('ROLE_CUSTOMER')")
public class CartController {

   private final CustomerService customerService;

   private final CartService cartService;

   // TODO why don't you use lombok and its reargsconstrator autowiring? It reduces code
   @Autowired
   public CartController(CustomerService customerService,
                         CartService cartService) {

      this.customerService = customerService;
      this.cartService = cartService;
   }

   // READ - CUSTOMER
   @GetMapping
   public String getCustomerCart(Model model) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String currentPrincipalName = authentication.getName();

      Customer customer = (Customer) customerService.loadUserByUsername(currentPrincipalName);
      CustomerDto customerDto = customerService.getCustomerByCartId(customer.getCart().getId());

      List<CartProductDto> cartProductsDto = customerDto.getCartDto().getCartProductsDto();

      // CART INFO
      Long cartId = customerDto.getCartDto().getId();
      int cartSize = cartService.getCartProductsSize(cartId);
      BigDecimal total = cartService.getTotalCartById(cartId);

      model.addAttribute("total", total);
      model.addAttribute("cartSize", cartSize);
      model.addAttribute("customer", customerDto);
      model.addAttribute("cartProducts", cartProductsDto);

      return "cart/cart";
   }

   // CREATE - ADD PRODUCT TO CART
   @PutMapping("/{restaurantId}/{productId}/add")
   public String addProductToCart(@PathVariable Long productId,
                                  @PathVariable Long restaurantId) throws CartException {

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String currentPrincipalName = authentication.getName();

      // TODO redundant type cast. You can reurn Customer type by loadUserByUsername method
      Customer customer = (Customer) customerService.loadUserByUsername(currentPrincipalName);

      CustomerDto customerDto = cartService.getCustomerById(customer.getId());

      cartService.addProductToCustomerCart(customerDto.getCartDto().getId(), productId);
      return "redirect:/restaurant/" + restaurantId;
   }

   // TODO good text formating style, cool! - to make a blanc line between signature and body. Just to let you know
   // CREATE NEW ORDER
   @PostMapping("/payCart")
   public String payCart(@ModelAttribute("customer") CustomerDto customerDtoForDelivery,
                         BindingResult result,
                         Model model) throws OrderException {

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String currentPrincipalName = authentication.getName();

      // TODO the same as in previous method - redundant type cast
      Customer customer = (Customer) customerService.loadUserByUsername(currentPrincipalName);

      CustomerDto customerDto = cartService.getCustomerById(customer.getId());

      Long cartId = customerDto.getCartDto().getId();
      int cartSize = cartService.getCartProductsSize(cartId);
      BigDecimal total = cartService.getTotalCartById(cartId);

      if (result.hasErrors()){
         model.addAttribute("customer", customerDto);
         model.addAttribute("cartSize", cartSize);
         model.addAttribute("total", total);

         return "cart/cart";
      }

      cartService.createOrder(customerDto);
      cartService.clearCart(cartId);
      return "redirect:/cart";
   }

   // DELETE - CLEAR CART
   @DeleteMapping("/{cartId}/clear")
   public String clearCart(@PathVariable Long cartId) {
      cartService.clearCart(cartId);
      return "redirect:/cart";
   }
}
