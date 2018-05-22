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
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("hackathons")
public class HackathonController {

    @Autowired
    HackathonService hackathonService;

    @Autowired
    OrganizerService organizerService;

    @PostMapping("/ognz")
    public ResponseEntity<Hackathon> addHackathonByOrganizer(@Valid @RequestBody Hackathon hackathon, Authentication authentication) {
        Long organizerId = ((Organizer) authentication.getPrincipal()).getId();
        return new ResponseEntity<Hackathon>(hackathonService.save(hackathon, organizerId), HttpStatus.CREATED);
    }

    @GetMapping("/ognz")
    public ResponseEntity<Page<Hackathon>> listAllHackathonsByOrganizer(Pageable pageable, Authentication authentication) {
        Long organizerId = ((Organizer) authentication.getPrincipal()).getId();
        return new ResponseEntity<>(hackathonService.getAllHackathonsByOrganizer(pageable, organizerId), HttpStatus.OK);
    }

    @GetMapping("/ptcp")
    public ResponseEntity<Page<Hackathon>> listAllHackathonsActive(Pageable pageable) {
        return new ResponseEntity<Page<Hackathon>>(hackathonService.listAllHackathonsActive(pageable), HttpStatus.OK);
    }

    @GetMapping("/ognz/{hackathonId}")
    public ResponseEntity<Hackathon> getHackathonByOrganizer(@PathVariable("hackathonId") Long hackathonId, Authentication authentication) {
        Long organizerId = ((Organizer) authentication.getPrincipal()).getId();
        return new ResponseEntity<>(hackathonService.getHackathonByOrganizer(hackathonId, organizerId), HttpStatus.OK);
    }

    @DeleteMapping("/ognz/{hackathonId}")
    public  ResponseEntity<?> deleteHackathonByOrganizer(@PathVariable("hackathonId") Long hackathonId, Authentication authentication) {
        Long organizerId = ((Organizer) authentication.getPrincipal()).getId();
        hackathonService.deleteHackathonByOrganizer(hackathonId, organizerId);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/ognz")
    public  ResponseEntity<Hackathon> updateHackathonByOrganizer(@Valid @RequestBody Hackathon hackathon, Authentication authentication) throws ParseException {
        Long organizerId = ((Organizer) authentication.getPrincipal()).getId();
        return new ResponseEntity<Hackathon>(hackathonService.updateHackathonByOrganizer(organizerId, hackathon), HttpStatus.OK);
    }

}
