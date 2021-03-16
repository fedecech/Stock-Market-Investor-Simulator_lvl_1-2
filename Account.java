import java.io.*;

abstract public class Account implements Serializable, Savable, Printable
{
    private String username;
    private String password;

    public Account(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public void save(Object object){
        try{
            FileOutputStream file = new FileOutputStream(this.username + Constants.FILE_ACC_EXTENTION.getFull());
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(object);

            out.close();
            file.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public boolean signIn(String password){
        if (this.password.equals(password)){
            return true;
        }
        return false;
    }

    public void print(){
        System.out.println("Username: " + this.username.toUpperCase());
    }

    public static Account retrieve(String username){
        Account acc = null;
        try{
            FileInputStream file = new FileInputStream(username + Constants.FILE_ACC_EXTENTION.getFull());
            ObjectInputStream in = new ObjectInputStream(file);

            acc = username.contains(Constants.ADMIN_SUFFIX.getFull()) ? (Admin) in.readObject() : (User) in.readObject() ;

            in.close();
            file.close();
        }catch (IOException | ClassNotFoundException e){

        }
        return acc;
    }
}

