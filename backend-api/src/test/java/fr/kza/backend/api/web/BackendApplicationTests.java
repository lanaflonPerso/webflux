package fr.kza.backend.api.web;

import fr.kza.backend.api.web.config.web.WebConfig;
import fr.kza.backend.api.web.controller.test.TestRestController;
import fr.kza.backend.infra.config.mongo.MongoConfiguration;
import fr.kza.backend.infra.config.security.jwt.JwtAuthenticationRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest(TestRestController.class)
//@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {BackendApplication.class, WebConfig.class, MongoConfiguration.class})
public class BackendApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void fetchUsers() {
        JwtAuthenticationRequest jwtAuthenticationRequest = new JwtAuthenticationRequest("jdev", "jdev");
        String result1 = webTestClient
                .post().uri("/auth/token")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(jwtAuthenticationRequest), JwtAuthenticationRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .returnResult()
                .toString();

        String[] ticket = result1.split("token");


        String token = ticket[2].split("\"")[2];

        System.out.println("!!!!!!");
        System.out.println(token);
        System.out.println("!!!!!!");

        String result = webTestClient
                .get().uri("/test/user/jdev")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("username").isEqualTo("jdev")
                .jsonPath("firstname").isEqualTo("Joe")
                .jsonPath("lastname").isEqualTo("Developer")
                .jsonPath("email").isEqualTo("dev@transempiric.com")
                .returnResult()
                .toString();

        System.out.println(result);

        logger.info(token);
    }

}
