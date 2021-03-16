import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Market implements Serializable, Savable, Printable{

    private String name;
    private ArrayList<Asset> assets;

    public Market(String name, ArrayList<Asset> assets){
        this.name = name;
        this.assets = assets;
    }

    // prints all the assets in market
    public void print(){
        System.out.println("<>".repeat(50));
        System.out.println(this.name.toUpperCase());
        for(Asset asset: this.assets){
            asset.print();
        }
        System.out.println("<>".repeat(50));
    }


    // save market to file
    public void save(){
        try{
            FileOutputStream file = new FileOutputStream(this.name + Constants.FILE_MARK_EXTENTION.getFull());
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(this);

            out.close();
            file.close();

        }catch(IOException e){
            System.out.println("IOException is caught");
        }
    }

    // retrieve market from file
    public static Market retrieve(String fileName){
        Market market = null;
        try{
            FileInputStream file = new FileInputStream(fileName + Constants.FILE_MARK_EXTENTION.getFull());
            ObjectInputStream in = new ObjectInputStream(file);

            market = (Market) in.readObject();

            in.close();
            file.close();
        }catch (IOException | ClassNotFoundException e){

        }
        return market;
    }

    // class variate in all the assets in the market
    public void variate(boolean isPrint){
        for(int i=0; i<assets.size(); i++){
            assets.get(i).variatePrice(Math.random() * 5, isPrint);
        }
    }

    // returns asset based on ticker (or null == NOT FOUND)
    public Asset getAssetByTicker(String key){
        List<Asset> stream = this.assets.stream().filter(s -> s.getTicker().equals(key)).collect(Collectors.toList());

        if (stream.isEmpty()){
            System.out.println("Asset does not exists");
            return null;
        }

        return stream.get(0);
    }

    // add asset to market
    public void addAsset(Asset asset){
        this.assets.add(asset);
    }

    // remove asset to market
    public void removeAsset(Asset asset){
        this.assets.remove(asset);
    }
}
