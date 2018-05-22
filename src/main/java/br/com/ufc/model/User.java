package br.com.ufc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @JsonIgnore
    private  String photo;

    @ElementCollection(targetClass = Paper.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "paper_user")
    @Column(name = "paper")
    private List<Paper> papers;

    @NotNull
    @NotEmpty
    private String phone;

    @NotNull
    @NotEmpty
    private String shirtSize;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public void setPapers(List<Paper> papers) {
        this.papers = papers;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShirtSize() {
        return shirtSize;
    }

    public void setShirtSize(String shirtSize) {
        this.shirtSize = shirtSize;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.papers;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    public boolean isOrganizer() {
        return this.papers.contains(Paper.ORGANIZER);
    }

    public void addPaper(Paper paper) {
        if (this.papers == null) {
            this.papers = new ArrayList<Paper>();
        }
        this.papers.add(paper);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, email);
    }
}
