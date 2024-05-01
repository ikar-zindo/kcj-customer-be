package com.kcurryjibcustomer.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// TODO maybe should be moved to exception folder? controller folder looks strange for it
@ControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(Exception.class)
   public String handleException(Exception e, Model model) {
      model.addAttribute("error", e.getMessage());
      return "error";
   }
}
