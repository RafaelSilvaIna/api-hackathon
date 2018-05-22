package br.com.ufc.controller;


import br.com.ufc.model.*;
import br.com.ufc.service.HackathonService;
import br.com.ufc.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("hackathons")
public class HackathonController {

    @Autowired
    HackathonService hackathonService;

    @Autowired
    OrganizerService organizerService;

    @PostMapping("/organizer")
    public ResponseEntity<Hackathon> saveHackathon(@Valid @RequestBody Hackathon hackathon, Authentication authentication) {
        Long organizerId = ((Organizer) authentication.getPrincipal()).getId();
        return new ResponseEntity<Hackathon>(hackathonService.saveHackathon(hackathon, organizerId), HttpStatus.CREATED);
    }

    @GetMapping("/organizer")
    public ResponseEntity<Page<Hackathon>> getHackathonsFromOrganizer(Pageable pageable, Authentication authentication) {
        Long organizerId = ((Organizer) authentication.getPrincipal()).getId();
        return new ResponseEntity<>(hackathonService.getHackathonsFromOrganizer(pageable, organizerId), HttpStatus.OK);
    }

    @GetMapping("/participant")
    public ResponseEntity<Page<Hackathon>> getHackathonsActive(Pageable pageable) {
        return new ResponseEntity<Page<Hackathon>>(hackathonService.getHackathonsActive(pageable), HttpStatus.OK);
    }

    @GetMapping("/organizer/{hackathonId}")
    public ResponseEntity<Hackathon> getHackathonFromOrganizer(@PathVariable("hackathonId") Long hackathonId, Authentication authentication) {
        Long organizerId = ((Organizer) authentication.getPrincipal()).getId();
        return new ResponseEntity<>(hackathonService.getHackathonFromOrganizer(hackathonId, organizerId), HttpStatus.OK);
    }

    @DeleteMapping("/organizer/{hackathonId}")
    public  ResponseEntity<?> removeHackathon(@PathVariable("hackathonId") Long hackathonId, Authentication authentication) {
        Long organizerId = ((Organizer) authentication.getPrincipal()).getId();
        hackathonService.removeHackathon(hackathonId, organizerId);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/organizer")
    public  ResponseEntity<Hackathon> updateHackathon(@Valid @RequestBody Hackathon hackathon, Authentication authentication) throws ParseException {
        Long organizerId = ((Organizer) authentication.getPrincipal()).getId();
        return new ResponseEntity<Hackathon>(hackathonService.updateHackathon(organizerId, hackathon), HttpStatus.OK);
    }

}
