package org.kgromov.concurrentLimit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;


@Slf4j
@Component
class ConcurrentClient {

    private final Set<String> threads = new ConcurrentSkipListSet<>();

    @ConcurrencyLimit(5)
    void write() throws Exception {
        Thread.sleep(1000);
        threads.add(Thread.currentThread().getName());
    }

    void debug() {
        log.info("{}:{}", threads.size(), threads);
    }
}
