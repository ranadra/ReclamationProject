package tn.veganet.reclamation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.veganet.reclamation.entities.Etat;

import java.util.UUID;

public interface EtatRepository
        extends JpaRepository<Etat, UUID> {
    @Query("select e from Etat e where e.code = :x")
    public Etat findByCode(@Param("x") String code);

}
