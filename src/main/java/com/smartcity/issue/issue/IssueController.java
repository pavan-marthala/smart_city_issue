package com.smartcity.issue.issue;

import com.smartcity.models.Issue;
import com.smartcity.models.IssueRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {
    private final IssueService issueService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public Flux<Issue> getAll(){
        return issueService.getAll();
    }
    @GetMapping("/user")
    public Flux<Issue> getUserIssue(){
        return issueService.getUserIssues();
    }
    @GetMapping("/worker")
    public Flux<Issue> getWorkerIssue(){
        return issueService.getWorkerIssues();
    }

    @PostMapping
    public Mono<String> createIssue(@Valid @RequestBody IssueRequest issueRequest){
        return issueService.createIssue(issueRequest);
    }
}
