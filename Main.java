import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args){
        final String EXIT = "exit";
        // list of stock that can be bought
        ArrayList<Stock> stockAvaiable = new ArrayList<>();
        stockAvaiable.add(new Apple());
        stockAvaiable.add(new Tesla());
        
        Account user = start();
        printOptions();
        print("Enter command: ");
        String command = new Scanner(System.in).nextLine();
        while(!command.contains(EXIT)){
            // process command
            String[] cmd = processCommand(command);

            if(cmd == null){
                print("Command not valid\n");
            }else{
                String subject = cmd[0];
                String verb = cmd[1];
                boolean isExtended = false;
                if(cmd.length == 3 && cmd[2] != null){
                    isExtended = cmd[2].equals("e");
                }
                if (subject.equals("account")){
                    if(verb.equals("p")){
                        ((User)user).print(isExtended);
                    }else{
                        print("Invalid verb\n");
                    }
                }else{
                    for(int i=0; i<stockAvaiable.size(); i++){
                        if(subject.equals(stockAvaiable.get(i).getTicker())){
                            if(verb.equals("p")){
                                stockAvaiable.get(i).print(isExtended);
                            }else if (verb.equals("b")){
                                print("Enter ammount: ");
                                int amount = new Scanner(System.in).nextInt();
                                ((User)user).buyStock(stockAvaiable.get(i), amount);
                            }else if (verb.equals("s")){
                                print("Enter ammount: ");
                                int amount = new Scanner(System.in).nextInt();
                                ((User)user).sellStock(stockAvaiable.get(i), amount);
                            }else{
                                print("Invalid verb\n");
                            }
                        }
                    }
                }
            }
            
            for(int i=0; i<stockAvaiable.size(); i++){
                stockAvaiable.get(i).variatePrice(Math.random() * 5);
            }
            //print(Arrays.toString(cmd));
            print("Enter command: ");
            command = new Scanner(System.in).nextLine();
        }
    }
    
    public static Account start(){
        print("WELCOME TO THE STOCK MARKET INVESTOR SIMULATOR\n");
        print("Enter your username: ");
        String username = new Scanner(System.in).nextLine();
        return new User(username);
    }
    
    public static void print(String msg){
        System.out.print(msg);
    }
    
    public static void printOptions(){
        print("(Subject -verb -options)");
    }
    
    public static String[] processCommand(String command){
        String [] cmd = command.split(" ");
        if(cmd.length < 2 || cmd.length > 3 || cmd[1].charAt(0) != '-'){
            return null;
        }
        
        if(cmd.length == 3){
            if(cmd[2].charAt(0) != '-'){
                return null;
            }
        }
        return new String[]{cmd[0], cmd[1].substring(1), cmd.length == 3 ? cmd[2].substring(1) : null};
    }
}
