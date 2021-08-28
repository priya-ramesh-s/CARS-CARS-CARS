package com.SystemManagementRentalCar;

import java.util.ArrayList;
import java.math.BigDecimal;

public class CarRentalDatabase {
    private ArrayList<Car2> cars;

    // constructor
    public CarRentalDatabase() {
        this.cars = new ArrayList<Car2>();
    }

    // Getter method
    public ArrayList<Car2> getCars() {
        return this.cars;
    }

    //methods
    public void addNewCars(String make, String model, int rentPrice, String regNum) {
        //adds car to arraylist of cars
        Car2 localCar;
        localCar = new Car2(make, model, rentPrice, regNum);
        this.cars.add(localCar);
    }

    public Car2 remove(String make, String model) {
        // if you are removing an available car to be rented
        Car2 removedCar = null;
        for (Car2 car : cars) {
            if (car.getCarMake().equals(make) && car.getCarModel().equals(model)) {
                removedCar = car;
                this.cars.remove(car);
                break;
            }
        }
        return removedCar;
    }

    public Car2 remove(String regNum){
        // if you are returning a rented car to be placed back in available
        Car2 removedCar = null;
        for (Car2 car : cars) {
            if (car.getRegNum().equals(regNum)) {
                removedCar = car;
                this.cars.remove(car);
                break;
            }
        }
        return removedCar;
    }

    public void add(Car2 car) {
        //adds car to arraylist of cars
        this.cars.add(car);
    }
}

