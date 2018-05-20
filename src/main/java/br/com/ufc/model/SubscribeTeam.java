package br.com.ufc.model;

import java.util.List;

public class SubscribeTeam {
    private String nameTeam;
    private List<Email> participantsEmails;

    public SubscribeTeam(String nameTeam, List<Email> participantsEmails) {
        this.nameTeam = nameTeam;
        this.participantsEmails = participantsEmails;
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
}
