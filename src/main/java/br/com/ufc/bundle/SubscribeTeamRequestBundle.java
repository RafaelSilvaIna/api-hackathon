package br.com.ufc.bundle;

import java.util.List;

public class SubscribeTeamRequestBundle {
    private String nameTeam;
    private List<EmailRequestBundle> participantsEmails;
    private Long hackathonId;

    public SubscribeTeamRequestBundle(String nameTeam, List<EmailRequestBundle> participantsEmails, Long hackathonId) {
        this.nameTeam = nameTeam;
        this.participantsEmails = participantsEmails;
        this.hackathonId = hackathonId;
    }

    public SubscribeTeamRequestBundle() {
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public List<EmailRequestBundle> getParticipantsEmails() {
        return participantsEmails;
    }

    public void setParticipantsEmails(List<EmailRequestBundle> participantsEmails) {
        this.participantsEmails = participantsEmails;
    }

    public Long getHackathonId() {
        return hackathonId;
    }

    public void setHackathonId(Long hackathonId) {
        this.hackathonId = hackathonId;
    }
}
