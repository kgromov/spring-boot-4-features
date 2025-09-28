package org.kgromov.concurrentLimit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;


@Component
public class ConcurrentClient {
    private static final Logger log = LoggerFactory.getLogger(ConcurrentClient.class);

    private final Set<String> threads = new ConcurrentSkipListSet<>();

    @ConcurrencyLimit(5)
    public void write() throws Exception {
        Thread.sleep( 1000);
        threads.add(Thread.currentThread().getName());
    }

    public void debug() {
        log.info("{}:{}", threads.size(), threads);
    }
}
