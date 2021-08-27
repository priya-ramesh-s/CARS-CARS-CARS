package com.SystemManagementRentalCar;

import java.math.BigDecimal;

public class Car1 {
    private String carBrand;
    private boolean available;
    private BigDecimal rentPrice;
    private String id;

    public Car1(String name, BigDecimal rentPrice, String id) {
        this.carBrand = name;
        this.rentPrice = rentPrice;
        this.id = id;
    }
}
