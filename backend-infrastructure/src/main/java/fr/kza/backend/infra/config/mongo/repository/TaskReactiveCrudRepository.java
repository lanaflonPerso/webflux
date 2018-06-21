package fr.kza.backend.infra.config.mongo.repository;

import fr.kza.backend.domain.tasks.Project;
import fr.kza.backend.domain.tasks.Task;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TaskReactiveCrudRepository extends ReactiveCrudRepository<Task, ObjectId> {
    Mono<Task> findById(String id);
    Mono<Task> findByUserId(String userId);
}