import java.io.*;
import java.util.*;

public class StudentCsvManager {
    static final String FILE_NAME = "Students.csv";
    static final String HEADER = "studentId,name,branch,marks1,marks2,marks3,marks4,marks5,percentage";
    static Scanner sc = new Scanner(System.in);

    // ── Initialize File ────────────────────────────────────────────────────────
    static void initializeFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
                bw.write(HEADER);
                bw.newLine();
            } catch (IOException e) {
                System.out.println("  [ERROR] File initialization error: " + e.getMessage());
            }
        }
    }

    // ── Helper: Calculate Percentage ───────────────────────────────────────────
    static String calculatePercentage(double m1, double m2, double m3, double m4, double m5) {
        double percentage = ((m1 + m2 + m3 + m4 + m5) / 500.0) * 100.0;
        return String.format("%.2f", percentage);
    }

    // ── 1. Add student ─────────────────────────────────────────────────────────
    static void addRecord() {
        try {
            System.out.print("  Student ID : "); String id = sc.nextLine().trim();
            System.out.print("  Name       : "); String name = sc.nextLine().trim();
            System.out.print("  Branch     : "); String branch = sc.nextLine().trim();
            System.out.print("  Marks 1    : "); double m1 = Double.parseDouble(sc.nextLine().trim());
            System.out.print("  Marks 2    : "); double m2 = Double.parseDouble(sc.nextLine().trim());
            System.out.print("  Marks 3    : "); double m3 = Double.parseDouble(sc.nextLine().trim());
            System.out.print("  Marks 4    : "); double m4 = Double.parseDouble(sc.nextLine().trim());
            System.out.print("  Marks 5    : "); double m5 = Double.parseDouble(sc.nextLine().trim());

            String percentage = calculatePercentage(m1, m2, m3, m4, m5);

            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true));
            bw.write(id + "," + name + "," + branch + "," + m1 + "," + m2 + "," + m3 + "," + m4 + "," + m5 + "," + percentage);
            bw.newLine();
            bw.close();
            System.out.println("  Record added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("  [ERROR] Invalid input. Please enter valid numbers for marks.");
        } catch (IOException e) {
            System.out.println("  [ERROR] File error: " + e.getMessage());
        }
    }

    // ── 2. View all records ────────────────────────────────────────────────────
    static void viewRecords() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line = br.readLine(); // Read header
            System.out.println("\n  +------+--------------+--------+------+------+------+------+------+-------+");
            System.out.println("  | ID   | Name         | Branch | Mk1  | Mk2  | Mk3  | Mk4  | Mk5  |   %   |");
            System.out.println("  +------+--------------+--------+------+------+------+------+------+-------+");
            
            boolean any = false;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 9) {
                    System.out.printf("  | %-4s | %-12s | %-6s | %-4s | %-4s | %-4s | %-4s | %-4s | %-5s |%n",
                            p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7], p[8]);
                    any = true;
                }
            }
            if (!any) System.out.println("  No student records found.");
            System.out.println("  +------+--------------+--------+------+------+------+------+------+-------+");
        } catch (FileNotFoundException e) {
            System.out.println("  No records file found. Add a student first.");
        } catch (IOException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    // ── 3. Update student record ───────────────────────────────────────────────
    static void updateRecord(String studentId) {
        try {
            File file = new File(FILE_NAME);
            List<String> lines = new ArrayList<>();
            boolean found = false;
            
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            
            lines.add(br.readLine()); // Keep header

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 9 && p[0].equals(studentId)) {
                    System.out.print("  New Name    : "); String name = sc.nextLine().trim();
                    System.out.print("  New Branch  : "); String branch = sc.nextLine().trim();
                    System.out.print("  New Marks 1 : "); double m1 = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("  New Marks 2 : "); double m2 = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("  New Marks 3 : "); double m3 = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("  New Marks 4 : "); double m4 = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("  New Marks 5 : "); double m5 = Double.parseDouble(sc.nextLine().trim());

                    String percentage = calculatePercentage(m1, m2, m3, m4, m5);
                    lines.add(studentId + "," + name + "," + branch + "," + m1 + "," + m2 + "," + m3 + "," + m4 + "," + m5 + "," + percentage);
                    found = true;
                } else {
                    lines.add(line);
                }
            }
            br.close();
            
            if (found) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                for (String l : lines) { 
                    bw.write(l); 
                    bw.newLine(); 
                }
                bw.close();
                System.out.println("  Record updated and percentage recalculated.");
            } else {
                System.out.println("  Student ID " + studentId + " not found.");
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    // ── 4. Delete student record ───────────────────────────────────────────────
    static void deleteRecord(String studentId) {
        try {
            File file = new File(FILE_NAME);
            List<String> lines = new ArrayList<>();
            boolean found = false;
            
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            
            lines.add(br.readLine()); // Keep header

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 9 && p[0].equals(studentId)) { 
                    found = true; 
                } else { 
                    lines.add(line); 
                }
            }
            br.close();
            
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (String l : lines) { 
                bw.write(l); 
                bw.newLine(); 
            }
            bw.close();
            
            System.out.println(found ? "  Record deleted." : "  Student ID not found.");
        } catch (IOException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    // ── Main Menu ──────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        initializeFile();
        System.out.println("=== CSV Student Record Manager ===");
        
        while (true) {
            System.out.println("\n1.Add  2.View  3.Update  4.Delete  0.Exit");
            System.out.print("Choice: ");
            int ch;
            try { 
                ch = Integer.parseInt(sc.nextLine().trim()); 
            } catch (NumberFormatException e) { 
                System.out.println("Invalid input!"); 
                continue; 
            }
            
            switch (ch) {
                case 1: 
                    addRecord(); 
                    break;
                case 2: 
                    viewRecords(); 
                    break;
                case 3:
                    System.out.print("  Student ID to update: ");
                    updateRecord(sc.nextLine().trim());
                    break;
                case 4:
                    System.out.print("  Student ID to delete: ");
                    deleteRecord(sc.nextLine().trim());
                    break;
                case 0: 
                    System.out.println("Goodbye!"); 
                    sc.close(); 
                    return;
                default: 
                    System.out.println("Invalid option.");
            }
        }
    }
}