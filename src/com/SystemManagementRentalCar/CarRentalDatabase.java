package com.SystemManagementRentalCar;


import java.io.*;
import java.util.ArrayList;


public class CarRentalDatabase {

    private ArrayList<Car2> cars;

    // Constructor
    public CarRentalDatabase() {
        this.cars = new ArrayList<Car2>();
    }

    // Getter Method
    public ArrayList<Car2> getCars() { return this.cars; }

    // Methods

    public void add(Car2 car) {
        this.cars.add(car);
    }

    public Car2 remove(String Make, String Model) {
        // used if car is being rented, so being removed from available database
        Car2 removeCar = null;
        for (Car2 car : this.cars) {
            if (car.getCarMake().equals(Make) && car.getCarModel().equals(Model)) {
                removeCar = car;
                this.cars.remove(car);
                break;
            }
        }
        return removeCar;
    }

    public Car2 remove(String regNum) {
        // used if car is being returned, so being removed from rented database
        Car2 removeCar = null;
        for (Car2 car : this.cars) {
            if (car.getRegNum().equals(regNum)) {
                removeCar = car;
                this.cars.remove(car);
                break;
            }
        }
        return removeCar;
    }

    public void saveData(String filepath) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filepath);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        for (Car2 car : this.cars) {
            out.writeObject(car);
        }
        out.close();
        fileOut.close();
    }

    public void loadData(String filepath) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filepath);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        while (true) {
            Car2 car = (Car2) in.readObject();
            if (car == null) {
                break;
            }
            this.cars.add(car);
        }
    }
}














//
//
//    @Override
//    public String toString() {
//        return "CarManagement{" + "ID=" + ID + ", \ncustomer=" + customer.toString() + ", \ncar2=" + car2.toString() + ", \nRentTime=" + RentTime + ", ReturnTime=" + ReturnTime + '}' + "\n";
//    }





    /*public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }*/

//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//
//    public long getRentTime() {
//        return RentTime;
//    }
//
//    public void setRentTime(long rentTime) {
//        RentTime = rentTime;
//    }
//
//    public long getReturnTime() {
//        return ReturnTime;
//    }
//
//    public void setReturnTime(long returnTime) {
//        ReturnTime = returnTime;
//    }




