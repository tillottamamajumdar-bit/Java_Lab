public class Manager extends FullTimeEmployee {
    private double ta; 
    private double eduAllowance; 

    public Manager(String empId, String name, String panNo, String joiningDate, 
                String designation, String departmentId, 
                double baseSalary, double perfBonus, double ta, double eduAllowance) {
        super(empId, name, panNo, joiningDate, designation, "Manager", departmentId, 
            baseSalary, perfBonus, 0.0);
        this.ta = ta;
        this.eduAllowance = eduAllowance;
    }

    @Override
    public double calcCTC() {
        return baseSalary + perfBonus + ta + eduAllowance;
    }
}