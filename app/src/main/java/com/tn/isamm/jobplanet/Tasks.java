package com.tn.isamm.jobplanet;


// model taches qu'on va l'utiliser lors du parsing du json
public class Tasks {
    private int id_task;
    private String titre_task;
    private String description_task;
    private String date_task;
    private String heure_task;
    private String couleur_task;
    private String auteurNom_task;
    private String auteurPrenom_task;

    public int getId_task() {
        return id_task;
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }

    public String getTitre_task() {
        return titre_task;
    }

    public void setTitre_task(String titre_task) {
        this.titre_task = titre_task;
    }

    public String getDescription_task() {
        return description_task;
    }

    public void setDescription_task(String description_task) {
        this.description_task = description_task;
    }

    public String getDate_task() {
        return date_task;
    }

    public void setDate_task(String date_task) {
        this.date_task = date_task;
    }

    public String getHeure_task() {
        return heure_task;
    }

    public void setHeure_task(String heure_task) {
        this.heure_task = heure_task;
    }

    public String getCouleur_task() {
        return couleur_task;
    }

    public void setCouleur_task(String couleur_task) {
        this.couleur_task = couleur_task;
    }

    public String getAuteurNom_task() {
        return auteurNom_task;
    }

    public void setAuteurNom_task(String auteurNom_task) {
        this.auteurNom_task = auteurNom_task;
    }

    public String getAuteurPrenom_task() {
        return auteurPrenom_task;
    }

    public void setAuteurPrenom_task(String auteurPrenom_task) {
        this.auteurPrenom_task = auteurPrenom_task;
    }
}

