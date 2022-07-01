package com.company.deliverappcore.test;

import com.company.deliverappcore.main.DeliveryTimeEstimationBizLogic;
import com.company.deliverappcore.main.OfferCodeFactory;
import com.company.deliverappservice.DeliverySystem;
import com.company.deliveryappmodel.Order;
import com.company.deliveryappmodel.OrderStatus;
import com.company.deliveryappmodel.Package;
import com.company.deliveryappmodel.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTimeEstimationBizLogicTest {
    OfferCodeFactory ofrCodeFac = new OfferCodeFactory();
    DeliveryTimeEstimationBizLogic deliveryTimeEstimationBizLogic = new DeliveryTimeEstimationBizLogic();
    DeliverySystem ds = new DeliverySystem();
    @Test
    void findDeliveryTime() {
        Package pack = new Package("PKG4", 110);
        Order order = new Order(pack,ofrCodeFac.createOfferCode("OFR002"),60, OrderStatus.PENDING,0);
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order);
        ds.addVehicles(2);
        ArrayList<Vehicle> vehicles = ds.getVehicles();
        deliveryTimeEstimationBizLogic.findDeliveryTime(orders,vehicles);
        assertEquals(0.85, order.getDeliveryTime());
    }
}