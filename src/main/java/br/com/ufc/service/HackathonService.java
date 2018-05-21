package br.com.ufc.service;

import br.com.ufc.bundle.SubscribeTeamRequestBodyBundle;
import br.com.ufc.model.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HackathonService {
    Hackathon save(Hackathon hackathon, Long idOrganizer);

    List<Hackathon> getAllHackathonsByOrganizer(Pageable pageable, Long organizerId);

    List<Hackathon> listAllHackathonsActive(Pageable pageable);

    Hackathon getHackathonByOrganizer(Long hackathonId, Long organizerId);

    void deleteHackathonByOrganizer(Long hackathonId, Long organizerId);

//    Team subscribeTeamInHackathon(Long participantId, SubscribeTeamRequestBodyBundle subscribeTeam);

    Hackathon updateHackathonByOrganizer(Long hackathonId, Long organizerId, Hackathon hackathon);
}
