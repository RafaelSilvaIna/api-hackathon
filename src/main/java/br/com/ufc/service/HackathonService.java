package br.com.ufc.service;

import br.com.ufc.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HackathonService {
    Hackathon save(Hackathon hackathon, Long idOrganizer);

    Page<Hackathon> getAllHackathonsByOrganizer(Pageable pageable, Long organizerId);

    Page<Hackathon> listAllHackathonsActive(Pageable pageable);

    Hackathon getHackathonByOrganizer(Long hackathonId, Long organizerId);

    Boolean deleteHackathonByOrganizer(Long hackathonId, Long organizerId);

    Hackathon updateHackathonByOrganizer(Long organizerId, Hackathon hackathon);
}
