package com.example.ihangout;

public class Activiti {

    private long id;
    private String title;
    private String city;
    private String address;
    private String date;
    private String phoneNumber;
    private String hostID;
    private String placesLeft;

    public Activiti() {
    }

    public Activiti(long id, String title, String city, String address, String date, String phoneNumber, String hostID,
            String placesLeft) {
        this.id = id;
        this.title = title;
        this.city = city;
        this.address = address;
        this.date = date;
        this.phoneNumber = phoneNumber;
        this.hostID = hostID;
        this.placesLeft = placesLeft;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHostID() {
        return hostID;
    }

    public void setHostID(String hostID) {
        this.hostID = hostID;
    }

    public String getPlacesLeft() {
        return placesLeft;
    }

    public void setPlacesLeft(String placesLeft) {
        this.placesLeft = placesLeft;
    }
}
