package br.com.ufc.bundle;

import java.util.List;

public class SubscribeTeamRequestBodyBundle {
    private String nameTeam;
    private List<EmailRequestBodyBundle> participantsEmails;
    private Long hackathonId;

    public SubscribeTeamRequestBodyBundle(String nameTeam, List<EmailRequestBodyBundle> participantsEmails, Long hackathonId) {
        this.nameTeam = nameTeam;
        this.participantsEmails = participantsEmails;
        this.hackathonId = hackathonId;
    }

    public SubscribeTeamRequestBodyBundle() {
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public List<EmailRequestBodyBundle> getParticipantsEmails() {
        return participantsEmails;
    }

    public void setParticipantsEmails(List<EmailRequestBodyBundle> participantsEmails) {
        this.participantsEmails = participantsEmails;
    }

    public Long getHackathonId() {
        return hackathonId;
    }

    public void setHackathonId(Long hackathonId) {
        this.hackathonId = hackathonId;
    }
}
