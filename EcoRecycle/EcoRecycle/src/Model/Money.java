package Model;

public class Money implements Payment {

    /**
     * Represents USD to EURO conversion rate
     */
    public static final double USD_TO_EURO_RATE = 0.7;
    /**
     * This variable stores the type of currency like dollar, pound etc.
     */
    private String currencyUnits = "dollars";
    /**
     * This variable holds the amount.
     */
    private double amount;

    /**
     * Default constructor initializes amount to zero.
     */
    public Money() {
        amount = 0.0;
    }

    /**
     * This constructor takes amount as a argument and set that to an amount.
     */
    public Money(double amount) {
        this.amount = amount;
    }

    /**
     * This method returns the value of amount variable
     */
    @Override
    public double getAmount() {
        return amount;
    }

    /**
     * This method set the value of amount variable.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * This method return the amount in EURO.
     */
    public double getAmountInEuro() {
        return amount * USD_TO_EURO_RATE;
    }
    
    @Override
    public String toString(){
        return ""+getAmount()+" "+currencyUnits;
    } 
    
    public static void main(String[] args){
    Money m=new Money(12);
        System.out.println(m);
        
        
    }
}
