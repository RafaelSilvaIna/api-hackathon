package br.com.ufc.controller;


import br.com.ufc.model.*;
import br.com.ufc.service.HackathonService;
import br.com.ufc.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("hackathons")
public class HackathonController {

    @Autowired
    HackathonService hackathonService;

    @Autowired
    OrganizerService organizerService;

    @PostMapping("/ognz")
    public ResponseEntity<?> addHackathonByOrganizer(@Valid @RequestBody Hackathon hackathon, Authentication authentication) {
        Long organizerId = ((Organizer) authentication.getPrincipal()).getId();
        return new ResponseEntity<>(hackathonService.save(hackathon, organizerId), HttpStatus.CREATED);
    }

    @GetMapping("/ognz")
    public ResponseEntity<?> listAllHackathonsByOrganizer(Pageable pageable, Authentication authentication) {
        Long organizerId = ((Organizer) authentication.getPrincipal()).getId();
        return new ResponseEntity<>(hackathonService.getAllHackathonsByOrganizer(pageable, organizerId), HttpStatus.OK);
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

    @PutMapping("/ognz/{hackathonId}")
    public  ResponseEntity<Hackathon> updateHackathonByOrganizer(@PathVariable("hackathonId") Long hackathonId, @RequestBody Hackathon hackathon, Authentication authentication) {
        Long organizerId = ((Organizer) authentication.getPrincipal()).getId();
        return new ResponseEntity<Hackathon>(hackathonService.updateHackathonByOrganizer(hackathonId, organizerId, hackathon), HttpStatus.OK);
    }

    @PostMapping("/ptcp/subscribe-team/{hackathonId}")
    public ResponseEntity<?> subscribeTeamInHackathon(@PathVariable("hackathonId") Long hackathonId, @RequestBody SubscribeTeam subscribeTeam, Authentication authentication) {
        Long participantId = ((Participant) authentication.getPrincipal()).getId();
        hackathonService.subscribeTeamInHackathon(hackathonId, participantId, subscribeTeam);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}