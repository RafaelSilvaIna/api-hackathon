package br.com.ufc.repository;

import br.com.ufc.model.Participant;
import br.com.ufc.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("FROM Team t WHERE t.hackathon.id=:hackathonId")
    Page<Team> listAllTeamsByHackathonByOrganizer(Pageable pageable, @Param("hackathonId") Long hackathonId);

    @Query("FROM  Team t JOIN t.participants p WHERE p.id=:participantId AND t.id=:teamId")
    Team findTeamByParticipant(@Param("teamId") Long teamId, @Param("participantId") Long participantId);

    @Query("SELECT p FROM Team t JOIN t.participants p WHERE t.hackathon.id=:hackathonId AND p.email=:emailParticipant")
    Participant findParticipantByNameTeam(@Param("hackathonId") Long hackathonId, @Param("emailParticipant") String emailParticipant);

    Team findByName(String name);
}
