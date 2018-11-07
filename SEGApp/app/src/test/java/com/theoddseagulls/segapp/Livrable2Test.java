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
        //assertEquals("Check the pass of the admin", "secret",  anAdminAccount.getPassword());
    }


}
