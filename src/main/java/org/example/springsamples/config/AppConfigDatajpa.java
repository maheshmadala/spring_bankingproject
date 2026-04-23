package org.example.springsamples.config;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;
@Configuration
@ComponentScan("org.example.springsamples")
@EnableTransactionManagement

public class AppConfigDatajpa {
@Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/banking");
        dataSource.setUsername("postgres");
        dataSource.setPassword("Maheshsql@123");
        return dataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManager() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("org.example.springsamples");

        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(hibernateJpaVendorAdapter);

        Properties properties = new Properties();
        properties.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql",true);
        properties.put("hibernate.hbm2ddl.auto","update");
        emf.setJpaProperties(properties);
        return emf;
    }
    @Bean
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManager().getObject());
        return  transactionManager;
    }
}