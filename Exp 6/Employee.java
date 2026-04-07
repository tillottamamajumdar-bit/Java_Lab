public abstract class Employee {
    private String empId;
    private String name;
    private String panNo;
    private String joiningDate;
    private String designation;
    private String role;
    private String departmentId;

    public Employee(String empId, String name, String panNo, String joiningDate, 
                    String designation, String role, String departmentId) {
        this.empId = empId;
        this.name = name;
        this.panNo = panNo;
        this.joiningDate = joiningDate;
        this.designation = designation;
        this.role = role;
        this.departmentId = departmentId;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public abstract double calcCTC();

    // Standardized display method
    public void displayDetails() {
        System.out.println("--------------------------------------------------");
        System.out.println("Employee ID   : " + empId);
        System.out.println("Name          : " + name);
        System.out.println("Designation   : " + designation);
        System.out.println("Role          : " + role);
        System.out.println("Department ID : " + departmentId);
        System.out.println("Calculated CTC: $" + calcCTC());
    }
}