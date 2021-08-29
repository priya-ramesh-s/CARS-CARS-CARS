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
            System.out.println("Enter 1 if you are part of the car management team or press 2 if you are a customer or press 3 to exit session.");
            int userType = userInput.nextInt();
            switch(userType) {
                // Are you part of the car management team or are you a customer?
                case 1:
                    boolean carManagementLoggedIn = true;
                    while (carManagementLoggedIn) {
                        //Car Management Team
                        System.out.println("Enter 1 if you would like to add a new car or press 2 to log out");
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
                    System.out.println("Hi welcome to CARS CARS CARS rental service!");
                    boolean customerLoggedIn = true;
                    while (customerLoggedIn) {
                        System.out.println("Enter 1 if you would like to rent a car or enter 2 if you would like to return a car or enter 3 if you would like to log out");
                        int customerOptions = userInput.nextInt();
                        switch(customerOptions) {
                            case 1:
                                // Customer is renting a car
                                System.out.println("currently these cars are available");
                                management.displayAvailableCars();
                                System.out.println("Enter the make of the car you would like");
                                String carMake = userInput.next();
                                System.out.println("Enter the model of the car you would like");
                                String carModel = userInput.next();
                                management.booking(carMake, carModel);
                                break;
                            case 2:
                                // Customer wants to return a car
                                System.out.println("Here are the cars which are currently being rented");
                                management.displayRentedCars();
                                System.out.println("Enter the registration number of the car you would like to return");
                                String regNum = userInput.next();
                                management.returnCar(regNum);
                                break;
                            case 3:
                                customerLoggedIn = false;
                                break;

                        }
                    }
                    break;
                case 3:
                    System.out.println("Do you want to exit? (y/n)");
                    String exit = userInput.next();
                    if (exit.equals("n")) {
                        break;
                    } else {
                        sessionRunning = false;
                    }

            }
        }
        management.closeDatabase();
    }
}
