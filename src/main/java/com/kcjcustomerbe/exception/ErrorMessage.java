package com.kcjcustomerbe.exception;

public class ErrorMessage {

   // GLOBAL EXCEPTION MASSAGES
   public static final String NULL_ID = "ID cannot be NULL";
   public static final String ID_NOT_FOUND = "Passed ID not found=";
   public static final String INVALID_ID = "It is not UUID format";
   public static final String INVALID_PHONE_NUMBER = "Incorrect number format";

   // CUSTOMER EXCEPTION MASSAGES
   public static final String CUSTOMER_NOT_FOUND = "The customer was not found!!";
   public static final String CUSTOMER_ID_NOT_FOUND = "The customer was not found with ID=";
   public static final String CUSTOMER_IS_NULL = "Customer not passed to method";

   // CART EXCEPTION MASSAGES
   public static final String CART_NOT_FOUND = "The customer was not found!!";
   public static final String CART_EXCEPTION = "Unable to add item to cart";
   public static final String CART_IS_EMPTY = "Cart is EMPTY! First add products to cart";
   public static final String CART_ID_NOT_FOUND = "Cart not found with ID=";
   public static final String CUSTOMER_CART_NOT_FOUND = "Customer not found in database with cart ID=";
   public static final String PRODUCTS_CANNOT_BE_ADD_TO_CART =
           "Cart contains products from different restaurants. Place an order from 1 restaurant";

   // PRODUCTS EXCEPTION MASSAGES
   public static final String PRODUCT_NOT_FOUND = "Products not found";
   public static final String PRODUCTS_NOT_FOUND = "Products not found";
   public static final String PRODUCT_ID_NOT_FOUND = "Products not found in database with ID=";

   // ORDER EXCEPTION MASSAGES
   public static final String ORDER_NOT_SAVED = "The order was not saved to the database";

   // RESTAURANT EXCEPTION MASSAGES
   public static final String RESTAURANT_NOT_FOUND = "Restaurant not found in database";
   public static final String RESTAURANT_ID_NOT_FOUND = "Restaurant not found in database with id=";
   public static final String RESTAURANTS_LIST_IS_EMPTY = "Restaurants list is empty";
   public static final String RESTAURANTS_IS_CLOSE = "Sorry, We are closed, try during opening hours.%n«%s» - %s";

   // PAYMENT EXCEPTION MASSAGES
   public static final String PAYMENT_NOT_THROUGH = "Payment for the order did not go through";
}
