package com.company.deliverappcore.main;

import com.company.deliveryappmodel.Offer;
import com.company.deliveryappmodel.Order;

import java.text.DecimalFormat;

import static com.company.deliverappcore.main.Constants.COSTPERKG;
import static com.company.deliverappcore.main.Constants.COSTPERKM;

public class DeliverCostBizLogic {

    private final DecimalFormat dfZero = new DecimalFormat("0.00");

    public void findDeliveryCost(double baseDeliveryCost, Order order) {
        try {
            double deliverCost = Double.parseDouble(dfZero.format(baseDeliveryCost + (order.getPackage().getWeight() * COSTPERKG) + (order.getDestinationDistance() * COSTPERKM)));
            double discount = Double.parseDouble(dfZero.format((getDiscount(order.getOfferApplied(), order.getPackage().getWeight(), order.getDestinationDistance()) / 100) * deliverCost));
            order.setDiscount(discount);
            order.setTotalCost(deliverCost - discount);
            System.out.println(order.getPackage().getId() + " " + discount + " " + (deliverCost - discount));
        }
        catch (Exception e)
        {
            System.out.printf("Unexpected error occured");
        }
    }

    public double getDiscount(Offer offer, double packWeight, double distance)
    {
        try {
            if (isValidOffercode(offer, packWeight, distance)) {
                return offer.getDiscountPercentage();
            } else {
                return 0.0;
            }
        }
        catch (Exception e)
        {
            System.out.println("Unexpected error occured");
        }
        return 0.0;
    }

    public boolean isValidOffercode(Offer offer, double packWeight, double distance)
    {
        if(packWeight >= offer.getStartWeight() && packWeight <= offer.getEndWeight() && distance >= offer.getStartDistance() && distance <= offer.getEndDistance())
            return true;

        return false;
    }

}
