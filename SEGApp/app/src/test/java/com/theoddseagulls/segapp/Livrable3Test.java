package com.theoddseagulls.segapp;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Livrable3Test {

    //To Complete (2 tests)
    @Test
    public void checkProviderMandatoryFields() {
        AdminAccount.getTheaccount("test@email.com", "secret");
        Account anAdminAccount = AdminAccount.getTheaccount();
        assertEquals("Check the email of the admin", "test@email.com",  anAdminAccount.getEmail());
    }
    @Test
    public void checkAddProviderAvailabilities() {
        AdminAccount.getTheaccount("test@email.com", "secret");
        Account anAdminAccount = AdminAccount.getTheaccount();
        assertEquals("Check the email of the admin", "test@email.com",  anAdminAccount.getEmail());
    }
}
