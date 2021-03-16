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

    public void print(){
        System.out.println(this.name.toUpperCase());
    }


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

    public void variate(boolean isPrint){
        for(int i=0; i<assets.size(); i++){
            assets.get(i).variatePrice(Math.random() * 5, isPrint);
        }
    }

    public Asset getAssetByTicker(String key){
        List<Asset> stream = this.assets.stream().filter(s -> s.getTicker().equals(key)).collect(Collectors.toList());

        if (stream.isEmpty()){
            System.out.println("Asset does not exists");
            return null;
        }

        return stream.get(0);
    }
    public void addAsset(Asset asset){
        this.assets.add(asset);
    }

    public void removeAsset(Asset asset){
        this.assets.remove(asset);
    }
}
