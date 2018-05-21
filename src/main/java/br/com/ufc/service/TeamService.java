package br.com.ufc.service;

import br.com.ufc.bundle.SubscribeTeamRequestBodyBundle;
import br.com.ufc.model.Team;

import java.util.List;

public interface TeamService {
    List<Team> listAllTeamsByHackathonByOrganizer(Long organizerId, Long hackathonId);

    Team subscribeTeamInHackathon(SubscribeTeamRequestBodyBundle subscribeTeam);

    void unsubscribeTeamInHackathon(Long participantId, Long teamId);
}
