package com.theoddseagulls.segapp;

public class UserAccount extends Account{

    private int id;
    private String email;
    private String password;
    private String username;
    private String type;


    public UserAccount(){

        super();

    }

    public UserAccount(String e, String p, String t){
        super(e , p , t);
    }

    public UserAccount(String e, String p){
        super(e,p);
    }


}
