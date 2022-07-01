package com.company.deliverappservice;

import com.company.deliverappcore.main.DeliverCostBizLogic;
import com.company.deliverappcore.main.DeliveryTimeEstimationBizLogic;
import com.company.deliverappcore.main.OfferCodeFactory;
import com.company.deliveryappmodel.Order;
import com.company.deliveryappmodel.OrderStatus;
import com.company.deliveryappmodel.Package;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DeliveryService {
//PKG1 5 5 OFR001
    //PKG2 15 5 OFR002
    //PKG3 10 100 OFR003
//    PKG1 50 30 OFR001
//    PKG2 75 125 OFFR0008
//    PKG3 175 100 OFFR003
//    PKG4 110 60 OFFR002
//    PKG5 155 95 NA
    public static DeliveryTimeEstimationBizLogic dTBizLogic = new DeliveryTimeEstimationBizLogic();
    public static DeliverCostBizLogic deliverCostBizLogic = new DeliverCostBizLogic();
    public static OfferCodeFactory offerCodeFactory = new OfferCodeFactory();
    public static DeliverySystem deliverySystem = new DeliverySystem();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the base deliver cost and no of packages");
        ArrayList<Order> orders = new ArrayList<>();

        try {
            double baseCost = sc.nextDouble();
            int noOfPackages = sc.nextInt();

            for (int i = 0; i < noOfPackages; i++) {
                Scanner sc2 = new Scanner(System.in);
                String input = sc2.nextLine();
                String[] inputs = input.split(" ");

                String packageId = inputs[0];
                double packageWeight = Double.parseDouble(inputs[1]);
                double destinationDistance = Double.parseDouble(inputs[2]);
                String offerCode = inputs[3];


                Package pack = deliverySystem.createPackage(packageId, packageWeight);
                Order order = deliverySystem.createOrder(pack, deliverySystem.createOffer(offerCode), destinationDistance, OrderStatus.PENDING, 0);
                deliverySystem.addOrder(order);

            }

            int noOfVehicles = sc.nextInt();
            double maxSpeed = sc.nextDouble();
            double maxWeight = sc.nextDouble();
            deliverySystem.addVehicles(noOfVehicles);
            deliverySystem.findDeliveryCost(baseCost);
            deliverySystem.setMaxSpeedOfVehicle(maxSpeed);
            deliverySystem.setMaxCapacityOfVehicle(maxWeight);
            deliverySystem.findDeliveryTime();

        }
        catch (InputMismatchException e)
        {
            System.out.println("Invalid Input Type : Please enter input in proper format");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Invalid Offer Code : Please enter some other Offer Code");
        }
    }
}
