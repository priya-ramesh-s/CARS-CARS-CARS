package com.SystemManagementRentalCar;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // populate cars
        CarManagementService mybs = new CarManagementService();
        Scanner userInput = new Scanner(System.in);

//        switch() {
//
//        }
        //put in while loop

        boolean addNewCar = true;
        while(addNewCar) {
            System.out.println("Do you want to add a new car (y/n)");
            String input = userInput.nextLine();
            if (input.equals("y")) {
                String carModel = "Fiat";
                String carMake = "500";
                int rentPrice = 100;
                System.out.println("Enter car registration number");
                String regNum = userInput.nextLine();
                mybs.addNewCar(carModel, carMake, rentPrice, regNum);
            } else {
                addNewCar = false;
            }
        }

        mybs.displayAvailableCars();
        System.out.println("Do you want to rent a car? (y/n)");
        String rentCar = userInput.nextLine();
        if (rentCar.equals("y")) {
            System.out.println("Enter the make of the car you would like");
            String carMake = userInput.nextLine();
            System.out.println("Enter the model of the car you would like");
            String carModel = userInput.nextLine();
            mybs.booking(carMake, carModel);


            System.out.println("Available cars left now one has been rented");
            mybs.displayAvailableCars();
            System.out.println("Rented Cars");
            mybs.displayRentedCars();
        }

        //return car
        System.out.println("Enter the registration number of the car you would like to return");
        String regNum = userInput.nextLine();
        mybs.returnCar(regNum);

        System.out.println("Available cars left now one has been RETURNED");
        mybs.displayAvailableCars();
        System.out.println("Rented Cars now one has been removed");
        mybs.displayRentedCars();

    }
}
