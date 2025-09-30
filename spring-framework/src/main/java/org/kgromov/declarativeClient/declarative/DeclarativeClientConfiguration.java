package org.kgromov.declarativeClient.declarative;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@ImportHttpServices(types = {PostClient.class}, group = "jsonplaceholder", basePackageClasses = PostClient.class)
public class DeclarativeClientConfiguration {
}
