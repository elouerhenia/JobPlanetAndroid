package com.tn.isamm.jobplanet.model;

/**
 * Created by Rihab on 03/11/2017.
 */

public class CandidatModel {

    private int candidatId;
    private String nom_cand;
    private String prenom_cand;
    private String adresse_cand;
    private int code_postale;
    private String avatar;
    private int tel_cand;
    private String mail;
    private String password;

    public CandidatModel(int candidatId, String nom_cand, String prenom_cand, String adresse_cand, int code_postale, String avatar, int tel_cand, String mail, String password) {
        this.candidatId = candidatId;
        this.nom_cand = nom_cand;
        this.prenom_cand = prenom_cand;
        this.adresse_cand = adresse_cand;
        this.code_postale = code_postale;
        this.avatar = avatar;
        this.tel_cand = tel_cand;
        this.mail = mail;
        this.password = password;
    }

    public CandidatModel(String nom_cand, String prenom_cand, String adresse_cand, int code_postale, String avatar, int tel_cand, String mail, String password) {
        this.nom_cand = nom_cand;
        this.prenom_cand = prenom_cand;
        this.adresse_cand = adresse_cand;
        this.code_postale = code_postale;
        this.avatar = avatar;
        this.tel_cand = tel_cand;
        this.mail = mail;
        this.password = password;
    }

    public CandidatModel() {

    }

    public int getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(int candidatId) {
        this.candidatId = candidatId;
    }

    public String getNom_cand() {
        return nom_cand;
    }

    public void setNom_cand(String nom_cand) {
        this.nom_cand = nom_cand;
    }

    public String getPrenom_cand() {
        return prenom_cand;
    }

    public void setPrenom_cand(String prenom_cand) {
        this.prenom_cand = prenom_cand;
    }

    public String getAdresse_cand() {
        return adresse_cand;
    }

    public void setAdresse_cand(String adresse_cand) {
        this.adresse_cand = adresse_cand;
    }

    public int getCode_postale() {
        return code_postale;
    }

    public void setCode_postale(int code_postale) {
        this.code_postale = code_postale;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getTel_cand() {
        return tel_cand;
    }

    public void setTel_cand(int tel_cand) {
        this.tel_cand = tel_cand;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}