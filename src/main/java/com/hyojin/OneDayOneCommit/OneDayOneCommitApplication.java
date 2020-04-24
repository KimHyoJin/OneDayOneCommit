package com.hyojin.OneDayOneCommit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class OneDayOneCommitApplication {

  public static void main(String[] args) {
    SpringApplication.run(OneDayOneCommitApplication.class, args);
  }

}
