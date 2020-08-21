/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.flooringmastery.service;

import com.fo.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.fo.flooringmastery.dto.Orders;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TheFemiFactor
 */
public interface FlooringMasteryServiceLayer {
    
    List<Orders> displayOrders(LocalDate date) throws
            FlooringMasteryPersistenceException;
    
    Orders addOrder(int orderNumber, Orders order) throws
            FlooringMasteryPersistenceException;
    
    Orders editOrder(LocalDate date, int orderNumber) throws
            FlooringMasteryPersistenceException;
    
    Orders removeOrder(LocalDate date, int orderNumber) throws
            FlooringMasteryPersistenceException;
    
    Map ListOfStates();
    
    Map ListOfProducts();

    Map ListOfOrders();
    
    void callToWriteOrder() throws FlooringMasteryPersistenceException;
    
    void callToLoadOrder() throws FlooringMasteryPersistenceException;
    
}
