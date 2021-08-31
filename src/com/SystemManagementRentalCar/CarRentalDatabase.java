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


    public void saveReadableData(String filepath) throws IOException{
        // to save a txt file which is readable outside the session
        File file = new File(filepath);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(filepath);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (Car2 car : this.cars) {
            printWriter.println(car.getCarMake() + " " + car.getCarModel() + " " + car.getRegNum() + " " + car.getRentPrice());
            printWriter.println();
        }
        printWriter.flush();
        printWriter.close();
    }


    public void saveObjData(String filepathObj) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filepathObj);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        for (Car2 car : this.cars) {
            out.writeObject(car);
        }
        out.close();
        fileOut.close();
    }


    public void loadObjData(String filepathObj) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filepathObj);
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
