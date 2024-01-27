package com.kcurryjibcustomer.controller;

import com.kcurryjibcustomer.dto.CustomerDto;
import com.kcurryjibcustomer.entity.Customer;
import com.kcurryjibcustomer.exception.list.CartException;
import com.kcurryjibcustomer.repo.ProductRepository;
import com.kcurryjibcustomer.service.CartService;
import com.kcurryjibcustomer.service.CustomerService;
import com.kcurryjibcustomer.service.MenuService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/cart")
@SessionAttributes("editCart")
@PreAuthorize("hasRole('ROLE_CUSTOMER')")
public class CartController {

   private final CustomerService customerService;

   private final CartService cartService;

   private final MenuService menuService;

   @Autowired
   public CartController(CustomerService customerService,
                         CartService cartService,
                         MenuService menuService) {

      this.customerService = customerService;
      this.cartService = cartService;
      this.menuService = menuService;
   }

   @PutMapping("{restaurantId}/{productId}/add")
   public String addProductToCart(@PathVariable Long productId,
                                  @PathVariable Long restaurantId) throws CartException {

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String currentPrincipalName = authentication.getName();

      Customer customer = (Customer) customerService.loadUserByUsername(currentPrincipalName);

      CustomerDto customerDto = cartService.getCustomerById(customer.getId());
//      Long restaurantId = menuService.getProductById(productId).getRestaurantDto().getId();

      cartService.addProductToCustomerCart(customerDto.getId(), productId);
      return "redirect:/restaurant/" + restaurantId;
   }
}
