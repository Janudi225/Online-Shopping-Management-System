import javax.swing.*;
import java.nio.file.*;
import java.util.*;
import java.io.*;

/**
 * Represents the shopping manager for the Westminster Shopping Center application.
 * This class implements the ShoppingManager interface and provides methods for managing a shopping list,
 * including adding, deleting, printing, and saving products.
 * It also interacts with the console and GUI interfaces to display menus and handle user interactions.
 */
public class WestminsterShoppingManager implements ShoppingManager {

    //Instance variables
    private final ArrayList<Product> productList;
    Scanner input = new Scanner(System.in);

    /**
     * Creates a new WestminsterShoppingManager object and initializes the product list.
     */
    public WestminsterShoppingManager() {
        this.productList = new ArrayList<>();
    }

    /**
     * Displays the console menu and handles user input for selecting tasks.
     * This method presents a menu of options to the user on the console, reads their input, and calls the
     * appropriate methods to perform the selected tasks.
     * It also handles invalid input and allows the user to exit the program.
     * @return {@code true} if the user wants to continue using the program, {@code false} if they want to exit.
     */
    public boolean menuDisplay() {
        //Print the console menu in a loop
        boolean loop=true;
        System.out.println("------------------------------------Console Menu------------------------------------\n");

        System.out.println("Enter 1: Add a new product.");
        System.out.println("Enter 2: Delete a product.");
        System.out.println("Enter 3: Print a list of the products.");
        System.out.println("Enter 4: Save in a file.");
        System.out.println("Enter 5: Open GUI.");
        System.out.println("Enter 0: Exit the program.");

        try {
            //Input the task number
            System.out.println("Enter the number of the task:");
            int userOption = input.nextInt();
            System.out.println(" ");

            if (userOption >= 0 && userOption <= 5) {
                if (userOption == 1) {
                    //Call the method to add products
                    addProduct();
                }
                if (userOption == 2) {
                    //Call the method to delete products
                    delProduct();
                }
                if (userOption == 3) {
                    //Call the method to print products
                    printList();
                }
                if (userOption == 4) {
                    //Call the method to save to the file
                    saveFile();
                }
                if(userOption==5){
                    //Create a frame for login
                    LoginFrame login1=new LoginFrame(productList);
                    login1.setTitle("Login");
                    login1.setSize(300,250);
                    login1.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
                    login1.setVisible(true);
                }
                if(userOption==0){
                    loop=false;
                }

            } else {
                System.out.println("Input option out of range.\n");
            }
        } catch (Exception e) {
            System.out.println(" ");
            System.out.println("Invalid input\n");
            input.nextLine();
        }
        return loop;
    }

    /**
     * Adds a new product to the shopping list
     * This method prompts the user to enter the product details,
     * validates the input, creates the appropriate product object (Electronics or Clothing), and adds it
     * to the product list.
     * It also handles the maximum number of products allowed in the system.
     * @throws IllegalArgumentException if the product type is invalid.
     */
    public void addProduct() {
        if (productList.size() <50) {
            System.out.println("Type of product:");
            System.out.println("Enter 1:Electronics");
            System.out.println("Enter 2:Clothing\n");

            int type;

            while(true){
                //Input product type
                System.out.println("Enter the type of product:");
                if(input.hasNextInt()){
                    type=input.nextInt();
                    if(type==1 || type==2){
                        break;
                    }
                    else{
                        System.out.println("Invalid product type. Please enter a valid product type.");
                    }
                }
                else{
                    System.out.println("Invalid product type. Please enter a valid product type.");
                    input.next();
                }
            }


            String productID = null;
            boolean isUnique = false;
            //Loop to ask the user to enter a unique product id again and again
            while (!isUnique) {
                boolean found = false;
                //Input product id
                System.out.println("Please enter the product ID:");
                productID = input.next();
                //Check if the product id already exists in the system
                for (Product product : productList) {
                    if (product.getProductId().equals(productID)) {
                        System.out.println("ProductID already exist.");
                        //Valid input, exit loop
                        found = true;
                        break;
                    }
                }
                isUnique = !found;
            }
            input.nextLine();

            //Input product name
            System.out.println("Enter the product name:");
            String productName = input.nextLine();

            int numItems;
            while(true){
                //Input number of items
                System.out.println("Enter the number of items:");
                if(input.hasNextInt()){
                    numItems=input.nextInt();
                    if(numItems>0){
                        break;
                    }
                    else{
                        System.out.println("Invalid no. of items. Please enter a valid no. of items.");
                    }
                }
                else{
                    System.out.println("Invalid no. of items. Please enter a valid no. of items.");
                    input.next();
                }
            }

            float price;
            while(true){
                //Input price
                System.out.println("Enter the product price:");
                if(input.hasNextFloat()){
                    price=input.nextFloat();
                    if(price>0){
                        break;
                    }
                    else{
                        System.out.println("Invalid price. Please enter a valid price.");
                    }
                }
                else{
                    System.out.println("Invalid price. Please enter a valid price");
                    input.next();
                }
            }

            if (type == 1) {
                //Input brand
                System.out.println("Enter the brand of the electronic:");
                String brand = input.next();

                int warranty;

                while(true){
                    System.out.println("Enter the warranty period of the electronic(In months):");
                    if(input.hasNextInt()){
                        warranty=input.nextInt();
                        if(warranty>0){
                            break;
                        }
                        else{
                            System.out.println("Invalid warranty period. Please enter a valid warranty period.");
                        }
                    }
                    else{
                        System.out.println("Invalid price. Please enter a valid price");
                        input.next();
                    }
                }
                System.out.println(" ");

                //Create an object of class Product
                Product Elect1 = new Electronics(productID, productName, numItems, price, brand, warranty);
                //Adding object to array
                productList.add(Elect1);
                System.out.println("Electronic successfully added to the system.\n");

            } else {

                String size;
                while(true){
                    //Input size of clothing
                    System.out.println("Enter the size of the clothing(XXS,XS,S,M,L,XL,XXL):");
                    if(input.hasNext()){
                        size=input.next();
                        break;
                    }
                    else{
                        System.out.println("Invalid clothing size. Please enter a valid clothing size.");
                        input.next();
                    }
                }

                if (size.equals("XXS") || size.equals("XS") || size.equals("S") || size.equals("M") || size.equals("L") || size.equals("XL") || size.equals("XXL")) {
                    //Input color of clothing
                    System.out.println("Enter the color of the clothing:");
                    String color = input.next();

                    //Create an object of class Product
                    Product Cloth1 = new Clothing(productID, productName, numItems, price, size, color);
                    //Adding object to array
                    productList.add(Cloth1);
                    System.out.println("Clothing successfully added to the system.\n");
                }
                else{
                    System.out.println("\nInvalid clothing size\n");
                }
            }
        }
        else {
            System.out.println("The system had reached the maximum no. of products");
        }
    }

    /**
     * Deletes a product from the shopping list based on its product ID.
     * This method prompts the user to enter the product ID of the product to be deleted.
     * It then searches the product list, removes the product if found, and prints a confirmation message.
     * If the product is not found, it prints an error message.
     */
    public void delProduct() {
        //Input product id
        System.out.println("Enter the product ID of the product that needs to be deleted:");
        String productId = input.next();

        boolean inList = false;

        //Check whether the input id is correct
        for (Product product : productList) {
            if (product.getProductId().equals(productId)) {
                productList.remove(product);
                System.out.println("\nProduct successfully removed.");
                System.out.println("\nRemoved product :\n");
                System.out.println(product);
                System.out.println("Remaining no. of products- " + productList.size()+"\n");
                inList = true;
                break;
            }
        }
        if (!inList) {
            System.out.println("\nProduct with the ID: " + productId + " is not in the system.\n");
        }
    }

    /**
     * Prints a list of all products in the shopping list to the console.
     * This method sorts the product list in alphabetical order by product ID and then prints each product's details.
     */
    public void printList() {
        //Sort the arrayList
        Collections.sort(productList);
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    /**
     * Saves the current shopping list to a file named "productsFile.txt".
     * This method writes each product's information to the file in a text format with each product on a separate line.
     * It handles potential I/O exceptions and prints a success or error message accordingly.
     * @throws IOException if an error occurs while writing to the file.
     */
    public void saveFile() {
        try {
            //Create a FileWriter
            FileWriter fileWri = new FileWriter("productsFile.txt");
            //Create a BufferedWriter
            BufferedWriter bufWri = new BufferedWriter(fileWri);
            //Write products into the file
            for (Product product : productList) {
                bufWri.write(product.toString());
                bufWri.write("\n");
            }
            System.out.println("Products successfully saved to the file.\n");
            //Close the file writers
            bufWri.close();
            fileWri.close();
        } catch (IOException e) {
            System.out.println("Error writing products to the file.");
        }
    }

    /**
     * Loads the shopping list from a file named "productsFile.txt".
     * This method reads the product information from the file,
     * parses the text, creates the appropriate product objects (Electronics or Clothing), and adds them to
     * the product list.
     * It handles potential I/O exceptions and prints an error message if the file cannot be read.
     * @throws IOException if an error occurs while reading the file.
     */
    public void loadFile() {
        //Create a temporary list
        ArrayList<String> tempList = new ArrayList<>();

        //Check whether the file exists
        if (Files.exists(Paths.get("productsFile.txt"))) {
            //Read the file line by line
            try (BufferedReader bufRea = new BufferedReader(new FileReader("productsFile.txt"))) {
                String line;
                while ((line = bufRea.readLine()) != null) {
                    //Split the line
                    String[] values = line.split("-");
                    //Add the split line into the temporary array
                    for (int y = 0; y < values.length; y++) {
                        tempList.add(values[y]);
                    }
                }
                //Re,ove unnecessary spaces
                for (int i = 0; i < tempList.size(); i++) {
                    if (i % 14 == 0) {
                        tempList.remove("");
                    }
                }

            } catch (IOException e) {
                System.out.println("An error occurred while reading the saved file");
            }
        }

        //Divide the read words into each object
        int listSize = 14;
        List<List<String>> subLists = new ArrayList<>();
        for (int i = 0; i < tempList.size(); i += listSize) {
            subLists.add(tempList.subList(i, Math.min(i + listSize, tempList.size())));
        }
        for (List<String> subList : subLists) {
            if (subList.get(1).equals("Electronics")) {
                String productId = subList.get(3);
                String productName = subList.get(5);
                //Create variables
                int numItems = Integer.parseInt(subList.get(7));
                float price = Float.parseFloat(subList.get(9));
                String brand = subList.get(11);
                int warranty = Integer.parseInt(subList.get(13));
                //Create an object of the class Product
                Product Elect1 = new Electronics(productId, productName, numItems, price, brand, warranty);
                //Add object to array
                productList.add(Elect1);

            } else {
                //Create variables
                String productId = subList.get(3);
                String productName = subList.get(5);
                int numItems = Integer.parseInt(subList.get(7));
                float price = Float.parseFloat(subList.get(9));
                String size = subList.get(11);
                String color = subList.get(13);
                //Create an object of the class Product
                Product Cloth1 = new Clothing(productId, productName, numItems, price, size, color);
                //Add object to array
                productList.add(Cloth1);
            }
        }
    }

    /**
     * Returns the list of products in the shopping list.
     * @return the {@code ArrayList} of {@code Product} objects representing the shopping list.
     */
    public ArrayList<Product>getProducts(){
        return productList;
    }
}

