/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.flooringmastery.dao;

import com.fo.flooringmastery.dto.Orders;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author TheFemiFactor
 */
public class FlooringMasteryDaoTest {
    
    FlooringMasteryDao dao = new FlooringMasteryDaoFileImpl();
    
    public FlooringMasteryDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Orders> ordersList = new ArrayList(dao.ListOfOrders().values());
        for (Orders currentOrder : ordersList) {
            dao.removeOrder
                (currentOrder.getDate(), currentOrder.getOrderNumber());
        }
    }
    
    @AfterEach
    public void tearDown() {
        
    }

    /**
     * Test of displayOrders method, of class FlooringMasteryDao.
     */
    @Test
    public void testDisplayOrders() {
        
        LocalDate date = LocalDate.parse("1313-12-12");
        Orders order = new Orders();
        order.setDate(date);
        order.setOrderNumber(1);
        dao.addOrder(order.getOrderNumber(), order);
        
        Orders order2 = new Orders();
        order2.setDate(date);
        order2.setOrderNumber(2);
        dao.addOrder(order2.getOrderNumber(), order2);
        
        Orders order3 = new Orders();
        order3.setDate(LocalDate.parse("1111-11-11"));
        order3.setOrderNumber(3);
        dao.addOrder(order3.getOrderNumber(), order3);
        
        List<Orders> displayList = dao.displayOrders(date);

        
        assertEquals(2, displayList.size());
    }

    /**
     * Test of addOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testAddOrder() {
        
        Orders order = new Orders();
        order.setDate(LocalDate.parse("1111-11-11"));
        order.setOrderNumber(1);
        dao.addOrder(order.getOrderNumber(), order);
        
        Orders fromDao = dao.editOrder(order.getDate(), order.getOrderNumber());
        
        assertEquals(order, fromDao);

    }

    /**
     * Test of editOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testEditOrder() {

        
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testRemoveOrder() {
        
        Orders order = new Orders();
        order.setDate(LocalDate.parse("1111-11-11"));
        order.setOrderNumber(1);
        dao.addOrder(order.getOrderNumber(), order);
        
        Orders order2 = new Orders();
        order2.setDate(LocalDate.parse("1212-12-12"));
        order2.setOrderNumber(2);
        dao.addOrder(order2.getOrderNumber(), order2);
        
        dao.removeOrder(order.getDate(), order.getOrderNumber());
        assertEquals(1, dao.ListOfOrders().size());
        assertNull(dao.ListOfOrders().get(order.getOrderNumber()));
        
        dao.removeOrder(order2.getDate(), order2.getOrderNumber());
        assertEquals(0, dao.ListOfOrders().size());
        assertNull(dao.ListOfOrders().get(order2.getOrderNumber()));
    }

    /**
     * Test of ListOfStates method, of class FlooringMasteryDao.
     */
    @Test
    public void testListOfStates() {
        
    }

    /**
     * Test of ListOfProducts method, of class FlooringMasteryDao.
     */
    @Test
    public void testListOfProducts() {
    }

    /**
     * Test of ListOfOrders method, of class FlooringMasteryDao.
     */
    @Test
    public void testListOfOrders() {
    }

    /**
     * Test of callToWriteOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testCallToWriteOrder() throws Exception {
    }

    /**
     * Test of callToLoadOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testCallToLoadOrder() throws Exception {
    }
    
}
