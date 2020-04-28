package com.hyojin.OneDayOneCommit.controller;

import com.hyojin.OneDayOneCommit.service.GitClientService;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(("/api/v1/commits"))
@RequiredArgsConstructor
public class CommitController {

  private final GitClientService gitClientService;

  @GetMapping("/get")
  public Mono<String> getCommits(@RequestParam String userName,
      @RequestParam String repositoryUrl) {
    return gitClientService.getCommit(userName, repositoryUrl);
  }

  @GetMapping("/days/get")
  public Mono<Collection<String>> getDaysOfCommits(@RequestParam String userName,
      @RequestParam String repositoryUrl) {
    // TODO : make LocalDate available
    return gitClientService.getCommitDates(userName, repositoryUrl)
        .map(it -> it.stream().map(LocalDate::toString).collect(
            Collectors.toCollection(LinkedHashSet::new)));
  }

}
