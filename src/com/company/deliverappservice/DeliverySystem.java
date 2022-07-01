package com.company.deliverappservice;

import com.company.deliverappcore.main.DeliverCostBizLogic;
import com.company.deliverappcore.main.DeliveryTimeEstimationBizLogic;
import com.company.deliverappcore.main.OfferCodeFactory;
import com.company.deliveryappmodel.*;
import com.company.deliveryappmodel.Package;

import java.util.ArrayList;

public class DeliverySystem {

    private double maxSpeedOfVehicle;
    private double maxCapacityOfVehicle;
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    public OfferCodeFactory offerCodeFactory = new OfferCodeFactory();
    private DeliverCostBizLogic deliverCostBizLogic = new DeliverCostBizLogic();
    private DeliveryTimeEstimationBizLogic deliveryTimeEstimationBizLogic = new DeliveryTimeEstimationBizLogic();

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setMaxSpeedOfVehicle(double maxSpeedOfVehicle) {
        this.maxSpeedOfVehicle = maxSpeedOfVehicle;
    }

    public void setMaxCapacityOfVehicle(double maxCapacityOfVehicle) {
        this.maxCapacityOfVehicle = maxCapacityOfVehicle;
    }

    public Package createPackage(String packageId, double packWeight)
    {
        return new Package(packageId, packWeight);
    }

    public Order createOrder(Package pack, Offer offer, double destinationDistance, OrderStatus status, double deliveryTime)
    {
        return new Order(pack, offer, destinationDistance, status, deliveryTime);
    }

    public void addOrder(Order order)
    {
        orders.add(order);
    }

    public ArrayList<Vehicle> addVehicles(int noOfVehicles)
    {
        for(int i=0; i<noOfVehicles; i++) {
            String id = "VH"+i;
            vehicles.add(new Bike(id, maxSpeedOfVehicle, maxCapacityOfVehicle, VehicleStatus.FREE));
        }
        return vehicles;
    }

    public Offer createOffer(String offerCode)
    {
        return offerCodeFactory.createOfferCode(offerCode);
    }

    public void findDeliveryCost(double baseCost)
    {
        for (Order order: orders) {
            deliverCostBizLogic.findDeliveryCost(baseCost, order);
        }
    }

    public void findDeliveryTime()
    {
        deliveryTimeEstimationBizLogic.findDeliveryTime(orders, vehicles);
        for(Order order : orders){
            System.out.println(order.getPackage().getId() +" "+ order.getDiscountApplied() + " "+order.getTotalCost()+" "+ order.getDeliveryTime());
        }
    }

}
