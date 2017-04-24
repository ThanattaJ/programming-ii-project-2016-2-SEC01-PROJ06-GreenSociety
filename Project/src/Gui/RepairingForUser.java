/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

//import bike_gui.*;
import BikeRapair.Repair;
import ConnectDB.ConnectDatabase;
import Timer.Timer;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;

/**
 *
 * @author user
 */
public class RepairingForUser extends javax.swing.JFrame {
    
    private Repair rp = new Repair();
    private static final int MY_MINIMUM = 0;
    private static final int MY_MAXIMUM = 100;
    private String asking;
    private String repairing;
    private String status;
    private String bike="";//รับจาก GUI ให้ user กรอก
    private String whyRepair="";//ให้ user กรอกว่าทำไมถึงต้องส่งซ่อม
    private String color="";
    private long peairId;
    private long countTransId;
    /**
     * Creates new form 
     */

    public RepairingForUser(Repair r) {
        initComponents();
        rp = r;
        System.out.println("RepairUser: "+rp.getProblem());
        userFollowingForRepair(r);
        jPanelRepairUserFollowRepairing.setVisible(true);
        jPanelRepairUserSentToAdmin.setVisible(false);

    }
    
    public RepairingForUser() {
        initComponents();
        connectDBShowRepairForUserFollowing(asking);
        jPanelRepairUserSentToAdmin.setVisible(true);
        jPanelRepairUserFollowRepairing.setVisible(false);
    }

    public void userFollowingForRepair(Repair r){
        connectDBShowRepairForUserFollowing(asking);
        textAsking.setText(rp.getProblem());
        textRecieving1.setText(rp.getStatus());
        textRepair.setText(rp.getDetail());
        //set ค่าให้ progress ว่าเป็น percent ตั้งแต่ 1-100
        jProgressBarAskingToRepairing.setMinimum(MY_MINIMUM);
        jProgressBarAskingToRepairing.setMaximum(MY_MAXIMUM);
        jPBpercentRepairToRecieving.setMinimum(MY_MINIMUM);
        jPBpercentRepairToRecieving.setMaximum(MY_MAXIMUM);
        //------------------------------------------------------------
        jProgressBarAskingToRepairing.setStringPainted(true);
        jProgressBarAskingToRepairing.setBackground(Color.blue);
        jProgressBarAskingToRepairing.setForeground(Color.white);
        jProgressBarAskingToRepairing.setFont(new java.awt.Font("Leelawadee",0,11));
        jProgressBarAskingToRepairing.setString(rp.getPercentAskingToRepair()+"%");
        //set progessBar ให้มีค่เท่ากับที่ admin ส่งค่ามาว่ากี่เปอร์เซ็น
        updateBarAskingToRepair(rp.getPercentAskingToRepair());
        updateBarRepairToRecieving(rp.getPercentRepairToRecie());
    }
    
    public void updateBarAskingToRepair(int newValue) {//รับจาก คลาส Repairing  Percent ถึงไหนแล้ว
    jProgressBarAskingToRepairing.setValue(newValue);
    }
    
    public void updateBarRepairToRecieving(int newValue) {//รับจาก คลาส Repairing  Percent ถึงไหนแล้ว
    jPBpercentRepairToRecieving.setValue(newValue);
    }
    
        public void connectDBFomeUserToAdmin(long userID){
        Timer t = new Timer();
        Timestamp startDate = new Timestamp(t.getBorrowTime().getTime());
        Timestamp returnDate = new Timestamp(t.getReturnTime().getTime());
        try{
            ConnectDatabase cndb = new ConnectDatabase();
            Connection connect = ConnectDatabase.connectDb("jan", "jan042");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("...connectDBFomeUserToAdmin");
            Statement st = connect.createStatement(); 
            //ดึงเอา id ที่มาที่สุดออกมา เพื่อให้มันสามารถ insert ลง table ให้ไม่ซ้ำกันได้
            String temp = "SELECT MAX(id) AS countId FROM Prepair_Desctiption ";
            ResultSet rs = st.executeQuery(temp);
            int count=0;
            while(rs.next()){
                count = rs.getInt("countId");
            }
            peairId = ++count;
            //------------------------------------------------------------------
            //ดึง transId ว่ามีมากที่สุดเท่าไร เพื่อให้มัน กรอกลง DB ได้ และไม่ซ้ำกับ transID
            String temp1 = "SELECT MAX(transId) AS countTransId FROM Transaction ";
            ResultSet rs1 = st.executeQuery(temp1);
            int countTrans=0;
            while(rs1.next()){
                countTrans = rs1.getInt("countTransId");
            }
            countTransId = ++countTrans;
            //------------------------------------------------------------------
            //เอาข้อมูลลง DB prapair_Desctiption
            String sqlid = "\'"+this.peairId+"\'";
            String brand = "\'"+bike+"\'";
            String sqlcolor = "\'"+color+"\'";
            String sqlWhy = "\'"+whyRepair+"\'";
            String sqlTransId = "\'"+countTransId+"\'";
            
            String temp2 ="INSERT INTO `Prepair_Desctiption` (`id`, `brand`, `color`, `other`, `transID`) "
                    + "VALUES"+" ("+sqlid+","
                    + brand +","
                    + sqlcolor + ","
                    + sqlWhy + ","
                    + sqlTransId +")";
            
            st.executeUpdate(temp2);
            //---------------------------------------------------------------------------------------
            //เอาข้อมูลลง transaction ด้วย 
            String userId = "\'"+userID+"\'";
            String action = "\'Repair\'";
            String officerId = "\'"+200+"\'";
            System.out.println("..insert into To Prepair_Desctiption calling by connectDBFomeUserToAdmin");
            
            String temp3 ="INSERT INTO Transaction VALUES " //set ค่าให้กับ Database
                    + "("+this.countTransId+","
                    +"'"+startDate+"','"
                     +returnDate+"',"
                      +0+ ","
                      +userId+","
                      +action+"," 
                      +officerId+")";
             
            st.executeUpdate(temp3);
            System.out.println(".insert into To Transaction calling by connectDBFomeUserToAdmin");
            //------------------------------------------------------------------
            if(connect != null){
                    connect.close();
		}
		}catch (SQLException e){
                    e.printStackTrace();
        }
        
        catch(ClassNotFoundException cfe){
            System.out.println(cfe);
        }
        catch(Exception ex){
            System.out.println(ex);
        }

    }
        
    public void connectDBShowRepairForUserFollowing(String ask){//เพื่อที่ User จะสามารถดูและติดตามดารซ่อมของตัวเองได้
        try{
            ConnectDatabase cndb = new ConnectDatabase();
            Connection connect = ConnectDatabase.connectDb("jan", "jan042");
            Class.forName("com.mysql.jdbc.Driver");
            
            Statement st = connect.createStatement(); 
            //ดึงเอา id ที่มาที่สุดออกมา เพื่อให้มันสามารถ insert ลง table ให้ไม่ซ้ำกันได้
            String temp = "SELECT Asking,Repairing,Recieving FROM Repair_State where Asking= "+ask;
            ResultSet rs = st.executeQuery(temp);
            int count=0;
            while(rs.next()){
                asking = rs.getString("Asking");
                repairing = rs.getString("Repairing");
                status = rs.getString("Recieving");
            }
            //---------------------------------------------------------------------------------------
            if(connect != null){
                    connect.close();
		}
		}catch (SQLException e){
                    e.printStackTrace();
        }
        
        catch(ClassNotFoundException cfe){
            System.out.println(cfe);
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    
       /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelRepairingIncrease1 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanelRepairUserSentAdmin = new javax.swing.JPanel();
        jPanelNotic = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanelHead = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanelShowUser = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanelBarBike = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanelSideBar = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanelRepairUserFollowRepairing = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jProgressBarAskingToRepairing = new javax.swing.JProgressBar();
        jLabel37 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPBpercentRepairToRecieving = new javax.swing.JProgressBar();
        jLabel35 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        textRecieving1 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        textRepair = new javax.swing.JLabel();
        jPnTimeDetail = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAShowTimeUser = new javax.swing.JTextArea();
        jBtBackRepairUserSentToAdmin = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        textAsking = new javax.swing.JLabel();
        jPanelRepairUserSentToAdmin = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabelBrandBike = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jTFWhat = new javax.swing.JTextField();
        jLabelBrandBike1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jTFbike = new javax.swing.JTextField();
        jLabelBrandBike2 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jTFColor = new javax.swing.JTextField();
        jBtToFollowingRepair = new javax.swing.JButton();

        jPanelRepairingIncrease1.setBackground(new java.awt.Color(25, 41, 65));
        jPanelRepairingIncrease1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setBackground(new java.awt.Color(76, 81, 86));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Bike Repairing");
        jPanel17.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 50));

        jPanelRepairingIncrease1.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 50));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelRepairUserSentAdmin.setBackground(new java.awt.Color(25, 41, 65));
        jPanelRepairUserSentAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelNotic.setBackground(new java.awt.Color(13, 24, 35));
        jPanelNotic.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setBackground(new java.awt.Color(55, 200, 255));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanelNotic.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 10, 60));

        jLabel11.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("1");
        jPanelNotic.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 20, 30));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/circle.png"))); // NOI18N
        jPanelNotic.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, -6, 70, 60));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/notifications-button.png"))); // NOI18N
        jPanelNotic.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanelRepairUserSentAdmin.add(jPanelNotic, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 730, 60));

        jPanelHead.setBackground(new java.awt.Color(13, 24, 35));
        jPanelHead.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Leelawadee", 0, 28)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("SOCIETY");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelHead.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 140, 50));

        jTextField1.setBackground(new java.awt.Color(13, 24, 35));
        jTextField1.setFont(new java.awt.Font("Leelawadee UI", 0, 20)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Search");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanelHead.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 70, 30));
        jPanelHead.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 120, 40));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/magnifying-glass.png"))); // NOI18N
        jPanelHead.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 40, 40));

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Leelawadee", 0, 28)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("GREEN");
        jLabel12.setToolTipText("");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelHead.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 130, 50));

        jPanelRepairUserSentAdmin.add(jPanelHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 860, 60));

        jPanelShowUser.setBackground(new java.awt.Color(19, 175, 248));
        jPanelShowUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(13, 24, 35));
        jLabel4.setText("NAME  SURNAME");
        jLabel4.setToolTipText("");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelShowUser.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 130, 50));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Student");
        jLabel2.setToolTipText("");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelShowUser.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 130, 50));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/man.png"))); // NOI18N
        jPanelShowUser.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanelRepairUserSentAdmin.add(jPanelShowUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 200, 60));

        jPanelBarBike.setBackground(new java.awt.Color(19, 175, 248));
        jPanelBarBike.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/menu.png"))); // NOI18N
        jPanelBarBike.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 50, 30));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/bike.png"))); // NOI18N
        jPanelBarBike.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 50, 40));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/profile-user.png"))); // NOI18N
        jPanelBarBike.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 50, -1));

        jPanelRepairUserSentAdmin.add(jPanelBarBike, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 540));

        jPanel4.setBackground(new java.awt.Color(55, 200, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelRepairUserSentAdmin.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 210, 60));

        jPanel2.setBackground(new java.awt.Color(55, 200, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelRepairUserSentAdmin.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 540));

        jPanelSideBar.setBackground(new java.awt.Color(22, 31, 39));

        jPanel7.setBackground(new java.awt.Color(13, 24, 35));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(19, 175, 248));
        jLabel14.setText("     News");
        jLabel14.setToolTipText("");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 140, 20));

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("     Bike Repairing");
        jLabel16.setToolTipText("");
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 140, 20));

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("     Can Counter");
        jLabel17.setToolTipText("");
        jLabel17.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 140, 20));

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("     Bike Sharing");
        jLabel18.setToolTipText("");
        jLabel18.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 140, 20));

        jLabel20.setBackground(new java.awt.Color(0, 0, 0));
        jLabel20.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("     History");
        jLabel20.setToolTipText("");
        jLabel20.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 140, 20));

        jLabel21.setBackground(new java.awt.Color(0, 0, 0));
        jLabel21.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("     PROFILE");
        jLabel21.setToolTipText("");
        jPanel7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 140, 30));

        jLabel22.setBackground(new java.awt.Color(0, 0, 0));
        jLabel22.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("     Timer");
        jLabel22.setToolTipText("");
        jLabel22.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel7.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 140, 20));

        jLabel23.setBackground(new java.awt.Color(0, 0, 0));
        jLabel23.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("     Support");
        jLabel23.setToolTipText("");
        jLabel23.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 140, 20));

        jLabel19.setBackground(new java.awt.Color(0, 0, 0));
        jLabel19.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("MENU");
        jLabel19.setToolTipText("");
        jPanel7.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 50, 30));

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/chevron-arrow-down.png"))); // NOI18N
        jLabel15.setToolTipText("");
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 40, 30));

        jLabel24.setBackground(new java.awt.Color(0, 0, 0));
        jLabel24.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/circle_mini.png"))); // NOI18N
        jLabel24.setToolTipText("");
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 40, 40));

        javax.swing.GroupLayout jPanelSideBarLayout = new javax.swing.GroupLayout(jPanelSideBar);
        jPanelSideBar.setLayout(jPanelSideBarLayout);
        jPanelSideBarLayout.setHorizontalGroup(
            jPanelSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
            .addGroup(jPanelSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSideBarLayout.createSequentialGroup()
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanelSideBarLayout.setVerticalGroup(
            jPanelSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
            .addGroup(jPanelSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
        );

        jPanelRepairUserSentAdmin.add(jPanelSideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 150, 480));

        jPanelRepairUserFollowRepairing.setBackground(new java.awt.Color(25, 41, 65));
        jPanelRepairUserFollowRepairing.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setBackground(new java.awt.Color(76, 81, 86));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Bike Repairing");
        jPanel18.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 50));

        jPanelRepairUserFollowRepairing.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 50));

        jLabel28.setBackground(new java.awt.Color(0, 0, 0));
        jLabel28.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Status");
        jLabel28.setToolTipText("");
        jLabel28.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelRepairUserFollowRepairing.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 50, 30));

        jLabel38.setIcon(new javax.swing.ImageIcon("D:\\Homework\\INT105\\Project\\Project\\Bike\\png\\001-success-1.png")); // NOI18N
        jPanelRepairUserFollowRepairing.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 40, 40));

        jLabel32.setBackground(new java.awt.Color(0, 0, 0));
        jLabel32.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Asking");
        jLabel32.setToolTipText("");
        jLabel32.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelRepairUserFollowRepairing.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 50, 30));

        jProgressBarAskingToRepairing.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelRepairUserFollowRepairing.add(jProgressBarAskingToRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 210, -1));

        jLabel37.setIcon(new javax.swing.ImageIcon("D:\\Homework\\INT105\\Project\\Project\\Bike\\png\\001-success-1.png")); // NOI18N
        jPanelRepairUserFollowRepairing.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 40, 40));

        jLabel29.setBackground(new java.awt.Color(0, 0, 0));
        jLabel29.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Repairing");
        jLabel29.setToolTipText("");
        jLabel29.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelRepairUserFollowRepairing.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 70, 30));
        jPanelRepairUserFollowRepairing.add(jPBpercentRepairToRecieving, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 210, -1));

        jLabel35.setIcon(new javax.swing.ImageIcon("D:\\Homework\\INT105\\Project\\Project\\Bike\\png\\001-success-1.png")); // NOI18N
        jPanelRepairUserFollowRepairing.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 40, 40));

        jLabel30.setBackground(new java.awt.Color(0, 0, 0));
        jLabel30.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Recieving");
        jLabel30.setToolTipText("");
        jLabel30.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelRepairUserFollowRepairing.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 70, 30));

        jPanel14.setBackground(new java.awt.Color(190, 192, 184));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setBackground(new java.awt.Color(51, 51, 51));
        jLabel31.setFont(new java.awt.Font("Leelawadee", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setText("Recieving");
        jLabel31.setToolTipText("");
        jLabel31.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel14.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, 30));

        textRecieving1.setBackground(new java.awt.Color(0, 0, 0));
        textRecieving1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        textRecieving1.setForeground(new java.awt.Color(51, 102, 255));
        textRecieving1.setToolTipText("");
        textRecieving1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel14.add(textRecieving1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 47, 119, 30));

        jLabel39.setIcon(new javax.swing.ImageIcon("D:\\Homework\\INT105\\Project\\Project\\Bike\\png\\002-bicycle.png")); // NOI18N
        jPanel14.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 11, 74, 75));

        jPanelRepairUserFollowRepairing.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 260, 100));

        jPanel13.setBackground(new java.awt.Color(190, 192, 184));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setBackground(new java.awt.Color(0, 0, 0));
        jLabel33.setFont(new java.awt.Font("Leelawadee", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("Repairing");
        jLabel33.setToolTipText("");
        jLabel33.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel13.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 70, 30));

        textRepair.setBackground(new java.awt.Color(0, 0, 0));
        textRepair.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        textRepair.setForeground(new java.awt.Color(51, 51, 51));
        textRepair.setToolTipText("");
        textRepair.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel13.add(textRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 47, 184, 30));

        jPanelRepairUserFollowRepairing.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 260, 100));

        jPnTimeDetail.setBackground(new java.awt.Color(190, 192, 184));
        jPnTimeDetail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTAShowTimeUser.setBackground(new java.awt.Color(190, 192, 184));
        jTAShowTimeUser.setColumns(20);
        jTAShowTimeUser.setFont(new java.awt.Font("Leelawadee", 0, 13)); // NOI18N
        jTAShowTimeUser.setRows(5);
        jScrollPane1.setViewportView(jTAShowTimeUser);

        jPnTimeDetail.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 100));

        jPanelRepairUserFollowRepairing.add(jPnTimeDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 260, 100));

        jBtBackRepairUserSentToAdmin.setIcon(new javax.swing.ImageIcon("C:\\Users\\January\\Documents\\NetBeansProjects\\Project\\icon\\left-arrow.png")); // NOI18N
        jBtBackRepairUserSentToAdmin.setBorder(null);
        jBtBackRepairUserSentToAdmin.setContentAreaFilled(false);
        jBtBackRepairUserSentToAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtBackRepairUserSentToAdminActionPerformed(evt);
            }
        });
        jPanelRepairUserFollowRepairing.add(jBtBackRepairUserSentToAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 70, 50));

        jPanel15.setBackground(new java.awt.Color(190, 192, 184));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setBackground(new java.awt.Color(0, 0, 0));
        jLabel36.setFont(new java.awt.Font("Leelawadee", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Asking");
        jLabel36.setToolTipText("");
        jLabel36.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel15.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 50, 30));

        textAsking.setBackground(new java.awt.Color(0, 0, 0));
        textAsking.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        textAsking.setForeground(new java.awt.Color(51, 51, 51));
        textAsking.setToolTipText("");
        textAsking.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel15.add(textAsking, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 47, 210, 30));

        jPanelRepairUserFollowRepairing.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 260, 100));

        jPanelRepairUserSentAdmin.add(jPanelRepairUserFollowRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 730, 420));

        jPanelRepairUserSentToAdmin.setBackground(new java.awt.Color(25, 41, 65));
        jPanelRepairUserSentToAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(76, 81, 86));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Bike Repairing");
        jPanel16.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 50));

        jPanelRepairUserSentToAdmin.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 50));

        jPanel11.setBackground(new java.awt.Color(56, 54, 54));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBrandBike.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        jLabelBrandBike.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBrandBike.setText("Why repair?");
        jPanel11.add(jLabelBrandBike, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 90, 40));
        jPanel11.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 340, 20));

        jTFWhat.setBackground(new java.awt.Color(56, 54, 54));
        jTFWhat.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jTFWhat.setForeground(new java.awt.Color(255, 255, 255));
        jTFWhat.setBorder(null);
        jTFWhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFWhatActionPerformed(evt);
            }
        });
        jPanel11.add(jTFWhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 340, 30));

        jLabelBrandBike1.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        jLabelBrandBike1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBrandBike1.setText("Color bicycle?");
        jPanel11.add(jLabelBrandBike1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 110, 40));
        jPanel11.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 290, 20));

        jTFbike.setBackground(new java.awt.Color(56, 54, 54));
        jTFbike.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jTFbike.setForeground(new java.awt.Color(255, 255, 255));
        jTFbike.setBorder(null);
        jTFbike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFbikeActionPerformed(evt);
            }
        });
        jPanel11.add(jTFbike, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 290, 30));

        jLabelBrandBike2.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        jLabelBrandBike2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBrandBike2.setText("Bicycle model name?");
        jPanel11.add(jLabelBrandBike2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 150, 40));
        jPanel11.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 340, 20));

        jTFColor.setBackground(new java.awt.Color(56, 54, 54));
        jTFColor.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jTFColor.setForeground(new java.awt.Color(255, 255, 255));
        jTFColor.setBorder(null);
        jTFColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFColorActionPerformed(evt);
            }
        });
        jPanel11.add(jTFColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 340, 30));

        jPanelRepairUserSentToAdmin.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 520, 310));

        jBtToFollowingRepair.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jBtToFollowingRepair.setIcon(new javax.swing.ImageIcon("C:\\Users\\January\\Documents\\NetBeansProjects\\Project\\icon\\right-arrow.png")); // NOI18N
        jBtToFollowingRepair.setBorder(null);
        jBtToFollowingRepair.setContentAreaFilled(false);
        jBtToFollowingRepair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtToFollowingRepairActionPerformed(evt);
            }
        });
        jPanelRepairUserSentToAdmin.add(jBtToFollowingRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 360, 40, 50));

        jPanelRepairUserSentAdmin.add(jPanelRepairUserSentToAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 730, 420));

        getContentPane().add(jPanelRepairUserSentAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTFWhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFWhatActionPerformed
        // TODO add your handling code here:
        rp.setWhyRepair(jTFWhat.getText());
    }//GEN-LAST:event_jTFWhatActionPerformed

    private void jTFbikeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFbikeActionPerformed
        // TODO add your handling code here:
        rp.setBike(jTFbike.getText());
    }//GEN-LAST:event_jTFbikeActionPerformed

    private void jTFColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFColorActionPerformed
        // TODO add your handling code here:
        rp.setColor(jTFColor.getText());
    }//GEN-LAST:event_jTFColorActionPerformed

    private void jBtToFollowingRepairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtToFollowingRepairActionPerformed
//        java.util.Date utilDate = new java.util.Date();
//        Timestamp nowDate = new Timestamp(utilDate.getTime());
        //--------------------------------------------
        color = jTFColor.getText();
        bike = jTFbike.getText();
        whyRepair = jTFWhat.getText();
        connectDBFomeUserToAdmin(222);//return date ยังใส่ไม่ได้ต้องให้ช่างประเมินเวลาก่อน
        System.out.println("Connect DB Success");
        jPanelRepairUserFollowRepairing.setVisible(true);
        jPanelRepairUserSentToAdmin.setVisible(false);
        
    }//GEN-LAST:event_jBtToFollowingRepairActionPerformed

    private void jBtBackRepairUserSentToAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtBackRepairUserSentToAdminActionPerformed
        // TODO add your handling code here:
        jPanelRepairUserSentToAdmin.setVisible(true);
        jPanelRepairUserFollowRepairing.setVisible(false);
    }//GEN-LAST:event_jBtBackRepairUserSentToAdminActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RepairingForUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RepairingForUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RepairingForUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RepairingForUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
      
       

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RepairingForUser rping = new RepairingForUser();
                rping.setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtBackRepairUserSentToAdmin;
    private javax.swing.JButton jBtToFollowingRepair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBrandBike;
    private javax.swing.JLabel jLabelBrandBike1;
    private javax.swing.JLabel jLabelBrandBike2;
    private javax.swing.JProgressBar jPBpercentRepairToRecieving;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelBarBike;
    private javax.swing.JPanel jPanelHead;
    private javax.swing.JPanel jPanelNotic;
    private javax.swing.JPanel jPanelRepairUserFollowRepairing;
    private javax.swing.JPanel jPanelRepairUserSentAdmin;
    private javax.swing.JPanel jPanelRepairUserSentToAdmin;
    private javax.swing.JPanel jPanelRepairingIncrease1;
    private javax.swing.JPanel jPanelShowUser;
    private javax.swing.JPanel jPanelSideBar;
    private javax.swing.JPanel jPnTimeDetail;
    private javax.swing.JProgressBar jProgressBarAskingToRepairing;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextArea jTAShowTimeUser;
    private javax.swing.JTextField jTFColor;
    private javax.swing.JTextField jTFWhat;
    private javax.swing.JTextField jTFbike;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel textAsking;
    private javax.swing.JLabel textRecieving1;
    private javax.swing.JLabel textRepair;
    // End of variables declaration//GEN-END:variables
    
    
}
