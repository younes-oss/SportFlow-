package com.learn.models;


import java.util.Date;

public class Membre extends Utilisateur {
    private Date dateNaissance;
    private String sport;

    public Membre() {}

    public Membre(int id, String nom, String email, String motDePasse, Date dateNaissance, String sport) {
        super(id, nom, email, motDePasse, "membre");
        this.dateNaissance = dateNaissance;
        this.sport = sport;
    }

    // Getters et Setters
    public Date getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }

    public String getSport() { return sport; }
    public void setSport(String sport) { this.sport = sport; }
}
