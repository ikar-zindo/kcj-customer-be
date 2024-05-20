//package com.kcurryjibcustomer.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.jdbc.init.DataSourceScriptDatabaseInitializer;
//import org.springframework.boot.sql.init.DatabaseInitializationMode;
//import org.springframework.boot.sql.init.DatabaseInitializationSettings;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//import java.util.List;
//
//@Configuration
//public class DataSourceConfig {
//
//   // MySQL ============================================================================================================
//   @Bean
//   @Primary
//   @ConfigurationProperties("mysql.datasource.k-curry-jib")
//   public DataSourceProperties mysqlDataSourceProperties() {
//      return new DataSourceProperties();
//   }
//
//   @Bean
//   @Primary
//   public HikariDataSource mysqlDataSource(DataSourceProperties blogDataSourceProperties) {
//      return blogDataSourceProperties.initializeDataSourceBuilder()
//              .type(HikariDataSource.class)
//              .build();
//   }
//
//   @Bean
//   DataSourceScriptDatabaseInitializer mysqlDataSourceScriptDatabaseInitializer(@Qualifier("mysqlDataSource") DataSource dataSource) {
//      var settings = new DatabaseInitializationSettings();
//      settings.setMode(DatabaseInitializationMode.EMBEDDED);
//      return new DataSourceScriptDatabaseInitializer(dataSource,settings);
//   }
//
//   // SESSIONS =========================================================================================================
//
//   @Bean
//   @ConfigurationProperties("h2.datasource.session")
//   public DataSourceProperties sessionDataSourceProperties() {
//      return new DataSourceProperties();
//   }
//
//   @Bean
//   public DataSource sessionDataSource(@Qualifier("sessionDataSourceProperties") DataSourceProperties sessionDataSourceProperties) {
//      return DataSourceBuilder
//              .create()
//              .url(sessionDataSourceProperties.getUrl())
//              .username(sessionDataSourceProperties.getUsername())
//              .password(sessionDataSourceProperties.getPassword())
//              .build();
//   }
//
//   @Bean
//   DataSourceScriptDatabaseInitializer sessionDataSourceScriptDatabaseInitializer(@Qualifier("sessionDataSource") DataSource dataSource) {
//      var settings = new DatabaseInitializationSettings();
////      settings.setSchemaLocations(List.of("classpath:/spring-session.sql"));
//      settings.setMode(DatabaseInitializationMode.EMBEDDED);
//      return new DataSourceScriptDatabaseInitializer(dataSource,settings);
//   }
//}
