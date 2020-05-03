package tn.veganet.reclamation.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;
@Entity
public class Demande implements Serializable {
    @Id @GeneratedValue()
    private UUID id_demande;
    private String titre;

    public void setIdProcess(String idProcess) {
        IdProcess = idProcess;
    }

    public String getIdProcess() {
        return IdProcess;
    }

    private String description;
    private String IdProcess;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEtat" )
    private Etat etat;
    @OneToMany(mappedBy = "demand" , fetch = FetchType.LAZY)
    private Collection<Historique> historiques;
    public Demande() {
    }
    public UUID getId_demande() {
        return id_demande;
    }

    public void setId_demande(UUID id_demande) {
        this.id_demande = id_demande;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Demande(UUID id_demande, String titre, String description, String idProcess, Etat etat, Collection<Historique> historiques) {
        this.id_demande = id_demande;
        this.titre = titre;
        this.description = description;
        IdProcess = idProcess;
        this.etat = etat;
        this.historiques = historiques;
    }
}
