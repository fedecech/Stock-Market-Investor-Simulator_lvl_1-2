public class Apple extends Stock
{
    public Apple()
    {
        super("Apple", "AAPL", 120.0);
    }
    
    public void variatePrice(){
         super.variatePrice(100);
    }
}
