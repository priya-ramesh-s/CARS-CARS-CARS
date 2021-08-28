package com.SystemManagementRentalCar;

public class Main {
    public static void main(String[] args) {
        //CarManagementService management = new CarManagementService(); //create object
        //ask if customer or part of customer management team

        /*customer management team:
         switch statement:
         1. display available cars
         boolean available = true;
         management.displayAvailableCars();
         2. display rented cars
         management.displayRentedCars();
         3. Add returned car -> add returned cars back to available car database and remove from rented car database
         4. Add Rented Car -> remove rented car from available car database and add it to rented car database
         5. Add new car to rental shop -> adds new car to inventory and available
        */

        /*Customer:
        Customer customer1 = new Customer("Jane",......);
          switch statement:
          1. do you want to book a car?
          management.displayAvailableCars();
          Q: Enter the id number of the car you want to book from this selection or press n if you don't want to book anymore?
          => store answer in String chosenCarId
          boolean carAvailable = management.removeAvailableCar(chosenCarId);
          if (carAvailable) {
            management.addRentedCar(chosenCarId, customer1.getName, customer1.getDateOfBirth, customer1.getLicenseReg);
            customer1.bookCar(chosenCar);[
          } else {
            ("Please Enter the correct car ID");
          }

          2. do you want to return a car?

        */

    }
}
