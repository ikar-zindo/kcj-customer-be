package com.kcjcustomerbe.controller;

import com.kcjcustomerbe.dto.ProductDto;
import com.kcjcustomerbe.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/product")
public class ProductRestController {

   private final MenuService menuService;

   public ProductRestController(MenuService menuService) {
      this.menuService = menuService;
   }

   @GetMapping
   public List<ProductDto> getAll() {
      return menuService.getAvailableProducts();
   }

   @GetMapping("/{id}")
   public ProductDto getProductById(@PathVariable(name = "id") Long id) {
      return menuService.getProductById(id);
   }
}
