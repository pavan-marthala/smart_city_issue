package com.smartcity.issue.issue;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IssueRepository extends ReactiveCrudRepository<IssueEntity,String> {
    Flux<IssueEntity> findAllByCreatedBy(String id);
    Flux<IssueEntity> findAllByAssignedTo(String id);
}
