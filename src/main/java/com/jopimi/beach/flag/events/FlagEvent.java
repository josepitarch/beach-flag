package com.jopimi.beach.flag.events;

import com.jopimi.beach.flag.entities.Flag;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class FlagEvent extends ApplicationEvent {

  private final Flag flag;

  public FlagEvent(Flag flag) {
    super(flag);
    this.flag = flag;
  }
}
