package tn.veganet.reclamation.dao;

import tn.veganet.reclamation.entities.Profile;
import tn.veganet.reclamation.entities.RoleUser;
import tn.veganet.reclamation.entities.Utilisateur;

public interface UtilisateurInterface {
    Utilisateur SaveUser(Utilisateur user);

    Profile SaveProfile(Profile profile);

    void AddRoleToUser(RoleUser roleUser);

    Utilisateur findUserByUsername(String username);
}
