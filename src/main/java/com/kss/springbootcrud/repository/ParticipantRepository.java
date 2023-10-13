package com.kss.springbootcrud.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.kss.springbootcrud.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long>{
    List<Participant> findByNation(String nation);
}
