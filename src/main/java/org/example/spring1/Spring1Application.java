package org.example.spring1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class Spring1Application {

    public static void main(String[] args) {

        System.out.println("Hello Spring 1");
        SpringApplication.run(Spring1Application.class, args);


    }


}
