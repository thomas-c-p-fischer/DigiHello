package com.diginamic.digihello.service;
import com.diginamic.digihello.domain.Departement;
import com.diginamic.digihello.domain.Ville;
import com.diginamic.digihello.exceptions.GestionExceptions;
import com.diginamic.digihello.repository.DepartementRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartementService {

    // ------------------------------------------ TP - 6 ------------------------------------------
    /*@PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Departement> getAllDepartements() {
        return entityManager.createQuery("SELECT d FROM Departement d", Departement.class).getResultList();
    }

    @Transactional
    public Departement getDepartementById(Long id) {
        return entityManager.find(Departement.class, id);
    }

    @Transactional
    public Departement addDepartement(Departement departement) {
        entityManager.persist(departement);
        return departement;
    }

    @Transactional
    public Departement updateDepartement(Long id, Departement departement) {
        Departement existingDepartement = entityManager.find(Departement.class, id);
        if (existingDepartement != null) {
            departement.setId(id);
            entityManager.merge(departement);
            return departement;
        }
        return null;
    }

    @Transactional
    public void deleteDepartement(Long id) {
        Departement departement = entityManager.find(Departement.class, id);
        if (departement != null) {
            entityManager.remove(departement);
        }
    }

    @Transactional
    public List<Ville> getTopNCitiesOfDepartement(Long id, int n) {
        return entityManager.createQuery("SELECT v FROM Ville v WHERE v.departement.id = :id ORDER BY v.nbHabitants DESC", Ville.class)
                .setParameter("id", id)
                .setMaxResults(n)
                .getResultList();
    }

    @Transactional
    public List<Ville> getCitiesInPopulationRangeOfDepartement(Long id, int minPopulation, int maxPopulation) {
        return entityManager.createQuery("SELECT v FROM Ville v WHERE v.departement.id = :id AND v.nbHabitants BETWEEN :minPopulation AND :maxPopulation", Ville.class)
                .setParameter("id", id)
                .setParameter("minPopulation", minPopulation)
                .setParameter("maxPopulation", maxPopulation)
                .getResultList();
    }*/

    // ------------------------------------------ TP - 8 ------------------------------------------

    @Autowired
    private DepartementRepository departementRepository;

    @Transactional
    public void insertDepartement(Departement departement) throws GestionExceptions {
        Departement departementBdd = departementRepository.findByCode(departement.getCode());
        if (departement.getCode().length() < 2 || departement.getCode().length() > 3) {
            throw new GestionExceptions("Le code département doit être composé de 2 à 3 caractères");
        }
        if (departement.getNom().length() < 3) {
            throw new GestionExceptions("Le nom du département doit contenir au moins 3 lettres");
        }
        if(departementBdd == null) {
            departementRepository.save(departement);
        }
    }

    @Transactional
    public void insertDepartementCsv(Departement departement) {
        departementRepository.save(departement);
    }

    @Transactional
    public List<Departement> getAllDepartements() {
        Iterable<Departement> departementsIterable = departementRepository.findAll();
        List<Departement> departements = new ArrayList<>();
        departementsIterable.forEach(departements::add);
        if(departements.isEmpty()) {
            return null;
        } else {
            return departements;
        }
    }

    @Transactional
    public Departement getDepartementByCode(String code) {
        Departement departement = departementRepository.findByCode(code);
        if(departement != null) {
            return departement;
        } else {
            return null;
        }
    }

    @Transactional
    public Departement getDepartementByNom(String nom, String code) {
        Departement departement = departementRepository.findByNomAndCode(nom, code);
        if(departement != null) {
            return departement;
        } else {
            return null;
        }
    }

    @Transactional
    public boolean updateDepartement(Departement departement) {
        Departement departementUpdate = departementRepository.findByCode(departement.getCode());
        if (departementUpdate != null) {
            departementRepository.save(departement);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean supprimerDepartement(String code) {
        Departement departementDelete = departementRepository.findByCode(code);
        if (departementDelete != null) {
            departementRepository.delete(departementDelete);
            return true;
        } else {
            return false;
        }
    }
}