import java.util.*;
import java.io.*;

abstract public class Account implements Serializable
{
    private String username;
    private String password;
    private HashMap<String, String> settings;

    public Account(String username, String password, HashMap<String, String> settings)
    {
        this.username = username;
        this.password = password;
        this.settings = settings;
    }

    public String getUsername(){
        return this.username;
    }
    
    public String getPassword(){
        return this.password;
    }

    public HashMap<String, String> getSettings() {
        return settings;
    }

    public void setSettings(HashMap<String, String> settings) {
        this.settings = settings;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    abstract public void save();

    public static Account retrieve(String username){
        Account acc = null;
        try{
            FileInputStream file = new FileInputStream(username + ".txt");
            ObjectInputStream in = new ObjectInputStream(file);

            acc = username.contains(Constants.ADMIN_SUFFIX.getFull()) ? (Admin) in.readObject() : (User) in.readObject() ;

            in.close();
            file.close();
        }catch (IOException | ClassNotFoundException e){

        }
        return acc;
    }
}