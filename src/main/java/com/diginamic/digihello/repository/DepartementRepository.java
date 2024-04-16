package com.diginamic.digihello.repository;

import com.diginamic.digihello.domain.Departement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartementRepository extends CrudRepository<Departement, Long> {
    Departement findByCode(String code);
    Departement findByNomAndCode(String nom, String code);
}