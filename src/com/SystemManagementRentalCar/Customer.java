package com.SystemManagementRentalCar;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

public class Customer {

    //instance field
    private String name;
    private String drivingLicence;
    private String paymentMethod;
    private int age;

    public Customer(String name, int age, String drivingLicence, String paymentMethod) {
        this.name = name;
        this.drivingLicence = drivingLicence;
        this.paymentMethod = paymentMethod;
        this.age = age;
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

    public String getName() {
        return this.name;
    }

    public String getDrivingLicence() { return this.drivingLicence; }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public int getAge() {
        return this.age;
    }
}
