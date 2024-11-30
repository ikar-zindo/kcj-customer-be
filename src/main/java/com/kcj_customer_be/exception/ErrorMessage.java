package com.kcj_customer_be.exception;

public class ErrorMessage {

   // GLOBAL EXCEPTION MASSAGES
   public static final String NULL_POINTER = "NULL_POINTER";
   public static final String NULL_ID = "ID_CANNOT_BE_NULL";
   public static final String ID_NOT_FOUND = "PASSED_ID_NOT_FOUND=";
   public static final String INVALID_ID = "ITS_NOT_UUID_FORMAT=";
   public static final String INVALID_PHONE_NUMBER = "INCORRECT_NUMBER_FORMAT";

   // CUSTOMER EXCEPTION MASSAGES
   public static final String CUSTOMER_NOT_FOUND = "CUSTOMER_NOT_FOUND";
   public static final String CUSTOMER_ID_NOT_FOUND = "CUSTOMER_WAS_NOT_FOUND_WITH_ID=";
   public static final String CUSTOMER_NOT_FOUND_WITH_EMAIL = "CUSTOMER_WAS_NOT_FOUND_WITH_EMAIL=";
   public static final String CUSTOMER_IS_NULL = "CUSTOMER_NOT_PASSED_IN_METHOD";
   public static final String EMAIL_ALREADY_EXISTS = "CUSTOMER_WITH_THE_SAME_EMAIL_ALREADY_EXISTS";
   public static final String USERNAME_ALREADY_EXISTS = "CUSTOMER_WITH_THE_SAME_USERNAME_ALREADY_EXISTS";
   public static final String INVALID_FIRSTNAME = "INVALID_FIRSTNAME";
   public static final String INVALID_LASTNAME = "INVALID_FIRSTNAME";
   public static final String INVALID_USERNAME = "INVALID_USERNAME";
   public static final String INVALID_PASSWORD = "INVALID_PASSWORD";
   public static final String INVALID_EMAIL = "INVALID_EMAIL";
   public static final String INVALID_ADDRESS = "INVALID_ADDRESS";
   public static final String INVALID_POSTAL_CODE = "INVALID_POSTAL_CODE";
   public static final String INVALID_CUSTOMER_UPDATE_DTO = "CUSTOMER_UPDATE_DTO_CANNOT_BE_NULL";
   public static final String INVALID_CUSTOMER_ID = "CUSTOMER_UPDATE_ID_CANNOT_BE_NULL";

   // CART EXCEPTION MASSAGES
   public static final String CART_NOT_FOUND = "CART_NOT_FOUND";
   public static final String CART_EXCEPTION = "UNABLE_TO_ADD_ITEM_TO_CART";
   public static final String CART_IS_EMPTY = "CART_IS_EMPTY";
   public static final String CART_ID_NOT_FOUND = "CART_WAS_NOT_FOUND_WITH_ID=";
   public static final String CUSTOMER_CART_NOT_FOUND = "CUSTOMER_WAS_NOT_FOUND_IN_BD_WITH_CART_ID=";
   public static final String PRODUCTS_CANNOT_BE_ADD_TO_CART =
           "CART CONTAINS PRODUCTS FROM DIFFERENT RESTAURANTS. PLACE AN ORDER FROM 1 RESTAURANT.";
   public static final String PRODUCT_WAS_NOT_ADDED_TO_YOUR_CART = "PRODUCT_WAS_NOT_ADDED_TO_YOUR_CART=";

   // PRODUCTS EXCEPTION MASSAGES
   public static final String PRODUCT_NOT_FOUND = "PRODUCT_NOT_FOUND";
   public static final String PRODUCTS_NOT_FOUND = "PRODUCTS_NOT_FOUND";
   public static final String PRODUCT_ID_NOT_FOUND = "PRODUCT_NOT_FOUND_WITH_ID=";
   public static final String PRODUCT_IS_NOT_AVAILABLE = "PRODUCT_IS_NOT_AVAILABLE_WITH_ID=";

   // ORDER EXCEPTION MASSAGES
   public static final String ORDER_NOT_SAVED = "ORDER_WAS_NOT_SAVED_ID_DB";
   public static final String ORDER_DIFFERENT_RESTAURANT =
         "Cart contains products from different restaurants.\nPlace an order from 1 restaurant";

   // RESTAURANT EXCEPTION MASSAGES
   public static final String RESTAURANT_NOT_FOUND = "RESTAURANT_WAS_NOT_FOUND";
   public static final String RESTAURANT_ID_NOT_FOUND = "RESTAURANT_WAS_NOT_FOUND_WITH_ID=";
   public static final String RESTAURANTS_LIST_IS_EMPTY = "RESTAURANTS_LIST_IS_EMPTY";
   public static final String RESTAURANTS_IS_CLOSE = "Sorry, We are closed, try during opening hours.%n«%s» - %s";

   // REVIEW EXCEPTION MASSAGES
   public static final String REVIEW_NOT_FOUND = "REVIEW_NOT_FOUND";
   public static final String REVIEW_ID_NOT_FOUND = "REVIEW_ID_NOT_FOUND_WITH_ID=";
   public static final String REVIEWS_NOT_FOUND = "REVIEWS_LIST_IS_EMPTY";
   public static final String REVIEW_BODY_IS_EMPTY = "REVIEW_BODY_IS_EMPTY";

   // PAYMENT EXCEPTION MASSAGES
   public static final String PAYMENT_NOT_THROUGH = "PAYMENT_FOR_THE_ORDER_DID_NOT_GO_THROUGH";
}
