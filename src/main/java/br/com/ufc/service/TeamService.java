package br.com.ufc.service;

import br.com.ufc.dto.SubscribeTeamDTO;
import br.com.ufc.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamService {
    Page<Team> getTeamsFromHackathon(Pageable pageable, Long organizerId, Long hackathonId);

    Team subscribeTeam(SubscribeTeamDTO subscribeTeam);

    Boolean unsubscribeTeam(Long participantId, Long teamId);
}
