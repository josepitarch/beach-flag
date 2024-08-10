package com.jopimi.beach.flag.entities;

import com.jopimi.beach.flag.entities.listeners.FlagEntityListener;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "flag")
@EntityListeners(FlagEntityListener.class)
public class Flag {

  @Id
  private String id;

  private String color;
}
