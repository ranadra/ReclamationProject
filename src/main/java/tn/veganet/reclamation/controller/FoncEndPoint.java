package tn.veganet.reclamation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.veganet.reclamation.dao.FoncRepository;
import tn.veganet.reclamation.entities.Fonc;

import java.util.List;

@CrossOrigin("*")
@RestController
public class FoncEndPoint {
    @Autowired
    private FoncRepository foncRepository;
    @PostMapping(value = "/addFonc",produces = MediaType.APPLICATION_JSON_VALUE)
    public Fonc saveFonc(@RequestBody Fonc fonc){
        foncRepository.save(fonc);
        return fonc;
    }
    @GetMapping(value = "/fonctionnalites",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Fonc> getFonc(){
       List<Fonc> foncs = foncRepository.findAll();
        return foncs;
    }

}
