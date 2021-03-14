import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException {
        final String EXIT = "exit";
        // list of stock that can be bought
        ArrayList<Stock> stockAvaiable = new ArrayList<>();
        stockAvaiable.add(new Stock("Apple", "AAPL", 300.0));
        stockAvaiable.add(new Stock("Tesla", "TSLA", 670));

        Account account = authenticate();
        printOptions();
        print("Enter command: ");

        Market market = new Market(stockAvaiable);
        String input = new Scanner(System.in).nextLine();
        Command command = new Command(input);

        while(!input.equals(EXIT)){
            if(account instanceof User){
                //market.open();
                if (command.getSubject().equals(account.getUsername())){
                    if(command.getVerb().equals(Constants.PRINT.getFull()) || command.getVerb().equals(Constants.PRINT.getAbbr())){
                        ((User)account).print(command.getOptions());
                    }else{
                        print("Invalid verb\n");
                    }
                }else{
                    if(command.getVerb().equals(Constants.PRINT.getFull()) || command.getVerb().equals(Constants.PRINT.getAbbr())){
                        market.getStockByTicker(command.getSubject()).print(command.getOptions());
                    }else {
                        print("Enter amount: ");
                        int amount = new Scanner(System.in).nextInt();
                        if(command.getVerb().equals(Constants.BUY.getFull()) || command.getVerb().equals(Constants.BUY.getAbbr())){
                            ((User)account).buyStock(market.getStockByTicker(command.getSubject()), amount);
                        }else if (command.getVerb().equals(Constants.SELL.getFull()) || command.getVerb().equals(Constants.SELL.getAbbr())){
                            ((User)account).sellStock(market.getStockByTicker(command.getSubject()), amount);
                        }else{
                            print("Invalid verb\n");
                        }
                    }
                }
            }else if (account instanceof Admin){
                if(command.getSubject().equals(account.getUsername())){
                    if(command.getVerb().equals(Constants.DELETE.getFull()) || command.getVerb().equals(Constants.DELETE.getAbbr())){
                        print("Enter the username of the account you want to delete");
                        String username = new Scanner(System.in).nextLine();
                        ((Admin)account).deleteAccount(username);
                    }
                }
            }


            print("Enter command: ");
            input = new Scanner(System.in).nextLine();
            command = new Command(input);
        }

        Command.exit(account);
    }

    public static Account authenticate() throws FileNotFoundException {
        println("WELCOME TO THE STOCK MARKET INVESTOR SIMULATOR\n");
        Account account = null;
        Scanner scanner = new Scanner(System.in);
        
        print("Enter your username: ");
        String username = scanner.nextLine();
        
        if(new File(username + ".txt").exists()){
            // signin
            account = Account.retrieve(username);
            print("Enter your password: ");
            String password = scanner.nextLine();
            while(!account.getPassword().equals(password)){
                print("Wrong password... try again: ");
                password = scanner.nextLine();
            }
            println("Successfuly signed in.");
        }else{
            // sign up
            if(isAdmin(username)){
                print("Enter the key to create an admin account: ");
                String key = scanner.nextLine();
                if(key.equals(Constants.ADMIN_KEY.getFull())){
                    print("Create a new password for your account: ");
                    String newPassword = scanner.nextLine();
                    account = new Admin(username, newPassword);
                }else{
                    println("ACCESS DENIED...EXITING!");
                    System.exit(0);
                }
            }else{
                print("Create a password for your account: ");
                String password = scanner.nextLine();
                account = new User(username, password);
                println("New Account created!");
            }
        }
        return account;
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

    public static void printOptions(){
        print("(Subject -verb -options)");
    }
}
