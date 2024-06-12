import java.io.Serializable;

/**
 * Represents a product in the Westminster Shopping Center application.
 * This abstract class defines common properties and behaviors for all
 * product types. It serves as a base class for more specific product
 * subclasses like {@link Electronics} and {@link Clothing}.
 */
public abstract class Product implements Comparable<Product>, Serializable {

    //Instance variables
    private String productId;
    private  String productName;
    private int numItems;
    private double productPrice;

    /**
     * Creates a new Product object with the specified properties.
     * @param productId The product ID.
     * @param productName The product name.
     * @param numItems The number of items in stock.
     * @param productPrice The product price.
     */
    public Product(String productId,String productName,int numItems,float productPrice){
        this.productId=productId;
        this.productName=productName;
        this.numItems=numItems;
        this.productPrice=productPrice;
    }

   //Getters
    public String getProductId(){
        return productId;
    }
    public String getProductName(){
        return productName;
    }
    public int getNumItems(){
        return numItems;
    }
    public double getProductPrice(){
        return productPrice;
    }

    //Setters
    public void setProductId(String productId){
        this.productId=productId;
    }
    public void setProductName(String productName){
        this.productName=productName;
    }
    public void setNumItems(int numItems){
        this.numItems=numItems;
    }
    public void setProductPrice(float productPrice){
        this.productPrice=productPrice;
    }

    /**
     * Returns a string representation of the product, including its ID, name, number of items, and price.
     * @return A string representation of the product.
     */
    @Override
    public String toString(){
        return "Product id-"+productId+"\nProduct name-"+productName+"\nNo. of items-"+numItems+"\nPrice-"+productPrice;
    }

    /**
     * Compares this product to another product based on their product IDs.
     * @param p The product to compare to.
     * @return A negative integer, zero, or a positive integer if this product is less than, equal to, or greater than
     * the specified product, respectively.
     */
    @Override
    public int compareTo(Product p) {
        return this.productId.compareTo(p.productId);
    }
}
