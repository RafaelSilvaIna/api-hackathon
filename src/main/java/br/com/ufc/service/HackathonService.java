package br.com.ufc.service;

import br.com.ufc.model.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HackathonService {
    Hackathon save(Hackathon hackathon, Long idOrganizer);

    List<Hackathon> getAllHackathonsByOrganizer(Pageable pageable, Long organizerId);

    Hackathon getHackathonByOrganizer(Long hackathonId, Long organizerId);

    void deleteHackathonByOrganizer(Long hackathonId, Long organizerId);

    Team subscribeTeamInHackathon(Long hackathonId, Long participantId, SubscribeTeam subscribeTeam);

    Hackathon updateHackathonByOrganizer(Long hackathonId, Long organizerId, Hackathon hackathon);
}
