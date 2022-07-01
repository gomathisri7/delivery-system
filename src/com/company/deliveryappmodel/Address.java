package com.company.deliveryappmodel;

public class Address {
    private String id;
    private String line1;
    private String line2;
    private String city;
    private String pincode;
    private Location location;

    public Address(String id, String line1, String line2, String city, String pincode, Location location) {
        this.id = id;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.pincode = pincode;
        this.location = location;
    }

    public String getId() {
        return id;
    }
    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getCity() {
        return city;
    }

    public String getPincode() {
        return pincode;
    }

    public Location getLocation() {
        return location;
    }

}
