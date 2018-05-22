package br.com.ufc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Participant extends User{
    @JsonIgnore
    @ManyToMany(mappedBy = "participants")
    private List<Team> teams;

    public Participant() {
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
