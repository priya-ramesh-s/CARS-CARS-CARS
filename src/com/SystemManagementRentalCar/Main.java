package com.SystemManagementRentalCar;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        // populate cars
        CarManagementService management = new CarManagementService();
        // load in both databases so session can continue from last time
        management.openDatabaseAvailable();
        management.openDatabaseRented();

        Scanner userInput = new Scanner(System.in);
        boolean sessionRunning = true;
        while (sessionRunning) {
            System.out.println("Select an option");
            System.out.println(" 1. Car management team");
            System.out.println(" 2. Customer");
            System.out.println(" 3. Exit the session");
            int userType = userInput.nextInt();
            switch(userType) {
                // Are you part of the car management team or are you a customer?
                case 1:
                    boolean carManagementLoggedIn = true;
                    while (carManagementLoggedIn) {
                        //Car Management Team
                        System.out.println("Select an option");
                        System.out.println(" 1. Add a new car");
                        System.out.println(" 2. Log out");
                        int managementTeamOptions = userInput.nextInt();
                        switch(managementTeamOptions) {
                            case 1:
                                // add new car (car which didn't exist in either available or rented) to available
                                System.out.println("Enter the car make");
                                String Make = userInput.next();
                                System.out.println("Enter the car model");
                                String Model = userInput.next();
                                System.out.println("Enter the daily price to rent this car");
                                int DailyPrice = userInput.nextInt();
                                System.out.println("Enter the registration number of this car");
                                String RegNum = userInput.next();
                                management.addNewCar(Make, Model, DailyPrice, RegNum);
                                break;

                            case 2:
                                carManagementLoggedIn = false;
                                break;
                        }
                    }

                    break;

                case 2:
                    // Customer side
                    System.out.println("Hi welcome to CARS CARS CARS rental service!");
                    boolean customerLoggedIn = true;
                    while (customerLoggedIn) {
                        System.out.println("Select an option");
                        System.out.println(" 1. View available cars");
                        System.out.println(" 2. Rent a car");
                        System.out.println(" 3. Return a car");
                        System.out.println(" 4. Log out");
                        int customerOptions = userInput.nextInt();
                        switch(customerOptions) {
                            case 1:
                                // Customer wants to view available cars
                                System.out.println("These cars are currently available");
                                management.displayAvailableCars();
                                break;

                            case 2:
                                System.out.println("Please enter your name");
                                String name = userInput.next();
                                System.out.println("Please enter your age");
                                int age  = userInput.nextInt();
                                Customer customer = new Customer(name, age);
                                System.out.println("Please enter your mobile number");
                                int mobileNumber = userInput.nextInt();
                                customer.setMobileNum(mobileNumber);
                                System.out.println("Please enter your Drivers Licence reference");
                                String DriversLicence = userInput.next();
                                customer.setDriversLicence(DriversLicence);
                                System.out.println("Please enter whether you would like to use credit or debit");
                                String paymentMethod = userInput.next();
                                customer.setPaymentMethod(paymentMethod);
                                boolean customerBooking = management.canCustomerBookCar(customer);

                                if (customerBooking) {
                                    System.out.println("Please select one of these available cars");
                                    management.displayAvailableCars();
                                    System.out.println("Enter the make of the car you would like");
                                    String carMake = userInput.next();
                                    System.out.println("Enter the model of the car you would like");
                                    String carModel = userInput.next();
                                    management.booking(carMake, carModel, customer);
                                } else {
                                    System.out.println("Sorry you can't book a car");

                                }

                                break;

                            case 3:
                                boolean returningCar = true;
                                while (returningCar) {
                                    // Customer wants to return a car
                                    System.out.println("Enter the registration number of the car you would like to return");
                                    String regNum = userInput.next();
                                    boolean carReturned = management.returnCar(regNum);
                                    if (!carReturned) {
                                        returningCar = false;
                                    }
                                }
                                break;

                            case 4:
                                customerLoggedIn = false;
                                break;
                        }
                    }
                    break;

                case 3:
                    sessionRunning = false;
            }
        }
        management.closeDatabase();
    }
}
