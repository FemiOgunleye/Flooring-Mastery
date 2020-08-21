/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.flooringmastery.ui;

import com.fo.flooringmastery.dto.Orders;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author TheFemiFactor
 */
public class FlooringMasteryView {
    
    private UserIO io;
    
    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }
    public String getProdOrTrainSelection() {
        
        String prodOrTrain = "";
        do {
        prodOrTrain = io.readString("Please enter either 'Prod' or 'Training'" +
                " for appropriate menu: ");
        } while (!prodOrTrain.equalsIgnoreCase("Prod") 
                && !prodOrTrain.equalsIgnoreCase("Training"));
        
        return prodOrTrain;
    }
    
    public int getMenuSelection(int maxSelection) {
        
        int selection = io.readInt("Please select from the above choices: ",
        1, maxSelection);
        
        return selection;
    }

    public int prodMenu() {
        io.print("Main Menu");
        io.print("1. Display Order");
        io.print("2. Add Order");
        io.print("3. Edit Order");
        io.print("4. Remove Order");
        io.print("5. Exit");
        io.print("6. Save");
        return 6;
    }

    public int trainingMenu() {
        io.print("Main Menu");
        io.print("1. Display Order");
        io.print("2. Add Order");
        io.print("3. Edit Order");
        io.print("4. Remove Order");
        io.print("5. Exit");
        return 5;
    }
    
    public Orders getNewOrderInfo(int orderNumber) {
        LocalDate date = getDate();
        String customerName;
        do {
        customerName = io.readString("Please enter customer name:");
        } while (customerName.isBlank());
        String stateString = getStateString();
        String typeString = getTypeString();
        Double area;
        do {
            area = io.readDouble("Please enter the area: ");
        } while(area < 1);
        
        
        Orders currentOrder = new Orders();
        currentOrder.setOrderNumber(orderNumber);
        currentOrder.setDate(date);
        currentOrder.setCustomerName(customerName);
        currentOrder.setStateString(stateString.toUpperCase());
        currentOrder.setTypeString(typeString.substring(0, 1).toUpperCase() + typeString.substring(1).toLowerCase());
        currentOrder.setArea(area);
        return currentOrder;
    }
    
    public Orders editOrder(LocalDate date, int orderNumber, Orders editOrder) {
        Orders currentOrder = new Orders();
        String customerName = io.readString("Please enter customer name:");
        if (customerName.isBlank())
            customerName = editOrder.getCustomerName();
        String stateString = "";
        do {
        stateString = io.readString("Please enter the state in the "
                + "format 'XX':");
        } while(!stateString.equalsIgnoreCase("OH") 
                && !stateString.equalsIgnoreCase("PA") 
                && !stateString.equalsIgnoreCase("MI") 
                && !stateString.equalsIgnoreCase("IN")
                && !stateString.isBlank());
        if (stateString.isEmpty())
        {
            stateString = editOrder.getStateString();
            currentOrder.setStateString(stateString);
        }
        else {
            currentOrder.setStateString(stateString.toUpperCase());
        }
        String typeString = "";
        do {
        typeString = io.readString("Please enter the product type: ");
        } while ((!typeString.equalsIgnoreCase("carpet") 
                && !typeString.equalsIgnoreCase("Laminate") 
                && !typeString.equalsIgnoreCase("Tile") 
                && !typeString.equalsIgnoreCase("Wood")
                && !typeString.isBlank()));
        if (typeString.isEmpty())
        { 
            typeString = editOrder.getTypeString(); 
            currentOrder.setTypeString(typeString);
        }
        else {
            currentOrder.setTypeString(typeString.substring(0, 1).toUpperCase() + typeString.substring(1).toLowerCase());
        }
        
        Double area = io.readDouble("Please enter the area: ");
        if (area <= 0)
        { 
            area = editOrder.getArea();
        
        }
        
        currentOrder.setDate(date);
        currentOrder.setOrderNumber(orderNumber);
        currentOrder.setCustomerName(customerName);
        currentOrder.setArea(area);
        
        return currentOrder;
    }
        
    public void displayAddOrderBanner() {
        io.print("=== Create Order ===");
    }
    
    public void displayAddSuccessBanner() {
        io.readString("Order successfully created. Please hit enter to continue:");
    }
    
    public void displayOrderList(List<Orders> ordersList) {
        for (Orders currentOrder : ordersList) {
            io.print(currentOrder.getOrderNumber() + " |"
            + currentOrder.getDate().toString() + " |"
            + currentOrder.getCustomerName() + " |"
            + currentOrder.getState().getState() + " |"
            + currentOrder.getState().getTaxRate() + " |"
            + currentOrder.getType().getType() + " |"
            + currentOrder.getArea() + " |" 
            + currentOrder.getType().getCostPer2foot() + " |"
            + currentOrder.getType().getLaborCostPer2foot() + " |"
            + currentOrder.getMaterialCost() + " |"
            + currentOrder.getLaborCost() + " |"
            + currentOrder.getTax() + " |"
            + currentOrder.getTotal());
        }
        io.readString("Please hit enter to continue: ");
    }
    
    
    
    public LocalDate getDateToDisplayOrder() {
        return getDate();
    }
    
    public void displayOrderListBanner() {
        io.print("=== Display Orders by Date ===");
    }
    
    public void displayEditOrderBanner() {
        io.print("=== Edit Order ===");
    }
    
    public int getOrderNumberToSearch() {
        int orderNumber = io.readInt(
                "Please enter Order Number to select order: ");
        return orderNumber;
    }
    
    public void displayOrderNotFoundMessage() {
        io.print("Sorry, this order was not found.");
    }
    
    
    public void displayEditedOrderSuccess() {
        io.readString("Order was edited successfully. Press enter to continue");
    }
    
    public void displayRemoveOrderBanner() {
        io.print("=== Remove Order ===");
    }
    
    public void displayRemoveSuccessBanner() {
        io.readString("Order successfully removed. Please hit enter to continue" );
    }
    
    public void displaySaveBanner() {
        io.print("=== Save ===");
    }
    
    public void displaySaveSuccessfulBanner() {
        io.readString("Save successful. Please hit enter to continue");
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    public LocalDate getDate() {
        String dateString = "";
        LocalDate date = null;
        do {    try {
        dateString = io.readString(
                "Please enter the date in 'YYYY-MM-DD' format:");
                date = LocalDate.parse(dateString);
        }   catch (DateTimeParseException e ) {
                System.out.println("Wrong date format.");
        } 
        } while(date == null);
        return date;
    }

    public String getStateString() {
        String  stateString = "";
        
        do {
            stateString = io.readString("Please enter the state in the "
                + "format 'XX':");
        } while (!stateString.equalsIgnoreCase("OH") 
                && !stateString.equalsIgnoreCase("PA") 
                && !stateString.equalsIgnoreCase("MI") 
                && !stateString.equalsIgnoreCase("IN"));
        return stateString;
    }
    
    public String getTypeString() {
        String typeString = "";
        do {
            typeString = io.readString("Please enter the product type: ");
        } while (!typeString.equalsIgnoreCase("carpet") 
                && !typeString.equalsIgnoreCase("Laminate") 
                && !typeString.equalsIgnoreCase("Tile") 
                && !typeString.equalsIgnoreCase("Wood"));
        return typeString;
    }
    
    public String yesOrNo() {
        String yesOrNo;
        do {
            yesOrNo = io.readString("Are you sure you want to continue? Yes/No");
            if (yesOrNo.equalsIgnoreCase("no")) {
                break;
            }
        } while (!yesOrNo.equalsIgnoreCase("yes"));
    return yesOrNo;
    }
    
}