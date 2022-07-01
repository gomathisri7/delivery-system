package com.company.deliveryappmodel;

public class Package {

    private String id;
    private double weight; //inKg

    public Package(String id, double weight) {
        this.id = id;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

}
