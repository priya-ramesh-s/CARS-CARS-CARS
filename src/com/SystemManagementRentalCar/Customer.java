package com.SystemManagementRentalCar;

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

    public String getDrivingLicence() { return this.drivingLicence; }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public int getAge() {
        return this.age;
    }
}
