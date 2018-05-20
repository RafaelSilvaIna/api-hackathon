package br.com.ufc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
public class Organizer extends User{

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.MERGE)
    private List<OrganizerHackathon> organizerHackathonList;

    public List<OrganizerHackathon> getOrganizerHackathonList() {
        return organizerHackathonList;
    }

    public void setOrganizerHackathonList(List<OrganizerHackathon> organizerHackathonList) {
        this.organizerHackathonList = organizerHackathonList;
    }
}
