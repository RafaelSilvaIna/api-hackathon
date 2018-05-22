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
public class Hackathon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    private String nameEvent;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    @NotEmpty
    private String local;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @NotNull
    private Integer numberParticipantsTeam;

    @NotNull
    private Integer  numberTeams;

    @Column
    private Boolean openSubscriptions;

    @JsonIgnore
    @ManyToMany(mappedBy = "hackathons", cascade = CascadeType.ALL)
    private List<Organizer> organizers;

    @JsonIgnore
    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL)
    private  List<Team> teams;

    public Hackathon() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumberParticipantsTeam() {
        return numberParticipantsTeam;
    }

    public void setNumberParticipantsTeam(Integer numberParticipantsTeam) {
        this.numberParticipantsTeam = numberParticipantsTeam;
    }

    public Integer getNumberTeams() {
        return numberTeams;
    }

    public void setNumberTeams(Integer numberTeams) {
        this.numberTeams = numberTeams;
    }

    public List<Organizer> getOrganizers() {
        return organizers;
    }

    public void setOrganizers(List<Organizer> organizers) {
        this.organizers = organizers;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Boolean getOpenSubscriptions() {
        return openSubscriptions;
    }

    public void setOpenSubscriptions(Boolean openSubscriptions) {
        this.openSubscriptions = openSubscriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hackathon hackathon = (Hackathon) o;
        return Objects.equals(id, hackathon.id) &&
                Objects.equals(nameEvent, hackathon.nameEvent) &&
                Objects.equals(description, hackathon.description) &&
                Objects.equals(local, hackathon.local) &&
                Objects.equals(date, hackathon.date) &&
                Objects.equals(numberParticipantsTeam, hackathon.numberParticipantsTeam) &&
                Objects.equals(numberTeams, hackathon.numberTeams);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nameEvent, description, local, date, numberParticipantsTeam, numberTeams);
    }
}
