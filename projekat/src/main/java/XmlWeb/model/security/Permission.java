package XmlWeb.model.security;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany
    @JsonBackReference
    private Collection<Authority> authorityList;

    public Permission(String name, Collection<Authority> authorityList) {
        this.name = name;
        this.authorityList = authorityList;
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

    public Collection<Authority> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(Collection<Authority> authorityList) {
        this.authorityList = authorityList;
    }

    public Permission() {
    }

    public Permission(Long id, String name, Collection<Authority> authorityList) {
        this.id = id;
        this.name = name;
        this.authorityList = authorityList;
    }
}
