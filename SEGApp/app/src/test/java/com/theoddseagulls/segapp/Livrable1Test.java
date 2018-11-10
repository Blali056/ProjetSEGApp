package com.theoddseagulls.segapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class Livrable1Test {
    @Test
    public void checkUserEmail() {
        UserAccount user = new UserAccount("emailTest","passwordTest");
        assertEquals("check email de l'utilisateur" ,"emailTest",user.getEmail());
    }
    @Test
    public void checkUserSetId() {
        UserAccount user = new UserAccount("emailTest2","passwordTest2");
        user.setId(12);
        assertEquals("check id de l'utilisateur" ,12 ,user.getId());
    }
    @Test
    public void checkUserGetPassword() {
        UserAccount user = new UserAccount("emailTest","passwordTest");
        assertEquals("check le mot de passe de l'utilisateur" ,"passwordTest",user.getPassword());
    }

}