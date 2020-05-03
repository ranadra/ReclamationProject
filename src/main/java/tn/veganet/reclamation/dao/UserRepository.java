package tn.veganet.reclamation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.veganet.reclamation.entities.Utilisateur;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<Utilisateur, UUID> {
    public Utilisateur findByUsername(String login);
    Boolean existsByUsername(String login);
     public Utilisateur findByEmail(String email);
}
