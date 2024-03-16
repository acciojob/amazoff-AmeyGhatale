package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private HashMap<String, Order> orderMap;
    private HashMap<String, DeliveryPartner> partnerMap;
    private HashMap<String, HashSet<String>> partnerToOrderMap;
    private HashMap<String, String> orderToPartnerMap;

    public OrderRepository(){
        this.orderMap = new HashMap<String, Order>();
        this.partnerMap = new HashMap<String, DeliveryPartner>();
        this.partnerToOrderMap = new HashMap<String, HashSet<String>>();
        this.orderToPartnerMap = new HashMap<String, String>();
    }

    public void saveOrder(Order order){
        // your code here
        String id = order.getId();
        orderMap.put(id, order);
    }

    public void savePartner(String partnerId){
        // your code here
        // create a new partner with given partnerId and save it
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        partnerMap.put(partnerId, deliveryPartner);
    }

    public void saveOrderPartnerMap(String orderId, String partnerId){
        if(orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)){
            // your code here
            //add order to given partner's order list

            //increase order count of partner
            DeliveryPartner deliveryPartner = partnerMap.get(partnerId);
            deliveryPartner.setNumberOfOrders(deliveryPartner.getNumberOfOrders()+1);
            partnerMap.put(partnerId, deliveryPartner);

            //assign partner to this order
            orderToPartnerMap.put(orderId, partnerId);

            HashSet<String> hs = partnerToOrderMap.get(partnerId);
            hs.add(orderId);
            partnerToOrderMap.put(partnerId, hs);
        }
    }

    public Order findOrderById(String orderId){
        // your code here
        return orderMap.get(orderId);
    }

    public DeliveryPartner findPartnerById(String partnerId){
        // your code here
        return partnerMap.get(partnerId);
    }

    public Integer findOrderCountByPartnerId(String partnerId){
        // your code here
        DeliveryPartner deliveryPartner = partnerMap.get(partnerId);
        Integer count =  deliveryPartner.getNumberOfOrders();
        return count;
    }

    public List<String> findOrdersByPartnerId(String partnerId){
        // your code here
        return new ArrayList<>(partnerToOrderMap.get(partnerId));
    }

    public List<String> findAllOrders(){
        // your code here
        // return list of all orders
        return new ArrayList<>(orderMap.keySet());
    }

    public void deletePartner(String partnerId){
        // your code here
        // delete partner by ID
        partnerMap.remove(partnerId);
        partnerToOrderMap.remove(partnerId);

        for(String order : orderToPartnerMap.keySet()){
            if(orderToPartnerMap.get(order).equals(partnerId)){
                orderToPartnerMap.remove(order);
                break;
            }
        }
    }

    public void deleteOrder(String orderId){
        // your code here
        // delete order by ID
        orderMap.remove(orderId);
        orderToPartnerMap.remove(orderId);

        boolean flag = false;
        for(HashSet<String> hs : partnerToOrderMap.values()){
            for(String order : hs){
                if(order.equals(orderId)){
                    hs.remove(order);
                    flag = true;
                    break;
                }
            }
            if(flag)
                break;
        }
    }

    public Integer findCountOfUnassignedOrders(){
        // your code here
        Integer count = 0;
        for(String order : orderMap.keySet()){
            if(!orderToPartnerMap.containsKey(order))
                count++;
        }
        return count;
    }

    public Integer findOrdersLeftAfterGivenTimeByPartnerId(String timeString, String partnerId){
        // your code here
        Integer count = 0;
        String hh = timeString.charAt(0) + timeString.charAt(1) + "";
        String mm = timeString.charAt(3) + timeString.charAt(4) + "";
        int hour = Integer.parseInt(hh);
        int min = Integer.parseInt(mm);
        int time = hour*60 + min;

        HashSet<String> hs = partnerToOrderMap.get(partnerId);
        for(String orders : hs){
            Order order = findOrderById(orders);
            int deliveryTime = order.getDeliveryTime();

            if(time<deliveryTime)
                count++;
        }
        return count;
    }

/*private HashMap<String, Order> orderMap;
    private HashMap<String, DeliveryPartner> partnerMap;
    private HashMap<String, HashSet<String>> partnerToOrderMap;
    private HashMap<String, String> orderToPartnerMap;*/


    public String findLastDeliveryTimeByPartnerId(String partnerId){
        // your code here
        // code should return string in format HH:MM
        float max = 0;
        HashSet<String> hs = partnerToOrderMap.get(partnerId);
        for(String orders : hs){
            Order order = findOrderById(orders);
            int time = order.getDeliveryTime();

            if(max<time)
                max = time;
        }

        int hour = (int)max/60;
        float min = ((max/60)- hour)*60;
        int mm=(int)min;
        String deliveryTime = "";

        if(hour<10 && min<10)
            deliveryTime = "0"+hour+":0"+mm;
        else if(min<10)
            deliveryTime = hour+":0"+mm;
        else if(hour<10)
            deliveryTime = "0"+hour+":"+mm;
        else
            deliveryTime = hour+":"+mm;

        return deliveryTime;
    }
}