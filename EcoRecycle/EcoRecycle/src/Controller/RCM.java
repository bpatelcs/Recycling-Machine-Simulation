package Controller;


import Model.Payment;
import Model.Location;
import Model.Money;
import Model.RecyclableItem;
import Model.Recyclable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import Model.RCMException;
import View.*;
import Model.*;
public class RCM {

    private int id;
    public Trash trash;
    private Location loc;
    private ArrayList<RecyclableItem> acceptableItems;
    private Payment payment;
    protected Session session;
    protected Payment paymentPref;
    private Status status;
    private double amount;
    protected boolean isSession = false;

    public RCM(int id, Location loc, ArrayList<RecyclableItem> acceptableItems, Status status, int capacity, double amount) {
        this.id = id;
        this.acceptableItems = acceptableItems;
        this.status = status;
        this.amount = amount;
        session = new Session();
        trash = new Trash(capacity);
        this.loc = loc;

    }

    public double getAmount() {
        return amount;
    }
    
    public synchronized void setLocation(Location loc){
        this.loc=loc;
    }
    public synchronized void setAmount(double amount){
        this.amount=amount;
    }

    public ArrayList<RecyclableItem> getAcceptableItemList() {
        return acceptableItems;
    }

    public synchronized void setAcceptableItemList(ArrayList<RecyclableItem> acceptableItems) {
        this.acceptableItems = acceptableItems;
    }

    public boolean isTypeValid(String type) {
        for (int i = 0; i < acceptableItems.size(); i++) {
            if (acceptableItems.get(i).getType().toString().equalsIgnoreCase(type)) {
                return true;
            }

        }
        return false;
    }

    public int getId() {
        return id;
    }

    public String getAcceptableItems() {
        String itemList = "Acceptable Items   Price\n";
        for (int i = 0; i < acceptableItems.size(); i++) {
            String type = acceptableItems.get(i).getType().toString();
            int length = 1 + type.length();
            String format = "%s %" + (25 - length) + "s %s";
            itemList += String.format(format, acceptableItems.get(i).getType(), acceptableItems.get(i).getItemPrice(), "\n");
        }

        return itemList;
    }
public ArrayList<RecyclableItem> getSessionGarbage(){
    return session.getSessiongarbage();
}
    public double getTotalAmount() {
        
        return session.getTotalAmount();
    }

    public void startSession() {
        if (isSession || status.equals(Status.IDLE)) {
            return;
        }
        isSession = true;
        session.start();
    }

    public void endSession() {
        if (!isSession) {
            return;
        }

        session.end();
        isSession = false;
    }
public String getEndSessionString(){
     return getSessionString() + "Total Payment=" + getTotalAmount();
 }
    public Payment getPayment() {
        if(session.getTotalAmount()>amount){
            return null;
        }
        payment = new Money(session.getTotalAmount());
        amount-=session.getTotalAmount();
        return payment;
    }

    public void addNewAcceptableItem(RecyclableItem item) {
        acceptableItems.add(item);
    }

    public Status getStatus() {
        return status;
    }

    public Location getLocation() {
        return loc;
    }

    public synchronized void setStatus(Status status) {
        this.status = status;
    }

    public void addItemToSession(RecyclableItem item) throws RCMException {
        session.addItemToSession(item);
    }

    public String getSessionString() {
        return session.getSessionString();
    }

    public static enum Status {

        ACTIVE, IDLE
    };

    public double getPriceFor(Recyclable.Types type) {
        double price = 0;
        for (int i = 0; i < acceptableItems.size(); i++) {
            if (acceptableItems.get(i).getType().equals(type)) {
                price = acceptableItems.get(i).getItemPrice();
            }
        }

        return price;
    }

    private class Session {

        String s;
        ArrayList<RecyclableItem> sessionGarbage;
        String itemList;
        double totalWeight;
        double totalAmount;

        public ArrayList<RecyclableItem> getSessiongarbage() {
            return sessionGarbage;
        }

        public double getTotalWeight() {
            return totalWeight;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void start() {
            itemList = "";
            totalWeight = 0;
            totalAmount = 0;
            s = "Items           Weight\n";
            sessionGarbage = new ArrayList<>();
        }

        public void addItemToSession(RecyclableItem item) throws RCMException {
            if ((item.getWeight() + trash.getTotalWeight() + totalWeight) <= trash.getCapacity()) {
                sessionGarbage.add(item);
                //trash.collectGarbage(item);
                totalWeight += item.getWeight();
                totalAmount += item.getWeight() * getPriceFor(item.getType());

                String format = "%s %" + (25 - (item.getType().toString().length() + 4)) + "s %s";
                s += String.format(format, item.getType(), item.getWeight(), "\n");


            } else {
                throw new RCMException("Trash is Full, can not accept further garbage");
            }
        }

        public void end() {
            trash.garbageSession(this);

        }
  
        
        public String getSessionString() {
//            if (isSession) {
                return s;
//            } else {
//                return s + "Total Payment=" + totalAmount;
//            }
        }
    }

    public class Trash {

        private ArrayList<RecyclableItem> garbage;
        private int totalWeight;
        private int capacity;
        HashMap<String, Double> collectionByType;

        public Trash(int capacity) {
            this.capacity = capacity;
            totalWeight = 0;
            garbage = new ArrayList<>();
            collectionByType = new HashMap<>();
        }

        public void emptyTrash() {
            totalWeight = 0;
            garbage = new ArrayList<>();
            collectionByType = new HashMap<>(); // we can store this data in database
        }

        public void collectGarbage(RecyclableItem garbage) throws RCMException {
            if ((totalWeight + garbage.getWeight()) <= capacity) {
                if (collectionByType.get(garbage.getType()) == null) {
                    collectionByType.put(garbage.getType().toString(), garbage.getWeight());
                } else {
                    collectionByType.put(garbage.getType().toString(), collectionByType.get(garbage.getType()) + garbage.getWeight());
                }
                this.garbage.add(garbage);
                totalWeight += garbage.getWeight();
            } else {

                throw new RCMException("Trash is full, can not accept further garbage.");
            }

        }

        public void garbageSession(Session s) {
            totalWeight += s.getTotalWeight();
            ArrayList<RecyclableItem> temp = s.getSessiongarbage();

            while (!temp.isEmpty()) {
                RecyclableItem garbage = temp.remove(0);
                if (collectionByType.get(garbage.getType()) == null) {
                    collectionByType.put(garbage.getType().toString(), garbage.getWeight());
                } else {
                    collectionByType.put(garbage.getType().toString(), collectionByType.get(garbage.getType()) + garbage.getWeight());
                }
                this.garbage.add(garbage);
            }
        }

        public Double getCollectionByType(String type) {
            return collectionByType.get(type);
        }

        public int getTotalWeight() {
            return totalWeight;
        }

        public void setTotalWeight(int totalWeight) {
            this.totalWeight = totalWeight;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }
    }
}
