package com.diginamic.digihello.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    /*private int nbHabitants;*/

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "departement_id")
    private Departement departement;

    public Ville(String nom/*, int nbHabitants*/, Departement departement) {
        this.nom = nom;
        /*this.nbHabitants = nbHabitants;*/
        this.departement = departement;
    }

    public Ville() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    /*@JsonProperty("nbHabitants")
    public int getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(int nbHabitants) {
        this.nbHabitants = nbHabitants;
    }*/

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}