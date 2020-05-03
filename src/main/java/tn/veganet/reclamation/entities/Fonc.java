package tn.veganet.reclamation.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Fonc implements Serializable {
    @Id @GeneratedValue
    private UUID idFonc;
    private String designation;
    private String icon;
    private String url;
    @Column(unique = true)
    private String code;
    @ManyToOne()
    @JoinColumn(name="parent_id")
    private Fonc fonc;


}
