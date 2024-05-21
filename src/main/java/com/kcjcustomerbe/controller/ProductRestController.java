package com.kcjcustomerbe.controller;

import com.kcjcustomerbe.dto.ProductDto;
import com.kcjcustomerbe.service.old.MenuServiceOld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/product")
public class ProductRestController {

   private final MenuServiceOld menuServiceOld;

   public ProductRestController(MenuServiceOld menuServiceOld) {
      this.menuServiceOld = menuServiceOld;
   }

   @GetMapping
   public List<ProductDto> getAll() {
      return menuServiceOld.getAvailableProducts();
   }

   @GetMapping("/{id}")
   public ProductDto getProductById(@PathVariable(name = "id") Long id) {
      return menuServiceOld.getProductById(id);
   }
}
