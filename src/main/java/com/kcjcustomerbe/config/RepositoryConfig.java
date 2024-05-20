//package com.kcurryjibcustomer.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
//
//@Configuration
//public class RepositoryConfig {
//
//   @Bean
//   @ConfigurationProperties("spring.mysql.datasource")
//   public DataSource mysqlDataSource() {
//      return DataSourceBuilder.create().build();
//   }
//
//   @Bean
//   @ConfigurationProperties("spring.h2.datasource")
//   public DataSource h2DataSource() {
//      return DataSourceBuilder.create().build();
//   }
//
//   @Bean
//   public JdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDataSource") DataSource dataSource) {
//      return new JdbcTemplate(dataSource);
//   }
//
//   @Bean
//   public JdbcTemplate h2JdbcTemplate(@Qualifier("h2DataSource") DataSource dataSource) {
//      return new JdbcTemplate(dataSource);
//   }
//}
