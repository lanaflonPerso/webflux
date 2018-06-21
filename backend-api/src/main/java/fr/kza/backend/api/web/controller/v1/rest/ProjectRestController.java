package fr.kza.backend.api.web.controller.v1.rest;

import fr.kza.backend.domain.tasks.Project;
import fr.kza.backend.infra.config.mongo.repository.ProjectReactiveCrudRepository;
import fr.kza.backend.infra.config.security.model.User;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(path = "/api/rest/project", produces = { APPLICATION_JSON_UTF8_VALUE })
public class ProjectRestController {

    private ProjectReactiveCrudRepository repo;
    public ProjectRestController(ProjectReactiveCrudRepository repo) {
        this.repo = repo;
    };

    @RequestMapping(method = GET)
    public Mono<ResponseEntity<List<Project>>> allProjects() {

        return repo.findAll().collectList()
                .filter(users -> users.size() > 0)
                .map(users -> ok(users))
                .defaultIfEmpty(noContent().build());
    }

    @GetMapping("/{id}")
    Mono<Project> findById(@PathVariable ObjectId id) {
        return this.repo.findById(id);
    }

}
