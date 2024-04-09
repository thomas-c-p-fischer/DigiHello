package com.diginamic.digihello.webRest;

import com.diginamic.digihello.domain.Ville;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleResource {

    ArrayList<Ville> villes = new ArrayList<Ville>();

    @PostMapping
    public ResponseEntity<String> insertVille(@RequestBody Ville newVille) {
        for (Ville v : villes) {
            if(newVille.getNom().equals(v.getNom())) {
                return ResponseEntity.badRequest().body("Une ville avec ce nom est déjà présente");
            }
        }
        villes.add(newVille);
        return ResponseEntity.ok("La ville a bien été ajoutée.");
    }

    @GetMapping
    public List<Ville> getVilles() {
        villes.add(new Ville("Paris", 2200000));
        villes.add(new Ville("New York", 8600000));
        villes.add(new Ville("Tokyo", 13960000));
        return new ArrayList<>(villes);
    }
}