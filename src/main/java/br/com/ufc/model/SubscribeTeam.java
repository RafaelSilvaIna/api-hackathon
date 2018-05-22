package br.com.ufc.model;

import java.util.List;

public class SubscribeTeam {
    private String nameTeam;
    private List<Email> participantsEmails;
    private Long hackathonId;

    public SubscribeTeam(String nameTeam, List<Email> participantsEmails, Long hackathonId) {
        this.nameTeam = nameTeam;
        this.participantsEmails = participantsEmails;
        this.hackathonId = hackathonId;
    }

    public SubscribeTeam() {
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public List<Email> getParticipantsEmails() {
        return participantsEmails;
    }

    public void setParticipantsEmails(List<Email> participantsEmails) {
        this.participantsEmails = participantsEmails;
    }

    public Long getHackathonId() {
        return hackathonId;
    }

    public void setHackathonId(Long hackathonId) {
        this.hackathonId = hackathonId;
    }
}
