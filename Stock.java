import java.util.*;

abstract class Stock
{
    private String company;
    private String ticker;
    private double currentPrice;
    private HashMap<Date, Double> pricesHistory;
    
    public Stock(String company, String ticker, double currentPrice)
    {
        this.company = company;
        this.ticker = ticker;
        this.currentPrice = currentPrice;
        this.pricesHistory = new HashMap<Date,Double>();
    }
    
    public String getTicker(){
        return this.ticker;
    }
    
    public String getCompany(){
        return this.company;
    }
    
    public double getCurrentPrice(){
        return this.currentPrice;
    }
    
    public HashMap<Date, Double> getPricesHistory(){
         return this.pricesHistory;   
    }
    public void print(){
        System.out.print(
             "*".repeat(100) + "\n" + "- " + this.ticker + " (" +
             this.company + ")\n" + "- Current Price: " + this.currentPrice
             + "\n" + "*".repeat(100) + "\n"
        );
    }
    
    public void print(boolean isExtended){
        System.out.print(
             "*".repeat(100) + "\n" + "- " + this.ticker + " (" +
             this.company + ")\n" + "- Current Price: " + this.currentPrice + "\n"
        );
        
        if(isExtended){
            System.out.println("- Prices History:");
            for(Map.Entry<Date, Double> entry: this.pricesHistory.entrySet()){
                System.out.println("\tDate: " + entry.getKey() + " Price: " + entry.getValue());
            }
            System.out.println("*".repeat(100));   
        }else{
            System.out.println("*".repeat(100));
        }
    }
    
    public void variatePrice(double variability){
        double max = (double)(this.currentPrice + variability);
        double min = (double)(this.currentPrice - variability);
        double newPrice = (double) min + (Math.random() * (max - min));
        this.pricesHistory.put(new Date(), this.currentPrice);
        System.out.println(this.ticker + " price changed from " 
            + this.currentPrice + " to " + newPrice);
        this.currentPrice = newPrice;
    }
    
    public void updatePrice(double price){
        this.pricesHistory.put(new Date(), this.currentPrice);
        this.currentPrice = price;
    }
}