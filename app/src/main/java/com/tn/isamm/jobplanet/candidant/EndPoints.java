package com.tn.isamm.jobplanet.candidant;

//Classe pour définir les urls des webservices

public class EndPoints {
    public static final String BASE_URL = "http://192.168.1.6:8080";


    public static final String REGISTER_URL = BASE_URL + "/user/candidat";
    public static final String URL_GET_ALL_CANDIDATS = BASE_URL + "/user/all-candidats";
    public static final String URL_AUTH_CAND = BASE_URL + "/user/login";


    public static final String URL_GET_ALL_ENTREPRISES = BASE_URL + "/apientreprise/entreprises";
    public static final String URL_AUTH_REC = BASE_URL + "/apientreprise/login";

    public static final String URL_GET_ONE_CAND = BASE_URL + "/user/getcandidat";
    public static final String URL_UPDATE_CAND = BASE_URL + "/user/candidat";

    public static final String URL_GET_MY_EXPERIENCES = BASE_URL + "/user/candidat";


    //BASE TABLE CANDIDAT
    public static final String KEY_ID_CAND = "candidatId";
    public static final String KEY_NAME_CAND = "nom_cand";
    public static final String KEY_PRENAME_CAND = "prenom_cand";
    public static final String KEY_EMAIL_CAND = "mail";
    public static final String KEY_TEL_CAND = "tel_cand";
    public static final String KEY_PWD_CAND = "password";
    public static final String KEY_ADRESSE_CAND = "adresse_cand";
    public static final String KEY_AVATAR_CAND = "avatar";
    public static final String KEY_CODE_POSTAL_CAND = "code_postale";

    //BASE TABLE ENTREPRISES
    public static final String KEY_ID_ETR = "id_etr";
    public static final String KEY_NOM_ETR = "nom_ent";
    public static final String KEY_POPULARITE_ETR = "popularite";
    public static final String KEY_SIEGE_ETR = "siege";
    public static final String KEY_LOGO_ETR = "logo";
    public static final String KEY_SITE_ETR = "siteweb";
    public static final String KEY_EFFECTIF_ETR = "effectif";
    public static final String KEY_FONDATION_ETR = "fondation";
    public static final String KEY_SECTEUR_ETR = "secteur_etr";
    public static final String KEY_DESCRIPTION_ETR = "descreption_etr";
    public static final String KEY_EMAIL_ETR = "email_etr";
    public static final String KEY_LOGIN_ETR = "login";
    public static final String KEY_PWD_ETR = "password";
    public static final String KEY_ETAT_COMPTE_ETR = "etat_compt_etr";

    //BASE TABLE EXPERIENSE
    public static final String KEY_EXP_ID = "experienceId";
    public static final String KEY_EXP_NOM_ORG = "nom_org";
    public static final String KEY_EXP_TITRE = "titrepost";
    public static final String KEY_EXP_DESCRIPTION = "description_exp";
    public static final String KEY_EXP_DATE_DEBUT = "date_debuEx";
    public static final String KEY_EXP_DATE_FIN = "dae_finEx";
    public static final String KEY_EXP_ID_CAND = "candidat";


    //BASE TABLE OFFRES
    //BASE TABLE CV
    //BASE TABLE LETTRE MOTIVATION

}