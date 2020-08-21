/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fo.flooringmastery.dto;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author TheFemiFactor
 */
public class Product {
    private String type;
    private double costPer2foot;
    private double laborCostPer2foot;
    
    public Product(String type, double costPer2foot, double laborCostPer2foot) {
        this.type = type;
        this.costPer2foot = costPer2foot;
        this.laborCostPer2foot = laborCostPer2foot;
    }
    
    public double getCostPer2foot() {
        return costPer2foot;
    }

    public double getLaborCostPer2foot() {
        return laborCostPer2foot;
    }

    public String getType() {
        return type;
    }
     
    
    
}
