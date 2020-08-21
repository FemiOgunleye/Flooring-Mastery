/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.flooringmastery;

import com.fo.flooringmastery.controller.FlooringMasteryController;
import com.fo.flooringmastery.dao.FlooringMasteryAuditDao;
import com.fo.flooringmastery.dao.FlooringMasteryAuditDaoFileImpl;
import com.fo.flooringmastery.dao.FlooringMasteryDao;
import com.fo.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.fo.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.fo.flooringmastery.service.FlooringMasteryServiceLayer;
import com.fo.flooringmastery.service.FlooringMasteryServiceLayerImpl;
import com.fo.flooringmastery.ui.FlooringMasteryView;
import com.fo.flooringmastery.ui.UserIO;
import com.fo.flooringmastery.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author TheFemiFactor
 */
public class App {
    public static void main(String[] args) throws FlooringMasteryPersistenceException {
//        UserIO myIo = new UserIOConsoleImpl();
//        FlooringMasteryView myView = new FlooringMasteryView(myIo);        
//        FlooringMasteryDao myDao = new FlooringMasteryDaoFileImpl();
//        FlooringMasteryAuditDao myAuditDao =
//                new FlooringMasteryAuditDaoFileImpl();
//        FlooringMasteryServiceLayer myService =
//                new FlooringMasteryServiceLayerImpl(myDao, myAuditDao);
//        FlooringMasteryController controller = 
//                new FlooringMasteryController(myService, myView);
//        controller.run();

        ApplicationContext ctx = 
                new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller = 
                ctx.getBean("controller", FlooringMasteryController.class);
        controller.run();
    }
}