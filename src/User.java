/**
 * Represents a user with a username and password.
 */
public class User {
    //Instance variables
    protected String username;
    protected String password;


    /**
     * Creates a new User object with the specified username and password.
     * @param username The user's username.
     * @param password The user's password.
     */
    public User(String username,String password){
        this.username=username;
        this.password=password;
    }

    /**
     * Returns the user's username.
     * @return The user's username.
     */
    public String getName(){
        return username;
    }

    /**
     * Returns the user's password.
     * @return The user's password.
     */
    public String getPassword(){
        return password;
    }

    /**
     * Sets the user's username.
     * @param name The new username.
     */
    public void setName(String name){
        this.username=name;
    }


    /**
     * Sets the user's password.
     * @param password The new password.
     */
    public void setPassword(String password){
        this.password=password;
    }

    /**
     * Returns a string representation of the User object in the format "username,password".
     * @return A string representation of the User object.
     */
    public String toString(){
        return username+","+password;
    }
}
