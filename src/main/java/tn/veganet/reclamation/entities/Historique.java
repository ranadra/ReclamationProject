package tn.veganet.reclamation.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.UUID;
@Entity
public class Historique implements Serializable {
    @Id
    private UUID id_historique;
    private String description;
    @ManyToOne
    @JoinColumn(name = "id_demande")
    private Demande demand;

    public Historique() {
    }

    public Historique(UUID id_historique, String description, Demande demand) {
        this.id_historique = id_historique;
        this.description = description;
        this.demand = demand;
    }

    public UUID getId_historique() {
        return id_historique;
    }

    public void setId_historique(UUID id_historique) {
        this.id_historique = id_historique;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Demande getDemand() {
        return demand;
    }

    public void setDemand(Demande demand) {
        this.demand = demand;
    }
}
