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
public class State {
    private String stateName;
    private double taxRate;
    
    
    public State(String state, double taxRate) {
        this.stateName = state;
        this.taxRate = taxRate;
    }
    
    public String getState() {
        return stateName;
    }

    public double getTaxRate() {
        return taxRate;
    }
    
    
    
}
