package com.diginamic.digihello.repository;

import com.diginamic.digihello.domain.Departement;
import com.diginamic.digihello.domain.Ville;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VilleRepository extends CrudRepository<Ville, Long> {

    Optional<Ville> findByNom(String nom);

    Optional<Ville> findByNomAndDepartement(String nom, Departement departement);

    List<Ville> findByNomStartingWith(String nom);

    /*List<Ville> findByNbHabitantsGreaterThan(int nbHabitantsMin);

    List<Ville> findByNbHabitantsBetween(int nbHabitantsMin, int nbHabitantsMax);

    List<Ville> findByDepartementCodeAndNbHabitantsGreaterThan(String departementCode, int nbHabitants);

    List<Ville> findByDepartementCodeAndNbHabitantsBetween(String departementCode, int nbHabitants, int nbHabitants2);*/

   /* Page<Ville> findVillesByDepartementCodeOrderByNbHabitantsDesc(String departementCode, Pageable pageable);*/
}