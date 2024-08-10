package com.jopimi.beach.flag.entities.listeners;

import com.jopimi.beach.flag.entities.Flag;
import com.jopimi.beach.flag.events.FlagEvent;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class FlagEntityListener {

  private final ApplicationEventPublisher publisher;

  @PostPersist
  @PostRemove
  @PostUpdate
  public void onChange(Flag flag) {
    publisher.publishEvent(new FlagEvent(flag));
  }
}
