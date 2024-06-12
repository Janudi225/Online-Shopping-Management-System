import java.util.HashMap;
import java.util.Map;

/**
 * Represents the shopping cart in the Westminster Shopping Center application.
 * This class manages a collection of products and their quantities, ensuring that only one instance of the shopping
 * cart exists using the singleton pattern.
 */
public class ShoppingCart {
    // Instance variables
    private final Map<Product, Integer> productQuantityMap;

    // Singleton instance
    private static ShoppingCart instance;

    /**
     * Private constructor to prevent external instantiation.
     */
    private ShoppingCart() {
        this.productQuantityMap = new HashMap<>();
    }

    /**
     * Returns the singleton instance of the shopping cart.
     * @return The singleton instance of ShoppingCart.
     */
    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    /**
     * Adds a product to the shopping cart.
     * @param product The product to add.
     */
    public void addProduct(Product product) {
        // Check if the product is already in the cart
        if (productQuantityMap.containsKey(product)) {
            // Increment the quantity if the product is already in the cart
            int quantity = productQuantityMap.get(product);
            productQuantityMap.put(product, quantity + 1);
        } else {
            // Add the product with a quantity of 1 if it's not in the cart
            productQuantityMap.put(product, 1);
        }
    }

    /**
     * Removes a product from the shopping cart.
     * @param product The product to remove.
     */
    public void remove(Product product) {
    }

    /**
     * Calculates the total cost of items in the shopping cart.
     */
    public void totCost() {
    }

    /**
     * Returns the product quantity map.
     * @return The map containing products and their quantities.
     */
    public Map<Product, Integer> getProductQuantityMap() {
        return productQuantityMap;
    }
}

