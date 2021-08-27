package com.SystemManagementRentalCar;

//import date of year package

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class CarRentalDatabase {
    private int ID;
    private Customer customer;
    private Car2 car;   //implement car class
    private long RentTime, ReturnTime; //stores system time - change to date


    public void Booking() {

    }


    public CarRentalDatabase(int ID, Customer customer, Car2 car, long rentTime, long returnTime) {
        this.ID = ID;
        this.customer = new Customer();
        this.car = new Car2();
        RentTime = rentTime;
        ReturnTime = returnTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car2 getCar() {
        return car;
    }

    public void setCar(Car2 car) {
        this.car = car;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarRentalDatabase that = (CarRentalDatabase) o;
        return ID == that.ID && RentTime == that.RentTime && ReturnTime == that.ReturnTime && Objects.equals(customer, that.customer) && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, customer, car, RentTime, ReturnTime);
    }

    //    public void Add() {
//        ArrayList<Booking>? booking = Booking.View();
//        if (booking.isEmpty()) {
//            this.ID = 1;
//        } else {
//            this.ID = booking.get(booking.size() -1).ID + 1;
        }
    }
public static ArrayList<Car> getBookedCars() {
        ArrayList<Car> bookedCars = new ArrayList<>();
        ArrayList<Booking> bookings = Booking.View();
        for (int i = 0; i < bookings.size(); i++) {
        if (bookings.get(i).ReturnTime == 0) {
        bookedCars.add(bookings.get(i).car);
        }
        }
        return bookedCars;
        }

public static ArrayList<Car> getUnbookedCars() {
        ArrayList<Car> allCars = Car.View();
        ArrayList<Car> bookedCars = Booking.getBookedCars();
        for (int i = 0; i < bookedCars.size(); i++) {
        allCars.remove(bookedCars.get(i));
        }
        return allCars;
        }


}
