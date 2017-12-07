package com.tn.isamm.jobplanet;

/**
 * Created by odc on 25/08/2016.
 */
public class Spot {


    private String ln;
    private String lat;
    private String ville;
    private String adress;
    private String distance;


    public Spot() {

    }

    public Spot(String ville, String adress, String lat, String ln, String distance) {
        this.ville = ville;
        this.adress = adress;
        this.distance = distance;
        this.lat = lat;
        this.ln = ln;
    }


    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }


    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }


    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }


    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }


    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }


}
