package com.jopimi.beach.flag.services;

import com.jopimi.beach.flag.entities.Flag;
import com.jopimi.beach.flag.repositories.FlagJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlagService {

  private final FlagJPARepository flagRepository;

  public Flag getCurrentFlag() {
    return flagRepository.findAll().getFirst();
  }

  public Flag updateFlag(String color) {
    var flagEntity = flagRepository.findAll().getFirst();
    flagEntity.setColor(color);

    return flagRepository.save(flagEntity);
  }

}
