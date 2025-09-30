package org.kgromov.declarativeClient.declarative;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PostClientCommandRunner implements CommandLineRunner {
    private final PostClient postClient;

    @Override
    public void run(String... args) throws Exception {
      log.info("1st post = {}", postClient.getPost(1));
    }
}
