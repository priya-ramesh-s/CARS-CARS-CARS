package com.SystemManagementRentalCar;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

public class CarManagementService {
    private CarRentalDatabase availableDatabase;
    private CarRentalDatabase rentedDatabase;
    private String objFilepathAvailable;
    private String objFilepathRented;
    private String filepathRented;
    private String filepathAvailable;


    // constructor
    public CarManagementService() {
        this.availableDatabase = new CarRentalDatabase();
        this.rentedDatabase = new CarRentalDatabase();
        this.objFilepathAvailable = "C:/Users/Sanchayata/Documents/available_cars.txt";  // enter filepath where you would like available cars info to be stored
        this.objFilepathRented = "C:/Users/Sanchayata/Documents/rented_cars.txt";     // enter filepath where you would like rented cars info to be stored
        this.filepathRented = "C:/Users/Sanchayata/Documents/rented_cars_readable.txt";
        this.filepathAvailable = "C:/Users/Sanchayata/Documents/available_cars_readable.txt";
    }

    // methods

    public void openDatabaseRented() {
        try {
            this.rentedDatabase.loadObjData(this.objFilepathRented);
            //this.availableDatabase.loadData(this.filepath_available);
        } catch(ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void openDatabaseAvailable() {
        try {
            this.availableDatabase.loadObjData(this.objFilepathAvailable);
        } catch(ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setTodaysAvailableCars() {
        boolean carNowAvailable = true;
        for (Car2 car : this.rentedDatabase.getCars()) {
            for (LocalDate rentDate : car.getRentalPeriods()) {
                if (LocalDate.now().equals(car.getRentalPeriods())) {
                    carNowAvailable = false;
                    break;
                }
            }
            if (carNowAvailable) {
                Car2 newlyAvailableCar = this.rentedDatabase.remove(car.getRegNum());
                this.availableDatabase.add(newlyAvailableCar);
            }

        }

    }

    public void setTodaysRentedCars() {
        boolean carNowNotAvailable = true;
        for (Car2 car : this.availableDatabase.getCars()) {
            for (LocalDate rentDate : car.getRentalPeriods()) {
                if (LocalDate.now().equals(car.getRentalPeriods())) {
                    carNowNotAvailable = false;
                    break;
                }
            }
            if (carNowNotAvailable) {
                Car2 newlyRentedCar = this.availableDatabase.remove(car.getCarMake(), car.getCarModel());
                this.availableDatabase.add(newlyRentedCar);
            }

        }

    }

    public void closeDatabase() {
        try {
            this.availableDatabase.saveObjData(this.objFilepathAvailable);
            this.rentedDatabase.saveObjData(this.objFilepathRented);
            this.availableDatabase.saveReadableData(this.filepathAvailable);
            this.rentedDatabase.saveReadableData(this.filepathRented);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addNewCar() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the car make");
        String make = userInput.nextLine();
        System.out.println("Enter the car model");
        String model = userInput.nextLine();
        System.out.println("Enter the daily price to rent this car");
        int rentPrice = userInput.nextInt();
        System.out.println("Enter the registration number of this car");
        String regNum = userInput.next();

        Car2 newCar = new Car2();
        newCar.setCarMake(make);
        newCar.setCarModel(model);
        newCar.setRentPrice(rentPrice);
        newCar.setRegNum(regNum);
        this.availableDatabase.add(newCar);
    }


    public void displayAvailableCars() {
        // use this for management side, where they just want to see which cars are currently available on the day
        for (Car2 availableCar : this.availableDatabase.getCars()) {
            System.out.println(availableCar.getCarMake() + " " + availableCar.getCarModel() + " £" + availableCar.getRentPrice());
        }
    }


    public void displayAvailableCars(String startDate, String endDate, ArrayList<LocalDate> requestedRentalDates) {
        // use this for customer side where customer wants to see cars based on booking dates
        System.out.println("These cars are currently available");
        LocalDate startRentingCar = LocalDate.parse(startDate);
        LocalDate endRentingCar = LocalDate.parse(endDate);
        ArrayList <String> availableDBCarRegNums = checkRequestedRentalDatesFromAvailableDatabase(requestedRentalDates);
        ArrayList <String> rentedDBCarRegNums;

        for (Car2 car : this.availableDatabase.getCars()) {
            for (String regNum : availableDBCarRegNums) {
                if (car.getRegNum().equals(regNum)) {
                    System.out.println(car.getCarMake() + " " + car.getCarModel() + " £" + car.getRentPrice());
                }
            }
        }
        if(!(startRentingCar.equals(LocalDate.now()))) {
            rentedDBCarRegNums = checkRequestedRentalDatesFromRentedDatabase(requestedRentalDates);

            for (Car2 car : this.rentedDatabase.getCars()) {
                for (String regNum : rentedDBCarRegNums) {
                    if (car.getRegNum().equals(regNum)) {
                        System.out.println(car.getCarMake() + " " + car.getCarModel() + " £" + car.getRentPrice());
                    }
                }
            }
        }
    }


    public void displayRentedCars() {
        // only needed for management side
        for (Car2 rentedCar : this.rentedDatabase.getCars()) {
            System.out.println(rentedCar.getCarMake() + " " + rentedCar.getCarModel() + " £" + rentedCar.getRentPrice());
        }
    }


    public boolean canCustomerBookCar(Customer customer) {
        Scanner userInput = new Scanner(System.in);

        int age = customer.getAge();
        String paymentMethod = customer.getPaymentMethod();
        String licence = customer.getDrivingLicence();

        boolean check1 = false;
        boolean check2 = false;
        boolean check3 = false;

        if (age >= 25) {
            check1 = true;
        } else {
            System.out.println("Sorry you need to be 25 or over to rent a car.");
        }
        if (paymentMethod.equals("credit") || paymentMethod.equals("debit")) {
            check2 = true;
        } else {
            System.out.println("Sorry you need to use a credit card or debit card to rent a car.");
        }

        String regex = "^[a-zA-Z0-9]{10,20}$";
        if (licence.matches(regex)) {
            check3 = true;
        } else {
            System.out.println("Sorry you have entered an incorrect licence number");
        }

        if (check1 && check2 && check3) {
            return true;
        }
        return false;
    }


    public void booking(String make, String model, ArrayList<LocalDate> requestedRentalDates,
                       String startDate) {
        LocalDate startRentingCar = LocalDate.parse(startDate);

        if (LocalDate.now().equals(startRentingCar)) {
            Car2 rentCar = availableDatabase.remove(make, model);
            rentedDatabase.add(rentCar);
            System.out.println("You have successfully booked a " + rentCar.getCarMake() + " " + rentCar.getCarModel() + " with registration number: " + rentCar.getRegNum());
            System.out.println("This will cost you " + rentCar.getRentPrice() + " per day");

            for (LocalDate requestedRentalDate : requestedRentalDates) {
                rentCar.getRentalPeriods().add(requestedRentalDate);
            }
        } else {
            boolean carFound = false;
            for (Car2 car : this.availableDatabase.getCars()) {
                if (car.getCarMake().equals(make) && car.getCarModel().equals(model)) {
                    ArrayList <String> availableDBCarRegNums = checkRequestedRentalDatesFromAvailableDatabase(requestedRentalDates);
                    for (String availableDBCarRegNum : availableDBCarRegNums) {
                        if (car.getRegNum().equals(availableDBCarRegNum)) {
                            carFound = true;
                            for (LocalDate requestedRentalDate : requestedRentalDates) {
                                car.getRentalPeriods().add(requestedRentalDate);
                            }
                            System.out.println("You have successfully booked a " + car.getCarMake() + " " + car.getCarModel() + " with registration number: " + car.getRegNum());
                            System.out.println("This will cost you " + car.getRentPrice() + " per day");
                            break;
                        }
                    }
                }
                if (carFound) {
                    break;
                }
            }

            if (!carFound) {
                for (Car2 car : this.rentedDatabase.getCars()) {
                    if (car.getCarMake().equals(make) && car.getCarModel().equals(model)) {
                        ArrayList<String> rentedDBCarRegNums = checkRequestedRentalDatesFromRentedDatabase(requestedRentalDates);
                        for (String rentedDBCarRegNum : rentedDBCarRegNums) {
                            if (car.getRegNum().equals(rentedDBCarRegNum)) {
                                carFound = true;
                                for (LocalDate requestedRentalDate : requestedRentalDates) {
                                    car.getRentalPeriods().add(requestedRentalDate);
                                }
                                System.out.println("You have successfully booked a " + car.getCarMake() + " " + car.getCarModel() + " with registration number: " + car.getRegNum());
                                System.out.println("This will cost you " + car.getRentPrice() + " per day");
                                break;
                            }
                        }
                    }
                    if (carFound) {
                        break;
                    }
                }
            }
        }
    }


    public void returnCar(String regNum) {
        boolean rentedCarFound = false;
        for (Car2 rentedCar : rentedDatabase.getCars()) {
            if (rentedCar.getRegNum().equals(regNum)) {
                Car2 availableCar = rentedDatabase.remove(regNum);
                availableDatabase.add(availableCar);
                System.out.println("You have successfully returned the car. Thank you, we hope to see you again soon!");
                rentedCarFound = true;
                break;
            }
        }
        if (!rentedCarFound) {
            System.out.println("Sorry this registration number is incorrect.");
        }
    }


    public ArrayList<LocalDate> rentalDaysCalc(String startDate, String endDate) {
        LocalDate startRentingCar = LocalDate.parse(startDate);
        LocalDate endRentingCar = LocalDate.parse(endDate);
        ArrayList<LocalDate> requestedRentalDates = new ArrayList<LocalDate>();

        boolean withinRentalPeriod = true;
        while (withinRentalPeriod) {
            requestedRentalDates.add(startRentingCar);
            startRentingCar = startRentingCar.plus(1, DAYS);

            if (startRentingCar.equals(endRentingCar.plus(1, DAYS))) {
                withinRentalPeriod = false;
            }
        }
        return requestedRentalDates;
    }

    public ArrayList<String> checkRequestedRentalDatesFromAvailableDatabase(ArrayList<LocalDate> requestedRentalDates) {
        ArrayList<String> availableCarRegNums = new ArrayList<String>();
        boolean carAvailableForRequestedDates = true;
        for (Car2 car : this.availableDatabase.getCars()) {
            for (LocalDate carRentedDate : car.getRentalPeriods()) {
                for (LocalDate requestedRentalDate : requestedRentalDates) {
                    if (requestedRentalDate.equals(carRentedDate)) {
                        carAvailableForRequestedDates = false;
                        break;
                    }
                }
                if (!carAvailableForRequestedDates) {
                    break;
                }
            }
            if (carAvailableForRequestedDates) {
                availableCarRegNums.add(car.getRegNum());
            }
        }
        return availableCarRegNums;
    }

    public ArrayList<String> checkRequestedRentalDatesFromRentedDatabase(ArrayList<LocalDate> requestedRentalDates) {
        ArrayList<String> availableCarRegNums = new ArrayList<String>();
        boolean carAvailableForRequestedDates = true;

        for (Car2 car : this.rentedDatabase.getCars()) {
            for (LocalDate carRentedDate : car.getRentalPeriods()) {
                for (LocalDate requestedRentalDate : requestedRentalDates) {
                    if (requestedRentalDate.equals(carRentedDate)) {
                        carAvailableForRequestedDates = false;
                        break;
                    }
                }
                if (!carAvailableForRequestedDates) {
                    break;
                }
            }
            if (carAvailableForRequestedDates) {
                availableCarRegNums.add(car.getRegNum());
            }
        }
        return availableCarRegNums;
    }





//    public void setRentalPeriods(ArrayList<LocalDate> rentalPeriods) {
//        for (int i = 0; i < rentalPeriods.size(); i++) {
//            this.rentalPeriods.add(rentalPeriods(i));
//        }
//    }
//
//    for (int i = 0; i < car.getRentalPeriods().size(); i++) {
//        LocalDate confirmedRentingDate = car.getRentalPeriods().get(i);
//        if (confirmedRentingDate == requestedRentingDate) {
//
//        }
//    }

    public long rentalPeriodCalc(String startDate, String endDate) {
        LocalDate startRentingCar = LocalDate.parse(startDate);
        LocalDate endRentingCar = LocalDate.parse(endDate);
        long rentalPeriod = DAYS.between(startRentingCar, endRentingCar);

        return rentalPeriod;
    }


//    public void calculateBill(int carDailyFee, long rentalPeriod) {
//        float totalBill = rentalPeriod * carDailyFee;
//        System.out.println("As you have booked this car for " + rentalPeriod + "day(s), your total bill is: ");
//        System.out.println(totalBill);
//    }
}
