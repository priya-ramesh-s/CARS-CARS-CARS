package com.SystemManagementRentalCar;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

public class Customer {

    //instance field
    private String fullName;
    private String drivingLicence;
    private String paymentMethod;
    private String age;

    public Customer(String fullName,String age, String drivingLicence, String paymentMethod) {
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

    public String getDrivingLicence() {
        return drivingLicence;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getAge() {
        return age;
    }
}
