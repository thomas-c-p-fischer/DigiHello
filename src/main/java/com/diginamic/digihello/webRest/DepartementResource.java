package com.diginamic.digihello.webRest;

import com.diginamic.digihello.domain.Departement;
import com.diginamic.digihello.domain.Ville;
import com.diginamic.digihello.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementResource {

    @Autowired
    private DepartementService departementService;

    @GetMapping
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

    // Custom Operations

    @GetMapping("/{id}/topCities")
    public List<Ville> getTopNCitiesOfDepartement(@PathVariable Long id, @RequestParam int n) {
        return departementService.getTopNCitiesOfDepartement(id, n);
    }

    @GetMapping("/{id}/citiesInPopulationRange")
    public List<Ville> getCitiesInPopulationRangeOfDepartement(@PathVariable Long id, @RequestParam int minPopulation, @RequestParam int maxPopulation) {
        return departementService.getCitiesInPopulationRangeOfDepartement(id, minPopulation, maxPopulation);
    }
}