package com.company.deliverappcore.main;

import com.company.deliveryappmodel.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class DeliveryTimeEstimationBizLogic {

    private double maxSpeed = 70;
    WeightComparator weightComparator = new WeightComparator();
    VehicleComparator vehicleComparator = new VehicleComparator();
    TreeMap<Double,ArrayList<Order>> sumPackageMap = new TreeMap<Double,ArrayList<Order>>(Collections.reverseOrder());
    private final DecimalFormat dfZero = new DecimalFormat("0.00");

    public void findDeliveryTime(ArrayList<Order> orders, ArrayList<Vehicle> vehicles)
    {
        Collections.sort(orders, weightComparator);
        sumUp(orders,200);
        shipOrders(sumPackageMap, vehicles);
    }

    public void shipOrders(TreeMap<Double,ArrayList<Order>> map, ArrayList<Vehicle> vehicles)
    {
        for(Map.Entry<Double, ArrayList<Order>> entry : map.entrySet())
        {
            ArrayList<Order> orders = entry.getValue();
            Collections.sort(vehicles, vehicleComparator);
            Vehicle vehicle = vehicles.get(0);
            if(!isShipped(orders))
            {
                final double[] maxtime = {0};
                orders.forEach(
                        t-> {
                            t.updateOrderStatus(OrderStatus.PROCESSING);
                            dfZero.setRoundingMode(RoundingMode.DOWN);
                            double deliverTime = Double.parseDouble(dfZero.format(getDeliveryTime(maxSpeed, t.getDestinationDistance())));
                            double dummy = Double.parseDouble(dfZero.format(vehicle.getAvailableTime()+ deliverTime));
                            t.setDeliveryTime(Double.parseDouble(dfZero.format(vehicle.getAvailableTime() + deliverTime)));
                            if(maxtime[0] < deliverTime)
                                maxtime[0] = deliverTime;
                        }
                );
                vehicle.setAvailableTime(vehicle.getAvailableTime() + (2*maxtime[0]));
            }
        }
    }

    private double getDeliveryTime(double speed, double distance)
    {
        return distance/speed;
    }

    private boolean isShipped(ArrayList<Order> orders)
    {
        for(Order order : orders)
        {
            if(order.getOrderStatus() != OrderStatus.PENDING)
                return true;
        }
        return false;
    }

     void sumUpRecursive(ArrayList<Order> orders, int target, ArrayList<Order> partial) {
        double sum = 0;
        for (Order x: partial) sum += x.getPackage().getWeight();
        if (sum <= target) {
            sumPackageMap.put(sum,partial);
        }
        if (sum > target)
            return;
        for(int i=0; i<orders.size(); i++) {
            ArrayList<Order> remaining = new ArrayList<Order>();
            Order n = orders.get(i);
            for (int j=i+1; j<orders.size();j++) remaining.add(orders.get(j));
            ArrayList<Order> partial_rec = new ArrayList<Order>(partial);
            partial_rec.add(n);
            sumUpRecursive(remaining,target,partial_rec);
        }
    }

    void sumUp(ArrayList<Order> orders, int target) {
        sumUpRecursive(orders,target,new ArrayList<Order>());
    }

}

 class WeightComparator implements Comparator<Order> {
    public int compare(Order o1, Order o2) {
        if (o1.getPackage().getWeight() == o2.getPackage().getWeight())
            return 0;
        else if (o1.getPackage().getWeight() > o2.getPackage().getWeight())
            return 1;
        else
            return -1;
    }
}

class VehicleComparator implements Comparator<Vehicle> {
    public int compare(Vehicle v1, Vehicle v2) {
        if (v1.getAvailableTime() == v2.getAvailableTime())
            return 0;
        else if (v1.getAvailableTime() > v2.getAvailableTime())
            return 1;
        else
            return -1;
    }
}