import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// --- Product Interface ---
interface Product {
    void displayDetails();
}

// --- Legacy Code  ---
class LegacyItem {
    private int itemId;
    private String description;

    public LegacyItem(int itemId, String description) {
        this.itemId = itemId;
        this.description = description;
    }

    public void print() {
        System.out.println("Legacy Item [" + itemId + "]: " + description);
    }
}

// --- Adapter Pattern Implementation ---
class ProductAdapter implements Product {
    private LegacyItem legacyItem;

    public ProductAdapter(LegacyItem legacyItem) {
        this.legacyItem = legacyItem;
    }

    @Override
    public void displayDetails() {
        // Adapting the print() method to displayDetails()
        legacyItem.print();
    }
}

// --- New Product Implementation ---
class NewProduct implements Product {
    private String name;

    public NewProduct(String name) {
        this.name = name;
    }

    @Override
    public void displayDetails() {
        System.out.println("New Product: " + name);
    }
}

// --- Singleton & Iterator Implementation ---
class InventoryManager {
    private static InventoryManager instance;
    private List<Product> products;

    // Private constructor for Singleton
    private InventoryManager() {
        products = new ArrayList<>();
    }

    public static InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    // Returning an Iterator for the Iterator Pattern
    public Iterator<Product> returnInventory() {
        return products.iterator();
    }
}

// --- Main Execution ---
public class Main {
    public static void main(String[] args) {
        // Get the Singleton instance
        InventoryManager inventory = InventoryManager.getInstance();

        // Adding a New Product
        inventory.addProduct(new NewProduct("Quantum Laptop"));

        // Adding a Legacy Item via the Adapter
        LegacyItem oldScanner = new LegacyItem(101, "Industrial Barcode Scanner (Model 2015)");
        Product adaptedLegacyItem = new ProductAdapter(oldScanner);
        inventory.addProduct(adaptedLegacyItem);

        // Adding more items
        inventory.addProduct(new NewProduct("Smart Watch"));
        inventory.addProduct(new ProductAdapter(new LegacyItem(505, "Dot Matrix Printer")));

        // Iterate through the inventory using Iterator
        System.out.println("--- Current Inventory Status ---");
        Iterator<Product> it = inventory.returnInventory();
        
        while (it.hasNext()) {
            Product p = it.next();
            p.displayDetails();
        }
    }
}