package com.SystemManagementRentalCar;

import java.io.File;
import java.io.IOException;

public class CarManagementService {
    private CarRentalDatabase availableDatabase;
    private CarRentalDatabase rentedDatabase;
    private String filepath_available;
    private String filepath_rented;


    // constructor
    public CarManagementService() {
        this.availableDatabase = new CarRentalDatabase();
        this.rentedDatabase = new CarRentalDatabase();
        this.filepath_available = "C:/Users/Sanchayata/Documents/available_cars.txt";  // enter filepath where you would like available cars info to be stored
        this.filepath_rented = "C:/Users/Sanchayata/Documents/rented_cars.txt";     // enter filepath where you would like rented cars info to be stored
    }

    // methods

    public void openDatabase() {
        try {
            this.availableDatabase.loadData(this.filepath_available);
            this.rentedDatabase.loadData(this.filepath_rented);
        } catch(ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

//    public void closeDatabase() {
//        try {
//            this.availableDatabase.saveData(this.filepath_available);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            this.rentedDatabase.saveData(this.filepath_rented);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void closeDatabase() {
        try {
            this.availableDatabase.saveData(this.filepath_available);
            this.rentedDatabase.saveData(this.filepath_rented);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void addNewCar(String model, String make, int rentPrice, String regNum) {
        this.availableDatabase.addNewCars(model, make, rentPrice, regNum);
    }

    public void displayAvailableCars() {
        for (Car2 availableCar : this.availableDatabase.getCars()) {
            System.out.println(availableCar.getCarMake() + " " + availableCar.getCarModel());
        }
    }

    public void displayRentedCars() {
        for (Car2 rentedCar : this.rentedDatabase.getCars()) {
            System.out.println(rentedCar.getCarMake() + " " + rentedCar.getCarModel());
        }
    }

    public void booking(String make, String model) {
        Car2 rentCar = availableDatabase.remove(make, model);
        rentedDatabase.add(rentCar);
        System.out.println("You have successfully booked a " + rentCar.getCarMake() + " " + rentCar.getCarModel() + " with registration number: " + rentCar.getRegNum());
        System.out.println("This will cost you " + rentCar.getRentPrice() + " per day");
    }

    public void returnCar(String regNum) {
        Car2 availableCar = rentedDatabase.remove(regNum);
        availableDatabase.add(availableCar);
        System.out.println("You have successfully returned the car. Thank you, we hope to see you again soon!");
    }
}
