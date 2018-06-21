package fr.kza.backend.api.web;

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
        "fr.kza.backend.api",
        "fr.kza.backend.infra.config.mongo.repository",
        "fr.kza.backend.infra.config"
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
