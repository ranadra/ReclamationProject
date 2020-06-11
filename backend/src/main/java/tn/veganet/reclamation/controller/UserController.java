package tn.veganet.reclamation.controller;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.veganet.reclamation.dao.ProfileRepository;
import tn.veganet.reclamation.dao.UserRepository;
import tn.veganet.reclamation.entities.Profile;
import tn.veganet.reclamation.entities.RegistrationForm;
import tn.veganet.reclamation.entities.RoleUser;
import tn.veganet.reclamation.entities.Utilisateur;
import tn.veganet.reclamation.exception.ResourceNotFoundException;
import tn.veganet.reclamation.service.UtilisateurService;
import java.security.Principal;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")

public class UserController {
    private static final Logger log = LoggerFactory.getLogger(DemandEndpoint.class);

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @PostMapping(value = "/register" , produces = MediaType.APPLICATION_JSON_VALUE)
    public  Utilisateur signUp(@RequestBody RegistrationForm data){
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
        Profile pr =profileRepository.findByName("ADMIN");
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
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping("/users")
    public List<Utilisateur> listusers(){
    List<Utilisateur> users = userRepository.findAll();
    return users;
    }
    @DeleteMapping(value = "/deleteuser" , produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(Utilisateur user){
        userRepository.delete(user);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<Utilisateur> updateTutorial(@PathVariable("id") UUID id, @RequestBody Utilisateur user) {
        Optional<Utilisateur> userData = userRepository.findById(id);
log.info("+++"+userData+"+++");
        if (userData.isPresent()) {
            Utilisateur _user = userData.get();
            log.info("+++"+_user+"+++");
            _user.setUsername(user.getUsername());
            _user.setEmail(user.getEmail());
            Optional<Profile> pr = user.getProfiles().stream().findAny();
            Profile profil = profileRepository.findByName(pr.get().getName());
            ArrayList<Profile> pro = new ArrayList<>();
            pro.add(profil);
            user.setProfiles(pro);
            log.info("+++"+profil+"+++");
            log.info("+++"+pr.get().getName()+"+++");
            _user.setProfiles(user.getProfiles());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") UUID userID)
            throws ResourceNotFoundException {
        Utilisateur user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userID));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @GetMapping("/profiles")
    public List<Profile> listprofiles(){
        List<Profile> profiles = profileRepository.findAll();
        return profiles;
    }
}
