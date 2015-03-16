package com.douniamastoura.macndour.DmModels;

/**
 * Created by macndour on 15/02/15.
 */
public class DmCategoriesModel {

    private int Id;
    private String Nom;
    private String Photo;

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

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }
}
