package com.company.deliveryappmodel;

public class User {
    private String userId;
    private String name;
    private String mobileNumber;
    private String emailId;
    private Address address;

    public User(String userId, String name, String mobileNumber, String emailId, Address address) {
        this.userId = userId;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public Address getAddress() {
        return address;
    }
}
