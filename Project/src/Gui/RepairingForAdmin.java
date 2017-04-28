/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

//import bike_gui.*;
import BikeRapair.Repair;
import ConnectDB.ConnectDatabase;
import Support.Support;
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
public class RepairingForAdmin extends javax.swing.JFrame {
    
    private Repair rp = new Repair();
    private int num;
    private String temp;
    private static RepairingForAdmin rping;
    public RepairingForUser rpUser;
    private String showTime;
    private long perpairID;
    private long transID;
    private int user;
    /**
     * Creates new form 
     */

    public RepairingForAdmin() {
        initComponents();
        jPanelRepairingAdmin.setVisible(true);
        connectDBForRepairAdmin();
        layerUser();
        jPanelRepairing.setVisible(false);
        jPanelRepairingShowTime.setVisible(false);
        jPanelShowRepair.setVisible(false);
        jPanelRepairingIncrease.setVisible(false);
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelSupport.setVisible(false);
    }

    public int[] notiTime() throws InterruptedException{//jPanel ShowTime
        int increase[]={0,0,0};
        int ans = JOptionPane.showConfirmDialog(this,"Time is running out.Do you want to add time?",
                "Warning",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(ans==JOptionPane.YES_OPTION){
            Object[] number = {0,1,2,3,4,5,6,7,8,9,10
                          ,11,12,13,14,15,16,17,18,19,20
                          ,21,22,23,24,25,26,27,28,29,30
                          ,31,32,33,34,35,36,37,38,39,40
                          ,41,42,43,44,45,46,47,48,49,50
                          ,51,52,53,54,55,56,57,58,59,60};
            JComboBox cbMin = new JComboBox(number);
            JComboBox cbHours = new JComboBox(number);
            JComboBox cbSec = new JComboBox(number);
            
            cbHours.setFont(new java.awt.Font("Leelawadee",0,18));
            cbMin.setFont(new java.awt.Font("Leelawadee",0,18));
            cbSec.setFont(new java.awt.Font("Leelawadee",0,18));
            
            JPanel popup = new JPanel(new GridLayout(0,1));
            popup.add(new JLabel("Hour"));
            popup.add(cbHours);
            popup.add(new JLabel("Minute"));
            popup.add(cbMin);
            popup.add(new JLabel("secound"));
            popup.add(cbSec);
            int result = JOptionPane.showConfirmDialog(null,popup,
                    "How much time you want to add?",JOptionPane.PLAIN_MESSAGE);
            increase[0]=(int) cbHours.getSelectedItem();
            increase[1]=(int) cbMin.getSelectedItem();
            increase[2]=(int) cbSec.getSelectedItem();
            rp.plusDay(increase[0], increase[1], increase[2]);
        }
        return increase;
    }
    
    public void layerUser(){//JPanel jPanelRepairingAdmin
        JPanel[] jp = new JPanel[num];
        JLabel[] name = new JLabel[num];
        JLabel[] surname = new JLabel[num];
        JLabel[] id = new JLabel[num];
        JLabel[] icon = new JLabel[num];
        JButton[] click = new JButton[num];
        for (int i = 0; i < num; i++) {
            int tem1 =i;
            jp[i] = new JPanel();
            jp[i].setBackground(new Color(240,240,240));
            jp[i].setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
            System.out.println(i);
            //----------------------------------------------------------------------------------------//
            name[i] = new JLabel();
            name[i].setFont(new java.awt.Font("Leelawadee",0,11));
            name[i].setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            name[i].setText("Name: ");//ใส่ชื่อของ User แต่ล่ะคน
            jp[i].add(name[i],new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));
            //----------------------------------------------------------------------------------------//
            surname[i] = new JLabel();
            surname[i].setFont(new java.awt.Font("Leelawadee",0,11));
            surname[i].setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            surname[i].setText("surname: ");//ใส่นามสกุลของ User แต่ล่ะคน
            jp[i].add(surname[i],new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));
            //----------------------------------------------------------------------------------------//
            id[i] = new JLabel();
            id[i].setFont(new java.awt.Font("Leelawadee",0,11));
            id[i].setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            id[i].setText("id: ");//ใส่ไอดีของ User แต่ล่ะคน
            jp[i].add(id[i],new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));
            //----------------------------------------------------------------------------------------//
            icon[i]= new JLabel();
            icon[i].setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            icon[i].setIcon(new javax.swing.ImageIcon("C:\\Users\\January\\Documents\\NetBeansProjects\\Project\\icon\\girl.png")); // NOI18N
            jp[i].add(icon[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 50, 50));
            //----------------------------------------------------------------------------------------//
            click[i] = new JButton();
            click[i].setText("Click");
            click[i].setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            click[i].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTclickActionPerformed(evt,tem1+1);
            }
            });
            jp[i].add(click[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, -1, -1));
            jPNBackGround.setLayout(new GridLayout(3, 3, 10, 10));
            jPNBackGround.add(jp[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 200, 110));
            getContentPane().add(jPanelHomeRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 540));
            
        }
    }
    
    private void jBTclickActionPerformed(java.awt.event.ActionEvent evt,int tem) {  //   click in  jPanelRepairingAdmin                                    
        // TODO add your handling code here:
        perpairID = tem; //เอามาจากที่ admin คลิกดูที่ user ส่งมา
        jPanelRepairAdminDetailUser.setVisible(true);
        temp = connectDBForRepairAdminDetail(tem);
        jPanelRepairingAdmin.setVisible(false);
        jPanelRepairing.setVisible(false);
        jPanelRepairingShowTime.setVisible(false);
        jPanelShowRepair.setVisible(false);
        jPanelRepairingIncrease.setVisible(false);
    }  
    
    public void connectDBForRepairAdmin(){ //for jPanelRepairingAdmin
        try{
            ConnectDatabase cndb = new ConnectDatabase();
            Connection connect = ConnectDatabase.connectDb("jan", "jan042");
            Class.forName("com.mysql.jdbc.Driver");
            
            Statement st = connect.createStatement(); 
            //ดึงเอา id ที่มาที่สุดออกมา เพื่อให้มันสามารถ insert ลง table ให้ไม่ซ้ำกันได้
            String temp = "SELECT MAX(id) AS countId FROM Prepair_Desctiption ";
            ResultSet rs = st.executeQuery(temp);

            while(rs.next()){
                this.num = rs.getInt("countId");
            }
        }   
        catch(ClassNotFoundException cfe){
            System.out.println(cfe);
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public String connectDBForRepairAdminDetail(int search){ //for support
        String detail="";
        try{
            ConnectDatabase cndb = new ConnectDatabase();
            Connection connect = ConnectDatabase.connectDb("jan", "jan042");
            Class.forName("com.mysql.jdbc.Driver");
      
            Statement st = connect.createStatement(); 
            //show detail user ส่งซ่อมจักรยาน
            String temp = "SELECT * FROM `Prepair_Desctiption` where id ="+search;
            ResultSet rs = st.executeQuery(temp);
            
            while(rs.next()){
                detail+="Id: "+rs.getInt("id")+"\n";
                detail+="Brand: "+rs.getString("brand")+"\n";
                detail+="Color: "+rs.getString("color")+"\n";
                detail+="Why Repair: "+rs.getString("other");
                transID = rs.getInt("transID");
            }
            
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
        return detail;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHomeRepair = new javax.swing.JPanel();
        jPanelNotic = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanelHead = new javax.swing.JPanel();
        jLabelSociety = new javax.swing.JLabel();
        jTFSearch = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabelGreen = new javax.swing.JLabel();
        jBTSearch = new javax.swing.JButton();
        jPanelShowUser = new javax.swing.JPanel();
        jLabelNameSurnameUser = new javax.swing.JLabel();
        jLabelPositionOfUser = new javax.swing.JLabel();
        jLabelPicOfUser = new javax.swing.JLabel();
        jPanelBarBike = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanelUserProfile = new javax.swing.JPanel();
        jPanelSideBarBike = new javax.swing.JPanel();
        jPanelSideBar = new javax.swing.JPanel();
        jPanelSideBarMenu = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jBtToSupport = new javax.swing.JButton();
        jBtToBikeRepair = new javax.swing.JButton();
        jPanelRepairingShowTime = new javax.swing.JPanel();
        jPanelShowTimeForRepairShowTime = new javax.swing.JPanel();
        jLabelIconTime = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAShowTime = new javax.swing.JTextArea();
        jBTStartTime = new javax.swing.JButton();
        jBTStopTime = new javax.swing.JButton();
        jBTAddDetailandTime = new javax.swing.JButton();
        jPanelHeadBikeRepairingForShowTime = new javax.swing.JPanel();
        jLabelBikeRepairingForShowTime = new javax.swing.JLabel();
        jButtonBackToRepairing = new javax.swing.JButton();
        jPanelRepairing = new javax.swing.JPanel();
        jBTnextToShowTime = new javax.swing.JButton();
        jPanelHeadBikeRepairing = new javax.swing.JPanel();
        jLabelBikeRepairingPageRepairing = new javax.swing.JLabel();
        jPanelSetDetailRepairForAdmin = new javax.swing.JPanel();
        jLabelProblem = new javax.swing.JLabel();
        jTFProblem = new javax.swing.JTextField();
        jSeparatorUnderProblem = new javax.swing.JSeparator();
        jTFDetail = new javax.swing.JTextField();
        jSeparatorUnderDetail = new javax.swing.JSeparator();
        jLabelDetail = new javax.swing.JLabel();
        jPanelSetTimeForAdmin = new javax.swing.JPanel();
        jLabelIconTimeForAdmin = new javax.swing.JLabel();
        jLabelSetTimeForAdmin = new javax.swing.JLabel();
        jLabelSetHourForAdmin = new javax.swing.JLabel();
        jCBoxHour = new javax.swing.JComboBox<>();
        jLabelSetMinuteForAdmin = new javax.swing.JLabel();
        jCBoxMinute = new javax.swing.JComboBox<>();
        jButtonBackAdmindetailUser = new javax.swing.JButton();
        jPanelRepairAdminDetailUser = new javax.swing.JPanel();
        jPanelHeadBikeRepairingForAdminDetailUser = new javax.swing.JPanel();
        jLabelBikeRepairingForAdminDetailUser = new javax.swing.JLabel();
        jPanelShoeUserSentToAdminForRepair = new javax.swing.JPanel();
        jLabelStatusBikeUserSentTOadmin = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTAShowDetaiUserSentRepirlForAdmin = new javax.swing.JTextArea();
        jBTbackToJPanelRepairAdmin = new javax.swing.JButton();
        jBTnextTojPanelRepairing = new javax.swing.JButton();
        jPanelRepairingAdmin = new javax.swing.JPanel();
        jPanelHeadBikeRepairForRepairForAdmin = new javax.swing.JPanel();
        jLabelBikeRepairingForRepairingAdmin = new javax.swing.JLabel();
        jPNBackGround = new javax.swing.JPanel();
        jPanelShowRepair = new javax.swing.JPanel();
        jScrollPaneShowDetail = new javax.swing.JScrollPane();
        jTAShowDetail = new javax.swing.JTextArea();
        jBTNextBactToIndexAdmin = new javax.swing.JButton();
        jBTBackToRepairShowTime = new javax.swing.JButton();
        jPanelHearBikeRepairForBikeRepair = new javax.swing.JPanel();
        jLabelBikeRepairForShoeDetail = new javax.swing.JLabel();
        jPanelRepairingIncrease = new javax.swing.JPanel();
        jPanelAddRepairForIncreaseProblemAndTime = new javax.swing.JPanel();
        jLabelProblemForIncrease = new javax.swing.JLabel();
        jTFAskingRepairIncrease = new javax.swing.JTextField();
        jSeparatorUnderProblemForAdminIncrease = new javax.swing.JSeparator();
        jTFRepairingRepairIncrease = new javax.swing.JTextField();
        jSeparatorUnderDetailFforAdminIncrease = new javax.swing.JSeparator();
        jLabelDetailForIncrease = new javax.swing.JLabel();
        jPanelForAddTimeAdminIncrease = new javax.swing.JPanel();
        jLabelIconTimeForIncrease = new javax.swing.JLabel();
        jLabelSetTimeForIncrease = new javax.swing.JLabel();
        jLabelSetHoursIncrease = new javax.swing.JLabel();
        jCBoxHourRepairIncrease = new javax.swing.JComboBox<>();
        jLabelSetMinuteIncreease = new javax.swing.JLabel();
        jCBoxMinuteRepairIncrease = new javax.swing.JComboBox<>();
        jBTnextInBileIncrease = new javax.swing.JButton();
        jPanelHeadBikeRepairingForIncrease = new javax.swing.JPanel();
        jLabelBikeRepairingForIncrease = new javax.swing.JLabel();
        jPanelSupport = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabelBikeRepair = new javax.swing.JLabel();
        jLBShowingResult = new javax.swing.JLabel();
        jLBWhatsearch = new javax.swing.JLabel();
        jSeparatorUnderForResult = new javax.swing.JSeparator();
        jLabelContact = new javax.swing.JLabel();
        jLabeliconContact = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTAShowyouSearch = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTAContact = new javax.swing.JTextArea();
        jBTBackToRepair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelHomeRepair.setBackground(new java.awt.Color(25, 41, 65));
        jPanelHomeRepair.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/circle.png"))); // NOI18N
        jPanelNotic.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, -6, 70, 60));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/notifications-button.png"))); // NOI18N
        jPanelNotic.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanelHomeRepair.add(jPanelNotic, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 730, 60));

        jPanelHead.setBackground(new java.awt.Color(13, 24, 35));
        jPanelHead.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelSociety.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSociety.setFont(new java.awt.Font("Leelawadee", 0, 28)); // NOI18N
        jLabelSociety.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSociety.setText("SOCIETY");
        jLabelSociety.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelHead.add(jLabelSociety, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 140, 50));

        jTFSearch.setBackground(new java.awt.Color(13, 24, 35));
        jTFSearch.setFont(new java.awt.Font("Leelawadee UI", 0, 20)); // NOI18N
        jTFSearch.setForeground(new java.awt.Color(255, 255, 255));
        jTFSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTFSearch.setText("Search");
        jTFSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTFSearchFocusGained(evt);
            }
        });
        jTFSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFSearchActionPerformed(evt);
            }
        });
        jPanelHead.add(jTFSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 120, 30));
        jPanelHead.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 120, 40));

        jLabelGreen.setBackground(new java.awt.Color(0, 0, 0));
        jLabelGreen.setFont(new java.awt.Font("Leelawadee", 0, 28)); // NOI18N
        jLabelGreen.setForeground(new java.awt.Color(255, 255, 255));
        jLabelGreen.setText("GREEN");
        jLabelGreen.setToolTipText("");
        jLabelGreen.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelHead.add(jLabelGreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 130, 50));

        jBTSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/magnifying-glass.png"))); // NOI18N
        jBTSearch.setContentAreaFilled(false);
        jBTSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTSearchActionPerformed(evt);
            }
        });
        jPanelHead.add(jBTSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, -1, -1));

        jPanelHomeRepair.add(jPanelHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 860, 60));

        jPanelShowUser.setBackground(new java.awt.Color(19, 175, 248));
        jPanelShowUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNameSurnameUser.setBackground(new java.awt.Color(0, 0, 0));
        jLabelNameSurnameUser.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        jLabelNameSurnameUser.setForeground(new java.awt.Color(13, 24, 35));
        jLabelNameSurnameUser.setText("NAME  SURNAME");
        jLabelNameSurnameUser.setToolTipText("");
        jLabelNameSurnameUser.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelShowUser.add(jLabelNameSurnameUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 130, 50));

        jLabelPositionOfUser.setBackground(new java.awt.Color(0, 0, 0));
        jLabelPositionOfUser.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabelPositionOfUser.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPositionOfUser.setText("Student");
        jLabelPositionOfUser.setToolTipText("");
        jLabelPositionOfUser.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelShowUser.add(jLabelPositionOfUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 130, 50));

        jLabelPicOfUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPicOfUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/man.png"))); // NOI18N
        jPanelShowUser.add(jLabelPicOfUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        jPanelHomeRepair.add(jPanelShowUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 200, 60));

        jPanelBarBike.setBackground(new java.awt.Color(19, 175, 248));
        jPanelBarBike.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/menu.png"))); // NOI18N
        jPanelBarBike.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 50, 30));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/bike.png"))); // NOI18N
        jPanelBarBike.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 50, 40));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/profile-user.png"))); // NOI18N
        jPanelBarBike.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 50, -1));

        jPanelHomeRepair.add(jPanelBarBike, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 540));

        jPanelUserProfile.setBackground(new java.awt.Color(55, 200, 255));
        jPanelUserProfile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelHomeRepair.add(jPanelUserProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 210, 60));

        jPanelSideBarBike.setBackground(new java.awt.Color(55, 200, 255));
        jPanelSideBarBike.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelHomeRepair.add(jPanelSideBarBike, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 540));

        jPanelSideBar.setBackground(new java.awt.Color(22, 31, 39));

        jPanelSideBarMenu.setBackground(new java.awt.Color(13, 24, 35));
        jPanelSideBarMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(19, 175, 248));
        jLabel14.setText("     News");
        jLabel14.setToolTipText("");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSideBarMenu.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 140, 20));

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("     Can Counter");
        jLabel17.setToolTipText("");
        jLabel17.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSideBarMenu.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 140, 20));

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("     Bike Sharing");
        jLabel18.setToolTipText("");
        jLabel18.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSideBarMenu.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 140, 20));

        jLabel20.setBackground(new java.awt.Color(0, 0, 0));
        jLabel20.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("     History");
        jLabel20.setToolTipText("");
        jLabel20.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSideBarMenu.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 140, 20));

        jLabel21.setBackground(new java.awt.Color(0, 0, 0));
        jLabel21.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("     PROFILE");
        jLabel21.setToolTipText("");
        jPanelSideBarMenu.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 140, 30));

        jLabel22.setBackground(new java.awt.Color(0, 0, 0));
        jLabel22.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("     Timer");
        jLabel22.setToolTipText("");
        jLabel22.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSideBarMenu.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 140, 20));

        jLabel19.setBackground(new java.awt.Color(0, 0, 0));
        jLabel19.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("MENU");
        jLabel19.setToolTipText("");
        jPanelSideBarMenu.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 50, 30));

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/chevron-arrow-down.png"))); // NOI18N
        jLabel15.setToolTipText("");
        jPanelSideBarMenu.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 40, 30));

        jLabel24.setBackground(new java.awt.Color(0, 0, 0));
        jLabel24.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gui/circle_mini.png"))); // NOI18N
        jLabel24.setToolTipText("");
        jPanelSideBarMenu.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 40, 40));

        jBtToSupport.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jBtToSupport.setForeground(new java.awt.Color(255, 255, 255));
        jBtToSupport.setText("Support");
        jBtToSupport.setBorder(null);
        jBtToSupport.setContentAreaFilled(false);
        jBtToSupport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtToSupportActionPerformed(evt);
            }
        });
        jPanelSideBarMenu.add(jBtToSupport, new org.netbeans.lib.awtextra.AbsoluteConstraints(-70, 290, 220, -1));

        jBtToBikeRepair.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jBtToBikeRepair.setForeground(new java.awt.Color(255, 255, 255));
        jBtToBikeRepair.setText("     Bike Repairing");
        jBtToBikeRepair.setBorder(null);
        jBtToBikeRepair.setContentAreaFilled(false);
        jBtToBikeRepair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtToBikeRepairActionPerformed(evt);
            }
        });
        jPanelSideBarMenu.add(jBtToBikeRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(-60, 140, 220, -1));

        javax.swing.GroupLayout jPanelSideBarLayout = new javax.swing.GroupLayout(jPanelSideBar);
        jPanelSideBar.setLayout(jPanelSideBarLayout);
        jPanelSideBarLayout.setHorizontalGroup(
            jPanelSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
            .addGroup(jPanelSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSideBarLayout.createSequentialGroup()
                    .addComponent(jPanelSideBarMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanelSideBarLayout.setVerticalGroup(
            jPanelSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
            .addGroup(jPanelSideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanelSideBarMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelHomeRepair.add(jPanelSideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 150, 480));

        jPanelRepairingShowTime.setBackground(new java.awt.Color(25, 41, 65));
        jPanelRepairingShowTime.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelShowTimeForRepairShowTime.setBackground(new java.awt.Color(62, 64, 57));
        jPanelShowTimeForRepairShowTime.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        jPanelShowTimeForRepairShowTime.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelIconTime.setBackground(new java.awt.Color(0, 0, 0));
        jLabelIconTime.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabelIconTime.setForeground(new java.awt.Color(255, 255, 255));
        jLabelIconTime.setIcon(new javax.swing.ImageIcon("C:\\Users\\January\\Documents\\NetBeansProjects\\Project\\icon\\alarm-clock.png")); // NOI18N
        jLabelIconTime.setToolTipText("");
        jLabelIconTime.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelShowTimeForRepairShowTime.add(jLabelIconTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 70, 70));

        jTAShowTime.setBackground(new java.awt.Color(62, 64, 57));
        jTAShowTime.setColumns(20);
        jTAShowTime.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jTAShowTime.setForeground(new java.awt.Color(255, 255, 255));
        jTAShowTime.setRows(5);
        jTAShowTime.setBorder(null);
        jScrollPane1.setViewportView(jTAShowTime);

        jPanelShowTimeForRepairShowTime.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 480, 120));

        jPanelRepairingShowTime.add(jPanelShowTimeForRepairShowTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 580, 120));

        jBTStartTime.setBackground(new java.awt.Color(25, 41, 65));
        jBTStartTime.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jBTStartTime.setForeground(new java.awt.Color(255, 255, 255));
        jBTStartTime.setText("START");
        jBTStartTime.setToolTipText("");
        jBTStartTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTStartTimeActionPerformed(evt);
            }
        });
        jPanelRepairingShowTime.add(jBTStartTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 110, 40));

        jBTStopTime.setBackground(new java.awt.Color(25, 41, 65));
        jBTStopTime.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jBTStopTime.setForeground(new java.awt.Color(255, 255, 255));
        jBTStopTime.setText("STOP");
        jBTStopTime.setToolTipText("");
        jBTStopTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTStopTimeActionPerformed(evt);
            }
        });
        jPanelRepairingShowTime.add(jBTStopTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 110, 40));

        jBTAddDetailandTime.setBackground(new java.awt.Color(25, 41, 65));
        jBTAddDetailandTime.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jBTAddDetailandTime.setForeground(new java.awt.Color(255, 255, 255));
        jBTAddDetailandTime.setText("Add Detail&Time");
        jBTAddDetailandTime.setToolTipText("");
        jBTAddDetailandTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTAddDetailandTimeActionPerformed(evt);
            }
        });
        jPanelRepairingShowTime.add(jBTAddDetailandTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 290, 140, 40));

        jPanelHeadBikeRepairingForShowTime.setBackground(new java.awt.Color(76, 81, 86));
        jPanelHeadBikeRepairingForShowTime.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBikeRepairingForShowTime.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        jLabelBikeRepairingForShowTime.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBikeRepairingForShowTime.setText("Bike Repairing");
        jPanelHeadBikeRepairingForShowTime.add(jLabelBikeRepairingForShowTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 50));

        jPanelRepairingShowTime.add(jPanelHeadBikeRepairingForShowTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 50));

        jButtonBackToRepairing.setIcon(new javax.swing.ImageIcon("D:\\ProjectConnectGit\\Project\\icon\\left-arrow.png")); // NOI18N
        jButtonBackToRepairing.setContentAreaFilled(false);
        jButtonBackToRepairing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackToRepairingActionPerformed(evt);
            }
        });
        jPanelRepairingShowTime.add(jButtonBackToRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));

        jPanelHomeRepair.add(jPanelRepairingShowTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 730, 420));

        jPanelRepairing.setBackground(new java.awt.Color(25, 41, 65));
        jPanelRepairing.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBTnextToShowTime.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jBTnextToShowTime.setIcon(new javax.swing.ImageIcon("C:\\Users\\January\\Documents\\NetBeansProjects\\Project\\icon\\right-arrow.png")); // NOI18N
        jBTnextToShowTime.setContentAreaFilled(false);
        jBTnextToShowTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTnextToShowTimeActionPerformed(evt);
            }
        });
        jPanelRepairing.add(jBTnextToShowTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 350, 90, 50));

        jPanelHeadBikeRepairing.setBackground(new java.awt.Color(76, 81, 86));
        jPanelHeadBikeRepairing.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBikeRepairingPageRepairing.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        jLabelBikeRepairingPageRepairing.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBikeRepairingPageRepairing.setText("Bike Repairing");
        jPanelHeadBikeRepairing.add(jLabelBikeRepairingPageRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 50));

        jPanelRepairing.add(jPanelHeadBikeRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 50));

        jPanelSetDetailRepairForAdmin.setBackground(new java.awt.Color(190, 192, 184));
        jPanelSetDetailRepairForAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelProblem.setBackground(new java.awt.Color(0, 0, 0));
        jLabelProblem.setFont(new java.awt.Font("Leelawadee", 1, 14)); // NOI18N
        jLabelProblem.setForeground(new java.awt.Color(51, 51, 51));
        jLabelProblem.setText("Problem");
        jLabelProblem.setToolTipText("");
        jLabelProblem.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSetDetailRepairForAdmin.add(jLabelProblem, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 25));

        jTFProblem.setBackground(new java.awt.Color(190, 192, 184));
        jTFProblem.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jTFProblem.setBorder(null);
        jPanelSetDetailRepairForAdmin.add(jTFProblem, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 300, 31));
        jPanelSetDetailRepairForAdmin.add(jSeparatorUnderProblem, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 300, 10));

        jTFDetail.setBackground(new java.awt.Color(190, 192, 184));
        jTFDetail.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jTFDetail.setBorder(null);
        jPanelSetDetailRepairForAdmin.add(jTFDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 300, 28));
        jPanelSetDetailRepairForAdmin.add(jSeparatorUnderDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 300, 23));

        jLabelDetail.setBackground(new java.awt.Color(0, 0, 0));
        jLabelDetail.setFont(new java.awt.Font("Leelawadee", 1, 14)); // NOI18N
        jLabelDetail.setForeground(new java.awt.Color(51, 51, 51));
        jLabelDetail.setText("Detail");
        jLabelDetail.setToolTipText("");
        jLabelDetail.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSetDetailRepairForAdmin.add(jLabelDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 54, 70, 30));

        jPanelRepairing.add(jPanelSetDetailRepairForAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 580, 110));

        jPanelSetTimeForAdmin.setBackground(new java.awt.Color(56, 54, 54));
        jPanelSetTimeForAdmin.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        jPanelSetTimeForAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelIconTimeForAdmin.setBackground(new java.awt.Color(0, 0, 0));
        jLabelIconTimeForAdmin.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabelIconTimeForAdmin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelIconTimeForAdmin.setIcon(new javax.swing.ImageIcon("C:\\Users\\January\\Documents\\NetBeansProjects\\Project\\icon\\alarm-clock.png")); // NOI18N
        jLabelIconTimeForAdmin.setToolTipText("");
        jLabelIconTimeForAdmin.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSetTimeForAdmin.add(jLabelIconTimeForAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 70, 70));

        jLabelSetTimeForAdmin.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSetTimeForAdmin.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabelSetTimeForAdmin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSetTimeForAdmin.setText("Set Time");
        jLabelSetTimeForAdmin.setToolTipText("");
        jLabelSetTimeForAdmin.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSetTimeForAdmin.add(jLabelSetTimeForAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 80, 30));

        jLabelSetHourForAdmin.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSetHourForAdmin.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabelSetHourForAdmin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSetHourForAdmin.setText("Hours");
        jLabelSetHourForAdmin.setToolTipText("");
        jLabelSetHourForAdmin.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSetTimeForAdmin.add(jLabelSetHourForAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 50, 30));

        jCBoxHour.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jCBoxHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        jPanelSetTimeForAdmin.add(jCBoxHour, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 50, -1));

        jLabelSetMinuteForAdmin.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSetMinuteForAdmin.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabelSetMinuteForAdmin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSetMinuteForAdmin.setText("Minute");
        jLabelSetMinuteForAdmin.setToolTipText("");
        jLabelSetMinuteForAdmin.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSetTimeForAdmin.add(jLabelSetMinuteForAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 50, 30));

        jCBoxMinute.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jCBoxMinute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        jPanelSetTimeForAdmin.add(jCBoxMinute, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 50, -1));

        jPanelRepairing.add(jPanelSetTimeForAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 580, 120));

        jButtonBackAdmindetailUser.setIcon(new javax.swing.ImageIcon("D:\\ProjectConnectGit\\Project\\icon\\left-arrow.png")); // NOI18N
        jButtonBackAdmindetailUser.setContentAreaFilled(false);
        jButtonBackAdmindetailUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackAdmindetailUserActionPerformed(evt);
            }
        });
        jPanelRepairing.add(jButtonBackAdmindetailUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, -1, -1));

        jPanelHomeRepair.add(jPanelRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 730, 420));

        jPanelRepairAdminDetailUser.setBackground(new java.awt.Color(25, 41, 65));
        jPanelRepairAdminDetailUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelHeadBikeRepairingForAdminDetailUser.setBackground(new java.awt.Color(76, 81, 86));
        jPanelHeadBikeRepairingForAdminDetailUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBikeRepairingForAdminDetailUser.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        jLabelBikeRepairingForAdminDetailUser.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBikeRepairingForAdminDetailUser.setText("Bike Repairing");
        jPanelHeadBikeRepairingForAdminDetailUser.add(jLabelBikeRepairingForAdminDetailUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 50));

        jPanelRepairAdminDetailUser.add(jPanelHeadBikeRepairingForAdminDetailUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 50));

        jPanelShoeUserSentToAdminForRepair.setBackground(new java.awt.Color(34, 34, 38));
        jPanelShoeUserSentToAdminForRepair.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelStatusBikeUserSentTOadmin.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        jLabelStatusBikeUserSentTOadmin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelStatusBikeUserSentTOadmin.setText("Status bicycle ");
        jPanelShoeUserSentToAdminForRepair.add(jLabelStatusBikeUserSentTOadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 120, 40));

        jComboBox1.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Yes" }));
        jPanelShoeUserSentToAdminForRepair.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 70, 30));

        jTAShowDetaiUserSentRepirlForAdmin.setBackground(new java.awt.Color(54, 54, 56));
        jTAShowDetaiUserSentRepirlForAdmin.setColumns(20);
        jTAShowDetaiUserSentRepirlForAdmin.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jTAShowDetaiUserSentRepirlForAdmin.setForeground(new java.awt.Color(255, 255, 255));
        jTAShowDetaiUserSentRepirlForAdmin.setRows(5);
        jTAShowDetaiUserSentRepirlForAdmin.setBorder(null);
        jTAShowDetaiUserSentRepirlForAdmin.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTAShowDetaiUserSentRepirlForAdminAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane3.setViewportView(jTAShowDetaiUserSentRepirlForAdmin);

        jPanelShoeUserSentToAdminForRepair.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 530, 130));

        jPanelRepairAdminDetailUser.add(jPanelShoeUserSentToAdminForRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 600, 250));

        jBTbackToJPanelRepairAdmin.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jBTbackToJPanelRepairAdmin.setIcon(new javax.swing.ImageIcon("D:\\ProjectConnectGit\\Project\\icon\\left-arrow.png")); // NOI18N
        jBTbackToJPanelRepairAdmin.setContentAreaFilled(false);
        jBTbackToJPanelRepairAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTbackToJPanelRepairAdminActionPerformed(evt);
            }
        });
        jPanelRepairAdminDetailUser.add(jBTbackToJPanelRepairAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 90, 50));

        jBTnextTojPanelRepairing.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jBTnextTojPanelRepairing.setIcon(new javax.swing.ImageIcon("D:\\ProjectConnectGit\\Project\\icon\\right-arrow.png")); // NOI18N
        jBTnextTojPanelRepairing.setContentAreaFilled(false);
        jBTnextTojPanelRepairing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTnextTojPanelRepairingActionPerformed(evt);
            }
        });
        jPanelRepairAdminDetailUser.add(jBTnextTojPanelRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 360, 90, 50));

        jPanelHomeRepair.add(jPanelRepairAdminDetailUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 730, 420));

        jPanelRepairingAdmin.setBackground(new java.awt.Color(25, 41, 65));
        jPanelRepairingAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelHeadBikeRepairForRepairForAdmin.setBackground(new java.awt.Color(76, 81, 86));
        jPanelHeadBikeRepairForRepairForAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBikeRepairingForRepairingAdmin.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        jLabelBikeRepairingForRepairingAdmin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBikeRepairingForRepairingAdmin.setText("Bike Repairing");
        jPanelHeadBikeRepairForRepairForAdmin.add(jLabelBikeRepairingForRepairingAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 50));

        jPanelRepairingAdmin.add(jPanelHeadBikeRepairForRepairForAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 50));

        jPNBackGround.setBackground(new java.awt.Color(25, 41, 65));
        jPNBackGround.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelRepairingAdmin.add(jPNBackGround, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 730, 370));

        jPanelHomeRepair.add(jPanelRepairingAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 730, 420));

        jPanelShowRepair.setBackground(new java.awt.Color(25, 41, 65));
        jPanelShowRepair.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPaneShowDetail.setBorder(null);

        jTAShowDetail.setEditable(false);
        jTAShowDetail.setBackground(new java.awt.Color(56, 54, 54));
        jTAShowDetail.setColumns(20);
        jTAShowDetail.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        jTAShowDetail.setForeground(new java.awt.Color(255, 255, 255));
        jTAShowDetail.setRows(5);
        jScrollPaneShowDetail.setViewportView(jTAShowDetail);

        jPanelShowRepair.add(jScrollPaneShowDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 490, 240));

        jBTNextBactToIndexAdmin.setIcon(new javax.swing.ImageIcon("C:\\Users\\January\\Documents\\NetBeansProjects\\Project\\icon\\right-arrow.png")); // NOI18N
        jBTNextBactToIndexAdmin.setContentAreaFilled(false);
        jBTNextBactToIndexAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTNextBactToIndexAdminActionPerformed(evt);
            }
        });
        jPanelShowRepair.add(jBTNextBactToIndexAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, -1, -1));

        jBTBackToRepairShowTime.setIcon(new javax.swing.ImageIcon("C:\\Users\\January\\Documents\\NetBeansProjects\\Project\\icon\\left-arrow.png")); // NOI18N
        jBTBackToRepairShowTime.setContentAreaFilled(false);
        jBTBackToRepairShowTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTBackToRepairShowTimeActionPerformed(evt);
            }
        });
        jPanelShowRepair.add(jBTBackToRepairShowTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));

        jPanelHearBikeRepairForBikeRepair.setBackground(new java.awt.Color(76, 81, 86));
        jPanelHearBikeRepairForBikeRepair.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBikeRepairForShoeDetail.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        jLabelBikeRepairForShoeDetail.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBikeRepairForShoeDetail.setText("Bike Repairing");
        jPanelHearBikeRepairForBikeRepair.add(jLabelBikeRepairForShoeDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 50));

        jPanelShowRepair.add(jPanelHearBikeRepairForBikeRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 50));

        jPanelHomeRepair.add(jPanelShowRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 730, 420));

        jPanelRepairingIncrease.setBackground(new java.awt.Color(25, 41, 65));
        jPanelRepairingIncrease.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelAddRepairForIncreaseProblemAndTime.setBackground(new java.awt.Color(190, 192, 184));
        jPanelAddRepairForIncreaseProblemAndTime.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelProblemForIncrease.setBackground(new java.awt.Color(0, 0, 0));
        jLabelProblemForIncrease.setFont(new java.awt.Font("Leelawadee", 1, 14)); // NOI18N
        jLabelProblemForIncrease.setForeground(new java.awt.Color(51, 51, 51));
        jLabelProblemForIncrease.setText("Problem");
        jLabelProblemForIncrease.setToolTipText("");
        jLabelProblemForIncrease.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelAddRepairForIncreaseProblemAndTime.add(jLabelProblemForIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 60, 25));

        jTFAskingRepairIncrease.setBackground(new java.awt.Color(190, 192, 184));
        jTFAskingRepairIncrease.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jTFAskingRepairIncrease.setBorder(null);
        jTFAskingRepairIncrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFAskingRepairIncreaseActionPerformed(evt);
            }
        });
        jPanelAddRepairForIncreaseProblemAndTime.add(jTFAskingRepairIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 0, 300, 31));
        jPanelAddRepairForIncreaseProblemAndTime.add(jSeparatorUnderProblemForAdminIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 31, 300, 10));

        jTFRepairingRepairIncrease.setBackground(new java.awt.Color(190, 192, 184));
        jTFRepairingRepairIncrease.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jTFRepairingRepairIncrease.setBorder(null);
        jTFRepairingRepairIncrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFRepairingRepairIncreaseActionPerformed(evt);
            }
        });
        jPanelAddRepairForIncreaseProblemAndTime.add(jTFRepairingRepairIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 290, 28));
        jPanelAddRepairForIncreaseProblemAndTime.add(jSeparatorUnderDetailFforAdminIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 290, 23));

        jLabelDetailForIncrease.setBackground(new java.awt.Color(0, 0, 0));
        jLabelDetailForIncrease.setFont(new java.awt.Font("Leelawadee", 1, 14)); // NOI18N
        jLabelDetailForIncrease.setForeground(new java.awt.Color(51, 51, 51));
        jLabelDetailForIncrease.setText("Detail");
        jLabelDetailForIncrease.setToolTipText("");
        jLabelDetailForIncrease.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelAddRepairForIncreaseProblemAndTime.add(jLabelDetailForIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 54, 70, 30));

        jPanelRepairingIncrease.add(jPanelAddRepairForIncreaseProblemAndTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 580, 110));

        jPanelForAddTimeAdminIncrease.setBackground(new java.awt.Color(51, 51, 51));
        jPanelForAddTimeAdminIncrease.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        jPanelForAddTimeAdminIncrease.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelIconTimeForIncrease.setBackground(new java.awt.Color(0, 0, 0));
        jLabelIconTimeForIncrease.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabelIconTimeForIncrease.setForeground(new java.awt.Color(255, 255, 255));
        jLabelIconTimeForIncrease.setIcon(new javax.swing.ImageIcon("C:\\Users\\January\\Documents\\NetBeansProjects\\Project\\icon\\alarm-clock.png")); // NOI18N
        jLabelIconTimeForIncrease.setToolTipText("");
        jLabelIconTimeForIncrease.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelForAddTimeAdminIncrease.add(jLabelIconTimeForIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 70, 70));

        jLabelSetTimeForIncrease.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSetTimeForIncrease.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabelSetTimeForIncrease.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSetTimeForIncrease.setText("Set Time");
        jLabelSetTimeForIncrease.setToolTipText("");
        jLabelSetTimeForIncrease.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelForAddTimeAdminIncrease.add(jLabelSetTimeForIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 30));

        jLabelSetHoursIncrease.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSetHoursIncrease.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabelSetHoursIncrease.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSetHoursIncrease.setText("Hours");
        jLabelSetHoursIncrease.setToolTipText("");
        jLabelSetHoursIncrease.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelForAddTimeAdminIncrease.add(jLabelSetHoursIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 50, 30));

        jCBoxHourRepairIncrease.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jCBoxHourRepairIncrease.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        jPanelForAddTimeAdminIncrease.add(jCBoxHourRepairIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 50, -1));

        jLabelSetMinuteIncreease.setBackground(new java.awt.Color(0, 0, 0));
        jLabelSetMinuteIncreease.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabelSetMinuteIncreease.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSetMinuteIncreease.setText("Minute");
        jLabelSetMinuteIncreease.setToolTipText("");
        jLabelSetMinuteIncreease.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelForAddTimeAdminIncrease.add(jLabelSetMinuteIncreease, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 50, 30));

        jCBoxMinuteRepairIncrease.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jCBoxMinuteRepairIncrease.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        jPanelForAddTimeAdminIncrease.add(jCBoxMinuteRepairIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 50, -1));

        jPanelRepairingIncrease.add(jPanelForAddTimeAdminIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 580, 120));

        jBTnextInBileIncrease.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jBTnextInBileIncrease.setIcon(new javax.swing.ImageIcon("C:\\Users\\January\\Documents\\NetBeansProjects\\Project\\icon\\right-arrow.png")); // NOI18N
        jBTnextInBileIncrease.setContentAreaFilled(false);
        jBTnextInBileIncrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTnextInBileIncreaseActionPerformed(evt);
            }
        });
        jPanelRepairingIncrease.add(jBTnextInBileIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 370, 90, 50));

        jPanelHeadBikeRepairingForIncrease.setBackground(new java.awt.Color(76, 81, 86));
        jPanelHeadBikeRepairingForIncrease.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBikeRepairingForIncrease.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        jLabelBikeRepairingForIncrease.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBikeRepairingForIncrease.setText("Bike Repairing");
        jPanelHeadBikeRepairingForIncrease.add(jLabelBikeRepairingForIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 50));

        jPanelRepairingIncrease.add(jPanelHeadBikeRepairingForIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 50));

        jPanelHomeRepair.add(jPanelRepairingIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 730, 420));

        jPanelSupport.setBackground(new java.awt.Color(25, 41, 65));
        jPanelSupport.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel22.setBackground(new java.awt.Color(76, 81, 86));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBikeRepair.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        jLabelBikeRepair.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBikeRepair.setText("Support");
        jPanel22.add(jLabelBikeRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 50));

        jPanelSupport.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 50));

        jLBShowingResult.setBackground(new java.awt.Color(255, 255, 255));
        jLBShowingResult.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLBShowingResult.setForeground(new java.awt.Color(255, 255, 255));
        jLBShowingResult.setText("Showing results for:");
        jPanelSupport.add(jLBShowingResult, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 170, 30));

        jLBWhatsearch.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLBWhatsearch.setForeground(new java.awt.Color(255, 255, 255));
        jPanelSupport.add(jLBWhatsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 230, 30));
        jPanelSupport.add(jSeparatorUnderForResult, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 230, 30));

        jLabelContact.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabelContact.setForeground(new java.awt.Color(255, 255, 255));
        jLabelContact.setText("Contact:");
        jPanelSupport.add(jLabelContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, 70, -1));

        jLabeliconContact.setIcon(new javax.swing.ImageIcon("C:\\Users\\January\\Documents\\NetBeansProjects\\Project\\icon\\phone-call.png")); // NOI18N
        jPanelSupport.add(jLabeliconContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, -1, -1));

        jTAShowyouSearch.setBackground(new java.awt.Color(25, 41, 65));
        jTAShowyouSearch.setColumns(20);
        jTAShowyouSearch.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jTAShowyouSearch.setForeground(new java.awt.Color(255, 255, 255));
        jTAShowyouSearch.setRows(5);
        jTAShowyouSearch.setBorder(null);
        jScrollPane4.setViewportView(jTAShowyouSearch);

        jPanelSupport.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 390, 250));

        jTAContact.setBackground(new java.awt.Color(25, 41, 65));
        jTAContact.setColumns(20);
        jTAContact.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jTAContact.setForeground(new java.awt.Color(255, 255, 255));
        jTAContact.setRows(5);
        jScrollPane5.setViewportView(jTAContact);

        jPanelSupport.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 250, 250));

        jBTBackToRepair.setIcon(new javax.swing.ImageIcon("C:\\Users\\January\\Documents\\NetBeansProjects\\Project\\icon\\left-arrow.png")); // NOI18N
        jBTBackToRepair.setContentAreaFilled(false);
        jBTBackToRepair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTBackToRepairActionPerformed(evt);
            }
        });
        jPanelSupport.add(jBTBackToRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 50, 50));

        jPanelHomeRepair.add(jPanelSupport, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 730, 420));

        getContentPane().add(jPanelHomeRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFSearchActionPerformed

    private void jBTStartTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTStartTimeActionPerformed
        Timer myTimer;
       
            // TODO add your handling code here:
            myTimer = rp.getTime();
        try {
            myTimer.start(this.rp);
        } catch (InterruptedException ex) {
            Logger.getLogger(RepairingForAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }//GEN-LAST:event_jBTStartTimeActionPerformed

    private void jBTStopTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTStopTimeActionPerformed
        // TODO add your handling code here:
        rp.stopTime();
        String submit = rp.submitRepair();
        JOptionPane.showMessageDialog(null,submit,"Success",JOptionPane.WARNING_MESSAGE);
        rp.Status(true);//ให้ Attribute String ใน class Repair เก็บเป็น true return Success
        jPanelShowRepair.setVisible(true);
        jTAShowDetail.setText(submit);//show รายละเอียดการซ่อม
        jPanelRepairingShowTime.setVisible(false);
        jPanelRepairing.setVisible(false);
        jPanelRepairingIncrease.setVisible(false);
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingAdmin.setVisible(false);
        jPanelSupport.setVisible(false);
    }//GEN-LAST:event_jBTStopTimeActionPerformed

    private void jBTAddDetailandTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTAddDetailandTimeActionPerformed
        // TODO add your handling code here:
        jPanelRepairingIncrease.setVisible(true);
        jPanelRepairing.setVisible(false);
        jPanelRepairingShowTime.setVisible(false);
        jPanelShowRepair.setVisible(false);
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingAdmin.setVisible(false);
        jPanelSupport.setVisible(false);
    }//GEN-LAST:event_jBTAddDetailandTimeActionPerformed

    private void jBTnextToShowTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTnextToShowTimeActionPerformed
        // TODO add your handling code here:
        String repairing;
        String asking;
        int hours;
        int minute;
        int secound=0;
        String timeDetail;
        String output="";
        //----------------------------------------------------------------------
        repairing = jTFDetail.getText();
        rp.setDetail(repairing);
        asking = jTFProblem.getText();
        rp.setProblem(asking);
        hours = jCBoxHour.getSelectedIndex();
        rp.setHours(hours);
        minute = jCBoxMinute.getSelectedIndex();
        rp.setMinute(minute);
        try {
            rp.time();
        } catch (InterruptedException ex) {
            Logger.getLogger(RepairingForAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        Timestamp startDate = new Timestamp(rp.startTimeToRepair().getTime());
        Timestamp returnDate = new Timestamp(rp.endTimeToRepair().getTime());
        rp.connectDBFromAdminToUser(perpairID,1);
//        rp.connectDBForAdminUpdateTime(transID, startDate, returnDate);

        showTime = rp.getShowTime();
        jPanelRepairingShowTime.setVisible(true);
        jPanelShowRepair.setVisible(false);
        jPanelRepairing.setVisible(false);
        jPanelRepairingIncrease.setVisible(false);
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingAdmin.setVisible(false);
        jPanelSupport.setVisible(false);
        jTAShowTime.setText(showTime);
    }//GEN-LAST:event_jBTnextToShowTimeActionPerformed

    private void jBTBackToRepairShowTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTBackToRepairShowTimeActionPerformed
        // TODO add your handling code here:
        jPanelRepairingShowTime.setVisible(true);
        jPanelShowRepair.setVisible(false);
        jPanelRepairing.setVisible(false);
        jPanelRepairingIncrease.setVisible(false);
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingAdmin.setVisible(false);
        jPanelSupport.setVisible(false);
    }//GEN-LAST:event_jBTBackToRepairShowTimeActionPerformed

    private void jBTNextBactToIndexAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNextBactToIndexAdminActionPerformed
        // TODO add your handling code here:
        rping.setVisible(false);
        //----------------------------------------------------------------
        System.out.println("RepairAdmin: "+rp.getProblem());
        rpUser = new RepairingForUser(rp);
        rpUser.setVisible(true);
//        jPanelRepairing.setVisible(true);
//        jPanelRepairingShowTime.setVisible(false);
//        jPanelShowRepair.setVisible(false);
//        jPanelRepairingIncrease.setVisible(false);
//        jPanelRepairAdminDetailUser.setVisible(false);
//        jPanelRepairingAdmin.setVisible(false);
    }//GEN-LAST:event_jBTNextBactToIndexAdminActionPerformed

    private void jTFAskingRepairIncreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFAskingRepairIncreaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFAskingRepairIncreaseActionPerformed

    private void jTFRepairingRepairIncreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFRepairingRepairIncreaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFRepairingRepairIncreaseActionPerformed

    private void jBTnextInBileIncreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTnextInBileIncreaseActionPerformed
        String output="";
        String repairing;
        String asking;
        int hours;
        int minute;
        repairing = jTFRepairingRepairIncrease.getText();
        asking = jTFAskingRepairIncrease.getText();
        hours = jCBoxHourRepairIncrease.getSelectedIndex();
        minute = jCBoxMinuteRepairIncrease.getSelectedIndex();
        
        JOptionPane.showMessageDialog(null,"You increase time is: "+hours+" Hours, "+minute+" Minute","Time warning",JOptionPane.WARNING_MESSAGE);
        try {
            rp.setShowTime(rp.increaseTimeRepair(repairing, asking, hours, minute, 0));
        } catch (InterruptedException ex) {
            Logger.getLogger(RepairingForAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
            showTime = rp.getShowTime();
       
        jTAShowTime.setText(showTime);
        jPanelRepairingShowTime.setVisible(true);
        jPanelShowRepair.setVisible(false);
        jPanelRepairing.setVisible(false);
        jPanelRepairingIncrease.setVisible(false);
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingAdmin.setVisible(false);
        jPanelSupport.setVisible(false);
    }//GEN-LAST:event_jBTnextInBileIncreaseActionPerformed

    private void jTAShowDetaiUserSentRepirlForAdminAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTAShowDetaiUserSentRepirlForAdminAncestorAdded
        // TODO add your handling code here:
        jTAShowDetaiUserSentRepirlForAdmin.setText(temp);
    }//GEN-LAST:event_jTAShowDetaiUserSentRepirlForAdminAncestorAdded

    private void jBTbackToJPanelRepairAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTbackToJPanelRepairAdminActionPerformed
        //
        jPanelRepairingShowTime.setVisible(false);
        jPanelShowRepair.setVisible(false);
        jPanelRepairing.setVisible(false);
        jPanelRepairingIncrease.setVisible(false);
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingAdmin.setVisible(true);
        jPanelSupport.setVisible(false);
    }//GEN-LAST:event_jBTbackToJPanelRepairAdminActionPerformed

    private void jBTnextTojPanelRepairingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTnextTojPanelRepairingActionPerformed
        // TODO add your handling code here:
        Object[] options = {"Yes","No"};
        int ans = JOptionPane.showOptionDialog(null, "Status bicycle", "Check Statuc",
            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
            null, options, options[0]);
        if(ans==0){
            jPanelRepairingShowTime.setVisible(false);
            jPanelShowRepair.setVisible(false);
            jPanelRepairing.setVisible(true);
            jPanelRepairingIncrease.setVisible(false);
            jPanelRepairAdminDetailUser.setVisible(false);
            jPanelRepairingAdmin.setVisible(false);
            jPanelSupport.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null,"Check Status bikecycle Again","Problem",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jBTnextTojPanelRepairingActionPerformed

    private void jBtToSupportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtToSupportActionPerformed
        // TODO add your handling code here:
        SupportGUI sp = new SupportGUI();
        sp.setVisible(true);
    }//GEN-LAST:event_jBtToSupportActionPerformed

    private void jBtToBikeRepairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtToBikeRepairActionPerformed
        // TODO add your handling code here:
//        rping.setVisible(true);
        jPanelRepairing.setVisible(true);
        jPanelSupport.setVisible(false);
        jPanelRepairingShowTime.setVisible(false);
        jPanelShowRepair.setVisible(false);
        jPanelRepairingIncrease.setVisible(false);
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingAdmin.setVisible(false);
    }//GEN-LAST:event_jBtToBikeRepairActionPerformed

    private void jTFSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFSearchFocusGained
        // TODO add your handling code here:
        jTFSearch.setText("");
    }//GEN-LAST:event_jTFSearchFocusGained

    private void jBTSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTSearchActionPerformed
        // TODO add your handling code here: rping
        Support sp = new Support();
        String whatToSearch;
        jPanelSupport.setVisible(true);
        jPanelRepairingShowTime.setVisible(false);
        jPanelShowRepair.setVisible(false);
        jPanelRepairing.setVisible(false);
        jPanelRepairingIncrease.setVisible(false);
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingAdmin.setVisible(false);
        
        whatToSearch = jTFSearch.getText();
        jLBWhatsearch.setText(whatToSearch);
        jTAContact.setText(sp.contact());
        sp.searchSupport(whatToSearch);
        jTAShowyouSearch.setText(sp.getOutput());
    }//GEN-LAST:event_jBTSearchActionPerformed

    private void jBTBackToRepairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTBackToRepairActionPerformed
        // TODO add your handling code here:
        jPanelSupport.setVisible(false);
        jPanelRepairingShowTime.setVisible(true);
        jPanelShowRepair.setVisible(false);
        jPanelRepairing.setVisible(false);
        jPanelRepairingIncrease.setVisible(false);
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingAdmin.setVisible(false);
    }//GEN-LAST:event_jBTBackToRepairActionPerformed

    private void jButtonBackAdmindetailUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackAdmindetailUserActionPerformed
        // TODO add your handling code here:
        jPanelSupport.setVisible(false);
        jPanelRepairingShowTime.setVisible(false);
        jPanelShowRepair.setVisible(false);
        jPanelRepairing.setVisible(false);
        jPanelRepairingIncrease.setVisible(false);
        jPanelRepairAdminDetailUser.setVisible(true);
        jPanelRepairingAdmin.setVisible(false);
    }//GEN-LAST:event_jButtonBackAdmindetailUserActionPerformed

    private void jButtonBackToRepairingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackToRepairingActionPerformed
        // TODO add your handling code here:
        jPanelSupport.setVisible(false);
        jPanelRepairingShowTime.setVisible(false);
        jPanelShowRepair.setVisible(false);
        jPanelRepairing.setVisible(true);
        jPanelRepairingIncrease.setVisible(false);
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingAdmin.setVisible(false);
    }//GEN-LAST:event_jButtonBackToRepairingActionPerformed

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
            java.util.logging.Logger.getLogger(RepairingForAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RepairingForAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RepairingForAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RepairingForAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                rping = new RepairingForAdmin();
                rping.setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTAddDetailandTime;
    private javax.swing.JButton jBTBackToRepair;
    private javax.swing.JButton jBTBackToRepairShowTime;
    private javax.swing.JButton jBTNextBactToIndexAdmin;
    private javax.swing.JButton jBTSearch;
    private javax.swing.JButton jBTStartTime;
    private javax.swing.JButton jBTStopTime;
    private javax.swing.JButton jBTbackToJPanelRepairAdmin;
    private javax.swing.JButton jBTnextInBileIncrease;
    private javax.swing.JButton jBTnextToShowTime;
    private javax.swing.JButton jBTnextTojPanelRepairing;
    private javax.swing.JButton jBtToBikeRepair;
    private javax.swing.JButton jBtToSupport;
    private javax.swing.JButton jButtonBackAdmindetailUser;
    private javax.swing.JButton jButtonBackToRepairing;
    private javax.swing.JComboBox<String> jCBoxHour;
    private javax.swing.JComboBox<String> jCBoxHourRepairIncrease;
    private javax.swing.JComboBox<String> jCBoxMinute;
    private javax.swing.JComboBox<String> jCBoxMinuteRepairIncrease;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLBShowingResult;
    private javax.swing.JLabel jLBWhatsearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBikeRepair;
    private javax.swing.JLabel jLabelBikeRepairForShoeDetail;
    private javax.swing.JLabel jLabelBikeRepairingForAdminDetailUser;
    private javax.swing.JLabel jLabelBikeRepairingForIncrease;
    private javax.swing.JLabel jLabelBikeRepairingForRepairingAdmin;
    private javax.swing.JLabel jLabelBikeRepairingForShowTime;
    private javax.swing.JLabel jLabelBikeRepairingPageRepairing;
    private javax.swing.JLabel jLabelContact;
    private javax.swing.JLabel jLabelDetail;
    private javax.swing.JLabel jLabelDetailForIncrease;
    private javax.swing.JLabel jLabelGreen;
    private javax.swing.JLabel jLabelIconTime;
    private javax.swing.JLabel jLabelIconTimeForAdmin;
    private javax.swing.JLabel jLabelIconTimeForIncrease;
    private javax.swing.JLabel jLabelNameSurnameUser;
    private javax.swing.JLabel jLabelPicOfUser;
    private javax.swing.JLabel jLabelPositionOfUser;
    private javax.swing.JLabel jLabelProblem;
    private javax.swing.JLabel jLabelProblemForIncrease;
    private javax.swing.JLabel jLabelSetHourForAdmin;
    private javax.swing.JLabel jLabelSetHoursIncrease;
    private javax.swing.JLabel jLabelSetMinuteForAdmin;
    private javax.swing.JLabel jLabelSetMinuteIncreease;
    private javax.swing.JLabel jLabelSetTimeForAdmin;
    private javax.swing.JLabel jLabelSetTimeForIncrease;
    private javax.swing.JLabel jLabelSociety;
    private javax.swing.JLabel jLabelStatusBikeUserSentTOadmin;
    private javax.swing.JLabel jLabeliconContact;
    private javax.swing.JPanel jPNBackGround;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanelAddRepairForIncreaseProblemAndTime;
    private javax.swing.JPanel jPanelBarBike;
    private javax.swing.JPanel jPanelForAddTimeAdminIncrease;
    private javax.swing.JPanel jPanelHead;
    private javax.swing.JPanel jPanelHeadBikeRepairForRepairForAdmin;
    private javax.swing.JPanel jPanelHeadBikeRepairing;
    private javax.swing.JPanel jPanelHeadBikeRepairingForAdminDetailUser;
    private javax.swing.JPanel jPanelHeadBikeRepairingForIncrease;
    private javax.swing.JPanel jPanelHeadBikeRepairingForShowTime;
    private javax.swing.JPanel jPanelHearBikeRepairForBikeRepair;
    private javax.swing.JPanel jPanelHomeRepair;
    private javax.swing.JPanel jPanelNotic;
    private javax.swing.JPanel jPanelRepairAdminDetailUser;
    private javax.swing.JPanel jPanelRepairing;
    private javax.swing.JPanel jPanelRepairingAdmin;
    private javax.swing.JPanel jPanelRepairingIncrease;
    private javax.swing.JPanel jPanelRepairingShowTime;
    private javax.swing.JPanel jPanelSetDetailRepairForAdmin;
    private javax.swing.JPanel jPanelSetTimeForAdmin;
    private javax.swing.JPanel jPanelShoeUserSentToAdminForRepair;
    private javax.swing.JPanel jPanelShowRepair;
    private javax.swing.JPanel jPanelShowTimeForRepairShowTime;
    private javax.swing.JPanel jPanelShowUser;
    private javax.swing.JPanel jPanelSideBar;
    private javax.swing.JPanel jPanelSideBarBike;
    private javax.swing.JPanel jPanelSideBarMenu;
    private javax.swing.JPanel jPanelSupport;
    private javax.swing.JPanel jPanelUserProfile;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPaneShowDetail;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparatorUnderDetail;
    private javax.swing.JSeparator jSeparatorUnderDetailFforAdminIncrease;
    private javax.swing.JSeparator jSeparatorUnderForResult;
    private javax.swing.JSeparator jSeparatorUnderProblem;
    private javax.swing.JSeparator jSeparatorUnderProblemForAdminIncrease;
    private javax.swing.JTextArea jTAContact;
    private javax.swing.JTextArea jTAShowDetaiUserSentRepirlForAdmin;
    private javax.swing.JTextArea jTAShowDetail;
    private javax.swing.JTextArea jTAShowTime;
    private javax.swing.JTextArea jTAShowyouSearch;
    private javax.swing.JTextField jTFAskingRepairIncrease;
    private javax.swing.JTextField jTFDetail;
    private javax.swing.JTextField jTFProblem;
    private javax.swing.JTextField jTFRepairingRepairIncrease;
    private javax.swing.JTextField jTFSearch;
    // End of variables declaration//GEN-END:variables
    
    
}
