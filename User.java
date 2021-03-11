import java.util.*;

public class User extends Account
{
    private ArrayList<Stock> myStocks;
    private double balance;
    
    public User(String username)
    {
        super(username);
        this.myStocks = new ArrayList<>();
        this.balance = 1000;
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
    
    public void print(){
        System.out.print(
            "*".repeat(50) + "\n" + super.getUsername().toUpperCase() + "\nBalance: " + this.balance + "\n" +
            "*".repeat(50) + "\n"
        );
    }
    
    public void print(boolean isExtended){
        System.out.print(
            "*".repeat(50) + "\n" + super.getUsername().toUpperCase()
            + "\n"  + "Balance: " + this.balance + "\n"
        );
        
        if(isExtended){
            int apple = 0;
            int tesla = 0;
            for(int i=0; i<this.myStocks.size(); i++){
                if(myStocks.get(i) instanceof Apple){
                    apple += 1;
                }else if (myStocks.get(i) instanceof Tesla){
                    tesla += 1;
                }
            }
            
            System.out.print("AAPL:\nTotal stocks: " + apple + 
            "\nTotal value: " + (new Apple().getCurrentPrice() * apple + "\n"));
            System.out.print("TSLA:\nTotal stocks: " + tesla + 
            "\nTotal value: " + (new Tesla().getCurrentPrice() * tesla + "\n"));
        }
        System.out.println("*".repeat(50));
    }
}
