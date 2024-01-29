package com.kcurryjibcustomer.controller;

import com.kcurryjibcustomer.dto.ProductDto;
import com.kcurryjibcustomer.dto.RestaurantDto;
import com.kcurryjibcustomer.exception.list.RestaurantException;
import com.kcurryjibcustomer.service.RestaurantService;
import jakarta.annotation.security.PermitAll;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/restaurant")
@SessionAttributes("restaurantsInfo")
@PermitAll
public class RestaurantController {

   private final RestaurantService service;

   public RestaurantController(RestaurantService service) {
      this.service = service;
   }

   @GetMapping("/all")
   public String getAllRestaurants(Model model) throws RestaurantException {
      List<RestaurantDto> restaurantsDto = service.getAll();

      model.addAttribute("restaurants", restaurantsDto);

      return "/restaurant/all";
   }


   @GetMapping("/{id}")
   public String getRestaurantByIdWithProducts(@PathVariable Long id,
                                   Model model) throws RestaurantException {

      if (service.getById(id) == null) {
         return "redirect:/restaurant/all";
      }

      RestaurantDto restaurantDto = service.getById(id);

      List<ProductDto> productsDto = restaurantDto.getProductsDto();

      productsDto.stream()
              .filter(ProductDto::isAvailable)
              .sorted(Comparator.comparing(ProductDto::getCreatedAt).reversed())
              .collect(Collectors.toList());

      model.addAttribute("restaurant", restaurantDto);
//      model.addAttribute("countComments", countComments);
//      model.addAttribute("avgRating", avgRating);
      model.addAttribute("products", productsDto);
//      model.addAttribute("reviews", reviewsDto);

      return "/restaurant/info";
   }
}
