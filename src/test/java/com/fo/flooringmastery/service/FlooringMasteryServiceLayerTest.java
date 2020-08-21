/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.flooringmastery.service;

import com.fo.flooringmastery.dao.FlooringMasteryAuditDao;
import com.fo.flooringmastery.dao.FlooringMasteryAuditDaoStubImpl;
import com.fo.flooringmastery.dao.FlooringMasteryDao;
import com.fo.flooringmastery.dao.FlooringMasteryDaoStubImpl;
import com.fo.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.fo.flooringmastery.dto.Orders;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author TheFemiFactor
 */
public class FlooringMasteryServiceLayerTest {
    
    private FlooringMasteryServiceLayer service;
    
    public FlooringMasteryServiceLayerTest() {
//        FlooringMasteryDao dao = new FlooringMasteryDaoStubImpl();
//        FlooringMasteryAuditDao auditDao = new FlooringMasteryAuditDaoStubImpl();
//        
//        service = new FlooringMasteryServiceLayerImpl(dao, auditDao);

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        service =
            ctx.getBean("serviceLayer", FlooringMasteryServiceLayer.class);
    
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of displayOrders method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testDisplayOrders() throws Exception {
        assertEquals(1, service.ListOfOrders().size());
    }

    /**
     * Test of addOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testAddOrder() throws Exception {
        
        Orders order = new Orders();
        order.setDate(LocalDate.parse("1111-11-11"));
        order.setOrderNumber(1);
        service.addOrder(1, order);
    }

    /**
     * Test of editOrder method, of class FlooringMasteryServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testEditOrder() throws Exception {
        
        Orders order = service.editOrder(LocalDate.parse("1111-11-11"), 1);
        order.setOrderNumber(1);
        assertNotNull(order);
        
        order = service.editOrder(LocalDate.parse("1122-08-09"), 1);
        assertNull(order);
  
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testRemoveOrder() throws FlooringMasteryPersistenceException {
        
        Orders order = new Orders();
        order.setOrderNumber(1);
        order.setDate(LocalDate.parse("1111-11-11"));
        service.removeOrder(LocalDate.parse("1111-11-11"), 1);
        assertNotNull(order);
        
        
//        order = new Orders();
//        order.setOrderNumber(1);
//        service.removeOrder(LocalDate.parse("1122-08-09"), 5);
//        assertNull(order);
        
    }

    /**
     * Test of ListOfStates method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testListOfStates() {
    }

    /**
     * Test of ListOfProducts method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testListOfProducts() {
    }

    /**
     * Test of ListOfOrders method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testListOfOrders() {
    }

    /**
     * Test of callToWriteOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testCallToWriteOrder() throws Exception {
    }

    /**
     * Test of callToLoadOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testCallToLoadOrder() throws Exception {
    }
    
}
