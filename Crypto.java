
public class Crypto extends Asset {

    // how the crypto is rated
    private double CVI;

    public Crypto(String company, String ticker, double currentPrice, double CVI)
    {
        super(company, ticker, currentPrice);
        this.CVI = CVI;
    }

    // print Asset info + crypto info
    @Override
    public void print() {
        super.print();
        System.out.println("CVI: " + this.CVI);
        System.out.println("*".repeat(100));
    }

    // variates crypto price
    // crypto is more volatile than stocks (+10)
    @Override
    public void variatePrice(double variability, boolean isPrint) {
        super.variatePrice(variability + 10, isPrint);
    }
}
