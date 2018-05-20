package br.com.ufc.service.impl;

import br.com.ufc.model.Hackathon;
import br.com.ufc.model.Organizer;
import br.com.ufc.model.OrganizerHackathon;
import br.com.ufc.repository.HackathonRepository;
import br.com.ufc.repository.OrganizerRepository;
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

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Hackathon save(Hackathon hackathon, Long idOrganizer) {

        Organizer organizer = organizerRepository.findById(idOrganizer).get();
        hackathon.setOpenSubscriptions(true);
        hackathon.setDate(new Date(System.currentTimeMillis()));
        hackathon.setOrganizerHackathonList(new ArrayList<OrganizerHackathon>());

        OrganizerHackathon organizerHackathon = new OrganizerHackathon();
        organizerHackathon.setHackathon(hackathon);
        organizerHackathon.setOrganizer(organizer);

        if(organizer.getOrganizerHackathonList() != null) {
            organizer.getOrganizerHackathonList().add(organizerHackathon);
        } else {
            organizer.setOrganizerHackathonList(new ArrayList<OrganizerHackathon>());
            organizer.getOrganizerHackathonList().add(organizerHackathon);
        }

        hackathon.getOrganizerHackathonList().add(organizerHackathon);

        hackathonRepository.save(hackathon);
        organizerRepository.save(organizer);

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

        if(hackathon != null)
            hackathonRepository.delete(hackathon);
    }


}
