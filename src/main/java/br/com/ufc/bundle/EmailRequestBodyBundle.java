package br.com.ufc.bundle;

import java.util.Objects;

public class EmailRequestBodyBundle {
    private String email;

    public EmailRequestBodyBundle(String email) {
        this.email = email;
    }

    public EmailRequestBodyBundle() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailRequestBodyBundle{" +
                "email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailRequestBodyBundle email1 = (EmailRequestBodyBundle) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(email);
    }
}
