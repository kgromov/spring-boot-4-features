package org.kgromov.recilience;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RiskyClientRunner implements CommandLineRunner {

    private final RiskyClient client;

    @Override
    public void run(String... args) throws Exception {
        client.unstableCall();
    }
}
