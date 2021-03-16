import java.io.*;

/***************************************************************************************************
     Account is the abstract class extended by Admin and Account
     Account has 2 proprieties called username and password:
     Account can:
        - be saved and this method is overridden by both Admin and User
        - an instance of account can sign in
        - be retrieved thanks to the static method retrieve, which based on the file name it
          creates return an instance of User or Admin but with type Account
 ***************************************************************************************************/
abstract public class Account implements Serializable, Savable, Printable
{
    private String username;
    private String password;

    public Account(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    // save account to file
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

    // check if password is equals to object password
    public boolean signIn(String password){
        if (this.password.equals(password)){
            return true;
        }
        return false;
    }

    // print username in capital letters
    public void print(){
        System.out.println("Username: " + this.username.toUpperCase());
    }

    // retrieve account from file
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

