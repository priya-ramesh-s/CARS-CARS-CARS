package com.SystemManagementRentalCar;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

public class Customer {

    //instance field
    private String fullName;
    private String drivingLicence;

    public Customer(String fullName, boolean hasCar, String drivingLicence) {
        this.fullName = fullName;
        this.drivingLicence = drivingLicence;
    }

    //RentCar method
    public void rentCar(LocalDate date) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Rental Start Date: dd/mm/yy");
        String rentalStartDate = scanner.nextLine();
        LocalDate startLocalDate = LocalDate.parse(rentalStartDate);
        System.out.println("Please enter Rental End Date: dd/mm/yy");
        String rentalEndDate = scanner.nextLine();
        LocalDate endLocalDate = LocalDate.parse(rentalEndDate);
        long rentalPeriod = DAYS.between(startLocalDate, endLocalDate);

    }
    //returnCar method
    public void returnCar(Car car) {
        this.listCars.add(car);
        System.out.println("Thank you for returning " + car + "(" + carID + ")");
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDrivingLicence() {
        return drivingLicence;
    }

    public void setDrivingLicence(String drivingLicence) {
        this.drivingLicence = drivingLicence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getFullName().equals(customer.getFullName()) && getDrivingLicence().equals(customer.getDrivingLicence());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFullName(), getDrivingLicence());
    }
}
