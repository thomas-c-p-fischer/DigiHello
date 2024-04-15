package com.diginamic.digihello.service;

import com.diginamic.digihello.domain.Ville;
import com.diginamic.digihello.domain.Departement;
import com.diginamic.digihello.exceptions.GestionExceptions;
import com.diginamic.digihello.repository.DepartementRepository;
import com.diginamic.digihello.repository.VilleRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VilleService {
    //------------------------------------ TP 6 ------------------------------------------
   /* private EntityManager entityManager;

    @Transactional
    public List<Ville> extractVilles() {
        return entityManager.createQuery("SELECT v FROM Ville v", Ville.class).getResultList();
    }

    @Transactional
    public Ville extractVille(@Param("id") Long id) {
        return entityManager.createQuery("SELECT v FROM Ville v WHERE v.id =:id ", Ville.class).setParameter("id", id).getSingleResult();
    }

    @Transactional
    public Ville extractVilleParNom(@Param("nom") String nom) {
        return entityManager.createQuery("SELECT v FROM Ville v WHERE v.nom =:nom ", Ville.class).setParameter("nom", nom).getSingleResult();
    }

    @Transactional
    public void insertVille(Ville ville) {
        entityManager.persist(ville);
    }

    @Transactional
    public void modifieVille(Ville ville) {
        Ville villeBdd = entityManager.find(ville.getClass(), ville.getId());
        if(villeBdd != null) {
            villeBdd.setNom(ville.getNom());
            villeBdd.setNbHabitants(ville.getNbHabitants());
        }
    }

    @Transactional
    public void supprimerVille(Ville ville) {
        entityManager.remove(ville);
    }*/

    //------------------------------------ TP 7/8/9 ------------------------------------------

    @Autowired
    private VilleRepository villeRepository;

    /*@PostConstruct
    public void init() {
        villeRepository.save(new Ville("Paris",2133111, new Departement("Paris","75")));
        villeRepository.save(new Ville("Marseille", 873076, new Departement("Bouches-du-Rhône", "13")));
        villeRepository.save(new Ville("Lyon", 522250, new Departement("Rhône","69")));
    }*/

    @Transactional
    public void insertVilleCsv(Ville ville){
        villeRepository.save(ville);
    }

    @Transactional
    public void insertVille(Ville ville) throws GestionExceptions {
        /*if(ville.getNbHabitants() < 6500) {
            throw new GestionExceptions("Une Ville doit avoir au moins 6500 habitants.");
        }*/
        if(ville.getNom().length() < 2) {
            throw new GestionExceptions("Le nom de la ville doit avoir au moins 2 caractères");
        }
        if(ville.getDepartement().getCode().length() < 2 || ville.getDepartement().getCode().length() > 3) {
            throw new GestionExceptions("Le code département doit être composé de 2 à 3 caractères");
        }
        if (ville.getDepartement().getNom().length() < 3) {
            throw new GestionExceptions("Le nom du département doit contenir au moins 3 lettres");
        }
        Iterable<Ville> villesIterable = villeRepository.findAll();
        List<Ville> villes = new ArrayList<>();
        villesIterable.forEach(villes::add);
        for(Ville v : villes) {
            if(v.getNom().equals(ville.getNom()) &&
                    v.getDepartement().getCode().equals(ville.getDepartement().getCode()) &&
                    v.getDepartement().getNom().equals(ville.getDepartement().getNom())) {
                throw  new GestionExceptions("Cette ville existe déjà.");
            }
        }
        villeRepository.save(ville);
    }

    @Transactional
    public List<Ville> getAllVilles() {
        Iterable<Ville> villesIterable = villeRepository.findAll();
        List<Ville> villes = new ArrayList<>();
        villesIterable.forEach(villes::add);
        if(villes.isEmpty()) {
            return null;
        } else {
            return villes;
        }
    }

    @Transactional
    public Ville getVilleById(@Param("id") Long id) {
        Optional<Ville> ville = villeRepository.findById(id);
        if(ville.isPresent()) {
            return ville.get();
        } else {
            return null;
        }
    }

    @Transactional
    public Ville getVilleByNom(@Param("nom") String nom) {
        Optional<Ville> ville = villeRepository.findByNom(nom);
        if(ville.isPresent()) {
            return ville.get();
        } else {
            return null;
        }
    }

    @Transactional
    public boolean updateVille(Ville ville) {
        Optional<Ville> villeUpdate = villeRepository.findByNomAndDepartement(ville.getNom(), ville.getDepartement());
        if (villeUpdate.get() != null) {
            villeRepository.save(ville);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean supprimerVille(Long id) {
        Optional<Ville> villeDelete = villeRepository.findById(id);
        if (villeDelete.isPresent()) {
            villeRepository.delete(villeDelete.get());
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public List<Ville> getVilleByNomStartingWith(String nom) throws GestionExceptions {
        List<Ville> villes = villeRepository.findByNomStartingWith(nom);
        if (villes.isEmpty()) {
            throw new GestionExceptions("Aucune ville dont le nom commence par " + nom + " n'a été trouvée");
        } else {
            return villes;
        }
    }

    /*@Transactional
    public List<Ville> getVilleNbHabitantsPlusGrandQue(int nbHabitantsMin) throws GestionExceptions {
        List<Ville> villes = villeRepository.findByNbHabitantsGreaterThan(nbHabitantsMin);
        if (villes.isEmpty()) {
            throw new GestionExceptions("Aucune ville n'a une population supérieure à " + nbHabitantsMin);
        } else {
            return villes;
        }
    }*/

   /* @Transactional
    public List<Ville> getVilleNbHabitantsEntre(int nbHabitantsMin, int nbHabitantsMax) throws GestionExceptions {
        List<Ville> villes = villeRepository.findByNbHabitantsBetween(nbHabitantsMin, nbHabitantsMax);
        if (villes.isEmpty()) {
            throw new GestionExceptions("Aucune ville n'a une population comprise entre " + nbHabitantsMin + " et " + nbHabitantsMax);
        } else {
            return villes;
        }
    }*/

    /*@Transactional
    public List<Ville> getVilleByDepartementEtNbHabitantsPlusGrandQue(String departementCode, int nbHabitants) throws GestionExceptions {
        List<Ville> villes = villeRepository.findByDepartementCodeAndNbHabitantsGreaterThan(departementCode, nbHabitants);
        if (villes.isEmpty()) {
            throw new GestionExceptions("Aucune ville n'a une population supérieure à " + nbHabitants + " dans le département " + departementCode);
        } else {
            return villes;
        }
    }

    @Transactional
    public List<Ville> getVilleByDepartementEtNbHabitantsEntre(String departementCode, int nbHabitants, int nbHabitants2) throws GestionExceptions {
        List<Ville> villes = villeRepository.findByDepartementCodeAndNbHabitantsBetween(departementCode, nbHabitants, nbHabitants2);
        if (villes.isEmpty()) {
            throw new GestionExceptions("Aucune ville n'a une population comprise entre " + nbHabitants + " et " + nbHabitants2 + " dans le département " + departementCode);
        } else {
            return villes;
        }
    }

    @Transactional
    public List<Ville> findVillesByDepartementOrderByNbHabitantsDesc(String departementCode, int size) throws GestionExceptions {
        List<Ville> villes = villeRepository.findVillesByDepartementCodeOrderByNbHabitantsDesc(departementCode, Pageable.ofSize(size)).getContent();
        if (villes.isEmpty()) {
            throw new GestionExceptions("Aucune ville n'a été trouvée dans le département " + departementCode);
        } else {
            return villes;
        }
    }*/
}