import java.util.*;

abstract public class Account
{
    private String username;
    
    public Account(String username)
    {
        this.username = username;
    }
    
    public String getUsername(){
        return this.username;
    }
}