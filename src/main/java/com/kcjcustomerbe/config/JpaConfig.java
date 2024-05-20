//package com.kcurryjibcustomer.config;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class JpaConfig {
//
//   private final DataSource dataSource;
//
//   public JpaConfig(@Qualifier("mysqlDataSource") DataSource dataSource) {
//      this.dataSource = dataSource;
//   }
//
//   @Bean
//   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//      em.setDataSource(dataSource);
//      em.setPackagesToScan("com.kcurryjibcustomer.entity");
//      em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//
//      // Установка диалекта Hibernate для H2
//      em.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//
//      return em;
//   }
//
//   @Bean
//   public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//      JpaTransactionManager transactionManager = new JpaTransactionManager();
//      transactionManager.setEntityManagerFactory(entityManagerFactory);
//      return transactionManager;
//   }
//}