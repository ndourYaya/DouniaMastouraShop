package com.douniamastoura.macndour.DmModels;

import java.util.ArrayList;

/**
 * Created by macndour on 16/02/15.
 */
public class DmArticlesModel {

    private int Id;
    private String Nom;
    private double Prix;
    private String Description;
    private int Quantite;
    private ArrayList<String> Couleurs;
    private ArrayList<String> Tailles;
    private ArrayList<String> Photos;

    public ArrayList<String> getPhotos() {
        return Photos;
    }

    public void setPhotos(ArrayList<String> photos) {
        Photos = photos;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public double getPrix() {
        return Prix;
    }

    public void setPrix(double prix) {
        Prix = prix;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int quantite) {
        Quantite = quantite;
    }

    public ArrayList<String> getCouleurs() {
        return Couleurs;
    }

    public void setCouleurs(ArrayList<String> couleurs) {
        Couleurs = couleurs;
    }

    public ArrayList<String> getTailles() {
        return Tailles;
    }

    public void setTailles(ArrayList<String> tailles) {
        Tailles = tailles;
    }
}
