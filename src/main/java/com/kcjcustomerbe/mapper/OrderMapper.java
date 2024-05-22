package com.kcjcustomerbe.mapper;

import com.kcjcustomerbe.dto.OrderDto;
import com.kcjcustomerbe.dto.OrderProductDto;
import com.kcjcustomerbe.entity.Order;
import com.kcjcustomerbe.entity.OrderProduct;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
      unmappedTargetPolicy = ReportingPolicy.IGNORE,
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
      uses = ProductMapper.class)
public interface OrderMapper {

   @Mappings({
         @Mapping(target = "id", source = "entity.id"),
         @Mapping(target = "restaurantDto", source = "entity.restaurant"),
         @Mapping(target = "createdAt", source = "entity.createdAt"),
         @Mapping(target = "updateAt", source = "entity.updateAt"),
         @Mapping(target = "deliveryAddress", source = "entity.deliveryAddress"),
         @Mapping(target = "postalCode", source = "entity.postalCode"),
         @Mapping(target = "totalAmount", source = "entity.totalAmount"),
         @Mapping(target = "orderStatus", source = "entity.orderStatus"),
         @Mapping(target = "orderProductsDto", source = "entity.orderProducts")
   })
   OrderDto orderToOrderDto(Order entity);

   @Mappings({
         @Mapping(target = "id", source = "dto.id"),
         @Mapping(target = "restaurant", source = "dto.restaurantDto"),
         @Mapping(target = "createdAt", source = "dto.createdAt"),
         @Mapping(target = "updateAt", source = "dto.updateAt"),
         @Mapping(target = "deliveryAddress", source = "dto.deliveryAddress"),
         @Mapping(target = "postalCode", source = "dto.postalCode"),
         @Mapping(target = "totalAmount", source = "dto.totalAmount"),
         @Mapping(target = "orderStatus", source = "dto.orderStatus"),
         @Mapping(target = "orderProducts", source = "dto.orderProductsDto")
   })
   Order orderDtoToOrder(OrderDto dto);

   @Mappings({
         @Mapping(target = "id", source = "entity.id"),
         @Mapping(target = "quantity", source = "entity.quantity"),
         @Mapping(target = "productDto", source = "entity.product"),
   })
   OrderProductDto orderProductToOrderProductDto(OrderProduct entity);

   @Mappings({
         @Mapping(target = "id", source = "dto.id"),
         @Mapping(target = "quantity", source = "dto.quantity"),
         @Mapping(target = "product", source = "dto.productDto"),
   })
   OrderProduct orderProductDtoToOrderProduct(OrderProductDto dto);

   List<OrderDto> ordersToOrdersDto(List<Order> entities);

   List<Order> ordersDtoToOrders(List<OrderDto> dtos);

   List<OrderProductDto> orderProductsToOrderProductsDto(List<OrderProduct> entities);

   List<OrderProduct> orderProductsDtoToOrderProducts(List<OrderProductDto> dtos);
}
