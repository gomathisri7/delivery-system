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
// input format
//100 3
//PKG1 5 5 OFR001
    //PKG2 15 5 OFR002
    //PKG3 10 100 OFR003
//2 70 200

//ip2
 //100 5
//    PKG1 50 30 OFR001
//    PKG2 75 125 OFFR0008
//    PKG3 175 100 OFFR003
//    PKG4 110 60 OFR002
//    PKG5 155 95 NA
//2 70 200
    public static DeliverySystem deliverySystem = new DeliverySystem();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the base deliver cost and no of packages");
        ArrayList<Order> orders = new ArrayList<>();

        try {
            double baseCost = sc.nextDouble();
            int noOfPackages = sc.nextInt();
            System.out.println("Enter the line with packageId weight distance and offercode ");

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
            System.out.println("Enter the no of vehicles, max speed and maxweight ");
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
        catch (Exception e)
        {
            System.out.println("Some unexpected error occured - Please retry");
        }
    }
}
