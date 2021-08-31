package com.SystemManagementRentalCar;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

public class CarManagementService {
    private CarRentalDatabase availableDatabase;
    private CarRentalDatabase rentedDatabase;
    private String objFilepathAvailable;
    private String objFilepathRented;
    private String filepathRented;
    private String filepathAvailable;


    // constructor
    public CarManagementService() {
        this.availableDatabase = new CarRentalDatabase();
        this.rentedDatabase = new CarRentalDatabase();
        this.objFilepathAvailable = "C:/Users/Sanchayata/Documents/available_cars.txt";  // enter filepath where you would like available cars info to be stored
        this.objFilepathRented = "C:/Users/Sanchayata/Documents/rented_cars.txt";     // enter filepath where you would like rented cars info to be stored
        this.filepathRented = "C:/Users/Sanchayata/Documents/rented_cars_readable.txt";
        this.filepathAvailable = "C:/Users/Sanchayata/Documents/available_cars_readable.txt";
    }

    // methods

    public void openDatabaseRented() {
        try {
            this.rentedDatabase.loadObjData(this.objFilepathRented);
            //this.availableDatabase.loadData(this.filepath_available);
        } catch(ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void openDatabaseAvailable() {
        try {
            this.availableDatabase.loadObjData(this.objFilepathAvailable);
        } catch(ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeDatabase() {
        try {
            this.availableDatabase.saveObjData(this.objFilepathAvailable);
            this.rentedDatabase.saveObjData(this.objFilepathRented);
            this.availableDatabase.saveReadableData(this.filepathAvailable);
            this.rentedDatabase.saveReadableData(this.filepathRented);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addNewCar() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the car make");
        String make = userInput.nextLine();
        System.out.println("Enter the car model");
        String model = userInput.nextLine();
        System.out.println("Enter the daily price to rent this car");
        int rentPrice = userInput.nextInt();
        System.out.println("Enter the registration number of this car");
        String regNum = userInput.next();

        Car2 newCar = new Car2();
        newCar.setCarMake(make);
        newCar.setCarModel(model);
        newCar.setRentPrice(rentPrice);
        newCar.setRegNum(regNum);
        this.availableDatabase.add(newCar);
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
        Scanner userInput = new Scanner(System.in);

        int age = customer.getAge();
        String paymentMethod = customer.getPaymentMethod();
        String licence = customer.getDrivingLicence();


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


    public int booking(String make, String model) {
        Car2 rentCar = availableDatabase.remove(make, model);
        //rentCar.setTempOwner(customer);
        rentedDatabase.add(rentCar);
        System.out.println("You have successfully booked a " + rentCar.getCarMake() + " " + rentCar.getCarModel() + " with registration number: " + rentCar.getRegNum());
        System.out.println("This will cost you " + rentCar.getRentPrice() + " per day");

        return rentCar.getRentPrice();
    }

    public void returnCar(String regNum) {
        boolean rentedCarFound = false;
        for (Car2 rentedCar : rentedDatabase.getCars()) {
            if (rentedCar.getRegNum().equals(regNum)) {
                Car2 availableCar = rentedDatabase.remove(regNum);
                availableDatabase.add(availableCar);
                System.out.println("You have successfully returned the car. Thank you, we hope to see you again soon!");
                rentedCarFound = true;
                break;
            }
        }
        if (!rentedCarFound) {
            System.out.println("Sorry this registration number is incorrect.");
        }
    }

    public long rentalPeriodCalc() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Rental Start Date: yyyy-mm-dd");
        String rentalStartDate = scanner.nextLine();
        LocalDate startLocalDate = LocalDate.parse(rentalStartDate);
        System.out.println("Please enter Rental End Date: yyyy-mm-dd");
        String rentalEndDate = scanner.nextLine();
        LocalDate endLocalDate = LocalDate.parse(rentalEndDate);
        long rentalPeriod = DAYS.between(startLocalDate, endLocalDate);

        return rentalPeriod;
    }


    public void calculateBill(int carDailyFee, long rentalPeriod) {
        float totalBill = rentalPeriod * carDailyFee;
        System.out.println("As you have booked this car for " + rentalPeriod + "day(s), your total bill is: ");
        System.out.println(totalBill);
    }
}
