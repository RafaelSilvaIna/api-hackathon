package br.com.ufc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @JoinTable(name = "team_participant", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "participant_id"))
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private  List<Participant> participants;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Hackathon hackathon;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;

    public Team() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) &&
                Objects.equals(name, team.name) &&
                Objects.equals(date, team.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, date);
    }
}
