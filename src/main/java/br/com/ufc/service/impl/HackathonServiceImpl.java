package br.com.ufc.service.impl;

import br.com.ufc.error.ResourceNotFoundException;
import br.com.ufc.model.*;
import br.com.ufc.repository.*;
import br.com.ufc.service.HackathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

        hackathon.setOpenSubscriptions(Boolean.TRUE);
        hackathon.setDate(hackathon.getDate());
        hackathon.setOrganizers(new ArrayList<Organizer>());
        hackathon.getOrganizers().add(organizer);

        organizer.getHackathons().add(hackathon);

        return hackathonRepository.save(hackathon);

    }

    @Override
    public Page<Hackathon> getAllHackathonsByOrganizer(Pageable pageable, Long organizerId) {
        return hackathonRepository.findAllHackathonsByOrganizer(organizerId, pageable);
    }

    @Override
    public Page<Hackathon> listAllHackathonsActive(Pageable pageable) {
        return hackathonRepository.listAllHackathonsActive(pageable);
    }

    @Override
    public Hackathon getHackathonByOrganizer(Long hackathonId, Long organizerId) {
        return hackathonRepository.findHackathonByOrganizer(hackathonId, organizerId);
    }

    @Override
    public Boolean deleteHackathonByOrganizer(Long hackathonId, Long organizerId) {
        Hackathon hackathon = hackathonRepository.findHackathonByOrganizer(hackathonId, organizerId);
        if(hackathon == null) {
            throw new ResourceNotFoundException("Hackathon não encontrado no sistema");
        }
        hackathonRepository.delete(hackathon);
        return Boolean.TRUE;
    }

    @Override
    public Hackathon updateHackathonByOrganizer(Long organizerId, Hackathon hackathon) {
        Hackathon hackathonPersist = getHackathonByOrganizer(hackathon.getId(), organizerId);

        if(hackathonPersist == null) {
            throw new ResourceNotFoundException("Hackathon não encontrado no sistema");
        }

        hackathonPersist.setDescription(hackathon.getDescription());
        hackathonPersist.setLocal(hackathon.getLocal());
        hackathonPersist.setNameEvent(hackathon.getNameEvent());
        hackathonPersist.setNumberParticipantsTeam(hackathon.getNumberParticipantsTeam());
        hackathonPersist.setNumberTeams(hackathon.getNumberTeams());
        hackathonPersist.setOpenSubscriptions(hackathon.getOpenSubscriptions());
        hackathonPersist.setDate(hackathon.getDate());

        return hackathonRepository.save(hackathonPersist);

    }


}
