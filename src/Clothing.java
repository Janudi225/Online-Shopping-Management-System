/**
 * Represents a clothing item in a shopping centre.
 * This class extends the {@link Product} class to include additional
 * attributes specific to clothing, such as size and color.
 */
public class Clothing extends Product {

    //Size of the clothing
    private String size;

    //Color of the clothing
    private String color;

    /**
     * Creates a new Clothing object with the specified product details.
     *
     * @param productId   The unique ID of the product.
     * @param productName The name of the product.
     * @param numItems    The number of items in stock.
     * @param productPrice The price of the product.
     */
    public Clothing(String productId, String productName,
                    int numItems, float productPrice) {
        super(productId, productName, numItems, productPrice);
    }

    /**
     * Creates a new Clothing object with the specified product details,
     * size, and color.
     * @param productId    The unique ID of the product.
     * @param productName  The name of the product.
     * @param numItems     The number of items in stock.
     * @param productPrice The price of the product.
     * @param size         The size of the clothing item.
     * @param color        The color of the clothing item.
     */
    public Clothing(String productId, String productName,
                    int numItems, float productPrice, String size, String color) {
        super(productId, productName, numItems, productPrice);
        this.size = size;
        this.color = color;
    }

    /**
     * Gets the size of the clothing item.
     * @return The size of the clothing item.
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the size of the clothing item.
     * @param size The new size of the clothing item.
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Gets the color of the clothing item.
     * @return The color of the clothing item.
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the color of the clothing item.
     * @param color The new color of the clothing item.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Returns a string representation of this Clothing object,
     * including its product details, size, and color.
     * @return A string representation of this Clothing object.
     */
    @Override
    public String toString() {
        return "type-Clothing\n" + super.toString() + "\nSize-" + size + "\nColor-" + color + "\n";
    }
}
