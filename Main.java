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

        Account user = start();
        printOptions();
        print("Enter command: ");

        Market market= new Market(stockAvaiable);
        market.open();

        String input = new Scanner(System.in).nextLine();

        Command command = new Command(input);

        while(!input.equals(EXIT)){

            if (command.getSubject().equals(user.getUsername())){
                if(command.getVerb().equals(Constants.PRINT.getFull()) || command.getVerb().equals(Constants.PRINT.getAbbr())){
                    ((User)user).print(command.getOptions());
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
                        ((User)user).buyStock(market.getStockByTicker(command.getSubject()), amount);
                    }else if (command.getVerb().equals(Constants.SELL.getFull()) || command.getVerb().equals(Constants.SELL.getAbbr())){
                        ((User)user).sellStock(market.getStockByTicker(command.getSubject()), amount);
                    }else{
                        print("Invalid verb\n");
                    }
                }
            }

            print("Enter command: ");
            input = new Scanner(System.in).nextLine();
            command = new Command(input);
        }

        Command.exit(user);
    }

    // Start new Timer to change prices randomly and returns a new User
    //
    public static Account start() throws FileNotFoundException {
        print("WELCOME TO THE STOCK MARKET INVESTOR SIMULATOR\n");
        Account user = null;

        if(new File("account.txt").exists()){
            user = new User(new FileInputStream("account.txt"));
        }else{
            print("Enter your username: ");
            String username = new Scanner(System.in).nextLine();
            user = new User(username);
        }
        return user;
    }

    public static void print(String msg){
        System.out.print(msg);
    }

    public static void printOptions(){
        print("(Subject -verb -options)");
    }
}
