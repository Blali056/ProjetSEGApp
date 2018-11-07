package com.theoddseagulls.segapp;

public class Account {

    private int id;
    private String email;
    private String password;
    private String username;
    private String type;

    public Account(){

    }
    public Account(String email,String pass,String type){
        this.email = email;
        this.password = pass;
        this.type = type ;
    }

    public Account(String email,String pass){
        this.email = email;
        this.password = pass;
    }


    public void setPassword(String a){
        password = a;
    }

    public void setEmail(String b){
        email = b;
    }

    public void setType(String c){
        type = c;
    }

    public void setUsername(String u){
        username = u;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getType(){
        return type;
    }

    public String getUsername(){
        return username;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
