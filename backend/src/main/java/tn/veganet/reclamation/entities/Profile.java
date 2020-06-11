package tn.veganet.reclamation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Profile implements Serializable {
    @Id @GeneratedValue
    private UUID uuid_profile;
    private String name;
   @ManyToMany( fetch =  FetchType.EAGER)
   private Collection<Utilisateur> utilisateurs = new ArrayList<>();

}
