package tn.veganet.reclamation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.veganet.reclamation.dao.ProfileRepository;
import tn.veganet.reclamation.dao.UserRepository;
import tn.veganet.reclamation.entities.Profile;
import tn.veganet.reclamation.entities.RegistrationForm;
import tn.veganet.reclamation.entities.RoleUser;
import tn.veganet.reclamation.entities.Utilisateur;
import tn.veganet.reclamation.service.UtilisateurService;

import java.security.Principal;
import java.util.List;

@CrossOrigin("*")
@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(DemandEndpoint.class);

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @PostMapping(value = "/register" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Utilisateur signUp(@RequestBody RegistrationForm data){
        String username = data.getUsername();
        Utilisateur user = utilisateurService.findUserByUsername(username);
        if(user!=null) throw new RuntimeException("this user already exists , try with an other username");
        String email = data.getEmail();
        String password = data.getPassword();
        String repassword = data.getRepassword();
        if (!password.equals(repassword))
            throw new RuntimeException("You must confirm your password");
        Utilisateur u = new Utilisateur();
        u.setUsername(username);
        u.setPassword(password);
        u.setEmail(email);
        Profile pr =profileRepository.findByName("USER");
        String rolename = pr.getName();
        RoleUser roleUser = new RoleUser();
        roleUser.setUsername(username);
        roleUser.setRolename(rolename);
        u.getProfiles().add(pr);
        utilisateurService.SaveUser(u);

        return u;
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }
    @GetMapping("/users")
    public List<Utilisateur> listusers(){
    List<Utilisateur> users = userRepository.findAll();

    return users;
    }
}
