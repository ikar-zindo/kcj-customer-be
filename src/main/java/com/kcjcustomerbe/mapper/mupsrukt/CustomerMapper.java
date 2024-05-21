package com.kcjcustomerbe.mapper.mupsrukt;

import com.kcjcustomerbe.dto.customer.CustomerAfterCreateDto;
import com.kcjcustomerbe.dto.customer.CustomerCreateDto;
import com.kcjcustomerbe.entity.Cart;
import com.kcjcustomerbe.entity.Customer;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

   @Mapping(target = "firstName", source = "firstName")
   @Mapping(target = "lastName", source = "lastName")
   @Mapping(target = "email", source = "email")
   @Mapping(target = "username", source = "username")
   @Mapping(target = "password", source = "password")
   @Mapping(target = "phoneNumber", source = "phoneNumber")
   @Mapping(target = "address", source = "address")
   @Mapping(target = "postalCode", source = "postalCode")
   @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
//   @Mapping(target = "cart.getCustomer", source = "cart")
   Customer toEntity(CustomerCreateDto customerCreateDto);

   @AfterMapping
   default void createCart(@MappingTarget Customer customer) {
      Cart cart = new Cart();
      cart.setCustomer(customer);
      customer.setCart(cart);
   }

   @Mapping(target = "id", source = "id")
   CustomerAfterCreateDto toAfterCreateDto(Customer customerAfterCreation);
}
