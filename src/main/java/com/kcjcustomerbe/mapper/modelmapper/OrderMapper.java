package com.kcjcustomerbe.mapper.modelmapper;

import com.kcjcustomerbe.dto.OrderDto;
import com.kcjcustomerbe.entity.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

   @Autowired
   private ModelMapper mapper;

   // CONVERT TO DTO
   public OrderDto cnovertToOrderDto(Order order) {
      return mapper.map(order, OrderDto.class);
   }

   // CONVERT TO ENTITY
   public Order cnovertToOrder(OrderDto orderDto) {
      return mapper.map(orderDto, Order.class);
   }

   public List<Order> convertToOrders(List<OrderDto> ordersDto) {
      return ordersDto.stream()
              .map(this::cnovertToOrder)
              .collect(Collectors.toList());
   }
}
