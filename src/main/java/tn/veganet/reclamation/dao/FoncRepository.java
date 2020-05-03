package tn.veganet.reclamation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.veganet.reclamation.entities.Fonc;

import java.util.UUID;

public interface FoncRepository
        extends JpaRepository<Fonc, UUID> {
}
