public class ContractEmployee extends Employee {
    private int noOfHrs;
    private double hourlyRate;

    public ContractEmployee(String empId, String name, String panNo, String joiningDate, 
                            String designation, String role, String departmentId, 
                            int noOfHrs, double hourlyRate) {
        super(empId, name, panNo, joiningDate, designation, role, departmentId);
        this.noOfHrs = noOfHrs;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calcCTC() {
        return noOfHrs * hourlyRate;
    }
}