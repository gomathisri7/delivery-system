package com.company.deliveryappmodel;

public class Vehicle {

    private String number;
    private double speed; //kmperHr
    private double capacity; //inKg
    private VehicleStatus status;
    private double availableTime;


    public Vehicle(String number, double speed, double capacity, VehicleStatus status) {
        this.number = number;
        this.speed = speed;
        this.capacity = capacity;
        this.status = status;
        this.availableTime = 0;
    }

    public String getNumber() {
        return number;
    }

    public VehicleStatus getStatus() { return status; }

    public double getSpeed() {
        return speed;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getAvailableTime() {  return availableTime; }

    public void setAvailableTime(double availableTime) { this.availableTime = availableTime; }

}
