package com.theoddseagulls.segapp;


public class AdminAccount extends Account {

    private static final String type = "Administrateur";

    public AdminAccount(){
        super();
        super.setType(type);
    }

    private AdminAccount(String email,String pass){

        super(email, pass);
        super.setType(type);
    }
    private static  AdminAccount theaccount;
    public static  AdminAccount getTheaccount(String email,String pass){
         if(theaccount==null){
             theaccount= new AdminAccount(email, pass);

         }
     return theaccount;}

    public static  AdminAccount getTheaccount(){
        return theaccount;

    }

}
