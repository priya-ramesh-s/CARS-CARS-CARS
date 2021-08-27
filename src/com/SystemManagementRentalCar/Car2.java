package com.SystemManagementRentalCar;

import java.math.BigDecimal;

public class Car2 {
    private String carBrand;
    private boolean available;
    private BigDecimal rentPrice;
    private String id;

    // Constructor
    public Car2(String name, BigDecimal rentPrice, String id) {
        this.carBrand = name;
        this.available = true;
        this.rentPrice = rentPrice;
        this.id = id;
    }

    // Setter Method
    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Getter Methods
    public String getCarBrand() { return this.carBrand; }
    public String getId() { return this.id; }
    public boolean getAvailable() { return this.available; }
    public BigDecimal getRentPrice() { return this.rentPrice; }
}