import java.io.*;
import java.util.HashMap;

public class Admin extends Account
{
    public Admin(FileInputStream file)
    {
        Account account = retrieve(file);
        super.setSettings(account.getSettings());
        super.setUsername(account.getUsername());
    }

    public Admin(String username, HashMap<String, String> settings){
        super(username, settings);
    }

    @Override
    void save(String fileName){
        try{
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(this);

            out.close();
            file.close();

        }catch(IOException e){
            System.out.println("IOException is caught");
        }
    }

    @Override
    Account retrieve(FileInputStream file) {
        Account acc = null;

        try{
            ObjectInputStream in = new ObjectInputStream(file);

            acc = (User)in.readObject();

            in.close();
            file.close();
        }catch (IOException | ClassNotFoundException e){

        }
        return acc;
    }
}
