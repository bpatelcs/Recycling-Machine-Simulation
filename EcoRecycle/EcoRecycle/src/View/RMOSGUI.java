package View;


import Controller.LogIn;
import Controller.RCM;
import Model.Location;
import Model.Graph;
import Model.Status;
import Model.RCMGroup;
import Model.RecyclableItem;
import Model.Recyclable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.CubicCurve2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Model.*;
import View.*;
public class RMOSGUI {

    private ArrayList<RCMGroup> groups;
    private javax.swing.JPanel canvas3;
    private javax.swing.JButton addItemInAddRCMTab, removeItemInAddRCMTab, addRCM, addItemInMaintainRCMTab, removeRCM, removeItemsInMaintainTab, updateRCM;
    private javax.swing.JComboBox groupInMaintainRCMTab, groupInMaintainTab, selectedGroupInMatainRCMTab, IdInMaintainRCMTab, selectAIInAddRCMTab, groupInAddRCMTab, groupInHomeTab, RCMInHomeTab, criteriaInHomeTab, groupInrRemoveRCMTab, selectAIInMaintainRCMTab, RCMInRemoveRCMTab;
    private javax.swing.JLabel idAddRCMTab, jLabel10, jLabel11, jLabel12, groupHome, jLabel14, criteria, groupRemoveRCM, IDMaintainRCM, jLabel18, AddMaintainRCM, addAddRCMTab, CityMaintainRCM, PINMaintainRCM, StateMaintainRCM, selectAIMaintainRCM, priceMaintainRCM, addedAIMaintainRCM, changeGroupMaintainRCM, statusMaintainRCM, amountMaintainRCM, groupMaintainRCM, cityAddRCMTab, pinAddRCMTab, stateAddRCMTab, selectAIAddRCMTab, priceAIAddRCMTab, jLabel8, jLabel9;
    private javax.swing.JPanel homeTab, addRCMTab, removeRCMTab, maintainRCMTab, logOutTab, jPanel6, jPanel7, jPanel8;
    private javax.swing.JRadioButton activeInAddRCMTab, disableInAddRCMTab, activeMaintainRCM, disableMaintainRCM;
    private javax.swing.JScrollBar jScrollBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane Home;
    //private javax.swing.JList addedItemsInAddRCMTab;
    private javax.swing.JList addedItemsInAddRCMTab, addedItemsInMaintainRCMTab;
    private javax.swing.JTextField priceInAddRCMTab, addInMaintainRCMTab, cityInMaintainRCMTab, pinInMaintainRCMTab, stateInMaintainRCMTab, amountInMaintainRCMTab, idInAddRCMTab, addressInAddRCMTab, cityInAddRCMTab, pinInAddRCMTab, stateInAddRCMTab, amountInAddRCMTab, priceInMaintainRCMTab;
    JFrame frame;
    private Graphics g;
    private ArrayList<RecyclableItem> addedItemsInAddRCM;
    private ArrayList<RecyclableItem> addedItemsInMaintainRCM;
    private String addedItemsInTextAreaAddRCM = "";
    private String addedItemsInTextAreaMaintainRCM = "";
    private boolean status;
    private ArrayList<String> groupNames;
    private ArrayList<String> RCMNames;
    private ButtonGroup radioButtonInAddRCMTab;
    private ButtonGroup radioButtonInMaintainRCMTab;
    private RCMGroup RCMGroupInMaintainRCMTab;
    private RCM RCMInMaintainRCMTab;

    public RMOSGUI() {
        radioButtonInAddRCMTab = new ButtonGroup();
        radioButtonInMaintainRCMTab = new ButtonGroup();
        groupNames = new ArrayList<>();
        groupNames.add("Sunnyvale");
        groupNames.add("Santa Clara");
        RCMNames = new ArrayList<>();
        frame = new JFrame("EcoRe Monitoring Machine");
        addedItemsInAddRCM = new ArrayList<>();
        groups = new ArrayList<>();
        groups.add(new RCMGroup("Sunnyvale"));
        groups.add(new RCMGroup("Santa Clara"));
        // frame.setSize(680, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        groupInMaintainRCMTab = new javax.swing.JComboBox();
        jScrollBar2 = new javax.swing.JScrollBar();
        Home = new javax.swing.JTabbedPane();
        homeTab = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        groupHome = new javax.swing.JLabel();
        groupInHomeTab = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        RCMInHomeTab = new javax.swing.JComboBox();

        canvas3 = new javax.swing.JPanel();
        canvas3.setBackground(Color.white);
        BorderLayout borderLayoutCanvas=new BorderLayout();
        canvas3.setLayout(borderLayoutCanvas);
         
        criteria = new javax.swing.JLabel();
        criteriaInHomeTab = new javax.swing.JComboBox();
        addRCMTab = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        idAddRCMTab = new javax.swing.JLabel();
        addAddRCMTab = new javax.swing.JLabel();
        cityAddRCMTab = new javax.swing.JLabel();
        pinAddRCMTab = new javax.swing.JLabel();
        stateAddRCMTab = new javax.swing.JLabel();
        selectAIAddRCMTab = new javax.swing.JLabel();
        priceAIAddRCMTab = new javax.swing.JLabel();
        selectAIInAddRCMTab = new javax.swing.JComboBox();
        priceInAddRCMTab = new javax.swing.JTextField();
        addItemInAddRCMTab = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        addedItemsInAddRCMTab = new javax.swing.JList(); 
        addedItemsInAddRCMTab.setMaximumSize(new Dimension(33,80));
        addedItemsInAddRCMTab.setPreferredSize(new Dimension(33,80));
        jLabel8 = new javax.swing.JLabel();
        removeItemInAddRCMTab = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        groupInAddRCMTab = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        activeInAddRCMTab = new javax.swing.JRadioButton();
        disableInAddRCMTab = new javax.swing.JRadioButton();
        radioButtonInAddRCMTab.add(activeInAddRCMTab);
        radioButtonInAddRCMTab.add(disableInAddRCMTab);
        addRCM = new javax.swing.JButton();
        idInAddRCMTab = new javax.swing.JTextField();
        addressInAddRCMTab = new javax.swing.JTextField();
        cityInAddRCMTab = new javax.swing.JTextField();
        pinInAddRCMTab = new javax.swing.JTextField();
        stateInAddRCMTab = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        amountInAddRCMTab = new javax.swing.JTextField();
        removeRCMTab = new javax.swing.JPanel();
        groupRemoveRCM = new javax.swing.JLabel();
        groupInrRemoveRCMTab = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        RCMInRemoveRCMTab = new javax.swing.JComboBox();
        removeRCM = new javax.swing.JButton();
        maintainRCMTab = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        IDMaintainRCM = new javax.swing.JLabel();
        AddMaintainRCM = new javax.swing.JLabel();
        CityMaintainRCM = new javax.swing.JLabel();
        PINMaintainRCM = new javax.swing.JLabel();
        StateMaintainRCM = new javax.swing.JLabel();
        selectAIMaintainRCM = new javax.swing.JLabel();
        priceMaintainRCM = new javax.swing.JLabel();
        selectAIInMaintainRCMTab = new javax.swing.JComboBox();
        priceInMaintainRCMTab = new javax.swing.JTextField();
        addItemInMaintainRCMTab = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        addedItemsInMaintainRCMTab = new javax.swing.JList();
        addedAIMaintainRCM = new javax.swing.JLabel();
        removeItemsInMaintainTab = new javax.swing.JButton();
        changeGroupMaintainRCM = new javax.swing.JLabel();
        groupInMaintainTab = new javax.swing.JComboBox();
        statusMaintainRCM = new javax.swing.JLabel();
        activeMaintainRCM = new javax.swing.JRadioButton();
        disableMaintainRCM = new javax.swing.JRadioButton();
        radioButtonInMaintainRCMTab.add(activeMaintainRCM);
        radioButtonInMaintainRCMTab.add(disableMaintainRCM);
        updateRCM = new javax.swing.JButton();
        addInMaintainRCMTab = new javax.swing.JTextField();
        cityInMaintainRCMTab = new javax.swing.JTextField();
        pinInMaintainRCMTab = new javax.swing.JTextField();
        stateInMaintainRCMTab = new javax.swing.JTextField();
        amountMaintainRCM = new javax.swing.JLabel();
        amountInMaintainRCMTab = new javax.swing.JTextField();
        groupMaintainRCM = new javax.swing.JLabel();
        selectedGroupInMatainRCMTab = new javax.swing.JComboBox();
        IdInMaintainRCMTab = new javax.swing.JComboBox();
        logOutTab = new javax.swing.JPanel();

        groupInMaintainRCMTab.setModel(new javax.swing.DefaultComboBoxModel(groups.toArray()));
        //Home.setSize(649, 500);

        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Home.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        Home.setPreferredSize(new java.awt.Dimension(649, 500));
        Home.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
            }
        });

        homeTab.setLayout(new java.awt.BorderLayout());
        homeTab.add(jLabel12, java.awt.BorderLayout.CENTER);

        jPanel7.setAutoscrolls(true);

        groupHome.setText("Group");

        groupInHomeTab.setModel(new javax.swing.DefaultComboBoxModel(groupNames.toArray()));

        jLabel14.setText("RCM");

        RCMInHomeTab.setModel(new javax.swing.DefaultComboBoxModel());
        criteriaInHomeTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    String groupName = groupInHomeTab.getSelectedItem().toString();
                    int rcmName = Integer.parseInt(RCMInHomeTab.getSelectedItem().toString());
                    String criteria = criteriaInHomeTab.getSelectedItem().toString();
                    String url = "jdbc:mysql://localhost:3306/ecore";
                    String driver = "com.mysql.jdbc.Driver";
                    String userName = "root";
                    String password = "password";
                    Class.forName(driver).newInstance();
                    Connection conn = DriverManager.getConnection(url, userName, password);
                    
                    
                    
                    if (criteria.equals("Show Collected Items By Type")) {
                        String sql = "SELECT ITEMNAME, SUM(WEIGHT) FROM TRANSACTION WHERE RCMID='"+rcmName+"'GROUP BY ITEMNAME";
                        PreparedStatement prepStmt = conn.prepareStatement(sql);
                        ResultSet rs = prepStmt.executeQuery();
                        ArrayList<Integer> weight=new ArrayList<>();
                        ArrayList<String> itemName=new ArrayList<>();
                        while(rs.next()){
                            itemName.add(rs.getString(1));
                            weight.add(Integer.parseInt(rs.getString(2)));
                            System.out.println(rs.getString(2));
                        }                        
                        conn.close();
                        int[] weights=new int[weight.size()];
                        String[] itemNames=new String[itemName.size()];
                        int i=0;
                        for(Integer w:weight){
                            weights[i++]=w;
                            
                        }
                        i=0;
                        for(String w:itemName){
                            itemNames[i++]=w;
                            
                        }
                        
                        //canvas3.setLayout(new GridLayout(1, 1));
                        javax.swing.JPanel graph= new Graph(weights,itemNames);
                        //graph.setPreferredSize(new Dimension(80,80));
                        canvas3.removeAll();
                        canvas3.add(graph,BorderLayout.CENTER);
                        frame.revalidate();
                        frame.repaint();
                        //canvas3.
                        
                        //canvas3.repaint();
                    } else if (criteria.equals("Show Today's Collected Items")) {
                        String sql = "SELECT itemname,sum(weight) FROM transaction WHERE rcmid='"+rcmName+"'and DATE(time) = DATE_SUB(CURDATE(), INTERVAL 0 DAY) GROUP BY itemname";
                        PreparedStatement prepStmt = conn.prepareStatement(sql);
                        ResultSet rs = prepStmt.executeQuery();
                        ArrayList<Integer> weight=new ArrayList<>();
                        ArrayList<String> itemName=new ArrayList<>();
                        while(rs.next()){
                            itemName.add(rs.getString(1));
                            weight.add(Integer.parseInt(rs.getString(2)));
                            System.out.println(rs.getString(2));
                        }                        
                        int[] weights=new int[weight.size()];
                        String[] itemNames=new String[itemName.size()];
                        int i=0;
                        for(Integer w:weight){
                            weights[i++]=w;
                            
                        }
                        i=0;
                        for(String w:itemName){
                            itemNames[i++]=w;
                            
                        }
                        
                        //canvas3.setLayout(new GridLayout(1, 1));
                        javax.swing.JPanel graph= new Graph(weights,itemNames);
                        //graph.setPreferredSize(new Dimension(80,80));
                        canvas3.removeAll();
                        canvas3.add(graph,BorderLayout.CENTER);
                        frame.revalidate();
                        frame.repaint();
                        
                        
                        
                        
                        
                        
                        
                    } else {
                        
                         Object[] columns=new Object[]{"RCM ID","Trash Status","Cash Status","Operational Status"};
                         Object[][] rows=new Object[1][4];
                         RCMGroup g= getGroup(groupName);
                         RCM r=g.getRCM(rcmName);
                         rows[0][0]=r.getId();
                         rows[0][1]=(1.0*r.trash.getTotalWeight()/r.trash.getCapacity())*100.0+" % Full";
                         rows[0][2]=r.getAmount();
                         rows[0][3]=r.getStatus();
                         String status="";
                         
                         
                        canvas3.removeAll();
                       javax.swing.JPanel sta= new Status(rows,columns);
                       canvas3.add(sta);
                        System.out.println(status);                                                                     
                        frame.revalidate();
                        frame.repaint();
                        
                        
                        
                        
                    } 


                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(RMOSGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(RMOSGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(RMOSGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(RMOSGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });







        criteria.setText("Criteria");

        criteriaInHomeTab.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Show Collected Items By Type", "Show Today's Collected Items", "Status Summary"}));





        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(groupHome)
                .addComponent(criteria))
                .addGap(44, 44, 44)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(groupInHomeTab, 0, 129, Short.MAX_VALUE)
                .addComponent(RCMInHomeTab, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(criteriaInHomeTab, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(canvas3, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                // .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(groupHome)
                .addComponent(groupInHomeTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel14)
                .addComponent(RCMInHomeTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(criteria)
                .addComponent(criteriaInHomeTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGap(33, 33, 33)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel7Layout.createSequentialGroup()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(canvas3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()))));
//         Graphics g=frame.getGraphics();
//         g.drawString("ss", 200, 200);

        homeTab.add(jPanel7, java.awt.BorderLayout.LINE_END);


        Home.addTab("Home                            ", homeTab);

        addRCMTab.setLayout(new java.awt.BorderLayout());

        jPanel6.setAutoscrolls(true);
        jPanel6.setName("Acceptable Items"); // NOI18N

        idAddRCMTab.setText("ID");

        addAddRCMTab.setText("Address");

        cityAddRCMTab.setText("City");

        pinAddRCMTab.setText("PIN");

        stateAddRCMTab.setText("State");

        selectAIAddRCMTab.setText("Select Acceptable Items");

        priceAIAddRCMTab.setText("Price");

        selectAIInAddRCMTab.setModel(new javax.swing.DefaultComboBoxModel(RecyclableItem.getTypes()));

        addItemInAddRCMTab.setText("Add");
        addItemInAddRCMTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                String name = selectAIInAddRCMTab.getSelectedItem().toString();
                String price = priceInAddRCMTab.getText();
                for (RecyclableItem r : addedItemsInAddRCM) {
                    if (r.type.toString().equals(name)) {
                        JOptionPane.showMessageDialog(null, "Item Already Exist");
                        return;
                    }
                }
                addedItemsInAddRCM.add(RecyclableItem.getObject(getType(selectAIInAddRCMTab.getSelectedItem().toString()), Double.parseDouble(priceInAddRCMTab.getText())));
                //addedItemsInTextAreaAddRCM += "\n" + name + "  " + price;
                final DefaultListModel model = new DefaultListModel();
                for (RecyclableItem r : addedItemsInAddRCM) {
                    model.addElement(r.type + "  " + r.price + "$");
                }
                addedItemsInAddRCMTab.setModel(model);

            }
        });

        //addedItemsInAddRCMTab.setEditable(false);
        addedItemsInAddRCMTab.setAlignmentX((float) 0.5);
        addedItemsInAddRCMTab.setAlignmentX((float) 0.5);
        addedItemsInAddRCMTab.setMaximumSize(new Dimension(33, 40));
        addedItemsInAddRCMTab.setMinimumSize(new Dimension(33, 40));

        //addedItemsInAddRCMTab.setLineWrap(true);
        //addedItemsInAddRCMTab.setRows(5);
        addedItemsInAddRCMTab.setAutoscrolls(true);
        jScrollPane1.setViewportView(addedItemsInAddRCMTab);

        jLabel8.setText("Added Items");

        removeItemInAddRCMTab.setText("Remove");
        removeItemInAddRCMTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String s = (String) addedItemsInAddRCMTab.getSelectedValue();
                String[] split = s.split(" ");
                //System.out.println("selected value "+s);
                RecyclableItem k = null;
                for (RecyclableItem r : addedItemsInAddRCM) {
                    if (r.type.toString().equals(split[0])) {
                        k = r;
                        break;
                    }
                }
                addedItemsInAddRCM.remove(k);
                final DefaultListModel model = new DefaultListModel();
                for (RecyclableItem r : addedItemsInAddRCM) {
                    model.addElement(r.type + "  " + r.price + "$");
                }
                addedItemsInAddRCMTab.setModel(model);




                //addedItemsInAddRCMTab.setText(prepareString(addedItemsInAddRCM));


            }
        });

        jLabel9.setText("Group");

        groupInAddRCMTab.setModel(new javax.swing.DefaultComboBoxModel(groupNames.toArray()));

        jLabel10.setText("Status");

        activeInAddRCMTab.setText("Active");
        activeInAddRCMTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                status = true;
            }
        });

        disableInAddRCMTab.setText("Disable");
        disableInAddRCMTab.setToolTipText("");
        disableInAddRCMTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                status = false;
            }
        });

        addRCM.setText("Add RCM");
        addRCM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println();
                RCM.Status st = RCM.Status.IDLE;
                if (status) {
                    st = st.ACTIVE;
                }
                System.out.println(groupInAddRCMTab.getSelectedItem().toString());

                getGroup(groupInAddRCMTab.getSelectedItem().toString()).addRCM(new RCMGUI(Integer.parseInt(idInAddRCMTab.getText()), new Location(addressInAddRCMTab.getText(), Integer.parseInt(pinInAddRCMTab.getText()), cityInAddRCMTab.getText(), stateInAddRCMTab.getText()), addedItemsInAddRCM, st, 10, Double.parseDouble(amountInAddRCMTab.getText())));
                RCMNames.add(idInAddRCMTab.getText());
                System.out.println(addedItemsInAddRCM);
                IdInMaintainRCMTab.setModel(new javax.swing.DefaultComboBoxModel(RCMNames.toArray()));
                RCMInHomeTab.setModel(new javax.swing.DefaultComboBoxModel(RCMNames.toArray()));
                RCMInRemoveRCMTab.setModel(new javax.swing.DefaultComboBoxModel(RCMNames.toArray()));
                clearAllFieldsInAddRCMTab();
                addedItemsInAddRCM = null;
                addedItemsInAddRCM = new ArrayList<>();
            }
        });

        idInAddRCMTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });

        jLabel11.setText("Amount");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(stateAddRCMTab)
                .addComponent(selectAIAddRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(pinAddRCMTab)
                .addComponent(cityAddRCMTab)
                .addComponent(addAddRCMTab)
                .addComponent(idAddRCMTab)
                .addComponent(jLabel8)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel10)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(priceAIAddRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(activeInAddRCMTab)
                .addGap(28, 28, 28)
                .addComponent(disableInAddRCMTab))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(amountInAddRCMTab, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(groupInAddRCMTab, javax.swing.GroupLayout.Alignment.LEADING, 0, 211, Short.MAX_VALUE))
                .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(addRCM)))
                .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addComponent(idInAddRCMTab, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(addressInAddRCMTab, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(cityInAddRCMTab, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pinInAddRCMTab, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(stateInAddRCMTab, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(priceInAddRCMTab, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(addItemInAddRCMTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(selectAIInAddRCMTab, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(removeItemInAddRCMTab)
                .addGap(55, 55, 55)))));
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(idAddRCMTab)
                .addComponent(idInAddRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(addAddRCMTab)
                .addComponent(addressInAddRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cityAddRCMTab)
                .addComponent(cityInAddRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pinAddRCMTab)
                .addComponent(pinInAddRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(stateAddRCMTab)
                .addComponent(stateInAddRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(selectAIInAddRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(priceInAddRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(addItemInAddRCMTab)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(removeItemInAddRCMTab)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(selectAIAddRCMTab)
                .addGap(18, 18, 18)
                .addComponent(priceAIAddRCMTab)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(70, 70, 70)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel9)
                .addComponent(groupInAddRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel10)
                .addComponent(activeInAddRCMTab)
                .addComponent(disableInAddRCMTab))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(amountInAddRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addComponent(addRCM)
                .addContainerGap()));

        addRCMTab.add(jPanel6, java.awt.BorderLayout.CENTER);

        Home.addTab("Add RCM                       ", addRCMTab);
        addRCMTab.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
            }
        });

        groupRemoveRCM.setText("Group");



        groupInrRemoveRCMTab.setModel(new javax.swing.DefaultComboBoxModel(groupNames.toArray()));

        jLabel18.setText("RCM");

        RCMInRemoveRCMTab.setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));

        removeRCM.setText("Remove");
        removeRCM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                for (RCMGroup r : groups) {
                    if (r.getName() == groupInrRemoveRCMTab.getSelectedItem().toString()) {
                        RCMNames.remove(RCMInRemoveRCMTab.getSelectedItem().toString());
                        RCMGUI rcm = (RCMGUI) r.getRCM(Integer.parseInt(RCMInRemoveRCMTab.getSelectedItem().toString()));
                        rcm.dispose();
                        r.removeRCM(Integer.parseInt(RCMInRemoveRCMTab.getSelectedItem().toString()));

                        IdInMaintainRCMTab.setModel(new javax.swing.DefaultComboBoxModel(RCMNames.toArray()));
                        RCMInHomeTab.setModel(new javax.swing.DefaultComboBoxModel(RCMNames.toArray()));
                        RCMInRemoveRCMTab.setModel(new javax.swing.DefaultComboBoxModel(RCMNames.toArray()));

                    }
                }
                System.out.println("buttin predssed");
            }
        });;

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(removeRCMTab);
        removeRCMTab.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(groupRemoveRCM)
                .addComponent(jLabel18))
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(groupInrRemoveRCMTab, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RCMInRemoveRCMTab, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(removeRCM, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                .addContainerGap(292, Short.MAX_VALUE)));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(groupRemoveRCM)
                .addComponent(groupInrRemoveRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel18)
                .addComponent(RCMInRemoveRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(removeRCM)
                .addContainerGap(222, Short.MAX_VALUE)));

        Home.addTab("Remove RCM               ", removeRCMTab);

        jPanel8.setAutoscrolls(false);
        jPanel8.setName("Acceptable Items"); // NOI18N

        IDMaintainRCM.setText("ID");

        AddMaintainRCM.setText("Address");

        CityMaintainRCM.setText("City");

        PINMaintainRCM.setText("PIN");

        StateMaintainRCM.setText("State");

        selectAIMaintainRCM.setText("Select Acceptable Items");

        priceMaintainRCM.setText("Price");









        selectAIInMaintainRCMTab.setModel(new javax.swing.DefaultComboBoxModel(RecyclableItem.getTypes()));

        priceInMaintainRCMTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });

        addItemInMaintainRCMTab.setText("Add");
        addItemInMaintainRCMTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                String name = selectAIInMaintainRCMTab.getSelectedItem().toString();
                String price = priceInMaintainRCMTab.getText();
                for (RecyclableItem r : addedItemsInMaintainRCM) {
                    if (r.type.toString().equals(name)) {
                        JOptionPane.showMessageDialog(null, "Item Already Exist");
                        return;
                    }
                }
                addedItemsInMaintainRCM.add(RecyclableItem.getObject(getType(selectAIInMaintainRCMTab.getSelectedItem().toString()), Double.parseDouble(priceInMaintainRCMTab.getText())));
                final DefaultListModel model = new DefaultListModel();
                for (RecyclableItem r : addedItemsInMaintainRCM) {
                    model.addElement(r.type + "  " + r.price + "$");
                }
                addedItemsInMaintainRCMTab.setModel(model);


            }
        });

        addedItemsInMaintainRCMTab.setAlignmentX((float) 0.5);
        addedItemsInMaintainRCMTab.setAlignmentX((float) 0.5);
        addedItemsInMaintainRCMTab.setMaximumSize(new Dimension(33, 40));
        addedItemsInMaintainRCMTab.setMinimumSize(new Dimension(33, 40));
//        addedItemsInMaintainRCMTab.setEditable(false);
//        addedItemsInMaintainRCMTab.setColumns(20);
//        addedItemsInMaintainRCMTab.setLineWrap(true);
//        addedItemsInMaintainRCMTab.setRows(5);
        addedItemsInMaintainRCMTab.setAutoscrolls(false);
        jScrollPane2.setViewportView(addedItemsInMaintainRCMTab);

        addedAIMaintainRCM.setText("Added Items");
///////////////////////////////////////////////////////////////////////////////////////////////////
        removeItemsInMaintainTab.setText("Remove");
        removeItemsInMaintainTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String s = (String) addedItemsInMaintainRCMTab.getSelectedValue();
                String[] split = s.split(" ");
                //System.out.println("selected value "+s);
                RecyclableItem k = null;
                for (RecyclableItem r : addedItemsInMaintainRCM) {
                    if (r.type.toString().equals(split[0])) {
                        k = r;
                        break;
                    }
                }
                addedItemsInMaintainRCM.remove(k);
                final DefaultListModel model = new DefaultListModel();
                for (RecyclableItem r : addedItemsInMaintainRCM) {
                    model.addElement(r.type + "  " + r.price + "$");
                }
                addedItemsInMaintainRCMTab.setModel(model);
            }
        });



        changeGroupMaintainRCM.setText("Group");

        groupInMaintainTab.setModel(new javax.swing.DefaultComboBoxModel(groupNames.toArray()));

        statusMaintainRCM.setText("Status");

        activeMaintainRCM.setText("Active");
        activeMaintainRCM.setActionCommand("ACTIVE");
        activeMaintainRCM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });

        disableMaintainRCM.setText("Disable");
        disableMaintainRCM.setToolTipText("");
        disableMaintainRCM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });

        updateRCM.setText("Update");
        updateRCM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RCMGroupInMaintainRCMTab.removeRCM(Integer.parseInt(IdInMaintainRCMTab.getSelectedItem().toString()));
                getGroup(groupInMaintainTab.getSelectedItem().toString()).addRCM(RCMInMaintainRCMTab);
                RCMInMaintainRCMTab.setAmount(Double.parseDouble(amountInMaintainRCMTab.getText()));
                RCMInMaintainRCMTab.setAcceptableItemList(addedItemsInMaintainRCM);
                if (radioButtonInMaintainRCMTab.getSelection().getActionCommand() == "ACTIVE") {
                    RCMInMaintainRCMTab.setStatus(RCM.Status.ACTIVE);
                } else {
                    RCMInMaintainRCMTab.setStatus(RCM.Status.IDLE);
                }

                RCMInMaintainRCMTab.setLocation(new Location(addInMaintainRCMTab.getText(), Integer.parseInt(pinInMaintainRCMTab.getText()), cityInMaintainRCMTab.getText(), stateInMaintainRCMTab.getText()));
                clearAllFieldsInUpdateRCMTab();

            }
        });

        amountMaintainRCM.setText("Amount");

        groupMaintainRCM.setText("Group");

        selectedGroupInMatainRCMTab.setModel(new javax.swing.DefaultComboBoxModel(groupNames.toArray()));
////////////////////////////////////////////////////////////////////////////////////////////////////////
        IdInMaintainRCMTab.setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));
        IdInMaintainRCMTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RCMGroupInMaintainRCMTab = getGroup(groupInMaintainTab.getSelectedItem().toString());
                RCMInMaintainRCMTab = RCMGroupInMaintainRCMTab.getRCM(Integer.parseInt(IdInMaintainRCMTab.getSelectedItem().toString()));
                System.out.println(RCMInMaintainRCMTab);
                System.out.println(RCMInMaintainRCMTab.getLocation().getStreet());
                addInMaintainRCMTab.setText(RCMInMaintainRCMTab.getLocation().getStreet());
                cityInMaintainRCMTab.setText(RCMInMaintainRCMTab.getLocation().getCity());
                pinInMaintainRCMTab.setText(RCMInMaintainRCMTab.getLocation().getPin() + "");
                stateInMaintainRCMTab.setText(RCMInMaintainRCMTab.getLocation().getState());
                amountInMaintainRCMTab.setText(RCMInMaintainRCMTab.getAmount() + "");
                addedItemsInMaintainRCM = RCMInMaintainRCMTab.getAcceptableItemList();
                final DefaultListModel model = new DefaultListModel();
                for (RecyclableItem r : addedItemsInMaintainRCM) {
                    model.addElement(r.type + " " + r.price + "$");
                }
                addedItemsInMaintainRCMTab.setModel(model);
                //addedItemsInMaintainRCMTab.setText(prepareString(addedItemsInMaintainRCM));
                System.out.println(RCMInMaintainRCMTab.getStatus().toString());
                if (RCMInMaintainRCMTab.getStatus().toString() == "ACTIVE") {
                    activeMaintainRCM.setEnabled(true);
                    radioButtonInMaintainRCMTab.setSelected(activeMaintainRCM.getModel(), true);
                } else {
                    radioButtonInMaintainRCMTab.setSelected(disableMaintainRCM.getModel(), true);
                }

            }
        });




        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(StateMaintainRCM)
                .addComponent(selectAIMaintainRCM, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(PINMaintainRCM)
                .addComponent(CityMaintainRCM)
                .addComponent(AddMaintainRCM)
                .addComponent(IDMaintainRCM)
                .addComponent(addedAIMaintainRCM)
                .addComponent(changeGroupMaintainRCM, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(statusMaintainRCM)
                .addComponent(amountMaintainRCM, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(priceMaintainRCM, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(groupMaintainRCM))
                .addGap(46, 46, 46)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(activeMaintainRCM)
                .addGap(28, 28, 28)
                .addComponent(disableMaintainRCM))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(amountInMaintainRCMTab, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(groupInMaintainTab, javax.swing.GroupLayout.Alignment.LEADING, 0, 211, Short.MAX_VALUE))
                .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(updateRCM)))
                .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(IdInMaintainRCMTab, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(selectedGroupInMatainRCMTab, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addComponent(addInMaintainRCMTab, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(cityInMaintainRCMTab, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pinInMaintainRCMTab, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(stateInMaintainRCMTab, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(priceInMaintainRCMTab, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(addItemInMaintainRCMTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(selectAIInMaintainRCMTab, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(removeItemsInMaintainTab)
                .addGap(55, 55, 55)))));
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(groupMaintainRCM)
                .addComponent(selectedGroupInMatainRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(IDMaintainRCM)
                .addComponent(IdInMaintainRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(AddMaintainRCM)
                .addComponent(addInMaintainRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(CityMaintainRCM)
                .addComponent(cityInMaintainRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PINMaintainRCM)
                .addComponent(pinInMaintainRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(StateMaintainRCM)
                .addComponent(stateInMaintainRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(selectAIInMaintainRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(priceInMaintainRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(addItemInMaintainRCMTab)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(removeItemsInMaintainTab)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(selectAIMaintainRCM)
                .addGap(18, 18, 18)
                .addComponent(priceMaintainRCM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addedAIMaintainRCM)
                .addGap(70, 70, 70)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(changeGroupMaintainRCM)
                .addComponent(groupInMaintainTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(statusMaintainRCM)
                .addComponent(activeMaintainRCM)
                .addComponent(disableMaintainRCM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(amountInMaintainRCMTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(amountMaintainRCM))
                .addGap(18, 18, 18)
                .addComponent(updateRCM)
                .addContainerGap()));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(maintainRCMTab);
        maintainRCMTab.setLayout(jPanel4Layout);
        maintainRCMTab.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                IdInMaintainRCMTab.setModel(new javax.swing.DefaultComboBoxModel(RCMNames.toArray()));
                System.out.println(RCMNames.toArray());
            }
        });
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 683, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))));
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 512, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))));

        Home.addTab("Maintain RCM                ", maintainRCMTab);
        maintainRCMTab.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                System.out.println("maintain");
            }
        });



        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(logOutTab);
        logOutTab.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 683, Short.MAX_VALUE));
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 509, Short.MAX_VALUE));

        Home.addTab("Log Out                    ", logOutTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Home, javax.swing.GroupLayout.PREFERRED_SIZE, 688, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Home, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)));
        logOutTab.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Home.setSelectedIndex(0);
                frame.setVisible(false);

                login.setVisible(true);
            }
        });


        frame.pack();




    }

    public String prepareString(ArrayList<RecyclableItem> list) {
        String s = "";
        for (RecyclableItem r : list) {
            s += r.type + "  " + r.price + "\n";
        }


        return s;
    }

    private void clearAllFieldsInUpdateRCMTab() {

        pinInMaintainRCMTab.setText("");
        stateInMaintainRCMTab.setText("");
        cityInMaintainRCMTab.setText("");
        addInMaintainRCMTab.setText("");
        priceInMaintainRCMTab.setText("");
        amountInMaintainRCMTab.setText("");
        final DefaultListModel model = new DefaultListModel();
        addedItemsInMaintainRCMTab.setModel(model);
        // addedItemsInAddRCMTab.setText(addedItemsInTextAreaAddRCM);
        addedItemsInAddRCM = null;

    }

    private void clearAllFieldsInAddRCMTab() {
        idInAddRCMTab.setText("");
        pinInAddRCMTab.setText("");
        stateInAddRCMTab.setText("");
        cityInAddRCMTab.setText("");
        addressInAddRCMTab.setText("");
        priceInAddRCMTab.setText("");
        amountInAddRCMTab.setText("");
        addedItemsInTextAreaAddRCM = "";
        final DefaultListModel model = new DefaultListModel();
        addedItemsInAddRCMTab.setModel(model);
        addedItemsInAddRCM = null;

    }

    public RCMGroup getGroup(String name) {
        for (RCMGroup g : groups) {
            if (g.getName() == name) {
                return g;
            }
        }

        return null;
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);


    }
    static LogIn login;

    public Recyclable.Types getType(String type) {
        for (Recyclable.Types t : Recyclable.Types.values()) {
            if (t.toString().equalsIgnoreCase(type)) {
                return t;
            }
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        
        RMOSGUI r = new RMOSGUI();
        r.setVisible(false);
        login = new LogIn(r);
        login.setVisible(true);
        System.out.println("ee");

    }
}
