public class EmployeeMain {
    public static void main(String[] args) {
        System.out.println("=== STANDARD EMPLOYEE DETAILS ===");

        // Employee 1: SWE
        FullTimeEmployee swe = new FullTimeEmployee("E001", "Alice Smith", "ABCDE1234F", "01-08-2023", 
                                                    "Backend Developer", "SWE", "DEPT-IT", 
                                                    80000.0, 15000.0, 0.0);
        swe.displayDetails();

        // Employee 2: HR
        FullTimeEmployee hr = new FullTimeEmployee("E002", "Bob Johnson", "FGHIJ5678K", "15-02-2024", 
                                                    "Talent Acquisition Specialist", "HR", "DEPT-HR", 
                                                    60000.0, 0.0, 8000.0);
        hr.displayDetails();

        // Employee 3: Contractor
        ContractEmployee contractor = new ContractEmployee("C001", "Charlie Davis", "KLMNO9012P", "10-10-2023", 
                                                        "UI/UX Consultant", "Contractor", "DEPT-DESIGN", 
                                                        160, 50.0);
        contractor.displayDetails();

        // Employee 4: Manager
        Manager manager = new Manager("M001", "Diana Prince", "PQRST3456U", "05-05-2020", 
                                    "Director of Engineering", "DEPT-IT", 
                                    120000.0, 25000.0, 5000.0, 10000.0);
        manager.displayDetails();
        
        System.out.println("\n=== EDGE CASE ===");

        // Employee 5: Edge Case (Unhandled Role)
        // Role is "MARKETING". The system will trigger the warning and output $0.0 CTC.
        FullTimeEmployee marketingExec = new FullTimeEmployee("E003", "Eve Adams", "VWXYZ7890M", "12-11-2023", 
                                                            "SEO Specialist", "MARKETING", "DEPT-MKT", 
                                                            50000.0, 5000.0, 2000.0);
        marketingExec.displayDetails();
        
        System.out.println("--------------------------------------------------");
    }
}