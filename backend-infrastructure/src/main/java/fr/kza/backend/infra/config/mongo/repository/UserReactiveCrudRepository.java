package fr.kza.backend.infra.config.mongo.repository;

import fr.kza.backend.infra.config.security.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserReactiveCrudRepository extends ReactiveCrudRepository<User, ObjectId> {
    Mono<UserDetails> findByUsername(String username);
    Mono<User> findUserByUsername(String username);
}