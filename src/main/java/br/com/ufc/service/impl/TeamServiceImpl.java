package br.com.ufc.service.impl;

import br.com.ufc.model.Email;
import br.com.ufc.model.SubscribeTeam;
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
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    HackathonRepository hackathonRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @Override
    public Page<Team> listAllTeamsByHackathonByOrganizer(Pageable pageable, Long organizerId, Long hackathonId) {

        Hackathon hackathon = hackathonRepository.findHackathonByOrganizer(hackathonId, organizerId);

        if(hackathon == null) {
            throw new ResourceNotFoundException("Hackathon não encontrado no sistema");
        }

        return teamRepository.listAllTeamsInHackathonByOrganizer(pageable, hackathonId);
    }

    @Override
    public Team subscribeTeamInHackathon(SubscribeTeam subscribeTeam) {


        Optional<Hackathon> hackathonOptional = hackathonRepository.findById(subscribeTeam.getHackathonId());

        Hackathon hackathon = null;

        if(hackathonOptional.isPresent()) {
            hackathon = hackathonOptional.get();
        }

        if(hackathon == null) {
            throw new ResourceNotFoundException("Hackathon não encontrado no sistema");
        }


        if(subscribeTeam.getParticipantsEmails().size() > hackathon.getNumberParticipantsTeam()) {
            throw new TeamMembersLimitExceededException("Limite de membros da equipe excedido", hackathon.getNumberParticipantsTeam());
        }

        Team teamDuplicate = teamRepository.findByName(subscribeTeam.getNameTeam());

        if(teamDuplicate != null) {
            throw new ResourceDuplicateException("Já existe uma equipe inscrita com o mesmo nome nesta hackathon");
        }

        Team team = new Team();
        team.setParticipants(new ArrayList<Participant>());
        team.setName(subscribeTeam.getNameTeam());
        team.setHackathon(hackathon);
        team.setDate(new Date(System.currentTimeMillis()));

        for (Email email : subscribeTeam.getParticipantsEmails()) {
            Participant participantDuplicate = teamRepository.findParticipantWithNameEqualInOtherTeamsOfHackathon(hackathon.getId(), email.getEmailAddress());
            if(participantDuplicate != null) {
                throw new ResourceDuplicateException("Participante já pertence a uma outra equipe nesta hackathon, participante:" + participantDuplicate.getName());
            }

            Participant participant = participantRepository.findByEmail(email.getEmailAddress());

            if(participant == null) {
                throw new ResourceNotFoundException("Participante não encontrado no sitema, email:" + email.getEmailAddress());
            }

            if(team.getParticipants().contains(participant)) {
                throw new ResourceNotFoundException("Participante duplicado na equipe, participante:" + participant.getName());
            }

            team.getParticipants().add(participant);

            participant.getTeams().add(team);
        }

        team.setHackathon(hackathon);

        hackathon.getTeams().add(team);

        teamRepository.save(team);

        return team;
    }

    @Override
    public Boolean unsubscribeTeamInHackathon(Long participantId, Long teamId) {
        Team team = teamRepository.findTeamByParticipant(teamId, participantId);
        if(team == null) {
            throw new ResourceNotFoundException("Time não encontrado no sitema");
        }
        team.getHackathon().getTeams().remove(team);
        teamRepository.deleteById(team.getId());
        return Boolean.TRUE;
    }
}
