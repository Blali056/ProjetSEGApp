package com.theoddseagulls.segapp;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Livrable4Test {

    //To complete (10 tests)
    @Test
    public void checkBooking() {
        UserAccount userAccount = new UserAccount("email", "password");
        userAccount.setAppointment("appointment");
        assertEquals("Check User Booking", "Fournisseur",  userAccount.getAppointment());
    }
    @Test
    public void checkRate() {
        ProviderAccount providerAccount = new ProviderAccount("email", "password");
        providerAccount.setRate(2);
        assertEquals("Check Provider Rate", 2,  providerAccount.getRate());
    }
    @Test
    public void checkTauxHoraire() {
        Service service = new Service("serviceName", 120);
        service.setTauxHoraire(200);
        assertEquals("Check ServiceTauxHoraire", 200,  service.getTauxHoraire());
    }
    public void checkServiceName() {
        Service service = new Service("serviceName", 120);
        service.setService("newServiceName");
        assertEquals("Check ServiceName", "serviceName",  service.getService());
    }
    public void checkServiceId() {
        Service service = new Service("serviceName", 120);
        service.setId(1);
        assertEquals("Check ServiceId", 1,  service.getId());
    }
    public void checkProviderDay1() {
        ProviderAccount provider = new ProviderAccount("providerAccount", "provider");
        provider.setLundi("test");
        assertEquals("Check ServiceId", "test",  provider.getLundi());
    }
    public void checkProviderDay2() {
        ProviderAccount provider = new ProviderAccount("providerAccount", "provider");
        provider.setMardi("test");
        assertEquals("Check ServiceId", "test",  provider.getMardi());
    }
    public void checkProviderDay3() {
        ProviderAccount provider = new ProviderAccount("providerAccount", "provider");
        provider.setMercredi("test");
        assertEquals("Check ServiceId", "test",  provider.getMercredi());
    }
    public void checkProviderDay4() {
        ProviderAccount provider = new ProviderAccount("providerAccount", "provider");
        provider.setJeudi("test");
        assertEquals("Check ServiceId", "test",  provider.getJeudi());
    }
    public void checkProviderDay5() {
        ProviderAccount provider = new ProviderAccount("providerAccount", "provider");
        provider.setVendredi("test");
        assertEquals("Check ServiceId", "test",  provider.getVendredi());
    }
}
