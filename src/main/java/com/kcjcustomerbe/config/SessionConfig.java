//package com.kcurryjibcustomer.config;
//
//import javax.sql.DataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
//import org.springframework.session.jdbc.config.annotation.web.http.JdbcHttpSessionConfiguration;
//
//@Configuration
//@EnableJdbcHttpSession
//public class SessionConfig {
//
//
//   @Bean
//   public DataSource dataSource() {
//      DriverManagerDataSource dataSource = new DriverManagerDataSource();
//      dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//      dataSource.setUrl("jdbc:mysql://localhost:3306/k-curry-jib");
//      dataSource.setUsername("root");
//      dataSource.setPassword("12345");
//      return dataSource;
//   }
//
////   @Bean
////   public DataSource dataSource() {
////      DriverManagerDataSource dataSource = new DriverManagerDataSource();
////      // Укажите настройки вашей базы данных
////      dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
////      dataSource.setUrl("jdbc:mysql://localhost:3306/SPRING_SESSION");
////      dataSource.setUsername("root");
////      dataSource.setPassword("12345");
////      return dataSource;
////   }
//}
