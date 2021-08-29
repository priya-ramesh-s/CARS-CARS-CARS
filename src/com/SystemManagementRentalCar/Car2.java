package com.SystemManagementRentalCar;

import java.io.Serializable;

public class Car2 implements Serializable {
    private String carMake;  //fiat
    private String carModel;  //500
    private int rentPrice;
    private String regNum;

    // Constructor
    public Car2(String carMake, String carModel, int rentPrice, String regNum) {
        this.carMake = carMake;
        this.carModel = carModel;
        this.rentPrice = rentPrice;
        this.regNum = regNum;
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