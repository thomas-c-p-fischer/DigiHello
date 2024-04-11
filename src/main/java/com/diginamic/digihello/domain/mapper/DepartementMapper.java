package com.diginamic.digihello.domain.mapper;

import com.diginamic.digihello.domain.Departement;
import com.diginamic.digihello.domain.dto.DepartementDto;

public class DepartementMapper {

    public DepartementDto toDto(Departement departement) {
        DepartementDto departementDto = new DepartementDto();
        departementDto.setCode(departement.getCode());
        departementDto.setNom(departement.getNom());
        return departementDto;
    }

    public Departement toBean(DepartementDto departementDto) {
        Departement departement =new Departement();
        departement.setCode(departementDto.getCode());
        departement.setNom(departementDto.getNom());
        return departement;
    }
}