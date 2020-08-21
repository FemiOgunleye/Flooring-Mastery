/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.flooringmastery.dao;

import com.fo.flooringmastery.dto.Orders;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TheFemiFactor
 */
public class FlooringMasteryDaoStubImpl implements FlooringMasteryDao{

    Orders onlyOrder;
    List<Orders> ordersList = new ArrayList<>();
    
    public FlooringMasteryDaoStubImpl() {
        onlyOrder = new Orders();
        onlyOrder.setOrderNumber(1);
        onlyOrder.setDate(LocalDate.parse("1111-11-11"));
        
        ordersList.add(onlyOrder);
    }
    
    @Override
    public List<Orders> displayOrders(LocalDate date) {
        
        List<Orders> newList = new ArrayList<>();
        for (Orders currentOrder : ordersList) {
            if (date.equals(onlyOrder.getDate())) {
                newList.add(currentOrder);
            } 
        }
         if (date.equals(onlyOrder.getDate())) {
             return newList;
         }
         else {
            return null;
        }
        
    }

    @Override
    public Orders addOrder(int orderNumber, Orders order) {
        
        if (orderNumber == onlyOrder.getOrderNumber() && order.equals(onlyOrder)) {
            return onlyOrder;
        }
        else {
            return null;
        }

    }

    @Override
    public Orders editOrder(LocalDate date, int orderNumber) {
        
        if (date.equals(onlyOrder.getDate()) && orderNumber == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        }
        else {
            return null;
        }

    }

    @Override
    public Orders removeOrder(LocalDate date, int orderNumber) {
        
        if (date.equals(onlyOrder.getDate()) && orderNumber == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        }
        else {
            return null;
        }
    }

    @Override
    public Map ListOfStates() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map ListOfProducts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map ListOfOrders() {
        Map<Integer, Orders> ordersMap = new HashMap<>();
        ordersMap.put(onlyOrder.getOrderNumber(), onlyOrder);
        return ordersMap;
    }

    @Override
    public void callToWriteOrder() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void callToLoadOrder() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
