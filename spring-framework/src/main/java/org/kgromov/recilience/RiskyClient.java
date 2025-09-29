package org.kgromov.recilience;

import lombok.extern.slf4j.Slf4j;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class RiskyClient {

    private final AtomicInteger attempts = new AtomicInteger();

    @Retryable(includes = {NotAvailableException.class}, maxAttempts = 4, delay = 1000L)
    void unstableCall() {
        log.info("Attempt #{} to get success", attempts.incrementAndGet());
        int random = ThreadLocalRandom.current().nextInt(0, 10);
        if (random < 6) {
            log.warn("Attempt #{} failed", attempts.get());
            throw new NotAvailableException("Not available after");
        }
        log.info("Success!");
    }

    private static class NotAvailableException extends RuntimeException {

        public NotAvailableException(String message) {
            super(Objects.requireNonNull(message));
        }
    }

}
