package com.SystemManagementRentalCar;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
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

//    public void saveData(String filepath) throws IOException {
//        File file = new File(filepath);
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//        FileWriter fileWriter = new FileWriter(file);
//        PrintWriter printWriter = new PrintWriter(fileWriter);
//        //printWriter.println("Make " + "Model " + "Registration Number " + "Daily Rent Price");
//        for (Car2 car : this.cars) {
//            printWriter.println(car.getCarMake() + " " + car.getCarModel() + " " + car.getRegNum() + " " + car.getRentPrice());
//        }
//
//
//        printWriter.flush();
//        printWriter.close();
//    }

    public void saveData(String filepath) throws IOException{
        FileOutputStream fileOut = new FileOutputStream(filepath);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for (Car2 car : this.cars) {
                out.writeObject(car);
            }
        //Car2 car = this.cars.get(0);
        //out.writeObject(car);
//        out.close();
//        fileOut.close();
    }

    public void loadData(String filepath) throws IOException, ClassNotFoundException{
//        Scanner read = new Scanner(filepath);
//        while (read.hasNext()) {
//            System.out.println(read.nextLine());
//        }
        //boolean fileNotEnd = true;
//        while (true) {
        FileInputStream fileIn = new FileInputStream(filepath);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        ArrayList<Car2> deserializeCar = (ArrayList<Car2>)in.readObject();
        in.close();
        for (Car2 car : deserializeCar) {
            System.out.println(car.getCarMake());
        }

//            Car2 car = (Car2)in.readObject();
//            System.out.println("hi");
//            if (car == null) {
//                System.out.println("entered if");
//                break;
//            }
//            this.cars.add(car);
//        }
//        System.out.println("Exited while loop");

        //System.out.println(car.getCarMake()+" "+car.getCarModel()+ " "+car.getRegNum()+ " "+car.getRentPrice());

//        for (Car2 car : this.cars) {
//            in.readObject(car);
//        }
    }
}

