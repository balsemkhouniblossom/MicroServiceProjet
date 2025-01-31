package com.esprit.ms.job;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Job implements Serializable {
    private static final long serialVersionUID = 6;

    @Id
    @GeneratedValue
    public  int id;
    private String nom;
    public String service;
    public Boolean etat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", Service='" + service + '\'' +
                ", Etat=" + etat +
                '}';
    }
    public Job(){}
    public Job(String service) {
        super();
        this.service = service;
    }

    public Job(String service, Boolean etat) {
        this.service = service;
        this.etat = etat;

    }

    public Job(String nom, String service, Boolean etat) {
        this.nom = nom;
        this.service = service;
        this.etat = etat;

    }
}
