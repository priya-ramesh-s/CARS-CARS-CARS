package com.SystemManagementRentalCar;

import java.io.Serializable;

public class Car2 implements Serializable {
    private String carMake;  //fiat
    private String carModel;  //500
    private int rentPrice;
    private String regNum;
    //private Customer tempOwner;
    private String permenantOwner;

    // Constructor
    public Car2() {
        this.permenantOwner = "Cars Cars Cars";
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

}