package com.tn.isamm.jobplanet.model;

public class EntrepriseModel {

    private int id_etr;
    private String nom_ent;
    private int popularite;
    private String siege;
    private String logo;
    private String siteweb;
    private int effectif;
    private String fondation;
    private String secteur_etr;
    private String descreption_etr;
    private String email_etr;
    private String login;
    private String password;
    private int etat_compt_etr;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getNom_ent() {
        return nom_ent;
    }

    public void setNom_ent(String nom_ent) {
        this.nom_ent = nom_ent;
    }


    public EntrepriseModel(int id_etr, String nom_ent, int popularite, String siege, String logo, String siteweb,
                           int effectif, String fondation, String secteur_etr, String descreption_etr, String email_etr, String login,
                           String password, int etat_compt_etr) {
        super();
        this.id_etr = id_etr;
        this.nom_ent = nom_ent;
        this.popularite = popularite;
        this.siege = siege;
        this.logo = logo;
        this.siteweb = siteweb;
        this.effectif = effectif;
        this.fondation = fondation;
        this.secteur_etr = secteur_etr;
        this.descreption_etr = descreption_etr;
        this.email_etr = email_etr;
        this.login = login;
        this.password = password;
        this.etat_compt_etr = etat_compt_etr;
    }

    public EntrepriseModel() {
    }

    public int getId_etr() {
        return id_etr;
    }

    public void setId_etr(int id_etr) {
        this.id_etr = id_etr;
    }

    public int getPopularite() {
        return popularite;
    }

    public void setPopularite(int popularite) {
        this.popularite = popularite;
    }

    public String getSiege() {
        return siege;
    }

    public void setSiege(String siege) {
        this.siege = siege;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSiteweb() {
        return siteweb;
    }

    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
    }

    public int getEffectif() {
        return effectif;
    }

    public void setEffectif(int effectif) {
        this.effectif = effectif;
    }

    public String getFondation() {
        return fondation;
    }

    public void setFondation(String fondation) {
        this.fondation = fondation;
    }

    public String getSecteur_etr() {
        return secteur_etr;
    }

    public void setSecteur_etr(String secteur_etr) {
        this.secteur_etr = secteur_etr;
    }

    public String getDescreption_etr() {
        return descreption_etr;
    }

    public void setDescreption_etr(String descreption_etr) {
        this.descreption_etr = descreption_etr;
    }

    public String getEmail_etr() {
        return email_etr;
    }

    public void setEmail_etr(String email_etr) {
        this.email_etr = email_etr;
    }

    public int getEtat_compt_etr() {
        return etat_compt_etr;
    }

    public void setEtat_compt_etr(int etat_compt_etr) {
        this.etat_compt_etr = etat_compt_etr;
    }


}

