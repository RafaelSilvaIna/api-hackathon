package br.com.ufc.controller;

import br.com.ufc.dto.EmailDTO;
import br.com.ufc.dto.SubscribeTeamDTO;
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

    @GetMapping("/organizer/hackathon/{hackathonId}")
    public ResponseEntity<Page<Team>> getTeamsFromHackathon(Pageable pageable, @PathVariable("hackathonId") Long hackathonId, Authentication authentication) {
        Long organizerId = ((Organizer) authentication.getPrincipal()).getId();
        return new ResponseEntity<Page<Team>>(teamService.getTeamsFromHackathon(pageable, organizerId, hackathonId), HttpStatus.OK);
    }

    @PostMapping("/participant/subscribe-team/hackathon/{hackathonId}")
    public ResponseEntity<Team> subscribeTeam(@PathVariable("hackathonId") Long hackathonId, @RequestBody SubscribeTeamDTO subscribeTeam, Authentication authentication) {
        subscribeTeam.setHackathonId(hackathonId);
        EmailDTO emailBoss = new EmailDTO(((Participant) authentication.getPrincipal()).getEmail());

        if(!subscribeTeam.getParticipantsEmails().contains(emailBoss)) {
            subscribeTeam.getParticipantsEmails().add(emailBoss);
        }

        return new ResponseEntity<Team>(teamService.subscribeTeam(subscribeTeam), HttpStatus.OK);
    }

    @DeleteMapping("/participant/unsubscribe-team/{teamId}")
    public ResponseEntity<Boolean> unsubscribeTeam(@PathVariable("teamId") Long teamId, Authentication authentication) {
        Long participantId = ((Participant) authentication.getPrincipal()).getId();
        return new ResponseEntity<>(teamService.unsubscribeTeam(participantId, teamId),HttpStatus.OK);
    }
}
