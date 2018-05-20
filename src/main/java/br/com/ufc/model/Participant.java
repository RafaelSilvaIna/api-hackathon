package br.com.ufc.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Participant extends User{

    @OneToMany(mappedBy = "participant", cascade = CascadeType.MERGE)
    private List<ParticipantTeam> participantTeamList;

    public List<ParticipantTeam> getParticipantTeamList() {
        return participantTeamList;
    }

    public void setParticipantTeamList(List<ParticipantTeam> participantTeamList) {
        this.participantTeamList = participantTeamList;
    }
}
