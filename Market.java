import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class Market {

    private ArrayList<Stock> stocksAvailable;
    private int counter;

    public Market(ArrayList<Stock> stocksAvailable){
        this.stocksAvailable = stocksAvailable;
        this.counter = 0;
    }

    public void open(){
        TimerTask timerTask= new TimerTask() {
            @Override
            public void run() {
                for(int i=0; i<stocksAvailable.size(); i++){
                    stocksAvailable.get(i).variatePrice(Math.random() * 5);
                }
                counter++;
            }
        };
        (new Timer()).scheduleAtFixedRate(timerTask, 30, ((int) ((Math.random() * 30000) + 10000)));
    }

    public Stock getStockByTicker(String key){
        List<Stock> stream = this.stocksAvailable.stream().filter(s -> s.getTicker().equals(key)).collect(Collectors.toList());

        if (stream.isEmpty()){
            System.out.println("Stock does not exists");
            return null;
        }

        return stream.get(0);
    }

}
