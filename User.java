import java.nio.file.Paths;
import java.util.*;
import java.io.*;

public class User extends Account
{
    private ArrayList<Stock> myStocks;
    private double balance;


    public User(FileInputStream file){
        Account account = retrieve(file);
//        super.setSettings(account.getSettings());
        super.setUsername(account.getUsername());
        this.myStocks = ((User)account).getMyStocks();
        this.balance = ((User)account).getBalance();
    }
    public User(String username )
    {
        super(username, new HashMap<String, String>());
        this.myStocks = new ArrayList<>();
        this.balance = 1000;
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
            e.printStackTrace();
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

    public void buyStock(Stock stock, int amount){
        if(this.balance > (stock.getCurrentPrice() * amount)){
            for(int i=0; i<amount; i++){
                this.myStocks.add(stock);
                this.balance -= stock.getCurrentPrice();
            }
            System.out.println("You bought " + amount + " stocks of " + stock.getCompany());
        }else{
            System.out.println("You do not have enought money");
        }
        System.out.println("*".repeat(50));
    }

    public void sellStock(Stock stock, int amount){
        for(int i=0; i<amount; i++){
            this.myStocks.remove(stock);
            this.balance += stock.getCurrentPrice();
        }
        System.out.println("You sold " + amount + " stocks of " + stock.getCompany());
        System.out.println("*".repeat(50));
    }

    public void print(ArrayList<String> options){
        double total = 0;
        for (Stock stock: this.myStocks){
            total += stock.getCurrentPrice();
        }
        System.out.print(
                "*".repeat(50) + "\n" + super.getUsername().toUpperCase()
                        + "\n"  + "Balance: " + this.balance + "\nTotal stocks: " + this.myStocks.size() + " (Value: " +
                        total + ")" +"\n"
        );

        if(options.contains(Constants.EXTENDED.getFull()) || options.contains(Constants.EXTENDED.getAbbr())){
            HashMap<String, Integer> stocksNumber = new HashMap<>();
            for (Stock s: this.myStocks){
                String ticker = s.getTicker();
                int count = (int) this.myStocks.stream().filter(stock -> stock.getTicker().equals(ticker)).count();
                stocksNumber.put(ticker, count);
            }

            for (Map.Entry<String, Integer> map: stocksNumber.entrySet()){
                System.out.println(map.getKey() + ": \nTotal stocks: " + map.getValue() + "\nTotal value: ");
            }

        }
        System.out.println("*".repeat(50));
    }

    public ArrayList<Stock> getMyStocks() {
        return myStocks;
    }

    public void setMyStocks(ArrayList<Stock> myStocks) {
        this.myStocks = myStocks;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
