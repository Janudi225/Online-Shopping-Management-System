import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Represents the registration frame for the Westminster Shopping Center application.
 * This class provides a GUI for users to register by entering their username and password.
 * It also handles loading and saving user data to a file.
 */
public class RegisterFrame extends JFrame{
    private static JTextField userText;
    private static JPasswordField passText;
    protected ArrayList<User>userDetails;

    /**
     * Creates a new RegisterFrame object and initializes its components.
     * @param productList The list of products to be displayed in the main frame after registration.
     */
    public RegisterFrame(ArrayList<Product> productList) {

        //Initialize the arrayList
        this.userDetails=new ArrayList<>();

        //Create a panel
        JPanel registerPanel1 = new JPanel();

        add(registerPanel1);
        registerPanel1.setLayout(null);

        JLabel userLabel1 = new JLabel("User");
        userLabel1.setBounds(10, 20, 80, 25);
        registerPanel1.add(userLabel1);

        userText = new JTextField(25);
        userText.setBounds(100, 20, 165, 25);
        registerPanel1.add(userText);

        JLabel passLabel1 = new JLabel("Password");
        passLabel1.setBounds(10, 55, 80, 25);
        registerPanel1.add(passLabel1);

        passText = new JPasswordField(25);
        passText.setBounds(100, 55, 165, 25);
        registerPanel1.add(passText);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(100, 100, 90, 25);
        registerPanel1.add(registerButton);


        registerButton.addActionListener(e ->{
            String user=userText.getText();
            String password=String.valueOf(passText.getPassword());

            if (!user.isEmpty() && !password.isEmpty()){
                //Create User object
                User user1 = new User(user, password);
                //Add user to the array
                userDetails.add(user1);
                try {
                    FileWriter fileWri = new FileWriter("userFile.txt",true);
                    BufferedWriter bufWri = new BufferedWriter(fileWri);
                    bufWri.write(user1 +"\n");
                    bufWri.close();
                    fileWri.close();
                } catch (IOException error) {
                    System.out.println("Error writing products to the file.");
                }

                //Open the main frame
                MainFrame mainFrame1 = new MainFrame(productList,true);
                mainFrame1.setTitle("Westminster  Shopping Center");
                mainFrame1.setVisible(true);
                mainFrame1.pack();
                mainFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dispose();
            }

        });

        }

    /**
     * Loads the registered users from a file.
     */
    public void loadUsers() {
        ArrayList<ArrayList<String>> tempUserList = new ArrayList<>();

        //Load the file
        if (Files.exists(Paths.get("userFile.txt"))) {
            try (BufferedReader bufRea = new BufferedReader(new FileReader("userFile.txt"))) {
                String line;
                while ((line = bufRea.readLine()) != null) {
                    ArrayList<String> tempList1 = new ArrayList<>(); // Create a new ArrayList for each line
                    String[] values = line.split(",");
                    for (int y = 0; y < values.length; y++) {
                        tempList1.add(values[y]);
                    }
                    tempUserList.add(new ArrayList<>(tempList1)); // Add a copy of tempList1 to tempUserList
                }
            } catch (IOException e) {
                System.out.println("An error occurred while reading the saved file");
            }
        }

        //Create objects using retrieved data
        for (ArrayList<String> userValues : tempUserList) {
            // Ensure there are at least two values (username and password)
            if (userValues.size() >= 2) {
                String username = userValues.get(0);
                String password = userValues.get(1);
                User user = new User(username, password);
                userDetails.add(user);
            }
        }
        System.out.println(userDetails);
    }

    /**
     * Returns the list of registered users.
     * @return The list of registered users.
     */
    public ArrayList<User>getUsers(){
        return userDetails;
    }
}
