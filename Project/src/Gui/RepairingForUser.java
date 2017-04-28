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
        textRecieving.setText(rp.getStatus());
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
        jLabelNumberNotic = new javax.swing.JLabel();
        jLabelIconCircle = new javax.swing.JLabel();
        jLabelIconNotic = new javax.swing.JLabel();
        jPanelHead = new javax.swing.JPanel();
        jLabelHeadSociety = new javax.swing.JLabel();
        jTextFieldSearchRepairUser = new javax.swing.JTextField();
        jSeparatorUnderSearch = new javax.swing.JSeparator();
        jLabelIconSearch = new javax.swing.JLabel();
        jLabelHeadGreen = new javax.swing.JLabel();
        jPanelShowUser = new javax.swing.JPanel();
        jLabelNameSurNameUser = new javax.swing.JLabel();
        jLabelPositionUser = new javax.swing.JLabel();
        jLabelIconUser = new javax.swing.JLabel();
        jPanelBarBike = new javax.swing.JPanel();
        jLabelMenuSideLigthBlue = new javax.swing.JLabel();
        jLabelBikeSideLigthBlue = new javax.swing.JLabel();
        jLabelUserSideLigthBlue = new javax.swing.JLabel();
        jPanelDetailUserUnder = new javax.swing.JPanel();
        jPanelSideBarUnder = new javax.swing.JPanel();
        jPanelSideBar = new javax.swing.JPanel();
        jPanelMenuSideBar = new javax.swing.JPanel();
        jLabelNew = new javax.swing.JLabel();
        jLabelBikeRepair = new javax.swing.JLabel();
        jLabelCanCounter = new javax.swing.JLabel();
        jLabelBikeSharing = new javax.swing.JLabel();
        jLabelHistory = new javax.swing.JLabel();
        jLabelProfile = new javax.swing.JLabel();
        jLabelTimer = new javax.swing.JLabel();
        jLabelSupport = new javax.swing.JLabel();
        jLabelMenu = new javax.swing.JLabel();
        jLabelSideDown = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanelRepairUserFollowRepairing = new javax.swing.JPanel();
        jPanelHeadBikeRepairing = new javax.swing.JPanel();
        jLabelBikeRepairing = new javax.swing.JLabel();
        jLabelStatus = new javax.swing.JLabel();
        jLabelIconAsking = new javax.swing.JLabel();
        jLabelAskingText = new javax.swing.JLabel();
        jProgressBarAskingToRepairing = new javax.swing.JProgressBar();
        jLabelIconRepairing = new javax.swing.JLabel();
        jLabelRepairing = new javax.swing.JLabel();
        jPBpercentRepairToRecieving = new javax.swing.JProgressBar();
        jLabelIconRecieving = new javax.swing.JLabel();
        jLabelRecieving = new javax.swing.JLabel();
        jPanelRecieving = new javax.swing.JPanel();
        jLabelRecievingText = new javax.swing.JLabel();
        textRecieving = new javax.swing.JLabel();
        jLabelIconBikeInRecieving = new javax.swing.JLabel();
        jPanelRepairing = new javax.swing.JPanel();
        jLabelRepairingText = new javax.swing.JLabel();
        textRepair = new javax.swing.JLabel();
        jPnTimeDetail = new javax.swing.JPanel();
        jBtBackRepairUserSentToAdmin = new javax.swing.JButton();
        jPanelAsking = new javax.swing.JPanel();
        jLabelAskingTextInJPanelAsking = new javax.swing.JLabel();
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

        jLabelNumberNotic.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jLabelNumberNotic.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNumberNotic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNumberNotic.setText("1");
        jPanelNotic.add(jLabelNumberNotic, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 20, 30));

        jLabelIconCircle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIconCircle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/circle.png"))); // NOI18N
        jPanelNotic.add(jLabelIconCircle, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, -6, 70, 60));

        jLabelIconNotic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIconNotic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/notifications-button.png"))); // NOI18N
        jPanelNotic.add(jLabelIconNotic, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanelRepairUserSentAdmin.add(jPanelNotic, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 730, 60));

        jPanelHead.setBackground(new java.awt.Color(13, 24, 35));
        jPanelHead.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHeadSociety.setBackground(new java.awt.Color(0, 0, 0));
        jLabelHeadSociety.setFont(new java.awt.Font("Leelawadee", 0, 28)); // NOI18N
        jLabelHeadSociety.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHeadSociety.setText("SOCIETY");
        jLabelHeadSociety.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelHead.add(jLabelHeadSociety, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 140, 50));

        jTextFieldSearchRepairUser.setBackground(new java.awt.Color(13, 24, 35));
        jTextFieldSearchRepairUser.setFont(new java.awt.Font("Leelawadee UI", 0, 20)); // NOI18N
        jTextFieldSearchRepairUser.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldSearchRepairUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSearchRepairUser.setText("Search");
        jTextFieldSearchRepairUser.setBorder(null);
        jTextFieldSearchRepairUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchRepairUserActionPerformed(evt);
            }
        });
        jPanelHead.add(jTextFieldSearchRepairUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 120, 30));
        jPanelHead.add(jSeparatorUnderSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 120, 40));

        jLabelIconSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIconSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/magnifying-glass.png"))); // NOI18N
        jPanelHead.add(jLabelIconSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 40, 40));

        jLabelHeadGreen.setBackground(new java.awt.Color(0, 0, 0));
        jLabelHeadGreen.setFont(new java.awt.Font("Leelawadee", 0, 28)); // NOI18N
        jLabelHeadGreen.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHeadGreen.setText("GREEN");
        jLabelHeadGreen.setToolTipText("");
        jLabelHeadGreen.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelHead.add(jLabelHeadGreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 130, 50));

        jPanelRepairUserSentAdmin.add(jPanelHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 860, 60));

        jPanelShowUser.setBackground(new java.awt.Color(19, 175, 248));
        jPanelShowUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNameSurNameUser.setBackground(new java.awt.Color(0, 0, 0));
        jLabelNameSurNameUser.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        jLabelNameSurNameUser.setForeground(new java.awt.Color(13, 24, 35));
        jLabelNameSurNameUser.setText("NAME  SURNAME");
        jLabelNameSurNameUser.setToolTipText("");
        jLabelNameSurNameUser.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelShowUser.add(jLabelNameSurNameUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 130, 50));

        jLabelPositionUser.setBackground(new java.awt.Color(0, 0, 0));
        jLabelPositionUser.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabelPositionUser.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPositionUser.setText("Student");
        jLabelPositionUser.setToolTipText("");
        jLabelPositionUser.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelShowUser.add(jLabelPositionUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 130, 50));

        jLabelIconUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIconUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/man.png"))); // NOI18N
        jPanelShowUser.add(jLabelIconUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanelRepairUserSentAdmin.add(jPanelShowUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 200, 60));

        jPanelBarBike.setBackground(new java.awt.Color(19, 175, 248));
        jPanelBarBike.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelMenuSideLigthBlue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMenuSideLigthBlue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/menu.png"))); // NOI18N
        jPanelBarBike.add(jLabelMenuSideLigthBlue, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 50, 30));

        jLabelBikeSideLigthBlue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBikeSideLigthBlue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/bike.png"))); // NOI18N
        jPanelBarBike.add(jLabelBikeSideLigthBlue, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 50, 40));

        jLabelUserSideLigthBlue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUserSideLigthBlue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/profile-user.png"))); // NOI18N
        jPanelBarBike.add(jLabelUserSideLigthBlue, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 50, -1));

        jPanelRepairUserSentAdmin.add(jPanelBarBike, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 540));

        jPanelDetailUserUnder.setBackground(new java.awt.Color(55, 200, 255));
        jPanelDetailUserUnder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelRepairUserSentAdmin.add(jPanelDetailUserUnder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 210, 60));

        jPanelSideBarUnder.setBackground(new java.awt.Color(55, 200, 255));
        jPanelSideBarUnder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelRepairUserSentAdmin.add(jPanelSideBarUnder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 540));

        jPanelSideBar.setBackground(new java.awt.Color(22, 31, 39));

        jPanelMenuSideBar.setBackground(new java.awt.Color(13, 24, 35));
        jPanelMenuSideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNew.setBackground(new java.awt.Color(0, 0, 0));
        jLabelNew.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabelNew.setForeground(new java.awt.Color(19, 175, 248));
        jLabelNew.setText("     News");
        jLabelNew.setToolTipText("");
        jLabelNew.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelMenuSideBar.add(jLabelNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 140, 20));

        jLabelBikeRepair.setBackground(new java.awt.Color(0, 0, 0));
        jLabelBikeRepair.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabelBikeRepair.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBikeRepair.setText("     Bike Repairing");
        jLabelBikeRepair.setToolTipText("");
        jLabelBikeRepair.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelMenuSideBar.add(jLabelBikeRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 140, 20));

        jLabelCanCounter.setBackground(new java.awt.Color(0, 0, 0));
        jLabelCanCounter.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabelCanCounter.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCanCounter.setText("     Can Counter");
        jLabelCanCounter.setToolTipText("");
        jLabelCanCounter.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelMenuSideBar.add(jLabelCanCounter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 140, 20));

        jLabelBikeSharing.setBackground(new java.awt.Color(0, 0, 0));
        jLabelBikeSharing.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabelBikeSharing.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBikeSharing.setText("     Bike Sharing");
        jLabelBikeSharing.setToolTipText("");
        jLabelBikeSharing.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelMenuSideBar.add(jLabelBikeSharing, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 140, 20));

        jLabelHistory.setBackground(new java.awt.Color(0, 0, 0));
        jLabelHistory.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabelHistory.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHistory.setText("     History");
        jLabelHistory.setToolTipText("");
        jLabelHistory.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelMenuSideBar.add(jLabelHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 140, 20));

        jLabelProfile.setBackground(new java.awt.Color(0, 0, 0));
        jLabelProfile.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabelProfile.setForeground(new java.awt.Color(255, 255, 255));
        jLabelProfile.setText("     PROFILE");
        jLabelProfile.setToolTipText("");
        jPanelMenuSideBar.add(jLabelProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 140, 30));

        jLabelTimer.setBackground(new java.awt.Color(0, 0, 0));
        jLabelTimer.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabelTimer.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTimer.setText("     Timer");
        jLabelTimer.setToolTipText("");
        jLabelTimer.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelMenuSideBar.add(jLabelTimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 140, 20));

        jLabelSupport.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSupport.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabelSupport.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSupport.setText("     Support");
        jLabelSupport.setToolTipText("");
        jLabelSupport.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelMenuSideBar.add(jLabelSupport, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 140, 20));

        jLabelMenu.setBackground(new java.awt.Color(0, 0, 0));
        jLabelMenu.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabelMenu.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMenu.setText("MENU");
        jLabelMenu.setToolTipText("");
        jPanelMenuSideBar.add(jLabelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 50, 30));

        jLabelSideDown.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSideDown.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabelSideDown.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSideDown.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSideDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/chevron-arrow-down.png"))); // NOI18N
        jLabelSideDown.setToolTipText("");
        jPanelMenuSideBar.add(jLabelSideDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 40, 30));

        jLabel24.setBackground(new java.awt.Color(0, 0, 0));
        jLabel24.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/circle_mini.png"))); // NOI18N
        jLabel24.setToolTipText("");
        jPanelMenuSideBar.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 40, 40));

        javax.swing.GroupLayout jPanelSideBarLayout = new javax.swing.GroupLayout(jPanelSideBar);
        jPanelSideBar.setLayout(jPanelSideBarLayout);
        jPanelSideBarLayout.setHorizontalGroup(
            jPanelSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
            .addGroup(jPanelSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSideBarLayout.createSequentialGroup()
                    .addComponent(jPanelMenuSideBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanelSideBarLayout.setVerticalGroup(
            jPanelSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
            .addGroup(jPanelSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanelMenuSideBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
        );

        jPanelRepairUserSentAdmin.add(jPanelSideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 150, 480));

        jPanelRepairUserFollowRepairing.setBackground(new java.awt.Color(25, 41, 65));
        jPanelRepairUserFollowRepairing.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelHeadBikeRepairing.setBackground(new java.awt.Color(76, 81, 86));
        jPanelHeadBikeRepairing.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBikeRepairing.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        jLabelBikeRepairing.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBikeRepairing.setText("Bike Repairing");
        jPanelHeadBikeRepairing.add(jLabelBikeRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 50));

        jPanelRepairUserFollowRepairing.add(jPanelHeadBikeRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 50));

        jLabelStatus.setBackground(new java.awt.Color(0, 0, 0));
        jLabelStatus.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        jLabelStatus.setForeground(new java.awt.Color(255, 255, 255));
        jLabelStatus.setText("Status");
        jLabelStatus.setToolTipText("");
        jLabelStatus.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelRepairUserFollowRepairing.add(jLabelStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 50, 30));

        jLabelIconAsking.setIcon(new javax.swing.ImageIcon("D:\\Homework\\INT105\\Project\\Project\\Bike\\png\\001-success-1.png")); // NOI18N
        jPanelRepairUserFollowRepairing.add(jLabelIconAsking, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 40, 40));

        jLabelAskingText.setBackground(new java.awt.Color(0, 0, 0));
        jLabelAskingText.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabelAskingText.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAskingText.setText("Asking");
        jLabelAskingText.setToolTipText("");
        jLabelAskingText.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelRepairUserFollowRepairing.add(jLabelAskingText, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 50, 30));

        jProgressBarAskingToRepairing.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelRepairUserFollowRepairing.add(jProgressBarAskingToRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 210, -1));

        jLabelIconRepairing.setIcon(new javax.swing.ImageIcon("D:\\Homework\\INT105\\Project\\Project\\Bike\\png\\001-success-1.png")); // NOI18N
        jPanelRepairUserFollowRepairing.add(jLabelIconRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 40, 40));

        jLabelRepairing.setBackground(new java.awt.Color(0, 0, 0));
        jLabelRepairing.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabelRepairing.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRepairing.setText("Repairing");
        jLabelRepairing.setToolTipText("");
        jLabelRepairing.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelRepairUserFollowRepairing.add(jLabelRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 70, 30));
        jPanelRepairUserFollowRepairing.add(jPBpercentRepairToRecieving, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 210, -1));

        jLabelIconRecieving.setIcon(new javax.swing.ImageIcon("D:\\Homework\\INT105\\Project\\Project\\Bike\\png\\001-success-1.png")); // NOI18N
        jPanelRepairUserFollowRepairing.add(jLabelIconRecieving, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 40, 40));

        jLabelRecieving.setBackground(new java.awt.Color(0, 0, 0));
        jLabelRecieving.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabelRecieving.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRecieving.setText("Recieving");
        jLabelRecieving.setToolTipText("");
        jLabelRecieving.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelRepairUserFollowRepairing.add(jLabelRecieving, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 70, 30));

        jPanelRecieving.setBackground(new java.awt.Color(190, 192, 184));
        jPanelRecieving.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelRecievingText.setBackground(new java.awt.Color(51, 51, 51));
        jLabelRecievingText.setFont(new java.awt.Font("Leelawadee", 1, 14)); // NOI18N
        jLabelRecievingText.setForeground(new java.awt.Color(51, 51, 51));
        jLabelRecievingText.setText("Recieving");
        jLabelRecievingText.setToolTipText("");
        jLabelRecievingText.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelRecieving.add(jLabelRecievingText, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, 30));

        textRecieving.setBackground(new java.awt.Color(0, 0, 0));
        textRecieving.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        textRecieving.setForeground(new java.awt.Color(51, 102, 255));
        textRecieving.setToolTipText("");
        textRecieving.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelRecieving.add(textRecieving, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 47, 119, 30));

        jLabelIconBikeInRecieving.setIcon(new javax.swing.ImageIcon("D:\\Homework\\INT105\\Project\\Project\\Bike\\png\\002-bicycle.png")); // NOI18N
        jPanelRecieving.add(jLabelIconBikeInRecieving, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 11, 74, 75));

        jPanelRepairUserFollowRepairing.add(jPanelRecieving, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 260, 100));

        jPanelRepairing.setBackground(new java.awt.Color(190, 192, 184));
        jPanelRepairing.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelRepairingText.setBackground(new java.awt.Color(0, 0, 0));
        jLabelRepairingText.setFont(new java.awt.Font("Leelawadee", 1, 14)); // NOI18N
        jLabelRepairingText.setForeground(new java.awt.Color(51, 51, 51));
        jLabelRepairingText.setText("Repairing");
        jLabelRepairingText.setToolTipText("");
        jLabelRepairingText.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelRepairing.add(jLabelRepairingText, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 70, 30));

        textRepair.setBackground(new java.awt.Color(0, 0, 0));
        textRepair.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        textRepair.setForeground(new java.awt.Color(51, 51, 51));
        textRepair.setToolTipText("");
        textRepair.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelRepairing.add(textRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 47, 184, 30));

        jPanelRepairUserFollowRepairing.add(jPanelRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 260, 100));

        jPnTimeDetail.setBackground(new java.awt.Color(190, 192, 184));
        jPnTimeDetail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
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

        jPanelAsking.setBackground(new java.awt.Color(190, 192, 184));
        jPanelAsking.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAskingTextInJPanelAsking.setBackground(new java.awt.Color(0, 0, 0));
        jLabelAskingTextInJPanelAsking.setFont(new java.awt.Font("Leelawadee", 1, 14)); // NOI18N
        jLabelAskingTextInJPanelAsking.setForeground(new java.awt.Color(51, 51, 51));
        jLabelAskingTextInJPanelAsking.setText("Asking");
        jLabelAskingTextInJPanelAsking.setToolTipText("");
        jLabelAskingTextInJPanelAsking.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelAsking.add(jLabelAskingTextInJPanelAsking, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 50, 30));

        textAsking.setBackground(new java.awt.Color(0, 0, 0));
        textAsking.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        textAsking.setForeground(new java.awt.Color(51, 51, 51));
        textAsking.setToolTipText("");
        textAsking.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelAsking.add(textAsking, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 47, 210, 30));

        jPanelRepairUserFollowRepairing.add(jPanelAsking, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 260, 100));

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

    private void jTextFieldSearchRepairUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchRepairUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchRepairUserActionPerformed

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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabelAskingText;
    private javax.swing.JLabel jLabelAskingTextInJPanelAsking;
    private javax.swing.JLabel jLabelBikeRepair;
    private javax.swing.JLabel jLabelBikeRepairing;
    private javax.swing.JLabel jLabelBikeSharing;
    private javax.swing.JLabel jLabelBikeSideLigthBlue;
    private javax.swing.JLabel jLabelBrandBike;
    private javax.swing.JLabel jLabelBrandBike1;
    private javax.swing.JLabel jLabelBrandBike2;
    private javax.swing.JLabel jLabelCanCounter;
    private javax.swing.JLabel jLabelHeadGreen;
    private javax.swing.JLabel jLabelHeadSociety;
    private javax.swing.JLabel jLabelHistory;
    private javax.swing.JLabel jLabelIconAsking;
    private javax.swing.JLabel jLabelIconBikeInRecieving;
    private javax.swing.JLabel jLabelIconCircle;
    private javax.swing.JLabel jLabelIconNotic;
    private javax.swing.JLabel jLabelIconRecieving;
    private javax.swing.JLabel jLabelIconRepairing;
    private javax.swing.JLabel jLabelIconSearch;
    private javax.swing.JLabel jLabelIconUser;
    private javax.swing.JLabel jLabelMenu;
    private javax.swing.JLabel jLabelMenuSideLigthBlue;
    private javax.swing.JLabel jLabelNameSurNameUser;
    private javax.swing.JLabel jLabelNew;
    private javax.swing.JLabel jLabelNumberNotic;
    private javax.swing.JLabel jLabelPositionUser;
    private javax.swing.JLabel jLabelProfile;
    private javax.swing.JLabel jLabelRecieving;
    private javax.swing.JLabel jLabelRecievingText;
    private javax.swing.JLabel jLabelRepairing;
    private javax.swing.JLabel jLabelRepairingText;
    private javax.swing.JLabel jLabelSideDown;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelSupport;
    private javax.swing.JLabel jLabelTimer;
    private javax.swing.JLabel jLabelUserSideLigthBlue;
    private javax.swing.JProgressBar jPBpercentRepairToRecieving;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanelAsking;
    private javax.swing.JPanel jPanelBarBike;
    private javax.swing.JPanel jPanelDetailUserUnder;
    private javax.swing.JPanel jPanelHead;
    private javax.swing.JPanel jPanelHeadBikeRepairing;
    private javax.swing.JPanel jPanelMenuSideBar;
    private javax.swing.JPanel jPanelNotic;
    private javax.swing.JPanel jPanelRecieving;
    private javax.swing.JPanel jPanelRepairUserFollowRepairing;
    private javax.swing.JPanel jPanelRepairUserSentAdmin;
    private javax.swing.JPanel jPanelRepairUserSentToAdmin;
    private javax.swing.JPanel jPanelRepairing;
    private javax.swing.JPanel jPanelRepairingIncrease1;
    private javax.swing.JPanel jPanelShowUser;
    private javax.swing.JPanel jPanelSideBar;
    private javax.swing.JPanel jPanelSideBarUnder;
    private javax.swing.JPanel jPnTimeDetail;
    private javax.swing.JProgressBar jProgressBarAskingToRepairing;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparatorUnderSearch;
    private javax.swing.JTextField jTFColor;
    private javax.swing.JTextField jTFWhat;
    private javax.swing.JTextField jTFbike;
    private javax.swing.JTextField jTextFieldSearchRepairUser;
    private javax.swing.JLabel textAsking;
    private javax.swing.JLabel textRecieving;
    private javax.swing.JLabel textRepair;
    // End of variables declaration//GEN-END:variables
    
    
}
