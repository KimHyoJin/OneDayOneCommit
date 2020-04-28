package com.hyojin.OneDayOneCommit.service;

import com.hyojin.OneDayOneCommit.external.GitClient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GitClientService {

  private final GitClient gitClient;

  public Mono<String> getCommit(String userName, String repositoryName) {
    return gitClient.commit(userName, repositoryName);
  }

  public Mono<List<LocalDate>> getCommitDates(String userName, String repositoryName) {
    return gitClient.commits(userName, repositoryName)
        .map(list -> list.stream()
            .map(it -> (Map<String, Object>) it.get("commit"))
            .map(it -> (Map<String, Object>) it.get("author"))
            .map(it -> (String) it.get("date"))
            .map(dateTimeStr -> LocalDateTime
                .parse(dateTimeStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME))
            .map(LocalDateTime::toLocalDate)
            .collect(Collectors.toList()));
  }
}
