import java.time.Year;

public class Vehicle {
    
    // Public data members
    public String brandName;
    public String modelName;
    public double price;
    public String color;
    public Year mfgYear;
    public char fuelType;

    // Private data members
    private String mfgCode;
    private String regNo;
    private double mileage;

    // --- Getters and Setters ---
    public String getMfgCode() {
        return mfgCode;
    }

    public void setMfgCode(String mCode) {
        mfgCode = mCode;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String rNo) {
        regNo = rNo;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    // --- 1. Default Constructor ---
    public Vehicle() {
        brandName = "Hyundai";
        modelName = "i10";
        color = "Silver";
        price = 500000.99;
        mfgCode = "HYN12345";
        regNo = "HYN98765";
        mfgYear = Year.of(2020);
        fuelType = 'P';
        mileage = 15.5; 
    }

    // --- 2. Parameterized Constructor ---
    public Vehicle(String brandName, String modelName, double price, String mfgCode, double mileage) {
        this.brandName = brandName;
        this.modelName = modelName;
        this.price = price;
        this.mfgCode = mfgCode;
        this.mileage = mileage;
    }

    // --- 3. Copy Constructor ---
    public Vehicle(Vehicle v) {
        this.brandName = v.brandName;
        this.modelName = v.modelName;
        this.price = v.price;
        this.color = v.color;
        this.mfgYear = v.mfgYear;
        this.fuelType = v.fuelType;
        this.mfgCode = v.mfgCode;
        this.regNo = v.regNo;
        this.mileage = v.mileage;
    }

    // --- Methods ---
    public void start(int initSp) {
        System.out.println(brandName + " started with an initial speed of " + initSp + " km/h.");
    }

    public void stop() {
        System.out.println(brandName + " has stopped. That was a nice ride!");
    }

    public void drive(int initGear, int initSp, int tgtSp) {
        if (initGear != 1) {
            System.out.println("I always start moving at the first gear.");
        }
        if (initSp < 20) {
            System.out.println("That is the ideal speed to start with.");
        } else {
            System.out.println("Be careful!");
        }

        if (tgtSp > 200) {
            System.out.println("Look out for cops!");
        } else {
            System.out.println("You are within the ideal speed range.");
        }
    }

    public double calcMileage(double noOfKms, double fuelUsed) {
        return noOfKms / fuelUsed;
    }

    public void changeSpeed(int newSpeed) {
        System.out.println(brandName + " speed changed to " + newSpeed + " km/h.");
    }
}