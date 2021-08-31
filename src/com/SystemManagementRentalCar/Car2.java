package com.SystemManagementRentalCar;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Car2 implements Serializable {
    private String carMake;
    private String carModel;
    private int rentPrice;
    private String regNum;
    //private Customer tempOwner;
    private String permenantOwner;
    private ArrayList<LocalDate> rentalPeriods;

    // Constructor
    public Car2() {
        this.permenantOwner = "Cars Cars Cars";
        this.rentalPeriods = new ArrayList<LocalDate>();
    }

    // Setter Methods
    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }



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

    public int getRentPrice() {
        return this.rentPrice;
    }

    public ArrayList<LocalDate> getRentalPeriods() { return rentalPeriods; }
}