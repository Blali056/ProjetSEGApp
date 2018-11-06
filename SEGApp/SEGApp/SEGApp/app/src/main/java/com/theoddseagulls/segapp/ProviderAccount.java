package com.theoddseagulls.segapp;

public class ProviderAccount extends Account {

    private static final String type = "Fournisseur";

    public ProviderAccount(){

        super();

        super.setType(type);
    }

    public ProviderAccount(String e,String p){

        super(e,p);

        super.setType(type);
    }

   


}
