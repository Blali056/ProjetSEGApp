package com.theoddseagulls.segapp;

import java.util.ArrayList;

public class ProviderAccount extends Account {

    private static final String type = "Fournisseur";
    private ArrayList<String> serviceList;

    public ProviderAccount(){

        super();
        super.setType(type);
        serviceList = new ArrayList<>();


    }

    public ProviderAccount(String email,String pass){

        super(email, pass);
        super.setType(type);
        serviceList = new ArrayList<>();

    }
    public void addService(String service){

        serviceList.add(service);
    }
   public void removeService(String service){
        serviceList.remove(service);
   }
   public boolean serviceExist(String service){
        return serviceList.contains(service);
   }
   public ArrayList<String> providerServiceList(){
        return serviceList;
   }
}
