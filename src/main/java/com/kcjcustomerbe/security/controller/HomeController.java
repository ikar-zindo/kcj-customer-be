package com.kcjcustomerbe.security.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

   @Value("${rest.api.url}")
   private String restApiUrl;

   @GetMapping("/")
   public String home(Model model) {
      model.addAttribute("restApiUrl", restApiUrl);
      return "index";
   }
}
