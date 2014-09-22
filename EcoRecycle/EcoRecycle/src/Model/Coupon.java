package Model;


public class Coupon implements Payment {
   
    String store="Walgreens";
    double amount;
    
    public Coupon(double amount){
        this.amount=amount;
        
    }
    
    @Override
    public double getAmount() {
        
        return amount;
        
    }
    public String getStore(){
        return store;
    }
    public void setStore(String store){
       this.store=store;
    }
    @Override
     public String toString(){
        return "This coupon is worth of $"+amount+".Redeemable at "+store+".";
         
     }
    
    public static void main(String args[]){
        
        
    }
    
}
