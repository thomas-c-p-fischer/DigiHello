package com.diginamic.digihello.webRest;

import com.diginamic.digihello.domain.Departement;
import com.diginamic.digihello.domain.Ville;
import com.diginamic.digihello.exceptions.GestionExceptions;
import com.diginamic.digihello.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementResource {

    @Autowired
    private DepartementService departementService;

    /*@GetMapping
    public List<Departement> getAllDepartements() {
        return departementService.getAllDepartements();
    }

    @GetMapping("/{id}")
    public Departement getDepartementById(@PathVariable Long id) {
        return departementService.getDepartementById(id);
    }

    @PostMapping
    public Departement addDepartement(@RequestBody Departement departement) {
        return departementService.addDepartement(departement);
    }

    @PutMapping("/{id}")
    public Departement updateDepartement(@PathVariable Long id, @RequestBody Departement departement) {
        return departementService.updateDepartement(id, departement);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartement(@PathVariable Long id) {
        departementService.deleteDepartement(id);
    }

    @GetMapping("/{id}/topCities")
    public List<Ville> getTopNCitiesOfDepartement(@PathVariable Long id, @RequestParam int n) {
        return departementService.getTopNCitiesOfDepartement(id, n);
    }

    @GetMapping("/{id}/citiesInPopulationRange")
    public List<Ville> getCitiesInPopulationRangeOfDepartement(@PathVariable Long id, @RequestParam int minPopulation, @RequestParam int maxPopulation) {
        return departementService.getCitiesInPopulationRangeOfDepartement(id, minPopulation, maxPopulation);
    }*/

    // ------------------------------------------ TP - 8 ------------------------------------------

    @PostMapping
    public ResponseEntity<String> createDepartement(@RequestBody Departement departement) throws GestionExceptions {
        departementService.insertDepartement(departement);
        return ResponseEntity.ok("Le département a bien été créée !");
    }

    @GetMapping
    public ResponseEntity<String> getVilles() {
        List<Departement> result = departementService.getAllDepartements();
        if (result.isEmpty()) {
            return ResponseEntity.badRequest().body("Il n'y a aucune département !");
        }
        return ResponseEntity.ok("Succès !");
    }

    @PutMapping
    public ResponseEntity<String> updateVille(@RequestBody Departement departement) {
        boolean departementModifiee = departementService.updateDepartement(departement);
        if(departementModifiee) {
            return ResponseEntity.ok("Succès !");
        } else {
            return ResponseEntity.badRequest().body("Ce département n'existe pas !");
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteDepartement(@PathVariable("code") String code) {
        boolean departementSupprimee = departementService.supprimerDepartement(code);
        if(departementSupprimee) {
            return ResponseEntity.ok("Succès !");
        } else {
            return ResponseEntity.badRequest().body("Ce département n'existe pas !");
        }
    }
}