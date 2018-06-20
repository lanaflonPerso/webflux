package fr.kza.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "fr.kza.backend.infra.config.web",
        "fr.kza.backend.infra.config.security",
        "fr.kza.backend.infra.config.websocket",
        "fr.kza.backend.mongo",
        "fr.kza.backend.infra.service",
        "fr.kza.backend.api"
})
@EnableConfigurationProperties({MongoProperties.class})
@EnableAutoConfiguration(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class,
})
public class BackendApplication {

        public static void main(String[] args) {
            SpringApplication app = new SpringApplication(BackendApplication.class);
            app.setWebApplicationType(WebApplicationType.REACTIVE);
            app.run(args);
        }
}
