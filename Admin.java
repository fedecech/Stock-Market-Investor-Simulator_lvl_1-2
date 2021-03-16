import java.io.*;
import java.util.*;

/***************************************************************************************************
    Admin is a type of Account has more rights of User and can modify and manage:
        - other accounts
        - market instances
    Admin has 1 propriety called KEY:
        - when the object is initialised the key is generated as a 20 characters
          long alpha numeric random final string
        - key can be used by other users to create an admin account (when signing up
          with the admin prefix [.admin] it asks the admin key)
    Admin has several methods:
        - save that uses the already defined method in the super class Account by passing
          "this" object
        - print that overrides the the super class method by calling it and printing also the
          key
        - create/delete account that create or deletes an account
        - add asset to add a new asset into the market
        - reset market that deletes the current market and create a new instance of it
 ***************************************************************************************************/
final public class Admin extends Account
{
    private final String KEY;
    
    public Admin(String username, String password){
        super(username, password);
        this.KEY = generateRandomKey(20);
    }

    // save  admin to file
    @Override
    public void save(){
        super.save(this);
    }

    // prints admin info
    @Override
    public void print(){
        System.out.println("*".repeat(50));
        super.print();
        System.out.println("Key: " + this.KEY);
        System.out.println("*".repeat(50));
    }
    
    // delete account with username passed as argument
    public void deleteAccount(String username){
        File file = new File(username + Constants.FILE_ACC_EXTENTION.getFull());
        if (file.delete()) {
          System.out.println("Account deleted: " + username);
        } else {
          System.out.println("Failed to delete the account: "  + username);
        } 
    }

    // saves account passed to file
    public void addAccount(Account account){
        account.save();
    }

    // add asset to market
    public void addAsset(Market market, Asset asset){
        market.addAsset(asset);
        System.out.println("You added the asset " + asset.getTicker() + " to the market");
    }

    // delete asset passed from market
    public void deleteAsset(Market market, Asset asset){
        market.removeAsset(asset);
        System.out.println("You removed the asset " + asset.getTicker() + " from the market");
    }

    // creates random key when class is initialised
    private String generateRandomKey(int length){
        Random r = new Random ();
        return Long.toString (r.nextLong () & Long.MAX_VALUE, length);
    }
    
    public String getKey(){
        return this.KEY;
    }
}
