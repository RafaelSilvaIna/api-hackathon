package br.com.ufc.controller;

import br.com.ufc.bundle.EmailRequestBodyBundle;
import br.com.ufc.bundle.SubscribeTeamRequestBodyBundle;
import br.com.ufc.model.Organizer;
import br.com.ufc.model.Participant;
import br.com.ufc.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("teams")
public class TeamController {
    @Autowired
    TeamService teamService;

    @GetMapping("/ognz/hackathon/{hackathonId}")
    public ResponseEntity<?> listAllTeamsByHackathonByOrganizer(Pageable pageable, @PathVariable("hackathonId") Long hackathonId, Authentication authentication) {
        Long organizerId = ((Organizer) authentication.getPrincipal()).getId();
        return new ResponseEntity<>(teamService.listAllTeamsByHackathonByOrganizer(organizerId, hackathonId), HttpStatus.OK);
    }

    @PostMapping("/ptcp/subscribe-team")
    public ResponseEntity<?> subscribeTeamInHackathon(@RequestBody SubscribeTeamRequestBodyBundle subscribeTeam, Authentication authentication) {

        EmailRequestBodyBundle emailBoss = new EmailRequestBodyBundle(((Participant) authentication.getPrincipal()).getEmail());

        if(!subscribeTeam.getParticipantsEmails().contains(emailBoss)) {
            subscribeTeam.getParticipantsEmails().add(emailBoss);
        }

        return new ResponseEntity<>(teamService.subscribeTeamInHackathon(subscribeTeam), HttpStatus.OK);
    }

    @DeleteMapping("/ptcp/unsubscribe-team/{teamId}")
    public ResponseEntity<?> unsubscribeTeamInHackathon(@PathVariable("teamId") Long teamId, Authentication authentication) {
        Long participantId = ((Participant) authentication.getPrincipal()).getId();
        teamService.unsubscribeTeamInHackathon(participantId, teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
