package com.smartcity.issue.issue;

import com.smartcity.issue.shared.exception.ResourceNotFoundException;
import com.smartcity.models.Issue;
import com.smartcity.models.IssueRequest;
import com.smartcity.models.IssueStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepository issueRepository;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public Flux<Issue> getAll(){
        log.info("getting all issues");
        return issueRepository.findAll()
                .map(IssueMapper.INSTANCE::toModel);
    }

    public Flux<Issue> getUserIssues(){
        log.info("getting issues for user");
        return getUserId()
                .flatMapMany(issueRepository::findAllByCreatedBy)
                .map(IssueMapper.INSTANCE::toModel);
    }
    public Flux<Issue> getWorkerIssues(){
        log.info("getting issues for worker");
        return getUserId()
                .flatMapMany(issueRepository::findAllByAssignedTo)
                .map(IssueMapper.INSTANCE::toModel);
    }
    public Mono<Issue> getById(String id){
        log.info("getting issue by id {}",id);
        return issueRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("issue not found with id: "+id)))
                .map(IssueMapper.INSTANCE::toModel);
    }


    public Mono<String> createIssue(IssueRequest issueRequest){
        log.info("creating issue");
        return getUserId().map(id -> {
            IssueEntity issue = IssueMapper.INSTANCE.toEntity(issueRequest);
            issue.setStatus(IssueStatus.CREATED);
            issue.setCreatedBy(id);
            issue.setId(UUID.randomUUID().toString());
            return issue;
        }).flatMap(r2dbcEntityTemplate::insert).map(IssueEntity::getId);
    }

    private static Mono<String> getUserId() {
        return ReactiveSecurityContextHolder.getContext()
                .map(securityContext -> securityContext.getAuthentication().getName());
    }
}
