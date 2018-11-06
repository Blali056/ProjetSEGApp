package com.theoddseagulls.segapp;

public class UserAccount extends Account{

    private static final String type = "Utilisateur";

    public UserAccount () {
        super();

        super.setType(type);
    }

    public UserAccount(String e, String p) {
        super(e,p);

        super.setType(type);
    }


}
