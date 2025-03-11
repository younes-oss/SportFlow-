package com.learn.models;


public class Entraineur extends Utilisateur {
    private String specialite;

    public Entraineur() {}

    public Entraineur(int id, String nom, String email, String motDePasse, String specialite) {
        super(id, nom, email, motDePasse, "entraineur");
        this.specialite = specialite;
    }

    // Getters et Setters
    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }
}
