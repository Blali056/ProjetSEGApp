package com.theoddseagulls.segapp;
import static org.junit.Assert.*;
import org.junit.Test;

public class Livrable2Test {

    /*
    Je vais m'occuper des tests du livrable 2
                                -Brian
     */

    @Test
    public void checkAdminEmail() {
        AdminAccount.getTheaccount("test@email.com", "secret");
        Account anAdminAccount = AdminAccount.getTheaccount();
        assertEquals("Check the email of the admin", "test@email.com",  anAdminAccount.getEmail());
    }
    @Test
    public void checkAdminPass() {
        AdminAccount.getTheaccount("test@email.com", "secret");
        AdminAccount anAdminAccount = AdminAccount.getTheaccount();
        assertEquals("Check the email of the admin", "secret",  anAdminAccount.getPassword());
    }
    @Test
    public void checkProviderAccount() {
        ProviderAccount providerAcc = new ProviderAccount("pro@email.com", "secret");
        assertEquals("Check the email of a provider", "pro@email.com",  providerAcc.getEmail());
    }
    @Test
    public void checkServiceGetService() {
        Service service = new Service("Painting Job", 15);
        assertEquals("Check the name of the service", "Painting Job",  service.getService());
    }
    @Test
    public void checkServiceSetId() {
        Service service = new Service("Painting Job", 15);
        service.setId(1);
        assertSame("Check the price of the service", 1, service.getId());
    }


}
