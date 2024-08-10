package com.jopimi.beach.flag.repositories;

import com.jopimi.beach.flag.entities.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlagJPARepository extends JpaRepository<Flag, String> {
}
