package com.company.deliveryappmodel;

public class Order {
    private Package aPackage;
    private User sender;
    private Offer offerApplied;
    private double destinationDistance; //inKm
    private OrderStatus orderStatus;
    private double deliveryTime; //inHrs
    private double discountApplied;
    private double totalCost;

    public Order(Package aPackage, Offer offerApplied, double destinationDistance, OrderStatus status, double deliveryTime) {
        this.aPackage = aPackage;
       // this.sender = sender;
        this.offerApplied = offerApplied;
        this.destinationDistance = destinationDistance;
        this.orderStatus = status;
        this.deliveryTime = deliveryTime;
    }

    public void updateOrderStatus(OrderStatus status) { this.orderStatus = status; }

    public Package getPackage() { return aPackage; }

    public double getDiscountApplied() { return discountApplied; }

    public void setDiscount(double discount) { this.discountApplied = discount; }

    public double getTotalCost(){ return totalCost; }

    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public User getSender() {
        return sender;
    }

    public Offer getOfferApplied() { return offerApplied; }

    public double getDestinationDistance() {
        return destinationDistance;
    }

    public OrderStatus getOrderStatus(){
        return orderStatus;
    }

    public double getDeliveryTime(){ return deliveryTime; }

    public void setDeliveryTime(double deliveryTime){ this.deliveryTime = deliveryTime; }
}
