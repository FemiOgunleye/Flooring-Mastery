/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.flooringmastery.dao;

import com.fo.flooringmastery.dto.Orders;
import com.fo.flooringmastery.dto.Product;
import com.fo.flooringmastery.dto.State;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.*;

/**
 *
 * @author TheFemiFactor
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao{
    
    private Map<Integer, Orders> ordersList = new HashMap<>();
    private Map <String, State> stateList = new HashMap<>();
    private Map <String, Product> productList = new HashMap<>();
    private State oh = new State("OH", 6.25);
    private State pa = new State("PA", 6.75);
    private State mi = new State("MI", 5.75);
    private State in = new State("IN", 6.00);
    
    private Product carpet = new Product("Carpet", 2.25, 2.10);
    private Product laminate = new Product("Laminate", 1.75, 2.10);
    private Product tile = new Product("Tile", 3.50, 4.15);
    private Product wood = new Product("Wood", 5.15, 4.75);
    
    
    public static final String ORDER_FILE = "Orders_MMDDYYYY.txt";
    public static final String DELIMITER = ",";
    
    private void loadOrder() throws FlooringMasteryPersistenceException {
        
        Scanner scanner;
        
        try {
            scanner = new Scanner(
                    new BufferedReader(
                        new FileReader(ORDER_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
            "Could not load Order data into memory", e);
        }
        
        String currentLine;
        String[] currentTokens;
        
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Orders currentOrder = new Orders();
            currentOrder.setDate(LocalDate.parse(currentTokens[0].substring(7)));
            currentOrder.setOrderNumber(Integer.parseInt(currentTokens[1]));
            currentOrder.setCustomerName(currentTokens[2]);
            currentOrder.setState((State)ListOfStates().get(currentTokens[3]));
            currentOrder.setStateString(currentTokens[3]);
            currentOrder.setTax(Double.parseDouble(currentTokens[4]));
            currentOrder.setType
                            ((Product)ListOfProducts().get(currentTokens[5]));
            currentOrder.setTypeString(currentTokens[5]);
            currentOrder.setArea(Double.parseDouble(currentTokens[6]));
            currentOrder.setCostPer2foot(Double.parseDouble(currentTokens[7]));
            currentOrder.setLaborCostPer2foot(Double.parseDouble(currentTokens[8]));
            currentOrder.setMaterialCost(Double.parseDouble(currentTokens[9]));
            currentOrder.setLaborCost(Double.parseDouble(currentTokens[10]));
            currentOrder.setTax(Double.parseDouble(currentTokens[11]));
            currentOrder.setTotal(Double.parseDouble(currentTokens[12]));
            
            ordersList.put(currentOrder.getOrderNumber(), currentOrder);
        }
        
        scanner.close();
        
    }
    
    @Override
    public void callToLoadOrder() throws FlooringMasteryPersistenceException
    
    
    {
        loadOrder();
    }
    
    @Override
    public void callToWriteOrder() throws FlooringMasteryPersistenceException {
        writeOrder();
    }
    
    private void writeOrder() throws FlooringMasteryPersistenceException {
        
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(ORDER_FILE));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
            "Could not save order data", e);
        }
        
        List<Orders> listOfOrders = new ArrayList(this.ListOfOrders().values());
        for (Orders currentOrder : listOfOrders) {
            DecimalFormat df = new DecimalFormat("#,###,##0.00");
            out.println( "Orders_" 
                    + currentOrder.getDate().toString() + DELIMITER
                    + currentOrder.getOrderNumber() + DELIMITER
                    + currentOrder.getCustomerName() + DELIMITER
                    + currentOrder.getState().getState() + DELIMITER
                    + (df.format(currentOrder.getState().getTaxRate())) + DELIMITER
                    + currentOrder.getType().getType() + DELIMITER
                    + (df.format(currentOrder.getArea())) + DELIMITER
                    + (df.format(currentOrder.getCostPer2foot())) + DELIMITER
                    + (df.format(currentOrder.getLaborCostPer2foot())) + DELIMITER
                    + (df.format(currentOrder.getMaterialCost())) + DELIMITER
                    + (df.format(currentOrder.getLaborCost())) + DELIMITER
                    + (df.format(currentOrder.getTax())) + DELIMITER
                    + (df.format(currentOrder.getTotal())) + DELIMITER );
            out.flush();
        }
        out.close();
    }
    
   
    @Override
    public Map ListOfStates() {
        
        stateList.put("OH", oh);
        stateList.put("PA", pa);
        stateList.put("MI", mi);
        stateList.put("IN", in);

        return stateList;
    }
    
    @Override
    public Map ListOfProducts() {
        
        productList.put("Carpet", carpet);
        productList.put("Laminate", laminate);
        productList.put("Tile", tile);
        productList.put("Wood", wood);

        return productList;
    }
    
    @Override
    public Map ListOfOrders() {
        return ordersList;
    }
    
//    @Override
//    public Map ListOfDatedOrders() {
//        
//        return datedOrders;
//    }

    @Override
     public List<Orders> displayOrders(LocalDate date) {
        
        
        List<Orders> newList = new ArrayList(ListOfOrders().values()) ;
        List<Orders> outputList = new ArrayList();
        
        for (Orders currentOrder : newList) {
            if (currentOrder.getDate().equals(date)) {
                outputList.add(currentOrder);
            }
        }
        
        return outputList;   
     }
    
    @Override
    public Orders addOrder(int orderNumber, Orders order) {

        ordersList.put(orderNumber, order);
        
        return order;
    }

    @Override
    public Orders editOrder(LocalDate date, int orderNumber) {
        List<Orders> newList = new ArrayList(ListOfOrders().values()) ;
        Orders editOrder = new Orders();
        for (Orders currentOrder : newList) {
            if (currentOrder.getDate().equals(date)) {
                if (currentOrder.getOrderNumber() == orderNumber)
                editOrder = currentOrder;
            }
        }
        return editOrder;
    }

    @Override
    public Orders removeOrder(LocalDate date, int orderNumber) {
        List<Orders> newList = new ArrayList(ListOfOrders().values());
        Orders removeOrder = new Orders();
        for (Orders currentOrder : newList) {
            if (currentOrder.getDate().equals(date)) {
                if (currentOrder.getOrderNumber() == orderNumber) {
                    removeOrder = currentOrder;
                    ordersList.remove(orderNumber, currentOrder);
                }
                
            }
            
        }
        return removeOrder;
    }
    
}
