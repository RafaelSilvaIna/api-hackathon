package br.com.ufc.service;

import br.com.ufc.model.SubscribeTeam;
import br.com.ufc.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamService {
    Page<Team> listAllTeamsByHackathonByOrganizer(Pageable pageable, Long organizerId, Long hackathonId);

    Team subscribeTeamInHackathon(SubscribeTeam subscribeTeam);

    Boolean unsubscribeTeamInHackathon(Long participantId, Long teamId);
}
