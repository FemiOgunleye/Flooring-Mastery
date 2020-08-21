/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.flooringmastery.dao;

import com.fo.flooringmastery.dto.Orders;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TheFemiFactor
 */
public interface FlooringMasteryDao {
    
    /**
     * Returns a String array containing all orders for that particular date
     * @param date date to search for
     * @return String array containing the orders for that date.
     */
    List<Orders> displayOrders(LocalDate date);
    
    
    /**
     * Adds all the order data to a List and associates it with a computer 
     * generated sequential number. Then adds the order to a HashMap associated
     * by date.
     * 
     * @param date to add order to.
     * @param orderNumber to associate order with
     * @param order Order object 
     * 
     * @return null
     */
    
    Orders addOrder(int orderNumber, Orders order);
    
    /**
     * Returns the order associated with given order number on given date.
     * Returns null if order not found.
     * 
     * Calls addOrder() to update information. If new info is blank, use 
     * previous data.
     * 
     * @param date to search through date
     * @param orderNumber to locate particular order
     * @return order associated with orderNumber previous or null if none
     */
    
    Orders editOrder(LocalDate date, int orderNumber);
    
    /**
     * Removes the order associated with given order number on given date.
     * Returns removed order object or null if order not found.
     *
     * @param date
     * @param orderNumber order that needs to be removed
     * @return Order object removed or null if no order was associated with 
     * given order
     */
    Orders removeOrder(LocalDate date, int orderNumber);
    
    
    /**
     * Gets a list of states with tax rate values
     */
    Map ListOfStates();
    
    /**
     * Gets a list of products with their attributes
     */
    Map ListOfProducts();
    
    /**
     * Gets a list of orders and their attributes
     */
    Map ListOfOrders();
    
    /**
     * Gets a list of datedOrders
     */
//    Map ListOfDatedOrders();
    
    void callToWriteOrder() throws FlooringMasteryPersistenceException;
    
    void callToLoadOrder() throws FlooringMasteryPersistenceException;
    
}
