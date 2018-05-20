package br.com.ufc.repository;

import br.com.ufc.model.Hackathon;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HackathonRepository extends JpaRepository<Hackathon, Long> {

//    @Query("SELECT hack FROM Hackathon hack JOIN hack.organizerHackathonList ohl JOIN ohl.organizer org WHERE org.id=:organizerId")
    @Query("SELECT hack FROM Hackathon hack JOIN hack.organizers org WHERE org.id=:organizerId")
    List<Hackathon> findAllHackathonsByOrganizer(@Param("organizerId")Long organizerId, Pageable pageable);

//    @Query("SELECT hack FROM Hackathon hack JOIN hack.organizerHackathonList ohl JOIN ohl.organizer org WHERE org.id=:organizerId AND hack.id=:hackathonId")
    @Query("SELECT hack FROM Hackathon hack JOIN hack.organizers org WHERE org.id=:organizerId AND hack.id=:hackathonId")
    Hackathon findHackathonByOrganizer(@Param("hackathonId") Long hackathonId, @Param("organizerId")Long organizerId);
}
