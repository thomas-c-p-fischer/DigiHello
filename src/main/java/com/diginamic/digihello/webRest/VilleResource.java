package com.diginamic.digihello.webRest;

import com.diginamic.digihello.domain.Ville;
import com.diginamic.digihello.exceptions.GestionExceptions;
import com.diginamic.digihello.service.VilleService;
import com.diginamic.digihello.service.dto.VilleDto;
import com.diginamic.digihello.service.mapper.VilleMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleResource {

    // ----------------------------------------------- TP 1 - 5 -----------------------------------------------
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

    // ----------------------------------------------- TP 06 -----------------------------------------------
    @Autowired
    private VilleService villeService;

    /*
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
    }*/

    //-------------------------------- TP - 7 --------------------------------
   @Autowired
   private VilleMapper villeMapper;

    @PostMapping
    public ResponseEntity<String> createVille(@RequestBody VilleDto villeDto) throws GestionExceptions {
        Ville ville = villeMapper.toBean(villeDto);
        villeService.insertVille(ville);
        return ResponseEntity.ok("La ville a bien été créée !");
    }

    @GetMapping
    public ResponseEntity<String> getVilles() {
        List<Ville> result = villeService.getAllVilles();
        if (result.isEmpty()) {
            return ResponseEntity.badRequest().body("Il n'y a aucune ville !");
        }
        return ResponseEntity.ok("Succès !");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getVilleById(@PathVariable("id") Long id) {
        Ville result = villeService.getVilleById(id);
        if (result == null) {
            return ResponseEntity.badRequest().body("Il n'y a aucune ville avec cette id !");
        } else {
            return ResponseEntity.ok("Succès !");
        }
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<String> getVilleByNom(@PathVariable("nom") String nom) {
        Ville result = villeService.getVilleByNom(nom);
        if (result == null) {
            return ResponseEntity.badRequest().body("Il n'y a aucune ville avec ce nom !");
        } else {
            return ResponseEntity.ok("Succès !");
        }
    }

    @PutMapping
    public ResponseEntity<String> updateVille(@RequestBody Ville ville) {
        boolean villeModifiee = villeService.updateVille(ville);
        if(villeModifiee) {
            return ResponseEntity.ok("Succès !");
        } else {
            return ResponseEntity.badRequest().body("Cette ville n'existe pas !");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVilleById(@PathVariable("id") Long id) {
        boolean villesupprimee = villeService.supprimerVille(id);
        if(villesupprimee) {
            return ResponseEntity.ok("Succès !");
        } else {
            return ResponseEntity.badRequest().body("Cette ville n'existe pas !");
        }
    }

    @GetMapping("/commencant/{nom}")
    public ResponseEntity<String> getVilleByNomCommencantPar(@PathVariable("nom") String nom) throws GestionExceptions {
        villeService.getVilleByNomStartingWith(nom);
        return ResponseEntity.ok("Succès !");
    }

    @GetMapping("/NbHabitant/{nbHabitants}")
    public ResponseEntity<String> getVilleByNbHabitantPlusGrand(@PathVariable("nbHabitants") int nbHabitants) throws GestionExceptions {
        villeService.getVilleNbHabitantsPlusGrandQue(nbHabitants);
        return ResponseEntity.ok("Succès !");
    }

    @GetMapping("/nbHabitantsEntre/{nbHabitantsMin}/{nbHabitantsMax}")
    public ResponseEntity<String> getVilleByNbHabitantEntre(
            @PathVariable("nbHabitantsMin") int nbHabitantsMin,
            @PathVariable("nbHabitantsMax") int nbHabitantsMax) throws GestionExceptions {
        List<Ville> result = villeService.getVilleNbHabitantsEntre(nbHabitantsMin, nbHabitantsMax);
        if (result == null) {
            return ResponseEntity.badRequest().body("Il n'y a aucune ville avec un nombre d'habitants entre " + nbHabitantsMin + " et " + nbHabitantsMax + " !");
        } else {
            return ResponseEntity.ok("Succès !");
        }
    }

    @GetMapping("/departementNbHabitantsPlusGrand/{departementCode}/{nbHabitants}")
    public ResponseEntity<String> getVilleByDepartementEtNbHabitantsPlusGrandQue(
            @PathVariable("departementCode") String departementCode,
            @PathVariable("nbHabitants") int nbHabitants) throws GestionExceptions {
        villeService.getVilleByDepartementEtNbHabitantsPlusGrandQue(departementCode, nbHabitants);
        return ResponseEntity.ok("Succès !");
    }

    @GetMapping("/nbHabitantsDepartementEntre")
    public ResponseEntity<String> getVilleByDepartementEtNbHabitantsEntre(
            @PathVariable("departementCode") String departementCode,
            @PathVariable("nbHabitants") int nbHabitants,
            @PathVariable("nbHabitants") int nbHabitants1) throws GestionExceptions {
        villeService.getVilleByDepartementEtNbHabitantsEntre(departementCode, nbHabitants, nbHabitants1);
        return ResponseEntity.ok("Succès !");
    }

    @GetMapping("/DepartementNbHabitants/{departementCode}/{size}")
    public ResponseEntity<String> getVilleByDepartementOrderByNbHabitantsDesc(@PathVariable("departementCode") String departementCode, @PathVariable("size") int size) throws GestionExceptions {
        villeService.findVillesByDepartementOrderByNbHabitantsDesc(departementCode, size);
        return ResponseEntity.ok("Succès !");
    }

    @GetMapping("/export")
    public void getFichierVille(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"villes.csv\"");
        List<Ville> villes = villeService.getAllVilles();
        PrintWriter writer = response.getWriter();
        writer.println("Nom de la ville,Nombre d'habitants,Code département,Nom du département");
        for (Ville ville : villes) {
            writer.println(ville.getNom() + "," +
                    ville.getNbHabitants() + "," +
                    ville.getDepartement().getCode() + "," +
                    ville.getDepartement().getNom());
        }
        writer.flush();
        writer.close();
    }
}