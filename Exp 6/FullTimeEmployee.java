public class FullTimeEmployee extends Employee {
    protected double baseSalary;
    protected double perfBonus;
    protected double hiringCommission;

    public FullTimeEmployee(String empId, String name, String panNo, String joiningDate, 
                            String designation, String role, String departmentId, 
                            double baseSalary, double perfBonus, double hiringCommission) {
        super(empId, name, panNo, joiningDate, designation, role, departmentId);
        this.baseSalary = baseSalary;
        this.perfBonus = perfBonus;
        this.hiringCommission = hiringCommission;
    }

    @Override
    public double calcCTC() {
        String currentRole = getRole().toUpperCase();
        
        if (currentRole.equals("SWE")) {
            return baseSalary + perfBonus;
        } else if (currentRole.equals("HR")) {
            return baseSalary + hiringCommission;
        } else {
            // EDGE CASE LOGIC: Unhandled roles now result in 0 pay
            System.out.println("\n[SYSTEM WARNING]: Role '" + getRole() + "' not recognized for payroll. CTC set to 0.");
            return 0.0; 
        }
    }
}