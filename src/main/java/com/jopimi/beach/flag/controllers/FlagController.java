package com.jopimi.beach.flag.controllers;

import com.jopimi.beach.flag.entities.Flag;
import com.jopimi.beach.flag.events.EventBus;
import com.jopimi.beach.flag.events.FlagEvent;
import com.jopimi.beach.flag.services.FlagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class FlagController {

  private final FlagService flagService;

  private final EventBus eventBus;

  @GetMapping(value = "/flag", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<String> getFlag() {
    var currentFlag = flagService.getCurrentFlag();
    return Flux.concat(
            Flux.just(currentFlag.getColor()),
            eventBus.getFlagEventFlux().map(FlagEvent::getFlag).map(Flag::getColor)
    );
  }

  @PatchMapping("/flag")
  public Mono<Map<String, String>> patchFlag(@RequestBody Map<String, String> flag) {
    var updatedFlag = flagService.updateFlag(flag.get("color"));
    return Mono.just(Map.of("color", updatedFlag.getColor()));
  }

}
