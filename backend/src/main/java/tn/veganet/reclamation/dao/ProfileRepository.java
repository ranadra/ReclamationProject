package tn.veganet.reclamation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.veganet.reclamation.entities.Profile;
import tn.veganet.reclamation.entities.Utilisateur;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    public Profile findByName(String name);

}
