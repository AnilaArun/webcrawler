package com.webcrawler.apiproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan("com.webcrawler.apiproject")
@EnableJpaRepositories("com.webcrawler.apiproject.dao")
@EntityScan("com.webcrawler.apiproject.domain")
@EnableScheduling
//@EnableJpaRepositories(basePackages="com.webcrawler.apiproject.dao", entityManagerFactoryRef="CustomerProfileDAO")
public class ApiprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiprojectApplication.class, args);
    }

    @Bean(name="entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        return sessionFactory;
    }

}
