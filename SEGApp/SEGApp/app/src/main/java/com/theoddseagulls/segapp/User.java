package com.theoddseagulls.segapp;

public class User {
    int id;
    private String email;
    private String password;
    private String type;
    public User(String e,String p,String t){
        email=e;
        password=p;
        type=t;
    }
    public User(String e,String p){
        email=e;
        password=p;
    }

    public void setPassword(String a){
        password=a;
    }
    public void setEmail(String a){
        email=a;
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
    public void setId(int id){
        this.id=id;

    }
    public int getId(){
        return id;
    }
}
