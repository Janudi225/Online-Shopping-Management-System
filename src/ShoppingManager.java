/**
 * Represents the interface for managing a shopping list.
 * Classes implementing this interface provide methods for adding, deleting, printing, and saving products
 * to a shopping list.
 */
public interface ShoppingManager {
    /**
     * Adds a new product to the shopping list.
     */
    void addProduct();

    /**
     * Deletes a product from the shopping list.
     */
    void delProduct();

    /**
     * Prints the contents of the shopping list to the console.
     */
    void printList();

    /**
     * Saves the shopping list to a file.
     */
    void saveFile();
}
