package com.SystemManagementRentalCar;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Date;
//inquirer.registerPrompt(name, prmopt);

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE

    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED1
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW1
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE






    public static void main(String[] args) throws IOException {
        //System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET); // colours
        CarManagementService management = new CarManagementService();
        // load in both databases so session can continue from last time
        management.openDatabaseAvailable();
        management.openDatabaseRented();



        Scanner userInput = new Scanner(System.in);
        boolean sessionRunning = true;
        while (sessionRunning) {
            System.out.println( RED_BACKGROUND_BRIGHT  +"Select an option"+ ANSI_RESET);
            System.out.println(RED_BOLD_BRIGHT +  " 1. Enter Car management team section" );
            System.out.println(" 2. Enter Customer section");
            System.out.println(" 3. Exit the entire session" +ANSI_RESET );
            int userType = userInput.nextInt();
            switch(userType) {
                // Are you part of the car management team or are you a customer?
                case 1:
                    boolean carManagementLoggedIn = true;
                    while (carManagementLoggedIn) {
                        //Car Management Team
                        sleep(1000);
                        System.out.println(YELLOW_BACKGROUND_BRIGHT + BLACK_BOLD_BRIGHT+ "Select an option" +ANSI_RESET);
                        sleep(1000);
                        System.out.println(YELLOW_BOLD_BRIGHT + " 1. Add a new car");
                        System.out.println(" 2. View Available cars");
                        System.out.println(" 3. View Rented cars");
                        System.out.println(" 4. Log out" +ANSI_RESET);
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
                    System.out.println(YELLOW_BACKGROUND_BRIGHT + BLACK_BOLD_BRIGHT + "Hi welcome to CARS CARS CARS rental service!" +ANSI_RESET);
                    sleep(1000);
                    boolean customerLoggedIn = true;
                    while (customerLoggedIn) {
                        System.out.println(YELLOW_BACKGROUND_BRIGHT + BLACK_BOLD_BRIGHT+ "Select an option" +ANSI_RESET);
                        sleep(1000);
                        System.out.println(YELLOW_BOLD_BRIGHT + " 1. View available cars");
                        System.out.println(" 2. Rent a car");
                        System.out.println(" 3. Return a car");
                        System.out.println(" 4. Log out" +ANSI_RESET);
                        int customerOptions = userInput.nextInt();
                        switch(customerOptions) {
                            case 1:
                                // Customer wants to view available cars
                                boolean customerViewingAvailableCars = true;
                                while(customerViewingAvailableCars) {
                                    management.displayAvailableCars();
                                    sleep(4000);
                                    System.out.println( RED_BACKGROUND_BRIGHT + "press any key to stop viewing the cars" +ANSI_RESET);
                                    String exitViewingCars = userInput.next();
                                    customerViewingAvailableCars = false;
                                }
                                break;
                            case 2:
                                System.out.println(YELLOW_BOLD_BRIGHT + "Please enter your name");
                                String name = userInput.next();
                                System.out.println("Please enter your age");
                                int age  = userInput.nextInt();
                                System.out.println("Please enter your Drivers Licence reference");
                                String driversLicence = userInput.next();
                                System.out.println("Please enter whether you would like to use credit or debit" +ANSI_RESET);
                                String payment = userInput.next();
                                Customer customer = new Customer(name, age, driversLicence, payment);
                                boolean customerBooking = management.canCustomerBookCar(customer);

                                if (customerBooking) {
                                    long rentalPeriod = management.rentalPeriodCalc();
                                    System.out.println(RED_BACKGROUND_BRIGHT + "Please select one of these available cars" +ANSI_RESET);
                                    sleep(1500);
                                    management.displayAvailableCars();
                                    sleep(1000);
                                    System.out.println(RED_BACKGROUND_BRIGHT + "Enter the make of the car you would like" +ANSI_RESET);
                                    String carMake = userInput.next();
                                    System.out.println(RED_BACKGROUND_BRIGHT + "Enter the model of the car you would like"+ANSI_RESET);
                                    String carModel = userInput.next();
                                    int carDailyRentPrice = management.booking(carMake, carModel);
                                    management.calculateBill(carDailyRentPrice, rentalPeriod);

                                } else {
                                    System.out.println( BLUE_BOLD_BRIGHT + "Sorry you can't book a car" +ANSI_RESET);
                                }
                                break;
                            case 3:
                                System.out.println(RED_BACKGROUND_BRIGHT + "Enter the registration number of the car you would like to return"+ANSI_RESET);
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