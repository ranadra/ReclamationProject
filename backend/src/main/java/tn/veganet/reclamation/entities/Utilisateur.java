package tn.veganet.reclamation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
public class Utilisateur implements Serializable {
    @Id @GeneratedValue
    private UUID uuid_util;
    private String username;
    private String email;
    @Size(max = 60)
    private String password;
    public Utilisateur() {
    }

    public Utilisateur(UUID uuid_util, String username, String email, @Size(max = 60) String password) {
        this.uuid_util = uuid_util;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Utilisateur(UUID uuid_util, String username, String email, @Size(max = 60) String password, Collection<Profile> profiles) {
        this.uuid_util = uuid_util;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profiles = profiles;
    }

    public UUID getUuid_util() {
        return uuid_util;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Collection<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Collection<Profile> profiles) {
        this.profiles = profiles;
    }

    @JsonIgnoreProperties(value= {"profiles"})

    @ManyToMany(fetch =  FetchType.LAZY , cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "utilprofile",
        joinColumns = @JoinColumn(name = "utilisateur_id", referencedColumnName = "uuid_util"),
        inverseJoinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "uuid_profile"))
    private Collection<Profile> profiles = new ArrayList<>();



}
