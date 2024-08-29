package com.jopimi.beach.flag.entities.listeners;

import com.jopimi.beach.flag.entities.Flag;
import com.jopimi.beach.flag.events.EventBus;
import com.jopimi.beach.flag.events.FlagEvent;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FlagEntityListener {

  private final EventBus eventBus;

  @PostPersist
  @PostRemove
  @PostUpdate
  public void onChange(Flag flag) {
    eventBus.publish(new FlagEvent(flag));
  }
}
