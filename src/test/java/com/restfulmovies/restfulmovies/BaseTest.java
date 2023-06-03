package com.restfulmovies.restfulmovies;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

public class BaseTest {
//    static final MySQLContainer ctr = new MySQLContainer<>("mysql:8.0.33");
//
//    @DynamicPropertySource
//    static void dynamicProps(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", ctr::getJdbcUrl);
//        registry.add("spring.datasource.username", ctr::getUsername);
//        registry.add("spring.datasource.password", ctr::getPassword);
//    }

//    static {
//        ctr.start();
//    }

//    @BeforeAll
//    public static void setup() {
//        ctr.start();
//    }
//
//    @AfterAll
//    public static void cleanup() {
//        ctr.stop();
//    }



}
