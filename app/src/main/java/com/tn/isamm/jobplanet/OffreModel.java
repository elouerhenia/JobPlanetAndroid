package com.tn.isamm.jobplanet;

/**
 * Created by Rihab on 29/10/2017.
 */

public class OffreModel {
    private String id;
    private String content;

    public OffreModel(String id, String description) {
        this.id = id;
        this.content = description;
    }

    public OffreModel() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String description) {
        this.content = description;
    }

}
