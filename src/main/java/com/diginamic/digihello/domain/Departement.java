package com.diginamic.digihello.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Departement {

    @Id
    @GeneratedValue
    private Long id;

    private String nom;

    private String code;

    @OneToMany(mappedBy = "departement")
    private List<Ville> villes;

    public Departement() {
    }

    public Departement(String nom, String code) {
        this.nom = nom;
        this.code = code;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }
}
