/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.flooringmastery.service;

import com.fo.flooringmastery.dao.FlooringMasteryAuditDao;
import com.fo.flooringmastery.dao.FlooringMasteryDao;
import com.fo.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.fo.flooringmastery.dto.Orders;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TheFemiFactor
 */
public class FlooringMasteryServiceLayerImpl implements 
            FlooringMasteryServiceLayer{
    
    private FlooringMasteryDao dao;
    private FlooringMasteryAuditDao auditDao;
    
    public FlooringMasteryServiceLayerImpl(FlooringMasteryDao dao, 
            FlooringMasteryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Orders> displayOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        return dao.displayOrders(date);
    }

    @Override
    public Orders addOrder(int orderNumber, Orders order) throws FlooringMasteryPersistenceException {
        dao.addOrder(order.getOrderNumber(), order);
        
//         auditDao.writeAuditEntry("Order " + order.getOrderNumber() + " |Date :"
//                 + order.getDate().toString() + " CREATED");
        
        return dao.addOrder(orderNumber, order);
    }

    @Override
    public Orders editOrder(LocalDate date, int orderNumber) throws FlooringMasteryPersistenceException {
        return dao.editOrder(date, orderNumber);
    }

    @Override
    public Orders removeOrder(LocalDate date, int orderNumber) throws FlooringMasteryPersistenceException {
        Orders order = new Orders();
        order.setDate(date);
        order.setOrderNumber(orderNumber);
        dao.removeOrder(order.getDate(), order.getOrderNumber());
//        auditDao.writeAuditEntry("Order " + order.getOrderNumber() + " |Date :"
//                 + order.getDate().toString() + " REMOVED");
        return dao.removeOrder(order.getDate(), order.getOrderNumber());
    }

    @Override
    public Map ListOfStates() {
        return dao.ListOfStates();
    }

    @Override
    public Map ListOfProducts() {
        return dao.ListOfProducts();
    }

    @Override
    public Map ListOfOrders() {
        return dao.ListOfOrders();
    }

    @Override
    public void callToWriteOrder() throws FlooringMasteryPersistenceException {
        dao.callToWriteOrder();
    }

    @Override
    public void callToLoadOrder() throws FlooringMasteryPersistenceException {
            dao.callToLoadOrder();
    }
    
}
