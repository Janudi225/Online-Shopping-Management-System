import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WestminsterShoppingManagerTest {

    private WestminsterShoppingManager shoppingManager;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        shoppingManager = new WestminsterShoppingManager();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    @Test
    void testAddProduct() {
        // Create a product for testing
        Product testProduct = new Electronics("TEST1", "Test Product", 10, 50.0f, "TestBrand", 12);

        // Set up a mock user input
        ByteArrayInputStream in = new ByteArrayInputStream("1\nTEST1\nTest Product\n10\n50.0\nTestBrand\n12\n".getBytes());
        System.setIn(in);

        // Call the addProduct method
        shoppingManager.addProduct();

        // Check if the product is added successfully
        assertTrue(shoppingManager.getProducts().contains(testProduct));
    }

    @Test
    void testDelProduct() {
        // Add a product to the list for testing
        Product testProduct = new Electronics("TEST2", "Test Product", 10, 50.0f, "TestBrand", 12);
        shoppingManager.getProducts().add(testProduct);

        // Set up a mock user input
        ByteArrayInputStream in = new ByteArrayInputStream("TEST2\n".getBytes());
        System.setIn(in);

        // Call the delProduct method
        shoppingManager.delProduct();

        // Check if the product is removed successfully
        assertFalse(shoppingManager.getProducts().contains(testProduct));
    }

    @Test
    void testPrintList() {
        // Add products to the list for testing
        Product product1 = new Electronics("E1", "Electronic 1", 5, 100.0f, "Brand1", 12);
        Product product2 = new Clothing("C1", "Clothing 1", 3, 30.0f, "M", "Blue");
        shoppingManager.getProducts().add(product1);
        shoppingManager.getProducts().add(product2);

        // Set up the expected output
        String expectedOutput = product2.toString() + "\n" + product1.toString() + "\n";

        // Call the printList method
        shoppingManager.printList();

        // Check if the printed list matches the expected output
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    void testSaveFile() {
        // Add products to the list for testing
        Product product1 = new Electronics("E1", "Electronic 1", 5, 100.0f, "Brand1", 12);
        Product product2 = new Clothing("C1", "Clothing 1", 3, 30.0f, "M", "Blue");
        shoppingManager.getProducts().add(product1);
        shoppingManager.getProducts().add(product2);

        // Call the saveFile method
        shoppingManager.saveFile();

        // Read the content of the saved file
        List<String> fileContent = new ArrayList<>();
        try {
            fileContent = Files.readAllLines(Paths.get("productsFile.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check if the content of the saved file matches the expected content
        assertEquals(product2.toString(), fileContent.get(0).trim());
        assertEquals(product1.toString(), fileContent.get(1).trim());
    }

    @Test
    void testLoadFile() {
        // Create a test file with product information
        List<String> testData = List.of(
                "Clothing-E1-Electronic 1-5-100.0-Brand1-12",
                "Electronics-C1-Clothing 1-3-30.0-M-Blue"
        );
        try {
            Files.write(Paths.get("testDataFile.txt"), testData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Call the loadFile method
        shoppingManager.loadFile();

        // Check if the products are loaded correctly
        List<Product> loadedProducts = shoppingManager.getProducts();
        assertEquals(2, loadedProducts.size());
        assertTrue(loadedProducts.get(0) instanceof Electronics);
        assertTrue(loadedProducts.get(1) instanceof Clothing);
    }
}