import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Represents the shopping cart GUI in the Westminster Shopping Center application.
 * This class displays the contents of the shopping cart in a table, calculates applicable discounts, and shows
 * he final total cost.
 */
public class ShoppingCartGUI extends JFrame {

    //Instance variables
    private int eleCount;
    private int cloCount;

    /**
     * Creates a new ShoppingCartGUI object and initializes its components.
     * @param cart The shopping cart containing the selected products.
     * @param isFirstTime Indicates whether this is the user's first purchase.
     */
    public ShoppingCartGUI(ShoppingCart cart, boolean isFirstTime) {

        String[] column = {"Product", "Quantity", "Price"};

        String[][] data = createArray(cart);

        //Create table model
        DefaultTableModel model = new DefaultTableModel(data, column);

        //Create table
        JTable table = new JTable(model);

        //Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 200));

        //Create a panel
        JPanel p1 = new JPanel();
        p1.add(scrollPane);

        String[] priceArray = priceCal(cart,isFirstTime);

        //Create JLabels
        JLabel total = new JLabel(priceArray[0]);
        JLabel fDiscount = new JLabel(priceArray[1]);
        JLabel tDiscount = new JLabel(priceArray[2]);
        JLabel fTotal = new JLabel(priceArray[3]);

        //Create a panel
        JPanel p2 = new JPanel(new GridBagLayout());
        GridBagConstraints tbg = new GridBagConstraints();

        tbg.gridx = 0;
        tbg.gridy = 0;
        tbg.insets = new Insets(0, 0, 5, 20);
        tbg.anchor = GridBagConstraints.LINE_END;
        p2.add(new JLabel("Total"), tbg);

        tbg.gridx = 1;
        tbg.insets = new Insets(0, 0, 5, 0);
        tbg.anchor = GridBagConstraints.LINE_START;
        p2.add(total, tbg);

        tbg.gridx = 0;
        tbg.gridy = 1;
        tbg.insets = new Insets(0, 0, 5, 20);
        tbg.anchor = GridBagConstraints.LINE_END;
        p2.add(new JLabel("First Purchase Discount (10%)"), tbg);

        tbg.gridx = 1;
        tbg.insets = new Insets(0, 0, 5, 0);
        tbg.anchor = GridBagConstraints.LINE_START;
        p2.add(fDiscount, tbg);

        tbg.gridx = 0;
        tbg.gridy = 2;
        tbg.insets = new Insets(0, 0, 5, 20);
        tbg.anchor = GridBagConstraints.LINE_END;
        p2.add(new JLabel("Three Items in Same Category Discount (20%)"), tbg);

        tbg.gridx = 1;
        tbg.insets = new Insets(0, 0, 5, 0);
        tbg.anchor = GridBagConstraints.LINE_START;
        p2.add(tDiscount, tbg);

        tbg.gridx = 0;
        tbg.gridy = 3;
        tbg.insets = new Insets(0, 0, 5, 20);
        tbg.anchor = GridBagConstraints.LINE_END;
        p2.add(new JLabel("Final Total"), tbg);

        tbg.gridx = 1;
        tbg.insets = new Insets(0, 0, 5, 0);
        tbg.anchor = GridBagConstraints.LINE_START;
        p2.add(fTotal, tbg);

        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.SOUTH);
    }

    /**
     * Converts the product quantity map from the shopping cart into a custom 2D array for table display.
     * @param cart The shopping cart containing the product quantity map.
     * @return A 2D array representing the product data for the table.
     */
    private String[][] createArray(ShoppingCart cart) {

        Map<Product, Integer> list = cart.getProductQuantityMap();
        int sizeOfHashMap = list.size();

        String[][] array = new String[sizeOfHashMap][3];

        AtomicInteger i = new AtomicInteger();
        list.forEach((product, quantity) -> {

            if (product instanceof Electronics) {
                array[i.get()][0] = "<html>" + product.getProductId() + "<br />" + product.getProductName() + "<br/>" +
                        ((Electronics) product).getBrand() + "," + ((Electronics) product).getWarPeriod() + "<html/>";
                eleCount+=quantity;
            } else if (product instanceof Clothing) {
                array[i.get()][0] = "<html>" + product.getProductId() + "<br />" + product.getProductName() + "<br/>" +
                        ((Clothing) product).getSize() + "," + ((Clothing) product).getColor() + "<html/>";
                cloCount+=quantity;
            }

            array[i.get()][1] = String.valueOf(quantity);

            array[i.get()][2] = String.valueOf(quantity * product.getProductPrice());


            i.getAndIncrement();
        });

        return array;
    }

    /**
     * Calculates the total price, discounts, and final total for the shopping cart.
     * @param cart The shopping cart containing the products.
     * @param isFirstTime Indicates whether the user is eligible for the first purchase discount.
     * @return An array of strings containing the calculated prices and discounts.
     */
    private String[] priceCal(ShoppingCart cart, boolean isFirstTime) {
        String[] price = new String[4];

        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        double tenPercentDiscount;
        double twentyPercentDiscount;
        double finalTotal;

        cart.getProductQuantityMap().forEach((p, q) -> {
            total.set(total.get() + (p.getProductPrice() * q));
        });

        //Calculate the discount
        if (eleCount>=3 || cloCount>=3) {
            twentyPercentDiscount = total.get() / 5;
        } else {
            twentyPercentDiscount = 0;
        }

        if (isFirstTime){
            tenPercentDiscount = total.get()/ 10;
        }else {
            tenPercentDiscount = 0;
        }

        //Calculate the total by deducting the discount
        finalTotal = Math.round((total.get() -(tenPercentDiscount+ twentyPercentDiscount))*100.0)/100.0;

        price[0] = String.valueOf(total.get());
        price[1] = String.valueOf(tenPercentDiscount);
        price[2] = String.valueOf(twentyPercentDiscount);
        price[3] = String.valueOf(finalTotal);

        return price;
    }

}


