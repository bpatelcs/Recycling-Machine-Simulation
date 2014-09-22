package Model;


public class RCMException extends Exception {
    public RCMException(String message){
        super(message);
    }
    public String toString(){
        return super.getMessage();
        
    }
    
}
