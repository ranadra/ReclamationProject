package tn.veganet.reclamation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;
@Entity
public class Etat implements Serializable {
    @Id @GeneratedValue()
    private UUID id_etat;
    private String code;
    private String designation;
    @OneToMany(mappedBy = "etat",cascade={ CascadeType.MERGE} )
    private Collection<Demande> demandes;

    public Etat() {
    }


    public UUID getId_etat() {
        return id_etat;
    }

    public void setId_etat(UUID id_etat) {
        this.id_etat = id_etat;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Collection<Demande> getDemandes() {
        return demandes;
    }

    public void setDemandes(Collection<Demande> demandes) {
        this.demandes = demandes;
    }
}
