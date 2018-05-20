package br.com.ufc.repository;

import br.com.ufc.model.Participant;
import br.com.ufc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Participant findByEmail(String email);
}
