package fr.kza.backend.api.web.config.init;

import fr.kza.backend.domain.tasks.Project;
import fr.kza.backend.domain.tasks.Task;
import fr.kza.backend.infra.config.mongo.repository.ProjectReactiveCrudRepository;
import fr.kza.backend.infra.config.mongo.repository.TaskReactiveCrudRepository;
import fr.kza.backend.infra.config.security.model.User;
import fr.kza.backend.infra.config.mongo.repository.UserReactiveCrudRepository;
import org.bson.types.ObjectId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class CmdLineRunner {
    private final UserReactiveCrudRepository userReactiveCrudRepository;
    private final ProjectReactiveCrudRepository projectReactiveCrudRepository;
    private final TaskReactiveCrudRepository taskReactiveCrudRepository;

    public CmdLineRunner(UserReactiveCrudRepository userReactiveCrudRepository,
                         ProjectReactiveCrudRepository projectReactiveCrudRepository,
                         TaskReactiveCrudRepository taskReactiveCrudRepository) {
        Assert.notNull(userReactiveCrudRepository, "userReactiveCrudRepository cannot be null");
        Assert.notNull(projectReactiveCrudRepository, "projectReactiveCrudRepository cannot be null");
        Assert.notNull(taskReactiveCrudRepository, "taskReactiveCrudRepository cannot be null");
        this.userReactiveCrudRepository = userReactiveCrudRepository;
        this.projectReactiveCrudRepository = projectReactiveCrudRepository;
        this.taskReactiveCrudRepository = taskReactiveCrudRepository;
    }

    @Bean
    public CommandLineRunner initDatabase() {

        Flux<User> people = Flux.just(
                new User(new ObjectId(), "jdev", "Joe", "Developer", "dev@transempiric.com", "{noop}dev", Arrays.asList("ROLE_ADMIN"), true, new Date())
        );


        Flux<Project> project = Flux.just(
          Project.builder()
          .id(new ObjectId())
          .name("Project1")
          .tasks(Arrays.asList(Task.builder()
                  .id(new ObjectId())
                  .description("Task1")
                  .dateBegin(new Date())
                  .build()))
          .build()
        );

        Flux<Task> task = Flux.just(
                Task.builder()
                        .id(new ObjectId())
                        .description("TaskWithoutProject")
                        .dateBegin(new Date())
                        .build()
        );

        return args -> {
            this.userReactiveCrudRepository.deleteAll().thenMany(userReactiveCrudRepository.saveAll(people)).blockLast();
            this.projectReactiveCrudRepository.deleteAll().thenMany(projectReactiveCrudRepository.saveAll(project)).blockLast();
            this.taskReactiveCrudRepository.deleteAll().thenMany(taskReactiveCrudRepository.saveAll(task)).blockLast();
        };
    }
}
