public class MainForVehicle {

    // Helper method to print details cleanly
    public static void printVehicleDetails(Vehicle v) {
        System.out.println("=============Vehicle details============");
        System.out.println("Brand Name: " + v.brandName);
        System.out.println("Model Name: " + v.modelName);
        System.out.println("Price : " + v.price);
        System.out.println("Mfg Code : " + v.getMfgCode());
        System.out.println("Registration Number: " + v.getRegNo());
        System.out.println("Year : " + v.mfgYear); 
        System.out.println("========End of Vehicle details========");
    }

    public static void main(String[] args) {

        // 1. Default constructor
        Vehicle defV = new Vehicle();
        printVehicleDetails(defV);

        // 2. Parameterized constructor
        Vehicle v2 = new Vehicle("Honda", "City", 1200000.50, "HON98822", 18.5);
        printVehicleDetails(v2);
        
        // Single vehicle test
        v2.start(20);
        v2.drive(0, 20, 120);
        double tripMileage = v2.calcMileage(140, 20); 
        System.out.println("Trip mileage : " + tripMileage);
        v2.stop();

        // 3. Reference assignment (Restored to your original structure)
        // Note: v3 and v2 now share the same data in memory.
        Vehicle v3 = v2;
        v3.setMfgCode("HON98765");
        v3.color = "Orange";
        v3.setMileage(16.0); // This also changes v2's mileage to 16.0!
        printVehicleDetails(v3);

        // 4. Copy constructor (Copied v2/v3, but modified to be a completely different car)
        Vehicle v4 = new Vehicle(v2);
        v4.brandName = "Mahindra";
        v4.modelName = "XUV700";
        v4.price = 1800000.00;
        v4.color = "Black";
        v4.setMfgCode("MAH12345");
        v4.fuelType = 'D';
        v4.setMileage(13.5);
        printVehicleDetails(v4);

        // 5. Parameterized constructor 
        Vehicle v5 = new Vehicle("Mercedes", "S Class", 8500000.0, "MER12345", 12.0);
        printVehicleDetails(v5);
        
        // 6. Array of Vehicles
        Vehicle[] myFleet = new Vehicle[]{defV, v2, v3, v4, v5};

        // --- Tabular formatting requirement ---
        System.out.println("\n===================================================================");
        System.out.printf("%-15s %-15s %-15s %-15s%n", "Brand Name", "Model Name", "Price", "Mileage");
        System.out.println("===================================================================");
        for (Vehicle myV : myFleet) {
            System.out.printf("%-15s %-15s %-15.2f %-15.2f%n", 
                    myV.brandName, 
                    myV.modelName, 
                    myV.price, 
                    myV.getMileage());
        }
        System.out.println("===================================================================\n");

        // 7. Loop to test methods with DIFFERENT speeds for each vehicle
        System.out.println("--- Fleet Action Test ---");
        int currentSpeed = 10; // Starting speed
        
        for (Vehicle myV : myFleet) {
            System.out.println("Testing: " + myV.brandName + " " + myV.modelName);
            
            // Apply dynamic speeds
            myV.start(currentSpeed);
            myV.changeSpeed(currentSpeed + 25);
            myV.drive(1, currentSpeed, currentSpeed + 80);
            System.out.println(); // Blank line for readability
            
            // Increase the speed for the next loop iteration
            currentSpeed += 15; 
        }
    }
}