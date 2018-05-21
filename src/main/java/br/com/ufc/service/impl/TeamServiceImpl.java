package br.com.ufc.service.impl;

import br.com.ufc.bundle.EmailRequestBundle;
import br.com.ufc.bundle.SubscribeTeamRequestBundle;
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

        return teamRepository.listAllTeamsByHackathonByOrganizer(pageable, hackathonId);
    }

    @Override
    public Team subscribeTeamInHackathon(SubscribeTeamRequestBundle subscribeTeam) {

        Hackathon hackathon = hackathonRepository.findById(subscribeTeam.getHackathonId()).get();

        if(hackathon == null) {
            throw new ResourceNotFoundException("Hackathon não encontrado no sistema");
        }

//        if(hackathon.getTeams() == null) {
//            hackathon.setTeams(new ArrayList<Team>());
//        }

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

        for (EmailRequestBundle email : subscribeTeam.getParticipantsEmails()) {
            Participant participantDuplicate = teamRepository.findParticipantByNameTeam(hackathon.getId(), email.getEmail());
            if(participantDuplicate != null) {
                throw new ResourceDuplicateException("Participante já pertence a uma outra equipe nesta hackathon, participante:" + participantDuplicate.getName());
            }

            Participant participant = participantRepository.findByEmail(email.getEmail());
            if(participant == null) {
                throw new ResourceNotFoundException("Participante não encontrado no sitema, email:" + email.getEmail());
            }

            if(team.getParticipants().contains(participant)) {
                throw new ResourceNotFoundException("Participante duplicado na equipe, participante:" + participant.getName());
            }

            team.getParticipants().add(participant);

//            if(participant.getTeams() == null) {
//                participant.setTeams(new ArrayList<Team>());
//            }

            participant.getTeams().add(team);
        }

        team.setHackathon(hackathon);

        hackathon.getTeams().add(team);

        teamRepository.save(team);


//        hackathonRepository.save(hackathon);

        return team;
    }

    @Override
    public Boolean unsubscribeTeamInHackathon(Long participantId, Long teamId) {
        Team team = teamRepository.findTeamByParticipant(teamId, participantId);

        if(team == null) {
            throw new ResourceNotFoundException("Time não encontrado no sitema");
        }
//        if(team != null) {
//            for(Participant participant : team.getParticipants()) {
//                participant.getTeams().remove(team);
//            }
        team.getHackathon().getTeams().remove(team);
        teamRepository.deleteById(team.getId());
        return Boolean.TRUE;
//        }
    }
}
