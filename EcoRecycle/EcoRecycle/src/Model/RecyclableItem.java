package Model;


import java.util.Date;


public class RecyclableItem extends Recyclable {
    
    private Date recyclingDate;
    public  Types type;
    public double price;
    public double weight;
        public RecyclableItem(Recyclable.Types type,double weight){
        this.type=type;
        this.weight=weight;
        
    }
        public static RecyclableItem getObject(Recyclable.Types type,double price){
         
            RecyclableItem item=new RecyclableItem();
            item.type=type;
            item.price=price;
            return item;
}

    private RecyclableItem() {
        
    }

    
    public final Types  getType(){                
        return type;
        
    }
        
    public double getWeight() {
    return weight;
    }
        
    public Date getRecyclingDate() {
     return recyclingDate;
    }  
    
    public void setRecyclingDate(Date d) {
    recyclingDate=d;
    }
    
    public double getItemPrice(){
        return price;
    }
    public String toString(){
        return type+" "+price+"/pound";
    }
    public static String[] getTypes(){
        return Types.getTypes();
    }
    
    
    
    
    

   
    
}
