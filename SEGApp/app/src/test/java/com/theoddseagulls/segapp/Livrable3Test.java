package com.theoddseagulls.segapp;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Livrable3Test {

    //To Complete (2 tests)
    @Test
    public void checkProviderType() {
        ProviderAccount providerAccount = new ProviderAccount("test@provider.com", "secret");
        assertEquals("Check the type of the Provider", "Fournisseur",  providerAccount.getType());
    }
    @Test
    public void checkProviderPassword() {
        ProviderAccount providerAccount = new ProviderAccount("test@provider.com", "secret");
        assertEquals("Check the password of the Provider", "secret",  providerAccount.getPassword());
    }
}
