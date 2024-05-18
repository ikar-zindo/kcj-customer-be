package com.kcjcustomerbe.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class MapperUtil {

   @Bean
   public ModelMapper getMapper() {
      ModelMapper mapper = new ModelMapper();

//      mapper.getConfiguration()
//              .setFieldMatchingEnabled(true)
////              .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
//              .setSkipNullEnabled(true);

      return mapper;
   }

   public static <R, E> List<R> convertlist(List<E> list, Function<E, R> convert) {
      return list.stream().map(e -> convert.apply(e)).collect(Collectors.toList());
   }
}
