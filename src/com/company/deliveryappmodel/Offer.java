package com.company.deliveryappmodel;

public class Offer {
    private String code;
    private double discountPercentage;
    private int startWeight;
    private int endWeight;
    private int startDistance;
    private int endDistance;

    public Offer(String code, double discountPercentage, int startWeight, int endWeight, int startDistance, int endDistance) {
        this.code = code;
        this.discountPercentage = discountPercentage;
        this.startWeight = startWeight;
        this.endWeight = endWeight;
        this.startDistance = startDistance;
        this.endDistance = endDistance;
    }

    public String getCode() {
        return code;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public int getStartWeight() {
        return startWeight;
    }

    public int getEndWeight() {
        return endWeight;
    }

    public int getStartDistance() {
        return startDistance;
    }

    public int getEndDistance() {
        return endDistance;
    }
}
