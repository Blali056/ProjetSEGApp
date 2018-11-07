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
        Account anAdminAccount = new AdminAccount("test@email.com", 180);
        assertEquals("Check the email of the account", "test@email.com",  anAdminAccount.getEmail());
    }


}
