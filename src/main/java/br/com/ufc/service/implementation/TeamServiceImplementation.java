package br.com.ufc.service.implementation;

import br.com.ufc.dto.EmailDTO;
import br.com.ufc.dto.SubscribeTeamDTO;
import br.com.ufc.error.TeamMembersLimitExceededException;
import br.com.ufc.model.Hackathon;
import br.com.ufc.model.Participant;
import br.com.ufc.model.Team;
import br.com.ufc.repository.HackathonRepository;
import br.com.ufc.repository.ParticipantRepository;
import br.com.ufc.repository.TeamRepository;
import br.com.ufc.service.TeamService;
import br.com.ufc.error.ResourceDuplicateException;
import br.com.ufc.error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class TeamServiceImplementation implements TeamService {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    HackathonRepository hackathonRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @Override
    public Page<Team> getTeamsFromHackathon(Pageable pageable, Long organizerId, Long hackathonId) {

        Hackathon hackathon = hackathonRepository.getHackathonFromOrganizer(hackathonId, organizerId);

        if(hackathon == null) {
            throw new ResourceNotFoundException("Hackathon not found");
        }

        return teamRepository.findTeamsFromHackathon(pageable, hackathonId);
    }

    @Override
    public Team subscribeTeam(SubscribeTeamDTO subscribeTeam) {

        Optional<Hackathon> hackathonOptional = hackathonRepository.findById(subscribeTeam.getHackathonId());

        verifyIfHackathonExists(hackathonOptional);

        Hackathon hackathon = hackathonOptional.get();

        verifyIfTeamSizeExceedsLimit(subscribeTeam.getParticipantsEmails().size(), hackathon.getNumberParticipantsTeam());

        verifyIfTeamIsDuplicat(teamRepository.findByName(subscribeTeam.getNameTeam()));

        Team team = new Team();
        team.setParticipants(new ArrayList<Participant>());
        team.setName(subscribeTeam.getNameTeam());
        team.setHackathon(hackathon);
        team.setDate(new Date(System.currentTimeMillis()));

        for (EmailDTO email : subscribeTeam.getParticipantsEmails()) {

            verifyIfParticipantIsDuplicat(teamRepository.findParticipantEqualInHackathon(hackathon.getId(), email.getEmailAddress()));

            Participant participant = participantRepository.findByEmail(email.getEmailAddress());

            verifyIfParticipantExists(participant, email);

            verifyIfParticipantIsDuplicatInTeam(team, participant);

            team.getParticipants().add(participant);

            participant.getTeams().add(team);
        }

        team.setHackathon(hackathon);

        hackathon.getTeams().add(team);

        teamRepository.save(team);

        return team;
    }

    @Override
    public Boolean unsubscribeTeam(Long participantId, Long teamId) {
        Team team = teamRepository.findTeamFromParticipant(teamId, participantId);
        if(team == null) {
            throw new ResourceNotFoundException("Time not found");
        }
        team.getHackathon().getTeams().remove(team);
        teamRepository.deleteById(team.getId());
        return Boolean.TRUE;
    }


    private void verifyIfHackathonExists(Optional<Hackathon> hackathon) {
        if(!hackathon.isPresent()) {
            throw new ResourceNotFoundException("Hackathon not found");
        }
    }

    private void verifyIfTeamSizeExceedsLimit(Integer sizeTeam, Integer limit) {
        if(sizeTeam > limit) {
            throw new TeamMembersLimitExceededException("Limit Team Members Exceeded", limit);
        }
    }

    private void verifyIfTeamIsDuplicat(Team team) {
        if(team == null) {
            throw new ResourceDuplicateException("There is an inscribed team with the same name");
        }
    }

    private void verifyIfParticipantIsDuplicat(Participant participant) {
        if(participant == null) {
            throw new ResourceDuplicateException("The participant already belongs to another team, participant:" + participant.getName());
        }
    }

    private void verifyIfParticipantExists(Participant participant, EmailDTO email) {
        if (participant == null) {
            throw new ResourceNotFoundException("Participant not found, email:" + email.getEmailAddress());
        }
    }

    private void verifyIfParticipantIsDuplicatInTeam(Team team, Participant participant) {
        if(team.getParticipants().contains(participant)) {
            throw new ResourceNotFoundException("Duplicate team member, participant:" + participant.getName());
        }
    }

}
