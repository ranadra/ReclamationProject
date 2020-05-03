package tn.veganet.reclamation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tn.veganet.reclamation.controller.DemandEndpoint;
import tn.veganet.reclamation.service.FilesStorageService;
import tn.veganet.reclamation.service.UtilisateurService;

import javax.annotation.Resource;

@SpringBootApplication
public class ReclamationApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(DemandEndpoint.class);

	@Autowired
private UtilisateurService utilisateurService;
	public static void main(String[] args) {
		SpringApplication.run(ReclamationApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

	@Resource
	FilesStorageService storageService;
	@Override
	public void run(String... args) throws Exception {
//		Utilisateur user1 = new Utilisateur(UUID.randomUUID(), "admin", "rana@gmail.com", "1234");
//		Profile role = new Profile(null,"ADMIN");
//		utilisateurService.SaveProfile(role);
//		user1.getProfiles().add(role);
//		utilisateurService.SaveUser(user1);
//
//		utilisateurService.SaveUser(new Utilisateur(null, "user", "rana@gmail.com", "1234", null));

	}


}