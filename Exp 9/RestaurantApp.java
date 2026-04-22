import java.sql.*;

public class RestaurantApp {

    static final String URL  = "jdbc:mysql://localhost:3306/restaurant_db?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "Guidinglight1*";

    // ── Get connection ─────────────────────────────────────────────────────────
    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // ── Create tables ──────────────────────────────────────────────────────────
    static void setupTables() {
        String dropMenuItem = "DROP TABLE IF EXISTS MenuItem";
        String dropRestaurant = "DROP TABLE IF EXISTS Restaurant";
        
        String createRestaurant = "CREATE TABLE Restaurant ("
                                + "Id INT PRIMARY KEY, "
                                + "Name VARCHAR(50), "
                                + "Address VARCHAR(100))";
                                
        String createMenuItem = "CREATE TABLE MenuItem ("
                              + "Id INT PRIMARY KEY, "
                              + "Name VARCHAR(50), "
                              + "Price DOUBLE, "
                              + "ResId INT, "
                              + "FOREIGN KEY (ResId) REFERENCES Restaurant(Id))";

        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            // Drop in reverse order of dependencies
            st.executeUpdate(dropMenuItem);
            st.executeUpdate(dropRestaurant);
            
            // Create tables
            st.executeUpdate(createRestaurant);
            st.executeUpdate(createMenuItem);
            System.out.println("[INFO] Tables 'Restaurant' and 'MenuItem' created successfully.");
        } catch (SQLException e) {
            System.out.println("[ERROR Setup] " + e.getMessage());
        }
    }

    // ── Insert 10 records into each table ──────────────────────────────────────
    static void insertInitialData() {
        String insertRes = "INSERT INTO Restaurant (Id, Name, Address) VALUES (?, ?, ?)";
        String insertMenu = "INSERT INTO MenuItem (Id, Name, Price, ResId) VALUES (?, ?, ?, ?)";

        Object[][] restaurants = {
            {1, "Cafe Java", "123 Tech Street"}, {2, "Spice House", "45 Curry Lane"},
            {3, "Burger Joint", "88 Fast Blvd"}, {4, "Pizza Planet", "99 Orion Way"},
            {5, "Sushi Central", "12 Tokyo Ave"}, {6, "Taco Fiesta", "44 Salsa Drive"},
            {7, "Bistro 9", "9 French Court"}, {8, "Steak & Co", "77 Meat St"},
            {9, "Vegan Vibes", "101 Green Blvd"}, {10, "Dessert Dash", "55 Sugar Lane"}
        };

        
        Object[][] menuItems = {
            {101, "Coffee", 50.0, 1}, {102, "Tea", 40.0, 1}, 
            {103, "Pasta", 150.0, 2}, {104, "Pizza", 250.0, 4}, 
            {105, "Burger", 90.0, 3}, {106, "Fries", 60.0, 3}, 
            {107, "Pancakes", 120.0, 1}, {108, "Pastry", 80.0, 1}, 
            {109, "Salad", 110.0, 9}, {110, "Pie", 95.0, 10}
        };

        try (Connection con = getConnection();
             PreparedStatement psRes = con.prepareStatement(insertRes);
             PreparedStatement psMenu = con.prepareStatement(insertMenu)) {
             
            // Insert Restaurants
            for (Object[] r : restaurants) {
                psRes.setInt(1, (Integer) r[0]);
                psRes.setString(2, (String) r[1]);
                psRes.setString(3, (String) r[2]);
                psRes.addBatch();
            }
            psRes.executeBatch();

            // Insert Menu Items
            for (Object[] m : menuItems) {
                psMenu.setInt(1, (Integer) m[0]);
                psMenu.setString(2, (String) m[1]);
                psMenu.setDouble(3, (Double) m[2]);
                psMenu.setInt(4, (Integer) m[3]);
                psMenu.addBatch();
            }
            psMenu.executeBatch();
            System.out.println("[INFO] 10 records inserted into both tables.");
            
        } catch (SQLException e) {
            System.out.println("[ERROR Insert] " + e.getMessage());
        }
    }

    // ── Utility Method: Print ResultSet in Tabular Format ──────────────────────
    static void printResultSet(ResultSet rs) throws SQLException {
        System.out.println("+------+----------------------+---------+---------+");
        System.out.println("|  Id  | Name                 | Price   | ResId   |");
        System.out.println("+------+----------------------+---------+---------+");
        boolean any = false;
        while (rs.next()) {
            System.out.printf("| %-4d | %-20s | %-7.1f | %-7d |%n",
                    rs.getInt("Id"), rs.getString("Name"),
                    rs.getDouble("Price"), rs.getInt("ResId"));
            any = true;
        }
        if (!any) System.out.println("| No records found.                               |");
        System.out.println("+------+----------------------+---------+---------+");
    }

    // ── Query Operations ───────────────────────────────────────────────────────

    // 1. Select all records from MenuItem table where price <= 100
    static void fetchItemsUnder100() {
        System.out.println("\n--- Menu Items with Price <= 100 ---");
        String sql = "SELECT * FROM MenuItem WHERE Price <= 100";
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            printResultSet(rs);
        } catch (SQLException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // 2. Select all records from MenuItem table available in "Cafe Java"
    static void fetchItemsInCafeJava() {
        System.out.println("\n--- Menu Items from 'Cafe Java' ---");
        String sql = "SELECT m.Id, m.Name, m.Price, m.ResId FROM MenuItem m "
                   + "JOIN Restaurant r ON m.ResId = r.Id "
                   + "WHERE r.Name = 'Cafe Java'";
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            printResultSet(rs);
        } catch (SQLException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // 3. Update all records from MenuItem where price <= 100 to price = 200
    static void updatePrices() {
        System.out.println("\n--- Updating Prices (<= 100 to 200) ---");
        String updateSql = "UPDATE MenuItem SET Price = 200 WHERE Price <= 100";
        String selectSql = "SELECT * FROM MenuItem";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            int rows = st.executeUpdate(updateSql);
            System.out.println("[INFO] " + rows + " records updated. New MenuItem Table:");
            try (ResultSet rs = st.executeQuery(selectSql)) {
                printResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // 4. Delete all records from MenuItem where name starts with 'P'
    static void deleteItemsStartingWithP() {
        System.out.println("\n--- Deleting Items starting with 'P' ---");
        String deleteSql = "DELETE FROM MenuItem WHERE Name LIKE 'P%'";
        String selectSql = "SELECT * FROM MenuItem";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            int rows = st.executeUpdate(deleteSql);
            System.out.println("[INFO] " + rows + " records deleted. Final MenuItem Table:");
            try (ResultSet rs = st.executeQuery(selectSql)) {
                printResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // ── Main ───────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        System.out.println("=== Starting JDBC Restaurant Application ===\n");
        
        setupTables();
        insertInitialData();
        
        fetchItemsUnder100();
        fetchItemsInCafeJava();
        updatePrices();
        deleteItemsStartingWithP();
        
        System.out.println("\n=== Application Completed successfully ===");
    }
}