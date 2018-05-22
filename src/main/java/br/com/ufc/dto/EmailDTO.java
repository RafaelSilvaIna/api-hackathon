package br.com.ufc.dto;

import java.util.Objects;

public class EmailDTO {
    private String emailAddress;

    public EmailDTO(String email) {
        this.emailAddress = email;
    }

    public EmailDTO() {
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "EmailDTO{" +
                "emailAddress='" + emailAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailDTO email1 = (EmailDTO) o;
        return Objects.equals(emailAddress, email1.emailAddress);
    }

    @Override
    public int hashCode() {

        return Objects.hash(emailAddress);
    }
}
