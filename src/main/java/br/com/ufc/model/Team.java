package br.com.ufc.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Team extends AbstractEntity{

    @NotNull
    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.MERGE)
    private List<ParticipantTeam> participantTeams;

    @ManyToOne(optional = false)
    private Hackathon hackathon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ParticipantTeam> getParticipantTeams() {
        return participantTeams;
    }

    public void setParticipantTeams(List<ParticipantTeam> participantTeams) {
        this.participantTeams = participantTeams;
    }

    public Hackathon getHackathon() {
        return hackathon;
    }

    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }
}
