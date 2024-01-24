package com.kcurryjibcustomer.entity;

import com.kcurryjibcustomer.entity.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "employee_id")
   private Long id;

   @Column(name = "first_name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "is_active")
   private Boolean isActive;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "restaurant_id")
   private Restaurant restaurant;

   @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
   private List<Order> orders;

   public Employee() {
   }

   // Getters & Setters
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

   public Restaurant getRestaurant() {
      return restaurant;
   }

   public void setRestaurant(Restaurant restaurant) {
      this.restaurant = restaurant;
   }

   public List<Order> getOrders() {
      return orders;
   }

   public void setOrders(List<Order> orders) {
      this.orders = orders;
   }

   // Equals & HashCode
   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Employee employee = (Employee) o;
      return Objects.equals(id, employee.id) && Objects.equals(firstName, employee.firstName) &&
              Objects.equals(lastName, employee.lastName) && Objects.equals(isActive, employee.isActive) &&
              Objects.equals(restaurant, employee.restaurant) && Objects.equals(orders, employee.orders);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, firstName, lastName, isActive, restaurant, orders);
   }

   // ToString


   @Override
   public String toString() {
      return "Employee{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", isActive=" + isActive +
              ", restaurant=" + restaurant +
              ", orders=" + orders +
              '}';
   }

   // Builder class
   public static class Builder {

      private Employee employee = new Employee();

      public Builder id(Long id) {
         employee.id = id;
         return this;
      }

      public Builder firstName(String firstName) {
         employee.firstName = firstName;
         return this;
      }

      public Builder lastName(String lastName) {
         employee.lastName = lastName;
         return this;
      }

      public Builder isActive(Boolean isActive) {
         employee.isActive = isActive;
         return this;
      }

      public Builder restaurant(Restaurant restaurant) {
         employee.restaurant = restaurant;
         return this;
      }

      public Employee build() {
         return employee;
      }
   }

   public static Builder builder() {
      return new Builder();
   }
}
