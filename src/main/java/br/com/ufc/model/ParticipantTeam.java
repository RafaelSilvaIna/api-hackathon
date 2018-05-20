package br.com.ufc.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ParticipantTeam extends AbstractEntity{

    @ManyToOne(optional = false)
    private Participant participant;

    @ManyToOne(optional = false)
    private Team team;

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
