package tn.veganet.reclamation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.veganet.reclamation.entities.Demande;
import tn.veganet.reclamation.entities.Etat;

import java.util.List;
import java.util.UUID;

public interface DemandeRepository
        extends JpaRepository<Demande, UUID> {
    @Query("select d from Demande d , Etat e where e.id_etat = d.etat and e.code = :x")
    public List<Demande> findByCode(@Param("x") String code);
}
