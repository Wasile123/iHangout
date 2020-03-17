package com.example.ihangout.model;

import junit.framework.TestCase;

import org.junit.Test;

public class ActivitiTest extends TestCase {
    @Test
    public void testGetId() {
        Activiti activiti = new Activiti(1,"title","cluj","Tatra","20.02.2020","0787345025","222","place1");
        assertEquals(1,activiti.getId());
    }
    @Test
    public void testSetId() {
        Activiti activiti = new Activiti(1, "title", "cluj", "Tatra", "20.02.2020", "0787345025", "222", "place1");
        activiti.setId(2);
        assertEquals(2,activiti.getId());
    }
    @Test

    public void testGetTitle() {
        Activiti activiti = new Activiti(1,"title","cluj","Tatra","20.02.2020","0787345025","222","place1");
        assertEquals("title",activiti.getTitle());
    }
    @Test

    public void testSetTitle() {
        Activiti activiti = new Activiti(1,"title","cluj","Tatra","20.02.2020","0787345025","222","place1");
        activiti.setTitle("title2");
        assertEquals("title2",activiti.getTitle());
    }
    @Test

    public void testGetCity() {
        Activiti activiti = new Activiti(1,"title","cluj","Tatra","20.02.2020","0787345025","222","place1");
        assertEquals("cluj",activiti.getCity());
    }
    @Test

    public void testSetCity() {
        Activiti activiti = new Activiti(1,"title","cluj","Tatra","20.02.2020","0787345025","222","place1");
        activiti.setCity("Sibiu");
        assertEquals("Sibiu",activiti.getCity());

    }
    @Test

    public void testGetAddress() {
        Activiti activiti = new Activiti(1,"title","cluj","Tatra","20.02.2020","0787345025","222","place1");
        assertEquals("Tatra",activiti.getAddress());

    }
    @Test

    public void testSetAddress() {
        Activiti activiti = new Activiti(1,"title","cluj","Tatra","20.02.2020","0787345025","222","place1");
        activiti.setAddress("Balcescu");
        assertEquals("Balcescu",activiti.getAddress());

    }
    @Test

    public void testGetDate() {
        Activiti activiti = new Activiti(1,"title","cluj","Tatra","20.02.2020","0787345025","222","place1");

        assertEquals("20.02.2020",activiti.getDate());

    }
    @Test

    public void testSetDate() {
        Activiti activiti = new Activiti(1,"title","cluj","Tatra","20.02.2020","0787345025","222","place1");
        activiti.setDate("21.21.2020");
        assertEquals("21.21.2020",activiti.getDate());

    }
    @Test

    public void testGetPhoneNumber() {
        Activiti activiti = new Activiti(1,"title","cluj","Tatra","20.02.2020","0787345025","222","place1");
        assertEquals("0787345025",activiti.getPhoneNumber());

    }
    @Test

    public void testSetPhoneNumber() {
        Activiti activiti = new Activiti(1,"title","cluj","Tatra","20.02.2020","0787345025","222","place1");
        activiti.setPhoneNumber("1111111111");
        assertEquals("1111111111",activiti.getPhoneNumber());

    }
    @Test

    public void testGetHostID() {
        Activiti activiti = new Activiti(1,"title","cluj","Tatra","20.02.2020","0787345025","222","place1");
        assertEquals("222",activiti.getHostID());

    }
    @Test

    public void testSetHostID() {
        Activiti activiti = new Activiti(1,"title","cluj","Tatra","20.02.2020","0787345025","222","place1");
        activiti.setHostID("123");
        assertEquals("123",activiti.getHostID());

    }
    @Test

    public void testGetPlacesLeft() {
        Activiti activiti = new Activiti(1,"title","cluj","Tatra","20.02.2020","0787345025","222","place1");
        assertEquals("place1",activiti.getPlacesLeft());

    }
    @Test

    public void testSetPlacesLeft() {
        Activiti activiti = new Activiti(1,"title","cluj","Tatra","20.02.2020","0787345025","222","place1");
       activiti.setPlacesLeft("place2");
        assertEquals("place2",activiti.getPlacesLeft());

    }
}