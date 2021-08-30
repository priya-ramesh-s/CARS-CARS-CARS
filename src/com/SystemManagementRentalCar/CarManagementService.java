package com.SystemManagementRentalCar;

import java.io.IOException;
import java.util.Scanner;

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

    public void addNewCar() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the car make");
        String make = userInput.next();
        System.out.println("Enter the car model");
        String model = userInput.next();
        System.out.println("Enter the daily price to rent this car");
        int rentPrice = userInput.nextInt();
        System.out.println("Enter the registration number of this car");
        String regNum = userInput.next();

        Car2 newCar = new Car2();
        newCar.setCarMake(make);
        newCar.setCarModel(model);
        newCar.setRentPrice(rentPrice);
        newCar.setRegNum(regNum);
        this.availableDatabase.addNewCars(newCar);
    }

    public void displayAvailableCars() {
        System.out.println("These cars are currently available");
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
        Scanner userInput = new Scanner(System.in);

//        System.out.println("Please enter your mobile number");
//        int mobileNumber = userInput.nextInt();
//        customer.setMobileNum(mobileNumber);
        System.out.println("Please enter your Drivers Licence reference");
        String DriversLicence = userInput.next();
        customer.setDriversLicence(DriversLicence);
        System.out.println("Please enter whether you would like to use credit or debit");
        String payment = userInput.next();
        customer.setPaymentMethod(payment);

        int age = customer.getAge();
        String paymentMethod = customer.getPaymentMethod();
        String licence = customer.getDriversLicence();

        boolean check1 = false;
        boolean check2 = false;
        boolean check3 = false;

        if (age >= 25) {
            check1 = true;
        } else {
            System.out.println("Sorry you need to be 25 or over to rent a car.");
        }
        if (paymentMethod.equals("credit") || paymentMethod.equals("debit")) {
            check2 = true;
        } else {
            System.out.println("Sorry you need to use a credit card or debit card to rent a car.");
        }

        String regex = "^[a-zA-Z0-9]{10,20}$";
        if (licence.matches(regex)) {
            check3 = true;
        } else {
            System.out.println("Sorry you have entered an incorrect licence number");
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
    }
}
