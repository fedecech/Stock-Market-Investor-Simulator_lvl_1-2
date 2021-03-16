
public class Crypto extends Asset {

    private int score;

    public Crypto(String company, String ticker, double currentPrice, int score)
    {
        super(company, ticker, currentPrice);
        this.score = score;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Score: ");        System.out.println("*".repeat(100));

    }

    @Override
    public void variatePrice(double variability, boolean isPrint) {
        super.variatePrice(10, isPrint);
    }
}
