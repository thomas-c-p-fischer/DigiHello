package com.diginamic.digihello.webRest;

import com.diginamic.digihello.domain.Ville;
import com.diginamic.digihello.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleResource {

    // ----------------------------------------------- TP sans BDD -----------------------------------------------
    /*ArrayList<Ville> villes = new ArrayList<Ville>();

    @PostMapping
    public ResponseEntity<String> insertVille(@RequestBody Ville newVille) {
        for (Ville v : villes) {
            if(newVille.getNom().equals(v.getNom()) && newVille.getId() != v.getId()) {
                return ResponseEntity.badRequest().body("Cette ville est déjà présente");
            }
        }
        villes.add(newVille);
        return ResponseEntity.ok("La ville a bien été ajoutée.");
    }

    @PutMapping
    public ResponseEntity<String> updateVilleById(@RequestBody Ville villeModifiee) {
        for (Ville ville : villes) {
            if (ville.getId() == villeModifiee.getId()) {
                ville.setNom(villeModifiee.getNom());
                ville.setNbHabitants(villeModifiee.getNbHabitants());
                return ResponseEntity.ok("La ville a bien été modifiée.");
            }
        }
        return ResponseEntity.badRequest().body("Cette ville n'existe pas.");
    }

    @GetMapping("/{id}")
    public Ville getVille(@PathVariable Long id) {
       for (Ville v : villes) {
           if(v.getId() == id) {
               return v;
           }
       }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVille(@PathVariable Long id) {
        for (Ville v : villes){
            if(v.getId() == id) {
                villes.remove(v);
                return ResponseEntity.ok("La ville a été supprimée");
            }
        }
        return ResponseEntity.badRequest().body("La ville avec cette id : " + id + " n'existe pas.");
    }

    @GetMapping
    public List<Ville> getVilles() {
        villes.add(new Ville("Paris", 2200000));
        villes.add(new Ville("New York", 8600000));
        villes.add(new Ville("Tokyo", 13960000));
        return new ArrayList<>(villes);
    }*/

    // ----------------------------------------------- TP avec BDD -----------------------------------------------
    @Autowired
    private VilleService villeService;

    @GetMapping
    public ResponseEntity<String> getVilles() {
        villeService.extractVilles();
        return ResponseEntity.ok("Succès !");
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<String> getVilleById(@PathVariable("id") Long id) {
        villeService.extractVille(id);
        return ResponseEntity.ok("Succès !");
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<String> getVilleByNom(@PathVariable("nom") String nom) {
        Ville villeBdd = villeService.extractVilleParNom(nom);
        return ResponseEntity.ok("Succès !");
    }

    @PostMapping
    public ResponseEntity<String> createVille(@RequestBody Ville ville) {
        villeService.insertVille(ville);
        return ResponseEntity.ok("Succès !");
    }

    @PutMapping
    public ResponseEntity<String> updateVille(@RequestBody Ville ville) {
        Ville villeModifiee = villeService.extractVille(ville.getId());
        if(villeModifiee != null) {
            villeService.modifieVille(ville);
            return ResponseEntity.ok("Succès !");
        } else {
            return ResponseEntity.badRequest().body("Cette ville n'existe pas !");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVilleById(@PathVariable("id") Long id) {
        Ville villesupprimee = villeService.extractVille(id);
        if(villesupprimee != null) {
            villeService.supprimerVille(villesupprimee);
            return ResponseEntity.ok("Succès !");
        } else {
            return ResponseEntity.badRequest().body("Cette ville n'existe pas !");
        }
    }
}