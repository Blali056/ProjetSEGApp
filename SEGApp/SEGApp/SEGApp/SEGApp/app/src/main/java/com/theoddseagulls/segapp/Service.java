package com.theoddseagulls.segapp;
import java.util.List;

public class Service {
    private String service;
    private double rate;
    private int id;
    public  Service(String service, double rate){
        this.service=service;
        this.rate=rate;

    }
    public Service(){}

    public double getRate() {
        return rate;
    }

    public String getService() {
        return service;
    }

    public void setRate(double rate) {
        this.rate = rate;
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
