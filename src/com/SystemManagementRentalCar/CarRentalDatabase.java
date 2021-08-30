package com.SystemManagementRentalCar;

//import date of year package

import org.xml.sax.ext.Locator2;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;




public class CarRentalDatabase {
    private int ID;
    private Customer customer;
    private ArrayList<Car2> cars;
    private long StartDate, ReturnDate; //stores system date


    public CarRentalDatabase(Customer customer,long startDate, long returnDate) {
        //this.ID = ID;
        //this.customer = new Customer();
        this.cars = new ArrayList<Car2>();
        this.StartDate = StartDate;
        this.ReturnDate = returnDate;
    }

    /*public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }*/

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getRentTime() {
        return RentTime;
    }

    public void setRentTime(long rentTime) {
        RentTime = rentTime;
    }

    public long getReturnTime() {
        return ReturnTime;
    }

    public void setReturnTime(long returnTime) {
        ReturnTime = returnTime;
    }

    public ArrayList<Car2> getCars() {
        return this.cars;
    }

    @Override
    public String toString() {
        return "CarManagement{" + "ID=" + ID + ", \ncustomer=" + customer.toString() + ", \ncar2=" + car2.toString() + ", \nRentTime=" + RentTime + ", ReturnTime=" + ReturnTime + '}' + "\n";
    }

    public void add(Car2 car) {   // Add cars to inventory
        this.cars.add(car);
    }

//    public void Add() {    //Add cars to inventory
//        ArrayList<CarManagement> booking = CarManagement.View();
//        if (CarManagement.isEmpty()) {
//            this.ID = 1;
//        } else {
//            this.ID = CarManagement.get(CarManagement.size() - 1).ID + 1; // Auto ID
//        }
//        this.ReturnTime = 0;
//        CarManagement.add(this);
//        File file = new File("Booking.ser");
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException ex) {
//                System.out.println(ex);
//            }
//        }
//        ObjectOutputStream outputStream = null;
//        try {
//            outputStream = new ObjectOutputStream(new FileOutputStream(file));
//            for (int i = 0; i < CarManagement.size(); i++) {
//                outputStream.writeObject(CarManagement.get(i));
//            }
//        } catch (FileNotFoundException ex) {
//            System.out.println(ex);
//        } catch (IOException ex) {
//            System.out.println(ex);
//        } finally {
//            if (outputStream != null) {
//                try {
//                    outputStream.close();
//                } catch (IOException ex) {
//                    System.out.println(ex);
//                }
//            }
//        }
//    }

    public void Update() {
        ArrayList<CarManagement> carManagement = CarManagement.View();

        // for loop for replacing the new Booking object with old one with same ID
        for (int i = 0; i < booking.size(); i++) {
            if (CarManagement.get(i).ID == ID) {
                CarManagement.set(i, this);
            }
        }

        // code for writing new Booking record
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("Booking.ser"));
            for (int i = 0; i < booking.size(); i++) {
                outputStream.writeObject(booking.get(i));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
    }


    public Car2 remove(String make, String Model) {
        Car2 removeCar = null;
        for (Car2 car : this.cars) {
            if (car.getCarMake.equals(make) && car.getCarModel.equals(Model)) {
                removeCar = car;
                this.cars.remove(car);
                break;
            }
        }
        return removeCar;
    }

    public Car2 remove(String regNum) {
        Car2 removeCar = null;
        for (Car2 car : this.cars) {
            if (Car2.getRegNum.equals(regNum)) {
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

    public void loadData(String filepath) throws IOException, ClassNotFoundException{
        FileInputStream fileIn = new FileInputStream(filepath);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        while(true) {
            Car2 car = (Car2)in.readObject();
            if (car == null) {
                break;
            }
            this.cars.add(car);
        }
    }


//        ArrayList<CarManagement> carManagement = CarManagement.View();
//     for loop for deleting the required Booking
//        for(
//    int i = 0; i<CarManagement.size()-1;i++)
//
//    {
//        if ((CarManagement.get(i).ID == ID)) {
//
//            for (int j = i; j < CarManagement.size() - 1; j++) {
//                CarManagement.set(j, (CarManagement.get(j + 1)));
//            }
//
//        }
//    }

//        // code for writing new Booking record
//        ObjectOutputStream outputStream = null;
//        try {
//            outputStream = new ObjectOutputStream(new FileOutputStream("Booking.ser"));
//            for (int i = 0; i < CarManagement.size() - 1; i++) {
//                outputStream.writeObject(booking.get(i));
//            }
//        } catch (FileNotFoundException ex) {
//            System.out.println(ex);
//        } catch (IOException ex) {
//            System.out.println(ex);
//        } finally {
//            if (outputStream != null) {
//                try {
//                    outputStream.close();
//                } catch (IOException ex) {
//                    System.out.println(ex);
//                }
//            }
//        }
//    }




    public int calculateBill() {
        // rent calculation
        long rentTime = this.getRentTime();
        long returnTime = this.getReturnTime();
        long totalTime = returnTime - rentTime;
        totalTime = totalTime / (1000 * 60 * 60);

        int rentPerHour = this.getCar().getRentPerHour();
        if (totalTime != 0) {
            return (int) (rentPerHour * totalTime);
        } else {
            return rentPerHour;
        }
    }

    public static ArrayList<CarManagement> SearchByCarID(int carID) {
        ArrayList<CarManagement> bookingList = new ArrayList<>(0);
        ObjectInputStream inputStream = null;
        try {
// open file for reading
            inputStream = new ObjectInputStream(new FileInputStream("Booking.ser"));
            boolean EOF = false;
// Keep reading file until file ends
            while (!EOF) {
                try {
                    CarManagement myObj = (CarManagement) inputStream.readObject();
                    if (myObj.car2.getID() == carID) {
                        bookingList.add(myObj);
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println(e);
                } catch (EOFException end) {
                    EOF = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return bookingList;
    }
    public static ArrayList<CarManagement> View() {
        ArrayList<CarManagement> bookingList = new ArrayList<>(0);
        ObjectInputStream inputStream = null;
        try {
// open file for reading
            inputStream = new ObjectInputStream(new FileInputStream("Booking.ser"));
            boolean EOF = false;
// Keep reading file until file ends
            while (!EOF) {
                try {
                    CarManagement myObj = (CarManagement) inputStream.readObject();
                    bookingList.add(myObj);
                } catch (ClassNotFoundException e) {
                    System.out.println(e);
                } catch (EOFException end) {
                    EOF = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return bookingList;
    }

//    public static ArrayList<Car2> getBookedCars() {
//        ArrayList<Car2> bookedCars = new ArrayList<>();
//        ArrayList<CarManagement> bookings = CarManagement.View();
//        for (int i = 0; i < bookings.size(); i++) {
//            if (bookings.get(i).ReturnTime == 0) {
//                bookedCars.add(bookings.get(i).car2);
//            }
//        }
//        return bookedCars;
//    }

//    public static ArrayList<Car2> getUnbookedCars() {
//        ArrayList<Car2> allCars = Car2.View();
//        ArrayList<Car2> bookedCars = CarManagement.getBookedCars();
//        for (int i = 0; i < bookedCars.size(); i++) {
//            allCars.remove(bookedCars.get(i));
//        }
//        return allCars;
    }