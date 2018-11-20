package com.theoddseagulls.segapp;

public class Account {

    private int id;
    private String email;
    private String password;
    private String username;
    private String type;
    private String address;
    private String phone;
    public String company;
    public String licence;

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

    public void setAddress(String address){
        this.address = address;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setCompany(String company){
        this.company = company;
    }

    public void setLicence(String licence) {
        this.licence = licence;
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

    public String getAddress(){
        return address;
    }

    public String getPhone(){ 
        return phone; 
    }

    public String getCompany() { 
        return company; 
    }

    public String getLicence() { 
        return licence; 
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }
}
