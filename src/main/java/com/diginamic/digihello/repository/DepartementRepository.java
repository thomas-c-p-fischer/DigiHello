package com.diginamic.digihello.repository;

import com.diginamic.digihello.domain.Departement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartementRepository extends CrudRepository<Departement, Long> {
    Optional<Departement> findByCode(String code);
}