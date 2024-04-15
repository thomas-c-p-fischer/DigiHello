package com.diginamic.digihello.service.mapper;

import com.diginamic.digihello.domain.Departement;
import com.diginamic.digihello.domain.Ville;
import com.diginamic.digihello.service.dto.VilleDto;
import com.diginamic.digihello.service.DepartementService;
import org.springframework.stereotype.Component;

@Component
public class VilleMapper {

    DepartementService departementService;

    public VilleDto toDto(Ville ville) {
        VilleDto villeDto = new VilleDto();
        villeDto.setNom(ville.getNom());
        /*villeDto.setNbHabitants(ville.getNbHabitants());*/
        villeDto.setCodeDepartement(ville.getDepartement().getCode());
        return villeDto;
    }

    public Ville toBean(VilleDto villeDto) {
        Ville ville = new Ville();
        Departement departement = new Departement();
        departement.setNom(villeDto.getNomDepartement());
        departement.setCode(villeDto.getCodeDepartement());
        ville.setNom(villeDto.getNom());
/*        ville.setNbHabitants(villeDto.getNbHabitants());*/
        ville.setDepartement(departement);
        return ville;
    }
}