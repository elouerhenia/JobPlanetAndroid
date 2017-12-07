package com.tn.isamm.jobplanet.model;

import com.tn.isamm.jobplanet.model.CandidatModel;

/**
 * Created by Rihab on 07/12/2017.
 */
public class ExperienceModel {

    private int experienceId;
    private String nom_org;
    private String titrepost;
    private String description_exp;
    private String date_debuEx;
    private String dae_finEx;
    private CandidatModel candidat;

    public int getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(int experienceId) {
        this.experienceId = experienceId;
    }

    public String getNom_org() {
        return nom_org;
    }

    public void setNom_org(String nom_org) {
        this.nom_org = nom_org;
    }

    public String getTitrepost() {
        return titrepost;
    }

    public void setTitrepost(String titrepost) {
        this.titrepost = titrepost;
    }

    public String getDescription_exp() {
        return description_exp;
    }

    public void setDescription_exp(String description_exp) {
        this.description_exp = description_exp;
    }

    public String getDate_debuEx() {
        return date_debuEx;
    }

    public void setDate_debuEx(String date_debuEx) {
        this.date_debuEx = date_debuEx;
    }

    public String getDae_finEx() {
        return dae_finEx;
    }

    public void setDae_finEx(String dae_finEx) {
        this.dae_finEx = dae_finEx;
    }

    public CandidatModel getCandidat() {
        return candidat;
    }

    public void setCandidat(CandidatModel candidat) {
        this.candidat = candidat;
    }


}
