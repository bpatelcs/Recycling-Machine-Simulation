package View;


import Controller.RCM;
import Model.RecyclableItem;
import Model.Recyclable;
import Model.Coupon;
import Model.Location;
import Model.Payment;
import Model.RCMException;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.RecyclableItem;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.*;
import controller.*;

public class RCMGUI extends RCM implements ActionListener {

    JFrame frame;
    JButton startSession;
    JButton endSession;
    JButton showItem;
    JButton processPayment;
    Display display;
    Receptacle receptacle;
    private final JComboBox paymentType;
    private final JLabel paymentLabel;

    public RCMGUI(int id, Location loc, ArrayList<RecyclableItem> acceptableItems, Status status, int capacity, double amount) {
        super(id, loc, acceptableItems, status, capacity, amount);
        frame = new JFrame("EcoRe Recycling Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showItem = new JButton();

        startSession = new JButton();
        JScrollPane jScrollPane1 = new JScrollPane();
        display = new Display();
        endSession = new JButton();
        processPayment = new JButton();
        receptacle = new Receptacle();
        paymentType = new JComboBox();
        paymentLabel = new JLabel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        showItem.setText("Show Acceptable Items");
        showItem.setActionCommand("SAI");
        startSession.setText("Start Session");
        startSession.setActionCommand("SS");
        display.setColumns(15);
        display.setRows(5);

        display.setEditable(false);
        jScrollPane1.setViewportView(display);
        endSession.setText("End Session");
        endSession.setActionCommand("ES");
        processPayment.setText("Payment");
        processPayment.setActionCommand("PP");
        receptacle.setText("Drop Items Here");
        paymentType.setModel(new DefaultComboBoxModel(new String[]{"Cash", "Coupon"}));
        paymentLabel.setFont(new Font("Monospaced", 1, 11));
        paymentLabel.setText("Payment Type");

        showItem.addActionListener(this);
        startSession.addActionListener(this);
        endSession.addActionListener(this);
        processPayment.addActionListener(this);
        paymentType.addActionListener(this);
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(receptacle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(endSession, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(processPayment, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(startSession, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(showItem, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(paymentLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paymentType, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addComponent(showItem, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startSession, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endSession, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(paymentLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addComponent(paymentType, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(processPayment, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
                .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addComponent(receptacle, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)));

        frame.setSize(400, 300);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SAI")) {

            String s = getAcceptableItems();
            display.show(s);
        } else if (e.getActionCommand().equals("SS")) {
            if (getStatus().equals(Status.IDLE)) {
                display.show("System Is Not Active");
                return;
            }
            startSession();
        } else if (e.getActionCommand().equals("ES")) {
             //endSession();
            //display.show(getSessionString());
            display.show(getEndSessionString());
        } else if (e.getActionCommand().equals("PP")) {
            try {
                if (!isSession) {
                    display.show("No Items Received");
                    return;
                }
                String s = (String) paymentType.getSelectedItem();
                ArrayList<RecyclableItem> garbage = getSessionGarbage();
                String url = "jdbc:mysql://localhost:3306/ecore";
                String driver = "com.mysql.jdbc.Driver";
                String userName = "root";
                String password = "password";
                Class.forName(driver).newInstance();
                Connection conn = DriverManager.getConnection(url, userName, password);
                System.out.println("here");
                String sql = "INSERT INTO TRANSACTION(RCMID,ITEMNAME,PAYMENTTYPE,WEIGHT) VALUES(?,?,?,?)";
                PreparedStatement prepStmt = conn.prepareStatement(sql);               
                for (RecyclableItem r : garbage) {
                    
                  prepStmt.setString(1,getId()+"");
                  prepStmt.setString(2, r.getType().toString());
                  prepStmt.setString(3, s);
                  prepStmt.setString(4, r.weight+"");
                  int rs = prepStmt.executeUpdate();
                    
                }                
                conn.close();
                endSession();
                System.out.println(garbage);
                Payment cash=getPayment();
                if (s.equals("Cash")&&cash!=null) {
                    try {
                        FileWriter f = new FileWriter(new File("C:\\Users\\ispatel\\Desktop\\cash.txt"));
                                            
                        String cashString = (cash).toString();
                        f.write(cashString);
                        f.close();
                    } catch (IOException ex) {
                        Logger.getLogger(RCMGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    FileWriter f;
                    try {
                        f = new FileWriter(new File("C:\\Users\\ispatel\\Desktop\\coupon.txt"));
                        String cashString = (new Coupon(getTotalAmount())).toString();
                        f.write(cashString);
                        f.close();
                    } catch (IOException ex) {
                        Logger.getLogger(RCMGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                 //display.show(getSessionString() + "\nPayment Processed");
                display.show(getEndSessionString() + "\nPayment Processed");
            } catch (InstantiationException ex) {
                Logger.getLogger(RCMGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(RCMGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RCMGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(RCMGUI.class.getName()).log(Level.SEVERE, null, ex);
            }


        }

    }

    private class Receptacle extends JButton implements ActionListener {

        JButton drop;
        JFileChooser fileChoose;
        RecyclableItem object;

        public Receptacle() {
            super("Drop Items Here");

            this.addActionListener(this);
        }

        public Recyclable.Types getType(String type) {
            for (Recyclable.Types t : Recyclable.Types.values()) {
                if (t.toString().equalsIgnoreCase(type)) {
                    return t;
                }
            }
            return null;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            fileChoose = new JFileChooser("C:\\Users\\ispatel\\Desktop");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT files only", "txt");
            fileChoose.setFileFilter(filter);
            int returnVal = fileChoose.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = fileChoose.getSelectedFile();
                    Scanner s = new Scanner(file);
                    String type = s.nextLine();
                    if (!isTypeValid(type)) {
                        display.show("Not Acceptable Item");
                        return;
                    }

                    double weight = Integer.parseInt(s.nextLine());

                    object = new RecyclableItem(getType(type), weight);
                    if (!isSession) {
                        if (getStatus().equals(Status.IDLE)) {
                            display.show("System Is Not Active");
                            return;
                        }
                        startSession();
                        addItemToSession(object);
                        //endSession();
                        display.show(getSessionString());
                    } else {
                        addItemToSession(object);
                        display.show(getSessionString());
                    }



                } catch (FileNotFoundException ex) {
                    System.out.println("Item Not Found");
                } catch (RCMException ex) {
                    System.out.println("Trash is Full, can not accept further garbage.");
                    display.show("Trash is Full, can not accept further garbage.");
                } 

            }
        }

        public RecyclableItem getItem() {
            return object;
        }
    }

    private class Display extends JTextArea {

        public Display() {
            super();
            setWrapStyleWord(true);
            setAlignmentY(RIGHT_ALIGNMENT);
            setFont(new Font("Monospaced", 1, 12));
            setLineWrap(true);

            show("");
        }

        public void show(String message) {

            setText(" EcoRe Recycling Machine\n-------------------------\n" + message);

        }
    }

    public void dispose() {
        frame.dispose();
    }

    public static void main(String[] args) {
        ArrayList<RecyclableItem> a = new ArrayList<>();
        a.add(RecyclableItem.getObject(Recyclable.Types.Glass, 3));
        a.add(RecyclableItem.getObject(Recyclable.Types.Aluminium, 4));

        RCMGUI r = new RCMGUI(1, null, a, Status.ACTIVE, 13, 12);





    }
}
