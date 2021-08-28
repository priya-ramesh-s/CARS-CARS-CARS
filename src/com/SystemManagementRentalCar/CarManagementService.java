package com.SystemManagementRentalCar;

public class CarManagementService {
    private CarRentalDatabase availableDatabase;
    private CarRentalDatabase rentedDatabase;


    // constructor
    public CarManagementService() {
        this.availableDatabase = new CarRentalDatabase();
        this.rentedDatabase = new CarRentalDatabase();
    }

    // methods

    public void addNewCar(String model, String make, int rentPrice, String regNum) {
        this.availableDatabase.addNewCars(model, make, rentPrice, regNum);
    }

    public void displayAvailableCars() {
        for (Car2 availableCar : this.availableDatabase.getCars()) {
            System.out.println(availableCar.getCarMake() + " " + availableCar.getCarModel());
        }
    }

    public void displayRentedCars() {
        for (Car2 rentedCar : this.rentedDatabase.getCars()) {
            System.out.println(rentedCar.getCarMake() + " " + rentedCar.getCarModel());
        }
    }

    public void booking(String make, String model) {
        Car2 rentCar = availableDatabase.remove(make, model);
        rentedDatabase.add(rentCar);
        System.out.println("You have successfully booked a " + rentCar.getCarMake() + " " + rentCar.getCarModel() + " with registration number: " + rentCar.getRegNum());
        System.out.println("This will cost you " + rentCar.getRentPrice() + " per day");
    }

    public void returnCar(String regNum) {
        Car2 availableCar = rentedDatabase.remove(regNum);
        availableDatabase.add(availableCar);
    }
}
