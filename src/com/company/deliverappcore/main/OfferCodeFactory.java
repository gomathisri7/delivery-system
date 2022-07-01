package com.company.deliverappcore.main;

import com.company.deliveryappmodel.Offer;

public class OfferCodeFactory {

    public Offer createOfferCode(String code){
        if(code.isEmpty() || code == null)
            return null;
        switch(code) {
            case "OFR001" : return new Offer("OFR001", 10,70,200,0 ,200);
            case "OFR002" : return new Offer("OFR002", 7, 100, 250, 50, 150);
            case "OFR003" : return new Offer("OFR003", 5, 10,150,50,250);
            default:
                return new Offer(code,0,0,0,0,0);
        }
    }
}
