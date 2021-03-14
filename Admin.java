import java.io.*;
import java.util.HashMap;

public class Admin extends Account
{

    public Admin(String username, String password){
        super(username, password, new HashMap<String, String>());
    }

    @Override
    public void save(){
        try{
            FileOutputStream file = new FileOutputStream(super.getUsername() + ".txt");
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(this);

            out.close();
            file.close();

        }catch(IOException e){
            System.out.println("IOException is caught");
        }
    }
    
    public void deleteAccount(String username){
        File file = new File(username + ".txt");
        if (file.delete()) { 
          System.out.println("Account deleted: " + username);
        } else {
          System.out.println("Failed to delete the account: "  + username);
        } 
    }
}
