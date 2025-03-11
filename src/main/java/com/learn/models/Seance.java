package com.learn.models;


import java.util.Date;

public class Seance {
    private int id;
    private int idMembre;
    private int idEntraineur;
    private Date dateHeure;

    public Seance() {}

    public Seance(int id, int idMembre, int idEntraineur, Date dateHeure) {
        this.id = id;
        this.idMembre = idMembre;
        this.idEntraineur = idEntraineur;
        this.dateHeure = dateHeure;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdMembre() { return idMembre; }
    public void setIdMembre(int idMembre) { this.idMembre = idMembre; }

    public int getIdEntraineur() { return idEntraineur; }
    public void setIdEntraineur(int idEntraineur) { this.idEntraineur = idEntraineur; }

    public Date getDateHeure() { return dateHeure; }
    public void setDateHeure(Date dateHeure) { this.dateHeure = dateHeure; }
}
