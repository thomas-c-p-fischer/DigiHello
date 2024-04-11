package com.diginamic.digihello.service;

import com.diginamic.digihello.domain.Ville;
import com.diginamic.digihello.domain.Departement;
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

    //------------------------------------ TP 7 ------------------------------------------

    @Autowired
    private VilleRepository villeRepository;

    @PostConstruct
    public void init() {
        villeRepository.save(new Ville("Paris",2133111, new Departement("Paris","75")));
        villeRepository.save(new Ville("Marseille", 873076, new Departement("Bouches-du-Rhône", "13")));
        villeRepository.save(new Ville("Lyon", 522250, new Departement("Rhône","69")));
    }

    @Transactional
    public boolean insertVille(Ville ville) {
        Optional<Ville> villeBdd = villeRepository.findByNomAndDepartement(ville.getNom(), ville.getDepartement());
        if(villeBdd.get() == null) {
            villeRepository.save(ville);
            return true;
        } else {
            return false;
        }
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
    public List<Ville> getVilleByNomStartingWith(String nom) {
        List<Ville> villes = villeRepository.findByNomStartingWith(nom);
        if (villes.isEmpty()) {
            return null;
        } else {
            return villes;
        }
    }

    @Transactional
    public List<Ville> getVilleNbHabitantsPlusGrandQue(int nbHabitantsMin) {
        List<Ville> villes = villeRepository.findByNbHabitantsGreaterThan(nbHabitantsMin);
        if (villes.isEmpty()) {
            return null;
        } else {
            return villes;
        }
    }

    @Transactional
    public List<Ville> getVilleNbHabitantsEntre(int nbHabitantsMin, int nbHabitantsMax) {
        List<Ville> villes = villeRepository.findByNbHabitantsBetween(nbHabitantsMin, nbHabitantsMax);
        if (villes.isEmpty()) {
            return null;
        } else {
            return villes;
        }
    }

    @Transactional
    public List<Ville> getVilleByDepartementEtNbHabitantsPlusGrandQue(String departementCode, int nbHabitants) {
        List<Ville> villes = villeRepository.findByDepartementCodeAndNbHabitantsGreaterThan(departementCode, nbHabitants);
        if (villes.isEmpty()) {
            return null;
        } else {
            return villes;
        }
    }

    @Transactional
    public List<Ville> getVilleByDepartementEtNbHabitantsEntre(String departementCode, int nbHabitants, int nbHabitants2) {
        List<Ville> villes = villeRepository.findByDepartementCodeAndNbHabitantsBetween(departementCode, nbHabitants, nbHabitants2);
        if (villes.isEmpty()) {
            return null;
        } else {
            return villes;
        }
    }

    @Transactional
    public List<Ville> findVillesByDepartementOrderByNbHabitantsDesc(String departementCode, int size) {
        List<Ville> villes = villeRepository.findVillesByDepartementCodeOrderByNbHabitantsDesc(departementCode, Pageable.ofSize(size)).getContent();
        if (villes.isEmpty()) {
            return null;
        } else {
            return villes;
        }
    }
}