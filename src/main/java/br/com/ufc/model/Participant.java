package br.com.ufc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Participant extends User{

//    @JsonIgnore
//    @OneToMany(mappedBy = "participant", cascade = CascadeType.MERGE)
//    private List<ParticipantTeam> participantTeamList;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Team> teams;

//    public List<ParticipantTeam> getParticipantTeamList() {
//        return participantTeamList;
//    }
//
//    public void setParticipantTeamList(List<ParticipantTeam> participantTeamList) {
//        this.participantTeamList = participantTeamList;
//    }


    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
