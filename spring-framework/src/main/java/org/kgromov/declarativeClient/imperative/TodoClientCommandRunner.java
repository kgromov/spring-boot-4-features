package org.kgromov.declarativeClient.imperative;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TodoClientCommandRunner implements CommandLineRunner {
    private final TodoClient todoClient;

    @Override
    public void run(String... args) throws Exception {
      log.info("1st todo item = {}", todoClient.getTodo(1));
    }
}
