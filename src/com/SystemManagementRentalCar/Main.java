package com.SystemManagementRentalCar;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW1
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED


    public static void main(String[] args) throws IOException {
        CarManagementService management = new CarManagementService();
        // load in both databases so session can continue from last time
        management.openDatabaseAvailable();
        management.openDatabaseRented();
        management.setTodaysRentedCars();

        Scanner userInput = new Scanner(System.in);
        boolean sessionRunning = true;
        while (sessionRunning) {
            System.out.println(BLUE_BACKGROUND_BRIGHT+ BLACK_BOLD_BRIGHT + "Select an option" + ANSI_RESET);
            System.out.println(BLUE_BOLD_BRIGHT + " 1. Enter Car management team section");
            System.out.println(" 2. Enter Customer section");
            System.out.println(" 3. Exit the entire session" + ANSI_RESET);
            int userType = userInput.nextInt();
            switch(userType) {
                // Are you part of the car management team or are you a customer?
                case 1:
                    boolean carManagementLoggedIn = true;
                    while (carManagementLoggedIn) {
                        //Car Management Team
                        sleep(1000);
                        System.out.println(YELLOW_BACKGROUND_BRIGHT + BLACK_BOLD_BRIGHT+ "Select an option" + ANSI_RESET);
                        sleep(1000);
                        System.out.println(YELLOW_BOLD_BRIGHT + " 1. Add a new car");
                        System.out.println(" 2. View Available cars");
                        System.out.println(" 3. View Rented cars");
                        System.out.println(" 4. Log out\n" + ANSI_RESET);
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
                    System.out.println(YELLOW_BACKGROUND_BRIGHT + BLACK_BOLD_BRIGHT +"Hi welcome to CARS CARS CARS rental service!" +ANSI_RESET);
                    sleep(1000);
                    System.out.println(YELLOW_BOLD_BRIGHT+ "Please enter Rental Start Date: yyyy-mm-dd");
                    String rentalStartDate = userInput.next();
                    System.out.println("Please enter Rental End Date: yyyy-mm-dd"+ANSI_RESET);
                    String rentalEndDate = userInput.next();
                    LocalDate startDate = LocalDate.parse(rentalStartDate);
                    LocalDate endDate = LocalDate.parse(rentalEndDate);
                    boolean customerLoggedIn = true;
                    while (customerLoggedIn) {
                        ArrayList<LocalDate> requestedRentalDates = management.rentalDaysCalc(startDate,
                                                                                              endDate);

                        System.out.println(BLUE_BACKGROUND_BRIGHT+ BLACK_BOLD_BRIGHT+"Select an option" + ANSI_RESET);
                        sleep(1000);
                        System.out.println(BLUE_BOLD_BRIGHT + " 1. View available cars");
                        System.out.println(" 2. Rent a car");
                        System.out.println(" 3. Return a car");
                        System.out.println(" 4. Log out" + ANSI_RESET);
                        int customerOptions = userInput.nextInt();
                        switch(customerOptions) {
                            case 1:
                                // Customer wants to view available cars
                                boolean customerViewingAvailableCars = true;
                                while(customerViewingAvailableCars) {
                                    management.displayAvailableCars(startDate, requestedRentalDates);
                                    sleep(4000);
                                    System.out.println(RED_BACKGROUND_BRIGHT + "press any key to stop viewing the cars" +ANSI_RESET);
                                    String exitViewingCars = userInput.next();
                                    customerViewingAvailableCars = false;
                                }
                                break;
                            case 2:
                                System.out.println(YELLOW_BACKGROUND_BRIGHT + BLACK_BOLD_BRIGHT +"Please enter your name");
                                String name = userInput.next();
                                System.out.println("Please enter your age");
                                int age = Integer.parseInt(userInput.next());
                                System.out.println("Please enter your Drivers Licence reference");
                                String driversLicence = userInput.next();
                                System.out.println("Please enter whether you would like to use credit or debit" +ANSI_RESET);
                                String payment = userInput.next();
                                Customer customer = new Customer(name, age, driversLicence, payment); //making an instance of customer class
                                boolean customerBooking = management.canCustomerBookCar(customer);

                                if (customerBooking) {

                                    System.out.println(BLUE_BACKGROUND_BRIGHT+ BLACK_BOLD_BRIGHT+ "Please select one of these available cars" +ANSI_RESET);
                                    sleep(1500);
                                    management.displayAvailableCars(startDate, requestedRentalDates);
                                    sleep(1000);
                                    System.out.println(YELLOW_BACKGROUND_BRIGHT + BLACK_BOLD_BRIGHT+ "Enter the make of the car you would like");
                                    String carMake = userInput.next();
                                    System.out.println("Enter the model of the car you would like" +ANSI_RESET);
                                    String carModel = userInput.next();
                                    BigDecimal carDailyRentPrice = management.booking(carMake, carModel, requestedRentalDates, rentalStartDate);
                                    long rentalPeriod = management.rentalPeriodCalc(startDate, endDate);
                                    management.calculateBill(carDailyRentPrice, rentalPeriod);

                                }
                                break;
                            case 3:
                                System.out.println(YELLOW_BACKGROUND_BRIGHT + BLACK_BOLD_BRIGHT+"Enter the registration number of the car you would like to return"+ANSI_RESET);
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