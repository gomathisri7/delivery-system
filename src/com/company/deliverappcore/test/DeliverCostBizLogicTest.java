package com.company.deliverappcore.test;

import com.company.deliverappcore.main.DeliverCostBizLogic;
import com.company.deliverappcore.main.OfferCodeFactory;
import com.company.deliveryappmodel.Offer;
import com.company.deliveryappmodel.Order;
import com.company.deliveryappmodel.OrderStatus;
import com.company.deliveryappmodel.Package;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliverCostBizLogicTest {

    DeliverCostBizLogic deliverCostBizLogic = new DeliverCostBizLogic();
    OfferCodeFactory ofrCodeFac = new OfferCodeFactory();
    @Test
    void findDeliveryCost() {
        Package pack = new Package("PKG4", 110);
        Order order = new Order(pack,ofrCodeFac.createOfferCode("OFR002"),60, OrderStatus.PENDING,0);
        deliverCostBizLogic.findDeliveryCost(100, order);
        assertEquals(1395.0,order.getTotalCost());
    }

    @Test
    void getDiscount() {
        Offer of1 = ofrCodeFac.createOfferCode("OFR001");
        assertEquals(10, deliverCostBizLogic.getDiscount(of1,80,150));
        Offer of2 = ofrCodeFac.createOfferCode("OFR002");
        assertEquals(0, deliverCostBizLogic.getDiscount(of2,80,150));
        Offer of3 = ofrCodeFac.createOfferCode("OFR002");
        assertEquals(7, deliverCostBizLogic.getDiscount(of2,180,150));

    }
}