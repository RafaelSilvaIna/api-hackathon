package br.com.ufc.error;

public class TeamMembersLimitExceededException extends RuntimeException  {
    private Integer limiteParticipantsTeam;
    public TeamMembersLimitExceededException(String message, Integer limiteParticipantsTeam) {
        super(message);
        this.limiteParticipantsTeam =limiteParticipantsTeam;
    }

    public Integer getLimiteParticipantsTeam() {
        return limiteParticipantsTeam;
    }

    public void setLimiteParticipantsTeam(Integer limiteParticipantsTeam) {
        this.limiteParticipantsTeam = limiteParticipantsTeam;
    }
}
