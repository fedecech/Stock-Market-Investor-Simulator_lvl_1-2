public class Tesla extends Stock
{
    public Tesla()
    {
        super("Tesla", "TSLA", 670.0);
    }
    
    public void variatePrice(){
         super.variatePrice(150);
    }
}
