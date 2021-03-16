import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Asset implements Serializable, Printable {
    private String company;
    private String ticker;
    private double currentPrice;
    private HashMap<Date, Double> pricesHistory;

    public Asset(String company, String ticker, double currentPrice){
        this.company = company;
        this.ticker = ticker;
        this.currentPrice = currentPrice;
        this.pricesHistory = new HashMap<>();
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
                        this.company + ")\n" + "- Current Price: " + this.currentPrice + "\n"
        );

        System.out.println("- Prices History:");
        for(Map.Entry<Date, Double> entry: this.pricesHistory.entrySet()){
            System.out.println("\tDate: " + entry.getKey() + " Price: " + entry.getValue());
        }
    }

    public void variatePrice(double variability, boolean isPrint){
        double max = (double)(this.currentPrice + variability);
        double min = (double)(this.currentPrice - variability);

        double newPrice = (double) min + (Math.random() * (max - min));
        double oldPrice = this.currentPrice;

        updatePrice(newPrice);
        if(isPrint){
            System.out.println( "\n" + "*".repeat(75));
            System.out.println(this.ticker + " price changed from "
                    + oldPrice + " to " + newPrice);
            System.out.println("*".repeat(75));
        }
    }

    private void updatePrice(double newPrice){
        this.pricesHistory.put(new Date(), this.currentPrice);
        this.currentPrice = newPrice;
    }
}
