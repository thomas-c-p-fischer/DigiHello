package com.diginamic.digihello.service;

import com.diginamic.digihello.domain.Departement;
import com.diginamic.digihello.domain.Ville;
import com.diginamic.digihello.exceptions.GestionExceptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class VilleServiceTest {

    @Autowired
    VilleService villeService;

    @Test
    public void testInsertExistingVille()  {
        Ville villeTest = new Ville("Paris",2133111, new Departement("Paris","75"));
        assertThrows(GestionExceptions.class, ()->villeService.insertVille(villeTest));
    }

    @Test
    public void testInsertNonExistingVille() throws GestionExceptions {
        Ville villeTest = new Ville("Toulouse",471941, new Departement("Haute-Garonne ","31"));
        assertDoesNotThrow(()->villeService.insertVille(villeTest));
    }
}