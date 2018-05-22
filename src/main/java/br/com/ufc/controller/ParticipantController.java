package br.com.ufc.controller;

import br.com.ufc.model.Participant;
import br.com.ufc.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("participants")
public class ParticipantController {

    @Autowired
    ParticipantService participantService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> saveParticipant(@Valid @RequestBody Participant participant) {
        return new ResponseEntity<>(participantService.saveParticipant(participant), HttpStatus.CREATED);
    }
}
