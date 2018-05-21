package br.com.ufc.service.impl;

import br.com.ufc.bundle.EmailRequestBodyBundle;
import br.com.ufc.bundle.SubscribeTeamRequestBodyBundle;
import br.com.ufc.model.Hackathon;
import br.com.ufc.model.Participant;
import br.com.ufc.model.Team;
import br.com.ufc.repository.HackathonRepository;
import br.com.ufc.repository.ParticipantRepository;
import br.com.ufc.repository.TeamRepository;
import br.com.ufc.service.TeamService;
import error.ResourceDuplicateException;
import error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    HackathonRepository hackathonRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @Override
    public List<Team> listAllTeamsByHackathonByOrganizer(Long organizerId, Long hackathonId) {

        Hackathon hackathon = hackathonRepository.findHackathonByOrganizer(hackathonId, organizerId);

        if(hackathon == null) {
            return null;
        }

        return teamRepository.listAllTeamsByHackathonByOrganizer(hackathonId);
    }

    @Override
    public Team subscribeTeamInHackathon(SubscribeTeamRequestBodyBundle subscribeTeam) {

        Hackathon hackathon = hackathonRepository.findById(subscribeTeam.getHackathonId()).get();

        if(hackathon.getTeamList() == null) {
            hackathon.setTeamList(new ArrayList<Team>());
        }

        Team teamDuplicate = teamRepository.findByName(subscribeTeam.getNameTeam());
        if(teamDuplicate != null) {
            throw new ResourceDuplicateException("Já existe uma equipe inscrita com o mesmo nome nesta hackathon");
        }

        Team team = new Team();
        team.setParticipants(new ArrayList<Participant>());
        team.setName(subscribeTeam.getNameTeam());
        team.setHackathon(hackathon);

        for (EmailRequestBodyBundle email : subscribeTeam.getParticipantsEmails()) {
            Participant participantDuplicate = teamRepository.findParticipantByNameTeam(hackathon.getId(), email.getEmail());
            if(participantDuplicate != null) {
                throw new ResourceDuplicateException("Participante já pertence a uma outra equipe nesta hackathon, participante:" + participantDuplicate.getName());
            }

            Participant participant = participantRepository.findByEmail(email.getEmail());
            if(participant == null) {
                throw new ResourceNotFoundException("Participante não cadastrado no sitema, email:" + email.getEmail());
            }

            if(team.getParticipants().contains(participant)) {
                throw new ResourceNotFoundException("Participante duplicado na equipe, participante:" + participant.getName());
            }

            team.getParticipants().add(participant);

            if(participant.getTeams() == null) {
                participant.setTeams(new ArrayList<Team>());
            }

            participant.getTeams().add(team);
        }

        team.setHackathon(hackathon);

        teamRepository.save(team);

        hackathon.getTeamList().add(team);

        hackathonRepository.save(hackathon);

        return team;
    }

    @Override
    public void unsubscribeTeamInHackathon(Long participantId, Long teamId) {
        Team team = teamRepository.findTeamByParticipant(teamId, participantId);

        System.out.println(team);
        if(team != null) {
            for(Participant participant : team.getParticipants()) {
                participant.getTeams().remove(team);
            }
            team.getHackathon().getTeamList().remove(team);
            teamRepository.deleteById(team.getId());
        }
    }
}
