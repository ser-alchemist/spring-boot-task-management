package com.aust.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TaskManager {

    public static void main(String[] args) {
        SpringApplication.run(TaskManager.class, args);
        System.out.println("Started!");
    }
}
