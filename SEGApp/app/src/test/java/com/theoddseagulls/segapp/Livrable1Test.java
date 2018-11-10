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

}