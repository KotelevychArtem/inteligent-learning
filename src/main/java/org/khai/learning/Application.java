package org.khai.learning;

import org.khai.learning.api.A;
import org.khai.learning.api.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    @Autowired
    private A tt;

    @Autowired
    private Controller controller;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
