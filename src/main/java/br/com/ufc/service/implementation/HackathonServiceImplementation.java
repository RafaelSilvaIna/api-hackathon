package br.com.ufc.service.implementation;

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
public class HackathonServiceImplementation implements HackathonService {
    @Autowired
    HackathonRepository hackathonRepository;

    @Autowired
    OrganizerRepository organizerRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public Hackathon saveHackathon(Hackathon hackathon, Long idOrganizer) {

        Organizer organizer = organizerRepository.findById(idOrganizer).get();

        hackathon.setOpenSubscriptions(Boolean.TRUE);
        hackathon.setDate(hackathon.getDate());
        hackathon.setOrganizers(new ArrayList<Organizer>());
        hackathon.getOrganizers().add(organizer);

        organizer.getHackathons().add(hackathon);

        return hackathonRepository.save(hackathon);
    }

    @Override
    public Page<Hackathon> getHackathonsFromOrganizer(Pageable pageable, Long organizerId) {
        return hackathonRepository.findHackathonsFromOrganizer(organizerId, pageable);
    }

    @Override
    public Page<Hackathon> getHackathonsActive(Pageable pageable) {
        return hackathonRepository.findHackathonsActive(pageable);
    }

    @Override
    public Hackathon getHackathonFromOrganizer(Long hackathonId, Long organizerId) {
        return hackathonRepository.getHackathonFromOrganizer(hackathonId, organizerId);
    }

    @Override
    public Boolean removeHackathon(Long hackathonId, Long organizerId) {
        Hackathon hackathon = hackathonRepository.getHackathonFromOrganizer(hackathonId, organizerId);
        verifyIfHackathonExists(hackathon);
        hackathonRepository.delete(hackathon);
        return Boolean.TRUE;
    }

    @Override
    public Hackathon updateHackathon(Long organizerId, Hackathon hackathon) {
        Hackathon hackathonPersist = getHackathonFromOrganizer(hackathon.getId(), organizerId);

        verifyIfHackathonExists(hackathonPersist);

        hackathonPersist.setDescription(hackathon.getDescription());
        hackathonPersist.setLocal(hackathon.getLocal());
        hackathonPersist.setNameEvent(hackathon.getNameEvent());
        hackathonPersist.setNumberParticipantsTeam(hackathon.getNumberParticipantsTeam());
        hackathonPersist.setNumberTeams(hackathon.getNumberTeams());
        hackathonPersist.setOpenSubscriptions(hackathon.getOpenSubscriptions());
        hackathonPersist.setDate(hackathon.getDate());

        return hackathonRepository.save(hackathonPersist);

    }

    private void verifyIfHackathonExists(Hackathon hackathon) {
        if (hackathon == null) {
            throw new ResourceNotFoundException("Hackathon not found");
        }
    }


}
