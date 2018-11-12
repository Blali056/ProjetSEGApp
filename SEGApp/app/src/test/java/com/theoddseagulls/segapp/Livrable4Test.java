package com.theoddseagulls.segapp;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Livrable4Test {

    //To complete (10 tests)
    @Test
    public void checkRateService() {
        AdminAccount.getTheaccount("test@email.com", "secret");
        Account anAdminAccount = AdminAccount.getTheaccount();
        assertEquals("Check the email of the admin", "test@email.com",  anAdminAccount.getEmail());
    }
    @Test
    public void checkSearchProvider() {
        AdminAccount.getTheaccount("test@email.com", "secret");
        Account anAdminAccount = AdminAccount.getTheaccount();
        assertEquals("Check the email of the admin", "test@email.com",  anAdminAccount.getEmail());
    }
    @Test
    public void checkBookService() {
        AdminAccount.getTheaccount("test@email.com", "secret");
        Account anAdminAccount = AdminAccount.getTheaccount();
        assertEquals("Check the email of the admin", "test@email.com",  anAdminAccount.getEmail());
    }
    @Test
    public void checkPasswordEncryption() {
        AdminAccount.getTheaccount("test@email.com", "secret");
        Account anAdminAccount = AdminAccount.getTheaccount();
        assertEquals("Check the email of the admin", "secret",  anAdminAccount.getPassword());
    }
    @Test
    public void checkTest5() {
        AdminAccount.getTheaccount("test@email.com", "secret");
        Account anAdminAccount = AdminAccount.getTheaccount();
        assertEquals("Check the email of the admin", "secret",  anAdminAccount.getPassword());
    }
    @Test
    public void checkTest6() {
        AdminAccount.getTheaccount("test@email.com", "secret");
        Account anAdminAccount = AdminAccount.getTheaccount();
        assertEquals("Check the email of the admin", "secret",  anAdminAccount.getPassword());
    }
    @Test
    public void checkTest7() {
        AdminAccount.getTheaccount("test@email.com", "secret");
        Account anAdminAccount = AdminAccount.getTheaccount();
        assertEquals("Check the email of the admin", "secret",  anAdminAccount.getPassword());
    }
    @Test
    public void checkTest8() {
        AdminAccount.getTheaccount("test@email.com", "secret");
        Account anAdminAccount = AdminAccount.getTheaccount();
        assertEquals("Check the email of the admin", "secret",  anAdminAccount.getPassword());
    }
    @Test
    public void checkTest9() {
        AdminAccount.getTheaccount("test@email.com", "secret");
        Account anAdminAccount = AdminAccount.getTheaccount();
        assertEquals("Check the email of the admin", "secret",  anAdminAccount.getPassword());
    }
    @Test
    public void checkTest10() {
        AdminAccount.getTheaccount("test@email.com", "secret");
        Account anAdminAccount = AdminAccount.getTheaccount();
        assertEquals("Check the email of the admin", "secret",  anAdminAccount.getPassword());
    }
}
