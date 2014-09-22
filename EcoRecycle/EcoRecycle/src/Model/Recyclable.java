package Model;


import java.util.Date;

public  abstract class Recyclable {
    
    public enum Types {
        
    Glass("Glass"),Aluminium("Aluminium"),Tin("Tin");
   
    private Types(final String text) {
        this.text = text;
    }

    private final String text;

    
    public static final String[]  types=new String[Types.values().length];
    public static String[] getTypes(){
        return types;
    }
    static{
        for(int i=0; i<Types.values().length; i++)
            types[i]=Types.values()[i].text;
    }
    @Override
    public String toString() {
        return text;
    }
    }
    
   

    
    
     
}
