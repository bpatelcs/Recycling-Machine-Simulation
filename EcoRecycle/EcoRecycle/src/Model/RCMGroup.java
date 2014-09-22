package Model;


import java.util.ArrayList;
import Controller.RCM;

public class RCMGroup {
    private String name;
    private ArrayList<RCM> group;
    private int totalRCM;
    
    
    
    public int totalSize(){
        return group.size();
    }
    public RCMGroup(String name){
        group=new ArrayList<>();
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public RCM getRCM(int id){
        
        for(RCM r:group){
            if(r.getId()==id){
                return r;
            }
        }                        
        return null;
        
    }
    
    public void addRCM(RCM r){
        group.add(r);
    }
    public void removeRCM(int id){
        for(RCM r:group){
            if(r.getId()==id){
                group.remove(r);
                return;
            }
        }
        
        
    }
}
