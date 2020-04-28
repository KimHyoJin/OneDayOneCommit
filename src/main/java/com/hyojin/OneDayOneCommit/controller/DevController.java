package com.hyojin.OneDayOneCommit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/dev")
public class DevController {

  @GetMapping("/test")
  Mono<String> dev() {
    return Mono.just("test");
  }

}
