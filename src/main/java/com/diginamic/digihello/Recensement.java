package com.diginamic.digihello;

import com.diginamic.digihello.domain.Departement;
import com.diginamic.digihello.domain.Ville;
import com.diginamic.digihello.service.DepartementService;
import com.diginamic.digihello.service.VilleService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class Recensement implements CommandLineRunner {

    @Autowired
    private VilleService villeService;

    @Autowired
    private DepartementService departementService;

    public static void main(String[] args) {
        SpringApplication.run(Recensement.class, args);
    }

    @Bean
    public CommandLineRunner loadData() {
        return (args) -> {
            try {
                chargerVillesCsv();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    @Transactional
    protected void chargerVillesCsv() throws IOException {
        ClassPathResource fichier = new ClassPathResource("cities.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier.getFile()))) {
            String ligne;
            boolean sautDeLigne = true;
            while ((ligne = reader.readLine()) != null) {
                if (sautDeLigne) {
                    sautDeLigne = false;
                    continue;
                }
                String[] donnees = ligne.split((","));
                String nomVille = donnees[3];
                String nomDepartement = donnees[6];
                String codeDepartement = donnees[7];
                Departement departement = new Departement(nomDepartement, codeDepartement);
                Ville ville = new Ville(nomVille, departement);
                villeService.insertVilleCsv(ville);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
