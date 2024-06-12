import javax.swing.*;
import java.util.ArrayList;

/**
 * Represents the login frame for the Westminster Shopping Center application.
 * This class creates a GUI that allows users to enter their username and
 * password to log in to the system.
 * It also provides a button for new customers to register.
 */
public class LoginFrame extends JFrame{
    private static JTextField userText;
    private static JPasswordField passText;

    /**
     * Creates a new LoginFrame object and initializes its components.
     * @param productList The list of products available in the system.
     */
    public LoginFrame(ArrayList <Product> productList){
        //Create a JFrame
        RegisterFrame regFrame1=new RegisterFrame(productList);

        regFrame1.loadUsers();

        ArrayList<User>userDetails=regFrame1.getUsers();

        //Create a panel
        JPanel loginPanel1=new JPanel();

        //Add panel to the frame
        add(loginPanel1);
        loginPanel1.setLayout(null );

        //Create userLabel1
        JLabel userLabel1 = new JLabel("User");
        userLabel1.setBounds(10,20,80,25);
        loginPanel1.add(userLabel1);

        //Create a typing area for username
        userText=new JTextField(25);
        userText.setBounds(100,20,165,25);
        loginPanel1.add(userText);

        //Create passLabel1
        JLabel passLabel1 = new JLabel("Password");
        passLabel1.setBounds(10,55,80,25);
        loginPanel1.add(passLabel1);

        //Create a typing area for the password
        passText=new JPasswordField(25);
        passText.setBounds(100,55,165,25);
        loginPanel1.add(passText);

        //Create loginButton
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100,100,80,25);
        loginPanel1.add(loginButton);

        //Action listener to handle login attempts
        loginButton.addActionListener(e -> {
            String user=userText.getText();
            String password=String.valueOf(passText.getPassword());
            boolean isLogin = false;

            if (!user.isEmpty() && !password.isEmpty()){
                for (User user1: userDetails){
                    if (user1.getName().equals(user) && user1.getPassword().equals(password)){
                        isLogin = true;
                        break;
                    }
                }
                if (isLogin){
                    MainFrame mainFrame = new MainFrame(productList, false);
                    mainFrame.setTitle("Westminster Shopping Center");
                    mainFrame.setVisible(true);
                    mainFrame.pack();
                    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Login Failed.");
                }
            }
        });

        //Create registerLabel1
        JLabel registerLabel=new JLabel("Are you a new customer?");
        registerLabel.setBounds(10, 150, 500, 25);
        loginPanel1.add(registerLabel);

        //Create registerButton
        JButton registerButton=new JButton("Register");
        registerButton.setBounds(100,175,100,25);
        loginPanel1.add(registerButton);

        //Action listener to open a frame for RegisterFrame
        registerButton.addActionListener(e -> {
            RegisterFrame regFrame2=new RegisterFrame(productList);
            regFrame2.setTitle("Register");
            regFrame2.setSize(300, 250);
            setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
            regFrame2.setVisible(true);
        });
    }
}
