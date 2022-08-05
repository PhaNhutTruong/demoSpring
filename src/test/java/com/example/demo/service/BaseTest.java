package com.example.demo.service;

import org.testcontainers.containers.MySQLContainer;

public class BaseTest {

    static MySQLContainer mySQLContainer = (MySQLContainer) new MySQLContainer("mysql:latest")
            .withDatabaseName("demospring")
            .withUsername("testuser")
            .withPassword("123")
            .withReuse(true);

    static {
        mySQLContainer.start();
    }

}
