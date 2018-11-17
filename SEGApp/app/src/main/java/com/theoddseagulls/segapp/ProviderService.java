package com.theoddseagulls.segapp;

public class ProviderService {
    private String providerName;
    private String serviceName;
    public ProviderService(String s1 , String s2){
            providerName = s1;
            serviceName = s2;
    }
    public String getProviderName(){
        return providerName;
    }
    public String getServiceName(){
        return serviceName;
    }
}
