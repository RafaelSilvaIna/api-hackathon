package br.com.ufc.controller;

import br.com.ufc.model.Paper;
import br.com.ufc.model.Participant;
import br.com.ufc.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("participants")
public class ParticipantController {

    @Autowired
    ParticipantService participantService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> save(@Valid @RequestBody Participant participant) {
        return new ResponseEntity<>(participantService.save(participant), HttpStatus.CREATED);
    }
}
