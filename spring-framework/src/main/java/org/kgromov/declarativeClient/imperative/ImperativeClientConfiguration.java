package org.kgromov.declarativeClient.imperative;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.AbstractHttpServiceRegistrar;

@Configuration
@Import(ImperativeClientConfiguration.ClientRegistrar.class)
public class ImperativeClientConfiguration {

    static class ClientRegistrar extends AbstractHttpServiceRegistrar {

        @Override
        protected void registerHttpServices(GroupRegistry registry, AnnotationMetadata metadata) {
            registry.forGroup("todos")
                    .register(TodoClient.class)
                    .detectInBasePackages(TodoClient.class);
        }
    }

    @Bean
    RestClientHttpServiceGroupConfigurer configurer() {
        return new RestClientHttpServiceGroupConfigurer() {
            @Override
            public void configureGroups(Groups<RestClient.Builder> groups) {
                groups.filterByName("todos")
                        .forEachClient((group, builder) -> builder.defaultHeader("User-Agent", "Spring"));
            }
        };
    }
}
