package br.com.ufc.controller;

import br.com.ufc.bundle.EmailRequestBundle;
import br.com.ufc.bundle.SubscribeTeamRequestBundle;
import br.com.ufc.model.Organizer;
import br.com.ufc.model.Participant;
import br.com.ufc.model.Team;
import br.com.ufc.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<Team>> listAllTeamsByHackathonByOrganizer(Pageable pageable, @PathVariable("hackathonId") Long hackathonId, Authentication authentication) {
        Long organizerId = ((Organizer) authentication.getPrincipal()).getId();
        return new ResponseEntity<Page<Team>>(teamService.listAllTeamsByHackathonByOrganizer(pageable, organizerId, hackathonId), HttpStatus.OK);
    }

    @PostMapping("/ptcp/subscribe-team/hackathon/{hackathonId}")
    public ResponseEntity<Team> subscribeTeamInHackathon(@PathVariable("hackathonId") Long hackathonId, @RequestBody SubscribeTeamRequestBundle subscribeTeam, Authentication authentication) {
        subscribeTeam.setHackathonId(hackathonId);
        EmailRequestBundle emailBoss = new EmailRequestBundle(((Participant) authentication.getPrincipal()).getEmail());

        if(!subscribeTeam.getParticipantsEmails().contains(emailBoss)) {
            subscribeTeam.getParticipantsEmails().add(emailBoss);
        }

        return new ResponseEntity<Team>(teamService.subscribeTeamInHackathon(subscribeTeam), HttpStatus.OK);
    }

    @DeleteMapping("/ptcp/unsubscribe-team/{teamId}")
    public ResponseEntity<?> unsubscribeTeamInHackathon(@PathVariable("teamId") Long teamId, Authentication authentication) {
        Long participantId = ((Participant) authentication.getPrincipal()).getId();
        teamService.unsubscribeTeamInHackathon(participantId, teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
