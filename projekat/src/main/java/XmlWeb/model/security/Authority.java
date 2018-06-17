package XmlWeb.model.security;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import XmlWeb.model.Korisnik;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "AUTHORITY")
public class Authority {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq")
    @SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    @Column(name = "USERS")
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Korisnik> users;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Permission> permissions;

    public Collection<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<Permission> permissions) {
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthorityName getName() {
        return name;
    }

    public void setName(AuthorityName name) {
        this.name = name;
    }

    public List<Korisnik> getUsers() {
        return users;
    }

    public void setUsers(List<Korisnik> users) {
        this.users = users;
    }

    public Authority(@NotNull AuthorityName name, List<Korisnik> users, Collection<Permission> permissions) {
        this.name = name;
        this.users = users;
        this.permissions = permissions;
    }

    public Authority() {
    }
}