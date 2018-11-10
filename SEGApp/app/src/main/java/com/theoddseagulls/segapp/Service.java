package com.theoddseagulls.segapp;

public class Service {

    private String service;
    private double tauxHoraire;
    private int id;

    public  Service(String service, double tauxHoraire) {
        this.service=service;
        this.tauxHoraire = tauxHoraire;
    }

    public Service(){}

    public Service(String service){
        this.service=service;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
