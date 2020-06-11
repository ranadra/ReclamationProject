package tn.veganet.reclamation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tn.veganet.reclamation.controller.DemandEndpoint;
import tn.veganet.reclamation.dao.ProfileRepository;
import tn.veganet.reclamation.dao.UtilisateurInterface;
import tn.veganet.reclamation.dao.UserRepository;
import tn.veganet.reclamation.entities.Profile;
import tn.veganet.reclamation.entities.RoleUser;
import tn.veganet.reclamation.entities.Utilisateur;
@Service
public class UtilisateurService implements UtilisateurInterface {
    private static final Logger log = LoggerFactory.getLogger(DemandEndpoint.class);

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public Utilisateur SaveUser(Utilisateur user){
        String hashPW = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPW);
        return userRepository.save(user);
    }
    @Override
    public Profile SaveProfile(Profile profile) {
        return  profileRepository.save(profile);
    }

    public void AddRoleToUser(RoleUser roleUser){
        String username = roleUser.getUsername();
        String rolename = roleUser.getRolename();

        Profile profile = profileRepository.findByName(rolename);
        log.info(profile+"***");
        Utilisateur user = userRepository.findByUsername(username);
        user.getProfiles().add(profile);
    }
    @Override
    public Utilisateur findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }



}
