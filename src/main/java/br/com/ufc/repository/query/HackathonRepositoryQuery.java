package br.com.ufc.repository.query;

import br.com.ufc.model.Hackathon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HackathonRepositoryQuery extends JpaRepository<Hackathon, Long> {
    @Query("SELECT hack FROM Hackathon hack JOIN hack.organizers org WHERE org.id=:organizerId")
    Page<Hackathon> findHackathonsFromOrganizer(@Param("organizerId")Long organizerId, Pageable pageable);

    @Query("FROM Hackathon hack WHERE hack.openSubscriptions=true")
    Page<Hackathon> findHackathonsActive(Pageable pageable);

    @Query("SELECT hack FROM Hackathon hack JOIN hack.organizers org WHERE org.id=:organizerId AND hack.id=:hackathonId")
    Hackathon getHackathonFromOrganizer(@Param("hackathonId") Long hackathonId, @Param("organizerId")Long organizerId);
}
