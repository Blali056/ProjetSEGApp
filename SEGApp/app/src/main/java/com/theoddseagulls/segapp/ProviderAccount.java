package com.theoddseagulls.segapp;

public class ProviderAccount extends Account {

    private static final String type = "Fournisseur";

    public ProviderAccount(){

        super();

        super.setType(type);
    }

    public ProviderAccount(String email,String pass){

        super(email,pass);

        super.setType(type);
    }

   


}
