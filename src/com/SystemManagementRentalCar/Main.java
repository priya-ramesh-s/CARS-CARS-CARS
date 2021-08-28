package com.SystemManagementRentalCar;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // populate cars
        CarManagementService management = new CarManagementService();
        Scanner userInput = new Scanner(System.in);

//        switch() {
//
//        }
        //put in while loop

        // Adding a car to the database, through using addNewCar, method in CarManagementService
        boolean addNewCar = true;
        while(addNewCar) {
            String carModel = "Fiat";
            String carMake = "500";
            int rentPrice = 100;
            System.out.println("Enter car registration number");
            String regNum = userInput.nextLine();
            management.addNewCar(carModel, carMake, rentPrice, regNum);
        }
        // display available cars
        management.displayAvailableCars();

        // Rent a car
        System.out.println("Enter the make of the car you would like");
        String carMake = userInput.nextLine();
        System.out.println("Enter the model of the car you would like");
        String carModel = userInput.nextLine();
        management.booking(carMake, carModel);


        // Return a car
        System.out.println("Enter the registration number of the car you would like to return");
        String regNum = userInput.nextLine();
        management.returnCar(regNum);
    }
}
