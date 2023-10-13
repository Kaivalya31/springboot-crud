package com.kss.springbootcrud.contoller;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kss.springbootcrud.model.Participant;
import com.kss.springbootcrud.repository.ParticipantRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ParticipantController {
    @Autowired
    ParticipantRepository participantRepository;

    @GetMapping(name = "/participants")
    public ResponseEntity<List<Participant>> getAllParticipants(@RequestParam(required = false)String nation){
        try{
            List<Participant> participants = new ArrayList<Participant>();
            if(nation == null)
                participantRepository.findAll().forEach(participants::add);
            else
                participantRepository.findByNation(nation).forEach(participants::add);

            if(participants.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(participants, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/participants/{standing}")
    public ResponseEntity<Participant> getParticipantByStanding(@PathVariable("standing")long standing){
        Optional<Participant> participantData = participantRepository.findById(standing);

        if(participantData.isPresent())
            return new ResponseEntity<>(participantData.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/participant")
    public ResponseEntity<Participant> createParticipant(@RequestBody Participant participant){
        try {
            Participant participant1 = participantRepository.save(new Participant(participant.getStanding(),
                    participant.getNation(), participant.getGold(), participant.getSilver(),
                    participant.getBronze()));
            return new ResponseEntity<>(participant1, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/participant/{standing}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable("standing")long standing, @RequestBody Participant participant){
        Optional<Participant> participantData = participantRepository.findById(standing);

        if(participantData.isPresent()){
            Participant participant1 = participantData.get();
            participant1.setStanding(participant.getStanding());
            participant1.setNation(participant.getNation());
            participant1.setGold(participant.getGold());
            participant1.setSilver(participant.getSilver());
            participant1.setBronze(participant.getBronze());
            participant1.setTotal(participant.getTotal());

            return new ResponseEntity<>(participantRepository.save(participant1), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/participant/{standing}")
    public ResponseEntity<HttpStatus> deleteParticipant(@PathVariable("standing") long standing){
        try {
            participantRepository.deleteById(standing);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/participants")
    public ResponseEntity<HttpStatus> deleteAllParticipants(){
        try {
            participantRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   /* @GetMapping("/participants/{standing}")
    public ResponseEntity<List<Participant>> getByNation(@PathVariable("standing") long standing){
        Optional<Participant> participants = participantRepository.findById(standing);

        if(participants.isPresent())
            return new ResponseEntity<>(participants.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/

    @GetMapping("/participants/{nation}")
    public ResponseEntity<List<Participant>> findBystanding(@PathVariable("nation") String nation){
        try {
            List<Participant> participants = participantRepository.findByNation(nation);

            if(participants.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(participants, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
