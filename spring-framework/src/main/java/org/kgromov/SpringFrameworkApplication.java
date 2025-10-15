package org.kgromov;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.resilience.annotation.EnableResilientMethods;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.classfile.ClassFile;

@EnableScheduling
@EnableResilientMethods
@SpringBootApplication
public class SpringFrameworkApplication {

    static void main(String[] args) {
//        ClassFile.of() .parse()
        SpringApplication.run(SpringFrameworkApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            System.out.println("Working directory = " +System.getProperty("user.dir"));
        };
    }
}
