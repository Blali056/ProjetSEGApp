package com.theoddseagulls.segapp;
import static org.junit.Assert.*;
import org.junit.Test;

public class Livrable1Test {

    @Test
    public void checkAdminEmail() {
        AdminAccount.getTheaccount("test@email.com", "secret");
        Account anAdminAccount = AdminAccount.getTheaccount();
        assertEquals("Check the email of the account", "test@email.com",  anAdminAccount.getEmail());
    }

    /*
    Ajoute ton code ici Lamya
     */


}
