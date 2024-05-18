package com.kcjcustomerbe.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

   @Bean
   public FilterRegistrationBean<HiddenHttpMethodFilter> hiddenHttpMethodFilter() {
      FilterRegistrationBean<HiddenHttpMethodFilter> filterRegBean = new FilterRegistrationBean<>();

      filterRegBean.setFilter(new HiddenHttpMethodFilter());
      filterRegBean.setOrder(Ordered.HIGHEST_PRECEDENCE);

      return filterRegBean;
   }
}