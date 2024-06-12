import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Represents the main frame of the Westminster Shopping Center application.
 * This class displays a list of products, allows users to filter them by category,
 * view product details, and add products to a shopping cart.
 */
public class MainFrame extends JFrame {
    // instance variables
    private final JTable table;
    private String selectedType = "All";

    // Make the tableModel accessible within the class
    private final DefaultTableModel tableModel;

    // Add this variable to store all products
    private final ArrayList<Product> allProducts;
    private final JTextArea productDetails;

    private Product selectedProduct = null;

    private ShoppingCart cart;

    /**
     * Creates a new MainFrame object and initializes its components.
     * @param products The list of products to display.
     * @param isFirstTime True if this is the first time the shopping cart is being displayed, false otherwise.
     */
    public MainFrame(ArrayList<Product> products,boolean isFirstTime) {
        // Store all products
        this.allProducts = new ArrayList<>(products);

        // Create a single shopping cart
        cart = ShoppingCart.getInstance();

        //Contains shopping cart button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //Create Shopping Cart Button
        JButton shoppingCartButton = new JButton("Shopping Cart");
        //Calling action listener for Shopping Cart Button
        shoppingCartButton.addActionListener(e -> showShoppingCartItems(isFirstTime));
        //Adding Shopping Cart Button to topPanel
        topPanel.add(shoppingCartButton);

        //Contains drop down label and combo box
        JPanel dropDownPanel = new JPanel();

        //Create label "Select Product Category"
        JLabel categoryLabel = new JLabel("Select Product Category");
        String[] productType = {"All", "Electronics", "Clothing"};
        //Add product types to the combo box
        JComboBox<String> productTypeCombo = new JComboBox<>(productType);
        productTypeCombo.setSelectedIndex(0);
        //Add label "Select Product Category" to the dropDownPanel
        dropDownPanel.add(categoryLabel);
        //Add combo box to the dropDownPanel
        dropDownPanel.add(productTypeCombo);

        productTypeCombo.addActionListener(e -> {
            selectedType = (String) productTypeCombo.getSelectedItem();
            filterProductsByCategory(selectedType);
        });

        //Contains product table
        JPanel tablePanel = new JPanel(new BorderLayout());

        // create table model
        String[] columnNames = {"Product ID", "Name", "Category", "Price", "Info"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            //make the cell uneditable
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //add all the products to the table
        for (Product product : products) {
            addProductToTable(product);
        }

        //creating new table using tableModel
        table = new JTable(tableModel);
        //create scroll pane to the table
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(750, 300));
        //add scroll panel of the table to the tablePanel
        tablePanel.add(tableScrollPane);

        // Enable automatic row sorting
        table.setAutoCreateRowSorter(true);
        // Sort by the first column (Product ID)
        table.getRowSorter().toggleSortOrder(0);
        // Sort by the second column (Name)
        table.getRowSorter().toggleSortOrder(1);
        // Sort by the third column (Category)
        table.getRowSorter().toggleSortOrder(2);
        // Sort by the fourth column (Price)
        table.getRowSorter().toggleSortOrder(3);
        // Sort by the fifth column (Info)
        table.getRowSorter().toggleSortOrder(3);


        //Contains tablePanel and dropDownPanel
        JPanel midPanel = new JPanel(new BorderLayout(1, 20));

        //Add dropDownPanel to the midPanel
        midPanel.add(dropDownPanel, BorderLayout.NORTH);
        //Add tablePanel to the midPanel
        midPanel.add(tablePanel, BorderLayout.CENTER);

        //Contains product details labels
        JPanel productDetailsPanel = new JPanel();
        productDetailsPanel.setLayout(new BoxLayout(productDetailsPanel, BoxLayout.Y_AXIS));
        productDetailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        //Selected product title
        JLabel productDetailsLabel = new JLabel("Selected Product-Details");

        //Text area for product details
        productDetails = new JTextArea();
        productDetails.setEditable(false);

        // ListSelectionListener to the table
        table.getSelectionModel().addListSelectionListener(new TableSelectionListener());

        // Add to shopping cart button
        JButton addToShoppingCartButton = new JButton("Add to Shopping Cart");
        addToShoppingCartButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cart.addProduct(selectedProduct);
                    }
                }
        );

        //Add elements to the productDetailsPanel
        productDetailsPanel.add(productDetailsLabel);
        productDetailsPanel.add(productDetails);
        productDetailsPanel.add(addToShoppingCartButton);

        //Add topPanel, midPanel, productDetailsPanel to the MainFrame
        add(topPanel, BorderLayout.NORTH);
        add(midPanel, BorderLayout.CENTER);
        add(productDetailsPanel, BorderLayout.SOUTH);
    }

    /**
     * Filters the table to display only products of the specified category.
     * @param category The category to filter by (All, Electronics, or Clothing).
     */
    private void filterProductsByCategory(String category) {
        // Clear existing rows
        tableModel.setRowCount(0);

        if ("All".equals(category)) {
            for (Product product : allProducts) {
                //Calling method to display products
                addProductToTable(product);
            }
        } else {
            for (Product product : allProducts) {
                if (("Electronics".equals(category) && product instanceof Electronics) || ("Clothing".equals(category) && product instanceof Clothing)) {
                    //Calling method to display products
                    addProductToTable(product);
                }
            }
        }
    }

    /**
     * Adds a product to the table.
     * @param product The product to add.
     */
    private void addProductToTable(Product product) {
        //Initializing an array called tempArray
        String[] tempArray;

        if (product instanceof Electronics) {
            //Add electronics to the tempArray
            tempArray = new String[]{product.getProductId(), product.getProductName(), "Electronics",
                    String.valueOf(product.getProductPrice()), ((Electronics) product).getBrand() +
                    "," + ((Electronics) product).getWarPeriod() + " months warranty"};
        } else if (product instanceof Clothing) {
            //Add clothings to the tempArray
            tempArray = new String[]{product.getProductId(), product.getProductName(), "Clothing",
                    String.valueOf(product.getProductPrice()), ((Clothing) product).getSize() + ", " +
                    ((Clothing) product).getColor()};
        } else {
            return; // Handle other product types as needed
        }
        //Add products to the rows of the table
        tableModel.addRow(tempArray);
    }


    // Inner class for table selection listener
    class TableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {

                    // Grab the product Id from the selected row
                    Object selectedProdId = table.getValueAt(row, 0);

                    // Get the product from the ArrayList
                    for (Product product : allProducts) {
                        if (product.getProductId().equals(selectedProdId)) {
                            selectedProduct = product;
                        }
                    }
                    // Display details of the selected product
                    if (selectedProduct != null) {
                       String details = selectedProduct.toString();
                        details = details.replaceAll(", ", "\n");
                        productDetails.setText(details);
                    }
                }
            }
        }
    }


    /**
     * Displays the shopping cart GUI.
     * @param isFirstTime True if this is the first time the shopping cart is being displayed, false otherwise.
     */
    public void showShoppingCartItems(boolean isFirstTime) {
        ShoppingCartGUI frame = new ShoppingCartGUI(cart,isFirstTime);
        frame.setTitle("Shopping Centre");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

}
