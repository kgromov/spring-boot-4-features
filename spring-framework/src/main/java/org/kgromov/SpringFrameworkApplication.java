package org.kgromov;

import org.kgromov.concurrentLimit.ConcurrentClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.resilience.annotation.EnableResilientMethods;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Duration;

@EnableScheduling
@EnableResilientMethods
@SpringBootApplication
public class SpringFrameworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFrameworkApplication.class, args);
    }

    @Bean
    ApplicationRunner reliabilityRunner(TaskScheduler scheduler, ConcurrentClient client) {
        return _ -> {
            scheduler.scheduleAtFixedRate(client::debug, Duration.ofMillis(1500));
            if (scheduler instanceof TaskExecutor executor) {
                for (var i = 0; i < 1000; i++) {
                    executor.execute(() -> {
                        try {
                            client.write();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
        };
    }
}
