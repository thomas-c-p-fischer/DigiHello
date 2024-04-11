package com.diginamic.digihello.service;
import com.diginamic.digihello.domain.Departement;
import com.diginamic.digihello.domain.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

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
}