package com.jopimi.beach.flag.entities;

import com.jopimi.beach.flag.entities.listeners.FlagEntityListener;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "beach_flag_color")
@EntityListeners(FlagEntityListener.class)
public class Flag {

  @Id
  private Integer id;

  private String color;
}
