package com.kcurryjibcustomer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Objects;


public class EmployeeDto {

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Long id;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Length(max = 30, message = "{validation.length.max.30}")
   private String firstName;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @NotEmpty(message = "{validation.length.empty}")
   @Length(max = 30, message = "{validation.length.max.30}")
   private String lastName;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private Boolean isActive;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   private RestaurantDto restaurantDto;

   @JsonInclude(JsonInclude.Include.NON_NULL)
   @JsonProperty("orders")
   private List<OrderDto> ordersDto;

   public EmployeeDto() {
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public Boolean getActive() {
      return isActive;
   }

   public void setActive(Boolean active) {
      isActive = active;
   }

   public RestaurantDto getRestaurantDto() {
      return restaurantDto;
   }

   public void setRestaurantDto(RestaurantDto restaurantDto) {
      this.restaurantDto = restaurantDto;
   }

   public List<OrderDto> getOrdersDto() {
      return ordersDto;
   }

   public void setOrdersDto(List<OrderDto> ordersDto) {
      this.ordersDto = ordersDto;
   }

   // Builder class
   public static class Builder {

      private EmployeeDto employeeDto = new EmployeeDto();

      public Builder id(Long id) {
         employeeDto.id = id;
         return this;
      }

      public Builder firstName(String firstName) {
         employeeDto.firstName = firstName;
         return this;
      }

      public Builder lastName(String lastName) {
         employeeDto.lastName = lastName;
         return this;
      }

      public Builder isActive(Boolean isActive) {
         employeeDto.isActive = isActive;
         return this;
      }

      public Builder restaurantDto(RestaurantDto restaurantDto) {
         employeeDto.restaurantDto = restaurantDto;
         return this;
      }

      public EmployeeDto build() {
         return employeeDto;
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
      EmployeeDto that = (EmployeeDto) o;
      return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(isActive, that.isActive) && Objects.equals(restaurantDto, that.restaurantDto) && Objects.equals(ordersDto, that.ordersDto);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, firstName, lastName, isActive, restaurantDto, ordersDto);
   }

   // ToString

   @Override
   public String toString() {
      return "EmployeeDto{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", isActive=" + isActive +
              ", restaurantDto=" + restaurantDto +
              ", ordersDto=" + ordersDto +
              '}';
   }
}
