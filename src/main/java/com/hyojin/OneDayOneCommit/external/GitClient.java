package com.hyojin.OneDayOneCommit.external;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GitClient {

//  //https://api.github.com/repos/KimHyoJin/kimhyojin.github.io/commits
//  @GetMapping(value = "/repos/{owner}/{repositoryName}/commits", produces = "application/json")
//  List<Object> commits(@PathVariable("owner") String owner,
//      @PathVariable("repositoryName") String repositoryName);

  ㅣ

  private final WebClient githubWebClient;

  public Mono<String> commit(String owner, String repositoryName) {
    return githubWebClient.get()
        .uri("repos/{owner}/{repositoryName}/commits", owner, repositoryName).retrieve()
        .bodyToMono(String.class);
  }

  public Mono<List<Map<String, Object>>> commits(String owner, String repositoryName) {
    return githubWebClient.get()
        .uri("repos/{owner}/{repositoryName}/commits", owner, repositoryName).retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {
        });
  }
}
