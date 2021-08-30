package com.SystemManagementRentalCar;

public class Customer {
    private String name;
    private int age;
    //private int mobileNum;
    private String driversLicence;
    private String paymentMethod;
    private String rentedCarRegNum;


    //constructor
    public Customer(String name, int age) {
        // use this constructor if new customer
        this.name = name;
        this.age = age;
        this.rentedCarRegNum = null;
    }

    // Setter methods
//    public void setMobileNum(int mobileNum) {
//        this.mobileNum = mobileNum;
//    }


    public void setRentedCarRegNum(String rentedCarRegNum) {
        this.rentedCarRegNum = rentedCarRegNum;
    }

    public void setDriversLicence(String driversLicence) {
        this.driversLicence = driversLicence;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // Getter methods
    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

//    public int getMobileNum() {
//        return this.mobileNum;
//    }

    public String getDriversLicence() {
        return this.driversLicence;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }
}
