package br.com.ufc.service;

import br.com.ufc.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HackathonService {
    Hackathon saveHackathon(Hackathon hackathon, Long idOrganizer);

    Page<Hackathon> getHackathonsFromOrganizer(Pageable pageable, Long organizerId);

    Page<Hackathon> getHackathonsActive(Pageable pageable);

    Hackathon getHackathonFromOrganizer(Long hackathonId, Long organizerId);

    Boolean removeHackathon(Long hackathonId, Long organizerId);

    Hackathon updateHackathon(Long organizerId, Hackathon hackathon);
}
