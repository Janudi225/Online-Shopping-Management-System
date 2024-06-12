/**
 * The main entry point for the Westminster Shopping Center application.
 * This class creates a {@link WestminsterShoppingManager} object, loads initial data, and starts a loop that
 * displays the application's menu until the user chooses to exit.
 */
public class Main {

    /**
     * The main method that starts the application.
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {

        //Declare a boolean value to continue the main menu loop
        boolean loop;

        // Create a WestminsterShoppingManager object and load initial data
        WestminsterShoppingManager westminster1 = new WestminsterShoppingManager();
        westminster1.loadFile();

        // Start the main loop
        /**
         * Displays the application's menu and gets user input.
         * @return true if the user wants to continue, false to exit.
         */
        while (true) {

            loop = westminster1.menuDisplay();

            if (!loop) {
                // Display a thank you message
                System.out.println("Thank you!");
                // Exit the loop
                break;
            }
        }
    }
}
