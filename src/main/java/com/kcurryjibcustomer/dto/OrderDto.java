package com.kcurryjibcustomer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kcurryjibcustomer.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;


public class OrderDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("customer")
   private CustomerDto customerDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("restaurant")
   private RestaurantDto restaurantDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("employee")
   private EmployeeDto employeeDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime createdAt;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private LocalDateTime updateAt;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private String deliveryAddress;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private BigDecimal totalAmount;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private OrderStatus orderStatus;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("orderProducts")
   private List<OrderProductDto> orderProductsDto;

   public OrderDto() {
   }

   // Getters & Setters
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public CustomerDto getCustomerDto() {
      return customerDto;
   }

   public void setCustomerDto(CustomerDto customerDto) {
      this.customerDto = customerDto;
   }

   public RestaurantDto getRestaurantDto() {
      return restaurantDto;
   }

   public void setRestaurantDto(RestaurantDto restaurantDto) {
      this.restaurantDto = restaurantDto;
   }

   public EmployeeDto getEmployeeDto() {
      return employeeDto;
   }

   public void setEmployeeDto(EmployeeDto employeeDto) {
      this.employeeDto = employeeDto;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }

   public LocalDateTime getUpdateAt() {
      return updateAt;
   }

   public void setUpdateAt(LocalDateTime updateAt) {
      this.updateAt = updateAt;
   }

   public String getDeliveryAddress() {
      return deliveryAddress;
   }

   public void setDeliveryAddress(String deliveryAddress) {
      this.deliveryAddress = deliveryAddress;
   }

   public BigDecimal getTotalAmount() {
      return totalAmount;
   }

   public void setTotalAmount(BigDecimal totalAmount) {
      this.totalAmount = totalAmount;
   }

   public OrderStatus getOrderStatus() {
      return orderStatus;
   }

   public void setOrderStatus(OrderStatus orderStatus) {
      this.orderStatus = orderStatus;
   }

   public List<OrderProductDto> getOrderProductsDto() {
      return orderProductsDto;
   }

   public void setOrderProductsDto(List<OrderProductDto> orderProductsDto) {
      this.orderProductsDto = orderProductsDto;
   }

   // Builder class
   public static class Builder {

      private OrderDto orderDto = new OrderDto();

      public Builder id(Long id) {
         orderDto.id = id;
         return this;
      }

      public Builder customerDto(CustomerDto customerDto) {
         orderDto.customerDto = customerDto;
         return this;
      }

      public Builder restaurantDto(RestaurantDto restaurantDto) {
         orderDto.restaurantDto = restaurantDto;
         return this;
      }

      public Builder employeeDto(EmployeeDto employeeDto) {
         orderDto.employeeDto = employeeDto;
         return this;
      }

      public Builder createdAt(LocalDateTime createdAt) {
         orderDto.createdAt = createdAt;
         return this;
      }

      public Builder updateAt(LocalDateTime updateAt) {
         orderDto.updateAt = updateAt;
         return this;
      }

      public Builder deliveryAddress(String deliveryAddress) {
         orderDto.deliveryAddress = deliveryAddress;
         return this;
      }

      public Builder totalAmount(BigDecimal totalAmount) {
         orderDto.totalAmount = totalAmount;
         return this;
      }

      public Builder orderStatus(OrderStatus orderStatus) {
         orderDto.orderStatus = orderStatus;
         return this;
      }

      public OrderDto build() {
         return orderDto;
      }
   }

   public static Builder builder() {
      return new Builder();
   }

   // Equals & HashCode
   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      OrderDto orderDto = (OrderDto) o;
      return Objects.equals(id, orderDto.id) && Objects.equals(customerDto, orderDto.customerDto) &&
              Objects.equals(restaurantDto, orderDto.restaurantDto) &&
              Objects.equals(employeeDto, orderDto.employeeDto) && Objects.equals(createdAt, orderDto.createdAt) &&
              Objects.equals(updateAt, orderDto.updateAt) && Objects.equals(deliveryAddress, orderDto.deliveryAddress) &&
              Objects.equals(totalAmount, orderDto.totalAmount) && orderStatus == orderDto.orderStatus &&
              Objects.equals(orderProductsDto, orderDto.orderProductsDto);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, customerDto, restaurantDto, employeeDto, createdAt, updateAt, deliveryAddress, totalAmount, orderStatus, orderProductsDto);
   }

   // ToString
   @Override
   public String toString() {
      return "OrderDto{" +
              "id=" + id +
              ", customerDto=" + customerDto +
              ", restaurantDto=" + restaurantDto +
              ", employeeDto=" + employeeDto +
              ", createdAt=" + createdAt +
              ", updateAt=" + updateAt +
              ", deliveryAddress='" + deliveryAddress + '\'' +
              ", totalAmount=" + totalAmount +
              ", orderStatus=" + orderStatus +
              ", orderProductsDto=" + orderProductsDto +
              '}';
   }

   // DateTimeFormatter
   public String getTime(LocalDateTime time) {
      DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
      return time.format(formatterTime);
   }

   public String getDate(LocalDateTime time) {
      DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd MMM yyyy");
      return time.format(formatterDate);
   }
}
