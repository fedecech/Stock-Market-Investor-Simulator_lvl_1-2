import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException {
        // list of stock that can be bought
        ArrayList<Asset> stockAvailable = new ArrayList<>();
        stockAvailable.add(new Crypto("Bitcoin", "BTC", 50000,5));
        stockAvailable.add(new Crypto("Ethereum", "ETH", 1700,2));
        stockAvailable.add(new Crypto("Ripple", "XRP", 2000,4));
        stockAvailable.add(new Crypto("Bitcoin Cash", "BCH", 2233,3));
        stockAvailable.add(new Crypto("Litecoin", "LTC", 333,1));

        stockAvailable.add(new Stock("Apple", "AAPL", 300.0, "Steve Jobs"));
        stockAvailable.add(new Stock("Tesla", "TSLA", 300.0, "Elon Mask"));
        stockAvailable.add(new Stock("Microsoft", "MSFT", 300.0, "Bill Gates"));


        Account account = authenticate();

        Market market = new File(Constants.MARKET_FILE_NAME.getFull() + Constants.FILE_MARK_EXTENTION.getFull()).exists()
        ? Market.retrieve(Constants.MARKET_FILE_NAME.getFull()) : new Market(Constants.MARKET_FILE_NAME.getFull(), stockAvailable);

        boolean showUpdates = inputString("Do you want to be updated on the price change of the assets? (y/n)").equals("y");
        Menu menu = new Menu(account, market);
        menu.print();
        int choice = menu.choose();

        while(choice != menu.getChoices().size()){
            menu.perform(choice);
            menu.print();

            market.variate(account instanceof User && showUpdates);
            choice = menu.choose();
        }
    }

    public static Account authenticate() throws FileNotFoundException {
        println("WELCOME TO THE STOCK MARKET INVESTOR SIMULATOR\n");
        String username = inputUsername();

        if(new File(username + Constants.FILE_ACC_EXTENTION.getFull()).exists()){
            // sign in
            return signIn(username);
        }else{
            // sign up
            return signUp(username);
        }
    }

    public static Account signIn(String username){
        Account account = Account.retrieve(username);
        boolean isSignedIn = account.signIn(inputPassword(false));
        while (!isSignedIn){
            println("Wrong password... Try Again!");
            isSignedIn = account.signIn(inputPassword(false));
        }
        println("Successfully signed in.");
        return account;
    }

    public static Account signUp(String username){
        Account account = null;
        if(isAdmin(username)){
            account =  adminSignUp(username);
        }else{
            account =  new User(username, inputPassword(true));
        }
        println("New Account created!");
        return account;
    }

    public static Account adminSignUp(String username){
        String key = inputString("Enter the key to create an admin account: ");
        String newPassword = "";
        if(key.equals(Constants.ADMIN_KEY.getFull())){
            newPassword = inputPassword(true);
        }else{
            newPassword = adminSignUpReferKey(key);
        }
        return new Admin(username, newPassword);
    }

    static String adminSignUpReferKey(String key){
        String referUsername = inputString("Enter the username of the admin account that shared the key: ");
        String newPassword = "";
        if(key.equals(((Admin)Account.retrieve(referUsername)).getKey())){
            newPassword = inputPassword(true);
        }else{
            println("ACCESS DENIED...EXITING!");
            System.exit(0);
        }
        return newPassword;
    }

    public static String inputUsername(){
        return inputString("Enter your username: ");
    }

    public static String inputPassword(boolean newPassword){
        if (!newPassword) {
            return inputString("Enter your password: ");
        } else {
            return inputString("Create the password for your account:");
        }
    }

    public static boolean isAdmin(String username){
        return username.contains(Constants.ADMIN_SUFFIX.getFull());
    }
    public static void print(String msg){
        System.out.print(msg);
    }
    
    public static void println(String msg){
        System.out.println(msg);
    }

    public static String inputString(String msg){
        print(msg);
        return new Scanner(System.in).nextLine();
    }
}
