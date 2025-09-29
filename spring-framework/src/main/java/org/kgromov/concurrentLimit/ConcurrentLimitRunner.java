package org.kgromov.concurrentLimit;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Duration;

@RequiredArgsConstructor
//@Component
public class ConcurrentLimitRunner implements CommandLineRunner {
    private final ConcurrentClient client;
    private final TaskScheduler scheduler;

    @Override
    public void run(String... args) {
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
    }
}
