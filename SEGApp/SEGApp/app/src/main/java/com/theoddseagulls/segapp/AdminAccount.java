package com.theoddseagulls.segapp;

public class AdminAccount extends Account {

    private static final String type = "Administrateur";

    public AdminAccount(){

        super();
        super.setType(type);
    }

    public AdminAccount(String e,String p){

        super(e,p);
        super.setType(type);
    }
}
