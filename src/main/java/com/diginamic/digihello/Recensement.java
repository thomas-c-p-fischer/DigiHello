package com.diginamic.digihello;

import com.diginamic.digihello.domain.Departement;
import com.diginamic.digihello.domain.Ville;
import com.diginamic.digihello.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class Recensement implements CommandLineRunner {

    @Autowired
    private VilleService villeService;

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

    private void chargerVillesCsv() throws IOException {
        ClassPathResource fichier = new ClassPathResource("cities.csv");
        BufferedReader reader = new BufferedReader(new FileReader(fichier.getFile()));
        String ligne;
        boolean sautDeLigne = true;
        while((ligne = reader.readLine()) != null) {
            if (sautDeLigne) {
                sautDeLigne = false;
                continue;
            }
            String[] donnees = ligne.split((","));
            String nomVille = donnees[3];
            Departement departement = new Departement(donnees[6], donnees[7]);
            Ville ville = new Ville(nomVille, departement);
            villeService.insertVilleCsv(ville);
        }
        reader.close();
    }

    @Override
    public void run(String... args) throws Exception {

    }
}