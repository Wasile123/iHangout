package com.example.ihangout.model;

public class UserInformation {

    private String hometown;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public UserInformation() {

    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String username) {
        this.hometown = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
