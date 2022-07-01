package com.company.deliverappcore.main;

import com.company.deliveryappmodel.Bike;
import com.company.deliveryappmodel.Vehicle;
import com.company.deliveryappmodel.VehicleStatus;

import java.util.ArrayList;

public class VehicleFactory {

    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    public void addBike(String code, double speed, double capacity)
    {
        vehicles.add(new Bike(code,speed, capacity, VehicleStatus.FREE));
    }

    public ArrayList<Vehicle> getVehicles()
    {
        return  vehicles;
    }
}
