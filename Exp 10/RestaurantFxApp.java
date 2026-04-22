import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.sql.*;


public class RestaurantFxApp extends Application {

    // ── Database Credentials ───────────────────────────────────────────────────
    static final String URL = "jdbc:mysql://localhost:3306/restaurant_db?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "Guidinglight1*";

    // ── UI Components ──────────────────────────────────────────────────────────
    private ComboBox<String> tableCombo;
    private TextField txtId, txtName, txtExtra1, txtExtra2;
    private Label lblExtra1, lblExtra2;
    private TextArea outputArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Restaurant Database Manager");

        // 1. Menu Bar Setup (The "Menu Driven" part)
        MenuBar menuBar = new MenuBar();
        Menu menuOps = new Menu("Database Operations");
        
        MenuItem menuInsert = new MenuItem("Insert Record");
        MenuItem menuSelect = new MenuItem("Select All Records");
        MenuItem menuUpdate = new MenuItem("Update Record (by ID)");
        MenuItem menuDelete = new MenuItem("Delete Record (by ID)");
        
        menuOps.getItems().addAll(menuInsert, menuSelect, menuUpdate, menuDelete);
        menuBar.getMenus().add(menuOps);

        // 2. Form Setup
        GridPane formPane = new GridPane();
        formPane.setPadding(new Insets(10));
        formPane.setHgap(10);
        formPane.setVgap(10);

        tableCombo = new ComboBox<>();
        tableCombo.getItems().addAll("Restaurant", "MenuItem");
        tableCombo.setValue("Restaurant"); // Default

        txtId = new TextField(); txtId.setPromptText("ID (Integer)");
        txtName = new TextField(); txtName.setPromptText("Name");
        txtExtra1 = new TextField(); 
        txtExtra2 = new TextField(); txtExtra2.setPromptText("ResId (Integer)");

        lblExtra1 = new Label("Address:");
        lblExtra2 = new Label("ResId:");
        
        // Hide/Show fields dynamically based on selected table
        lblExtra2.setVisible(false);
        txtExtra2.setVisible(false);
        
        tableCombo.setOnAction(e -> {
            if (tableCombo.getValue().equals("Restaurant")) {
                lblExtra1.setText("Address:");
                txtExtra1.setPromptText("Address");
                lblExtra2.setVisible(false);
                txtExtra2.setVisible(false);
            } else {
                lblExtra1.setText("Price:");
                txtExtra1.setPromptText("Price (Double)");
                lblExtra2.setVisible(true);
                txtExtra2.setVisible(true);
            }
        });

        formPane.add(new Label("Select Table:"), 0, 0); formPane.add(tableCombo, 1, 0);
        formPane.add(new Label("ID:"), 0, 1);           formPane.add(txtId, 1, 1);
        formPane.add(new Label("Name:"), 0, 2);         formPane.add(txtName, 1, 2);
        formPane.add(lblExtra1, 0, 3);                  formPane.add(txtExtra1, 1, 3);
        formPane.add(lblExtra2, 0, 4);                  formPane.add(txtExtra2, 1, 4);

        // 3. Output Area Setup
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setFont(javafx.scene.text.Font.font("Monospaced", 12));
        outputArea.setPrefHeight(250);

        // 4. Layout Assembly
        VBox root = new VBox(10);
        root.getChildren().addAll(menuBar, formPane, new Label("  Console Output:"), outputArea);

        // 5. Event Handlers for Menu Items
        menuInsert.setOnAction(e -> handleInsert());
        menuSelect.setOnAction(e -> handleSelect());
        menuUpdate.setOnAction(e -> handleUpdate());
        menuDelete.setOnAction(e -> handleDelete());

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // ── Get Connection ─────────────────────────────────────────────────────────
    private Connection getConnection() throws SQLException {
        try {
            // Explicitly load the MySQL JDBC driver to bypass JavaFX module restrictions
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            log("MySQL Driver not found! Make sure the .jar is in the folder.");
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // ── CRUD Event Handlers ────────────────────────────────────────────────────
    private void handleInsert() {
        String table = tableCombo.getValue();
        try (Connection con = getConnection()) {
            if (table.equals("Restaurant")) {
                String sql = "INSERT INTO Restaurant (Id, Name, Address) VALUES (?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(txtId.getText()));
                ps.setString(2, txtName.getText());
                ps.setString(3, txtExtra1.getText());
                ps.executeUpdate();
            } else {
                String sql = "INSERT INTO MenuItem (Id, Name, Price, ResId) VALUES (?, ?, ?, ?)";
               PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(txtId.getText()));
                ps.setString(2, txtName.getText());
                ps.setDouble(3, Double.parseDouble(txtExtra1.getText()));
                ps.setInt(4, Integer.parseInt(txtExtra2.getText()));
                ps.executeUpdate();
            }
            log("Successfully inserted record into " + table);
            clearFields();
        } catch (Exception ex) {
            log("Error inserting: " + ex.getMessage());
        }
    }

    private void handleSelect() {
        String table = tableCombo.getValue();
        String sql = "SELECT * FROM " + table;
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
             
            StringBuilder sb = new StringBuilder();
            if (table.equals("Restaurant")) {
                sb.append(String.format("%-5s | %-15s | %-20s\n", "ID", "Name", "Address"));
                sb.append("--------------------------------------------------\n");
                while (rs.next()) {
                    sb.append(String.format("%-5d | %-15s | %-20s\n", 
                        rs.getInt("Id"), rs.getString("Name"), rs.getString("Address")));
                }
            } else {
                sb.append(String.format("%-5s | %-15s | %-10s | %-5s\n", "ID", "Name", "Price", "ResId"));
                sb.append("--------------------------------------------------\n");
                while (rs.next()) {
                    sb.append(String.format("%-5d | %-15s | %-10.2f | %-5d\n", 
                        rs.getInt("Id"), rs.getString("Name"), rs.getDouble("Price"), rs.getInt("ResId")));
                }
            }
            log("--- " + table + " Records ---\n" + sb.toString());
        } catch (Exception ex) {
            log("Error selecting: " + ex.getMessage());
        }
    }

    private void handleUpdate() {
        String table = tableCombo.getValue();
        try (Connection con = getConnection()) {
            if (table.equals("Restaurant")) {
                String sql = "UPDATE Restaurant SET Name=?, Address=? WHERE Id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, txtName.getText());
                ps.setString(2, txtExtra1.getText());
                ps.setInt(3, Integer.parseInt(txtId.getText()));
                int rows = ps.executeUpdate();
                log("Updated " + rows + " row(s) in Restaurant.");
            } else {
                String sql = "UPDATE MenuItem SET Name=?, Price=?, ResId=? WHERE Id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, txtName.getText());
                ps.setDouble(2, Double.parseDouble(txtExtra1.getText()));
                ps.setInt(3, Integer.parseInt(txtExtra2.getText()));
                ps.setInt(4, Integer.parseInt(txtId.getText()));
                int rows = ps.executeUpdate();
                log("Updated " + rows + " row(s) in MenuItem.");
            }
        } catch (Exception ex) {
            log("Error updating: " + ex.getMessage());
        }
    }

    private void handleDelete() {
        String table = tableCombo.getValue();
        try (Connection con = getConnection()) {
            String sql = "DELETE FROM " + table + " WHERE Id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtId.getText()));
            int rows = ps.executeUpdate();
            log("Deleted " + rows + " row(s) from " + table + ".");
            clearFields();
        } catch (Exception ex) {
            log("Error deleting: " + ex.getMessage());
        }
    }

    // ── Helper Methods ─────────────────────────────────────────────────────────
    private void log(String message) {
        outputArea.setText(message + "\n\n" + outputArea.getText());
    }

    private void clearFields() {
        txtId.clear(); txtName.clear(); txtExtra1.clear(); txtExtra2.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}