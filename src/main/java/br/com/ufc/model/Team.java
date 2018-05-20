package br.com.ufc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Team extends AbstractEntity{

    @NotNull
    @NotEmpty
    private String name;

//    @JsonIgnore
//    @OneToMany(mappedBy = "team", cascade = CascadeType.MERGE)
//    private List<ParticipantTeam> participantTeams;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE)
    private  List<Participant> participants;

    @ManyToOne(optional = false)
    private Hackathon hackathon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<ParticipantTeam> getParticipantTeams() {
//        return participantTeams;
//    }
//
//    public void setParticipantTeams(List<ParticipantTeam> participantTeams) {
//        this.participantTeams = participantTeams;
//    }


    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public Hackathon getHackathon() {
        return hackathon;
    }

    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }
}
