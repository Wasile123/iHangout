package com.example.ihangout.model;

import junit.framework.TestCase;

import org.junit.Test;

public class UserInformationTest extends TestCase {
    @Test
    public void testGetHometown() {
        UserInformation userInformation= new UserInformation("cluj","email","firsname","lastname","phone");
        assertEquals("cluj",userInformation.getHometown());
    }
    @Test
    public void testSetHometown() {
        UserInformation userInformation= new UserInformation("cluj","email","firsname","lastname","phone");
        userInformation.setHometown("sibiu");
        assertEquals("sibiu",userInformation.getHometown());
    }

    @Test
    public void testGetEmail() {
        UserInformation userInformation= new UserInformation("cluj","email","firsname","lastname","phone");
        assertEquals("email",userInformation.getEmail());

    }

    @Test
    public void testSetEmail() {
        UserInformation userInformation= new UserInformation("cluj","email","firsname","lastname","phone");
        userInformation.setEmail("email2");
        assertEquals("email2",userInformation.getEmail());

    }

    @Test
    public void testGetFirstName() {
        UserInformation userInformation= new UserInformation("cluj","email","firsname","lastname","phone");
        assertEquals("firsname",userInformation.getFirstName());

    }

    @Test
    public void testSetFirstName() {
        UserInformation userInformation= new UserInformation("cluj","email","firsname","lastname","phone");
        userInformation.setFirstName("firstname2");
        assertEquals("firstname2",userInformation.getFirstName());

    }

    @Test
    public void testGetLastName() {
        UserInformation userInformation= new UserInformation("cluj","email","firsname","lastname","phone");
        assertEquals("lastname",userInformation.getLastName());

    }

    @Test
    public void testSetLastName() {
        UserInformation userInformation= new UserInformation("cluj","email","firsname","lastname","phone");
        userInformation.setLastName("last2");
        assertEquals("last2",userInformation.getLastName());

    }

    @Test
    public void testGetPhoneNumber() {
        UserInformation userInformation= new UserInformation("cluj","email","firsname","lastname","phone");
        assertEquals("phone",userInformation.getPhoneNumber());

    }

    @Test
    public void testSetPhoneNumber() {
        UserInformation userInformation= new UserInformation("cluj","email","firsname","lastname","phone");
        userInformation.setPhoneNumber("fix");
        assertEquals("fix",userInformation.getPhoneNumber());

    }
}