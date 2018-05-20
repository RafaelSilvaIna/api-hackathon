package br.com.ufc.service;

import br.com.ufc.model.Hackathon;
import br.com.ufc.model.Organizer;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HackathonService {
    Hackathon save(Hackathon hackathon, Long idOrganizer);

    List<Hackathon> getAllHackathonsByOrganizer(Pageable pageable, Long organizerId);

    Hackathon getHackathonByOrganizer(Long hackathonId, Long organizerId);

    void deleteHackathonByOrganizer(Long hackathonId, Long organizerId);
}
