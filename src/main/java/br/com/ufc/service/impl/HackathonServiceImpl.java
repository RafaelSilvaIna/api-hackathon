package br.com.ufc.service.impl;

import br.com.ufc.model.*;
import br.com.ufc.repository.*;
import br.com.ufc.service.HackathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HackathonServiceImpl implements HackathonService {
    @Autowired
    HackathonRepository hackathonRepository;

    @Autowired
    OrganizerRepository organizerRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public Hackathon save(Hackathon hackathon, Long idOrganizer) {

        Organizer organizer = organizerRepository.findById(idOrganizer).get();
        hackathon.setOpenSubscriptions(true);
        hackathon.setDate(new Date(System.currentTimeMillis()));
        hackathon.setOrganizers(new ArrayList<Organizer>());

        if(organizer.getHackathons() == null) {
            organizer.setHackathons(new ArrayList<Hackathon>());
        }
        organizer.getHackathons().add(hackathon);
        hackathon.getOrganizers().add(organizer);
        hackathonRepository.save(hackathon);
        return hackathon;
    }

    @Override
    public List<Hackathon> getAllHackathonsByOrganizer(Pageable pageable, Long organizerId) {
        return hackathonRepository.findAllHackathonsByOrganizer(organizerId, pageable);
    }

    @Override
    public Hackathon getHackathonByOrganizer(Long hackathonId, Long organizerId) {
        return hackathonRepository.findHackathonByOrganizer(hackathonId, organizerId);
    }

    @Override
    public void deleteHackathonByOrganizer(Long hackathonId, Long organizerId) {
        Hackathon hackathon = hackathonRepository.findHackathonByOrganizer(hackathonId, organizerId);
        Organizer organizer = organizerRepository.findById(organizerId).get();

        if(hackathon != null) {
            organizer.getHackathons().remove(hackathon);
            hackathon.getOrganizers().remove(organizer);
            hackathonRepository.delete(hackathon);
        }
    }

    @Override
    public Team subscribeTeamInHackathon(Long hackathonId, Long participantId, SubscribeTeam subscribeTeam) {

        Participant teamBoss = participantRepository.findById(participantId).get();

        Email emailBoss = new Email(teamBoss.getEmail());

        if(!subscribeTeam.getParticipantsEmails().contains(emailBoss)) {
            subscribeTeam.getParticipantsEmails().add(emailBoss);
        }

        Hackathon hackathon = hackathonRepository.findById(hackathonId).get();

        Team team = new Team();
        team.setParticipants(new ArrayList<Participant>());
        team.setName(subscribeTeam.getNameTeam());
        team.setHackathon(hackathon);

        for (Email email : subscribeTeam.getParticipantsEmails()) {
            Participant participant = participantRepository.findByEmail(email.getEmail());

            team.getParticipants().add(participant);

            if(participant.getTeams() == null) {
                participant.setTeams(new ArrayList<Team>());
            }
            participant.getTeams().add(team);
        }

        team.setHackathon(hackathon);

        teamRepository.save(team);

        if(hackathon.getTeamList() == null) {
            hackathon.setTeamList(new ArrayList<Team>());
        }

        hackathon.getTeamList().add(team);

        hackathonRepository.save(hackathon);

        return team;
    }

    @Override
    public Hackathon updateHackathonByOrganizer(Long hackathonId, Long organizerId, Hackathon hackathon) {
        Hackathon hackathonPersist = getHackathonByOrganizer(hackathonId, organizerId);

        if(hackathonPersist != null) {
            hackathonPersist.setDescription(hackathon.getDescription());
            hackathonPersist.setLocal(hackathon.getLocal());
            hackathonPersist.setNameEvent(hackathon.getNameEvent());
            hackathonPersist.setNumberParticipantsTeam(hackathon.getNumberParticipantsTeam());
            hackathonPersist.setNumberTeams(hackathon.getNumberTeams());
            hackathonPersist.setOpenSubscriptions(hackathon.getOpenSubscriptions());

            hackathonRepository.save(hackathonPersist);
            return  hackathonPersist;
        }
        return null;
    }


}
