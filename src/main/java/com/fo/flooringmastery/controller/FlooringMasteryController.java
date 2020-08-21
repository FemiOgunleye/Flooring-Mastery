/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.flooringmastery.controller;

import com.fo.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.fo.flooringmastery.dao.FlooringMasteryDao;
import com.fo.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.fo.flooringmastery.dto.Orders;
import com.fo.flooringmastery.dto.Product;
import com.fo.flooringmastery.dto.State;
import com.fo.flooringmastery.service.FlooringMasteryServiceLayer;
import com.fo.flooringmastery.ui.FlooringMasteryView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TheFemiFactor
 */
public class FlooringMasteryController {
    
    private FlooringMasteryServiceLayer service;
    private FlooringMasteryView view;
    private int maxSelection;
    
    public FlooringMasteryController(FlooringMasteryServiceLayer service,
            FlooringMasteryView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() throws FlooringMasteryPersistenceException {
        String select = prodOrTrainSelection();
        service.callToLoadOrder();
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try {
            
        while(keepGoing) {
            
            if (select.equalsIgnoreCase("prod")) {
                view.prodMenu();
                maxSelection = 6;
            } else if (select.equalsIgnoreCase("training")) {
                view.trainingMenu();
                maxSelection = 5;
            }
            menuSelection = menuSelection();

            
            switch(menuSelection) {
                
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    keepGoing = false;
                    break;
                case 6:
                    save();
                    break;
                default:
                    unknownCommand();
            }
        }   
        exitMessage();
    } catch (FlooringMasteryPersistenceException e) {
        view.displayErrorMessage(e.getMessage());
    }
    }
    
    private void addOrder() throws FlooringMasteryPersistenceException {
        view.displayAddOrderBanner();
        int num = (service.ListOfOrders().size() + 1);
        Orders newOrder = view.getNewOrderInfo(num);
        
        newOrder.setState
        ((State)service.ListOfStates().get(newOrder.getStateString()));
        newOrder.setType
        ((Product)service.ListOfProducts().get(newOrder.getTypeString()));
        
        Double materialCost = 
                newOrder.getType().getCostPer2foot() * newOrder.getArea();
        Double laborCost = 
                newOrder.getType().getLaborCostPer2foot() * newOrder.getArea();
        Double tax = 
                newOrder.getState().getTaxRate() * (materialCost + laborCost);
        Double total = tax + (materialCost + laborCost);
        newOrder.setMaterialCost(materialCost);
        newOrder.setLaborCost(laborCost);
        newOrder.setTax(tax);
        newOrder.setTotal(total);
        
        String finish = view.yesOrNo();
        if (finish.equalsIgnoreCase("no")) {
            return;
        }
        
        service.addOrder(num, newOrder); 
        view.displayAddSuccessBanner();
    }
    
    private void displayOrders() throws FlooringMasteryPersistenceException {
        view.displayOrderListBanner();
        
        List<Orders> ordersList = new ArrayList();
        do { 
        LocalDate date = dateToSearch();
        ordersList = service.displayOrders(date);
        
        if (ordersList.size() < 1) {
            view.displayOrderNotFoundMessage();
            return;
        }
        } while ((ordersList.size() < 1));
        
        view.displayOrderList(ordersList);
    }
    
    private void editOrder() throws FlooringMasteryPersistenceException {
        view.displayEditOrderBanner();
        List<Orders> ordersList = new ArrayList();
        LocalDate date;
        do { 
        date = dateToSearch();
        ordersList = service.displayOrders(date);
        
        if (ordersList.size() < 1) {
            view.displayOrderNotFoundMessage();
            return;
        }
        } while ((ordersList.size() < 1));
        
        view.displayOrderList(ordersList);
        int orderNumber = orderNumberToSearch();
        Orders editOrder = service.editOrder(date, orderNumber);

        
        Integer numInteger = (editOrder.getOrderNumber());
        boolean notValid;
        try {
            if (editOrder.getCustomerName().isEmpty() 
                || editOrder.getDate().toString().isEmpty() 
                || numInteger.toString().isEmpty())
                notValid = true;
                } catch (NullPointerException e) {
                    view.displayOrderNotFoundMessage(); 
                    return;
                }

        
        Orders editedOrder = view.editOrder(date, orderNumber, editOrder);

        editedOrder.setType
        ((Product)service.ListOfProducts().get(editedOrder.getTypeString()));
        editedOrder.setState
        ((State)service.ListOfStates().get(editedOrder.getStateString()));
        
        Double materialCost = 
                editedOrder.getType().getCostPer2foot() * editedOrder.getArea();
        Double laborCost = 
                editedOrder.getType().getLaborCostPer2foot() * editedOrder.getArea();
        Double tax = 
                editedOrder.getState().getTaxRate() * (materialCost + laborCost);
        Double total = tax + (materialCost + laborCost);
        editedOrder.setMaterialCost(materialCost);
        editedOrder.setLaborCost(laborCost);
        editedOrder.setTax(tax);
        editedOrder.setTotal(total);
        
        String finish = view.yesOrNo();
        if (finish.equalsIgnoreCase("no")) {
            return;
        }
        
        service.addOrder(orderNumber, editedOrder);
        view.displayEditedOrderSuccess();
        
    }
    
    private void removeOrder() throws FlooringMasteryPersistenceException {
        view.displayRemoveOrderBanner();
        List<Orders> ordersList = new ArrayList();
        LocalDate date;
        do { 
        date = dateToSearch();
        ordersList = service.displayOrders(date);
        
        if (ordersList.size() < 1) {
            view.displayOrderNotFoundMessage();
            return;
        }
        } while ((ordersList.size() < 1));
        
        view.displayOrderList(ordersList);
        
        Orders editOrder = new Orders();
        int orderNumber = 0;
        boolean valid = true;
        do {
        orderNumber = orderNumberToSearch();
        editOrder = service.editOrder(date, orderNumber);

        
        Integer numInteger = (editOrder.getOrderNumber());
        
        try {
            if (editOrder.getCustomerName().isEmpty() 
                || editOrder.getDate().toString().isEmpty() 
                || numInteger.toString().isEmpty())
                orderNumber = 0;
                } catch (NullPointerException e) {
                    view.displayOrderNotFoundMessage(); 
                    valid = false;
                }
        } while (valid == false);
        
        String finish = view.yesOrNo();
        if (finish.equalsIgnoreCase("no")) {
            return;
        }
        service.removeOrder(date, orderNumber);
        view.displayRemoveSuccessBanner();
    }

    private void save() throws FlooringMasteryPersistenceException {
        view.displaySaveBanner();
        service.callToWriteOrder();
        view.displaySaveSuccessfulBanner();
    }
    
    public String prodOrTrainSelection() throws FlooringMasteryPersistenceException {

        String select = view.getProdOrTrainSelection();
        return select;
    }
    
    public int menuSelection() {
        return view.getMenuSelection(maxSelection);
    }
    
    public LocalDate dateToSearch() {
        return view.getDateToDisplayOrder();
    }
    
    public int orderNumberToSearch() {
        int num = 0;
        int highest = 0;
        do {
            num = view.getOrderNumberToSearch();
            highest = highestOrderNumber();
        } while (num > highest);
        return num;
    }
    
    public int highestOrderNumber() {
        List<Orders> highestList = new ArrayList(service.ListOfOrders().values());
        int highestArray[] = new int [service.ListOfOrders().size()];
        int count = 0;
        for (Orders currentOrder : highestList) {
            highestArray[count] += currentOrder.getOrderNumber();
            count++;
        }
        // Can use single loop
        int max = 0;
        for (int counter = 0; counter < highestArray.length; counter++) {
            
            if (highestArray[counter] > max) {
                max = highestArray[counter];
            }
        }
        return max;
    }
    
    public void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    public void exitMessage() {
        view.displayExitBanner();
    }
    
}
