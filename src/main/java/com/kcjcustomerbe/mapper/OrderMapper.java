package com.kcjcustomerbe.mapper;

import com.kcjcustomerbe.dto.OrderDto;
import com.kcjcustomerbe.dto.OrderProductDto;
import com.kcjcustomerbe.entity.Order;
import com.kcjcustomerbe.entity.OrderProduct;
import org.mapstruct.*;

import java.util.List;

/**
 * This mapper helps in the conversion between `Order` entity and `OrderDto`
 */
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
   OrderDto mapOrderToOrderDto(Order entity);

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
   Order mapOrderDtoToOrder(OrderDto dto);

   @Mappings({
         @Mapping(target = "id", source = "entity.id"),
         @Mapping(target = "quantity", source = "entity.quantity"),
         @Mapping(target = "productDto", source = "entity.product"),
   })
   OrderProductDto mapOrderProductToOrderProductDto(OrderProduct entity);

   @Mappings({
         @Mapping(target = "id", source = "dto.id"),
         @Mapping(target = "quantity", source = "dto.quantity"),
         @Mapping(target = "product", source = "dto.productDto"),
   })
   OrderProduct mapOrderProductDtoToOrderProduct(OrderProductDto dto);

   List<OrderDto> mapOrdersToOrdersDto(List<Order> entities);

   List<Order> mapOrdersDtoToOrders(List<OrderDto> dtos);

   List<OrderProductDto> mapOrderProductsToOrderProductsDto(List<OrderProduct> entities);

   List<OrderProduct> mapOrderProductsDtoToOrderProducts(List<OrderProductDto> dtos);
}
