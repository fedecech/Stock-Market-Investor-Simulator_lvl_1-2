import java.util.*;

public class User extends Account
{
    private ArrayList<Asset> myAssets;
    private double balance;
    
    public User(String username, String password)
    {
        super(username, password);
        this.myAssets = new ArrayList<>();
        this.balance = 1000;
    }

    @Override
    public void save(){
        super.save(this);
    }

    public void buy(Asset asset, int amount){
        if(this.balance > (asset.getCurrentPrice() * amount)){
            for(int i=0; i<amount; i++){
                this.myAssets.add(asset);
                this.balance -= asset.getCurrentPrice();
            }
            System.out.println("You bought " + amount + " assets of " + asset.getCompany());
        }else{
            System.out.println("You do not have enough money");
        }
        System.out.println("*".repeat(50));
    }

    public void sell(Asset asset, int amount){
        int size = this.myAssets.size();
        for(int i=0; i<amount; i++){
            this.myAssets.remove(asset);
            this.balance += asset.getCurrentPrice();
        }
        if(size > this.myAssets.size()){
            System.out.println("You sold " + amount + " assets of " + asset.getCompany());
        }else{
            System.out.println("You do not have ay asset of " + asset.getCompany());
        }
        System.out.println("*".repeat(50));
    }

    @Override
    public void print(){
        double total = 0;
        for (Asset asset: this.myAssets){
            total += asset.getCurrentPrice();
        }
        System.out.println("*".repeat(50));
        super.print();
        System.out.println("Balance: " + this.balance);
        System.out.println("Total assets: " + this.myAssets.size() + " (Value: " + total + ")");
        
        HashMap<String, Integer> assetsNumber = new HashMap<>();
        for (Asset s: this.myAssets){
            String ticker = s.getTicker();
            int count = (int) this.myAssets.stream().filter(asset -> asset.getTicker().equals(ticker)).count();
            assetsNumber.put(ticker, count);
        }

        for (Map.Entry<String, Integer> map: assetsNumber.entrySet()){
            System.out.println(map.getKey() + ": \nTotal assets: " + map.getValue() + "\nTotal value: ");
        }

        System.out.println("*".repeat(50));
    }

}
