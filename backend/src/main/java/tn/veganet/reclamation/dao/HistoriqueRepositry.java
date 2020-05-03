package tn.veganet.reclamation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.veganet.reclamation.entities.Historique;

import java.util.UUID;

public interface HistoriqueRepositry
        extends JpaRepository<Historique, UUID> {
}
