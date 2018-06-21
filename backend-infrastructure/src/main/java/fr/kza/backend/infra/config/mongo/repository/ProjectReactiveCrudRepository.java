package fr.kza.backend.infra.config.mongo.repository;

import fr.kza.backend.domain.tasks.Project;
import fr.kza.backend.infra.config.security.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProjectReactiveCrudRepository extends ReactiveCrudRepository<Project, ObjectId> {
    Mono<Project> findById(String id);
    Mono<Project> findByName(String username);
}