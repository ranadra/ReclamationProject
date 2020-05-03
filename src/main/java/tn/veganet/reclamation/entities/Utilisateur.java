package tn.veganet.reclamation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
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

    @ManyToMany(fetch =  FetchType.EAGER)
    @JoinTable(name = "utilprofile",
        joinColumns = @JoinColumn(name = "utilisateur_id", referencedColumnName = "uuid_util"),
        inverseJoinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "uuid_profile"))
    private Collection<Profile> profiles = new ArrayList<>();



}
