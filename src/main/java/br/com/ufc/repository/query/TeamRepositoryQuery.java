package br.com.ufc.repository.query;

import br.com.ufc.model.Participant;
import br.com.ufc.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeamRepositoryQuery extends JpaRepository<Team, Long> {
    @Query("FROM Team t WHERE t.hackathon.id=:hackathonId")
    Page<Team> findTeamsFromHackathon(Pageable pageable, @Param("hackathonId") Long hackathonId);

    @Query("FROM  Team t JOIN t.participants p WHERE p.id=:participantId AND t.id=:teamId")
    Team findTeamFromParticipant(@Param("teamId") Long teamId, @Param("participantId") Long participantId);

    @Query("SELECT p FROM Team t JOIN t.participants p WHERE t.hackathon.id=:hackathonId AND p.email=:emailParticipant")
    Participant findParticipantEqualInHackathon(@Param("hackathonId") Long hackathonId, @Param("emailParticipant") String emailParticipant);
}
