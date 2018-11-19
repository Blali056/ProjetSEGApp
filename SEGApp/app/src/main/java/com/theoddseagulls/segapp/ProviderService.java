package com.theoddseagulls.segapp;

public class ProviderService extends Service{

    private String providerName;

    public ProviderService(){

    }

    public ProviderService(String s1 , String s2){
        super(s2);
        providerName = s1;
    }
    public String getProviderName(){
        return providerName;
    }

    public void setProviderName(String providerName){
        this.providerName = providerName;
    }

}
