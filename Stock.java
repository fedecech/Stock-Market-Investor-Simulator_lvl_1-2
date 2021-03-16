
class Stock extends Asset
{
    private String companyOwner;

    public Stock(String company, String ticker, double currentPrice, String companyOwner)
    {
        super(company, ticker, currentPrice);
        this.companyOwner = companyOwner;
    }

    // print stock info
    @Override
    public void print() {
        super.print();
        System.out.println("Company owner: " + this.companyOwner);
        System.out.println("*".repeat(100));
    }

    // variate price
    // less volatility then crypto (+2)
    @Override
    public void variatePrice(double variability, boolean isPrint) {
        super.variatePrice(variability + 2, isPrint);
    }
}