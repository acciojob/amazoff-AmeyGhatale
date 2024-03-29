//package com.driver;
//
//import java.util.*;
//import java.util.concurrent.Phaser;
//
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class OrderRepository {
//
//    private HashMap<String, Order> orderMap;
//    private HashMap<String, DeliveryPartner> partnerMap;
//    private HashMap<String, HashSet<String>> partnerToOrderMap;
//    private HashMap<String, String> orderToPartnerMap;
//
//    public OrderRepository(){
//        this.orderMap = new HashMap<String, Order>();
//        this.partnerMap = new HashMap<String, DeliveryPartner>();
//        this.partnerToOrderMap = new HashMap<String, HashSet<String>>();
//        this.orderToPartnerMap = new HashMap<String, String>();
//
////        orderMap.put("1", new Order("1", "02:59"));
////        orderMap.put("2", new Order("2", "07:30"));
////        orderMap.put("3", new Order("3", "10:56"));
////        orderMap.put("4", new Order("4", "15:42"));
////        orderMap.put("5", new Order("5", "23:59"));
////
////        partnerMap.put("1", new DeliveryPartner("1"));
////        partnerMap.put("2", new DeliveryPartner("2"));
////        partnerMap.put("3", new DeliveryPartner("3"));
////
////        HashSet<String> hs = new HashSet<>();
////        hs.add("2"); hs.add("3"); hs.add("4"); hs.add("5");
////        partnerToOrderMap.put("3", hs);
////        HashSet<String> hss = new HashSet<>();
////        hss.add("1");
////        partnerToOrderMap.put("1", hss);
//
//    }
//
//
//    public void saveOrder(Order order){
//        // your code here
//        if(order!=null && !orderMap.containsKey(order.getId())) {
//            String id = order.getId();
//            orderMap.put(id, order);
//        }
//
////        for (String orders : orderMap.keySet()){
////            System.out.println(orderMap.get(orders).toString());
////        }
////        System.out.println();
//    }
//
//    public void savePartner(String partnerId){
//        // your code here
//        // create a new partner with given partnerId and save it
//        if(partnerId!=null && !partnerMap.containsKey(partnerId)) {
//            partnerMap.put(partnerId, new DeliveryPartner(partnerId));
//        }
////        for (String partner : partnerMap.keySet()){
////            System.out.println(partnerMap.get(partner).toString());
////        }
////        System.out.println();
//    }
//
//    public void saveOrderPartnerMap(String orderId, String partnerId){
//        if(orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)){
//            // your code here
//            //add order to given partner's order list
//
//            //increase order count of partner
//            DeliveryPartner deliveryPartner = partnerMap.get(partnerId);
//            deliveryPartner.setNumberOfOrders(deliveryPartner.getNumberOfOrders()+1);
//            partnerMap.put(partnerId, deliveryPartner);
//
//            //assign partner to this order
//            orderToPartnerMap.put(orderId, partnerId);
//
//            if(!partnerToOrderMap.containsKey(partnerId)){
//                HashSet<String> hs = new HashSet<>();
//                hs.add(orderId);
//                partnerToOrderMap.put(partnerId, hs);
//            }
//            else
//                partnerToOrderMap.get(partnerId).add(orderId);
//
//
////            System.out.println("OPm Inside  order present="+orderMap.containsKey(orderId)+"  partner present="+partnerMap.containsKey(partnerId));
//        }
////
////        for (String orders : orderToPartnerMap.keySet()){
////            System.out.println("order No="+orders+"  partner no="+orderToPartnerMap.get(orders));
////        }
////        System.out.println("OPm   order present="+orderMap.containsKey(orderId)+"  partner present="+partnerMap.containsKey(partnerId));
//
//    }
//
//    public Order findOrderById(String orderId){
//        // your code here
//        return orderMap.get(orderId);
//    }
//
//    public DeliveryPartner findPartnerById(String partnerId){
//        // your code here
//        return partnerMap.get(partnerId);
//    }
//
//    public Integer findOrderCountByPartnerId(String partnerId){
//        // your code here
//        Integer count = 0;
//        DeliveryPartner deliveryPartner = partnerMap.get(partnerId);
//         count =  deliveryPartner.getNumberOfOrders();
//        return count;
//    }
//
//    public List<String> findOrdersByPartnerId(String partnerId){
//        // your code here
//
////        System.out.println("Id  present="+partnerToOrderMap.containsKey(partnerId));
//        if(partnerToOrderMap.containsKey(partnerId))
//            return new ArrayList<>(partnerToOrderMap.get(partnerId));
////        System.out.println("Id not present");
//        return null;
//    }
//
//    public List<String> findAllOrders(){
//        // your code here
//        // return list of all orders
//        return new ArrayList<>(orderMap.keySet());
//    }
//
//    public void deletePartner(String partnerId){
//        // your code here
//        // delete partner by ID
//        if (!partnerMap.containsKey(partnerId))
//            return;
//
//        partnerMap.remove(partnerId);
//
//        HashSet<String > hs = partnerToOrderMap.get(partnerId);
//        for (String order : hs){
//            orderToPartnerMap.remove(order);
//        }
//        partnerToOrderMap.remove(partnerId);
//
////        for(String order : orderToPartnerMap.keySet()){
////            System.out.println("OtPm  order= "+order+"  partner="+orderToPartnerMap.get(order));
////        }
////
////        for(String partner : partnerToOrderMap.keySet()){
////            System.out.println("PtOm  partner= "+partner);
////        }
//    }
//
//    public void deleteOrder(String orderId){
//        // your code here
//        // delete order by ID
//        orderMap.remove(orderId);
//        orderToPartnerMap.remove(orderId);
//
//        boolean flag = false;
//        for(HashSet<String> hs : partnerToOrderMap.values()){
//            for(String order : hs){
//                if(order.equals(orderId)){
//                    hs.remove(order);
//                    flag = true;
//                    break;
//                }
//            }
//            if(flag)
//                break;
//        }
//    }
//
//    public Integer findCountOfUnassignedOrders(){
//        // your code here
//        Integer count = 0;
//        for(String order : orderMap.keySet()){
//            if(!orderToPartnerMap.containsKey(order))
//                count++;
//        }
//        return count;
//    }
//
//    public Integer findOrdersLeftAfterGivenTimeByPartnerId(String timeString, String partnerId){
//        // your code here
//        Integer count = 0;
//        String hh = timeString.charAt(0) +""+ timeString.charAt(1) ;
//        String mm = timeString.charAt(3) +""+ timeString.charAt(4);
//        int hour = Integer.parseInt(hh);
//        int min = Integer.parseInt(mm);
//        int time = hour*60 + min;
//
//        HashSet<String> hs = partnerToOrderMap.get(partnerId);
//        for(String orders : hs){
//            Order order = findOrderById(orders);
//            int deliveryTime = order.getDeliveryTime();
//
//            if(time<deliveryTime)
//                count++;
//        }
//        return count;
//    }
//
///*private HashMap<String, Order> orderMap;
//    private HashMap<String, DeliveryPartner> partnerMap;
//    private HashMap<String, HashSet<String>> partnerToOrderMap;
//    private HashMap<String, String> orderToPartnerMap;*/
//
//
//    public String findLastDeliveryTimeByPartnerId(String partnerId){
//        // your code here
//        // code should return string in format HH:MM
//        if (partnerToOrderMap.containsKey(partnerId)) {
//            float max = 0;
//            HashSet<String> hs = partnerToOrderMap.get(partnerId);
//            for (String orders : hs) {
//                Order order = findOrderById(orders);
//                int time = order.getDeliveryTime();
//
//                if (max < time)
//                    max = time;
//            }
//
//            int hour = (int) max / 60;
//            float min = ((max / 60) - hour) * 60;
//            int mm = (int) min;
//            String deliveryTime = "";
//
//            if (hour < 10 && min < 10)
//                deliveryTime = "0" + hour + ":0" + mm;
//            else if (min < 10)
//                deliveryTime = hour + ":0" + mm;
//            else if (hour < 10)
//                deliveryTime = "0" + hour + ":" + mm;
//            else
//                deliveryTime = hour + ":" + mm;
//
//            return deliveryTime;
//        }
//        return null;
//    }
//
//}




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
        if (order != null && order.getId() != null) {
            orderMap.put(order.getId(), order);
        }
    }

    public void savePartner(String partnerId){
        // your code here
        // create a new partner with given partnerId and save it
        if (partnerId != null) {
            DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
            partnerMap.put(partnerId, deliveryPartner);
        }
    }

    public void saveOrderPartnerMap(String orderId, String partnerId){
        if(orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)){
            // your code here
            //add order to given partner's order list
            //increase order count of partner
            //assign partner to this order
            if (orderId != null && partnerId != null) {
                orderToPartnerMap.put(orderId, partnerId);

                DeliveryPartner partner = partnerMap.get(partnerId);
                partnerToOrderMap.computeIfAbsent(partnerId, k -> new HashSet<>()).add(orderId);

                partner.setNumberOfOrders(partner.getNumberOfOrders() + 1);
            }
        }
    }

    public Order findOrderById(String orderId){
        // your code here
        return orderId != null ? orderMap.get(orderId) : null;
    }

    public DeliveryPartner findPartnerById(String partnerId){
        // your code here
        return partnerId != null ? partnerMap.get(partnerId) : null;
    }

    public Integer findOrderCountByPartnerId(String partnerId){
        // your code here
//        if (partnerToOrderMap.containsKey(partnerId)) {
//            return partnerToOrderMap.get(partnerId).size();
//        }
//        return 0;
        if (partnerId != null && partnerToOrderMap.containsKey(partnerId)) {
            DeliveryPartner deliveryPartner = partnerMap.get(partnerId);
            return deliveryPartner.getNumberOfOrders();
        }
        return 0;
    }

    public List<String> findOrdersByPartnerId(String partnerId){
        // your code here
        if (partnerId != null) {
            Set<String> orders = partnerToOrderMap.getOrDefault(partnerId, new HashSet<>());
            return new ArrayList<>(orders);
        }
        return Collections.emptyList();
    }

    public List<String> findAllOrders(){
        // your code here
        // return list of all orders
        List<String> ordersList = new ArrayList<>();
        for (Order order : orderMap.values()) {
            ordersList.add(order.getId());
        }
        return ordersList;
    }

    public void deletePartner(String partnerId){
        // your code here
        // delete partner by ID
        if (partnerId != null && partnerMap.containsKey(partnerId)) {
            partnerMap.remove(partnerId);
            Set<String> assignedOrders = partnerToOrderMap.remove(partnerId);
            if (assignedOrders != null) {
                for (String orderId : assignedOrders) {
                    orderToPartnerMap.remove(orderId);
                }
            }
        }
    }

    public void deleteOrder(String orderId){
        // your code here
        // delete order by ID
        if (orderId != null && orderMap.containsKey(orderId)) {
            String partnerId = orderToPartnerMap.remove(orderId);
            if (partnerId != null && partnerToOrderMap.containsKey(partnerId)) {
                partnerToOrderMap.get(partnerId).remove(orderId);
                DeliveryPartner partner = partnerMap.get(partnerId);
                if (partner != null) {
                    partner.setNumberOfOrders(partner.getNumberOfOrders() - 1);
                }
            }
            orderMap.remove(orderId);
        }
    }

    public Integer findCountOfUnassignedOrders(){
        // your code here
        int unassignedCount = 0;
        for (String orderId : orderMap.keySet()) {
            if (orderToPartnerMap.get(orderId) == null) {
                unassignedCount++;
            }
        }
        return unassignedCount;
    }

    public Integer findOrdersLeftAfterGivenTimeByPartnerId(String timeString, String partnerId){
        // your code here
        int ordersLeft = 0;
        String[] timeParts = timeString.split(":");
        if (timeParts.length == 2) {
            int givenTime = Integer.parseInt(timeParts[0]) * 60 + Integer.parseInt(timeParts[1]);
            Set<String> partnerOrders = partnerToOrderMap.getOrDefault(partnerId, new HashSet<>());
            for (String orderId : partnerOrders) {
                Order order = orderMap.get(orderId);
                if (order != null && order.getDeliveryTime() > givenTime) {
                    ordersLeft++;
                }
            }
        }
        return ordersLeft;
    }

    public String findLastDeliveryTimeByPartnerId(String partnerId){
        // your code here
        // code should return string in format HH:MM
        int latestTime = Integer.MIN_VALUE;
        String lastDeliveryTime = "";
        int time = 0;
        Set<String> partnerOrders = partnerToOrderMap.getOrDefault(partnerId, new HashSet<>());
        for (String orderId : partnerOrders) {
            Order order = orderMap.get(orderId);
            if (order != null && order.getDeliveryTime() > latestTime) {
                latestTime = order.getDeliveryTime();
                time = order.getDeliveryTime();
            }
        }
        int hours = time / 60;
        int minutes = time % 60;
        lastDeliveryTime = String.format("%02d:%02d", hours, minutes);
        return lastDeliveryTime;
    }
}