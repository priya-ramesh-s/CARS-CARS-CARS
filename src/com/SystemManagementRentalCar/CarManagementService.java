package com.SystemManagementRentalCar;

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

    public void openDatabaseRented() {
        try {
            this.rentedDatabase.loadData(this.filepath_rented);
            //this.availableDatabase.loadData(this.filepath_available);
        } catch(ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void openDatabaseAvailable() {
        try {
            this.availableDatabase.loadData(this.filepath_available);
        } catch(ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

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
            System.out.println(availableCar.getCarMake() + " " + availableCar.getCarModel() + " £" + availableCar.getRentPrice());
        }
    }

    public void displayRentedCars() {
        for (Car2 rentedCar : this.rentedDatabase.getCars()) {
            System.out.println(rentedCar.getCarMake() + " " + rentedCar.getCarModel() + " £" + rentedCar.getRentPrice());
        }
    }

    public boolean canCustomerBookCar(Customer customer) {
        // this method checks if the person is the correct age, has a licence, ID, correct payment etc..
        // if customer good then return true, if customer bad then return false
        int age = customer.getAge();
        String paymentMethod = customer.getPaymentMethod();
        String licence = customer.getDriversLicence();
        boolean check1 = false;
        boolean check2 = false;
        boolean check3 = true;

        if (age >= 25) {
            check1 = true;
        }
        if (paymentMethod.equals("credit") || paymentMethod.equals("debit")) {
            check2 = true;
        }

        if (check1 && check2 && check3) {
            return true;
        }
        return false;
    }

    public void booking(String make, String model, Customer customer) {
        Car2 rentCar = availableDatabase.remove(make, model);
        //rentCar.setTempOwner(customer);
        rentedDatabase.add(rentCar);
        System.out.println("You have successfully booked a " + rentCar.getCarMake() + " " + rentCar.getCarModel() + " with registration number: " + rentCar.getRegNum());
        System.out.println("This will cost you " + rentCar.getRentPrice() + " per day");
    }

    public void returnCar(String regNum) {
        boolean rentedCarFound = false;
        for (Car2 rentedCar : rentedDatabase.getCars()) {
            if (rentedCar.getRegNum().equals(regNum)) {
                Car2 availableCar = rentedDatabase.remove(regNum);
                availableDatabase.add(availableCar);
                System.out.println("You have successfully returned the car. Thank you, we hope to see you again soon!");
                //return true;
                rentedCarFound = true;
                break;
            }
        }
        if (!rentedCarFound) {
            System.out.println("Sorry this registration number is incorrect.");
        }

        //return false;
    }
}
