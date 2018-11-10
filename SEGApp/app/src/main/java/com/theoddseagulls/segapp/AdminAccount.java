package com.theoddseagulls.segapp;

import java.util.List;

public class AdminAccount extends Account {

    private static final String type = "Administrateur";
    private List<Service> servicesList;

    private AdminAccount(){
        super();
        super.setType(type);
    }
    private AdminAccount(String e,String p){

        super(e,p);
        super.setType(type);
    }
    private static  AdminAccount theaccount;
    public static  AdminAccount getTheaccount(String e,String p){
         if(theaccount==null){
             theaccount = new AdminAccount( e, p);

         }
     return theaccount;}
    public static  AdminAccount getTheaccount(){
        return theaccount;

    }     public void addService(String service,double rate){
        servicesList.add(new Service(service,rate));
     }
     public void removeService(String service){
        servicesList.remove(service);
     }
     public void changeServiceRate(String service, double newRate){
        servicesList.get(servicesList.indexOf(service)).setRate(newRate);
     }

    public List<Service> getServicesList() {
        return servicesList;
    }
}
