package com.diginamic.digihello.repository;

import com.diginamic.digihello.domain.Departement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DepartementRepository extends CrudRepository<Departement, Long> {
    Optional<Departement> findByCode(String code);
}