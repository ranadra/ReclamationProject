package tn.veganet.reclamation.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RegistrationForm {
    private String username;
    private String email;
    private String password;
    private String repassword;

}

