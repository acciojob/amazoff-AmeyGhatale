package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM

        String hh = deliveryTime.charAt(0) + deliveryTime.charAt(1) + "";
        String mm = deliveryTime.charAt(3) + deliveryTime.charAt(4) + "";
        int hour = Integer.parseInt(hh);
        int min = Integer.parseInt(mm);

        int time = hour*60 + min;

        this.id = id;
        this.deliveryTime = time;

    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
