package org.kgromov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.resilience.annotation.EnableResilientMethods;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableResilientMethods
@SpringBootApplication
public class SpringFrameworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFrameworkApplication.class, args);
    }
}
