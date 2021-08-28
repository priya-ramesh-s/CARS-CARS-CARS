package com.SystemManagementRentalCar;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        // populate cars
        CarManagementService management = new CarManagementService();
        // openDatabase so program can continue from last session
        //management.openDatabase();

        Scanner userInput = new Scanner(System.in);

//        switch() {
//
//        }
        //put in while loop

        // Adding a car to the database, through using addNewCar, method in CarManagementService
        boolean addNewCar = true;
        int i = 0;
        while(addNewCar) {
            String carModel = "Fiat";
            String carMake = "500";
            int rentPrice = 100;
            System.out.println("Enter car registration number");
            String regNum = userInput.nextLine();
            management.addNewCar(carModel, carMake, rentPrice, regNum);
            i++;
            if (i == 4) {
                addNewCar = false;
            }
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

        // writing to database
        System.out.println("Do you want to exit? (y/n)");
        String exit = userInput.nextLine();

        management.closeDatabase();
    }
}
