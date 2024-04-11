package com.diginamic.digihello.domain.mapper;

import com.diginamic.digihello.domain.Departement;
import com.diginamic.digihello.domain.Ville;
import com.diginamic.digihello.domain.dto.VilleDto;
import com.diginamic.digihello.service.DepartementService;

public class VilleMapper {


    DepartementService departementService;

    public VilleDto toDto(Ville ville) {
        VilleDto villeDto = new VilleDto();
        villeDto.setNom(ville.getNom());
        villeDto.setNbHabitants(ville.getNbHabitants());
        villeDto.setCodeDepartement(ville.getDepartement().getCode());
        return villeDto;
    }

    public Ville toBean(VilleDto villeDto) {
        Ville ville = new Ville();
        Departement departement = departementService.getDepartementByCode(villeDto.getCodeDepartement());
        ville.setNom(villeDto.getNom());
        ville.setNbHabitants(villeDto.getNbHabitants());
        ville.setDepartement(departement);
        return ville;
    }
}