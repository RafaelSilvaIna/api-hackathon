package br.com.ufc.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TeamMembersLimitExceededDetails extends ErrorDetails{
    private Integer limiteParticipantsTeam;

    public Integer getLimiteParticipantsTeam() {
        return limiteParticipantsTeam;
    }

    public void setLimiteParticipantsTeam(Integer limiteParticipantsTeam) {
        this.limiteParticipantsTeam = limiteParticipantsTeam;
    }
}
