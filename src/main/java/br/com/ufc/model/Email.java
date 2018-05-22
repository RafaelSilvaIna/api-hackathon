package br.com.ufc.model;

import java.util.Objects;

public class Email {
    private String emailAddress;

    public Email(String email) {
        this.emailAddress = email;
    }

    public Email() {
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Email{" +
                "emailAddress='" + emailAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(emailAddress, email1.emailAddress);
    }

    @Override
    public int hashCode() {

        return Objects.hash(emailAddress);
    }
}
