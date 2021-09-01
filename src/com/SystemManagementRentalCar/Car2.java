package com.SystemManagementRentalCar;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Car2 implements Serializable {
    private String carMake;
    private String carModel;
    private BigDecimal rentPrice;
    private String regNum;
    private String permenantOwner;
    private ArrayList<LocalDate> rentalPeriods;
    private String description;

    // Constructor
    public Car2(String make, String model, BigDecimal rentPrice, String regNum) {
        this.permenantOwner = "Cars Cars Cars";
        this.rentalPeriods = new ArrayList<LocalDate>();
        this.description = "It's a car... what more do you even need to know??";
        this.carMake = make;
        this.carModel = model;
        this.rentPrice = rentPrice;
        this.regNum = regNum;
    }

    // Setter Method
    public void setDescription(String description) { this.description = description; }

    // Getter Methods
    public String getCarMake() {
        return this.carMake;
    }
    public String getCarModel() {
        return this.carModel;
    }
    public String getRegNum() {
        return this.regNum;
    }
    public BigDecimal getRentPrice() {
        return this.rentPrice;
    }
    public ArrayList<LocalDate> getRentalPeriods() { return rentalPeriods; }
    public String getDescription() { return description; }
}