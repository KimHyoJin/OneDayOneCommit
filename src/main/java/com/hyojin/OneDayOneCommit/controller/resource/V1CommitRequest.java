package com.hyojin.OneDayOneCommit.controller.resource;

import java.time.LocalDate;
import lombok.Data;

@Data
public class V1CommitRequest {

  private String userName;
  private String repositoryUrl;
}
