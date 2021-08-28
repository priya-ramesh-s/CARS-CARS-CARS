package com.SystemManagementRentalCar;

public class Car2 {
    private String carMake;  //fiat
    private String carModel;  //500
    private boolean available;
    private int rentPrice;
    private String regNum;

    // Constructor
    public Car2(String carMake, String carModel, int rentPrice, String regNum) {
        this.carMake = carMake;
        this.carModel = carModel;
        this.available = true;
        this.rentPrice = rentPrice;
        this.regNum = regNum;
    }

    // Setter Method
    public void setAvailable(boolean available) {
        this.available = available;
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

    public boolean getAvailable() {
        return this.available;
    }

    public int getRentPrice() {
        return this.rentPrice;
    }

}