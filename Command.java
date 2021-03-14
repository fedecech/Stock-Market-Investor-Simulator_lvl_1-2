import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command {
    private String subject;
    private String verb;
    private ArrayList<String> options;

    public Command(String command){
        this.options = new ArrayList<>();
        verify(command);
        process(command);
    }

    public String getSubject() {
        return subject;
    }

    public String getVerb() {
        return verb;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void verify(String command){
        if(command.isEmpty() || command.split(" ").length < 2){
            if(!command.equals("exit") && !command.equals("help")){
                System.out.println("Invalid command");
            }
        }
    }

    public void process(String command){
        List<String> split = Arrays.asList(command.split(" "));
        this.subject = split.get(0);
        if(subject.equals(Constants.HELP.getFull()) || subject.equals(Constants.HELP.getAbbr())){
            System.out.print(Constants.HELP.getDesc());
            return;
        }
        this.verb = split.get(1).replace("-", "");
        if(split.size() > 2){
            for(int i=2; i< split.size(); i++){
                this.options.add(split.get(i).replace("-", ""));
            }
        }
    }

    public static void exit(Account account){
        account.save("account.txt");
        System.exit(0);
    }
}
