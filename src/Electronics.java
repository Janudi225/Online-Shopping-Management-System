/**
 * Represents an electronic item in a shopping centre.
 * This class extends the {@link Product} class to include additional
 * attributes specific to electronics, such as brand and warranty period.
 */
public class Electronics extends Product {

    //Brand of the electronic
    private String brand;

    //Warranty period of the electronic
    private int warPeriod;

    /**
     * Creates a new Electronics object with the specified product details.
     * @param productId   The unique ID of the product.
     * @param productName The name of the product.
     * @param numItems    The number of items in stock.
     * @param productPrice The price of the product.
     */
    public Electronics(String productId, String productName,
                       int numItems, float productPrice) {
        super(productId, productName, numItems, productPrice);
    }

    /**
     * Creates a new Electronics object with the specified product details,
     * brand, and warranty period.
     * @param productId     The unique ID of the product.
     * @param productName   The name of the product.
     * @param numItems      The number of items in stock.
     * @param productPrice  The price of the product.
     * @param brand         The brand of the electronic item.
     * @param warPeriod     The warranty period of the electronic item in months.
     */
    public Electronics(String productId, String productName,
                       int numItems, float productPrice, String brand, int warPeriod) {
        super(productId, productName, numItems, productPrice);
        this.brand = brand;
        this.warPeriod = warPeriod;
    }

    /**
     * Gets the brand of the electronic item.
     * @return The brand of the electronic item.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand of the electronic item.
     * @param brand The new brand of the electronic item.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets the warranty period of the electronic item in months.
     * @return The warranty period of the electronic item in months.
     */
    public int getWarPeriod() {
        return warPeriod;
    }

    /**
     * Sets the warranty period of the electronic item in months.
     * @param warPeriod The new warranty period of the electronic item in months.
     */
    public void setWarPeriod(int warPeriod) {
        this.warPeriod = warPeriod;
    }

    /**
     * Returns a string representation of this Electronics object,
     * including its product details, brand, and warranty period.
     * @return A string representation of this Electronics object.
     */
    @Override
    public String toString() {
        return "type-Electronics\n" + super.toString() + "\nBrand-" + brand + "\nWarranty-" + warPeriod + "\n";
    }
}
