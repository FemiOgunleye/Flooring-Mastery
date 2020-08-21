/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.flooringmastery.dto;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author TheFemiFactor
 */
public class Orders {
    private LocalDate date;
    private int orderNumber;
    private String customerName;
    private State state;
    private String stateString;
    private Product type;
    private String typeString;
    private double area;
    private double materialCost;
    private double laborCost;
    private double tax;
    private double total;
    private double costPer2foot;
    private double laborCostPer2foot;
    
    @Override
    public String toString() {
        
        DecimalFormat df = new DecimalFormat("#,###,##0.00");
        
        return "Order Number: " + orderNumber + " |Date: " + date + 
            " Customer Name: " + customerName + " |State: " + stateString +
            " |Product: " + typeString + " |Area: " + (df.format(area)) +
    " |Material Cost: " + (df.format(materialCost)) + " |Cost per square foot: " + (df.format(costPer2foot)) +
" |Labor Cost: " + (df.format(laborCost))+ " |Labor cost per square foot: " + (df.format(laborCostPer2foot)) +
            " |Tax: " + (df.format(tax)) + " |Total: " + (df.format(total));
    }

    public String getStateString() {
        return stateString;
    }

    public double getCostPer2foot() {
        return costPer2foot;
    }

    public void setCostPer2foot(double costPer2foot) {
        this.costPer2foot = costPer2foot;
    }

    public double getLaborCostPer2foot() {
        return laborCostPer2foot;
    }

    public void setLaborCostPer2foot(double laborCostPer2foot) {
        this.laborCostPer2foot = laborCostPer2foot;
    }

    public void setStateString(String stateString) {
        this.stateString = stateString;
    }

    public String getTypeString() {
        return typeString;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }


    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Product getType() {
        return type;
    }

    public void setType(Product type) {
        this.type = type;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getMaterialCost() {
        materialCost = this.type.getCostPer2foot() * this.area;
        return materialCost;
    }
    
    public void setMaterialCost(Double materialCost) {
        this.materialCost = materialCost;
    }    


    public double getLaborCost() {
        laborCost = this.type.getLaborCostPer2foot() * this.area;
        return laborCost;
    }
    
    public void setLaborCost(Double laborCost) {
        this.laborCost = laborCost;
    }    

    public double getTax() {
        tax = this.state.getTaxRate() * (this.getMaterialCost() 
                + this.getLaborCost());
        return tax;
    }
    
    public void setTax(double tax) {
        this.tax = tax;
    }    

    public double getTotal() {
        total = tax + (this.getMaterialCost() + this.getLaborCost());
        return total;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }        

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + this.orderNumber;
        hash = 97 * hash + Objects.hashCode(this.customerName);
        hash = 97 * hash + Objects.hashCode(this.state);
        hash = 97 * hash + Objects.hashCode(this.stateString);
        hash = 97 * hash + Objects.hashCode(this.type);
        hash = 97 * hash + Objects.hashCode(this.typeString);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.area) ^ (Double.doubleToLongBits(this.area) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.materialCost) ^ (Double.doubleToLongBits(this.materialCost) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.laborCost) ^ (Double.doubleToLongBits(this.laborCost) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.tax) ^ (Double.doubleToLongBits(this.tax) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.total) ^ (Double.doubleToLongBits(this.total) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.costPer2foot) ^ (Double.doubleToLongBits(this.costPer2foot) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.laborCostPer2foot) ^ (Double.doubleToLongBits(this.laborCostPer2foot) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Orders other = (Orders) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (Double.doubleToLongBits(this.area) != Double.doubleToLongBits(other.area)) {
            return false;
        }
        if (Double.doubleToLongBits(this.materialCost) != Double.doubleToLongBits(other.materialCost)) {
            return false;
        }
        if (Double.doubleToLongBits(this.laborCost) != Double.doubleToLongBits(other.laborCost)) {
            return false;
        }
        if (Double.doubleToLongBits(this.tax) != Double.doubleToLongBits(other.tax)) {
            return false;
        }
        if (Double.doubleToLongBits(this.total) != Double.doubleToLongBits(other.total)) {
            return false;
        }
        if (Double.doubleToLongBits(this.costPer2foot) != Double.doubleToLongBits(other.costPer2foot)) {
            return false;
        }
        if (Double.doubleToLongBits(this.laborCostPer2foot) != Double.doubleToLongBits(other.laborCostPer2foot)) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.stateString, other.stateString)) {
            return false;
        }
        if (!Objects.equals(this.typeString, other.typeString)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }
    
    
    
}
