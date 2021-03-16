
class Stock extends Asset
{
    private String companyOwner;

    public Stock(String company, String ticker, double currentPrice, String companyOwner)
    {
        super(company, ticker, currentPrice);
        this.companyOwner = companyOwner;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Company owner: " + this.companyOwner);
        System.out.println("*".repeat(100));
    }

    @Override
    public void variatePrice(double variability, boolean isPrint) {
        super.variatePrice(2, isPrint);
    }
}