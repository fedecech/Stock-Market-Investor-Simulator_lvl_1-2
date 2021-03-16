import java.util.ArrayList;
import java.util.Scanner;

public class Menu implements Printable{

    private Account account;
    private Market market;
    private ArrayList<String> choices;

    public Menu(Account account, Market market){
        this.account = account;
        this.market = market;
        this.choices = initChoices();
    }

    public int choose(){
        System.out.print("Select choice: ");
        String choice = new Scanner(System.in).nextLine();
        return validateChoice(choice);
    }

    public void print(){
        System.out.println("-".repeat(50));
        for (int i=0; i<this.choices.size(); i++){
            System.out.println(i + ") " + choices.get(i));
        }
        System.out.println("-".repeat(50));
    }

    public void perform(int choice){
        String c = this.choices.get(choice);
        if(c.contains("print")){
           performPrint(c);
        }else if(c.contains("buy") || c.contains("sell")){
            if (c.contains("buy"))
                ((User)account).buy(selectExistingAsset(), selectAmount());
            else if (c.contains("sell"))
                ((User)account).sell(selectExistingAsset(), selectAmount());
        }else if(c.contains("delete")){
            if(c.contains("asset"))
                ((Admin)account).deleteAsset(this.market, selectExistingAsset());
            else if (c.contains("account"))
                ((Admin)account).deleteAccount(selectUsername());

        }else if(c.contains("add")){
            if(c.contains("account"))
                ((Admin)account).addAccount(selectAccount());
            else if (c.contains("asset"))
                ((Admin)account).addAsset(market, createAsset());
        }else if(c.contains("exit")){
            market.save();
            account.save();
            System.exit(0);
        }
    }

    private ArrayList<String> initChoices(){
        ArrayList<String> c = new ArrayList<>();
        c.add("print your profile information");
        c.add("print information of a asset");
        c.add("print information of the asset market");
        if (account instanceof User){
            c.add("buy a asset");
            c.add("sell a asset");
        }else {
            c.add("delete an account");
            c.add("add an account");
            c.add("delete a asset from the market");
            c.add("add a asset to the market");
        }
        c.add("exit and save");
        return c;
    }

    private void performPrint(String subject){
        if(subject.contains("profile")){
            account.print();
        }else if(subject.contains("asset")){
            if(subject.contains("market")){
                market.print();
            }else{
                Asset asset = selectExistingAsset();
                asset.print();
            }
        }
    }
    private Asset createAsset(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the company: ");
        String company = scanner.nextLine();
        System.out.print("Enter the ticker: ");
        String ticker = scanner.nextLine();
        System.out.print("Enter the starting price: ");
        int price = validateChoice(scanner.nextLine());
        return new Asset(company, ticker, price);
    }
    private int validateChoice(String string){
        while (!isValidChoice(string)){
            string = new Scanner(System.in).nextLine();
        }
        return Integer.parseInt(string);
    }

    private boolean isValidChoice(String string){
        try{
            Integer.parseInt(string);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    private Asset selectExistingAsset(){
        System.out.print("Enter the ticker of the asset: ");
        String ticker = new Scanner(System.in).nextLine();
        while (this.market.getAssetByTicker(ticker) == null){
            ticker = new Scanner(System.in).nextLine();
        }
        return this.market.getAssetByTicker(ticker);
    }

    private String selectUsername(){
        System.out.print("Enter the username: ");
        return new Scanner(System.in).nextLine();
    }
    public static String selectPassword(){
        System.out.print("Enter the password: ");
        return new Scanner(System.in).nextLine();
    }

    private boolean isAdmin(){
        System.out.print("Do you want to create an admin account? (y/n) ");
        return new Scanner(System.in).nextLine() == "y";
    }

    private int selectAmount(){
        System.out.print("Enter the amount: ");
        return validateChoice(new Scanner(System.in).nextLine());
    }

    private Account selectAccount(){
        return isAdmin() ? new User(selectUsername(), selectPassword()) : new Admin(selectUsername(), selectPassword());
    }

    public ArrayList<String> getChoices() {
        return choices;
    }
}
