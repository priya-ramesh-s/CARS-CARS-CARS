package com.SystemManagementRentalCar;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        CarManagementService management = new CarManagementService();
        // load in both databases so session can continue from last time
        management.openDatabaseAvailable();
        management.openDatabaseRented();

        Scanner userInput = new Scanner(System.in);
        boolean sessionRunning = true;
        while (sessionRunning) {
            System.out.println("\nSelect an option");
            System.out.println(" 1. Enter Car management team section");
            System.out.println(" 2. Enter Customer section");
            System.out.println(" 3. Exit the entire session\n");
            int userType = userInput.nextInt();
            switch(userType) {
                // Are you part of the car management team or are you a customer?
                case 1:
                    boolean carManagementLoggedIn = true;
                    while (carManagementLoggedIn) {
                        //Car Management Team
                        sleep(1000);
                        System.out.println("\nSelect an option");
                        sleep(1000);
                        System.out.println(" 1. Add a new car");
                        System.out.println(" 2. View Available cars");
                        System.out.println(" 3. View Rented cars");
                        System.out.println(" 4. Log out\n");
                        int managementTeamOptions = userInput.nextInt();
                        switch(managementTeamOptions) {
                            case 1:
                                management.addNewCar();
                                break;
                            case 2:
                                management.displayAvailableCars();
                                break;
                            case 3:
                                management.displayRentedCars();
                                break;
                            case 4:
                                carManagementLoggedIn = false;
                                break;
                        }
                    }
                    break;

                case 2:
                    // Customer side
                    System.out.println("Hi welcome to CARS CARS CARS rental service!");
                    sleep(1000);
                    boolean customerLoggedIn = true;
                    while (customerLoggedIn) {
                        System.out.println("\nSelect an option");
                        sleep(1000);
                        System.out.println(" 1. View available cars");
                        System.out.println(" 2. Rent a car");
                        System.out.println(" 3. Return a car");
                        System.out.println(" 4. Log out\n");
                        int customerOptions = userInput.nextInt();
                        switch(customerOptions) {
                            case 1:
                                // Customer wants to view available cars
                                boolean customerViewingAvailableCars = true;
                                while(customerViewingAvailableCars) {
                                    management.displayAvailableCars();
                                    sleep(4000);
                                    System.out.println("press any key to stop viewing the cars");
                                    String exitViewingCars = userInput.next();
                                    customerViewingAvailableCars = false;
                                }
                                break;
                            case 2:
                                System.out.println("Please enter your name");
                                String name = userInput.next();
                                System.out.println("Please enter your age");
                                int age  = userInput.nextInt();
                                System.out.println("Please enter your Drivers Licence reference");
                                String driversLicence = userInput.next();
                                System.out.println("Please enter whether you would like to use credit or debit");
                                String payment = userInput.next();
                                Customer customer = new Customer(name, age, driversLicence, payment);
                                boolean customerBooking = management.canCustomerBookCar(customer);

                                if (customerBooking) {
                                    long rentalPeriod = management.rentalPeriodCalc();
                                    System.out.println("Please select one of these available cars");
                                    sleep(1500);
                                    management.displayAvailableCars();
                                    sleep(1000);
                                    System.out.println("Enter the make of the car you would like");
                                    String carMake = userInput.next();
                                    System.out.println("Enter the model of the car you would like");
                                    String carModel = userInput.next();
                                    int carDailyRentPrice = management.booking(carMake, carModel);
                                    management.calculateBill(carDailyRentPrice, rentalPeriod);

                                } else {
                                    System.out.println("Sorry you can't book a car");
                                }
                                break;
                            case 3:
                                System.out.println("Enter the registration number of the car you would like to return");
                                String regNum = userInput.next();
                                management.returnCar(regNum);
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


    static void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}