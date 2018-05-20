package br.com.ufc.service.impl;

import br.com.ufc.model.Organizer;
import br.com.ufc.model.Paper;
import br.com.ufc.repository.OrganizerRepository;
import br.com.ufc.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OrganizerServiceImpl implements OrganizerService {

    @Autowired
    OrganizerRepository organizerRepository;

    @Override
    public Organizer save(Organizer organizer) {
        organizer.addPaper(Paper.PARTICIPANT);
        organizer.addPaper(Paper.ORGANIZER);
        organizer.setPassword(new BCryptPasswordEncoder().encode(organizer.getPassword()));
        return organizerRepository.save(organizer);
    }
}
