package com.jopimi.beach.flag.controllers;

import com.jopimi.beach.flag.events.FlagEvent;
import com.jopimi.beach.flag.repositories.FlagJPARepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class FlagController implements ApplicationListener<FlagEvent> {

  private final FlagJPARepository flagRepository;

  private final SseEmitter emitter = new SseEmitter((long) (60000 * 5));

  @SneakyThrows
  @GetMapping(value = "/flag", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public SseEmitter getFlag(HttpServletResponse response) {
    response.setHeader(HttpHeaders.CONNECTION, "keep-alive");
    response.setHeader("Keep-Alive", "timeout=0, max=100");
    emitter.send(flagRepository.findAll().getFirst().getColor());
    return emitter;
  }

  @PatchMapping("/flag")
  public String patchFlag(@RequestBody Map<String, String> flag) {
    var flagEntity = flagRepository.findAll().getFirst();
    flagEntity.setColor(flag.get("color"));
    var savedFlag = flagRepository.save(flagEntity);
    return savedFlag.getColor();
  }


  @Override
  @SneakyThrows
  public void onApplicationEvent(FlagEvent event) {
    emitter.send(event.getFlag().getColor());
  }

}
