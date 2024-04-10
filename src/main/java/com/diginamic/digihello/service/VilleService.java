package com.diginamic.digihello.service;

import com.diginamic.digihello.domain.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VilleService {

    @PersistenceContext
    private EntityManager entityManager;

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
    }
}