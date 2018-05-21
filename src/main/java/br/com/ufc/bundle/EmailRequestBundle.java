package br.com.ufc.bundle;

import java.util.Objects;

public class EmailRequestBundle {
    private String email;

    public EmailRequestBundle(String email) {
        this.email = email;
    }

    public EmailRequestBundle() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailRequestBundle{" +
                "email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailRequestBundle email1 = (EmailRequestBundle) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email);
    }
}
