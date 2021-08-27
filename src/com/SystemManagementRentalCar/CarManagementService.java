package com.SystemManagementRentalCar;

import java.math.BigDecimal;
import java.time.LocalDate;


public class CarManagementService {
    private BigDecimal dailyRentPrice;
    private CarRentalDatabase carsDatabase;

    // constructor
    public CarManagementService(BigDecimal rentFee) {
        this.dailyRentPrice = rentFee;
        this.carsDatabase = new CarRentalDatabase();
    }

    // methods
    public void displayAvailableCars(LocalDate startDate, LocalDate endDate) {

    }

    public void displayRentedCars() {
        //this.carsDatabase
    }

    public void removeRentedCar() {
        //this.carsDatabase
    }

}
