package br.com.ufc.dto;

import java.util.List;
import java.util.Objects;

public class SubscribeTeamDTO {
    private String nameTeam;
    private List<EmailDTO> participantsEmails;
    private Long hackathonId;

    public SubscribeTeamDTO(String nameTeam, List<EmailDTO> participantsEmails, Long hackathonId) {
        this.nameTeam = nameTeam;
        this.participantsEmails = participantsEmails;
        this.hackathonId = hackathonId;
    }

    public SubscribeTeamDTO() {
    }


    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public List<EmailDTO> getParticipantsEmails() {
        return participantsEmails;
    }

    public void setParticipantsEmails(List<EmailDTO> participantsEmails) {
        this.participantsEmails = participantsEmails;
    }

    public Long getHackathonId() {
        return hackathonId;
    }

    public void setHackathonId(Long hackathonId) {
        this.hackathonId = hackathonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscribeTeamDTO that = (SubscribeTeamDTO) o;
        return Objects.equals(nameTeam, that.nameTeam) &&
                Objects.equals(participantsEmails, that.participantsEmails) &&
                Objects.equals(hackathonId, that.hackathonId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nameTeam, participantsEmails, hackathonId);
    }
}
