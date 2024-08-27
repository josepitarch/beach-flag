package com.jopimi.beach.flag.events;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class EventBus {

  private final Sinks.Many<FlagEvent> sink = Sinks.many().multicast().onBackpressureBuffer();

  public void publish(FlagEvent event) {
    sink.tryEmitNext(event);
  }

  public Flux<FlagEvent> getFlagEventFlux() {
    return sink.asFlux();
  }
}
