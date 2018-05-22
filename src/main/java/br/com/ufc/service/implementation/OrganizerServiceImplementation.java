package br.com.ufc.service.implementation;

import br.com.ufc.model.Organizer;
import br.com.ufc.model.Paper;
import br.com.ufc.repository.OrganizerRepository;
import br.com.ufc.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OrganizerServiceImplementation implements OrganizerService {

    @Autowired
    OrganizerRepository organizerRepository;

    @Override
    public Organizer saveOrganizer(Organizer organizer) {
        organizer.setPhoto("default.png");
        organizer.addPaper(Paper.PARTICIPANT);
        organizer.addPaper(Paper.ORGANIZER);
        organizer.setPassword(new BCryptPasswordEncoder().encode(organizer.getPassword()));
        return organizerRepository.save(organizer);
    }
}
