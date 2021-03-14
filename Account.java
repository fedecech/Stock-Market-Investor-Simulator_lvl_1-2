import java.util.*;
import java.io.*;

abstract public class Account implements Serializable
{
    private String username;
    private HashMap<String, String> settings;

    public Account(){

    }

    public Account(String username, HashMap<String, String> settings)
    {
        this.username = username;
        this.settings = settings;
    }

    public String getUsername(){
        return this.username;
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

    abstract void save(String fileName);

    abstract Account retrieve(FileInputStream file);
}