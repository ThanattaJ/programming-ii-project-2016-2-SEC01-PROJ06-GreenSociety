package bike_gui;

import bike.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

public class GreenSociety extends javax.swing.JFrame {
    private Authenication ac = new Authenication();
    private Register rs = new Register();
    private News nw = new News();
    private Repair rpw = new Repair();
    private Sharing sh = new Sharing();
    private History his = new History();
    private Support sp = new Support();
    
    //Sharing
    private int countBorrow[];
    private int availableItem[];
    private String nameOfItem[];
    private String pathOfItem[];
    private int cpOfItem[];
    private JSpinner itemOfUser[];
    
    //Repair
    private String bike="";//รับจาก GUI ให้ user กรอก
    private String whyRepair="";//ให้ user กรอกว่าทำไมถึงต้องส่งซ่อม
    private String color="";
    private long repairIDAdmin;
    private long transIDRepairAdmin;
    private long prepairIDRepaiAdmin;
    private long userIDRepairAdmin;
    private String detailRepairAdmin;
    
    public GreenSociety() {
        initComponents();
        login.setVisible(true);
        forgotPassPage.setVisible(false);
        registerPage.setVisible(false);
        warning.setVisible(false);
        setLocationRelativeTo(null);
        sPassB.setVisible(false);
        sEmailB.setVisible(false);
        mainAdminSetVisible();
        mainAdmin.setVisible(false);
        mainUserSetVisible();
        mainUser.setVisible(false);
        setBorderAllfield();
        setColorOfBarMenu();
    }

    private void mainAdminSetVisible() {
        cpPage.setVisible(false);
        newsPage.setVisible(false);
        repairPage.setVisible(false);
        sharingPage.setVisible(false);
        historyPage.setVisible(false);
        titleSignout.setVisible(false);
        timerPage.setVisible(false);
        supportPage.setVisible(false);
        userProfilePage.setVisible(false);
        sh.selectAllItemID();
        sh.setDataOfItem();
    }

    private void mainUserSetVisible() {
        sh.setDataOfItem();
        settingItem();
        startSetVisible();
        setLocationRelativeTo(null);
    }

    public void startSetVisible() {
        newsPage1.setVisible(false);
        cpPage1.setVisible(false);
        titleSignout1.setVisible(false);
        repairPage1.setVisible(false);
        sharingStep1.setVisible(false);
        sharingScroll.setVisible(false);
        sharingStep2.setVisible(false);
        historyPage1.setVisible(false);
        userProfilePage1.setVisible(false);
        supportPage1.setVisible(false);
        timePageT.setVisible(false);
        timePageF.setVisible(false);
        textNotHis.setVisible(false);
        iconNotHis.setVisible(false);
        circleMini.setVisible(false);
        circleMini1.setVisible(false);
        circleNoti.setVisible(false);
    }

    public void setColorOfBarMenu(){
        news.setForeground(new Color(255,255,255));
        canCounter.setForeground(new Color(255,255,255));
        bikeRepair.setForeground(new Color(255,255,255));
        bikeSharing.setForeground(new Color(255,255,255));
        timer.setForeground(new Color(255,255,255));
        history.setForeground(new Color(255,255,255));
        support.setForeground(new Color(255,255,255));
        profile.setForeground(new Color(255,255,255));
        
        news1.setForeground(new Color(255,255,255));
        canCounter1.setForeground(new Color(255,255,255));
        bikeRepair1.setForeground(new Color(255,255,255));
        bikeSharing1.setForeground(new Color(255,255,255));
        timer1.setForeground(new Color(255,255,255));
        history1.setForeground(new Color(255,255,255));
        support1.setForeground(new Color(255,255,255));
        profile1.setForeground(new Color(255,255,255));
    }
    
    public void setBorderAllfield() {
        //txtfield
        search1.setBorder(null);
        nameProfile1.setBorder(null);
        genderProfile1.setBorder(null);
        congenProfile1.setBorder(null);
        emailProfile1.setBorder(null);
        telProfile1.setBorder(null);
        search.setBorder(null);
        nameProfile.setBorder(null);
        genderProfile.setBorder(null);
        congenProfile.setBorder(null);
        emailProfile.setBorder(null);
        telProfile.setBorder(null);
        nameOfUser.setBorder(null);
        surnameOfUser.setBorder(null);
        itemName.setBorder(null);
        itemAmount.setBorder(null);
        itemCP.setBorder(null);
        itemId.setBorder(null);
        email.setBorder(null);
        password.setBorder(null);
        insertSupporTitle.setBorder(null);
        countOfCan.setBorder(null);
        birthProfile.setBorder(null);
        birthProfile1.setBorder(null);
        
        //scrollfield
        sentRepairScroll.setBorder(null);
        jScrollPaneHistory.setBorder(null);
        jScrollPaneInTextAreaNewsDetail.setBorder(null);
        listNewsScroll.setBorder(null);
        sharingScroll.setBorder(null);
        describeNews.setBorder(null);
        contactScroll.setBorder(null);
        contactScroll1.setBorder(null);
        insertDetailScroll.setBorder(null);
        insertSupportContentArea.setBorder(null);
        itemListScroll.setBorder(null);
        jScrollPaneShowDetailUserSentToRepair.setBorder(null);
        jScrollPaneBikeRepairingNotSuccess.setBorder(null);
        newsList.setBorder(null);
        resultScroll.setBorder(null);
        resultScroll1.setBorder(null);

    }

    public void notiTime(Object obj) {
        if(obj instanceof Sharing){
            JOptionPane.showMessageDialog(this, "Time left : 10 minutes");
        }
    }

    public String password(String oldPass, String newPass) {//พาสเวิร์ดที่ user ใส่มาสองรอบต้องเป็นตัวเดียวกัน
        String password = "";
        if (oldPass.equals(newPass)) {
            password = newPass;
        } else {
            JOptionPane.showMessageDialog(null, "These passwords don't match. Try again?",
                    "Check Password", JOptionPane.WARNING_MESSAGE);
            jPasswordSignUp.requestFocusInWindow();
        }
        return password;
    }

    public void setBorrowStep1() {
        int x = 50;
        int y = 50;
        sh.setDataOfItem();
        settingItem();
        JPanel sharingPage1 = new JPanel();
        sharingPage1.setBackground(new java.awt.Color(25, 41, 65));
        sharingPage1.setEnabled(false);
        sharingPage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        s1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        s1.setForeground(new java.awt.Color(255, 255, 255));
        s1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        s1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/Step1.png"))); // NOI18N
        sharingPage1.add(s1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 180, 40));
        isCp.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        isCp.setForeground(new java.awt.Color(255, 255, 255));
        isCp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sharingPage1.add(isCp, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 180, 30));

        JPanel[] jp = new JPanel[nameOfItem.length];
        JLabel[] imgItem = new JLabel[nameOfItem.length];
        JLabel[] available = new JLabel[nameOfItem.length];
        itemOfUser = new JSpinner[nameOfItem.length];
        for (int i = 0; i < nameOfItem.length; i++) {
            jp[i] = new JPanel();
            jp[i].setBackground(new java.awt.Color(13, 24, 35));
            jp[i].setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), nameOfItem[i] + "(" + cpOfItem[i] + " CP)", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Leelawadee", 0, 18), new java.awt.Color(19, 175, 248)));
            jp[i].setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            imgItem[i] = new JLabel();
            imgItem[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            imgItem[i].setIcon(new ImageIcon(new ImageIcon(getClass().getResource(pathOfItem[i])).getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT))); // NOI18N
            jp[i].add(imgItem[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 140, 130));

            available[i] = new JLabel();
            available[i].setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
            available[i].setForeground(new java.awt.Color(102, 204, 255));
            available[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            available[i].setText("Available :" + availableItem[i] + " Items");
            jp[i].add(available[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 140, 20));

            itemOfUser[i] = new JSpinner();
            itemOfUser[i].setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
            itemOfUser[i].setModel(new javax.swing.SpinnerNumberModel(0, 0, availableItem[i], 1));
            itemOfUser[i].setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            itemOfUser[i].setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
            itemOfUser[i].setPreferredSize(new java.awt.Dimension(60, 25));
            jp[i].add(itemOfUser[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));
            sharingPage1.add(jp[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, 300, 170));
            if (x == 390) {
                x = 50;
                y += 210;
            } else {
                x += 340;
            }
        }
        y += 180;
        sharingPage1.add(nextStep, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, y, 60, 50));
        sharingScroll.setViewportView(sharingPage1);
    }

    public void setUserBorrow() {
        int x = 30;
        int y = 40;
        JPanel panelMainUser = new JPanel();
        panelMainUser.setBackground(new java.awt.Color(15, 30, 52));
        panelMainUser.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "List", javax.swing.border.TitledBorder.ABOVE_TOP, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Leelawadee", 0, 18), new java.awt.Color(255, 255, 255)));
        panelMainUser.setPreferredSize(new java.awt.Dimension(590, 260));
        panelMainUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        ArrayList<String> tempUser = sh.adminCheckUserBorrow();
        ArrayList<Integer> tempId = sh.getIdUserBorrowing();

        if ((!tempUser.isEmpty()) && (!tempId.isEmpty()) && (tempUser.size() == tempId.size())) {
            JPanel[] backUser = new JPanel[tempUser.size()];
            JLabel[] user = new JLabel[tempUser.size()];
            for (int i = 0; i < tempUser.size(); i++) {
                int t = tempId.get(i);
                backUser[i] = new JPanel();
                backUser[i].setBackground(new java.awt.Color(13, 24, 35));
                backUser[i].setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                user[i] = new JLabel();
                user[i].setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
                user[i].setForeground(new java.awt.Color(55, 200, 255));
                user[i].setText(tempUser.get(i));

                user[i].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        userIdMouseClick(evt, t);
                    }
                });
                backUser[i].add(user[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 40));

                panelMainUser.add(backUser[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(30, y, 520, 40));
                y += 70;
            }
        }

        listUserBorrowing.setViewportView(panelMainUser);

    }

    public void userIdMouseClick(java.awt.event.MouseEvent evt, int id) {
        timewatch1.setVisible(false);
        listUserBorrowing.setVisible(false);
        selectItemReturn.setVisible(false);
        userDetail.setVisible(true);
        String tempName = "";
        String tempAmount = "";
        JButton iconReturn = new JButton();
        JLabel itemName = new JLabel();
        JLabel itemAmount = new JLabel();
        String name = sh.nameOfuser(id);
        sh.itemUserBorrow(id);
        ArrayList<String> item = sh.getItemNameReturnUser();
        ArrayList<Integer> amount = sh.getItemAmountReturnUser();

        userDetail.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(19, 175, 248), 1, true), "Name  : " + name, javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Leelawadee", 0, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        iconReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/ReturnBut.png"))); // NOI18N
        iconReturn.setContentAreaFilled(false);
        iconReturn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iconReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnActionPerformed(evt, id);
            }
        });

        iconReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                iconReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/ReturnButClick.png")));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                iconReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/ReturnBut.png")));
            }
        });

        userDetail.add(iconReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 279, 150, 60));

        itemName.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        itemName.setForeground(new java.awt.Color(55, 200, 255));
        itemName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        for (int i = 0; i < item.size(); i++) {
            tempName += item.get(i) + "<br>";
        }
        itemName.setText("<html><body>" + tempName + "</body></html>");
        itemName.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        userDetail.add(itemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 130, 240));

        itemAmount.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        itemAmount.setForeground(new java.awt.Color(55, 200, 255));
        itemAmount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        for (int i = 0; i < amount.size(); i++) {
            tempAmount += "Amount : " + amount.get(i) + "<br>";
        }
        itemAmount.setText("<html><body>" + tempAmount + "</body></html>");
        itemAmount.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        userDetail.add(itemAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 170, 240));
        userDetail.setVisible(true);
    }

    public void returnActionPerformed(ActionEvent evt, int id) {
        returnDateBorrowing.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        returnDateBorrowing.setForeground(new java.awt.Color(102, 255, 102));
        returnDateBorrowing.setText("Return : 20/3/2017  18:00");
        returnDateBorrowing.setToolTipText("");
        selectItemReturn.add(returnDateBorrowing, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 200, 40));

        cancleReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleMini.png"))); // NOI18N
        cancleReturn.setContentAreaFilled(false);
        cancleReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancleReturnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancleReturnMouseExited(evt);
            }
        });
        cancleReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancleReturnActionPerformed(evt);
            }
        });
        selectItemReturn.add(cancleReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 181, 90, 50));
        
        selectItemReturn.setVisible(true);
        closeUserDetail.setEnabled(false);
        JButton submitReturn = new JButton();
        sh.itemUserBorrow(id);
        ArrayList<String> item = sh.getItemNameReturnUser();
        ArrayList<Integer> amount = sh.getItemAmountReturnUser();
        JCheckBox[] nameItemReturn = new JCheckBox[sh.getItemNameReturnUser().size()];
        JSpinner[] countItemReturn = new JSpinner[sh.getItemAmountReturnUser().size()];
        int x = 30;
        int y = 30;
        for (int i = 0; i < countItemReturn.length; i++) {
            nameItemReturn[i] = new JCheckBox();
            nameItemReturn[i].setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
            nameItemReturn[i].setForeground(new java.awt.Color(19, 175, 248));
            nameItemReturn[i].setText(item.get(i));
            nameItemReturn[i].setContentAreaFilled(false);
            selectItemReturn.add(nameItemReturn[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, 130, 20));
            x += 130;
            countItemReturn[i] = new JSpinner();
            int temp = amount.get(i);
            countItemReturn[i].setModel(new javax.swing.SpinnerNumberModel(0, 0, temp, 1));
            selectItemReturn.add(countItemReturn[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, 50, -1));
            x += 100;
            if (x == 490) {
                x = 30;
                y += 40;
            }
        }
        boolean check = sh.isTimesUp();
        if (check == true) {
            Date temp = sh.getReturnTimeBorrow();
            returnDateBorrowing.setForeground(new java.awt.Color(255, 102, 102));
            returnDateBorrowing.setText("Return : " + temp.getDate() + "/" + (temp.getMonth() + 1) + "/" + (temp.getYear() + 1900) + "  " + temp.getHours() + " : " + temp.getMinutes());
        } else if (check == false) {
            Date temp = sh.getReturnTimeBorrow();
            returnDateBorrowing.setForeground(new java.awt.Color(102, 255, 102));
            returnDateBorrowing.setText("Return : " + temp.getDate() + "/" + (temp.getMonth() + 1) + "/" + (temp.getYear() + 1900) + "  " + temp.getHours() + " : " + temp.getMinutes());
        }

        submitReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMini.png"))); // NOI18N
        submitReturn.setContentAreaFilled(false);
        submitReturn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        submitReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                submitReturnActionPerformed(evt,id);
                ArrayList<String> tempId = new ArrayList<String>();
                ArrayList<Integer> tempAmount = new ArrayList<Integer>();
                ArrayList<String> idItem = sh.getItemIDReturnUser();
                int ans = JOptionPane.showConfirmDialog(null, "Do you confirm item to return?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (ans == JOptionPane.YES_OPTION) {
                    int cpUse = 0;
                    boolean notAmount = false;
                    boolean notSelect = false;
                    ArrayList<Integer> cpItem = sh.getItemCPReturnUser();
                    for (int i = 0; i < nameItemReturn.length; i++) {
                        if (nameItemReturn[i].isSelected()) {
                            if (((int) countItemReturn[i].getValue()) != 0) {
                                tempId.add(idItem.get(i));
                                tempAmount.add((int) countItemReturn[i].getValue());
                                cpUse += (int) countItemReturn[i].getValue() * cpItem.get(i);
                            } else {
                                notAmount = true;
                                break;
                            }
                        }
                    }
                    for (int i = 0; i < countItemReturn.length; i++) {
                        if (((int)countItemReturn[i].getValue()) != 0) {
                            if(nameItemReturn[i].isSelected() == false){
                                notSelect = true;
                                break;
                            }
                        }
                    }
                    if (notSelect == true){
                        JOptionPane.showMessageDialog(null, "Plese Enter Select item.", "Error", JOptionPane.ERROR_MESSAGE);
                    }else if (notAmount == true) {
                        JOptionPane.showMessageDialog(null, "Plese Enter amount of item.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (check == true) {
                            cpUse *= 2;
                            sh.returnItems(cpUse, tempAmount, tempId, id);
                        } else if (check == false) {
                            sh.returnItems(0, tempAmount, tempId, id);
                        }
                    
                        JOptionPane.showMessageDialog(null, "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
                        selectItemReturn.setVisible(false);
                        closeUserDetail.setEnabled(true);
                        userDetail.setVisible(false);
                        timewatch1.setVisible(true);
                        setUserBorrow();
                        userDetail.removeAll();
                        selectItemReturn.removeAll();
                        listUserBorrowing.setVisible(true);
                    }
                }
            }
        });
        submitReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMiniClick.png")));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMini.png")));
            }
        });
        selectItemReturn.add(submitReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 181, 90, 50));
    }

    public void settingItem() {
        sh.selectAllItemID();
        sh.setDataOfItem();
        nameOfItem = sh.getNameItem();
        pathOfItem = sh.getPathImgItem();
        availableItem = sh.getAvailableItem();
        cpOfItem = sh.getItemCP();
        countBorrow = new int[nameOfItem.length];
    }

    public void insertBorrow() throws SelectItemException {
        int temp = 0;
        int num[] = new int[itemOfUser.length];
        for (int i = 0; i < num.length; i++) {
            num[i] = (int) itemOfUser[i].getValue();
        }
        for (int i = 0; i < num.length; i++) {
            if (num[i] == 0) {
                temp++;
            }
        }
        if (num.length == temp) {
            SelectItemException slt = new SelectItemException();
            throw slt;
        }
        for (int i = 0; i < countBorrow.length; i++) {
            countBorrow[i] = num[i];
        }
        sh.setNumBikeUser(countBorrow);
    }

    public void setBorrowDetail() {
        String tmp = "";
        for (int i = 0; i < countBorrow.length; i++) {
            if (countBorrow[i] != 0) {
                tmp += "- " + nameOfItem[i] + " : " + countBorrow[i] + "<br>";
            }
        }
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String now = "Start: " + df.format(sh.getTime().getBorrowTime());
        String re = "End: " + df.format(sh.getTime().getReturnTime());
        tmp += now + "<br>" + re;
        detailData.setText("<html><body>" + tmp + "</body></html>");
    }

    public void clearingSharing() {
        sh.editStep();
        settingItem();
        for (int i = 0; i < countBorrow.length; i++) {
            countBorrow[i] = 0;
        }
        detailData.setText("");
    }

    public void setDataUserProfile() {
        if (User.getPositon().equals("-")) {
            userID1.setText(User.getUserId() + "");
            nameProfile1.setText(User.getFirstName() + " " + User.getLastName());
            genderProfile1.setText(User.getGender());
            birthProfile1.setText(User.getBirthDate());
            departProfile1.setText(User.getDept());
            congenProfile1.setText(User.getCongenitialDisease());
            emailProfile1.setText(User.getEmail());
            telProfile1.setText(User.getTel());
            imageProfile1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(User.getImgPath())).getImage().getScaledInstance(200, 170, Image.SCALE_DEFAULT)));
            pic1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(User.getImgPath())).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        } else if (User.getPositon().equalsIgnoreCase("Officer") || User.getPositon().equalsIgnoreCase("Technician")) {
            userID.setText(User.getUserId() + "");
            nameProfile.setText(User.getFirstName() + " " + User.getLastName());
            genderProfile.setText(User.getGender());
            birthProfile.setText(User.getBirthDate());
            departProfile.setText(User.getDept());
            congenProfile.setText(User.getCongenitialDisease());
            emailProfile.setText(User.getEmail());
            telProfile.setText(User.getTel());
            imageProfile.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(User.getImgPath())).getImage().getScaledInstance(200, 170, Image.SCALE_DEFAULT)));
            pic.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(User.getImgPath())).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        }
    }

    public void setListNewsUser() {    //JPanel show news for user
        int x = 40;
        int y = 80;
        JPanel mainNews = new JPanel();
        mainNews.setBackground(new Color(25,41,65));
        mainNews.setEnabled(false);
        mainNews.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        String[] pathImgNews = nw.selectImgNewsForUser();
        String[] topicNews = nw.selectTopicNewsForUser();
        JLabel read[] = new JLabel[topicNews.length];
        JPanel[] jPanelNews = new JPanel[topicNews.length];
        JLabel[] jLabelImgNews = new JLabel[topicNews.length];
        JLabel[] jLabelTopicNews = new JLabel[topicNews.length];
        JButton[] viewButton = new JButton[topicNews.length];

        for (int i = 0; i < topicNews.length; i++) {
            int tem = nw.getIdCanShow().get(i);
            jPanelNews[i] = new JPanel();
            jPanelNews[i].setBackground(new java.awt.Color(255, 255, 255));
            jPanelNews[i].setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            //------------------------------------------------------------------
            jLabelTopicNews[i] = new JLabel();
            jLabelTopicNews[i].setFont(new java.awt.Font("Leelawadee", 0, 13)); 
            jLabelTopicNews[i].setText(topicNews[i]);
            jPanelNews[i].add(jLabelTopicNews[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 190, 30));
            //------------------------------------------------------------------
            jLabelImgNews[i] = new JLabel();
            jLabelImgNews[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            if(pathImgNews[i].equalsIgnoreCase("No Picture.")){
                jLabelImgNews[i].setText(pathImgNews[i]);
                jLabelImgNews[i].setForeground(new Color(204,204,204));
            }else{
                jLabelImgNews[i].setIcon(new ImageIcon(new ImageIcon(getClass().getResource(pathImgNews[i])).getImage().getScaledInstance(180, 210, Image.SCALE_DEFAULT))); // NOI18N
            }
            jPanelNews[i].add(jLabelImgNews[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, 210));
            //------------------------------------------------------------------
            read[i] = new JLabel();
            read[i].setText("Read News");
            viewButton[i] = new JButton();
            viewButton[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/readNews.png")));
            viewButton[i].setContentAreaFilled(false);
            viewButton[i].setBackground(new Color(19, 175, 248));
            viewButton[i].setBorder(null);
            viewButton[i].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            int num = i;
            viewButton[i].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {   //กดแล้วทำงาน
//                    showNews();
                    jBtUserClickActionPerformed(evt, tem,pathImgNews[num]);
                }
            });
            viewButton[i].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewButton[num].setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/readNewsClick.png")));
                read[num].setVisible(true);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewButton[num].setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/readNews.png")));
                read[num].setVisible(false);
            }
            });
            jPanelNews[i].add(viewButton[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 243, 40, 30));
            jPanelNews[i].add(read[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 243, -1, 30));
            read[i].setVisible(false);
            mainNews.add(jPanelNews[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, 200, 280));
            x += 240;
            if (x > 520) {
                x = 40;
                y += 320;
            }
        }
        titleNewsPageUser.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        titleNewsPageUser.setForeground(new java.awt.Color(255, 255, 255));
        titleNewsPageUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleNewsPageUser.setText("News");
        mainNews.add(titleNewsPageUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 70, 30));
        listNewsScroll.setViewportView(mainNews);
    }

    public void jBtUserClickActionPerformed(java.awt.event.ActionEvent evt, int tem,String path) {
        jPanelShowNewsAfterClick.setVisible(true);
        listNewsScroll.setVisible(false);
        listNewsPage.setVisible(false);
        jLabelShowTopicAfterClick.setText(nw.topicNewsSelect(tem));
        jTextAreaShowDetailNewsAfterClick.setText("    "+nw.detailNewsSelect(tem));
        if(path.equalsIgnoreCase("No Picture.")){
            imgNews.setText(path);
            imgNews.setIcon(null); // NOI18N
            imgNews.setForeground(new Color(255,255,255));
        }else{
            imgNews.setText("");
            imgNews.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage().getScaledInstance(200, 260, Image.SCALE_DEFAULT))); // NOI18N
        }
    }

    public void setListNewsAdmin() {
        int y = 20;

        String[] topic = nw.selectTopicNews();
        JPanel[] jp = new JPanel[topic.length];
        JLabel[] label = new JLabel[topic.length];
        JButton[] click = new JButton[topic.length];

        for (int i = 0; i < topic.length; i++) {
            int tem = nw.getIdCanShow().get(i);
            jp[i] = new JPanel();
            jp[i].setBackground(new java.awt.Color(51, 51, 51));
            jp[i].setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            label[i] = new JLabel();
            label[i].setForeground(new Color(19, 175, 248));
            label[i].setText(topic[i]);
            jp[i].add(label[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

            click[i] = new JButton();
            click[i].setText("View");
            click[i].setBackground(new Color(19, 175, 248));
            click[i].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            click[i].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {   //กดแล้วทำงาน
//                    showNews
                    jBtClickActionPerformed(evt, tem);
                }
            });

            jp[i].add(click[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 70, 20));
            showlistNews.add(jp[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(30, y, 530, 40));
            y += 50;
        }
        newsList.setViewportView(showlistNews);
    }

    public void deleteActionPerformed(java.awt.event.ActionEvent evt, int tem) {
        nw.deleteNews(tem);
        showlistNews.removeAll();
         
        jPanelInsertNews.setVisible(false);
        binding.setVisible(false);
        showNews.setVisible(false);
        showlistNews.setVisible(true);
        newsList.setVisible(true);
        setListNewsAdmin();
        showlistNews.revalidate();
        showlistNews.repaint(); 
        jButtonInsert.setVisible(true);
    }

    public void updateActionPerformed(java.awt.event.ActionEvent evt, int tem) {
        describeNewsText.setText(nw.detailNewsSelect(tem));
        topicNews.setEditable(true);
        topicNews.setBackground(new java.awt.Color(255, 255, 255));
        topicNews.setForeground(new java.awt.Color(0, 0, 0));
        describeNewsText.setEditable(true);
        describeNewsText.setBackground(new java.awt.Color(255, 255, 255));
        describeNewsText.setForeground(new java.awt.Color(0, 0, 0));
        binding.setVisible(true);
        submitUpdateNews.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {   //กดแล้วทำงาน
                nw.editNews(topicNews.getText(), describeNewsText.getText(), tem);
                topicNews.setEditable(false);
                describeNewsText.setEditable(false);
                binding.setVisible(false);
                topicNews.setBackground(new java.awt.Color(9, 20, 36));
                topicNews.setForeground(new java.awt.Color(255, 255, 255));
                describeNewsText.setBackground(new java.awt.Color(9, 20, 36));
                describeNewsText.setForeground(new java.awt.Color(255, 255, 255));

            }
        });

        cancleUpdateNews.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {   //กดแล้วทำงาน
                topicNews.setEditable(false);
                describeNewsText.setEditable(false);
                binding.setVisible(false);
                topicNews.setBackground(new java.awt.Color(9, 20, 36));
                topicNews.setForeground(new java.awt.Color(255, 255, 255));
                describeNewsText.setBackground(new java.awt.Color(9, 20, 36));
                describeNewsText.setForeground(new java.awt.Color(255, 255, 255));
            }
        });
    }

    public void jBtClickActionPerformed(java.awt.event.ActionEvent evt, int tem) {
        topicNews.setBorder(null);
        binding.setVisible(false);
        showNews.setVisible(true);
        jButtonInsert.setVisible(false);
        describeNewsText.setText(nw.detailNewsSelect(tem));
        describeNewsText.setLineWrap(true);
        topicNews.setText(nw.topicNewsSelect(tem));//เปลี่ยนหัวข้อข่าว
        showlistNews.setVisible(false);
        newsList.setVisible(false);
        //----------Delete button-------------
        JButton delete = new JButton();
        delete.setText("DELETE");
        delete.setBackground(new Color(19, 175, 248));
        delete.setFont(new java.awt.Font("Leelawadee", 0, 14));
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt, tem);
            }

        });
        //-------------Submit Button in Update---------
        JButton update = new JButton();
        update.setText("Update");
        update.setBackground(new Color(19, 175, 248));
        update.setFont(new java.awt.Font("Leelawadee", 0, 14));
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt, tem);
            }

        });
        showNews.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 360, -1, 30));
        showNews.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 360, -1, 30));
    }
    
    public void tableHistoryGui(ArrayList<String> tem){
        ArrayList<String> startDate = tem;
        ArrayList<String> endDate = his.getNewReturn();
        ArrayList<String> item = his.getItem();
        ArrayList<String> action = his.getAction();
        jScrollPaneHistory.getViewport().setViewPosition(new Point(0, 0));
        JPanel []jPanelRecordTable = new JPanel[tem.size()];
        JLabel []jLabelStart = new JLabel[tem.size()];
        JLabel []jLabelAction = new JLabel[tem.size()];
        JLabel []jLabelItem = new JLabel[tem.size()];
        JLabel []jLabelEnd = new JLabel[tem.size()];
        int y=20;
        
        for(int i=0;i<tem.size();i++){
            jPanelRecordTable[i] = new JPanel();
            jPanelRecordTable[i].setBackground(new java.awt.Color(51, 51, 51));
            jPanelRecordTable[i].setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
            //-------------------------------------------------------------------
            jLabelStart[i] = new JLabel();
            jLabelStart[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabelStart[i].setText(startDate.get(i));
            jLabelStart[i].setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
            jLabelStart[i].setForeground(new java.awt.Color(19, 175, 248));
            jPanelRecordTable[i].add(jLabelStart[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, 50));
            
            jLabelAction[i] = new JLabel();
            jLabelAction[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabelAction[i].setText(action.get(i));
            jLabelAction[i].setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
            jLabelAction[i].setForeground(new java.awt.Color(19, 175, 248));
            jPanelRecordTable[i].add(jLabelAction[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 140, 50));

            jLabelItem[i] = new JLabel();
            jLabelItem[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabelItem[i].setText(item.get(i));
            jLabelItem[i].setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
            jLabelItem[i].setForeground(new java.awt.Color(19, 175, 248));
            jPanelRecordTable[i].add(jLabelItem[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 140, 50));

            jLabelEnd[i] = new JLabel();
            jLabelEnd[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabelEnd[i].setText(endDate.get(i));
            jLabelEnd[i].setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
            jLabelEnd[i].setForeground(new java.awt.Color(19, 175, 248));
            jPanelRecordTable[i].add(jLabelEnd[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 140, 50));
            
            jPanelTableHistory.add(jPanelRecordTable[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 640, 50));
            y+=70;
            
            jPanelHistory.add(jPanelTableHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 640, 220));
        }
        jScrollPaneHistory.setViewportView(jPanelTableHistory);
   }
    
   public void showStatHistory(){
       //ดึง method statGreensociety...... ใน History มาใส่
        int tempRepair = his.statGreensocietyRepair();  
        int tempBorrow = his.statGreensocietyBorrow();  
        int tempReturn = his.statGreensocietyReturn();  
        DefaultCategoryDataset barchartData = new DefaultCategoryDataset();
    
        barchartData.setValue(tempRepair, "0", "Repair");
        barchartData.setValue(tempBorrow, "1", "Borrow");
        barchartData.setValue(tempReturn, "2", "Return");
        
        
        JFreeChart chart = ChartFactory.createBarChart("Statictics Daily's Usage", "Action", "Amount", barchartData, PlotOrientation.HORIZONTAL, false, true, false);
        chart.setPadding(new RectangleInsets(10, 7, 7, 7));
//        chart.getTitle().setPaint(Color.WHITE);   //เผื่อจะแก้สีหัวข้อ
        
        //--------SET FONT--------------
        try{
            File f = new File("src/font/leelawad.ttf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, f);
            customFont = customFont.deriveFont(16f);
            chart.getTitle().setFont(customFont);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        //------------------------------
        chart.setBackgroundPaint(new Color(255,255,255));     //setBackground รอบนอก

        CategoryPlot barchartPlot = chart.getCategoryPlot();
        barchartPlot.setForegroundAlpha(0.8f);
        barchartPlot.setRangeGridlinePaint(Color.BLACK);    //set เส้นแบ่งช่วงกราฟ
        ValueAxis rangeAxis = barchartPlot.getRangeAxis();
        rangeAxis.setRange(0, 100);
        //Customize renderer
        BarRenderer renderer = (BarRenderer)barchartPlot.getRenderer();
        renderer.setItemMargin(.25);
        java.awt.Paint paint1 = new Color(18,189,255);
        java.awt.Paint paint2 = new Color(19,175,248);
        java.awt.Paint paint3 = new Color(27,108,204);
        renderer.setMaximumBarWidth(1);
        renderer.setSeriesPaint(0, paint1); //Color for RepairGraph
        renderer.setSeriesPaint(1, paint2); //Color for BorrowGraph
        renderer.setSeriesPaint(2, paint3); //Color for ReturnGraph
        
        ChartPanel barPanel = new ChartPanel(chart);
        jPanelShowGraph.removeAll();
        jPanelShowGraph.add(barPanel,BorderLayout.CENTER);
        jPanelShowGraph.validate();
    }

    public void iconStatusFollowingRepairing(){
        String subStatus="";
        
        if(rpw.getStatusUser()!= null && rpw.getStatusUser().length()!=0){
            subStatus = rpw.getStatusUser().substring(2,3);
        }else{
            subStatus = "N";
        }
        
        if(rpw.getStatusUser()!= null && rpw.getStatusUser().length()==0){
            jLabelStatusRepairIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/repairUserStep1.png")));
        }
        
        if(rpw.getRepairingUser()!=null){
            circleMini1.setVisible(false);
            if(circleMini.isVisible() == true){
                circleNoti.setVisible(true);
            }else{
                circleNoti.setVisible(false);
            }
            jLabelStatusRepairIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/repairUserStep2.png")));
        }
        
        if(rpw.getStatusUser()!= null && subStatus.equalsIgnoreCase("S")){
            circleMini1.setVisible(true);
            circleNoti.setVisible(true);
            jLabelStatusRepairIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/repairUserStep3.png")));
        }
    }
    
    public void setDataReparing(){
        rpw.connectDBShowRepairForUserFollowing(User.getUserId());
        iconStatusFollowingRepairing();
        textAsking.setText("<html><body>"+rpw.getAskingUser()+"</html></body>");
        textRepair.setText("<html><body>"+rpw.getRepairingUser()+"</html></body>");
        textRecieving.setText("<html><body>"+rpw.getStatusUser()+"</html></body>");
        jLabelTime.setText(rpw.getTimerUser());
    }
    
    public void repairingNotSuccess(){ //สำหรับการซ่อมที่ยังไม่เสร็จ
        int size = rpw.connectDBForCheckRepairNotSucceess().size();
        ArrayList<String> notSuccess = rpw.connectDBForCheckRepairNotSucceess();
        JPanel []jp = new JPanel[size];
        JLabel []detail = new JLabel[size];
        JLabel []remaining = new JLabel[size];
        JButton []insertButton = new JButton[size];
        JButton []doneButton = new JButton[size];
        String dtNotSuccess;

        int y=10;
        for(int i=0;i<size;i++){
            jp[i]=new JPanel();
            jp[i].setBackground(new java.awt.Color(51, 51, 51));
            jp[i].setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
            
            dtNotSuccess = notSuccess.get(i);//String จาก ArrayList = Stringตัวหนึ่ง
            int lengthTemp = dtNotSuccess.length();
            String output = dtNotSuccess.substring(0,lengthTemp-23);
            
            detail[i]=new JLabel();
            detail[i].setText(output);
            detail[i].setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
            detail[i].setForeground(new java.awt.Color(255, 255, 255));
            jp[i].add(detail[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

            String idRepairState1 = dtNotSuccess.substring(0,1); // SubString เอา repairID
            int idRepairState = Integer.parseInt(idRepairState1);
            String time = dtNotSuccess.substring(lengthTemp-23,lengthTemp);
            
            remaining[i]=new JLabel();
            remaining[i].setText(time);
            remaining[i].setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
            remaining[i].setForeground(new java.awt.Color(255, 255, 255));
            jp[i].add(remaining[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));
            
            insertButton[i]=new JButton();
            insertButton[i].setText("Insert");
            insertButton[i].setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
            insertButton[i].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButtonInsertButtonInJPanelNotSuccess(evt,idRepairState,rpw.getItemId(),rpw.getUserID());
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            }
            });
            jp[i].add(insertButton[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 11, -1, 20));
            
            doneButton[i]=new JButton();
            doneButton[i].setText("Done");
            doneButton[i].setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
            doneButton[i].addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDoneInJPanelNotSuccess(evt,idRepairState);
            }
            });
            jp[i].add(doneButton[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, 20));
            jPanelBikeRepairNotSuccess.add(jp[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(10, y, 670, 40));
            y+=50;
        }
            jScrollPaneBikeRepairingNotSuccess.setViewportView(jPanelBikeRepairNotSuccess);
    }
    
    private void jButtonDoneInJPanelNotSuccess(java.awt.event.ActionEvent evt,int idRepairState) {  
        Object[] options = {"Yes","No"};
        int n = JOptionPane.showOptionDialog(null,"Is your work done,isn't it? ","Comfirm finishing",
                JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);    
        if(n==0){
            Date nowTime = new Date();
            Timestamp stopTime = new Timestamp(nowTime.getTime());
            transIDRepairAdmin = rpw.connectDBFormAdminSelectTransID(repairIDAdmin);
            rpw.connectDBForAdminUpdateTime(transIDRepairAdmin, stopTime);
            rpw.connectDBForChangeToSuccessFormRepairState(idRepairState);
            repairingNotSuccess();
            repaint();
            jPanelRepairingNotSuccess.setVisible(true);
            jPanelShowRepair.setVisible(false);
            jPanelHeadBikeRepairForRepairForAdmin.setVisible(false);
            jPanelRepairAdminDetailUser.setVisible(false);
            jPanelRepairingAdmin.setVisible(false);
        }
        
    }     
    
    private void jButtonInsertButtonInJPanelNotSuccess(java.awt.event.ActionEvent evt,int idRepairState,long prepairID,long userID) throws InterruptedException {
        repairIDAdmin = idRepairState;
        prepairIDRepaiAdmin = prepairID;
        transIDRepairAdmin = rpw.getTransIDAdminNotSuccess();
        userIDRepairAdmin = userID;
        //--------------------------------
        rpw.setProblem(rpw.getAskingAdminNotSuccess());
        rpw.setDetail(rpw.getRepairingAdminNotSuccess());
//        
        jButtonRepairForNextToPageNotsuccess.setVisible(false);
        jPanelRepairingNotSuccess.setVisible(false);
        jPanelRepairingAdmin.setVisible(false);
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingSetRepair.setVisible(true);
        jPanelShowRepair .setVisible(false);
    } 
        
   public void listUserRepair(){//JPanel jPanelRepairingAdmin
        ArrayList<String> list = rpw.connectDBforListUserSentToRepair();
        int numList = list.size();
        JPanel[] jp = new JPanel[numList];
        JLabel[] name = new JLabel[numList];
        JLabel[] surname = new JLabel[numList];
        JLabel[] id = new JLabel[numList];
        JLabel[] icon = new JLabel[numList];
        JButton[] click = new JButton[numList];
        int y =20;
        for (int i = 0; i < numList; i++) {
            int tem1 =i;
            jp[i] = new JPanel();
            jp[i].setBackground(new Color(240,240,240));
            jp[i].setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
            //----------------------------------------------------------------------------------------//
            name[i] = new JLabel();
            name[i].setFont(new java.awt.Font("Leelawadee",0,14));
            name[i].setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            name[i].setText(list.get(i));//ใส่ชื่อของ User แต่ล่ะคน
            jp[i].add(name[i],new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));
            //----------------------------------------------------------------------------------------//
            String idPrepair1 = list.get(i).substring(0,1);
            int idPrepair2 = Integer.parseInt(idPrepair1);
            int lengthList = list.get(i).length();
            int find1 = list.get(i).lastIndexOf(":");
            String userIDPrepair1 = list.get(i).substring(find1+2,lengthList);
            int userIDPrepair2 = Integer.parseInt(userIDPrepair1);
            
            click[i] = new JButton();
            click[i].setText("Click");
            click[i].setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            click[i].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTRepairClickActionPerformed(evt,idPrepair2,userIDPrepair2);
            }
            });
            jp[i].add(click[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(620,10, -1, 20));
            jPNBackGround.add(jp[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(20, y, 740, 40));
            y+=55;
            jScrollPaneShowDetailUserSentToRepair.setViewportView(jPNBackGround);
            
        }
    }

    
    private void jBTRepairClickActionPerformed(java.awt.event.ActionEvent evt,int repairID,long userID) {  //   click in  jPanelRepairingAdmin                                    
        // TODO add your handling code here:
        userIDRepairAdmin = userID;
        repairIDAdmin = repairID; //เอามาจากที่ admin คลิกดูที่ user ส่งมา
        jPanelRepairAdminDetailUser.setVisible(true);
        detailRepairAdmin = rpw.connectDBForRepairAdminDetail(repairIDAdmin);
        jPanelRepairingAdmin.setVisible(false);
        jButtonRepairForNextToPageNotsuccess.setVisible(false);
        jPanelShowRepair.setVisible(false);
        revalidate();
        repaint();
    }  
    
    public void clearTextLabelFollowRepair(){
        rpw.setAskingUser("");
        textAsking.setText("");
        rpw.setStatusUser("");
        textRecieving.setText("");
        rpw.setRepairingUser("");
        textRepair.setText("");
        jLabelTime.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupGender = new javax.swing.ButtonGroup();
        mainAdmin = new javax.swing.JPanel();
        barSearch = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        iconSearch = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        backMenuBar = new javax.swing.JPanel();
        menuBar = new javax.swing.JPanel();
        news = new javax.swing.JLabel();
        bikeRepair = new javax.swing.JLabel();
        canCounter = new javax.swing.JLabel();
        bikeSharing = new javax.swing.JLabel();
        history = new javax.swing.JLabel();
        profile = new javax.swing.JLabel();
        menu = new javax.swing.JLabel();
        timer = new javax.swing.JLabel();
        support = new javax.swing.JLabel();
        titleSignout = new javax.swing.JLabel();
        arrowDownIcon = new javax.swing.JLabel();
        barUser = new javax.swing.JPanel();
        pic = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        position = new javax.swing.JLabel();
        backBarUser = new javax.swing.JPanel();
        backBarIcon = new javax.swing.JPanel();
        barIcon = new javax.swing.JPanel();
        iconMenu = new javax.swing.JLabel();
        iconBike = new javax.swing.JLabel();
        iconProfile = new javax.swing.JLabel();
        signout = new javax.swing.JButton();
        barNoti = new javax.swing.JPanel();
        timerPage = new javax.swing.JPanel();
        selectItemReturn = new javax.swing.JPanel();
        cancleReturn = new javax.swing.JButton();
        returnDateBorrowing = new javax.swing.JLabel();
        userDetail = new javax.swing.JPanel();
        closeUserDetail = new javax.swing.JButton();
        timewatch1 = new javax.swing.JPanel();
        titleNow1 = new javax.swing.JLabel();
        timeLeftShow1 = new javax.swing.JLabel();
        endTime1 = new javax.swing.JLabel();
        iconClockBig1 = new javax.swing.JLabel();
        listUserBorrowing = new javax.swing.JScrollPane();
        historyPage = new javax.swing.JPanel();
        jPanelHeadHistory2 = new javax.swing.JPanel();
        jLabelHistoryText2 = new javax.swing.JLabel();
        jPanelShowGraph = new javax.swing.JPanel();
        supportPage = new javax.swing.JPanel();
        bodySupport = new javax.swing.JPanel();
        jLBShowingResult = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLBWhatsearch = new javax.swing.JLabel();
        resultScroll = new javax.swing.JScrollPane();
        jTAShowyouSearch = new javax.swing.JTextArea();
        contactScroll = new javax.swing.JScrollPane();
        jTAContact = new javax.swing.JTextArea();
        titleContactSupport = new javax.swing.JLabel();
        insertSupportPanel = new javax.swing.JPanel();
        titleContent = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        insertSupporTitle = new javax.swing.JTextField();
        titleInsertSupport = new javax.swing.JLabel();
        submitSupport = new javax.swing.JButton();
        insertSupportContentArea = new javax.swing.JScrollPane();
        insertContentSup = new javax.swing.JTextArea();
        waringInsertSupport = new javax.swing.JLabel();
        cancleInsertSup = new javax.swing.JButton();
        titleCancleInsertSup = new javax.swing.JLabel();
        barTitle = new javax.swing.JPanel();
        titleSupport = new javax.swing.JLabel();
        newManual = new javax.swing.JButton();
        cpPage = new javax.swing.JPanel();
        titleCountCan = new javax.swing.JLabel();
        titleNameUser = new javax.swing.JLabel();
        s3 = new javax.swing.JSeparator();
        s4 = new javax.swing.JSeparator();
        nameOfUser = new javax.swing.JTextField();
        countOfCan = new javax.swing.JTextField();
        submitBut = new javax.swing.JButton();
        cancleAdminBut = new javax.swing.JButton();
        titleLastname = new javax.swing.JLabel();
        surnameOfUser = new javax.swing.JTextField();
        s5 = new javax.swing.JSeparator();
        repairPage = new javax.swing.JPanel();
        jPanelHeadBikeRepairForRepairForAdmin = new javax.swing.JPanel();
        jLabelBikeRepairingForRepairingAdmin = new javax.swing.JLabel();
        jPanelShowRepair = new javax.swing.JPanel();
        jScrollPaneShowDetail = new javax.swing.JScrollPane();
        jTAShowDetail = new javax.swing.JTextArea();
        jBTNextToPanelNotSuccess = new javax.swing.JButton();
        jBTBackToRepairShowTime = new javax.swing.JButton();
        jPanelRepairingNotSuccess = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPaneBikeRepairingNotSuccess = new javax.swing.JScrollPane();
        jPanelBikeRepairNotSuccess = new javax.swing.JPanel();
        jButtonRefreshRepairNotSuccess = new javax.swing.JButton();
        jButtonToRepairAdmin = new javax.swing.JButton();
        jPanelRepairingSetRepair = new javax.swing.JPanel();
        titleSetPloblem = new javax.swing.JLabel();
        jBTnextToShowTime = new javax.swing.JButton();
        jPanelSetDetailRepairForAdmin = new javax.swing.JPanel();
        jLabelProblem = new javax.swing.JLabel();
        jTFProblem = new javax.swing.JTextField();
        jSeparatorUnderProblem = new javax.swing.JSeparator();
        jTFDetail = new javax.swing.JTextField();
        jSeparatorUnderDetail = new javax.swing.JSeparator();
        jLabelDetail = new javax.swing.JLabel();
        jButtonBackAdmindetailUser = new javax.swing.JButton();
        jButtonBackToAdminUserDetail = new javax.swing.JButton();
        jPanelRepairAdminDetailUser = new javax.swing.JPanel();
        jPanelShoeUserSentToAdminForRepair = new javax.swing.JPanel();
        jLabelStatusBikeUserSentTOadmin = new javax.swing.JLabel();
        jComboBoxStatusBike = new javax.swing.JComboBox<>();
        sentRepairScroll = new javax.swing.JScrollPane();
        jTAShowDetaiUserSentRepirlForAdmin = new javax.swing.JTextArea();
        jBTbackToJPanelRepairAdmin = new javax.swing.JButton();
        jBTnextTojPanelRepairing = new javax.swing.JButton();
        jPanelRepairingAdmin = new javax.swing.JPanel();
        jScrollPaneShowDetailUserSentToRepair = new javax.swing.JScrollPane();
        jPNBackGround = new javax.swing.JPanel();
        jButtonRepairForNextToPageNotsuccess = new javax.swing.JButton();
        refreshRepairAdmin = new javax.swing.JButton();
        sharingPage = new javax.swing.JPanel();
        sharingPageTab = new javax.swing.JPanel();
        layerAddItem = new javax.swing.JPanel();
        titleItemId = new javax.swing.JLabel();
        s6 = new javax.swing.JSeparator();
        s7 = new javax.swing.JSeparator();
        titleItemName = new javax.swing.JLabel();
        itemName = new javax.swing.JTextField();
        titleItemCount = new javax.swing.JLabel();
        s8 = new javax.swing.JSeparator();
        titleItemCP = new javax.swing.JLabel();
        itemCP = new javax.swing.JTextField();
        s11 = new javax.swing.JSeparator();
        itemAmount = new javax.swing.JTextField();
        submitButSharing = new javax.swing.JButton();
        cancleButSharing = new javax.swing.JButton();
        itemId = new javax.swing.JTextField();
        pathImgItem = new javax.swing.JLabel();
        notiSharing = new javax.swing.JLabel();
        itemList = new javax.swing.JButton();
        titleItemPic = new javax.swing.JLabel();
        chooseImgItem = new javax.swing.JButton();
        itemListScroll = new javax.swing.JScrollPane();
        itemListPage = new javax.swing.JPanel();
        titleItemList = new javax.swing.JLabel();
        closeBut = new javax.swing.JLabel();
        titleWhat = new javax.swing.JLabel();
        radioType = new javax.swing.JRadioButton();
        radioEquip = new javax.swing.JRadioButton();
        menuSharing = new javax.swing.JPanel();
        menuAdd = new javax.swing.JButton();
        menuEdit = new javax.swing.JButton();
        menuDelete = new javax.swing.JButton();
        backAdd = new javax.swing.JPanel();
        backEdit = new javax.swing.JPanel();
        backDelete = new javax.swing.JPanel();
        newsPage = new javax.swing.JPanel();
        newsList = new javax.swing.JScrollPane();
        showlistNews = new javax.swing.JPanel();
        showNews = new javax.swing.JPanel();
        titletopicNews = new javax.swing.JLabel();
        topicNews = new javax.swing.JTextField();
        describeNews = new javax.swing.JScrollPane();
        describeNewsText = new javax.swing.JTextArea();
        binding = new javax.swing.JPanel();
        cancleUpdateNews = new javax.swing.JButton();
        submitUpdateNews = new javax.swing.JButton();
        closeNews = new javax.swing.JButton();
        jPanelInsertNews = new javax.swing.JPanel();
        insertDetailScroll = new javax.swing.JScrollPane();
        txtAreaNewsDetail = new javax.swing.JTextArea();
        txtDescription1 = new javax.swing.JLabel();
        txtFieldNewsDescription = new javax.swing.JTextField();
        txtDescription = new javax.swing.JLabel();
        cancleButtonNews = new javax.swing.JButton();
        submitButtonNews = new javax.swing.JButton();
        jPanelBackgroundInsertLabel = new javax.swing.JPanel();
        jLabelInsert = new javax.swing.JLabel();
        chooseImgNews = new javax.swing.JButton();
        pathImgNews = new javax.swing.JLabel();
        titleNews = new javax.swing.JLabel();
        jButtonInsert = new javax.swing.JButton();
        showtitleInsertNews = new javax.swing.JLabel();
        userProfilePage = new javax.swing.JPanel();
        editProfileBut = new javax.swing.JButton();
        titleUserId = new javax.swing.JLabel();
        userID = new javax.swing.JLabel();
        imageProfile = new javax.swing.JLabel();
        titleAbout = new javax.swing.JLabel();
        titleNameProfile = new javax.swing.JLabel();
        nameProfile = new javax.swing.JTextField();
        titleGenderProfile = new javax.swing.JLabel();
        genderProfile = new javax.swing.JTextField();
        titlebBirthProfile = new javax.swing.JLabel();
        birthProfile = new javax.swing.JFormattedTextField();
        titleDepartProfile = new javax.swing.JLabel();
        departProfile = new javax.swing.JLabel();
        titleCongenProfile = new javax.swing.JLabel();
        congenProfile = new javax.swing.JTextField();
        titleContact = new javax.swing.JLabel();
        titleEmailProfile = new javax.swing.JLabel();
        emailProfile = new javax.swing.JTextField();
        titleTelProfile = new javax.swing.JLabel();
        telProfile = new javax.swing.JTextField();
        chooseImgProfileBut = new javax.swing.JButton();
        pathImgProfile = new javax.swing.JLabel();
        submitProfile = new javax.swing.JButton();
        warningProfile = new javax.swing.JLabel();
        mainUser = new javax.swing.JPanel();
        barSearch1 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        iconSearch1 = new javax.swing.JLabel();
        title1 = new javax.swing.JLabel();
        search1 = new javax.swing.JTextField();
        backMenuBar1 = new javax.swing.JPanel();
        menuBar1 = new javax.swing.JPanel();
        news1 = new javax.swing.JLabel();
        bikeRepair1 = new javax.swing.JLabel();
        canCounter1 = new javax.swing.JLabel();
        bikeSharing1 = new javax.swing.JLabel();
        history1 = new javax.swing.JLabel();
        profile1 = new javax.swing.JLabel();
        timer1 = new javax.swing.JLabel();
        support1 = new javax.swing.JLabel();
        menu1 = new javax.swing.JLabel();
        arrowDownIcon1 = new javax.swing.JLabel();
        circleMini = new javax.swing.JLabel();
        titleSignout1 = new javax.swing.JLabel();
        circleMini1 = new javax.swing.JLabel();
        barUser1 = new javax.swing.JPanel();
        pic1 = new javax.swing.JLabel();
        name1 = new javax.swing.JLabel();
        backBarUser1 = new javax.swing.JPanel();
        backBarIcon1 = new javax.swing.JPanel();
        barIcon1 = new javax.swing.JPanel();
        iconMenu1 = new javax.swing.JLabel();
        iconBike1 = new javax.swing.JLabel();
        iconProfile1 = new javax.swing.JLabel();
        signout1 = new javax.swing.JButton();
        barNoti1 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        circleNoti = new javax.swing.JLabel();
        iconNoti1 = new javax.swing.JLabel();
        userProfilePage1 = new javax.swing.JPanel();
        editProfileBut1 = new javax.swing.JButton();
        titleUserId1 = new javax.swing.JLabel();
        userID1 = new javax.swing.JLabel();
        imageProfile1 = new javax.swing.JLabel();
        titleAbout1 = new javax.swing.JLabel();
        titleNameProfile1 = new javax.swing.JLabel();
        nameProfile1 = new javax.swing.JTextField();
        titleGenderProfile1 = new javax.swing.JLabel();
        genderProfile1 = new javax.swing.JTextField();
        titlebBirthProfile1 = new javax.swing.JLabel();
        birthProfile1 = new javax.swing.JFormattedTextField();
        titleDepartProfile1 = new javax.swing.JLabel();
        departProfile1 = new javax.swing.JLabel();
        titleCongenProfile1 = new javax.swing.JLabel();
        congenProfile1 = new javax.swing.JTextField();
        titleContact1 = new javax.swing.JLabel();
        titleEmailProfile1 = new javax.swing.JLabel();
        emailProfile1 = new javax.swing.JTextField();
        titleTelProfile1 = new javax.swing.JLabel();
        telProfile1 = new javax.swing.JTextField();
        chooseImgProfileBut1 = new javax.swing.JButton();
        pathImgProfile1 = new javax.swing.JLabel();
        submitProfile1 = new javax.swing.JButton();
        warningProfile1 = new javax.swing.JLabel();
        sharingStep2 = new javax.swing.JPanel();
        textYourCP = new javax.swing.JLabel();
        cpUser = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        textRemain = new javax.swing.JLabel();
        point1 = new javax.swing.JLabel();
        textCpUse = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        cpUse = new javax.swing.JLabel();
        point2 = new javax.swing.JLabel();
        pointRemain = new javax.swing.JLabel();
        point3 = new javax.swing.JLabel();
        detailBox = new javax.swing.JPanel();
        detailData = new javax.swing.JLabel();
        backStep1 = new javax.swing.JButton();
        cancleBut = new javax.swing.JButton();
        borrowBut = new javax.swing.JButton();
        s9 = new javax.swing.JLabel();
        supportPage1 = new javax.swing.JPanel();
        barTitle1 = new javax.swing.JPanel();
        titleSupport1 = new javax.swing.JLabel();
        bodySupport1 = new javax.swing.JPanel();
        jLBShowingResult1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLBWhatsearch1 = new javax.swing.JLabel();
        resultScroll1 = new javax.swing.JScrollPane();
        jTAShowyouSearch1 = new javax.swing.JTextArea();
        contactScroll1 = new javax.swing.JScrollPane();
        jTAContact1 = new javax.swing.JTextArea();
        titleContactSupport1 = new javax.swing.JLabel();
        historyPage1 = new javax.swing.JPanel();
        jPanelHeadHistory = new javax.swing.JPanel();
        jLabelHistoryText = new javax.swing.JLabel();
        jPanelHistory = new javax.swing.JPanel();
        iconStart = new javax.swing.JLabel();
        iconAction = new javax.swing.JLabel();
        iconItem = new javax.swing.JLabel();
        iconend = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPaneHistory = new javax.swing.JScrollPane();
        jPanelTableHistory = new javax.swing.JPanel();
        timePageF = new javax.swing.JPanel();
        textNotHis = new javax.swing.JLabel();
        iconNotHis = new javax.swing.JLabel();
        timePageT = new javax.swing.JPanel();
        timewatch = new javax.swing.JPanel();
        titleNow = new javax.swing.JLabel();
        timeLeftShow = new javax.swing.JLabel();
        endTime = new javax.swing.JLabel();
        iconClockBig = new javax.swing.JLabel();
        itemListShow = new javax.swing.JPanel();
        itemListShowInsidee = new javax.swing.JPanel();
        listShow = new javax.swing.JLabel();
        titleItemListShow = new javax.swing.JLabel();
        timeupPage = new javax.swing.JPanel();
        titleItemTimeUp = new javax.swing.JLabel();
        timeupInside = new javax.swing.JPanel();
        listTimeup = new javax.swing.JLabel();
        newsPage1 = new javax.swing.JPanel();
        listNewsScroll = new javax.swing.JScrollPane();
        listNewsPage = new javax.swing.JPanel();
        titleNewsPageUser = new javax.swing.JLabel();
        jPanelShowNewsAfterClick = new javax.swing.JPanel();
        jLabelShowTopicAfterClick = new javax.swing.JLabel();
        jScrollPaneInTextAreaNewsDetail = new javax.swing.JScrollPane();
        jTextAreaShowDetailNewsAfterClick = new javax.swing.JTextArea();
        closeNews1 = new javax.swing.JButton();
        imgNews = new javax.swing.JLabel();
        repairPage1 = new javax.swing.JPanel();
        jPanelRepairUserSentToAdmin = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabelBrandBike = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jTFWhat = new javax.swing.JTextField();
        jLabelBrandBike1 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jTFbike = new javax.swing.JTextField();
        jLabelBrandBike2 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jTFColor = new javax.swing.JTextField();
        jLabelWarningUserInputColorBike = new javax.swing.JLabel();
        jLabelWarningUserInputWhyRepair = new javax.swing.JLabel();
        jLabelWarningUserInputModelBike = new javax.swing.JLabel();
        jBtToFollowingRepair = new javax.swing.JButton();
        titlenextFollingRepairUser = new javax.swing.JLabel();
        jButtonFollowingRepair = new javax.swing.JButton();
        jPanelRepairUserFollowRepairing = new javax.swing.JPanel();
        jBtBackRepairUserSentToAdmin = new javax.swing.JButton();
        jPanelHeadBikeRepairing = new javax.swing.JPanel();
        jLabelBikeRepairing = new javax.swing.JLabel();
        jLabelStatus = new javax.swing.JLabel();
        jLabelAskingText = new javax.swing.JLabel();
        jLabelRepairing = new javax.swing.JLabel();
        jLabelRecieving = new javax.swing.JLabel();
        jPanelRecieving = new javax.swing.JPanel();
        jLabelRecievingText = new javax.swing.JLabel();
        textRecieving = new javax.swing.JLabel();
        jLabelIconBikeInRecieving = new javax.swing.JLabel();
        jPanelRepairing = new javax.swing.JPanel();
        jLabelRepairingText = new javax.swing.JLabel();
        textRepair = new javax.swing.JLabel();
        jPnTimeDetail = new javax.swing.JPanel();
        jLabelTime = new javax.swing.JLabel();
        jPanelAsking = new javax.swing.JPanel();
        jLabelAskingTextInJPanelAsking = new javax.swing.JLabel();
        textAsking = new javax.swing.JLabel();
        jLabelStatusRepairIcon = new javax.swing.JLabel();
        titleBackRepairUser = new javax.swing.JLabel();
        cpPage1 = new javax.swing.JPanel();
        showCp = new javax.swing.JLabel();
        circleCp = new javax.swing.JLabel();
        History = new javax.swing.JPanel();
        detailBoxHistory = new javax.swing.JPanel();
        poinsHis = new javax.swing.JLabel();
        actionHis = new javax.swing.JLabel();
        titleHistory = new javax.swing.JLabel();
        point4 = new javax.swing.JLabel();
        sharingScroll = new javax.swing.JScrollPane();
        sharingStep1 = new javax.swing.JPanel();
        nextStep = new javax.swing.JButton();
        s1 = new javax.swing.JLabel();
        isCp = new javax.swing.JLabel();
        registerPage = new javax.swing.JPanel();
        backSigninRegis = new javax.swing.JLabel();
        jPanelSignUp = new javax.swing.JPanel();
        jLbSignUp = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTfFirstName = new javax.swing.JTextField();
        jLbLastName = new javax.swing.JLabel();
        jLbDateOfBirth = new javax.swing.JLabel();
        jLabelStarLN = new javax.swing.JLabel();
        jComboBoxPosition = new javax.swing.JComboBox<>();
        jLbGender = new javax.swing.JLabel();
        jLbPassWord = new javax.swing.JLabel();
        jTextFieldSurname = new javax.swing.JTextField();
        jLbFirstName = new javax.swing.JLabel();
        jLbStarId = new javax.swing.JLabel();
        jLbStarFirstName = new javax.swing.JLabel();
        jLbStarGender = new javax.swing.JLabel();
        jLbStarBirthDay = new javax.swing.JLabel();
        jLbStarPassword = new javax.swing.JLabel();
        jLbPosition = new javax.swing.JLabel();
        jLbStarPosition = new javax.swing.JLabel();
        jLbCongenitialDisease = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLbTelophone = new javax.swing.JLabel();
        jLbStarTelophone = new javax.swing.JLabel();
        jTextFieldConDisease = new javax.swing.JTextField();
        jLbEmail = new javax.swing.JLabel();
        jLbStarEmail = new javax.swing.JLabel();
        jPasswordSignUp = new javax.swing.JPasswordField();
        jPasswordConfirmPassSignup = new javax.swing.JPasswordField();
        jLbConfirmPassWord = new javax.swing.JLabel();
        jLbStarConfirmPass = new javax.swing.JLabel();
        jButtonSignUp = new javax.swing.JButton();
        jRadioButtonGenderFemale = new javax.swing.JRadioButton();
        jRadioButtonGenderMale = new javax.swing.JRadioButton();
        jLabelPictureProfileRegister = new javax.swing.JLabel();
        jButtonChooseFileForUpload = new javax.swing.JButton();
        jLabelPartPictureUserUpload = new javax.swing.JLabel();
        jLbId = new javax.swing.JLabel();
        jLabelEmailNotCorrect = new javax.swing.JLabel();
        jLabelNoticSurname = new javax.swing.JLabel();
        jLabelifLengthId = new javax.swing.JLabel();
        jLabelNoticNewPass = new javax.swing.JLabel();
        jLabelNoticName = new javax.swing.JLabel();
        jLabelNoticGender = new javax.swing.JLabel();
        jLabelNoticBirthDate = new javax.swing.JLabel();
        jLabelNoticPosition = new javax.swing.JLabel();
        jLabelNoticTel = new javax.swing.JLabel();
        jLabelNoticOldPass = new javax.swing.JLabel();
        jDateChooserBirthDate = new com.toedter.calendar.JDateChooser();
        jFormatTextFieldForId = new javax.swing.JFormattedTextField();
        jFormattedTelophone = new javax.swing.JFormattedTextField();
        Backgroung2 = new javax.swing.JLabel();
        login = new javax.swing.JPanel();
        GreenSociety = new javax.swing.JLabel();
        iconEmail = new javax.swing.JLabel();
        iconKey = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        sEmailG = new javax.swing.JLabel();
        sEmailB = new javax.swing.JLabel();
        showPass = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        sPassG = new javax.swing.JLabel();
        sPassB = new javax.swing.JLabel();
        warning = new javax.swing.JLabel();
        signin = new javax.swing.JButton();
        backLogin = new javax.swing.JLabel();
        menuSignup = new javax.swing.JLabel();
        forgotPass = new javax.swing.JLabel();
        titleSignup = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();
        forgotPassPage = new javax.swing.JPanel();
        inputForgot = new javax.swing.JPanel();
        titleForgotPass = new javax.swing.JLabel();
        s10 = new javax.swing.JSeparator();
        titleEmail = new javax.swing.JLabel();
        forgotEmail = new javax.swing.JTextField();
        titleNewPass = new javax.swing.JLabel();
        titleConPass = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        star1 = new javax.swing.JLabel();
        star2 = new javax.swing.JLabel();
        star3 = new javax.swing.JLabel();
        submitPass = new javax.swing.JButton();
        errorForgot = new javax.swing.JLabel();
        backSignin = new javax.swing.JLabel();
        Background1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        barSearch.setBackground(new java.awt.Color(13, 24, 35));
        barSearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        barSearch.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 120, 40));

        iconSearch.setFont(new java.awt.Font("Leelawadee UI", 0, 20)); // NOI18N
        iconSearch.setForeground(new java.awt.Color(255, 255, 255));
        iconSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/search.png"))); // NOI18N
        iconSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconSearchMouseClicked(evt);
            }
        });
        barSearch.add(iconSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 60, 50));

        title.setBackground(new java.awt.Color(0, 0, 0));
        title.setFont(new java.awt.Font("Leelawadee", 0, 30)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("GREEN  SOCIETY");
        title.setToolTipText("");
        barSearch.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 60));

        search.setBackground(new java.awt.Color(13, 24, 35));
        search.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        search.setForeground(new java.awt.Color(255, 255, 255));
        search.setText("Search");
        search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFocusLost(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchKeyPressed(evt);
            }
        });
        barSearch.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 120, 30));

        mainAdmin.add(barSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 900, 60));

        backMenuBar.setBackground(new java.awt.Color(22, 31, 39));
        backMenuBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuBar.setBackground(new java.awt.Color(13, 24, 35));
        menuBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        news.setBackground(new java.awt.Color(0, 0, 0));
        news.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        news.setForeground(new java.awt.Color(255, 255, 255));
        news.setText("     News");
        news.setToolTipText("");
        news.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        news.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        news.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newsMouseClicked(evt);
            }
        });
        menuBar.add(news, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 140, 20));

        bikeRepair.setBackground(new java.awt.Color(0, 0, 0));
        bikeRepair.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        bikeRepair.setForeground(new java.awt.Color(255, 255, 255));
        bikeRepair.setText("     Bike Repairing");
        bikeRepair.setToolTipText("");
        bikeRepair.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        bikeRepair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bikeRepair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bikeRepairMouseClicked(evt);
            }
        });
        menuBar.add(bikeRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 140, 20));

        canCounter.setBackground(new java.awt.Color(0, 0, 0));
        canCounter.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        canCounter.setForeground(new java.awt.Color(255, 255, 255));
        canCounter.setText("     Can Counter");
        canCounter.setToolTipText("");
        canCounter.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        canCounter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        canCounter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                canCounterMouseClicked(evt);
            }
        });
        menuBar.add(canCounter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 140, 20));

        bikeSharing.setBackground(new java.awt.Color(255, 255, 255));
        bikeSharing.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        bikeSharing.setForeground(new java.awt.Color(255, 255, 255));
        bikeSharing.setText("     Bike Sharing");
        bikeSharing.setToolTipText("");
        bikeSharing.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        bikeSharing.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bikeSharing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bikeSharingMouseClicked(evt);
            }
        });
        menuBar.add(bikeSharing, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 140, 20));

        history.setBackground(new java.awt.Color(0, 0, 0));
        history.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        history.setForeground(new java.awt.Color(255, 255, 255));
        history.setText("     History");
        history.setToolTipText("");
        history.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        history.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        history.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                historyMouseClicked(evt);
            }
        });
        menuBar.add(history, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 140, 20));

        profile.setBackground(new java.awt.Color(0, 0, 0));
        profile.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        profile.setForeground(new java.awt.Color(255, 255, 255));
        profile.setText("     PROFILE");
        profile.setToolTipText("");
        profile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileMouseClicked(evt);
            }
        });
        menuBar.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 140, 30));

        menu.setBackground(new java.awt.Color(0, 0, 0));
        menu.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        menu.setForeground(new java.awt.Color(255, 255, 255));
        menu.setText("MENU");
        menu.setToolTipText("");
        menuBar.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 50, 30));

        timer.setBackground(new java.awt.Color(0, 0, 0));
        timer.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        timer.setForeground(new java.awt.Color(255, 255, 255));
        timer.setText("     Timer");
        timer.setToolTipText("");
        timer.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        timer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        timer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timerMouseClicked(evt);
            }
        });
        menuBar.add(timer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 140, 20));

        support.setBackground(new java.awt.Color(0, 0, 0));
        support.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        support.setForeground(new java.awt.Color(255, 255, 255));
        support.setText("     Support");
        support.setToolTipText("");
        support.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        support.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        support.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supportMouseClicked(evt);
            }
        });
        menuBar.add(support, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 140, 20));

        titleSignout.setBackground(new java.awt.Color(0, 0, 0));
        titleSignout.setFont(new java.awt.Font("Leelawadee", 0, 13)); // NOI18N
        titleSignout.setForeground(new java.awt.Color(255, 255, 255));
        titleSignout.setText("     SIGN OUT");
        titleSignout.setToolTipText("");
        menuBar.add(titleSignout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 140, 30));

        arrowDownIcon.setBackground(new java.awt.Color(0, 0, 0));
        arrowDownIcon.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        arrowDownIcon.setForeground(new java.awt.Color(255, 255, 255));
        arrowDownIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        arrowDownIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/chevron-arrow-down.png"))); // NOI18N
        arrowDownIcon.setToolTipText("");
        menuBar.add(arrowDownIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 40, 30));

        backMenuBar.add(menuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 480));

        mainAdmin.add(backMenuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 150, 480));

        barUser.setBackground(new java.awt.Color(19, 175, 248));
        barUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barUser.add(pic, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        name.setBackground(new java.awt.Color(0, 0, 0));
        name.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        name.setForeground(new java.awt.Color(13, 24, 35));
        name.setText("NAME");
        name.setToolTipText("");
        name.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        barUser.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 130, 50));

        position.setBackground(new java.awt.Color(0, 0, 0));
        position.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        position.setForeground(new java.awt.Color(255, 255, 255));
        position.setText("Student");
        position.setToolTipText("");
        position.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        barUser.add(position, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 130, 50));

        mainAdmin.add(barUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 200, 60));

        backBarUser.setBackground(new java.awt.Color(55, 200, 255));
        backBarUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        mainAdmin.add(backBarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 210, 60));

        backBarIcon.setBackground(new java.awt.Color(55, 200, 255));
        backBarIcon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        barIcon.setBackground(new java.awt.Color(19, 175, 248));
        barIcon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/menu.png"))); // NOI18N
        barIcon.add(iconMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 50, 30));

        iconBike.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconBike.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/bike.png"))); // NOI18N
        barIcon.add(iconBike, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 50, 40));

        iconProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/profile-user.png"))); // NOI18N
        barIcon.add(iconProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 50, 50));

        signout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/signout.png"))); // NOI18N
        signout.setContentAreaFilled(false);
        signout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signoutMouseExited(evt);
            }
        });
        signout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signoutActionPerformed(evt);
            }
        });
        barIcon.add(signout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 50, 50));

        backBarIcon.add(barIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 540));

        mainAdmin.add(backBarIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 540));

        barNoti.setBackground(new java.awt.Color(13, 24, 35));
        barNoti.setLayout(null);
        mainAdmin.add(barNoti, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 770, 60));

        timerPage.setBackground(new java.awt.Color(25, 41, 65));
        timerPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectItemReturn.setBackground(new java.awt.Color(25, 41, 65));
        selectItemReturn.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Select Items", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Leelawadee", 0, 14), new java.awt.Color(19, 175, 248))); // NOI18N
        selectItemReturn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cancleReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleMini.png"))); // NOI18N
        cancleReturn.setContentAreaFilled(false);
        cancleReturn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancleReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancleReturnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancleReturnMouseExited(evt);
            }
        });
        cancleReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancleReturnActionPerformed(evt);
            }
        });
        selectItemReturn.add(cancleReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 181, 90, 50));

        returnDateBorrowing.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        returnDateBorrowing.setForeground(new java.awt.Color(102, 255, 102));
        returnDateBorrowing.setText("Return : 20/3/2017  18:00");
        returnDateBorrowing.setToolTipText("");
        selectItemReturn.add(returnDateBorrowing, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 200, 40));

        timerPage.add(selectItemReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 480, 240));

        userDetail.setBackground(new java.awt.Color(22, 31, 39));
        userDetail.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Name : John", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Leelawadee", 0, 16), new java.awt.Color(0, 153, 255))); // NOI18N
        userDetail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeUserDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/delete-button.png"))); // NOI18N
        closeUserDetail.setContentAreaFilled(false);
        closeUserDetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeUserDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeUserDetailActionPerformed(evt);
            }
        });
        userDetail.add(closeUserDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(497, 10, 50, 40));

        timerPage.add(userDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 40, 550, 340));

        timewatch1.setBackground(new java.awt.Color(255, 255, 255));
        timewatch1.setEnabled(false);
        timewatch1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleNow1.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        titleNow1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleNow1.setText("Now:");
        timewatch1.add(titleNow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 60, 40));

        timeLeftShow1.setFont(new java.awt.Font("Leelawadee", 0, 28)); // NOI18N
        timeLeftShow1.setForeground(new java.awt.Color(255, 51, 51));
        timewatch1.add(timeLeftShow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 460, 40));

        endTime1.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        timewatch1.add(endTime1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 490, 20));

        iconClockBig1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconClockBig1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/clockBig.png"))); // NOI18N
        timewatch1.add(iconClockBig1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 130));

        timerPage.add(timewatch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 660, 130));
        timerPage.add(listUserBorrowing, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 620, 260));

        mainAdmin.add(timerPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 420));

        historyPage.setBackground(new java.awt.Color(25, 41, 65));
        historyPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelHeadHistory2.setBackground(new java.awt.Color(76, 81, 86));
        jPanelHeadHistory2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHistoryText2.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        jLabelHistoryText2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHistoryText2.setText("History");
        jPanelHeadHistory2.add(jLabelHistoryText2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 50));

        historyPage.add(jPanelHeadHistory2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 50));

        jPanelShowGraph.setBackground(new java.awt.Color(25, 41, 65));
        jPanelShowGraph.setLayout(new java.awt.BorderLayout());
        historyPage.add(jPanelShowGraph, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 690, 300));

        mainAdmin.add(historyPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 421));

        supportPage.setBackground(new java.awt.Color(25, 41, 65));
        supportPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bodySupport.setBackground(new java.awt.Color(25, 41, 65));
        bodySupport.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLBShowingResult.setBackground(new java.awt.Color(255, 255, 255));
        jLBShowingResult.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLBShowingResult.setForeground(new java.awt.Color(255, 255, 255));
        jLBShowingResult.setText("Showing results for:");
        bodySupport.add(jLBShowingResult, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 180, 30));
        bodySupport.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 220, 10));

        jLBWhatsearch.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLBWhatsearch.setForeground(new java.awt.Color(255, 255, 255));
        bodySupport.add(jLBWhatsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 220, 30));

        jTAShowyouSearch.setEditable(false);
        jTAShowyouSearch.setBackground(new java.awt.Color(25, 41, 65));
        jTAShowyouSearch.setColumns(20);
        jTAShowyouSearch.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jTAShowyouSearch.setForeground(new java.awt.Color(255, 255, 255));
        jTAShowyouSearch.setRows(5);
        jTAShowyouSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(19, 175, 248), 1, true));
        resultScroll.setViewportView(jTAShowyouSearch);

        bodySupport.add(resultScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 420, 300));

        jTAContact.setEditable(false);
        jTAContact.setBackground(new java.awt.Color(25, 41, 65));
        jTAContact.setColumns(20);
        jTAContact.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jTAContact.setForeground(new java.awt.Color(255, 255, 255));
        jTAContact.setRows(5);
        jTAContact.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(19, 175, 248), 1, true));
        contactScroll.setViewportView(jTAContact);

        bodySupport.add(contactScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 270, 300));

        titleContactSupport.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        titleContactSupport.setForeground(new java.awt.Color(255, 255, 255));
        titleContactSupport.setText("Contact Info :");
        bodySupport.add(titleContactSupport, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, -1));

        supportPage.add(bodySupport, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 770, 370));

        insertSupportPanel.setBackground(new java.awt.Color(25, 41, 65));
        insertSupportPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleContent.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        titleContent.setForeground(new java.awt.Color(255, 255, 255));
        titleContent.setText("Content Manual : ");
        insertSupportPanel.add(titleContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 160, 30));
        insertSupportPanel.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 440, 10));

        insertSupporTitle.setBackground(new java.awt.Color(25, 41, 65));
        insertSupporTitle.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        insertSupporTitle.setForeground(new java.awt.Color(55, 200, 255));
        insertSupportPanel.add(insertSupporTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 440, 30));

        titleInsertSupport.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        titleInsertSupport.setForeground(new java.awt.Color(255, 255, 255));
        titleInsertSupport.setText("Title Manual : ");
        insertSupportPanel.add(titleInsertSupport, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 120, 30));

        submitSupport.setBackground(new java.awt.Color(19, 175, 248));
        submitSupport.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        submitSupport.setForeground(new java.awt.Color(255, 255, 255));
        submitSupport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMini.png"))); // NOI18N
        submitSupport.setContentAreaFilled(false);
        submitSupport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitSupportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitSupportMouseExited(evt);
            }
        });
        submitSupport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitSupportActionPerformed(evt);
            }
        });
        insertSupportPanel.add(submitSupport, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 320, 100, 50));

        insertContentSup.setBackground(new java.awt.Color(25, 41, 65));
        insertContentSup.setColumns(20);
        insertContentSup.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        insertContentSup.setForeground(new java.awt.Color(255, 255, 255));
        insertContentSup.setLineWrap(true);
        insertContentSup.setRows(5);
        insertContentSup.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(19, 175, 248), 1, true));
        insertSupportContentArea.setViewportView(insertContentSup);

        insertSupportPanel.add(insertSupportContentArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 530, 250));

        waringInsertSupport.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        waringInsertSupport.setForeground(new java.awt.Color(255, 51, 51));
        waringInsertSupport.setText("Please enter title or content.");
        waringInsertSupport.setToolTipText("");
        insertSupportPanel.add(waringInsertSupport, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 300, -1, -1));

        cancleInsertSup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancle.png"))); // NOI18N
        cancleInsertSup.setContentAreaFilled(false);
        cancleInsertSup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancleInsertSupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancleInsertSupMouseExited(evt);
            }
        });
        cancleInsertSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancleInsertSupActionPerformed(evt);
            }
        });
        insertSupportPanel.add(cancleInsertSup, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 60, 50));

        titleCancleInsertSup.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        titleCancleInsertSup.setForeground(new java.awt.Color(255, 255, 255));
        titleCancleInsertSup.setText("Cancle");
        insertSupportPanel.add(titleCancleInsertSup, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, 40, 20));

        supportPage.add(insertSupportPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 770, 370));

        barTitle.setBackground(new java.awt.Color(15, 30, 52));
        barTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleSupport.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        titleSupport.setForeground(new java.awt.Color(19, 175, 248));
        titleSupport.setText("Support");
        barTitle.add(titleSupport, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 50));

        newManual.setBackground(new java.awt.Color(19, 175, 248));
        newManual.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        newManual.setForeground(new java.awt.Color(255, 255, 255));
        newManual.setText("New Manual");
        newManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newManualActionPerformed(evt);
            }
        });
        barTitle.add(newManual, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, -1, 30));

        supportPage.add(barTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 50));

        mainAdmin.add(supportPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 420));

        cpPage.setBackground(new java.awt.Color(25, 41, 65));
        cpPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleCountCan.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        titleCountCan.setForeground(new java.awt.Color(255, 255, 255));
        titleCountCan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleCountCan.setText("Count of Can :");
        cpPage.add(titleCountCan, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 130, 40));

        titleNameUser.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        titleNameUser.setForeground(new java.awt.Color(255, 255, 255));
        titleNameUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleNameUser.setText("Firstname User :");
        cpPage.add(titleNameUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 160, 60));

        s3.setForeground(new java.awt.Color(255, 255, 255));
        cpPage.add(s3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 240, 10));

        s4.setForeground(new java.awt.Color(255, 255, 255));
        cpPage.add(s4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, 240, 10));

        nameOfUser.setBackground(new java.awt.Color(25, 41, 65));
        nameOfUser.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        nameOfUser.setForeground(new java.awt.Color(153, 153, 153));
        nameOfUser.setText("    Name");
        nameOfUser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nameOfUserFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameOfUserFocusLost(evt);
            }
        });
        cpPage.add(nameOfUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 230, 30));

        countOfCan.setBackground(new java.awt.Color(25, 41, 65));
        countOfCan.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        countOfCan.setForeground(new java.awt.Color(153, 153, 153));
        countOfCan.setText("    Count");
        countOfCan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                countOfCanFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                countOfCanFocusLost(evt);
            }
        });
        cpPage.add(countOfCan, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 240, 30));

        submitBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submit.png"))); // NOI18N
        submitBut.setContentAreaFilled(false);
        submitBut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        submitBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitButMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitButMouseExited(evt);
            }
        });
        submitBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButActionPerformed(evt);
            }
        });
        cpPage.add(submitBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 150, -1));

        cancleAdminBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleBut.png"))); // NOI18N
        cancleAdminBut.setContentAreaFilled(false);
        cancleAdminBut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancleAdminBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancleAdminButMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancleAdminButMouseExited(evt);
            }
        });
        cancleAdminBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancleAdminButActionPerformed(evt);
            }
        });
        cpPage.add(cancleAdminBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 160, -1));

        titleLastname.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        titleLastname.setForeground(new java.awt.Color(255, 255, 255));
        titleLastname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLastname.setText("Lastname User :");
        cpPage.add(titleLastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 160, 60));

        surnameOfUser.setBackground(new java.awt.Color(25, 41, 65));
        surnameOfUser.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        surnameOfUser.setForeground(new java.awt.Color(153, 153, 153));
        surnameOfUser.setText("    Surname");
        surnameOfUser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                surnameOfUserFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                surnameOfUserFocusLost(evt);
            }
        });
        cpPage.add(surnameOfUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 230, 30));

        s5.setForeground(new java.awt.Color(255, 255, 255));
        cpPage.add(s5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 240, 10));

        mainAdmin.add(cpPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 421));

        repairPage.setBackground(new java.awt.Color(25, 41, 65));
        repairPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelHeadBikeRepairForRepairForAdmin.setBackground(new java.awt.Color(76, 81, 86));
        jPanelHeadBikeRepairForRepairForAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBikeRepairingForRepairingAdmin.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        jLabelBikeRepairingForRepairingAdmin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBikeRepairingForRepairingAdmin.setText("Bike Repairing");
        jPanelHeadBikeRepairForRepairForAdmin.add(jLabelBikeRepairingForRepairingAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 50));

        repairPage.add(jPanelHeadBikeRepairForRepairForAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 50));

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

        jPanelShowRepair.add(jScrollPaneShowDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 490, 240));

        jBTNextToPanelNotSuccess.setContentAreaFilled(false);
        jBTNextToPanelNotSuccess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTNextToPanelNotSuccessActionPerformed(evt);
            }
        });
        jPanelShowRepair.add(jBTNextToPanelNotSuccess, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, -1, -1));

        jBTBackToRepairShowTime.setContentAreaFilled(false);
        jBTBackToRepairShowTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTBackToRepairShowTimeActionPerformed(evt);
            }
        });
        jPanelShowRepair.add(jBTBackToRepairShowTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));

        repairPage.add(jPanelShowRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 770, 370));

        jPanelRepairingNotSuccess.setBackground(new java.awt.Color(25, 41, 65));
        jPanelRepairingNotSuccess.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Leelawadee", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Username | Action | Remaining");
        jPanelRepairingNotSuccess.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 270, 30));

        jPanelBikeRepairNotSuccess.setBackground(new java.awt.Color(25, 41, 65));
        jPanelBikeRepairNotSuccess.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jScrollPaneBikeRepairingNotSuccess.setViewportView(jPanelBikeRepairNotSuccess);

        jPanelRepairingNotSuccess.add(jScrollPaneBikeRepairingNotSuccess, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 740, 290));

        jButtonRefreshRepairNotSuccess.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jButtonRefreshRepairNotSuccess.setText("Refresh");
        jButtonRefreshRepairNotSuccess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshRepairNotSuccessActionPerformed(evt);
            }
        });
        jPanelRepairingNotSuccess.add(jButtonRefreshRepairNotSuccess, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, -1, -1));

        jButtonToRepairAdmin.setBackground(new java.awt.Color(102, 102, 102));
        jButtonToRepairAdmin.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jButtonToRepairAdmin.setForeground(new java.awt.Color(255, 255, 255));
        jButtonToRepairAdmin.setText("RepairAdmin");
        jButtonToRepairAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonToRepairAdminActionPerformed(evt);
            }
        });
        jPanelRepairingNotSuccess.add(jButtonToRepairAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 140, -1));

        repairPage.add(jPanelRepairingNotSuccess, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 770, 370));

        jPanelRepairingSetRepair.setBackground(new java.awt.Color(25, 41, 65));
        jPanelRepairingSetRepair.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleSetPloblem.setBackground(new java.awt.Color(255, 255, 255));
        titleSetPloblem.setFont(new java.awt.Font("Leelawadee", 0, 36)); // NOI18N
        titleSetPloblem.setForeground(new java.awt.Color(255, 51, 51));
        titleSetPloblem.setText("Insert Problem");
        jPanelRepairingSetRepair.add(titleSetPloblem, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 300, 60));

        jBTnextToShowTime.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jBTnextToShowTime.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/right arrow.png"))); // NOI18N
        jBTnextToShowTime.setContentAreaFilled(false);
        jBTnextToShowTime.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBTnextToShowTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTnextToShowTimeActionPerformed(evt);
            }
        });
        jPanelRepairingSetRepair.add(jBTnextToShowTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 360, 80, 60));

        jPanelSetDetailRepairForAdmin.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSetDetailRepairForAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelProblem.setBackground(new java.awt.Color(0, 0, 0));
        jLabelProblem.setFont(new java.awt.Font("Leelawadee", 1, 14)); // NOI18N
        jLabelProblem.setForeground(new java.awt.Color(51, 51, 51));
        jLabelProblem.setText("Problem :");
        jLabelProblem.setToolTipText("");
        jLabelProblem.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSetDetailRepairForAdmin.add(jLabelProblem, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 45, 70, 30));

        jTFProblem.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jTFProblem.setBorder(null);
        jPanelSetDetailRepairForAdmin.add(jTFProblem, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 460, 30));

        jSeparatorUnderProblem.setBackground(new java.awt.Color(0, 0, 0));
        jPanelSetDetailRepairForAdmin.add(jSeparatorUnderProblem, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 460, 10));

        jTFDetail.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jTFDetail.setBorder(null);
        jPanelSetDetailRepairForAdmin.add(jTFDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 460, 28));

        jSeparatorUnderDetail.setBackground(new java.awt.Color(0, 0, 0));
        jPanelSetDetailRepairForAdmin.add(jSeparatorUnderDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 460, 23));

        jLabelDetail.setBackground(new java.awt.Color(0, 0, 0));
        jLabelDetail.setFont(new java.awt.Font("Leelawadee", 1, 14)); // NOI18N
        jLabelDetail.setForeground(new java.awt.Color(51, 51, 51));
        jLabelDetail.setText(" Detail :");
        jLabelDetail.setToolTipText("");
        jLabelDetail.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSetDetailRepairForAdmin.add(jLabelDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 60, 20));

        jPanelRepairingSetRepair.add(jPanelSetDetailRepairForAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 600, 180));

        jButtonBackAdmindetailUser.setContentAreaFilled(false);
        jButtonBackAdmindetailUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackAdmindetailUserActionPerformed(evt);
            }
        });
        jPanelRepairingSetRepair.add(jButtonBackAdmindetailUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, -1, -1));

        jButtonBackToAdminUserDetail.setContentAreaFilled(false);
        jButtonBackToAdminUserDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackToAdminUserDetailActionPerformed(evt);
            }
        });
        jPanelRepairingSetRepair.add(jButtonBackToAdminUserDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 80, -1));

        repairPage.add(jPanelRepairingSetRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 420));

        jPanelRepairAdminDetailUser.setBackground(new java.awt.Color(25, 41, 65));
        jPanelRepairAdminDetailUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelShoeUserSentToAdminForRepair.setBackground(new java.awt.Color(34, 34, 38));
        jPanelShoeUserSentToAdminForRepair.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelStatusBikeUserSentTOadmin.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        jLabelStatusBikeUserSentTOadmin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelStatusBikeUserSentTOadmin.setText("Customer brought bicycle to Green Society :");
        jPanelShoeUserSentToAdminForRepair.add(jLabelStatusBikeUserSentTOadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 330, 30));

        jComboBoxStatusBike.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jComboBoxStatusBike.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No", "Yes" }));
        jPanelShoeUserSentToAdminForRepair.add(jComboBoxStatusBike, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 70, 30));

        jTAShowDetaiUserSentRepirlForAdmin.setEditable(false);
        jTAShowDetaiUserSentRepirlForAdmin.setBackground(new java.awt.Color(54, 54, 56));
        jTAShowDetaiUserSentRepirlForAdmin.setColumns(20);
        jTAShowDetaiUserSentRepirlForAdmin.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jTAShowDetaiUserSentRepirlForAdmin.setForeground(new java.awt.Color(255, 255, 255));
        jTAShowDetaiUserSentRepirlForAdmin.setLineWrap(true);
        jTAShowDetaiUserSentRepirlForAdmin.setRows(5);
        jTAShowDetaiUserSentRepirlForAdmin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jTAShowDetaiUserSentRepirlForAdmin.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTAShowDetaiUserSentRepirlForAdminAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        sentRepairScroll.setViewportView(jTAShowDetaiUserSentRepirlForAdmin);

        jPanelShoeUserSentToAdminForRepair.add(sentRepairScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 540, 130));

        jPanelRepairAdminDetailUser.add(jPanelShoeUserSentToAdminForRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 600, 250));

        jBTbackToJPanelRepairAdmin.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jBTbackToJPanelRepairAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/left arrow.png"))); // NOI18N
        jBTbackToJPanelRepairAdmin.setContentAreaFilled(false);
        jBTbackToJPanelRepairAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBTbackToJPanelRepairAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTbackToJPanelRepairAdminActionPerformed(evt);
            }
        });
        jPanelRepairAdminDetailUser.add(jBTbackToJPanelRepairAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 70, 50));

        jBTnextTojPanelRepairing.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jBTnextTojPanelRepairing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/right arrow.png"))); // NOI18N
        jBTnextTojPanelRepairing.setContentAreaFilled(false);
        jBTnextTojPanelRepairing.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBTnextTojPanelRepairing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTnextTojPanelRepairingActionPerformed(evt);
            }
        });
        jPanelRepairAdminDetailUser.add(jBTnextTojPanelRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 320, 70, 50));

        repairPage.add(jPanelRepairAdminDetailUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 770, 370));

        jPanelRepairingAdmin.setBackground(new java.awt.Color(25, 41, 65));
        jPanelRepairingAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPNBackGround.setBackground(new java.awt.Color(25, 41, 65));
        jPNBackGround.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jScrollPaneShowDetailUserSentToRepair.setViewportView(jPNBackGround);

        jPanelRepairingAdmin.add(jScrollPaneShowDetailUserSentToRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 770, 370));

        jButtonRepairForNextToPageNotsuccess.setBackground(new java.awt.Color(0, 0, 0));
        jButtonRepairForNextToPageNotsuccess.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jButtonRepairForNextToPageNotsuccess.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRepairForNextToPageNotsuccess.setText("RepiarForNotsuccess");
        jButtonRepairForNextToPageNotsuccess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRepairForNextToPageNotsuccessActionPerformed(evt);
            }
        });
        jPanelRepairingAdmin.add(jButtonRepairForNextToPageNotsuccess, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 160, 30));

        refreshRepairAdmin.setBackground(new java.awt.Color(51, 51, 51));
        refreshRepairAdmin.setForeground(new java.awt.Color(19, 175, 248));
        refreshRepairAdmin.setText("Refresh");
        refreshRepairAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshRepairAdminActionPerformed(evt);
            }
        });
        jPanelRepairingAdmin.add(refreshRepairAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 30));

        repairPage.add(jPanelRepairingAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 420));

        mainAdmin.add(repairPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 421));

        sharingPage.setBackground(new java.awt.Color(25, 41, 65));
        sharingPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sharingPageTab.setBackground(new java.awt.Color(25, 41, 65));
        sharingPageTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        layerAddItem.setBackground(new java.awt.Color(13, 24, 35));
        layerAddItem.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Add Item", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Leelawadee", 0, 20), new java.awt.Color(3, 153, 223))); // NOI18N
        layerAddItem.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleItemId.setBackground(new java.awt.Color(255, 255, 255));
        titleItemId.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        titleItemId.setForeground(new java.awt.Color(255, 255, 255));
        titleItemId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleItemId.setText("Item ID :");
        layerAddItem.add(titleItemId, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 80, 30));
        layerAddItem.add(s6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 150, 10));
        layerAddItem.add(s7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 170, 10));

        titleItemName.setBackground(new java.awt.Color(255, 255, 255));
        titleItemName.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        titleItemName.setForeground(new java.awt.Color(255, 255, 255));
        titleItemName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleItemName.setText("Item Name :");
        layerAddItem.add(titleItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 130, 30));

        itemName.setBackground(new java.awt.Color(13, 24, 35));
        itemName.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        itemName.setForeground(new java.awt.Color(153, 153, 153));
        itemName.setText("Name");
        itemName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                itemNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                itemNameFocusLost(evt);
            }
        });
        layerAddItem.add(itemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 160, 30));

        titleItemCount.setBackground(new java.awt.Color(255, 255, 255));
        titleItemCount.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        titleItemCount.setForeground(new java.awt.Color(255, 255, 255));
        titleItemCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleItemCount.setText("Item Amount :");
        layerAddItem.add(titleItemCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 130, 30));
        layerAddItem.add(s8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 150, 20));

        titleItemCP.setBackground(new java.awt.Color(255, 255, 255));
        titleItemCP.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        titleItemCP.setForeground(new java.awt.Color(255, 255, 255));
        titleItemCP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleItemCP.setText("Item CP :");
        layerAddItem.add(titleItemCP, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 90, 30));

        itemCP.setBackground(new java.awt.Color(13, 24, 35));
        itemCP.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        itemCP.setForeground(new java.awt.Color(153, 153, 153));
        itemCP.setText("Count");
        itemCP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                itemCPFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                itemCPFocusLost(evt);
            }
        });
        layerAddItem.add(itemCP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 140, 30));
        layerAddItem.add(s11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 150, 20));

        itemAmount.setBackground(new java.awt.Color(13, 24, 35));
        itemAmount.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        itemAmount.setForeground(new java.awt.Color(153, 153, 153));
        itemAmount.setText("Count");
        itemAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                itemAmountFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                itemAmountFocusLost(evt);
            }
        });
        layerAddItem.add(itemAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 140, 30));

        submitButSharing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMini.png"))); // NOI18N
        submitButSharing.setContentAreaFilled(false);
        submitButSharing.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        submitButSharing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitButSharingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitButSharingMouseExited(evt);
            }
        });
        submitButSharing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButSharingActionPerformed(evt);
            }
        });
        layerAddItem.add(submitButSharing, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 110, -1));

        cancleButSharing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleMini.png"))); // NOI18N
        cancleButSharing.setContentAreaFilled(false);
        cancleButSharing.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancleButSharing.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancleButSharingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancleButSharingMouseExited(evt);
            }
        });
        cancleButSharing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancleButSharingActionPerformed(evt);
            }
        });
        layerAddItem.add(cancleButSharing, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, 90, 40));

        itemId.setEditable(false);
        itemId.setBackground(new java.awt.Color(13, 24, 35));
        itemId.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        itemId.setForeground(new java.awt.Color(153, 153, 153));
        itemId.setText("ID");
        itemId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                itemIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                itemIdFocusLost(evt);
            }
        });
        layerAddItem.add(itemId, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 130, 30));

        pathImgItem.setBackground(new java.awt.Color(13, 24, 35));
        pathImgItem.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        pathImgItem.setForeground(new java.awt.Color(153, 153, 153));
        pathImgItem.setToolTipText("");
        layerAddItem.add(pathImgItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 150, 30));

        notiSharing.setBackground(new java.awt.Color(13, 24, 35));
        notiSharing.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        notiSharing.setForeground(new java.awt.Color(130, 255, 134));
        notiSharing.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notiSharing.setText("Success!!!");
        notiSharing.setToolTipText("");
        layerAddItem.add(notiSharing, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 200, 20));

        itemList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/itemList.png"))); // NOI18N
        itemList.setToolTipText("Item List");
        itemList.setContentAreaFilled(false);
        itemList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        itemList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                itemListMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                itemListMouseExited(evt);
            }
        });
        itemList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemListActionPerformed(evt);
            }
        });
        layerAddItem.add(itemList, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 50, -1));

        titleItemPic.setBackground(new java.awt.Color(255, 255, 255));
        titleItemPic.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        titleItemPic.setForeground(new java.awt.Color(255, 255, 255));
        titleItemPic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleItemPic.setText("Item Picture :");
        layerAddItem.add(titleItemPic, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 140, 30));

        chooseImgItem.setBackground(new java.awt.Color(126, 192, 237));
        chooseImgItem.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        chooseImgItem.setForeground(new java.awt.Color(255, 255, 255));
        chooseImgItem.setText("Choose");
        chooseImgItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseImgItemActionPerformed(evt);
            }
        });
        layerAddItem.add(chooseImgItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 90, 30));

        sharingPageTab.add(layerAddItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 400, 280));

        itemListScroll.setBackground(new java.awt.Color(13, 24, 35));

        itemListPage.setBackground(new java.awt.Color(13, 24, 35));
        itemListPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleItemList.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        titleItemList.setForeground(new java.awt.Color(255, 255, 255));
        titleItemList.setText("Item List");
        itemListPage.add(titleItemList, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 100, 60));

        closeBut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        closeBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/delete-button.png"))); // NOI18N
        closeBut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeButMouseClicked(evt);
            }
        });
        itemListPage.add(closeBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 50, 40));

        itemListScroll.setViewportView(itemListPage);

        sharingPageTab.add(itemListScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 710, 330));

        titleWhat.setBackground(new java.awt.Color(255, 255, 255));
        titleWhat.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        titleWhat.setForeground(new java.awt.Color(255, 255, 255));
        titleWhat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titleWhat.setText("What item do you want to ___? ");
        sharingPageTab.add(titleWhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 450, 50));

        radioType.setBackground(new java.awt.Color(25, 41, 65));
        radioType.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        radioType.setForeground(new java.awt.Color(19, 175, 248));
        radioType.setText("Type Bicycle");
        radioType.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        radioType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTypeActionPerformed(evt);
            }
        });
        sharingPageTab.add(radioType, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 140, 40));

        radioEquip.setBackground(new java.awt.Color(25, 41, 65));
        radioEquip.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        radioEquip.setForeground(new java.awt.Color(19, 175, 248));
        radioEquip.setText("Equipment");
        radioEquip.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        radioEquip.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        radioEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioEquipActionPerformed(evt);
            }
        });
        sharingPageTab.add(radioEquip, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 140, 40));

        sharingPage.add(sharingPageTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 770, 360));

        menuSharing.setBackground(new java.awt.Color(13, 24, 35));
        menuSharing.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuAdd.setBackground(new java.awt.Color(13, 24, 35));
        menuAdd.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        menuAdd.setForeground(new java.awt.Color(102, 102, 102));
        menuAdd.setText("Add Item");
        menuAdd.setToolTipText("");
        menuAdd.setContentAreaFilled(false);
        menuAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAddActionPerformed(evt);
            }
        });
        menuSharing.add(menuAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, 50));

        menuEdit.setBackground(new java.awt.Color(13, 24, 35));
        menuEdit.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        menuEdit.setForeground(new java.awt.Color(102, 102, 102));
        menuEdit.setText("Edit Item");
        menuEdit.setToolTipText("");
        menuEdit.setContentAreaFilled(false);
        menuEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEditActionPerformed(evt);
            }
        });
        menuSharing.add(menuEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 10, 120, 50));

        menuDelete.setBackground(new java.awt.Color(25, 41, 65));
        menuDelete.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        menuDelete.setForeground(new java.awt.Color(102, 102, 102));
        menuDelete.setText("Delete Item");
        menuDelete.setToolTipText("");
        menuDelete.setContentAreaFilled(false);
        menuDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDeleteActionPerformed(evt);
            }
        });
        menuSharing.add(menuDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, 50));

        backAdd.setBackground(new java.awt.Color(25, 41, 65));

        javax.swing.GroupLayout backAddLayout = new javax.swing.GroupLayout(backAdd);
        backAdd.setLayout(backAddLayout);
        backAddLayout.setHorizontalGroup(
            backAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        backAddLayout.setVerticalGroup(
            backAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        menuSharing.add(backAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 130, -1));

        backEdit.setBackground(new java.awt.Color(25, 41, 65));

        javax.swing.GroupLayout backEditLayout = new javax.swing.GroupLayout(backEdit);
        backEdit.setLayout(backEditLayout);
        backEditLayout.setHorizontalGroup(
            backEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        backEditLayout.setVerticalGroup(
            backEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        menuSharing.add(backEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 130, -1));

        backDelete.setBackground(new java.awt.Color(25, 41, 65));

        javax.swing.GroupLayout backDeleteLayout = new javax.swing.GroupLayout(backDelete);
        backDelete.setLayout(backDeleteLayout);
        backDeleteLayout.setHorizontalGroup(
            backDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        backDeleteLayout.setVerticalGroup(
            backDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        menuSharing.add(backDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 140, -1));

        sharingPage.add(menuSharing, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 60));

        mainAdmin.add(sharingPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 421));

        newsPage.setBackground(new java.awt.Color(25, 41, 65));
        newsPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        showlistNews.setBackground(new java.awt.Color(11, 24, 43));
        showlistNews.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        newsList.setViewportView(showlistNews);

        newsPage.add(newsList, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 600, 330));

        showNews.setBackground(new java.awt.Color(9, 20, 36));
        showNews.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titletopicNews.setBackground(new java.awt.Color(255, 255, 255));
        titletopicNews.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        titletopicNews.setForeground(new java.awt.Color(255, 255, 255));
        titletopicNews.setText(" Topic :");
        showNews.add(titletopicNews, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 70, 30));

        topicNews.setEditable(false);
        topicNews.setBackground(new java.awt.Color(9, 20, 36));
        topicNews.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        topicNews.setForeground(new java.awt.Color(255, 255, 255));
        topicNews.setText("Galaxy S8");
        showNews.add(topicNews, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 570, 30));

        describeNewsText.setEditable(false);
        describeNewsText.setBackground(new java.awt.Color(9, 20, 36));
        describeNewsText.setColumns(20);
        describeNewsText.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        describeNewsText.setForeground(new java.awt.Color(255, 255, 255));
        describeNewsText.setLineWrap(true);
        describeNewsText.setRows(5);
        describeNews.setViewportView(describeNewsText);

        showNews.add(describeNews, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 600, 260));

        binding.setBackground(new java.awt.Color(9, 20, 36));
        binding.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cancleUpdateNews.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleMini.png"))); // NOI18N
        cancleUpdateNews.setContentAreaFilled(false);
        cancleUpdateNews.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancleUpdateNews.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancleUpdateNewsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancleUpdateNewsMouseExited(evt);
            }
        });
        binding.add(cancleUpdateNews, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 120, 50));

        submitUpdateNews.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMini.png"))); // NOI18N
        submitUpdateNews.setContentAreaFilled(false);
        submitUpdateNews.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        submitUpdateNews.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitUpdateNewsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitUpdateNewsMouseExited(evt);
            }
        });
        binding.add(submitUpdateNews, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 120, 50));

        showNews.add(binding, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 340, 290, 50));

        closeNews.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/delete-button.png"))); // NOI18N
        closeNews.setContentAreaFilled(false);
        closeNews.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeNews.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeNewsActionPerformed(evt);
            }
        });
        showNews.add(closeNews, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 0, 40, 50));

        newsPage.add(showNews, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 710, 400));

        jPanelInsertNews.setBackground(new java.awt.Color(25, 41, 65));
        jPanelInsertNews.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtAreaNewsDetail.setBackground(new java.awt.Color(13, 24, 35));
        txtAreaNewsDetail.setColumns(20);
        txtAreaNewsDetail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAreaNewsDetail.setForeground(new java.awt.Color(19, 175, 248));
        txtAreaNewsDetail.setRows(5);
        insertDetailScroll.setViewportView(txtAreaNewsDetail);

        jPanelInsertNews.add(insertDetailScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 630, 170));

        txtDescription1.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        txtDescription1.setForeground(new java.awt.Color(255, 255, 255));
        txtDescription1.setText("DESCRIPTION");
        jPanelInsertNews.add(txtDescription1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        txtFieldNewsDescription.setBackground(new java.awt.Color(13, 24, 35));
        txtFieldNewsDescription.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        txtFieldNewsDescription.setForeground(new java.awt.Color(19, 175, 248));
        jPanelInsertNews.add(txtFieldNewsDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 630, 40));

        txtDescription.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        txtDescription.setForeground(new java.awt.Color(255, 255, 255));
        txtDescription.setText("DETAILS");
        jPanelInsertNews.add(txtDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 280, -1));

        cancleButtonNews.setBackground(new java.awt.Color(255, 255, 255));
        cancleButtonNews.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        cancleButtonNews.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleMini.png"))); // NOI18N
        cancleButtonNews.setContentAreaFilled(false);
        cancleButtonNews.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancleButtonNews.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancleButtonNewsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancleButtonNewsMouseExited(evt);
            }
        });
        cancleButtonNews.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancleButtonNewsActionPerformed(evt);
            }
        });
        jPanelInsertNews.add(cancleButtonNews, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, 90, 40));

        submitButtonNews.setBackground(new java.awt.Color(255, 255, 255));
        submitButtonNews.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        submitButtonNews.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMini.png"))); // NOI18N
        submitButtonNews.setContentAreaFilled(false);
        submitButtonNews.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitButtonNewsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitButtonNewsMouseExited(evt);
            }
        });
        submitButtonNews.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonNewsActionPerformed(evt);
            }
        });
        jPanelInsertNews.add(submitButtonNews, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 370, 90, 40));

        jPanelBackgroundInsertLabel.setBackground(new java.awt.Color(102, 102, 102));
        jPanelBackgroundInsertLabel.setForeground(new java.awt.Color(255, 255, 255));
        jPanelBackgroundInsertLabel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelInsert.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabelInsert.setForeground(new java.awt.Color(255, 255, 255));
        jLabelInsert.setText("INSERT");
        jPanelBackgroundInsertLabel.add(jLabelInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 130, 40));

        jPanelInsertNews.add(jPanelBackgroundInsertLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 60));

        chooseImgNews.setBackground(new java.awt.Color(19, 175, 248));
        chooseImgNews.setForeground(new java.awt.Color(255, 255, 255));
        chooseImgNews.setText("Select Image");
        chooseImgNews.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseImgNewsActionPerformed(evt);
            }
        });
        jPanelInsertNews.add(chooseImgNews, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        pathImgNews.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        pathImgNews.setForeground(new java.awt.Color(153, 153, 153));
        pathImgNews.setText("No Select");
        jPanelInsertNews.add(pathImgNews, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 384, 170, 20));

        newsPage.add(jPanelInsertNews, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 420));

        titleNews.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        titleNews.setForeground(new java.awt.Color(255, 255, 255));
        titleNews.setText("News");
        newsPage.add(titleNews, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 60, 30));

        jButtonInsert.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jButtonInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/addNews.png"))); // NOI18N
        jButtonInsert.setContentAreaFilled(false);
        jButtonInsert.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonInsert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonInsertMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonInsertMouseExited(evt);
            }
        });
        jButtonInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertActionPerformed(evt);
            }
        });
        newsPage.add(jButtonInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 60, 50));

        showtitleInsertNews.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        showtitleInsertNews.setForeground(new java.awt.Color(255, 255, 255));
        showtitleInsertNews.setText("Insert News");
        newsPage.add(showtitleInsertNews, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 40, -1, 30));

        mainAdmin.add(newsPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 420));

        userProfilePage.setBackground(new java.awt.Color(25, 41, 65));
        userProfilePage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editProfileBut.setBackground(new java.awt.Color(126, 192, 237));
        editProfileBut.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        editProfileBut.setForeground(new java.awt.Color(255, 255, 255));
        editProfileBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/editProfile.png"))); // NOI18N
        editProfileBut.setContentAreaFilled(false);
        editProfileBut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editProfileBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editProfileButMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editProfileButMouseExited(evt);
            }
        });
        editProfileBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editProfileButActionPerformed(evt);
            }
        });
        userProfilePage.add(editProfileBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 3, 110, 40));

        titleUserId.setBackground(new java.awt.Color(55, 200, 255));
        titleUserId.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        titleUserId.setForeground(new java.awt.Color(255, 255, 255));
        titleUserId.setText("User ID :");
        userProfilePage.add(titleUserId, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 60, -1));

        userID.setBackground(new java.awt.Color(55, 200, 255));
        userID.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        userID.setForeground(new java.awt.Color(19, 175, 248));
        userID.setText("1");
        userID.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        userProfilePage.add(userID, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 60, 20));
        userProfilePage.add(imageProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 200, 170));

        titleAbout.setBackground(new java.awt.Color(13, 24, 35));
        titleAbout.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        titleAbout.setForeground(new java.awt.Color(255, 255, 255));
        titleAbout.setText("ABOUT");
        userProfilePage.add(titleAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, -1, -1));

        titleNameProfile.setBackground(new java.awt.Color(55, 200, 255));
        titleNameProfile.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        titleNameProfile.setForeground(new java.awt.Color(236, 233, 233));
        titleNameProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleNameProfile.setText("Name :");
        userProfilePage.add(titleNameProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 80, 20));

        nameProfile.setEditable(false);
        nameProfile.setBackground(new java.awt.Color(25, 41, 65));
        nameProfile.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        nameProfile.setForeground(new java.awt.Color(19, 175, 248));
        nameProfile.setText("Thanatta  Opatkajonyos");
        userProfilePage.add(nameProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 380, 40));

        titleGenderProfile.setBackground(new java.awt.Color(55, 200, 255));
        titleGenderProfile.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        titleGenderProfile.setForeground(new java.awt.Color(236, 233, 233));
        titleGenderProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleGenderProfile.setText("Gender :");
        userProfilePage.add(titleGenderProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 90, 20));

        genderProfile.setEditable(false);
        genderProfile.setBackground(new java.awt.Color(25, 41, 65));
        genderProfile.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        genderProfile.setForeground(new java.awt.Color(19, 175, 248));
        genderProfile.setText("F");
        userProfilePage.add(genderProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 70, 20));

        titlebBirthProfile.setBackground(new java.awt.Color(55, 200, 255));
        titlebBirthProfile.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        titlebBirthProfile.setForeground(new java.awt.Color(236, 233, 233));
        titlebBirthProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titlebBirthProfile.setText("Birthday :");
        userProfilePage.add(titlebBirthProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 100, 40));

        birthProfile.setEditable(false);
        birthProfile.setBackground(new java.awt.Color(25, 41, 65));
        birthProfile.setForeground(new java.awt.Color(19, 175, 248));
        birthProfile.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-M-d"))));
        birthProfile.setToolTipText("yyy-mm-dd");
        birthProfile.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        birthProfile.setName(""); // NOI18N
        userProfilePage.add(birthProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, 220, 40));

        titleDepartProfile.setBackground(new java.awt.Color(55, 200, 255));
        titleDepartProfile.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        titleDepartProfile.setForeground(new java.awt.Color(236, 233, 233));
        titleDepartProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleDepartProfile.setText("Department :");
        userProfilePage.add(titleDepartProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 130, 40));

        departProfile.setBackground(new java.awt.Color(55, 200, 255));
        departProfile.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        departProfile.setForeground(new java.awt.Color(19, 175, 248));
        departProfile.setText("School of Information Technology");
        userProfilePage.add(departProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 330, 40));

        titleCongenProfile.setBackground(new java.awt.Color(55, 200, 255));
        titleCongenProfile.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        titleCongenProfile.setForeground(new java.awt.Color(236, 233, 233));
        titleCongenProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleCongenProfile.setText("Congenitial Disease :");
        userProfilePage.add(titleCongenProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 210, 40));

        congenProfile.setEditable(false);
        congenProfile.setBackground(new java.awt.Color(25, 41, 65));
        congenProfile.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        congenProfile.setForeground(new java.awt.Color(19, 175, 248));
        congenProfile.setText("ภูมิแพ้");
        congenProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        userProfilePage.add(congenProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 230, 280, 40));

        titleContact.setBackground(new java.awt.Color(13, 24, 35));
        titleContact.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        titleContact.setForeground(new java.awt.Color(255, 255, 255));
        titleContact.setText("Contact");
        userProfilePage.add(titleContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, 30));

        titleEmailProfile.setBackground(new java.awt.Color(55, 200, 255));
        titleEmailProfile.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        titleEmailProfile.setForeground(new java.awt.Color(236, 233, 233));
        titleEmailProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleEmailProfile.setText("Email :");
        userProfilePage.add(titleEmailProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 70, 20));

        emailProfile.setEditable(false);
        emailProfile.setBackground(new java.awt.Color(25, 41, 65));
        emailProfile.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        emailProfile.setForeground(new java.awt.Color(19, 175, 248));
        emailProfile.setText("thanatta.o@mail.kmutt.ac.th");
        userProfilePage.add(emailProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 380, 40));

        titleTelProfile.setBackground(new java.awt.Color(55, 200, 255));
        titleTelProfile.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        titleTelProfile.setForeground(new java.awt.Color(236, 233, 233));
        titleTelProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleTelProfile.setText("Telephone :");
        userProfilePage.add(titleTelProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 130, 40));

        telProfile.setEditable(false);
        telProfile.setBackground(new java.awt.Color(25, 41, 65));
        telProfile.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        telProfile.setForeground(new java.awt.Color(19, 175, 248));
        telProfile.setText("0900000000");
        userProfilePage.add(telProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 270, -1));

        chooseImgProfileBut.setBackground(new java.awt.Color(126, 192, 237));
        chooseImgProfileBut.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        chooseImgProfileBut.setForeground(new java.awt.Color(255, 255, 255));
        chooseImgProfileBut.setText("Choose Profile");
        chooseImgProfileBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseImgProfileButActionPerformed(evt);
            }
        });
        userProfilePage.add(chooseImgProfileBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        pathImgProfile.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        pathImgProfile.setForeground(new java.awt.Color(255, 255, 255));
        pathImgProfile.setText("No Select File.");
        userProfilePage.add(pathImgProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 245, 80, -1));

        submitProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMini.png"))); // NOI18N
        submitProfile.setContentAreaFilled(false);
        submitProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        submitProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitProfileMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitProfileMouseExited(evt);
            }
        });
        submitProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitProfileActionPerformed(evt);
            }
        });
        userProfilePage.add(submitProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, 110, 50));

        warningProfile.setBackground(new java.awt.Color(55, 200, 255));
        warningProfile.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        warningProfile.setForeground(new java.awt.Color(255, 51, 51));
        warningProfile.setText("Your name is incorrect");
        userProfilePage.add(warningProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 150, 30));

        mainAdmin.add(userProfilePage, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 420));

        getContentPane().add(mainAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        mainUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        barSearch1.setBackground(new java.awt.Color(13, 24, 35));
        barSearch1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        barSearch1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 120, 40));

        iconSearch1.setFont(new java.awt.Font("Leelawadee UI", 0, 20)); // NOI18N
        iconSearch1.setForeground(new java.awt.Color(255, 255, 255));
        iconSearch1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconSearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/search.png"))); // NOI18N
        iconSearch1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconSearch1MouseClicked(evt);
            }
        });
        barSearch1.add(iconSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 60, 50));

        title1.setBackground(new java.awt.Color(0, 0, 0));
        title1.setFont(new java.awt.Font("Leelawadee", 0, 30)); // NOI18N
        title1.setForeground(new java.awt.Color(255, 255, 255));
        title1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title1.setText("GREEN  SOCIETY");
        title1.setToolTipText("");
        barSearch1.add(title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 60));

        search1.setBackground(new java.awt.Color(13, 24, 35));
        search1.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        search1.setForeground(new java.awt.Color(255, 255, 255));
        search1.setText("Search");
        search1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                search1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                search1FocusLost(evt);
            }
        });
        search1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                search1KeyPressed(evt);
            }
        });
        barSearch1.add(search1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 120, 30));

        mainUser.add(barSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 900, 60));

        backMenuBar1.setBackground(new java.awt.Color(22, 31, 39));
        backMenuBar1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuBar1.setBackground(new java.awt.Color(13, 24, 35));
        menuBar1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        news1.setBackground(new java.awt.Color(0, 0, 0));
        news1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        news1.setForeground(new java.awt.Color(255, 255, 255));
        news1.setText("     News");
        news1.setToolTipText("");
        news1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        news1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        news1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                news1MouseClicked(evt);
            }
        });
        menuBar1.add(news1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 140, 20));

        bikeRepair1.setBackground(new java.awt.Color(0, 0, 0));
        bikeRepair1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        bikeRepair1.setForeground(new java.awt.Color(255, 255, 255));
        bikeRepair1.setText("     Bike Repairing");
        bikeRepair1.setToolTipText("");
        bikeRepair1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        bikeRepair1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bikeRepair1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bikeRepair1MouseClicked(evt);
            }
        });
        menuBar1.add(bikeRepair1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 140, 20));

        canCounter1.setBackground(new java.awt.Color(0, 0, 0));
        canCounter1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        canCounter1.setForeground(new java.awt.Color(255, 255, 255));
        canCounter1.setText("     Can Counter");
        canCounter1.setToolTipText("");
        canCounter1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        canCounter1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        canCounter1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                canCounter1MouseClicked(evt);
            }
        });
        menuBar1.add(canCounter1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 140, 20));

        bikeSharing1.setBackground(new java.awt.Color(255, 255, 255));
        bikeSharing1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        bikeSharing1.setForeground(new java.awt.Color(255, 255, 255));
        bikeSharing1.setText("     Bike Sharing");
        bikeSharing1.setToolTipText("");
        bikeSharing1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        bikeSharing1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bikeSharing1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bikeSharing1MouseClicked(evt);
            }
        });
        menuBar1.add(bikeSharing1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 140, 20));

        history1.setBackground(new java.awt.Color(0, 0, 0));
        history1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        history1.setForeground(new java.awt.Color(255, 255, 255));
        history1.setText("     History");
        history1.setToolTipText("");
        history1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        history1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        history1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                history1MouseClicked(evt);
            }
        });
        menuBar1.add(history1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 140, 20));

        profile1.setBackground(new java.awt.Color(0, 0, 0));
        profile1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        profile1.setForeground(new java.awt.Color(255, 255, 255));
        profile1.setText("     PROFILE");
        profile1.setToolTipText("");
        profile1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profile1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profile1MouseClicked(evt);
            }
        });
        menuBar1.add(profile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 140, 30));

        timer1.setBackground(new java.awt.Color(0, 0, 0));
        timer1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        timer1.setForeground(new java.awt.Color(255, 255, 255));
        timer1.setText("     Timer");
        timer1.setToolTipText("");
        timer1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        timer1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        timer1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timer1MouseClicked(evt);
            }
        });
        menuBar1.add(timer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 140, 20));

        support1.setBackground(new java.awt.Color(0, 0, 0));
        support1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        support1.setForeground(new java.awt.Color(255, 255, 255));
        support1.setText("     Support");
        support1.setToolTipText("");
        support1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        support1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        support1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                support1MouseClicked(evt);
            }
        });
        menuBar1.add(support1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 140, 20));

        menu1.setBackground(new java.awt.Color(0, 0, 0));
        menu1.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        menu1.setForeground(new java.awt.Color(255, 255, 255));
        menu1.setText("MENU");
        menu1.setToolTipText("");
        menuBar1.add(menu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 50, 30));

        arrowDownIcon1.setBackground(new java.awt.Color(0, 0, 0));
        arrowDownIcon1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        arrowDownIcon1.setForeground(new java.awt.Color(255, 255, 255));
        arrowDownIcon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        arrowDownIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/chevron-arrow-down.png"))); // NOI18N
        arrowDownIcon1.setToolTipText("");
        menuBar1.add(arrowDownIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 40, 30));

        circleMini.setBackground(new java.awt.Color(0, 0, 0));
        circleMini.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        circleMini.setForeground(new java.awt.Color(255, 255, 255));
        circleMini.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/circle_mini.png"))); // NOI18N
        circleMini.setToolTipText("");
        menuBar1.add(circleMini, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 20, 20));

        titleSignout1.setBackground(new java.awt.Color(0, 0, 0));
        titleSignout1.setFont(new java.awt.Font("Leelawadee", 0, 13)); // NOI18N
        titleSignout1.setForeground(new java.awt.Color(255, 255, 255));
        titleSignout1.setText("     SIGN OUT");
        titleSignout1.setToolTipText("");
        menuBar1.add(titleSignout1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 140, 30));

        circleMini1.setBackground(new java.awt.Color(0, 0, 0));
        circleMini1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        circleMini1.setForeground(new java.awt.Color(255, 255, 255));
        circleMini1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/circle_mini.png"))); // NOI18N
        circleMini1.setToolTipText("");
        menuBar1.add(circleMini1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 20, 20));

        backMenuBar1.add(menuBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 480));

        mainUser.add(backMenuBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 150, 480));

        barUser1.setBackground(new java.awt.Color(19, 175, 248));
        barUser1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pic1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barUser1.add(pic1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        name1.setBackground(new java.awt.Color(0, 0, 0));
        name1.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        name1.setForeground(new java.awt.Color(13, 24, 35));
        name1.setText("THANATTA");
        name1.setToolTipText("");
        name1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        barUser1.add(name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 130, 20));

        mainUser.add(barUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 200, 60));

        backBarUser1.setBackground(new java.awt.Color(55, 200, 255));
        backBarUser1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        mainUser.add(backBarUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 210, 60));

        backBarIcon1.setBackground(new java.awt.Color(55, 200, 255));
        backBarIcon1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        barIcon1.setBackground(new java.awt.Color(19, 175, 248));
        barIcon1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconMenu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/menu.png"))); // NOI18N
        barIcon1.add(iconMenu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 50, 30));

        iconBike1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconBike1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/bike.png"))); // NOI18N
        barIcon1.add(iconBike1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 50, 40));

        iconProfile1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconProfile1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/profile-user.png"))); // NOI18N
        barIcon1.add(iconProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 50, 50));

        signout1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/signout.png"))); // NOI18N
        signout1.setContentAreaFilled(false);
        signout1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signout1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signout1MouseExited(evt);
            }
        });
        signout1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signout1ActionPerformed(evt);
            }
        });
        barIcon1.add(signout1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 50, 50));

        backBarIcon1.add(barIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 540));

        mainUser.add(backBarIcon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 540));

        barNoti1.setBackground(new java.awt.Color(13, 24, 35));
        barNoti1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator4.setBackground(new java.awt.Color(55, 200, 255));
        jSeparator4.setForeground(new java.awt.Color(55, 200, 255));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        barNoti1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 10, 60));

        circleNoti.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        circleNoti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/circle.png"))); // NOI18N
        barNoti1.add(circleNoti, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 40, 30));

        iconNoti1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconNoti1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/notifications-button.png"))); // NOI18N
        barNoti1.add(iconNoti1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        mainUser.add(barNoti1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 770, 60));

        userProfilePage1.setBackground(new java.awt.Color(25, 41, 65));
        userProfilePage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editProfileBut1.setBackground(new java.awt.Color(126, 192, 237));
        editProfileBut1.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        editProfileBut1.setForeground(new java.awt.Color(255, 255, 255));
        editProfileBut1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/editProfile.png"))); // NOI18N
        editProfileBut1.setContentAreaFilled(false);
        editProfileBut1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editProfileBut1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editProfileBut1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editProfileBut1MouseExited(evt);
            }
        });
        editProfileBut1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editProfileBut1ActionPerformed(evt);
            }
        });
        userProfilePage1.add(editProfileBut1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 3, 110, 40));

        titleUserId1.setBackground(new java.awt.Color(55, 200, 255));
        titleUserId1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        titleUserId1.setForeground(new java.awt.Color(255, 255, 255));
        titleUserId1.setText("User ID :");
        userProfilePage1.add(titleUserId1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 60, -1));

        userID1.setBackground(new java.awt.Color(55, 200, 255));
        userID1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        userID1.setForeground(new java.awt.Color(19, 175, 248));
        userID1.setText("1");
        userID1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        userProfilePage1.add(userID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 60, 20));
        userProfilePage1.add(imageProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 200, 170));

        titleAbout1.setBackground(new java.awt.Color(13, 24, 35));
        titleAbout1.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        titleAbout1.setForeground(new java.awt.Color(255, 255, 255));
        titleAbout1.setText("ABOUT");
        userProfilePage1.add(titleAbout1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, -1, -1));

        titleNameProfile1.setBackground(new java.awt.Color(55, 200, 255));
        titleNameProfile1.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        titleNameProfile1.setForeground(new java.awt.Color(236, 233, 233));
        titleNameProfile1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleNameProfile1.setText("Name :");
        userProfilePage1.add(titleNameProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 80, 20));

        nameProfile1.setEditable(false);
        nameProfile1.setBackground(new java.awt.Color(25, 41, 65));
        nameProfile1.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        nameProfile1.setForeground(new java.awt.Color(19, 175, 248));
        nameProfile1.setText("Thanatta  Opatkajonyos");
        userProfilePage1.add(nameProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 380, 40));

        titleGenderProfile1.setBackground(new java.awt.Color(55, 200, 255));
        titleGenderProfile1.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        titleGenderProfile1.setForeground(new java.awt.Color(236, 233, 233));
        titleGenderProfile1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleGenderProfile1.setText("Gender :");
        userProfilePage1.add(titleGenderProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 90, 40));

        genderProfile1.setEditable(false);
        genderProfile1.setBackground(new java.awt.Color(25, 41, 65));
        genderProfile1.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        genderProfile1.setForeground(new java.awt.Color(19, 175, 248));
        genderProfile1.setText("Female");
        userProfilePage1.add(genderProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, 70, 40));

        titlebBirthProfile1.setBackground(new java.awt.Color(55, 200, 255));
        titlebBirthProfile1.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        titlebBirthProfile1.setForeground(new java.awt.Color(236, 233, 233));
        titlebBirthProfile1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titlebBirthProfile1.setText("Birthday :");
        userProfilePage1.add(titlebBirthProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 90, 40));

        birthProfile1.setEditable(false);
        birthProfile1.setBackground(new java.awt.Color(25, 41, 65));
        birthProfile1.setForeground(new java.awt.Color(19, 175, 248));
        birthProfile1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-M-d"))));
        birthProfile1.setToolTipText("yyy-mm-dd");
        birthProfile1.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        birthProfile1.setName(""); // NOI18N
        userProfilePage1.add(birthProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 131, 230, 40));

        titleDepartProfile1.setBackground(new java.awt.Color(55, 200, 255));
        titleDepartProfile1.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        titleDepartProfile1.setForeground(new java.awt.Color(236, 233, 233));
        titleDepartProfile1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleDepartProfile1.setText("Department :");
        userProfilePage1.add(titleDepartProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 130, 40));

        departProfile1.setBackground(new java.awt.Color(55, 200, 255));
        departProfile1.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        departProfile1.setForeground(new java.awt.Color(19, 175, 248));
        departProfile1.setText("School of Information Technology");
        userProfilePage1.add(departProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 330, 40));

        titleCongenProfile1.setBackground(new java.awt.Color(55, 200, 255));
        titleCongenProfile1.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        titleCongenProfile1.setForeground(new java.awt.Color(236, 233, 233));
        titleCongenProfile1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleCongenProfile1.setText("Congenitial Disease :");
        userProfilePage1.add(titleCongenProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 210, 40));

        congenProfile1.setEditable(false);
        congenProfile1.setBackground(new java.awt.Color(25, 41, 65));
        congenProfile1.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        congenProfile1.setForeground(new java.awt.Color(19, 175, 248));
        congenProfile1.setText("ภูมิแพ้");
        congenProfile1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        userProfilePage1.add(congenProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 230, 280, 40));

        titleContact1.setBackground(new java.awt.Color(13, 24, 35));
        titleContact1.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        titleContact1.setForeground(new java.awt.Color(255, 255, 255));
        titleContact1.setText("Contact");
        userProfilePage1.add(titleContact1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, 30));

        titleEmailProfile1.setBackground(new java.awt.Color(55, 200, 255));
        titleEmailProfile1.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        titleEmailProfile1.setForeground(new java.awt.Color(236, 233, 233));
        titleEmailProfile1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleEmailProfile1.setText("Email :");
        userProfilePage1.add(titleEmailProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 70, 20));

        emailProfile1.setEditable(false);
        emailProfile1.setBackground(new java.awt.Color(25, 41, 65));
        emailProfile1.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        emailProfile1.setForeground(new java.awt.Color(19, 175, 248));
        emailProfile1.setText("thanatta.o@mail.kmutt.ac.th");
        userProfilePage1.add(emailProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 380, 40));

        titleTelProfile1.setBackground(new java.awt.Color(55, 200, 255));
        titleTelProfile1.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        titleTelProfile1.setForeground(new java.awt.Color(236, 233, 233));
        titleTelProfile1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleTelProfile1.setText("Telephone :");
        userProfilePage1.add(titleTelProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 130, 40));

        telProfile1.setEditable(false);
        telProfile1.setBackground(new java.awt.Color(25, 41, 65));
        telProfile1.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        telProfile1.setForeground(new java.awt.Color(19, 175, 248));
        telProfile1.setText("0900000000");
        userProfilePage1.add(telProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 270, -1));

        chooseImgProfileBut1.setBackground(new java.awt.Color(126, 192, 237));
        chooseImgProfileBut1.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        chooseImgProfileBut1.setForeground(new java.awt.Color(255, 255, 255));
        chooseImgProfileBut1.setText("Choose Profile");
        chooseImgProfileBut1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseImgProfileBut1ActionPerformed(evt);
            }
        });
        userProfilePage1.add(chooseImgProfileBut1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        pathImgProfile1.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        pathImgProfile1.setForeground(new java.awt.Color(255, 255, 255));
        pathImgProfile1.setText("No Select File.");
        userProfilePage1.add(pathImgProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 245, 80, -1));

        submitProfile1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMini.png"))); // NOI18N
        submitProfile1.setContentAreaFilled(false);
        submitProfile1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        submitProfile1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitProfile1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitProfile1MouseExited(evt);
            }
        });
        submitProfile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitProfile1ActionPerformed(evt);
            }
        });
        userProfilePage1.add(submitProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, 110, 50));

        warningProfile1.setBackground(new java.awt.Color(55, 200, 255));
        warningProfile1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        warningProfile1.setForeground(new java.awt.Color(255, 51, 51));
        warningProfile1.setText("Your name is incorrect");
        userProfilePage1.add(warningProfile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 150, 30));

        mainUser.add(userProfilePage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 420));

        sharingStep2.setBackground(new java.awt.Color(25, 41, 65));
        sharingStep2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textYourCP.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        textYourCP.setForeground(new java.awt.Color(255, 255, 255));
        textYourCP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textYourCP.setText("Your CP :");
        sharingStep2.add(textYourCP, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 80, 20));

        cpUser.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        cpUser.setForeground(new java.awt.Color(255, 255, 255));
        cpUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cpUser.setText("1000");
        sharingStep2.add(cpUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 110, 20));
        sharingStep2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 110, 10));

        textRemain.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        textRemain.setForeground(new java.awt.Color(255, 255, 255));
        textRemain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textRemain.setText("Remaining Points : ");
        sharingStep2.add(textRemain, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 170, 40));

        point1.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        point1.setForeground(new java.awt.Color(255, 255, 255));
        point1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        point1.setText("Points");
        sharingStep2.add(point1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 80, 20));

        textCpUse.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        textCpUse.setForeground(new java.awt.Color(255, 255, 255));
        textCpUse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textCpUse.setText("CP Use :");
        sharingStep2.add(textCpUse, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 80, 20));
        sharingStep2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 110, 10));

        cpUse.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        cpUse.setForeground(new java.awt.Color(255, 255, 255));
        cpUse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cpUse.setText("160");
        sharingStep2.add(cpUse, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 110, 20));

        point2.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        point2.setForeground(new java.awt.Color(255, 255, 255));
        point2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        point2.setText("Points");
        sharingStep2.add(point2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, 80, 20));

        pointRemain.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        pointRemain.setForeground(new java.awt.Color(255, 255, 255));
        pointRemain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pointRemain.setText("840");
        pointRemain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        sharingStep2.add(pointRemain, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 60, 40));

        point3.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        point3.setForeground(new java.awt.Color(255, 255, 255));
        point3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        point3.setText("Points");
        sharingStep2.add(point3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 100, 40));

        detailBox.setBackground(new java.awt.Color(13, 24, 35));
        detailBox.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Borrow Detail", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Leelawadee", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        detailBox.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        detailData.setBackground(new java.awt.Color(13, 24, 35));
        detailData.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        detailData.setForeground(new java.awt.Color(55, 200, 255));
        detailData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        detailData.setToolTipText("detail");
        detailData.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        detailBox.add(detailData, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 380, 180));

        sharingStep2.add(detailBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 460, 240));

        backStep1.setBackground(new java.awt.Color(25, 41, 65));
        backStep1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/left arrow.png"))); // NOI18N
        backStep1.setContentAreaFilled(false);
        backStep1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backStep1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backStep1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backStep1MouseExited(evt);
            }
        });
        backStep1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backStep1ActionPerformed(evt);
            }
        });
        sharingStep2.add(backStep1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 60, 60));

        cancleBut.setBackground(new java.awt.Color(25, 41, 65));
        cancleBut.setForeground(new java.awt.Color(255, 255, 255));
        cancleBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancle.png"))); // NOI18N
        cancleBut.setToolTipText("cancle");
        cancleBut.setContentAreaFilled(false);
        cancleBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancleButMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancleButMouseExited(evt);
            }
        });
        cancleBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancleButActionPerformed(evt);
            }
        });
        sharingStep2.add(cancleBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, 60, 50));

        borrowBut.setBackground(new java.awt.Color(25, 41, 65));
        borrowBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/BorrowBut.png"))); // NOI18N
        borrowBut.setContentAreaFilled(false);
        borrowBut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        borrowBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                borrowButMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                borrowButMouseExited(evt);
            }
        });
        borrowBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowButActionPerformed(evt);
            }
        });
        sharingStep2.add(borrowBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 340, 140, 60));

        s9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        s9.setForeground(new java.awt.Color(255, 255, 255));
        s9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        s9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/Step3.png"))); // NOI18N
        sharingStep2.add(s9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 40));

        mainUser.add(sharingStep2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 421));

        supportPage1.setBackground(new java.awt.Color(25, 41, 65));
        supportPage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        barTitle1.setBackground(new java.awt.Color(15, 30, 52));
        barTitle1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleSupport1.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        titleSupport1.setForeground(new java.awt.Color(19, 175, 248));
        titleSupport1.setText("Support");
        barTitle1.add(titleSupport1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 50));

        supportPage1.add(barTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 50));

        bodySupport1.setBackground(new java.awt.Color(25, 41, 65));
        bodySupport1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLBShowingResult1.setBackground(new java.awt.Color(255, 255, 255));
        jLBShowingResult1.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLBShowingResult1.setForeground(new java.awt.Color(255, 255, 255));
        jLBShowingResult1.setText("Showing results for:");
        bodySupport1.add(jLBShowingResult1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 180, 30));
        bodySupport1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 220, 10));

        jLBWhatsearch1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLBWhatsearch1.setForeground(new java.awt.Color(255, 255, 255));
        bodySupport1.add(jLBWhatsearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 220, 30));

        jTAShowyouSearch1.setEditable(false);
        jTAShowyouSearch1.setBackground(new java.awt.Color(25, 41, 65));
        jTAShowyouSearch1.setColumns(20);
        jTAShowyouSearch1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jTAShowyouSearch1.setForeground(new java.awt.Color(255, 255, 255));
        jTAShowyouSearch1.setRows(5);
        jTAShowyouSearch1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(19, 175, 248), 1, true));
        resultScroll1.setViewportView(jTAShowyouSearch1);

        bodySupport1.add(resultScroll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 420, 300));

        jTAContact1.setEditable(false);
        jTAContact1.setBackground(new java.awt.Color(25, 41, 65));
        jTAContact1.setColumns(20);
        jTAContact1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jTAContact1.setForeground(new java.awt.Color(255, 255, 255));
        jTAContact1.setRows(5);
        jTAContact1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(19, 175, 248), 1, true));
        contactScroll1.setViewportView(jTAContact1);

        bodySupport1.add(contactScroll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 270, 300));

        titleContactSupport1.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        titleContactSupport1.setForeground(new java.awt.Color(255, 255, 255));
        titleContactSupport1.setText("Contact Info :");
        bodySupport1.add(titleContactSupport1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, -1));

        supportPage1.add(bodySupport1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 770, 370));

        mainUser.add(supportPage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 420));

        historyPage1.setBackground(new java.awt.Color(25, 41, 65));
        historyPage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelHeadHistory.setBackground(new java.awt.Color(76, 81, 86));
        jPanelHeadHistory.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHistoryText.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        jLabelHistoryText.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHistoryText.setText("History");
        jPanelHeadHistory.add(jLabelHistoryText, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 50));

        historyPage1.add(jPanelHeadHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, -1));

        jPanelHistory.setBackground(new java.awt.Color(25, 41, 65));
        jPanelHistory.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconStart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/startHistory.png"))); // NOI18N
        jPanelHistory.add(iconStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 80, 70));

        iconAction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/actionHistory.png"))); // NOI18N
        jPanelHistory.add(iconAction, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 80, 70));

        iconItem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/itemHistory.png"))); // NOI18N
        jPanelHistory.add(iconItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 80, 70));

        iconend.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/endHistory.png"))); // NOI18N
        jPanelHistory.add(iconend, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 80, 70));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/startHis.png"))); // NOI18N
        jPanelHistory.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 120, 35));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/actionHis.png"))); // NOI18N
        jPanelHistory.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 120, 35));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/itemHis.png"))); // NOI18N
        jPanelHistory.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 120, 35));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/endHis.png"))); // NOI18N
        jPanelHistory.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 120, 35));

        jPanelTableHistory.setBackground(new java.awt.Color(36, 45, 61));
        jPanelTableHistory.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jScrollPaneHistory.setViewportView(jPanelTableHistory);

        jPanelHistory.add(jScrollPaneHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 690, 220));

        historyPage1.add(jPanelHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 770, 370));

        mainUser.add(historyPage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 420));

        timePageF.setBackground(new java.awt.Color(25, 41, 65));
        timePageF.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textNotHis.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        textNotHis.setForeground(new java.awt.Color(255, 255, 255));
        textNotHis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textNotHis.setText("ยังไม่มีประวัติการยืม");
        timePageF.add(textNotHis, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 180, 40));

        iconNotHis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/notHis.png"))); // NOI18N
        timePageF.add(iconNotHis, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        mainUser.add(timePageF, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 420));

        timePageT.setBackground(new java.awt.Color(25, 41, 65));
        timePageT.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        timewatch.setBackground(new java.awt.Color(255, 255, 255));
        timewatch.setEnabled(false);
        timewatch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleNow.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        titleNow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleNow.setText("Now:");
        timewatch.add(titleNow, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 70, 40));

        timeLeftShow.setFont(new java.awt.Font("Leelawadee", 0, 30)); // NOI18N
        timeLeftShow.setForeground(new java.awt.Color(255, 51, 51));
        timewatch.add(timeLeftShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 450, 40));

        endTime.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        timewatch.add(endTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 490, 20));

        iconClockBig.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconClockBig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/clockBig.png"))); // NOI18N
        timewatch.add(iconClockBig, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 130));

        timePageT.add(timewatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 660, 130));

        itemListShow.setBackground(new java.awt.Color(15, 30, 52));
        itemListShow.setEnabled(false);
        itemListShow.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        itemListShowInsidee.setBackground(new java.awt.Color(13, 24, 35));
        itemListShowInsidee.setEnabled(false);
        itemListShowInsidee.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listShow.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        listShow.setForeground(new java.awt.Color(55, 200, 255));
        listShow.setText("-  B01    Amount  : 3");
        listShow.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        itemListShowInsidee.add(listShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 320, 160));

        titleItemListShow.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        titleItemListShow.setForeground(new java.awt.Color(255, 255, 255));
        titleItemListShow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleItemListShow.setText("Item List");
        itemListShowInsidee.add(titleItemListShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 90, 40));

        itemListShow.add(itemListShowInsidee, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 350, 200));

        timePageT.add(itemListShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 390, 220));

        timeupPage.setBackground(new java.awt.Color(15, 30, 52));
        timeupPage.setEnabled(false);
        timeupPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleItemTimeUp.setFont(new java.awt.Font("Leelawadee", 0, 26)); // NOI18N
        titleItemTimeUp.setForeground(new java.awt.Color(255, 255, 255));
        titleItemTimeUp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleItemTimeUp.setText("TIME'S UP");
        timeupPage.add(titleItemTimeUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 50));

        timeupInside.setBackground(new java.awt.Color(13, 24, 35));
        timeupInside.setEnabled(false);
        timeupInside.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listTimeup.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        listTimeup.setForeground(new java.awt.Color(55, 200, 255));
        listTimeup.setText("-  B01    Amount  : 3");
        listTimeup.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        timeupInside.add(listTimeup, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 430, 250));

        timeupPage.add(timeupInside, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 470, 290));

        timePageT.add(timeupPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 510, 380));

        mainUser.add(timePageT, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 421));

        newsPage1.setBackground(new java.awt.Color(25, 41, 65));
        newsPage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listNewsPage.setBackground(new java.awt.Color(25, 41, 65));
        listNewsPage.setPreferredSize(new java.awt.Dimension(750, 400));
        listNewsPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleNewsPageUser.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        titleNewsPageUser.setForeground(new java.awt.Color(255, 255, 255));
        titleNewsPageUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleNewsPageUser.setText("News");
        listNewsPage.add(titleNewsPageUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 70, 30));

        listNewsScroll.setViewportView(listNewsPage);

        newsPage1.add(listNewsScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 790, 440));

        jPanelShowNewsAfterClick.setBackground(new java.awt.Color(25, 41, 65));
        jPanelShowNewsAfterClick.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelShowTopicAfterClick.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabelShowTopicAfterClick.setForeground(new java.awt.Color(255, 255, 255));
        jLabelShowTopicAfterClick.setText("jLabel3");
        jPanelShowNewsAfterClick.add(jLabelShowTopicAfterClick, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 490, 30));

        jTextAreaShowDetailNewsAfterClick.setEditable(false);
        jTextAreaShowDetailNewsAfterClick.setBackground(new java.awt.Color(22, 31, 39));
        jTextAreaShowDetailNewsAfterClick.setColumns(20);
        jTextAreaShowDetailNewsAfterClick.setFont(new java.awt.Font("Leelawadee", 0, 15)); // NOI18N
        jTextAreaShowDetailNewsAfterClick.setForeground(new java.awt.Color(19, 175, 248));
        jTextAreaShowDetailNewsAfterClick.setLineWrap(true);
        jTextAreaShowDetailNewsAfterClick.setRows(5);
        jTextAreaShowDetailNewsAfterClick.setBorder(null);
        jScrollPaneInTextAreaNewsDetail.setViewportView(jTextAreaShowDetailNewsAfterClick);

        jPanelShowNewsAfterClick.add(jScrollPaneInTextAreaNewsDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(226, 60, 500, 302));

        closeNews1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/delete-button.png"))); // NOI18N
        closeNews1.setContentAreaFilled(false);
        closeNews1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeNews1ActionPerformed(evt);
            }
        });
        jPanelShowNewsAfterClick.add(closeNews1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, -1, 50));

        imgNews.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        imgNews.setForeground(new java.awt.Color(255, 255, 255));
        imgNews.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgNews.setText("No Picture.");
        imgNews.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanelShowNewsAfterClick.add(imgNews, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 200, 260));

        newsPage1.add(jPanelShowNewsAfterClick, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 420));

        mainUser.add(newsPage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 420));

        repairPage1.setBackground(new java.awt.Color(25, 41, 65));
        repairPage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelRepairUserSentToAdmin.setBackground(new java.awt.Color(25, 41, 65));
        jPanelRepairUserSentToAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(76, 81, 86));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Bike Repairing");
        jPanel16.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 50));

        jPanelRepairUserSentToAdmin.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 50));

        jPanel11.setBackground(new java.awt.Color(56, 54, 54));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBrandBike.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        jLabelBrandBike.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBrandBike.setText("Why repair?");
        jPanel11.add(jLabelBrandBike, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 90, 40));
        jPanel11.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 340, 10));

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
        jPanel11.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 290, 20));

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
        jPanel11.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 340, 20));

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

        jLabelWarningUserInputColorBike.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jLabelWarningUserInputColorBike.setForeground(new java.awt.Color(255, 0, 0));
        jPanel11.add(jLabelWarningUserInputColorBike, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 190, 20));

        jLabelWarningUserInputWhyRepair.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jLabelWarningUserInputWhyRepair.setForeground(new java.awt.Color(255, 0, 0));
        jPanel11.add(jLabelWarningUserInputWhyRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 190, 20));

        jLabelWarningUserInputModelBike.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jLabelWarningUserInputModelBike.setForeground(new java.awt.Color(255, 0, 0));
        jPanel11.add(jLabelWarningUserInputModelBike, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 190, 20));

        jPanelRepairUserSentToAdmin.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 520, 280));

        jBtToFollowingRepair.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jBtToFollowingRepair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/right arrow.png"))); // NOI18N
        jBtToFollowingRepair.setBorder(null);
        jBtToFollowingRepair.setContentAreaFilled(false);
        jBtToFollowingRepair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtToFollowingRepair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtToFollowingRepairMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtToFollowingRepairMouseExited(evt);
            }
        });
        jBtToFollowingRepair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtToFollowingRepairActionPerformed(evt);
            }
        });
        jPanelRepairUserSentToAdmin.add(jBtToFollowingRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 370, 60, 50));

        titlenextFollingRepairUser.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        titlenextFollingRepairUser.setForeground(new java.awt.Color(255, 255, 255));
        titlenextFollingRepairUser.setText("Send & Next to Following Repair.");
        jPanelRepairUserSentToAdmin.add(titlenextFollingRepairUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 400, -1, -1));

        jButtonFollowingRepair.setBackground(new java.awt.Color(13, 24, 35));
        jButtonFollowingRepair.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jButtonFollowingRepair.setForeground(new java.awt.Color(19, 175, 248));
        jButtonFollowingRepair.setText("Following Repair");
        jButtonFollowingRepair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFollowingRepairActionPerformed(evt);
            }
        });
        jPanelRepairUserSentToAdmin.add(jButtonFollowingRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 120, -1));

        repairPage1.add(jPanelRepairUserSentToAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 420));

        jPanelRepairUserFollowRepairing.setBackground(new java.awt.Color(25, 41, 65));
        jPanelRepairUserFollowRepairing.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtBackRepairUserSentToAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/left arrow.png"))); // NOI18N
        jBtBackRepairUserSentToAdmin.setBorder(null);
        jBtBackRepairUserSentToAdmin.setContentAreaFilled(false);
        jBtBackRepairUserSentToAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBtBackRepairUserSentToAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBtBackRepairUserSentToAdminMouseExited(evt);
            }
        });
        jBtBackRepairUserSentToAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtBackRepairUserSentToAdminActionPerformed(evt);
            }
        });
        jPanelRepairUserFollowRepairing.add(jBtBackRepairUserSentToAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 60, 50));

        jPanelHeadBikeRepairing.setBackground(new java.awt.Color(76, 81, 86));
        jPanelHeadBikeRepairing.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBikeRepairing.setFont(new java.awt.Font("Leelawadee", 0, 22)); // NOI18N
        jLabelBikeRepairing.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBikeRepairing.setText("Bike Repairing");
        jPanelHeadBikeRepairing.add(jLabelBikeRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 180, 50));

        jPanelRepairUserFollowRepairing.add(jPanelHeadBikeRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 50));

        jLabelStatus.setBackground(new java.awt.Color(0, 0, 0));
        jLabelStatus.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        jLabelStatus.setForeground(new java.awt.Color(255, 255, 255));
        jLabelStatus.setText("Status");
        jLabelStatus.setToolTipText("");
        jLabelStatus.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelRepairUserFollowRepairing.add(jLabelStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 50, 30));

        jLabelAskingText.setBackground(new java.awt.Color(0, 0, 0));
        jLabelAskingText.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLabelAskingText.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAskingText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAskingText.setText("Asking");
        jLabelAskingText.setToolTipText("");
        jLabelAskingText.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelRepairUserFollowRepairing.add(jLabelAskingText, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 50, 20));

        jLabelRepairing.setBackground(new java.awt.Color(0, 0, 0));
        jLabelRepairing.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLabelRepairing.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRepairing.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRepairing.setText("Repairing");
        jLabelRepairing.setToolTipText("");
        jLabelRepairing.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelRepairUserFollowRepairing.add(jLabelRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 60, 20));

        jLabelRecieving.setBackground(new java.awt.Color(0, 0, 0));
        jLabelRecieving.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLabelRecieving.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRecieving.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRecieving.setText("Recieving");
        jLabelRecieving.setToolTipText("");
        jLabelRecieving.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelRepairUserFollowRepairing.add(jLabelRecieving, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 60, 20));

        jPanelRecieving.setBackground(new java.awt.Color(255, 255, 255));
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
        jPanelRecieving.add(textRecieving, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 37, 200, 80));

        jLabelIconBikeInRecieving.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIconBikeInRecieving.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/bicycleRepair.png"))); // NOI18N
        jPanelRecieving.add(jLabelIconBikeInRecieving, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 80, 80));

        jPanelRepairUserFollowRepairing.add(jPanelRecieving, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 320, 120));

        jPanelRepairing.setBackground(new java.awt.Color(255, 255, 255));
        jPanelRepairing.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelRepairingText.setBackground(new java.awt.Color(0, 0, 0));
        jLabelRepairingText.setFont(new java.awt.Font("Leelawadee", 1, 14)); // NOI18N
        jLabelRepairingText.setForeground(new java.awt.Color(51, 51, 51));
        jLabelRepairingText.setText("Repairing");
        jLabelRepairingText.setToolTipText("");
        jLabelRepairingText.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelRepairing.add(jLabelRepairingText, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 70, 30));

        textRepair.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jPanelRepairing.add(textRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 260, 80));

        jPanelRepairUserFollowRepairing.add(jPanelRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 310, 120));

        jPnTimeDetail.setBackground(new java.awt.Color(255, 255, 255));
        jPnTimeDetail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTime.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jPnTimeDetail.add(jLabelTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 270, 60));

        jPanelRepairUserFollowRepairing.add(jPnTimeDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 310, 120));

        jPanelAsking.setBackground(new java.awt.Color(255, 255, 255));
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
        jPanelAsking.add(textAsking, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 37, 280, 60));

        jPanelRepairUserFollowRepairing.add(jPanelAsking, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 320, 120));

        jLabelStatusRepairIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/repairUserStep1.png"))); // NOI18N
        jPanelRepairUserFollowRepairing.add(jLabelStatusRepairIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 240, 40));

        titleBackRepairUser.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        titleBackRepairUser.setForeground(new java.awt.Color(255, 255, 255));
        titleBackRepairUser.setText("Back to send ploblem.");
        jPanelRepairUserFollowRepairing.add(titleBackRepairUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, -1, -1));

        repairPage1.add(jPanelRepairUserFollowRepairing, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 420));

        mainUser.add(repairPage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 421));

        cpPage1.setBackground(new java.awt.Color(25, 41, 65));
        cpPage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        showCp.setFont(new java.awt.Font("Leelawadee", 0, 80)); // NOI18N
        showCp.setForeground(new java.awt.Color(255, 255, 255));
        showCp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showCp.setText("200");
        cpPage1.add(showCp, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 310, 260));

        circleCp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        circleCp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/oval.png"))); // NOI18N
        cpPage1.add(circleCp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 370, 350));

        History.setBackground(new java.awt.Color(13, 24, 35));
        History.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        detailBoxHistory.setBackground(new java.awt.Color(255, 255, 255));
        detailBoxHistory.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        poinsHis.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        poinsHis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        poinsHis.setText("200 Points");
        detailBoxHistory.add(poinsHis, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 116, 40));

        actionHis.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        actionHis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        actionHis.setText("4 Cans");
        detailBoxHistory.add(actionHis, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 82, -1));

        History.add(detailBoxHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 230, -1));

        titleHistory.setFont(new java.awt.Font("Leelawadee", 0, 28)); // NOI18N
        titleHistory.setForeground(new java.awt.Color(255, 255, 255));
        titleHistory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleHistory.setText("HISTORY");
        History.add(titleHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 270, -1));

        cpPage1.add(History, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 270, 110));

        point4.setFont(new java.awt.Font("Leelawadee", 0, 40)); // NOI18N
        point4.setForeground(new java.awt.Color(255, 255, 255));
        point4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        point4.setText("Points");
        cpPage1.add(point4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 310, 210));

        mainUser.add(cpPage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 421));

        sharingScroll.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(25, 41, 65), 1, true));

        sharingStep1.setBackground(new java.awt.Color(25, 41, 65));
        sharingStep1.setEnabled(false);
        sharingStep1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nextStep.setBackground(new java.awt.Color(25, 41, 65));
        nextStep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/right arrow.png"))); // NOI18N
        nextStep.setContentAreaFilled(false);
        nextStep.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nextStep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nextStepMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nextStepMouseExited(evt);
            }
        });
        nextStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextStepActionPerformed(evt);
            }
        });
        sharingStep1.add(nextStep, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 370, 60, 50));

        s1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        s1.setForeground(new java.awt.Color(255, 255, 255));
        s1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        s1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/Step1.png"))); // NOI18N
        sharingStep1.add(s1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 40));

        isCp.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        isCp.setForeground(new java.awt.Color(255, 255, 255));
        isCp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        isCp.setText("Your CP : 1000 Points ");
        sharingStep1.add(isCp, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 180, 30));

        sharingScroll.setViewportView(sharingStep1);

        mainUser.add(sharingScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 58, 770, 422));

        getContentPane().add(mainUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        registerPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backSigninRegis.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        backSigninRegis.setForeground(new java.awt.Color(255, 255, 255));
        backSigninRegis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backSigninRegis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/backStepLogin.png"))); // NOI18N
        backSigninRegis.setText("   Back to Sign in");
        backSigninRegis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backSigninRegisMouseClicked(evt);
            }
        });
        registerPage.add(backSigninRegis, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 30));

        jPanelSignUp.setBackground(new java.awt.Color(0, 0, 0));
        jPanelSignUp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLbSignUp.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        jLbSignUp.setForeground(new java.awt.Color(255, 255, 255));
        jLbSignUp.setText("SignUp");
        jPanelSignUp.add(jLbSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 195, 40));
        jPanelSignUp.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 640, 10));

        jTfFirstName.setBackground(new java.awt.Color(0, 0, 0));
        jTfFirstName.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jTfFirstName.setForeground(new java.awt.Color(102, 102, 102));
        jTfFirstName.setText("John");
        jTfFirstName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jTfFirstName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTfFirstNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTfFirstNameFocusLost(evt);
            }
        });
        jPanelSignUp.add(jTfFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 230, 30));

        jLbLastName.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbLastName.setForeground(new java.awt.Color(255, 255, 255));
        jLbLastName.setText("LastName");
        jPanelSignUp.add(jLbLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 60, 20));

        jLbDateOfBirth.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbDateOfBirth.setForeground(new java.awt.Color(255, 255, 255));
        jLbDateOfBirth.setText("Date of Birth");
        jPanelSignUp.add(jLbDateOfBirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 70, 20));

        jLabelStarLN.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLabelStarLN.setForeground(new java.awt.Color(19, 175, 248));
        jLabelStarLN.setText("*");
        jLabelStarLN.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSignUp.add(jLabelStarLN, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 30, 30));

        jComboBoxPosition.setBackground(new java.awt.Color(0, 0, 0));
        jComboBoxPosition.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jComboBoxPosition.setForeground(new java.awt.Color(255, 255, 255));
        jComboBoxPosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "เลือก..", "นักศึกษา ", "อาจารย์และบุคลากร ", "ช่าง ", "เจ้าหน้าที่" }));
        jPanelSignUp.add(jComboBoxPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 240, 30));

        jLbGender.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbGender.setForeground(new java.awt.Color(255, 255, 255));
        jLbGender.setText("Gender");
        jPanelSignUp.add(jLbGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 40, 20));

        jLbPassWord.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbPassWord.setForeground(new java.awt.Color(255, 255, 255));
        jLbPassWord.setText("Password");
        jPanelSignUp.add(jLbPassWord, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 50, 20));

        jTextFieldSurname.setBackground(new java.awt.Color(0, 0, 0));
        jTextFieldSurname.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jTextFieldSurname.setForeground(new java.awt.Color(102, 102, 102));
        jTextFieldSurname.setText("Wick");
        jTextFieldSurname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jTextFieldSurname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldSurnameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldSurnameFocusLost(evt);
            }
        });
        jPanelSignUp.add(jTextFieldSurname, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 240, 30));

        jLbFirstName.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbFirstName.setForeground(new java.awt.Color(255, 255, 255));
        jLbFirstName.setText("FirstName");
        jPanelSignUp.add(jLbFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 60, 20));

        jLbStarId.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbStarId.setForeground(new java.awt.Color(19, 175, 248));
        jLbStarId.setText("*");
        jLbStarId.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSignUp.add(jLbStarId, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 30, 30));

        jLbStarFirstName.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbStarFirstName.setForeground(new java.awt.Color(19, 175, 248));
        jLbStarFirstName.setText("*");
        jLbStarFirstName.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSignUp.add(jLbStarFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 20, 20));

        jLbStarGender.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbStarGender.setForeground(new java.awt.Color(19, 175, 248));
        jLbStarGender.setText("*");
        jLbStarGender.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSignUp.add(jLbStarGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 30, 20));

        jLbStarBirthDay.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbStarBirthDay.setForeground(new java.awt.Color(19, 175, 248));
        jLbStarBirthDay.setText("*");
        jLbStarBirthDay.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSignUp.add(jLbStarBirthDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 30, 30));

        jLbStarPassword.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbStarPassword.setForeground(new java.awt.Color(19, 175, 248));
        jLbStarPassword.setText("*");
        jLbStarPassword.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSignUp.add(jLbStarPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 30, 30));

        jLbPosition.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbPosition.setForeground(new java.awt.Color(255, 255, 255));
        jLbPosition.setText("Position");
        jPanelSignUp.add(jLbPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 50, 20));

        jLbStarPosition.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbStarPosition.setForeground(new java.awt.Color(19, 175, 248));
        jLbStarPosition.setText("*");
        jLbStarPosition.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSignUp.add(jLbStarPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 30, 30));

        jLbCongenitialDisease.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbCongenitialDisease.setForeground(new java.awt.Color(255, 255, 255));
        jLbCongenitialDisease.setText("Congenitial Disease (โรคประจำตัว)");
        jPanelSignUp.add(jLbCongenitialDisease, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 200, 20));

        jTextFieldEmail.setBackground(new java.awt.Color(0, 0, 0));
        jTextFieldEmail.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jTextFieldEmail.setForeground(new java.awt.Color(102, 102, 102));
        jTextFieldEmail.setText("example@gmail.com");
        jTextFieldEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jTextFieldEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldEmailFocusLost(evt);
            }
        });
        jPanelSignUp.add(jTextFieldEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 540, 30));

        jLbTelophone.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbTelophone.setForeground(new java.awt.Color(255, 255, 255));
        jLbTelophone.setText("Phone Number");
        jPanelSignUp.add(jLbTelophone, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 90, 20));

        jLbStarTelophone.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbStarTelophone.setForeground(new java.awt.Color(19, 175, 248));
        jLbStarTelophone.setText("*");
        jLbStarTelophone.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSignUp.add(jLbStarTelophone, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, 30, 30));

        jTextFieldConDisease.setBackground(new java.awt.Color(0, 0, 0));
        jTextFieldConDisease.setForeground(new java.awt.Color(102, 102, 102));
        jTextFieldConDisease.setText("If you have");
        jTextFieldConDisease.setToolTipText("");
        jTextFieldConDisease.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jTextFieldConDisease.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldConDiseaseFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldConDiseaseFocusLost(evt);
            }
        });
        jPanelSignUp.add(jTextFieldConDisease, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 240, 30));

        jLbEmail.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbEmail.setForeground(new java.awt.Color(255, 255, 255));
        jLbEmail.setText("E-Mail");
        jPanelSignUp.add(jLbEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 40, 20));

        jLbStarEmail.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbStarEmail.setForeground(new java.awt.Color(19, 175, 248));
        jLbStarEmail.setText("*");
        jLbStarEmail.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSignUp.add(jLbStarEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 30, 30));

        jPasswordSignUp.setBackground(new java.awt.Color(0, 0, 0));
        jPasswordSignUp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPasswordSignUp.setForeground(new java.awt.Color(102, 102, 102));
        jPasswordSignUp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPasswordSignUp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordSignUpFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordSignUpFocusLost(evt);
            }
        });
        jPanelSignUp.add(jPasswordSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 240, 30));

        jPasswordConfirmPassSignup.setBackground(new java.awt.Color(0, 0, 0));
        jPasswordConfirmPassSignup.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPasswordConfirmPassSignup.setForeground(new java.awt.Color(102, 102, 102));
        jPasswordConfirmPassSignup.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPasswordConfirmPassSignup.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordConfirmPassSignupFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordConfirmPassSignupFocusLost(evt);
            }
        });
        jPanelSignUp.add(jPasswordConfirmPassSignup, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 370, 240, 30));

        jLbConfirmPassWord.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbConfirmPassWord.setForeground(new java.awt.Color(255, 255, 255));
        jLbConfirmPassWord.setText("Comfirm Password");
        jPanelSignUp.add(jLbConfirmPassWord, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, 100, 20));

        jLbStarConfirmPass.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbStarConfirmPass.setForeground(new java.awt.Color(19, 175, 248));
        jLbStarConfirmPass.setText("*");
        jLbStarConfirmPass.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanelSignUp.add(jLbStarConfirmPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 350, 30, 30));

        jButtonSignUp.setBackground(new java.awt.Color(0, 204, 255));
        jButtonSignUp.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jButtonSignUp.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSignUp.setText("SIGN UP");
        jButtonSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSignUpActionPerformed(evt);
            }
        });
        jPanelSignUp.add(jButtonSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 460, 100, 30));

        jRadioButtonGenderFemale.setBackground(new java.awt.Color(0, 0, 0));
        buttonGroupGender.add(jRadioButtonGenderFemale);
        jRadioButtonGenderFemale.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jRadioButtonGenderFemale.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButtonGenderFemale.setText("Female");
        jRadioButtonGenderFemale.setDisplayedMnemonicIndex(0);
        jRadioButtonGenderFemale.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jRadioButtonGenderFemaleFocusGained(evt);
            }
        });
        jPanelSignUp.add(jRadioButtonGenderFemale, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 70, -1));

        jRadioButtonGenderMale.setBackground(new java.awt.Color(0, 0, 0));
        buttonGroupGender.add(jRadioButtonGenderMale);
        jRadioButtonGenderMale.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jRadioButtonGenderMale.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButtonGenderMale.setText("Male");
        jRadioButtonGenderMale.setDisplayedMnemonicIndex(1);
        jRadioButtonGenderMale.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jRadioButtonGenderMaleFocusGained(evt);
            }
        });
        jPanelSignUp.add(jRadioButtonGenderMale, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, -1, -1));

        jLabelPictureProfileRegister.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLabelPictureProfileRegister.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPictureProfileRegister.setText("Picture Profile: ");
        jPanelSignUp.add(jLabelPictureProfileRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, -1, 20));

        jButtonChooseFileForUpload.setBackground(new java.awt.Color(13, 24, 35));
        jButtonChooseFileForUpload.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jButtonChooseFileForUpload.setForeground(new java.awt.Color(0, 204, 255));
        jButtonChooseFileForUpload.setText("Choose File");
        jButtonChooseFileForUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChooseFileForUploadActionPerformed(evt);
            }
        });
        jPanelSignUp.add(jButtonChooseFileForUpload, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 100, -1));

        jLabelPartPictureUserUpload.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLabelPartPictureUserUpload.setForeground(new java.awt.Color(102, 102, 102));
        jLabelPartPictureUserUpload.setText("Not Select File");
        jLabelPartPictureUserUpload.setToolTipText("");
        jPanelSignUp.add(jLabelPartPictureUserUpload, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, 160, 20));

        jLbId.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLbId.setForeground(new java.awt.Color(255, 255, 255));
        jLbId.setText("ID");
        jPanelSignUp.add(jLbId, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 20, 20));

        jLabelEmailNotCorrect.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jLabelEmailNotCorrect.setForeground(new java.awt.Color(255, 0, 0));
        jPanelSignUp.add(jLabelEmailNotCorrect, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 280, 20));

        jLabelNoticSurname.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jLabelNoticSurname.setForeground(new java.awt.Color(255, 0, 0));
        jPanelSignUp.add(jLabelNoticSurname, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 180, 20));

        jLabelifLengthId.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jLabelifLengthId.setForeground(new java.awt.Color(255, 0, 0));
        jPanelSignUp.add(jLabelifLengthId, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 220, 20));

        jLabelNoticNewPass.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jLabelNoticNewPass.setForeground(new java.awt.Color(255, 0, 0));
        jPanelSignUp.add(jLabelNoticNewPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 350, 130, 20));

        jLabelNoticName.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jLabelNoticName.setForeground(new java.awt.Color(255, 0, 0));
        jPanelSignUp.add(jLabelNoticName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 180, 20));

        jLabelNoticGender.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jLabelNoticGender.setForeground(new java.awt.Color(255, 0, 0));
        jPanelSignUp.add(jLabelNoticGender, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 180, 20));

        jLabelNoticBirthDate.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jLabelNoticBirthDate.setForeground(new java.awt.Color(255, 0, 0));
        jPanelSignUp.add(jLabelNoticBirthDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 160, 20));

        jLabelNoticPosition.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jLabelNoticPosition.setForeground(new java.awt.Color(255, 0, 0));
        jPanelSignUp.add(jLabelNoticPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 180, 20));

        jLabelNoticTel.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jLabelNoticTel.setForeground(new java.awt.Color(255, 0, 0));
        jPanelSignUp.add(jLabelNoticTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, 150, 20));

        jLabelNoticOldPass.setFont(new java.awt.Font("Leelawadee", 0, 11)); // NOI18N
        jLabelNoticOldPass.setForeground(new java.awt.Color(255, 0, 0));
        jPanelSignUp.add(jLabelNoticOldPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 180, 20));

        jDateChooserBirthDate.setBackground(new java.awt.Color(0, 0, 0));
        jDateChooserBirthDate.setForeground(new java.awt.Color(255, 255, 255));
        jPanelSignUp.add(jDateChooserBirthDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, 240, 30));

        jFormatTextFieldForId.setBackground(new java.awt.Color(0, 0, 0));
        jFormatTextFieldForId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jFormatTextFieldForId.setForeground(new java.awt.Color(102, 102, 102));
        jFormatTextFieldForId.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###########"))));
        jFormatTextFieldForId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormatTextFieldForIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormatTextFieldForIdFocusLost(evt);
            }
        });
        jPanelSignUp.add(jFormatTextFieldForId, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 240, 30));

        jFormattedTelophone.setBackground(new java.awt.Color(0, 0, 0));
        jFormattedTelophone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jFormattedTelophone.setForeground(new java.awt.Color(102, 102, 102));
        jFormattedTelophone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0000000000"))));
        jFormattedTelophone.setToolTipText("");
        jFormattedTelophone.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jFormattedTelophone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTelophoneFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTelophoneFocusLost(evt);
            }
        });
        jPanelSignUp.add(jFormattedTelophone, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, 240, 30));

        registerPage.add(jPanelSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 680, 500));

        Backgroung2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/background.jpg"))); // NOI18N
        Backgroung2.setText("jLabel1");
        registerPage.add(Backgroung2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 540));

        getContentPane().add(registerPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, -1));

        login.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        GreenSociety.setFont(new java.awt.Font("Leelawadee", 0, 48)); // NOI18N
        GreenSociety.setForeground(new java.awt.Color(255, 255, 255));
        GreenSociety.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GreenSociety.setText("Green Society");
        login.add(GreenSociety, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 340, 70));

        iconEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/mail.png"))); // NOI18N
        login.add(iconEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 30, 30));

        iconKey.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconKey.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/pass.png"))); // NOI18N
        login.add(iconKey, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, 30, 30));

        email.setBackground(new java.awt.Color(17, 20, 25));
        email.setFont(new java.awt.Font("Leelawadee", 0, 15)); // NOI18N
        email.setForeground(new java.awt.Color(153, 153, 153));
        email.setText("Email");
        email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFocusLost(evt);
            }
        });
        login.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 210, 240, 30));

        sEmailG.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sEmailG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/gray.png"))); // NOI18N
        login.add(sEmailG, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, -1, -1));

        sEmailB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sEmailB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/blue.png"))); // NOI18N
        login.add(sEmailB, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, -1, -1));

        showPass.setFont(new java.awt.Font("Leelawadee", 0, 15)); // NOI18N
        showPass.setForeground(new java.awt.Color(153, 153, 153));
        showPass.setText("Password");
        login.add(showPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 259, 70, 30));

        password.setBackground(new java.awt.Color(17, 20, 25));
        password.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        password.setForeground(new java.awt.Color(255, 255, 255));
        password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFocusLost(evt);
            }
        });
        login.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, 240, 30));

        sPassG.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sPassG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/gray.png"))); // NOI18N
        login.add(sPassG, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 286, -1, 10));

        sPassB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sPassB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/blue.png"))); // NOI18N
        login.add(sPassB, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 286, -1, 10));

        warning.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        warning.setForeground(new java.awt.Color(255, 51, 51));
        warning.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        warning.setText("Your username or password is incorrect.");
        login.add(warning, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, 280, 20));

        signin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/signinBut.png"))); // NOI18N
        signin.setContentAreaFilled(false);
        signin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signinMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signinMouseExited(evt);
            }
        });
        signin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signinActionPerformed(evt);
            }
        });
        login.add(signin, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 330, 300, 30));

        backLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/backLogin.PNG"))); // NOI18N
        backLogin.setLabelFor(this);
        login.add(backLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, -1, -1));

        menuSignup.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        menuSignup.setForeground(new java.awt.Color(255, 255, 255));
        menuSignup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuSignup.setText("Sign Up");
        menuSignup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuSignup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSignupMouseClicked(evt);
            }
        });
        login.add(menuSignup, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 400, 60, 30));

        forgotPass.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        forgotPass.setForeground(new java.awt.Color(204, 204, 204));
        forgotPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        forgotPass.setText("Forgot Password?");
        forgotPass.setToolTipText("");
        forgotPass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        forgotPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgotPassMouseClicked(evt);
            }
        });
        login.add(forgotPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, 120, 30));

        titleSignup.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        titleSignup.setForeground(new java.awt.Color(102, 102, 102));
        titleSignup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleSignup.setText("Don't have an account? ");
        titleSignup.setToolTipText("");
        login.add(titleSignup, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 400, 140, 30));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/background.jpg"))); // NOI18N
        login.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        forgotPassPage.setBackground(new java.awt.Color(255, 255, 255));
        forgotPassPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputForgot.setBackground(new java.awt.Color(0, 0, 0));
        inputForgot.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleForgotPass.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        titleForgotPass.setForeground(new java.awt.Color(255, 255, 255));
        titleForgotPass.setText("Forgot Password");
        inputForgot.add(titleForgotPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 199, 50));
        inputForgot.add(s10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 480, -1));

        titleEmail.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        titleEmail.setForeground(new java.awt.Color(114, 118, 120));
        titleEmail.setText("Your Email");
        inputForgot.add(titleEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 80, 30));

        forgotEmail.setBackground(new java.awt.Color(0, 0, 0));
        forgotEmail.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        forgotEmail.setForeground(new java.awt.Color(153, 153, 153));
        forgotEmail.setText("Email");
        forgotEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        forgotEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                forgotEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                forgotEmailFocusLost(evt);
            }
        });
        inputForgot.add(forgotEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 340, 30));

        titleNewPass.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        titleNewPass.setForeground(new java.awt.Color(114, 118, 120));
        titleNewPass.setText("New Password");
        inputForgot.add(titleNewPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 100, 30));

        titleConPass.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        titleConPass.setForeground(new java.awt.Color(114, 118, 120));
        titleConPass.setText("Confirm Password");
        inputForgot.add(titleConPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 120, 30));

        jPasswordField1.setBackground(new java.awt.Color(0, 0, 0));
        jPasswordField1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        inputForgot.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 340, 30));

        jPasswordField2.setBackground(new java.awt.Color(0, 0, 0));
        jPasswordField2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPasswordField2.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        inputForgot.add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 340, 30));

        star1.setBackground(new java.awt.Color(0, 0, 0));
        star1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        star1.setForeground(new java.awt.Color(19, 175, 248));
        star1.setText("*");
        inputForgot.add(star1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 30, 20));

        star2.setBackground(new java.awt.Color(0, 0, 0));
        star2.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        star2.setForeground(new java.awt.Color(19, 175, 248));
        star2.setText("*");
        inputForgot.add(star2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 30, 20));

        star3.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        star3.setForeground(new java.awt.Color(19, 175, 248));
        star3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        star3.setText("*");
        inputForgot.add(star3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 20, 20));

        submitPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitPass.png"))); // NOI18N
        submitPass.setContentAreaFilled(false);
        submitPass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        submitPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitPassMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitPassMouseExited(evt);
            }
        });
        submitPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitPassActionPerformed(evt);
            }
        });
        inputForgot.add(submitPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 360, -1));

        errorForgot.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        errorForgot.setForeground(new java.awt.Color(255, 51, 51));
        errorForgot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        inputForgot.add(errorForgot, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 340, 20));

        forgotPassPage.add(inputForgot, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 520, 360));

        backSignin.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        backSignin.setForeground(new java.awt.Color(255, 255, 255));
        backSignin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backSignin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/backStepLogin.png"))); // NOI18N
        backSignin.setText("   Back to Sign in");
        backSignin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backSignin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backSigninMouseClicked(evt);
            }
        });
        forgotPassPage.add(backSignin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 40));

        Background1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/background.jpg"))); // NOI18N
        forgotPassPage.add(Background1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(forgotPassPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signinActionPerformed
        if (email.getText().equals("Email") || new String(password.getPassword()).equals("")) {
            warning.setVisible(true);
        } else {
            String username = email.getText();
            String pass = new String(password.getPassword());
            boolean correct = ac.login(username, pass);
            if (correct == true) {
                if (User.getPositon().equals("-")) {
                    setColorOfBarMenu();
                    login.setVisible(false);
                    mainUser.setVisible(true);
                    mainUserSetVisible();
                    mainAdmin.setVisible(false);
                    mainAdminSetVisible();
                    newsPage1.setVisible(true);
                    news1.setForeground(new java.awt.Color(19, 175, 248));
                    listNewsScroll.setVisible(true);
                    listNewsPage.setVisible(true);
                    jPanelShowNewsAfterClick.setVisible(false);
                    setListNewsUser();
                    name1.setText(User.getFirstName().toUpperCase());
                    pic1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(User.getImgPath())).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
                    boolean status = sh.checkStatus();
                    if (status == false) {
                        boolean timesup = sh.isTimesUp();
                        if (timesup == false) {
                            try {
                                sh.checkTime();
                            } catch (InterruptedException ie) {
                                ie.printStackTrace();
                            }
                        } else {
                            circleMini.setVisible(true);
                            circleNoti.setVisible(true);
                        }
                    }
                    setDataReparing();
                } else if (User.getPositon().equalsIgnoreCase("Officer") || User.getPositon().equalsIgnoreCase("Technician")) {
                    setColorOfBarMenu();
                    login.setVisible(false);
                    mainAdmin.setVisible(true);
                    mainAdminSetVisible();
                    historyPage.setVisible(true);
                    showStatHistory();
                    history.setForeground(new Color(19,175,248));
                    mainUser.setVisible(false);
                    mainUserSetVisible();
                    name.setText(User.getFirstName().toUpperCase());
                    position.setText(User.getPositon());
                    pic.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(User.getImgPath())).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
                }
            } else if (correct == false) {
                warning.setVisible(true);
            }
        }
    }//GEN-LAST:event_signinActionPerformed

    private void emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusGained
        if (email.getText().equals("Email")) {
            email.setForeground(new Color(255, 255, 255));
            email.setText("");
        }
        sEmailB.setVisible(true);
        sEmailG.setVisible(false);
    }//GEN-LAST:event_emailFocusGained

    private void emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusLost
        if (email.getText().equals("") || email.getText().length() == 0) {
            email.setForeground(new Color(153, 153, 153));
            email.setText("Email");
        }
        sEmailB.setVisible(false);
        sEmailG.setVisible(true);
    }//GEN-LAST:event_emailFocusLost

    private void passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusGained
        if (showPass.getText().equals("Password")) {
            showPass.setVisible(false);
        }
        sPassB.setVisible(true);
        sPassG.setVisible(false);
    }//GEN-LAST:event_passwordFocusGained

    private void passwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusLost
        if (password.getText().equals("") || password.getText().length() == 0) {
            showPass.setVisible(true);
        }
        sPassB.setVisible(false);
        sPassG.setVisible(true);
    }//GEN-LAST:event_passwordFocusLost

    private void signinMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signinMouseEntered
        signin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/signinButClick.png")));
    }//GEN-LAST:event_signinMouseEntered

    private void signinMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signinMouseExited
        signin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/signinBut.png")));
    }//GEN-LAST:event_signinMouseExited

    private void iconSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconSearchMouseClicked
        search.requestFocusInWindow();
    }//GEN-LAST:event_iconSearchMouseClicked

    private void searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusGained
        if (search.getText().equals("Search")) {
            search.setText("");
        }
    }//GEN-LAST:event_searchFocusGained

    private void searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusLost
        if (search.getText().equals("") || search.getText().length() == 0) {
            search.setText("Search");
        }
    }//GEN-LAST:event_searchFocusLost

    private void canCounterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canCounterMouseClicked
        news.setForeground(new java.awt.Color(255, 255, 255));
        canCounter.setForeground(new java.awt.Color(19, 175, 248));
        bikeRepair.setForeground(new java.awt.Color(255, 255, 255));
        bikeSharing.setForeground(new java.awt.Color(255, 255, 255));
        timer.setForeground(new java.awt.Color(255, 255, 255));
        history.setForeground(new java.awt.Color(255, 255, 255));
        support.setForeground(new java.awt.Color(255, 255, 255));
        profile.setForeground(new java.awt.Color(255, 255, 255));
        newsPage.setVisible(false);
        cpPage.setVisible(true);
        repairPage.setVisible(false);
        sharingPage.setVisible(false);
        timerPage.setVisible(false);
        historyPage.setVisible(false);
        supportPage.setVisible(false);
        userProfilePage.setVisible(false);

        nameOfUser.setText("    Name");
        surnameOfUser.setText("    Surname");
        countOfCan.setText("    Count");
        nameOfUser.setForeground(new Color(153, 153, 153, 150));
        surnameOfUser.setForeground(new Color(153, 153, 153, 150));
        countOfCan.setForeground(new Color(153, 153, 153, 150));
    }//GEN-LAST:event_canCounterMouseClicked

    private void bikeSharingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bikeSharingMouseClicked
        news.setForeground(new java.awt.Color(255, 255, 255));
        canCounter.setForeground(new java.awt.Color(255, 255, 255));
        bikeRepair.setForeground(new java.awt.Color(255, 255, 255));
        bikeSharing.setForeground(new java.awt.Color(19, 175, 248));
        timer.setForeground(new java.awt.Color(255, 255, 255));
        history.setForeground(new java.awt.Color(255, 255, 255));
        support.setForeground(new java.awt.Color(255, 255, 255));
        profile.setForeground(new java.awt.Color(255, 255, 255));
        newsPage.setVisible(false);
        cpPage.setVisible(false);
        repairPage.setVisible(false);
        sharingPage.setVisible(true);
        timerPage.setVisible(false);
        historyPage.setVisible(false);
        supportPage.setVisible(false);
        userProfilePage.setVisible(false);

        menuAdd.setForeground(new Color(102, 102, 102));
        menuEdit.setForeground(new Color(102, 102, 102));
        menuDelete.setForeground(new Color(102, 102, 102));
        menuSharing.setVisible(true);
        backAdd.setVisible(false);
        backEdit.setVisible(false);
        backDelete.setVisible(false);
        sharingPageTab.setVisible(false);
        radioType.setSelected(false);
        radioEquip.setSelected(false);
        layerAddItem.setVisible(false);
        canCounter.setForeground(new java.awt.Color(255, 255, 255));
        itemListScroll.setVisible(false);

    }//GEN-LAST:event_bikeSharingMouseClicked

    private void timerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timerMouseClicked
        news.setForeground(new java.awt.Color(255, 255, 255));
        canCounter.setForeground(new java.awt.Color(255, 255, 255));
        bikeRepair.setForeground(new java.awt.Color(255, 255, 255));
        bikeSharing.setForeground(new java.awt.Color(255, 255, 255));
        timer.setForeground(new java.awt.Color(19, 175, 248));
        history.setForeground(new java.awt.Color(255, 255, 255));
        support.setForeground(new java.awt.Color(255, 255, 255));
        profile.setForeground(new java.awt.Color(255, 255, 255));
        newsPage.setVisible(false);
        cpPage.setVisible(false);
        repairPage.setVisible(false);
        sharingPage.setVisible(false);
        timerPage.setVisible(true);
        historyPage.setVisible(false);
        supportPage.setVisible(false);
        userProfilePage.setVisible(false);
        userDetail.setVisible(false);
        selectItemReturn.setVisible(false);
        setUserBorrow();
        
        timewatch1.setVisible(true);
        listUserBorrowing.setVisible(true);
        Date current = new java.util.Date();
        try {
            sh.timeAdmin(current);
            endTime1.setText("End:    " + current.getDate() + " / " + (current.getMonth() + 1) + " / " + (current.getYear() + 1900) + "  18 : 00");
            if(sh.getTimeDetail().indexOf("-") != -1){
                timeLeftShow1.setText("Closed.");
            }else{
                timeLeftShow1.setText(sh.getTimeDetail());
                new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    endTime1.setText("End:    " + current.getDate() + " / " + (current.getMonth() + 1) + " / " + (current.getYear() + 1900) + "  18 : 00");
                    if(sh.getTimeDetail().indexOf("-") != -1){
                        timeLeftShow1.setText("Closed.");
                    }else{
                        timeLeftShow1.setText(sh.getTimeDetail());
                    }
                }
                }).start();
            }
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }

    }//GEN-LAST:event_timerMouseClicked

    private void signoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signoutMouseEntered
        titleSignout.setVisible(true);
    }//GEN-LAST:event_signoutMouseEntered

    private void signoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signoutMouseExited
        titleSignout.setVisible(false);
    }//GEN-LAST:event_signoutMouseExited

    private void signoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signoutActionPerformed
        int ans = JOptionPane.showConfirmDialog(this, "Do you want to Sign out ?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (ans == JOptionPane.YES_OPTION) {
            User.setUserId(0);
            User.setOfficeId(null);
            User.setFirstName(null);
            User.setLastName(null);
            User.setGender(null);
            User.setBirthDate(null);
            User.setCongenitialDisease(null);
            User.setEmail(null);
            User.setTel(null);
            User.setDept(null);
            User.setDeptId(null);
            User.setPositon(null);
            User.setImgPath(null);
            login.setVisible(true);
            mainAdmin.setVisible(false);
            mainUser.setVisible(false);
            password.setText("");
            showPass.setVisible(true);
            email.setText("Email");
            email.setForeground(new Color(153, 153, 153));
            warning.setVisible(false);
            mainAdminSetVisible();
            mainUserSetVisible();
        }
    }//GEN-LAST:event_signoutActionPerformed

    private void nameOfUserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameOfUserFocusGained
        if (nameOfUser.getText().equals("    Name")) {
            nameOfUser.setForeground(new Color(255, 255, 255));
            nameOfUser.setText("");
        }
    }//GEN-LAST:event_nameOfUserFocusGained

    private void nameOfUserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameOfUserFocusLost
        if (nameOfUser.getText().equals("") || nameOfUser.getText().length() == 0) {
            nameOfUser.setForeground(new Color(153, 153, 153, 150));
            nameOfUser.setText("    Name");
        }
    }//GEN-LAST:event_nameOfUserFocusLost

    private void countOfCanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_countOfCanFocusGained
        if (countOfCan.getText().equals("    Count")) {
            countOfCan.setForeground(new Color(255, 255, 255));
            countOfCan.setText("");
        }
    }//GEN-LAST:event_countOfCanFocusGained

    private void countOfCanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_countOfCanFocusLost
        if (countOfCan.getText().equals("") || countOfCan.getText().length() == 0) {
            countOfCan.setForeground(new Color(153, 153, 153, 150));
            countOfCan.setText("    Count");
        }
    }//GEN-LAST:event_countOfCanFocusLost

    private void submitButMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButMouseEntered
        submitBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitClick.png")));
    }//GEN-LAST:event_submitButMouseEntered

    private void submitButMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButMouseExited
        submitBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submit.png")));
    }//GEN-LAST:event_submitButMouseExited

    private void submitButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButActionPerformed
        String name = nameOfUser.getText();
        String surname = surnameOfUser.getText();
        String can = countOfCan.getText();
        if (name.equals("    Name") || surname.equals("    Surname")) {
            JOptionPane.showMessageDialog(this, "Please EnterName and Surname", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                int c = Integer.parseInt(can);
                sh.getCp().increseCp(name, surname, c);
                JOptionPane.showMessageDialog(this, "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
                nameOfUser.setForeground(new Color(153, 153, 153, 150));
                nameOfUser.setText("    Name");
                countOfCan.setForeground(new Color(153, 153, 153, 150));
                countOfCan.setText("    Count");
                surnameOfUser.setForeground(new Color(153, 153, 153, 150));
                surnameOfUser.setText("    Surname");
            } catch (SelectMemberNameException smn) {
                JOptionPane.showMessageDialog(this, smn.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ne) {
                JOptionPane.showMessageDialog(this, "Can of Count not correct, Please enter again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_submitButActionPerformed

    private void cancleAdminButMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleAdminButMouseEntered
        cancleAdminBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleButClick.png")));
    }//GEN-LAST:event_cancleAdminButMouseEntered

    private void cancleAdminButMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleAdminButMouseExited
        cancleAdminBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleBut.png")));
    }//GEN-LAST:event_cancleAdminButMouseExited

    private void cancleAdminButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleAdminButActionPerformed
        nameOfUser.setForeground(new Color(153, 153, 153, 150));
        nameOfUser.setText("    Name");
        countOfCan.setForeground(new Color(153, 153, 153, 150));
        countOfCan.setText("    Count");
        surnameOfUser.setForeground(new Color(153, 153, 153, 150));
        surnameOfUser.setText("    Surname");
    }//GEN-LAST:event_cancleAdminButActionPerformed

    private void surnameOfUserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_surnameOfUserFocusGained
        if (surnameOfUser.getText().equals("    Surname")) {
            surnameOfUser.setForeground(new Color(255, 255, 255));
            surnameOfUser.setText("");
        }
    }//GEN-LAST:event_surnameOfUserFocusGained

    private void surnameOfUserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_surnameOfUserFocusLost
        if (surnameOfUser.getText().equals("") || surnameOfUser.getText().length() == 0) {
            surnameOfUser.setForeground(new Color(153, 153, 153, 150));
            surnameOfUser.setText("    Surname");
        }
    }//GEN-LAST:event_surnameOfUserFocusLost

    private void menuAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAddActionPerformed
        titleItemName.setForeground(new Color(255, 255, 255));
        titleItemCount.setForeground(new Color(255, 255, 255));
        titleItemId.setForeground(new Color(255, 255, 255));
        menuAdd.setForeground(new Color(255, 255, 255));
        menuEdit.setForeground(new Color(102, 102, 102));
        menuDelete.setForeground(new Color(102, 102, 102));
        backAdd.setVisible(true);
        backEdit.setVisible(false);
        backDelete.setVisible(false);
        sharingPageTab.setVisible(true);
        titleWhat.setText("What's item do you want to add?");
        radioType.setSelected(false);
        radioEquip.setSelected(false);
        radioType.setVisible(true);
        radioEquip.setVisible(true);
        layerAddItem.setVisible(false);
        layerAddItem.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Add Item", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Leelawadee", 0, 20), new java.awt.Color(3, 153, 223)));
        itemListScroll.setVisible(false);
        notiSharing.setVisible(false);
        itemName.setVisible(true);
        itemAmount.setVisible(true);
        itemCP.setVisible(true);
        titleItemCount.setVisible(true);
        titleItemCP.setVisible(true);
        titleItemName.setVisible(true);
        titleItemPic.setVisible(true);
        chooseImgItem.setVisible(true);
        pathImgItem.setVisible(true);
        s7.setVisible(true);
        s8.setVisible(true);
        s11.setVisible(true);
    }//GEN-LAST:event_menuAddActionPerformed

    private void menuEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEditActionPerformed
        settingItem();
        titleItemName.setForeground(new Color(255, 255, 255));
        titleItemCount.setForeground(new Color(255, 255, 255));
        titleItemId.setForeground(new Color(255, 255, 255));
        menuAdd.setForeground(new Color(102, 102, 102));
        menuEdit.setForeground(new Color(255, 255, 255));
        menuDelete.setForeground(new Color(102, 102, 102));
        backAdd.setVisible(false);
        backEdit.setVisible(true);
        backDelete.setVisible(false);
        sharingPageTab.setVisible(true);
        titleWhat.setText("What's item do you want to edit?");
        radioType.setSelected(false);
        radioEquip.setSelected(false);
        radioType.setVisible(false);
        radioEquip.setVisible(false);
        layerAddItem.setVisible(true);
        layerAddItem.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Edit Item", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Leelawadee", 0, 20), new java.awt.Color(3, 153, 223)));
        itemListScroll.setVisible(false);
        notiSharing.setVisible(false);
        itemId.setText("ID");
        itemId.setEditable(true);
        itemId.setForeground(new Color(153, 153, 153, 150));
        itemName.setText("Name");
        itemName.setForeground(new Color(153, 153, 153, 150));
        itemName.setVisible(true);
        itemAmount.setText("Count");
        itemAmount.setForeground(new Color(153, 153, 153, 150));
        itemAmount.setVisible(true);
        itemCP.setText("Count");
        itemCP.setForeground(new Color(153, 153, 153, 150));
        itemCP.setVisible(false);
        itemList.setVisible(true);
        titleItemCount.setVisible(true);
        titleItemCP.setVisible(false);
        titleItemName.setVisible(true);
        titleItemPic.setVisible(false);
        chooseImgItem.setVisible(false);
        pathImgItem.setVisible(false);
        s7.setVisible(true);
        s8.setVisible(true);
        s11.setVisible(false);
    }//GEN-LAST:event_menuEditActionPerformed

    private void menuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeleteActionPerformed
        settingItem();
        titleItemName.setForeground(new Color(255, 255, 255));
        titleItemCount.setForeground(new Color(255, 255, 255));
        titleItemId.setForeground(new Color(255, 255, 255));
        menuAdd.setForeground(new Color(102, 102, 102));
        menuEdit.setForeground(new Color(102, 102, 102));
        menuDelete.setForeground(new Color(255, 255, 255));
        backAdd.setVisible(false);
        backEdit.setVisible(false);
        backDelete.setVisible(true);
        sharingPageTab.setVisible(true);
        titleWhat.setText("What's item do you want to delete?");
        radioType.setSelected(false);
        radioEquip.setSelected(false);
        layerAddItem.setVisible(true);
        layerAddItem.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Delete Item", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Leelawadee", 0, 20), new java.awt.Color(3, 153, 223)));
        itemListScroll.setVisible(false);
        notiSharing.setVisible(false);
        itemId.setText("ID");
        itemId.setForeground(new Color(153, 153, 153, 150));
        itemId.setEditable(true);
        itemName.setText("Name");
        itemName.setForeground(new Color(153, 153, 153, 150));
        itemName.setVisible(false);
        itemAmount.setText("Count");
        itemAmount.setForeground(new Color(153, 153, 153, 150));
        itemAmount.setVisible(false);
        itemCP.setText("Count");
        itemCP.setForeground(new Color(153, 153, 153, 150));
        itemCP.setVisible(false);
        itemList.setVisible(true);
        titleItemCount.setVisible(false);
        titleItemCP.setVisible(false);
        titleItemName.setVisible(false);
        titleItemPic.setVisible(false);
        chooseImgItem.setVisible(false);
        pathImgItem.setVisible(false);
        s7.setVisible(false);
        s8.setVisible(false);
        s11.setVisible(false);
        radioEquip.setVisible(false);
        radioType.setVisible(false);
    }//GEN-LAST:event_menuDeleteActionPerformed

    private void itemNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_itemNameFocusGained
        if (itemName.getText().equals("Name")) {
            itemName.setForeground(new Color(255, 255, 255));
            itemName.setText("");
        }
    }//GEN-LAST:event_itemNameFocusGained

    private void itemNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_itemNameFocusLost
        if (itemName.getText().equals("") || itemName.getText().length() == 0) {
            itemName.setForeground(new Color(153, 153, 153, 150));
            itemName.setText("Name");
        }
    }//GEN-LAST:event_itemNameFocusLost

    private void itemAmountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_itemAmountFocusGained
        if (itemAmount.getText().equals("Count")) {
            itemAmount.setForeground(new Color(255, 255, 255));
            itemAmount.setText("");
        }
    }//GEN-LAST:event_itemAmountFocusGained

    private void itemAmountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_itemAmountFocusLost
        if (itemAmount.getText().equals("") || itemAmount.getText().length() == 0) {
            itemAmount.setForeground(new Color(153, 153, 153, 150));
            itemAmount.setText("Count");
        }
    }//GEN-LAST:event_itemAmountFocusLost

    private void submitButSharingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButSharingMouseEntered
        submitButSharing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMiniClick.png")));
    }//GEN-LAST:event_submitButSharingMouseEntered

    private void submitButSharingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButSharingMouseExited
        submitButSharing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMini.png")));
    }//GEN-LAST:event_submitButSharingMouseExited

    private void submitButSharingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButSharingActionPerformed
        if (backAdd.isVisible()) {
            String id = itemId.getText();
            String name = itemName.getText();
            String pathPic = pathImgItem.getText();
            titleItemName.setForeground(new Color(255, 255, 255));
            titleItemCount.setForeground(new Color(255, 255, 255));
            titleItemPic.setForeground(new Color(255, 255, 255));
            try {
                int count = Integer.parseInt(itemAmount.getText());
                int cp = Integer.parseInt(itemCP.getText());
                if (name.equals("Name")) {
                    if (pathPic.equals("")) {
                        titleItemName.setForeground(new Color(255, 51, 51));
                        titleItemPic.setForeground(new Color(255, 51, 51));
                        notiSharing.setText("Not Success,Please enter again");
                        notiSharing.setForeground(new Color(255, 51, 51));
                        notiSharing.setVisible(true);
                    } else {
                        titleItemName.setForeground(new Color(255, 51, 51));
                        notiSharing.setText("Not Success,Please enter again");
                        notiSharing.setForeground(new Color(255, 51, 51));
                        notiSharing.setVisible(true);
                    }
                } else if (pathPic.equals("")) {
                    titleItemPic.setForeground(new Color(255, 51, 51));
                    notiSharing.setText("Not Success,Please enter again");
                    notiSharing.setForeground(new Color(255, 51, 51));
                    notiSharing.setVisible(true);
                } else {
                    sh.addItem(id, name, cp,count, pathPic);
                    notiSharing.setText("Success.");
                    notiSharing.setForeground(new Color(130, 255, 134));
                    notiSharing.setVisible(true);
                    itemId.setForeground(new Color(153, 153, 153, 150));
                    itemId.setText("ID");
                    itemName.setForeground(new Color(153, 153, 153, 150));
                    itemName.setText("Name");
                    itemAmount.setForeground(new Color(153, 153, 153, 150));
                    itemAmount.setText("Count");
                    itemCP.setForeground(new Color(153, 153, 153, 150));
                    itemCP.setText("Count");
                    pathImgItem.setText("");
                }
            } catch (NumberFormatException ne) {
                if (name.equals("Name")) {
                    if (pathPic.equals("")) {
                        titleItemName.setForeground(new Color(255, 51, 51));
                        titleItemPic.setForeground(new Color(255, 51, 51));
                        titleItemCount.setForeground(new Color(255, 51, 51));
                        titleItemCP.setForeground(new Color(255, 51, 51));
                        notiSharing.setText("Not Success,Please enter again");
                        notiSharing.setForeground(new Color(255, 51, 51));
                        notiSharing.setVisible(true);
                    } else {
                        titleItemName.setForeground(new Color(255, 51, 51));
                        titleItemCount.setForeground(new Color(255, 51, 51));
                        titleItemCP.setForeground(new Color(255, 51, 51));
                        notiSharing.setText("Not Success,Please enter again");
                        notiSharing.setForeground(new Color(255, 51, 51));
                        notiSharing.setVisible(true);
                    }
                } else if (pathPic.equals("")) {
                    titleItemCount.setForeground(new Color(255, 51, 51));
                    titleItemCP.setForeground(new Color(255, 51, 51));
                    titleItemPic.setForeground(new Color(255, 51, 51));
                    notiSharing.setText("Not Success,Please enter again");
                    notiSharing.setForeground(new Color(255, 51, 51));
                    notiSharing.setVisible(true);
                } else {
                    titleItemPic.setForeground(new Color(255, 51, 51));
                    notiSharing.setText("Not Success,Please enter again");
                    notiSharing.setForeground(new Color(255, 51, 51));
                    notiSharing.setVisible(true);
                }
            }
            settingItem();
        } else if (backEdit.isVisible()) {
            boolean check = false;
            String id = itemId.getText();
            String allId[] = sh.getItemID();
            for (int i = 0; i < allId.length; i++) {
                if (id.equals(allId[i])) {
                    check = true;
                }
            }
            String name = itemName.getText();
            titleItemName.setForeground(new Color(255, 255, 255));
            titleItemCount.setForeground(new Color(255, 255, 255));
            titleItemId.setForeground(new Color(255, 255, 255));

            try {
                int count = Integer.parseInt(itemAmount.getText());
                if (check == false && name.equals("Name")) {
                    titleItemId.setForeground(new Color(255, 51, 51));
                    titleItemName.setForeground(new Color(255, 51, 51));
                    notiSharing.setText("Not Success,Please enter again");
                    notiSharing.setForeground(new Color(255, 51, 51));
                    notiSharing.setVisible(true);
                } else if (name.equals("Name") && check == true) {
                    titleItemName.setForeground(new Color(255, 51, 51));
                    notiSharing.setText("Not Success,Please enter again");
                    notiSharing.setForeground(new Color(255, 51, 51));
                    notiSharing.setVisible(true);
                } else if (check == false) {
                    notiSharing.setText("Not Success,Please enter again");
                    notiSharing.setForeground(new Color(255, 51, 51));
                    notiSharing.setVisible(true);
                    titleItemId.setForeground(new Color(255, 51, 51));
                } else {
                    sh.editItem(id, name, count);
                    notiSharing.setText("Success.");
                    notiSharing.setForeground(new Color(130, 255, 134));
                    notiSharing.setVisible(true);
                    itemId.setForeground(new Color(153, 153, 153, 150));
                    itemId.setText("ID");
                    itemName.setForeground(new Color(153, 153, 153, 150));
                    itemName.setText("Name");
                    itemAmount.setForeground(new Color(153, 153, 153, 150));
                    itemAmount.setText("Count");
                }
            } catch (NumberFormatException ne) {
                if (check == false && name.equals("Name")) {
                    titleItemId.setForeground(new Color(255, 51, 51));
                    titleItemName.setForeground(new Color(255, 51, 51));
                    titleItemCount.setForeground(new Color(255, 51, 51));
                    notiSharing.setText("Not Success,Please enter again");
                    notiSharing.setForeground(new Color(255, 51, 51));
                    notiSharing.setVisible(true);
                } else if (name.equals("Name") && check == true) {
                    titleItemName.setForeground(new Color(255, 51, 51));
                    titleItemCount.setForeground(new Color(255, 51, 51));
                    notiSharing.setText("Not Success,Please enter again");
                    notiSharing.setForeground(new Color(255, 51, 51));
                    notiSharing.setVisible(true);
                } else if (check == false) {
                    titleItemId.setForeground(new Color(255, 51, 51));
                    titleItemCount.setForeground(new Color(255, 51, 51));
                    notiSharing.setText("Not Success,Please enter again");
                    notiSharing.setForeground(new Color(255, 51, 51));
                    notiSharing.setVisible(true);
                } else {
                    titleItemCount.setForeground(new Color(255, 51, 51));
                    notiSharing.setText("Not Success,Please enter again");
                    notiSharing.setForeground(new Color(255, 51, 51));
                    notiSharing.setVisible(true);
                }
            }
            settingItem();
        } else if (backDelete.isVisible()) {
            boolean check = false;
            titleItemId.setForeground(new Color(255, 255, 255));
            String id = itemId.getText();
            String allId[] = sh.getItemID();
            for (int i = 0; i < allId.length; i++) {
                if (id.equals(allId[i])) {
                    check = true;
                }
            }
            if (check == true) {
                sh.deleteItem(id);
                itemId.setForeground(new Color(153, 153, 153, 150));
                itemId.setText("ID");
            } else if (check == false) {
                notiSharing.setText("Not Success,Please enter again");
                notiSharing.setForeground(new Color(255, 51, 51));
                notiSharing.setVisible(true);
                titleItemId.setForeground(new Color(255, 51, 51));
            }
            settingItem();
        }
    }//GEN-LAST:event_submitButSharingActionPerformed

    private void cancleButSharingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleButSharingMouseEntered
        cancleButSharing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleMiniClick.png")));
    }//GEN-LAST:event_cancleButSharingMouseEntered

    private void cancleButSharingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleButSharingMouseExited
        cancleButSharing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleMini.png")));
    }//GEN-LAST:event_cancleButSharingMouseExited

    private void cancleButSharingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleButSharingActionPerformed
        itemId.setForeground(new Color(153, 153, 153, 150));
        itemId.setText("ID");
        itemName.setForeground(new Color(153, 153, 153, 150));
        itemName.setText("Name");
        itemAmount.setForeground(new Color(153, 153, 153, 150));
        itemAmount.setText("Count");
        pathImgItem.setText("");
        titleItemName.setForeground(new Color(255, 255, 255));
        titleItemCount.setForeground(new Color(255, 255, 255));
        titleItemId.setForeground(new Color(255, 255, 255));
        titleItemPic.setForeground(new Color(255, 255, 255));
        notiSharing.setVisible(false);
        if (backAdd.isVisible()) {
            radioType.setSelected(false);
            radioEquip.setSelected(false);
            layerAddItem.setVisible(false);
        } else if (backEdit.isVisible()) {
            layerAddItem.setVisible(true);
        } else if (backEdit.isVisible()) {
            layerAddItem.setVisible(true);
        }
    }//GEN-LAST:event_cancleButSharingActionPerformed

    private void itemIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_itemIdFocusGained
        if (itemId.getText().equals("ID")) {
            itemId.setForeground(new Color(255, 255, 255));
            itemId.setText("");
        }
    }//GEN-LAST:event_itemIdFocusGained

    private void itemIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_itemIdFocusLost
        if (itemId.getText().equals("") || itemId.getText().length() == 0) {
            itemId.setForeground(new Color(153, 153, 153, 150));
            itemId.setText("ID");
        }
    }//GEN-LAST:event_itemIdFocusLost

    private void itemListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemListMouseEntered
        itemList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/itemListClick.png")));
    }//GEN-LAST:event_itemListMouseEntered

    private void itemListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemListMouseExited
        itemList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/itemList.png")));
    }//GEN-LAST:event_itemListMouseExited

    private void itemListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemListActionPerformed
        itemListScroll.setVisible(true);
        layerAddItem.setVisible(false);
        itemListScroll.getViewport().setViewPosition(new Point(0, 0));
        int y = 70;
        JPanel tmp = new JPanel();
        tmp.setBackground(new java.awt.Color(13, 24, 35));
        tmp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleItemList.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        titleItemList.setForeground(new java.awt.Color(255, 255, 255));
        titleItemList.setText("Item List");
        tmp.add(titleItemList, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 100, 60));
        String all[] = sh.selectAllAdmin();
        JPanel[] jp = new JPanel[all.length];
        JLabel[] detail = new JLabel[all.length];
        for (int i = 0; i < all.length; i++) {
            jp[i] = new JPanel();
            jp[i].setBackground(new Color(102, 102, 102));
            jp[i].setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            detail[i] = new JLabel();
            detail[i].setFont(new java.awt.Font("Leelawadee", 0, 18));
            detail[i].setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            detail[i].setText(all[i]);

            jp[i].add(detail[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 11, 580, 30));
            tmp.add(jp[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40, y, 600, 50));
            y += 70;
        }
        itemListScroll.setViewportView(tmp  );
    }//GEN-LAST:event_itemListActionPerformed

    private void closeButMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButMouseClicked
        itemListScroll.setVisible(false);
        layerAddItem.setVisible(true);
    }//GEN-LAST:event_closeButMouseClicked

    private void radioTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTypeActionPerformed
        layerAddItem.setVisible(true);
        radioEquip.setSelected(false);
        if (backAdd.isVisible()) {
            titleItemName.setForeground(new Color(255, 255, 255));
            titleItemCount.setForeground(new Color(255, 255, 255));
            titleItemPic.setForeground(new Color(255, 255, 255));
            itemId.setText(sh.selectItemIdBike());
            itemId.setEditable(false);
            itemId.setForeground(new Color(153, 153, 153, 150));
            notiSharing.setVisible(false);
            titleItemName.setVisible(true);
            itemName.setVisible(true);
            itemName.setText("Name");
            itemName.setForeground(new Color(153, 153, 153, 150));
            titleItemCount.setVisible(true);
            itemAmount.setVisible(true);
            itemAmount.setText("Count");
            itemAmount.setForeground(new Color(153, 153, 153, 150));
            pathImgItem.setText("");
            s7.setVisible(true);
            s8.setVisible(true);
            itemList.setVisible(false);
        }
    }//GEN-LAST:event_radioTypeActionPerformed

    private void radioEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioEquipActionPerformed
        layerAddItem.setVisible(true);
        radioType.setSelected(false);
        if (backAdd.isVisible()) {
            titleItemName.setForeground(new Color(255, 255, 255));
            titleItemCount.setForeground(new Color(255, 255, 255));
            titleItemPic.setForeground(new Color(255, 255, 255));
            itemId.setText(sh.selectItemIdEquip());
            itemId.setEditable(false);
            itemId.setForeground(new Color(153, 153, 153, 150));
            notiSharing.setVisible(false);
            titleItemName.setVisible(true);
            itemName.setVisible(true);
            itemName.setText("Name");
            itemName.setForeground(new Color(153, 153, 153, 150));
            titleItemCount.setVisible(true);
            itemAmount.setVisible(true);
            itemAmount.setText("Count");
            itemAmount.setForeground(new Color(153, 153, 153, 150));
            pathImgItem.setText("");
            s7.setVisible(true);
            s8.setVisible(true);
            itemList.setVisible(false);
        }
    }//GEN-LAST:event_radioEquipActionPerformed

    private void iconSearch1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconSearch1MouseClicked
        search1.requestFocusInWindow();
    }//GEN-LAST:event_iconSearch1MouseClicked

    private void search1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_search1FocusGained
        if (search1.getText().equals("Search")) {
            search1.setText("");
        }
    }//GEN-LAST:event_search1FocusGained

    private void search1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_search1FocusLost
        if (search1.getText().equals("") || search.getText().length() == 0) {
            search1.setText("Search");
        }
    }//GEN-LAST:event_search1FocusLost

    private void canCounter1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canCounter1MouseClicked
        news1.setForeground(new java.awt.Color(255,255,255));
        canCounter1.setForeground(new java.awt.Color(19, 175, 248));
        bikeRepair1.setForeground(new java.awt.Color(255, 255, 255));
        bikeSharing1.setForeground(new java.awt.Color(255, 255, 255));
        timer1.setForeground(new java.awt.Color(255, 255, 255));
        history1.setForeground(new java.awt.Color(255, 255, 255));
        support1.setForeground(new java.awt.Color(255, 255, 255));
        profile1.setForeground(new java.awt.Color(255, 255, 255));
        newsPage1.setVisible(false);
        cpPage1.setVisible(true);
        repairPage1.setVisible(false);
        sharingScroll.setVisible(false);
        sharingStep2.setVisible(false);
        timePageT.setVisible(false);
        timePageF.setVisible(false);
        historyPage1.setVisible(false);
        supportPage1.setVisible(false);
        userProfilePage1.setVisible(false);
        sh.getCp().selectCP();
        showCp.setText(sh.getCp().getCp() + "");
        (sh.getCp()).selectTrans();
        actionHis.setText(sh.getCp().getAction());
        poinsHis.setText(sh.getCp().getCount() + " Points");
    }//GEN-LAST:event_canCounter1MouseClicked

    private void bikeSharing1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bikeSharing1MouseClicked
        news1.setForeground(new java.awt.Color(255, 255, 255));
        canCounter1.setForeground(new java.awt.Color(255, 255, 255));
        bikeRepair1.setForeground(new java.awt.Color(255, 255, 255));
        bikeSharing1.setForeground(new java.awt.Color(19, 175, 248));
        timer1.setForeground(new java.awt.Color(255, 255, 255));
        history1.setForeground(new java.awt.Color(255, 255, 255));
        support1.setForeground(new java.awt.Color(255, 255, 255));
        profile1.setForeground(new java.awt.Color(255, 255, 255));
        newsPage1.setVisible(false);
        cpPage1.setVisible(false);
        repairPage1.setVisible(false);
        timePageT.setVisible(false);
        timePageF.setVisible(false);
        historyPage1.setVisible(false);
        supportPage1.setVisible(false);
        userProfilePage1.setVisible(false);
        sharingScroll.setVisible(true);
        settingItem();
        sh.editStep();
        setBorrowStep1();
        sharingStep2.setVisible(false);
        sh.getCp().selectCP();
        isCp.setText("Your CP : " + sh.getCp().getCp() + " Points");
        int countHis = sh.countHis();
        if (countHis != 0) {
            boolean check = sh.checkStatus();
            if (check == false) {
                boolean timesup = sh.isTimesUp();
                if (timesup == true) {
                    JOptionPane.showMessageDialog(this, "Please return Items before borrow.", "Warning", JOptionPane.OK_OPTION);
                    timer1.setForeground(new java.awt.Color(19, 175, 248));
                    bikeSharing1.setForeground(new java.awt.Color(255, 255, 255));
                    canCounter1.setForeground(new java.awt.Color(255, 255, 255));
                    profile1.setForeground(new java.awt.Color(255, 255, 255));
                    cpPage1.setVisible(false);
                    timePageT.setVisible(false);
                    timePageF.setVisible(false);
                    sharingScroll.setVisible(false);
                    sharingStep2.setVisible(false);
                    userProfilePage1.setVisible(false);
                    timePageT.setVisible(true);
                    timeupPage.setVisible(true);
                    timewatch.setVisible(false);
                    itemListShow.setVisible(false);
                }
            }
        }
    }//GEN-LAST:event_bikeSharing1MouseClicked

    private void timer1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timer1MouseClicked
        timer1.setForeground(new java.awt.Color(19, 175, 248));
        news1.setForeground(new java.awt.Color(255, 255, 255));
        canCounter1.setForeground(new java.awt.Color(255, 255, 255));
        bikeRepair1.setForeground(new java.awt.Color(255, 255, 255));
        bikeSharing1.setForeground(new java.awt.Color(255, 255, 255));
        history1.setForeground(new java.awt.Color(255, 255, 255));
        support1.setForeground(new java.awt.Color(255, 255, 255));
        profile1.setForeground(new java.awt.Color(255, 255, 255));
        newsPage1.setVisible(false);
        cpPage1.setVisible(false);
        repairPage1.setVisible(false);
        timePageT.setVisible(false);
        timePageF.setVisible(false);
        sharingScroll.setVisible(false);
        sharingStep1.setVisible(false);
        sharingStep2.setVisible(false);
        historyPage1.setVisible(false);
        supportPage1.setVisible(false);
        userProfilePage1.setVisible(false);
        int num = sh.countHis();
        boolean status = sh.checkStatus();
        if (status == false) {
            boolean timesup = sh.isTimesUp();
            timePageT.setVisible(true);
            timePageF.setVisible(false);
            if (timesup == true) {
                //โชว์ของยังไม่คืน
                timeupPage.setVisible(true);
                listTimeup.setText("<html><body>" + sh.itemMustReturn() + "</body></html>");
                timewatch.setVisible(false);
                itemListShow.setVisible(false);
                timeupPage.setVisible(true);
            } else if (timesup == false) {
                //โชว์ 2.ยิมแล้ว เวลา + รายการของ
                circleMini.setVisible(false);
                if(circleMini1.isVisible() == true){
                    circleNoti.setVisible(true);
                }else{
                    circleNoti.setVisible(false);
                }
                timeupPage.setVisible(false);
                Date current = new Date();
                timePageT.setVisible(true);
                timewatch.setVisible(true);
                itemListShow.setVisible(true);
                try{
                    sh.timeAdmin(current);
                    endTime.setText("End:    " + current.getDate() + " / " + (current.getMonth() + 1) + " / " + (current.getYear() + 1900) + "  18 : 00");
                    timeLeftShow.setText(sh.getTimeDetail());
                    new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            endTime.setText("End:    " + current.getDate() + " / " + (current.getMonth() + 1) + " / " + (current.getYear() + 1900) + "  18 : 00");
                            timeLeftShow.setText(sh.getTimeDetail());
                        }
                    }).start();
                    listShow.setText("<html><body>" + sh.itemMustReturn() + "</body></html>");
                }catch(InterruptedException ie){
                    System.out.println(ie);
                }
            }
        } else if (status == true) {
            circleMini.setVisible(false);
            circleNoti.setVisible(false);
            timePageT.setVisible(false);
            timePageF.setVisible(true);
            timewatch.setVisible(false);
            if (num == 0) {
                textNotHis.setVisible(true);
                iconNotHis.setVisible(true);
            } else {
                int y = 50;
                String his[] = sh.showHis();
                JPanel[] jp = new JPanel[num];
                JLabel[] detailBorrow = new JLabel[num];
                JLabel[] icon = new JLabel[num];
                for (int i = 0; i < num; i++) {
                    jp[i] = new JPanel();
                    jp[i].setBackground(new Color(102, 102, 102));
                    jp[i].setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
                    detailBorrow[i] = new JLabel();
                    detailBorrow[i].setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
                    detailBorrow[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    detailBorrow[i].setText(his[i]);
                    jp[i].add(detailBorrow[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 490, 50));
                    icon[i] = new JLabel();
                    icon[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    icon[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/clock.png"))); // NOI18N
                    jp[i].add(icon[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 50, 50));
                    timePageF.add(jp[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(50, y, 660, 50));
                    y += 70;
                }
            }
        }
    }//GEN-LAST:event_timer1MouseClicked

    private void signout1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signout1MouseEntered
        titleSignout1.setVisible(true);
    }//GEN-LAST:event_signout1MouseEntered

    private void signout1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signout1MouseExited
        titleSignout1.setVisible(false);
    }//GEN-LAST:event_signout1MouseExited

    private void signout1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signout1ActionPerformed
        int ans = JOptionPane.showConfirmDialog(this, "Do you want to Sign out ?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (ans == JOptionPane.YES_OPTION) {
            User.setUserId(0);
            User.setOfficeId(null);
            User.setFirstName(null);
            User.setLastName(null);
            User.setGender(null);
            User.setBirthDate(null);
            User.setCongenitialDisease(null);
            User.setEmail(null);
            User.setTel(null);
            User.setDept(null);
            User.setDeptId(null);
            User.setPositon(null);
            User.setImgPath(null);
            login.setVisible(true);
            mainAdmin.setVisible(false);
            mainUser.setVisible(false);
            password.setText("");
            showPass.setVisible(true);
            email.setText("Email");
            email.setForeground(new Color(153, 153, 153));
            warning.setVisible(false);
            mainAdminSetVisible();
            mainUserSetVisible();
        }
    }//GEN-LAST:event_signout1ActionPerformed

    private void backStep1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backStep1MouseEntered
        backStep1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/left arrow click.png")));
    }//GEN-LAST:event_backStep1MouseEntered

    private void backStep1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backStep1MouseExited
        backStep1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/left arrow.png")));
    }//GEN-LAST:event_backStep1MouseExited

    private void backStep1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backStep1ActionPerformed
        sh.editStep();
        detailData.setText("");
        sharingScroll.setVisible(true);
        sharingStep2.setVisible(false);
    }//GEN-LAST:event_backStep1ActionPerformed

    private void cancleButMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleButMouseEntered
        cancleBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleClick.png")));
        cancleBut.getToolTipText();
    }//GEN-LAST:event_cancleButMouseEntered

    private void cancleButMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleButMouseExited
        cancleBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancle.png")));
    }//GEN-LAST:event_cancleButMouseExited

    private void cancleButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleButActionPerformed
        sh.editStep();
        settingItem();
        sharingScroll.setVisible(true);
        setBorrowStep1();
        sharingStep2.setVisible(false);
        for (int i = 0; i < countBorrow.length; i++) {
            countBorrow[i] = 0;
        }
    }//GEN-LAST:event_cancleButActionPerformed

    private void borrowButMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrowButMouseEntered
        borrowBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/BorrowButClick.png")));
    }//GEN-LAST:event_borrowButMouseEntered

    private void borrowButMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrowButMouseExited
        borrowBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/BorrowBut.png")));
    }//GEN-LAST:event_borrowButMouseExited

    private void borrowButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowButActionPerformed
        int ans = JOptionPane.showConfirmDialog(this, "Do you want to Borrow?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (ans == JOptionPane.YES_OPTION) {
            backStep1.setEnabled(false);
            try {
                sh.startBorrow();
                settingItem();
                sharingScroll.setVisible(true);
                setBorrowStep1();
                sharingStep2.setVisible(false);
                for (int i = 0; i < countBorrow.length; i++) {
                    countBorrow[i] = 0;
                }
                sh.getCp().selectCP();
                isCp.setText("Your CP : " + sh.getCp().getCp() + " Points");
            } catch (InterruptedException ie) {
                JOptionPane.showMessageDialog(this, ie.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_borrowButActionPerformed

    private void backSigninMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backSigninMouseClicked
        login.setVisible(true);
        forgotPassPage.setVisible(false);
        
        email.setText("Email");
        password.setText("");
        showPass.setVisible(true);
    }//GEN-LAST:event_backSigninMouseClicked

    private void forgotPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgotPassMouseClicked
        forgotPassPage.setVisible(true);
        login.setVisible(false);
    }//GEN-LAST:event_forgotPassMouseClicked

    private void forgotEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_forgotEmailFocusGained
        if (forgotEmail.getText().equals("Email")) {
            forgotEmail.setText("");
            forgotEmail.setForeground(new java.awt.Color(255, 255, 255));
        }
    }//GEN-LAST:event_forgotEmailFocusGained

    private void forgotEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_forgotEmailFocusLost
        if (forgotEmail.getText().equals("") || forgotEmail.getText().length() == 0) {
            forgotEmail.setText("Email");
            forgotEmail.setForeground(new java.awt.Color(153, 153, 153));
        }
    }//GEN-LAST:event_forgotEmailFocusLost

    private void profile1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profile1MouseClicked
        news1.setForeground(new java.awt.Color(255, 255, 255));
        canCounter1.setForeground(new java.awt.Color(255, 255, 255));
        bikeSharing1.setForeground(new java.awt.Color(255, 255, 255));
        bikeRepair1.setForeground(new java.awt.Color(255, 255, 255));
        timer1.setForeground(new java.awt.Color(255, 255, 255));
        history1.setForeground(new java.awt.Color(255, 255, 255));
        support1.setForeground(new java.awt.Color(255, 255, 255));
        profile1.setForeground(new java.awt.Color(19, 175, 248));
        newsPage1.setVisible(false);
        cpPage1.setVisible(false);
        repairPage1.setVisible(false);
        timePageT.setVisible(false);
        timePageF.setVisible(false);
        sharingScroll.setVisible(false);
        sharingStep2.setVisible(false);
        userProfilePage1.setVisible(true);
        historyPage1.setVisible(false);
        supportPage1.setVisible(false);
        chooseImgProfileBut1.setVisible(false);
        pathImgProfile1.setVisible(false);
        submitProfile1.setVisible(false);
        warningProfile1.setVisible(false);
        setDataUserProfile();
        if (genderProfile1.getText().equalsIgnoreCase("f")) {
            genderProfile1.setText("Female");
        } else if (genderProfile1.getText().equalsIgnoreCase("m")) {
            genderProfile1.setText("Male");
        }

        imageProfile1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(User.getImgPath())).getImage().getScaledInstance(200, 170, Image.SCALE_DEFAULT)));
    }//GEN-LAST:event_profile1MouseClicked

    private void editProfileBut1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editProfileBut1MouseEntered
        editProfileBut1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/editProfileClick.png")));
    }//GEN-LAST:event_editProfileBut1MouseEntered

    private void editProfileBut1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editProfileBut1MouseExited
        editProfileBut1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/editProfile.png")));
    }//GEN-LAST:event_editProfileBut1MouseExited

    private void editProfileBut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editProfileBut1ActionPerformed
        chooseImgProfileBut1.setVisible(true);
        pathImgProfile1.setVisible(true);
        submitProfile1.setVisible(true);
        nameProfile1.setEditable(true);
        nameProfile1.setBackground(Color.white);
        genderProfile1.setEditable(true);
        genderProfile1.setBackground(Color.white);
        birthProfile1.setEditable(true);
        birthProfile1.setBackground(Color.white);
        congenProfile1.setEditable(true);
        congenProfile1.setBackground(Color.white);
        emailProfile1.setEditable(true);
        emailProfile1.setBackground(Color.white);
        telProfile1.setEditable(true);
        telProfile1.setBackground(Color.white);
        warningProfile1.setVisible(false);
    }//GEN-LAST:event_editProfileBut1ActionPerformed

    private void submitProfile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitProfile1ActionPerformed
        int i = nameProfile1.getText().indexOf(" ");
        warningProfile1.setVisible(false);
        if (i == -1) {
            warningProfile1.setVisible(true);
        } else {
            if (pathImgProfile1.getText().equals("No Select File.")) {
                pathImgProfile1.setText(User.getImgPath());
            }
            if (genderProfile1.getText().equalsIgnoreCase("female")) {
                genderProfile1.setText("F");
            } else if (genderProfile1.getText().equalsIgnoreCase("male")) {
                genderProfile1.setText("M");
            }
            ac.updateProfile(nameProfile1.getText(), genderProfile1.getText(), birthProfile1.getText(), congenProfile1.getText(), emailProfile1.getText(), telProfile1.getText(), pathImgProfile1.getText());
            chooseImgProfileBut1.setVisible(false);
            pathImgProfile1.setVisible(false);
            submitProfile1.setVisible(false);
            nameProfile1.setEditable(false);
            genderProfile1.setEditable(false);
            birthProfile1.setEditable(false);
            congenProfile1.setEditable(false);
            emailProfile1.setEditable(false);
            telProfile1.setEditable(false);
            warningProfile1.setVisible(false);
            nameProfile1.setBackground(new Color(25, 41, 65));
            genderProfile1.setBackground(new Color(25, 41, 65));
            birthProfile1.setBackground(new Color(25, 41, 65));
            congenProfile1.setBackground(new Color(25, 41, 65));
            emailProfile1.setBackground(new Color(25, 41, 65));
            telProfile1.setBackground(new Color(25, 41, 65));
            pathImgProfile1.setText("No Select File.");
            setDataUserProfile();

        }
    }//GEN-LAST:event_submitProfile1ActionPerformed

    private void chooseImgProfileBut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseImgProfileBut1ActionPerformed
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop"));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.toString();
            pathImgProfile1.setText(path);
        } else if (result == JFileChooser.CANCEL_OPTION) {
            pathImgProfile1.setText("No Select File.");
        }
    }//GEN-LAST:event_chooseImgProfileBut1ActionPerformed

    private void submitProfile1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitProfile1MouseEntered
        submitProfile1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMiniClick.png")));
    }//GEN-LAST:event_submitProfile1MouseEntered

    private void submitProfile1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitProfile1MouseExited
        submitProfile1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMini.png")));
    }//GEN-LAST:event_submitProfile1MouseExited

    private void editProfileButMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editProfileButMouseEntered
        editProfileBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/editProfileClick.png")));
    }//GEN-LAST:event_editProfileButMouseEntered

    private void editProfileButMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editProfileButMouseExited
        editProfileBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/editProfile.png")));
    }//GEN-LAST:event_editProfileButMouseExited

    private void editProfileButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editProfileButActionPerformed
        chooseImgProfileBut.setVisible(true);
        pathImgProfile.setVisible(true);
        submitProfile.setVisible(true);
        nameProfile.setEditable(true);
        nameProfile.setBackground(Color.white);
        genderProfile.setEditable(true);
        genderProfile.setBackground(Color.white);
        birthProfile.setEditable(true);
        birthProfile.setBackground(Color.white);
        congenProfile.setEditable(true);
        congenProfile.setBackground(Color.white);
        emailProfile.setEditable(true);
        emailProfile.setBackground(Color.white);
        telProfile.setEditable(true);
        telProfile.setBackground(Color.white);
        warningProfile.setVisible(false);
    }//GEN-LAST:event_editProfileButActionPerformed

    private void chooseImgProfileButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseImgProfileButActionPerformed
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop"));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.toString();
            pathImgProfile.setText(path);
        } else if (result == JFileChooser.CANCEL_OPTION) {
            pathImgProfile.setText("No Select File.");
        }
    }//GEN-LAST:event_chooseImgProfileButActionPerformed

    private void submitProfileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitProfileMouseEntered
        submitProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMiniClick.png")));
    }//GEN-LAST:event_submitProfileMouseEntered

    private void submitProfileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitProfileMouseExited
        submitProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMini.png")));
    }//GEN-LAST:event_submitProfileMouseExited

    private void submitProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitProfileActionPerformed
        int i = nameProfile.getText().indexOf(" ");
        warningProfile.setVisible(false);
        if (i == -1) {
            warningProfile.setVisible(true);
        } else {
            if (pathImgProfile.getText().equals("No Select File.")) {
                pathImgProfile.setText(User.getImgPath());
            }
            if (genderProfile.getText().equalsIgnoreCase("female")) {
                genderProfile.setText("F");
            } else if (genderProfile.getText().equalsIgnoreCase("male")) {
                genderProfile.setText("M");
            }
            ac.updateProfile(nameProfile.getText(), genderProfile.getText(), birthProfile.getText(), congenProfile.getText(), emailProfile.getText(), telProfile.getText(), pathImgProfile.getText());
            chooseImgProfileBut.setVisible(false);
            pathImgProfile.setVisible(false);
            submitProfile.setVisible(false);
            nameProfile.setEditable(false);
            genderProfile.setEditable(false);
            birthProfile.setEditable(false);
            congenProfile.setEditable(false);
            emailProfile.setEditable(false);
            telProfile.setEditable(false);
            warningProfile.setVisible(false);
            nameProfile.setBackground(new Color(25, 41, 65));
            genderProfile.setBackground(new Color(25, 41, 65));
            birthProfile.setBackground(new Color(25, 41, 65));
            congenProfile.setBackground(new Color(25, 41, 65));
            emailProfile.setBackground(new Color(25, 41, 65));
            telProfile.setBackground(new Color(25, 41, 65));
            pathImgProfile.setText("No Select File.");
            setDataUserProfile();

        }
    }//GEN-LAST:event_submitProfileActionPerformed

    private void profileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileMouseClicked
        news.setForeground(new java.awt.Color(255, 255, 255));
        canCounter.setForeground(new java.awt.Color(255, 255, 255));
        bikeRepair.setForeground(new java.awt.Color(255, 255, 255));
        bikeSharing.setForeground(new java.awt.Color(255, 255, 255));
        timer.setForeground(new java.awt.Color(255, 255, 255));
        history.setForeground(new java.awt.Color(255, 255, 255));
        support.setForeground(new java.awt.Color(255, 255, 255));
        profile.setForeground(new java.awt.Color(19, 175, 248));
        newsPage.setVisible(false);
        cpPage.setVisible(false);
        repairPage.setVisible(false);
        sharingPage.setVisible(false);
        timerPage.setVisible(false);
        historyPage.setVisible(false);
        supportPage.setVisible(false);
        userProfilePage.setVisible(true);

        chooseImgProfileBut.setVisible(false);
        pathImgProfile.setVisible(false);
        submitProfile.setVisible(false);
        warningProfile.setVisible(false);
        setDataUserProfile();
        if (genderProfile.getText().equalsIgnoreCase("f")) {
            genderProfile.setText("Female");
        } else if (genderProfile.getText().equalsIgnoreCase("m")) {
            genderProfile.setText("Male");
        }
        imageProfile.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(User.getImgPath())).getImage().getScaledInstance(200, 170, Image.SCALE_DEFAULT)));
    }//GEN-LAST:event_profileMouseClicked

    private void nextStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextStepActionPerformed
        Date dt = new Date();
        try {
            insertBorrow();
            sh.borrowStep();
            sh.setPointUse();
            sh.getCp().selectCP();
            (sh.getCp()).checkCp();
            sh.enterTime(dt.getDate(), dt.getMonth() + 1, dt.getYear() + 1900, 18, 0, 0);
            sharingScroll.setVisible(false);
            sharingStep2.setVisible(true);
            setBorrowDetail();
            int point = (sh.getCp()).getCp();
            cpUser.setText(point + "");
            int pointUse = (sh.getCp()).getCpUse();
            cpUse.setText(pointUse + "");
            pointRemain.setText((point - pointUse) + "");
            borrowBut.setEnabled(true);
        } catch (CanCountException cce) {
            JOptionPane.showMessageDialog(this, cce.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SelectItemException s) {
            JOptionPane.showMessageDialog(this, s.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_nextStepActionPerformed

    private void nextStepMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextStepMouseExited
        nextStep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/right arrow.png")));
    }//GEN-LAST:event_nextStepMouseExited

    private void nextStepMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextStepMouseEntered
        nextStep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/right arrow click.png")));
    }//GEN-LAST:event_nextStepMouseEntered

    private void backSigninRegisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backSigninRegisMouseClicked
        login.setVisible(true);
        registerPage.setVisible(false);
    }//GEN-LAST:event_backSigninRegisMouseClicked

    private void jTfFirstNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTfFirstNameFocusGained
        if (jTfFirstName.getText().equals("John")) {
            jTfFirstName.setText("");
            jTfFirstName.setForeground(new java.awt.Color(255, 255, 255));
        }
        jTfFirstName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(19, 175, 248)));
    }//GEN-LAST:event_jTfFirstNameFocusGained

    private void jTfFirstNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTfFirstNameFocusLost
        if (jTfFirstName.getText().equals("") || jTfFirstName.getText().length() == 0) {
            jTfFirstName.setForeground(new Color(153, 153, 153, 150));
            jTfFirstName.setText("John");
        }
        jTfFirstName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_jTfFirstNameFocusLost

    private void jTextFieldSurnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSurnameFocusGained
        if (jTextFieldSurname.getText().equals("Wick")) {
            jTextFieldSurname.setText("");
            jTextFieldSurname.setForeground(new java.awt.Color(255, 255, 255));
        }
        jTextFieldSurname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(19, 175, 248)));
    }//GEN-LAST:event_jTextFieldSurnameFocusGained

    private void jTextFieldSurnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSurnameFocusLost
        if (jTextFieldSurname.getText().equals("") || jTfFirstName.getText().length() == 0) {
            jTextFieldSurname.setForeground(new Color(153, 153, 153, 150));
            jTextFieldSurname.setText("Wick");
        }
        jTextFieldSurname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_jTextFieldSurnameFocusLost

    private void jTextFieldEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldEmailFocusGained
        if (jTextFieldEmail.getText().equals("example@gmail.com")) {
            jTextFieldEmail.setText("");
            jTextFieldEmail.setForeground(new java.awt.Color(255, 255, 255));
        }
        jTextFieldEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(19, 175, 248)));
    }//GEN-LAST:event_jTextFieldEmailFocusGained

    private void jTextFieldEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldEmailFocusLost
        if (jTextFieldEmail.getText().equals("") || jTfFirstName.getText().length() == 0) {
            jTextFieldEmail.setForeground(new Color(153, 153, 153, 150));
            jTextFieldEmail.setText("example@gmail.com");
        }
        jTextFieldEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_jTextFieldEmailFocusLost

    private void jTextFieldConDiseaseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldConDiseaseFocusGained
        if (jTextFieldConDisease.getText().equals("If you have")) {
            jTextFieldConDisease.setText("");
            jTextFieldConDisease.setForeground(new java.awt.Color(255, 255, 255));
        }
        jTextFieldConDisease.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(19, 175, 248)));
    }//GEN-LAST:event_jTextFieldConDiseaseFocusGained

    private void jTextFieldConDiseaseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldConDiseaseFocusLost
        if (jTextFieldConDisease.getText().equals("") || jTfFirstName.getText().length() == 0) {
            jTextFieldConDisease.setForeground(new Color(153, 153, 153, 150));
            jTextFieldConDisease.setText("If you have");
        }
        jTextFieldConDisease.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_jTextFieldConDiseaseFocusLost

    private void jPasswordSignUpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordSignUpFocusGained
        jPasswordSignUp.setText("");
        jPasswordSignUp.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordSignUp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(19, 175, 248)));
    }//GEN-LAST:event_jPasswordSignUpFocusGained

    private void jPasswordSignUpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordSignUpFocusLost
        jPasswordSignUp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_jPasswordSignUpFocusLost

    private void jPasswordConfirmPassSignupFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordConfirmPassSignupFocusGained
        jPasswordConfirmPassSignup.setText("");
        jPasswordConfirmPassSignup.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordConfirmPassSignup.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(19, 175, 248)));
    }//GEN-LAST:event_jPasswordConfirmPassSignupFocusGained

    private void jPasswordConfirmPassSignupFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordConfirmPassSignupFocusLost
        jPasswordConfirmPassSignup.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_jPasswordConfirmPassSignupFocusLost

    private void jButtonSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSignUpActionPerformed
        String name = null;
        String surname = null;
        String gender = null;
        Date birthDate = null;
        String conDisease = null;
        String email = null;
        String tel = null;
        String deptID = null;
        String oldPass = null;
        String newPass = null;
        StringBuffer sb = null;
        String position = null;
        String telephone = null;
        String picture = null;
        jLabelNoticName.setText("");
        jLabelNoticSurname.setText("");
        jLabelNoticGender.setText("");
        jLabelNoticBirthDate.setText("");
        jLabelNoticNewPass.setText("");
        jLabelNoticOldPass.setText("");
        jLabelNoticPosition.setText("");
        jLabelNoticTel.setText("");
        jLabelEmailNotCorrect.setText("");
        jLabelifLengthId.setText("");
        //jTfFirstName----------------------------------------------------------
        boolean checkName = jTfFirstName.getText().equals("John") == false;
        if (checkName) {
            name = jTfFirstName.getText();
        } else {
            jLabelNoticName.setText("Name is empty!");
        }

        //jTfSurName----------------------------------------------------------
        boolean checkSurname = jTextFieldSurname.getText().equals("Wick") == false;
        if (checkSurname) {
            surname = jTextFieldSurname.getText();
        } else {
            jLabelNoticSurname.setText("Surname is empty!");
        }

        //jRadio Gender---------------------------------------------------------
        boolean checkGender = jRadioButtonGenderMale.getActionCommand().equals("1")
                || jRadioButtonGenderFemale.getActionCommand().equals("0");
        if (jRadioButtonGenderMale.getActionCommand().equals("1")) {
            gender = "M";
        } else if (jRadioButtonGenderFemale.getActionCommand().equals("0")) {
            gender = "F";
        } else {
            jLabelNoticGender.setText("Gender is empty!");
        }

        //jDateChooserBirthDate-------------------------------------------------
        if (jDateChooserBirthDate.getDate() != null) {
            birthDate = jDateChooserBirthDate.getDate();
        } else {
            jLabelNoticBirthDate.setText("Birthdate is empty!");
        }
        boolean checkBirthDate = jDateChooserBirthDate.getDate() != null;

        //jComboBoxPosition-----------------------------------------------------
        if (jComboBoxPosition.getSelectedIndex() == 0) {
            position = "-";
        } else if (jComboBoxPosition.getSelectedIndex() == 1) {
            position = "Student";
        } else if (jComboBoxPosition.getSelectedIndex() == 2) {
            position = "Professor";
        } else if (jComboBoxPosition.getSelectedIndex() == 3) {
            position = "Technician";
        } else if (jComboBoxPosition.getSelectedIndex() == 4) {
            position = "Officer";
        } else {
            jLabelNoticPosition.setText("Position is empty!");
        }

        //jFormatTextFieldForId-----------------------------------------------------
        boolean checkIdTextField = jFormatTextFieldForId.getValue() != null;
        int temp=0;
        
        if (checkIdTextField) {
            deptID = jFormatTextFieldForId.getValue().toString();
            temp = deptID.length();
            if (temp > 11 || temp < 11) {
                jLabelifLengthId.setText("Error incorrect");
            }
        } else {
            jLabelifLengthId.setText("ID is empty!");
        }
        checkIdTextField = jFormatTextFieldForId.getValue() != null && temp==11;

        //jLbCongenitialDisease-----------------------------------------------------
        if (jTextFieldConDisease.getText().equals("If you have") == false) {
            conDisease = jTextFieldConDisease.getText();
        } else {
            conDisease = null;
        }

//        jTextFieldTelophone----------------------------------------------------------
        boolean checkTelNumber = jFormattedTelophone.getValue() != null;
        int lengthFmTel=0;
        if (checkTelNumber) {
            telephone = "0"+jFormattedTelophone.getValue().toString();
            lengthFmTel = telephone.length();
            if (lengthFmTel!=10) {
                jLabelNoticTel.setText("Error incorrect");
            }
        } else {
            jLabelNoticTel.setText("Telophone number is empty!");
        }
        
        checkTelNumber = jFormattedTelophone.getValue() != null && lengthFmTel==10;

//        jTextFieldEmail-------------------------------------------------------
        int checkEmailIndex = jTextFieldEmail.getText().indexOf("@");
        boolean checkEmailTextField = jTextFieldEmail.getText().equals("example@gmail.com") == false && checkEmailIndex!=-1;
        
        if (checkEmailTextField) {
            email = jTextFieldEmail.getText();
        }else if(checkEmailIndex==-1){
            jLabelEmailNotCorrect.setText("Error incorrect!");
        } else {
            jLabelEmailNotCorrect.setText("Email is empty!");
        }

//        jPasswordSignUp-------------------------------------------------------
        if (jPasswordSignUp.getText().equals("") == false
                && jPasswordConfirmPassSignup.getText().equals("") == false) {
            oldPass = jPasswordSignUp.getText();
            newPass = jPasswordConfirmPassSignup.getText();
            String password = password(oldPass, newPass);
            sb = rs.encocdMd5(password);
        } else {
            jLabelNoticOldPass.setText("Password is empty!");
        }

        boolean checkEmail = rs.connectDBforCheckEmail(email);
        
        if (jLabelPartPictureUserUpload.getText().equals("Not Select File")) {
            picture = "default";
        }else{
            picture = jLabelPartPictureUserUpload.getText();
        }

        if ((checkName) && (checkSurname) && (checkGender)
                && (checkBirthDate) && (checkEmailTextField) && (checkTelNumber)
                && (checkIdTextField) && (sb != null)) {
            if (checkEmail == true) {
                ac.insertDataUser(name, surname, gender, birthDate, conDisease, email,
                        telephone, deptID, sb, position, picture);
                login.setVisible(true);
                registerPage.setVisible(false);
            } else if (checkEmail == false) {
                jLabelEmailNotCorrect.setText("This email is already registered");
            }
        }
    }//GEN-LAST:event_jButtonSignUpActionPerformed

    private void jRadioButtonGenderFemaleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jRadioButtonGenderFemaleFocusGained
        jRadioButtonGenderFemale.setActionCommand("0");
    }//GEN-LAST:event_jRadioButtonGenderFemaleFocusGained

    private void jRadioButtonGenderMaleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jRadioButtonGenderMaleFocusGained
        jRadioButtonGenderMale.setActionCommand("1");
    }//GEN-LAST:event_jRadioButtonGenderMaleFocusGained

    private void jButtonChooseFileForUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChooseFileForUploadActionPerformed
        JFileChooser file = new JFileChooser();
        String userhome = System.getProperty("user.home");
        file.setCurrentDirectory(new File(userhome));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            jLabelPartPictureUserUpload.setText(path);
        } else if (result == JFileChooser.CANCEL_OPTION) {
            jLabelPartPictureUserUpload.setText("Not File Select");
        }
    }//GEN-LAST:event_jButtonChooseFileForUploadActionPerformed

    private void menuSignupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSignupMouseClicked
        registerPage.setVisible(true);
        jDateChooserBirthDate.getDateEditor().setEnabled(false);
        jTfFirstName.setText("John");
        jTfFirstName.setForeground(new Color(153, 153, 153, 150));
        jTextFieldSurname.setText("Wick");
        jTextFieldSurname.setForeground(new Color(153, 153, 153, 150));
        jTextFieldEmail.setText("example@gmail.com");
        jTextFieldEmail.setForeground(new Color(153, 153, 153, 150));
        jPasswordSignUp.setText("");
        jPasswordConfirmPassSignup.setText("");
        jFormattedTelophone.setText("");
        jFormatTextFieldForId.setText("");
        jComboBoxPosition.setSelectedIndex(0);
        jDateChooserBirthDate.setCalendar(null);
        jLabelPartPictureUserUpload.setText("Not Select File");
        login.setVisible(false);
    }//GEN-LAST:event_menuSignupMouseClicked

    private void jFormatTextFieldForIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormatTextFieldForIdFocusGained
        jFormatTextFieldForId.setForeground(new java.awt.Color(255, 255, 255));
        jFormatTextFieldForId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(19, 175, 248)));
    }//GEN-LAST:event_jFormatTextFieldForIdFocusGained

    private void jFormatTextFieldForIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormatTextFieldForIdFocusLost
        jFormatTextFieldForId.setForeground(new Color(153, 153, 153, 150));
        jFormatTextFieldForId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_jFormatTextFieldForIdFocusLost

    private void support1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_support1MouseClicked
        news1.setForeground(new java.awt.Color(255, 255, 255));
        canCounter1.setForeground(new java.awt.Color(255, 255, 255));
        bikeRepair1.setForeground(new java.awt.Color(255, 255, 255));
        bikeSharing1.setForeground(new java.awt.Color(255, 255, 255));
        timer1.setForeground(new java.awt.Color(255, 255, 255));
        history1.setForeground(new java.awt.Color(255, 255, 255));
        support1.setForeground(new java.awt.Color(19, 175, 248));
        profile1.setForeground(new java.awt.Color(255, 255, 255));
        newsPage1.setVisible(false);
        cpPage1.setVisible(false);
        repairPage1.setVisible(false);
        sharingScroll.setVisible(false);
        sharingStep2.setVisible(false);
        timePageT.setVisible(false);
        timePageF.setVisible(false);
        historyPage1.setVisible(false);
        supportPage1.setVisible(true);
        userProfilePage1.setVisible(false);
        jTAContact1.setText(sp.contact());
        jTAShowyouSearch1.setText("");
    }//GEN-LAST:event_support1MouseClicked

    private void search1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search1KeyPressed
        jTAShowyouSearch1.setText("");
        jTAShowyouSearch1.setLineWrap(true);
        jTAContact1.setText(sp.contact());
        sp.setOutput("");
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String whatToSearch = search1.getText();
            sp.searchSupport(whatToSearch);
            jLBWhatsearch1.setText(whatToSearch);
            jTAShowyouSearch1.setText(sp.getOutput());
            news1.setForeground(new java.awt.Color(255, 255, 255));
            canCounter1.setForeground(new java.awt.Color(255, 255, 255));
            bikeRepair1.setForeground(new java.awt.Color(255, 255, 255));
            bikeSharing1.setForeground(new java.awt.Color(255, 255, 255));
            timer1.setForeground(new java.awt.Color(255, 255, 255));
            history1.setForeground(new java.awt.Color(255, 255, 255));
            support1.setForeground(new java.awt.Color(19, 175, 248));
            profile1.setForeground(new java.awt.Color(255, 255, 255));
            news.setForeground(new java.awt.Color(255, 255, 255));
            canCounter.setForeground(new java.awt.Color(255, 255, 255));
            bikeRepair.setForeground(new java.awt.Color(255, 255, 255));
            bikeSharing.setForeground(new java.awt.Color(255, 255, 255));
            timer.setForeground(new java.awt.Color(255, 255, 255));
            history.setForeground(new java.awt.Color(255, 255, 255));
            support.setForeground(new java.awt.Color(19, 175, 248));
            profile.setForeground(new java.awt.Color(255, 255, 255));
            
            newsPage1.setVisible(false);
            cpPage1.setVisible(false);
            repairPage1.setVisible(false);
            historyPage1.setVisible(false);
            cpPage1.setVisible(false);
            sharingScroll.setVisible(false);
            sharingStep2.setVisible(false);
            timePageT.setVisible(false);
            timePageF.setVisible(false);
            userProfilePage1.setVisible(false);
            supportPage1.setVisible(true);
            bodySupport1.setVisible(true);
            search1.setText("Search");
        }
    }//GEN-LAST:event_search1KeyPressed

    private void searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyPressed
        jTAShowyouSearch.setLineWrap(true);
        jTAContact.setText(sp.contact());
        sp.setOutput("");
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String whatToSearch = search.getText();
            sp.searchSupport(whatToSearch);
            jLBWhatsearch.setText(whatToSearch);
            jTAShowyouSearch.setText(sp.getOutput());

            news.setForeground(new java.awt.Color(255, 255, 255));
            canCounter.setForeground(new java.awt.Color(255, 255, 255));
            bikeRepair.setForeground(new java.awt.Color(255, 255, 255));
            bikeSharing.setForeground(new java.awt.Color(255, 255, 255));
            timer.setForeground(new java.awt.Color(255, 255, 255));
            history.setForeground(new java.awt.Color(255, 255, 255));
            support.setForeground(new java.awt.Color(19, 175, 248));
            profile.setForeground(new java.awt.Color(255, 255, 255));
            
            newsPage.setVisible(false);
            cpPage.setVisible(false);
            repairPage.setVisible(false);
            sharingPage.setVisible(false);
            timerPage.setVisible(false);
            historyPage.setVisible(false);
            supportPage.setVisible(true);
            userProfilePage.setVisible(false);
            bodySupport.setVisible(true);
            insertSupportPanel.setVisible(false);
            search.setText("Search");
        }
    }//GEN-LAST:event_searchKeyPressed

    private void supportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supportMouseClicked
        news.setForeground(new java.awt.Color(255, 255, 255));
        canCounter.setForeground(new java.awt.Color(255, 255, 255));
        bikeRepair.setForeground(new java.awt.Color(255, 255, 255));
        bikeSharing.setForeground(new java.awt.Color(255, 255, 255));
        timer.setForeground(new java.awt.Color(255, 255, 255));
        history.setForeground(new java.awt.Color(255, 255, 255));
        support.setForeground(new java.awt.Color(19, 175, 248));
        profile.setForeground(new java.awt.Color(255, 255, 255));
        newsPage.setVisible(false);
        cpPage.setVisible(false);
        repairPage.setVisible(false);
        sharingPage.setVisible(false);
        timerPage.setVisible(false);
        historyPage.setVisible(false);
        supportPage.setVisible(true);
        userProfilePage.setVisible(false);
        jTAContact.setText(sp.contact());
        jTAShowyouSearch.setText("");
        bodySupport.setVisible(true);
        insertSupportPanel.setVisible(false);
        waringInsertSupport.setVisible(false);
    }//GEN-LAST:event_supportMouseClicked

    private void newManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newManualActionPerformed
        bodySupport.setVisible(false);
        insertSupportPanel.setVisible(true);
        titleCancleInsertSup.setVisible(false);
        waringInsertSupport.setVisible(false);
    }//GEN-LAST:event_newManualActionPerformed

    private void submitSupportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitSupportActionPerformed
        String title = insertSupporTitle.getText();
        String content = insertContentSup.getText();
        if (title.length() == 0 || content.length() == 0) {
            waringInsertSupport.setVisible(true);
        } else {
            sp.insertSupport(title, content);
            waringInsertSupport.setVisible(false);
            bodySupport.setVisible(true);
            insertSupportPanel.setVisible(false);
            jTAContact.setText(sp.contact());
            jTAShowyouSearch.setText("");
        }
    }//GEN-LAST:event_submitSupportActionPerformed

    private void cancleInsertSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleInsertSupActionPerformed
        bodySupport.setVisible(true);
        insertSupportPanel.setVisible(false);
        insertSupporTitle.setText("");
        insertContentSup.setText("");
        jTAContact.setText(sp.contact());
        jTAShowyouSearch.setText("");
    }//GEN-LAST:event_cancleInsertSupActionPerformed

    private void cancleInsertSupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleInsertSupMouseEntered
        cancleInsertSup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleClick.png")));
        titleCancleInsertSup.setVisible(true);
    }//GEN-LAST:event_cancleInsertSupMouseEntered

    private void cancleInsertSupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleInsertSupMouseExited
        cancleInsertSup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancle.png")));
        titleCancleInsertSup.setVisible(false);
    }//GEN-LAST:event_cancleInsertSupMouseExited

    private void submitSupportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitSupportMouseEntered
        submitSupport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMiniClick.png")));
    }//GEN-LAST:event_submitSupportMouseEntered

    private void submitSupportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitSupportMouseExited
        submitSupport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMini.png")));
    }//GEN-LAST:event_submitSupportMouseExited

    private void chooseImgItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseImgItemActionPerformed
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop"));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.toString();
            pathImgItem.setText(path);
        } else if (result == JFileChooser.CANCEL_OPTION) {
            pathImgItem.setText("");
        }
    }//GEN-LAST:event_chooseImgItemActionPerformed

    private void history1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_history1MouseClicked
        news.setForeground(new java.awt.Color(255, 255, 255));
        canCounter1.setForeground(new java.awt.Color(255, 255, 255));
        bikeSharing1.setForeground(new java.awt.Color(255, 255, 255));
        bikeRepair1.setForeground(new java.awt.Color(255, 255, 255));
        timer1.setForeground(new java.awt.Color(255, 255, 255));
        history1.setForeground(new java.awt.Color(19, 175, 248));
        support1.setForeground(new java.awt.Color(255, 255, 255));
        profile1.setForeground(new java.awt.Color(255, 255, 255));
        newsPage1.setVisible(false);
        cpPage1.setVisible(false);
        repairPage1.setVisible(false);
        sharingScroll.setVisible(false);
        sharingStep2.setVisible(false);
        timePageT.setVisible(false);
        timePageF.setVisible(false);
        historyPage1.setVisible(true);
        supportPage1.setVisible(false);
        userProfilePage1.setVisible(false);
        
        tableHistoryGui(his.tableHistory());
        
    }//GEN-LAST:event_history1MouseClicked

    private void closeUserDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeUserDetailActionPerformed
        userDetail.setVisible(false);
        timewatch1.setVisible(true);
        listUserBorrowing.setVisible(true);
    }//GEN-LAST:event_closeUserDetailActionPerformed

    private void cancleReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleReturnActionPerformed
        selectItemReturn.setVisible(false);
        userDetail.setVisible(true);
        closeUserDetail.setEnabled(true);
    }//GEN-LAST:event_cancleReturnActionPerformed

    private void cancleReturnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleReturnMouseEntered
        cancleReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleMiniClick.png")));
    }//GEN-LAST:event_cancleReturnMouseEntered

    private void cancleReturnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleReturnMouseExited
        cancleReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleMini.png")));
    }//GEN-LAST:event_cancleReturnMouseExited

    private void newsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newsMouseClicked
        news.setForeground(new java.awt.Color(19, 175, 248));
        canCounter.setForeground(new java.awt.Color(255, 255, 255));
        bikeRepair.setForeground(new java.awt.Color(255, 255, 255));
        bikeSharing.setForeground(new java.awt.Color(255, 255, 255));
        timer.setForeground(new java.awt.Color(255, 255, 255));
        history.setForeground(new java.awt.Color(255, 255, 255));
        support.setForeground(new java.awt.Color(255, 255, 255));
        profile.setForeground(new java.awt.Color(255, 255, 255));
        newsPage.setVisible(true);
        cpPage.setVisible(false);
        repairPage.setVisible(false);
        sharingPage.setVisible(false);
        timerPage.setVisible(false);
        historyPage.setVisible(false);
        supportPage.setVisible(false);
        userProfilePage.setVisible(false);
        
        showtitleInsertNews.setVisible(false);
        setListNewsAdmin();
        jPanelInsertNews.setVisible(false);
        showNews.setVisible(false);
        newsList.setVisible(true);
    }//GEN-LAST:event_newsMouseClicked

    private void jButtonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertActionPerformed
        jPanelInsertNews.setVisible(true);
        newsList.setVisible(false);
        txtAreaNewsDetail.setLineWrap(true);
        jButtonInsert.setVisible(false);
    }//GEN-LAST:event_jButtonInsertActionPerformed

    private void submitButtonNewsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonNewsActionPerformed
        String description = txtFieldNewsDescription.getText();
        String detail = txtAreaNewsDetail.getText();
        nw.insertNews(description, detail, pathImgNews.getText());
        pathImgNews.setText("No Select File.");
        //Reset Text Fields
        txtFieldNewsDescription.setText("");
        txtAreaNewsDetail.setText("");
        jPanelInsertNews.setVisible(false);
        showlistNews.setVisible(true);
        newsList.setVisible(true);
        setListNewsAdmin();
        jButtonInsert.setVisible(true);
        JOptionPane.showMessageDialog(null, "Insert Sucessfully");
    }//GEN-LAST:event_submitButtonNewsActionPerformed

    private void chooseImgNewsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseImgNewsActionPerformed
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop"));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.toString();
            pathImgNews.setText(path);
        } else if (result == JFileChooser.CANCEL_OPTION) {
            pathImgNews.setText("No Select File.");
        }
    }//GEN-LAST:event_chooseImgNewsActionPerformed

    private void cancleButtonNewsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleButtonNewsActionPerformed
        jPanelInsertNews.setVisible(false);
        newsList.setVisible(true);
        showlistNews.setVisible(true);
        newsList.setVisible(true);
        setListNewsAdmin();
        jButtonInsert.setVisible(true);
    }//GEN-LAST:event_cancleButtonNewsActionPerformed

    private void closeNewsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeNewsActionPerformed
        jPanelInsertNews.setVisible(false);
        binding.setVisible(false);
        showNews.setVisible(false);
        showlistNews.setVisible(true);
        newsList.setVisible(true);
        setListNewsAdmin();
        jButtonInsert.setVisible(true);
    }//GEN-LAST:event_closeNewsActionPerformed

    private void submitButtonNewsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonNewsMouseEntered
        submitButtonNews.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMiniClick.png")));
    }//GEN-LAST:event_submitButtonNewsMouseEntered

    private void submitButtonNewsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonNewsMouseExited
        submitButtonNews.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMini.png")));
    }//GEN-LAST:event_submitButtonNewsMouseExited

    private void cancleButtonNewsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleButtonNewsMouseEntered
        cancleButtonNews.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleMiniClick.png")));
    }//GEN-LAST:event_cancleButtonNewsMouseEntered

    private void cancleButtonNewsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleButtonNewsMouseExited
        cancleButtonNews.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleMini.png")));
    }//GEN-LAST:event_cancleButtonNewsMouseExited

    private void submitUpdateNewsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitUpdateNewsMouseEntered
        submitUpdateNews.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMiniClick.png")));
    }//GEN-LAST:event_submitUpdateNewsMouseEntered

    private void submitUpdateNewsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitUpdateNewsMouseExited
        submitUpdateNews.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitMini.png")));
    }//GEN-LAST:event_submitUpdateNewsMouseExited

    private void cancleUpdateNewsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleUpdateNewsMouseEntered
        cancleUpdateNews.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleMiniClick.png")));
    }//GEN-LAST:event_cancleUpdateNewsMouseEntered

    private void cancleUpdateNewsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleUpdateNewsMouseExited
        cancleUpdateNews.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleMini.png")));
    }//GEN-LAST:event_cancleUpdateNewsMouseExited

    private void news1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_news1MouseClicked
        news1.setForeground(new java.awt.Color(19, 175, 248));
        canCounter1.setForeground(new java.awt.Color(255, 255, 255));
        bikeSharing1.setForeground(new java.awt.Color(255, 255, 255));
        bikeRepair1.setForeground(new java.awt.Color(255, 255, 255));
        timer1.setForeground(new java.awt.Color(255, 255, 255));
        history1.setForeground(new java.awt.Color(255, 255, 255));
        support1.setForeground(new java.awt.Color(255, 255, 255));
        profile1.setForeground(new java.awt.Color(255, 255, 255));
        newsPage1.setVisible(true);
        cpPage1.setVisible(false);
        repairPage1.setVisible(false);
        sharingScroll.setVisible(false);
        sharingStep2.setVisible(false);
        timePageT.setVisible(false);
        timePageF.setVisible(false);
        historyPage1.setVisible(false);
        supportPage1.setVisible(false);
        userProfilePage1.setVisible(false);

        listNewsScroll.setVisible(true);
        listNewsPage.setVisible(true);
        setListNewsUser();
        jPanelShowNewsAfterClick.setVisible(false);
    }//GEN-LAST:event_news1MouseClicked

    private void closeNews1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeNews1ActionPerformed
        jPanelShowNewsAfterClick.setVisible(false);
        listNewsScroll.setVisible(true);
        listNewsPage.setVisible(true);
        setListNewsUser();
    }//GEN-LAST:event_closeNews1ActionPerformed

    private void jButtonInsertMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonInsertMouseEntered
        showtitleInsertNews.setVisible(true);
    }//GEN-LAST:event_jButtonInsertMouseEntered

    private void jButtonInsertMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonInsertMouseExited
        showtitleInsertNews.setVisible(false);
    }//GEN-LAST:event_jButtonInsertMouseExited

    private void historyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyMouseClicked
        news.setForeground(new java.awt.Color(255, 255, 255));
        canCounter.setForeground(new java.awt.Color(255, 255, 255));
        bikeRepair.setForeground(new java.awt.Color(255, 255, 255));
        bikeSharing.setForeground(new java.awt.Color(255, 255, 255));
        timer.setForeground(new java.awt.Color(255, 255, 255));
        history.setForeground(new java.awt.Color(19, 175, 248));
        support.setForeground(new java.awt.Color(255, 255, 255));
        profile.setForeground(new java.awt.Color(255, 255, 255));
        newsPage.setVisible(false);
        cpPage.setVisible(false);
        repairPage.setVisible(false);
        sharingPage.setVisible(false);
        timerPage.setVisible(false);
        historyPage.setVisible(true);
        supportPage.setVisible(false);
        userProfilePage.setVisible(false);
        showStatHistory();
    }//GEN-LAST:event_historyMouseClicked

    private void jBtBackRepairUserSentToAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtBackRepairUserSentToAdminActionPerformed
        jPanelRepairUserSentToAdmin.setVisible(true);
        jPanelRepairUserFollowRepairing.setVisible(false);
    }//GEN-LAST:event_jBtBackRepairUserSentToAdminActionPerformed

    private void jTFWhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFWhatActionPerformed
        rpw.setWhyRepair(jTFWhat.getText());
    }//GEN-LAST:event_jTFWhatActionPerformed

    private void jTFbikeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFbikeActionPerformed
        rpw.setBike(jTFbike.getText());
    }//GEN-LAST:event_jTFbikeActionPerformed

    private void jTFColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFColorActionPerformed
        rpw.setColor(jTFColor.getText());
    }//GEN-LAST:event_jTFColorActionPerformed

    private void jBtToFollowingRepairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtToFollowingRepairActionPerformed
        if(jTFWhat.getText().equalsIgnoreCase("")){
            jLabelWarningUserInputWhyRepair.setText("Why Repair is empty!");
        }
        if(jTFbike.getText().equalsIgnoreCase("")){
            jLabelWarningUserInputModelBike.setText("Model Bike is empty!");
        }
        if(jTFColor.getText().equalsIgnoreCase("")){
            jLabelWarningUserInputColorBike.setText("Coloe bike is empty!");
        }

        if(jTFWhat.getText().equalsIgnoreCase("")==false && jTFbike.getText().equalsIgnoreCase("")==false && jTFColor.getText().equalsIgnoreCase("")==false){
            whyRepair = jTFWhat.getText();
            bike = jTFbike.getText();
            color = jTFColor.getText();
            rpw.connectDBFromUserToAdmin(whyRepair,bike,color,User.getUserId());//return date ยังใส่ไม่ได้ต้องให้ช่างประเมินเวลาก่อน
            clearTextLabelFollowRepair();
            setDataReparing();
            jPanelRepairUserFollowRepairing.setVisible(true);
            jPanelRepairUserSentToAdmin.setVisible(false);
            titlenextFollingRepairUser.setVisible(false);
            titleBackRepairUser.setVisible(false);
        }

    }//GEN-LAST:event_jBtToFollowingRepairActionPerformed

    private void bikeRepair1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bikeRepair1MouseClicked
        news1.setForeground(new java.awt.Color(255, 255, 255));
        canCounter1.setForeground(new java.awt.Color(255, 255, 255));
        bikeSharing1.setForeground(new java.awt.Color(255, 255, 255));
        bikeRepair1.setForeground(new java.awt.Color(19, 175, 248));
        timer1.setForeground(new java.awt.Color(255, 255, 255));
        history1.setForeground(new java.awt.Color(255, 255, 255));
        support1.setForeground(new java.awt.Color(255, 255, 255));
        profile1.setForeground(new java.awt.Color(255, 255, 255));
        newsPage1.setVisible(false);
        cpPage1.setVisible(false);
        repairPage1.setVisible(true);
        sharingScroll.setVisible(false);
        sharingStep2.setVisible(false);
        timePageT.setVisible(false);
        timePageF.setVisible(false);
        historyPage1.setVisible(false);
        supportPage1.setVisible(false);
        userProfilePage1.setVisible(false);
        
        jPanelRepairUserSentToAdmin.setVisible(true);
        jPanelRepairUserFollowRepairing.setVisible(false);
        titlenextFollingRepairUser.setVisible(false);
        titleBackRepairUser.setVisible(false);
    }//GEN-LAST:event_bikeRepair1MouseClicked

    private void jBtToFollowingRepairMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtToFollowingRepairMouseEntered
        jBtToFollowingRepair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/right arrow click.png")));
        titlenextFollingRepairUser.setVisible(true);
    }//GEN-LAST:event_jBtToFollowingRepairMouseEntered

    private void jBtToFollowingRepairMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtToFollowingRepairMouseExited
        jBtToFollowingRepair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/right arrow.png")));
        titlenextFollingRepairUser.setVisible(false);
    }//GEN-LAST:event_jBtToFollowingRepairMouseExited

    private void jBtBackRepairUserSentToAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtBackRepairUserSentToAdminMouseEntered
        jBtBackRepairUserSentToAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/left arrow click.png")));
        titleBackRepairUser.setVisible(true);
    }//GEN-LAST:event_jBtBackRepairUserSentToAdminMouseEntered

    private void jBtBackRepairUserSentToAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtBackRepairUserSentToAdminMouseExited
        jBtBackRepairUserSentToAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/left arrow.png")));
        titleBackRepairUser.setVisible(false);
    }//GEN-LAST:event_jBtBackRepairUserSentToAdminMouseExited

    private void jButtonFollowingRepairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFollowingRepairActionPerformed
        clearTextLabelFollowRepair();
        setDataReparing();
        iconStatusFollowingRepairing();
        jPanelRepairUserSentToAdmin.setVisible(false);
        jPanelRepairUserFollowRepairing.setVisible(true);
    }//GEN-LAST:event_jButtonFollowingRepairActionPerformed

    private void jBTNextToPanelNotSuccessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNextToPanelNotSuccessActionPerformed
        revalidate();
            repaint();
        jPanelRepairingNotSuccess.setVisible(false);
        repairingNotSuccess();
        jPanelShowRepair.setVisible(false);
        jPanelHeadBikeRepairForRepairForAdmin.setVisible(false);
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingAdmin.setVisible(false);

    }//GEN-LAST:event_jBTNextToPanelNotSuccessActionPerformed

    private void jBTBackToRepairShowTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTBackToRepairShowTimeActionPerformed
        revalidate();
        repaint();
        jPanelShowRepair.setVisible(false);
        jPanelHeadBikeRepairForRepairForAdmin.setVisible(false);
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingAdmin.setVisible(false);
        jPanelRepairingNotSuccess.setVisible(false);
    }//GEN-LAST:event_jBTBackToRepairShowTimeActionPerformed

    private void jBTnextToShowTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTnextToShowTimeActionPerformed
        String repairing;
        String asking;
        //----------------------------------------------------------------------
        repairing = jTFDetail.getText();
        rpw.setDetail(repairing);
        asking = jTFProblem.getText();
        rpw.setProblem(asking);
        
        Date nowTime = new Date();
        Timestamp startDate = new Timestamp(nowTime.getTime());
        
        if(jTFProblem.getText().equals("") || (jTFDetail.getText().equals(""))){
            JOptionPane.showMessageDialog(null,"ยังไม่ได้กรอกข้อความ","Warning Message",JOptionPane.WARNING_MESSAGE);
        }else{
             rpw.connectDBFromAdminToUser(repairIDAdmin,userIDRepairAdmin);
            rpw.connectDBForAdminUpdateTime(transIDRepairAdmin, startDate, startDate);
            rpw.connectDBForRepairAdmin();
            listUserRepair();
            jPanelHeadBikeRepairForRepairForAdmin.setVisible(true);
            jButtonRepairForNextToPageNotsuccess.setVisible(false);
            jPanelRepairingNotSuccess.setVisible(true);
            jPanelRepairingAdmin.setVisible(false);
            jPanelRepairAdminDetailUser.setVisible(false);
            jPanelRepairingSetRepair.setVisible(false);
            jPanelShowRepair .setVisible(false);
            jScrollPaneBikeRepairingNotSuccess.setVisible(true);
            repairingNotSuccess();
            revalidate();
            repaint();
        }

    }//GEN-LAST:event_jBTnextToShowTimeActionPerformed

    private void jButtonBackAdmindetailUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackAdmindetailUserActionPerformed
        jPanelShowRepair.setVisible(false);
        jPanelHeadBikeRepairForRepairForAdmin.setVisible(false);
        revalidate();
        repaint();
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingAdmin.setVisible(false);
        jPanelRepairingNotSuccess.setVisible(false);
    }//GEN-LAST:event_jButtonBackAdmindetailUserActionPerformed

    private void jButtonBackToAdminUserDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackToAdminUserDetailActionPerformed
        jPanelShowRepair.setVisible(false);
        jPanelHeadBikeRepairForRepairForAdmin.setVisible(false);
        revalidate();
        repaint();
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingAdmin.setVisible(false);
        jPanelRepairingNotSuccess.setVisible(false);
    }//GEN-LAST:event_jButtonBackToAdminUserDetailActionPerformed

    private void jButtonToRepairAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonToRepairAdminActionPerformed
        listUserRepair();
        jButtonRepairForNextToPageNotsuccess.setVisible(true);
        jPanelRepairingNotSuccess.setVisible(false);
        jPanelRepairingAdmin.setVisible(true);
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingSetRepair.setVisible(false);
        jPanelShowRepair .setVisible(false);
        jPanelRepairingAdmin.revalidate();
        jPanelRepairingAdmin.repaint();
    }//GEN-LAST:event_jButtonToRepairAdminActionPerformed

    private void jButtonRefreshRepairNotSuccessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshRepairNotSuccessActionPerformed
        jPanelBikeRepairNotSuccess.removeAll();
        repairingNotSuccess();
        jPanelBikeRepairNotSuccess.revalidate();
        jPanelBikeRepairNotSuccess.repaint();
    }//GEN-LAST:event_jButtonRefreshRepairNotSuccessActionPerformed

    private void jTAShowDetaiUserSentRepirlForAdminAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTAShowDetaiUserSentRepirlForAdminAncestorAdded
        jTAShowDetaiUserSentRepirlForAdmin.setText(detailRepairAdmin);
    }//GEN-LAST:event_jTAShowDetaiUserSentRepirlForAdminAncestorAdded

    private void jBTbackToJPanelRepairAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTbackToJPanelRepairAdminActionPerformed
        jPanelRepairingAdmin.setVisible(true);
        jButtonRepairForNextToPageNotsuccess.setVisible(true);
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingSetRepair.setVisible(false);
        jPanelShowRepair.setVisible(false);
        jPanelRepairingNotSuccess.setVisible(false);
        listUserRepair();
        revalidate();
        repaint();
    }//GEN-LAST:event_jBTbackToJPanelRepairAdminActionPerformed

    private void jBTnextTojPanelRepairingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTnextTojPanelRepairingActionPerformed
        Object[] options = {"Yes","No"};
        int ans = JOptionPane.showOptionDialog(null, "Customer brought bicycle to Green Society", "Check Status",
            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
            null, options, options[0]);
        int select = jComboBoxStatusBike.getSelectedIndex();
        if(ans==0 && select==1){
            jPanelRepairingAdmin.setVisible(false);
            jPanelRepairAdminDetailUser.setVisible(false);
            jPanelRepairingSetRepair.setVisible(true);
            jPanelShowRepair.setVisible(false);
            jPanelRepairingNotSuccess.setVisible(false);
            jButtonRepairForNextToPageNotsuccess.setVisible(false);
            revalidate();
            repaint();
        }else{
            JOptionPane.showMessageDialog(null,"Check Status bicycle Again","Problem",JOptionPane.WARNING_MESSAGE);
        }
        
        
    }//GEN-LAST:event_jBTnextTojPanelRepairingActionPerformed

    private void bikeRepairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bikeRepairMouseClicked
        news.setForeground(new java.awt.Color(255, 255, 255));
        canCounter.setForeground(new java.awt.Color(255, 255, 255));
        bikeSharing.setForeground(new java.awt.Color(255, 255, 255));
        bikeRepair.setForeground(new java.awt.Color(19, 175, 248));
        timer.setForeground(new java.awt.Color(255, 255, 255));
        history.setForeground(new java.awt.Color(255, 255, 255));
        support.setForeground(new java.awt.Color(255, 255, 255));
        profile.setForeground(new java.awt.Color(255, 255, 255));
        newsPage.setVisible(false);
        cpPage.setVisible(false);
        repairPage.setVisible(true);
        sharingPage.setVisible(false);
        timerPage.setVisible(false);
        historyPage.setVisible(false);
        supportPage.setVisible(false);
        userProfilePage.setVisible(false);
        
        
        jPanelHeadBikeRepairForRepairForAdmin.setVisible(true);
        jButtonRepairForNextToPageNotsuccess.setVisible(false);
        jPanelRepairingNotSuccess.setVisible(true);
        jPanelRepairingAdmin.setVisible(false);
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingSetRepair.setVisible(false);
        jPanelShowRepair .setVisible(false);
        repairingNotSuccess();
        revalidate();
        repaint();
    }//GEN-LAST:event_bikeRepairMouseClicked

    private void jButtonRepairForNextToPageNotsuccessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRepairForNextToPageNotsuccessActionPerformed
        jPanelHeadBikeRepairForRepairForAdmin.setVisible(true);
        jPanelRepairingNotSuccess.setVisible(true);
        jPanelRepairingAdmin.setVisible(false);
        jPanelRepairAdminDetailUser.setVisible(false);
        jPanelRepairingSetRepair.setVisible(false);
        jPanelShowRepair .setVisible(false);
        repairingNotSuccess();
        revalidate();
        repaint();
    }//GEN-LAST:event_jButtonRepairForNextToPageNotsuccessActionPerformed

    private void submitPassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitPassMouseEntered
        submitPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitPassClick.png")));
    }//GEN-LAST:event_submitPassMouseEntered

    private void submitPassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitPassMouseExited
        submitPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitPass.png")));
    }//GEN-LAST:event_submitPassMouseExited

    private void submitPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitPassActionPerformed
        String pass = new String(jPasswordField1.getPassword());
        String pass2 = new String(jPasswordField2.getPassword());
        if(ac.checkEmail(forgotEmail.getText())){
            if(pass.equals(pass2)){
                ac.forgetPass(forgotEmail.getText(), pass);
                login.setVisible(true);
                forgotPassPage.setVisible(false);
                forgotEmail.setText("");
                jPasswordField1.setText("");
                jPasswordField1.setText("");
                errorForgot.setText("");
            }else{
                errorForgot.setText("Password is incorrect.");
            }
        }else{
            errorForgot.setText("Can't find that email, sorry.");
        }
    }//GEN-LAST:event_submitPassActionPerformed

    private void refreshRepairAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshRepairAdminActionPerformed
        jPNBackGround.removeAll();
        listUserRepair();
        jPNBackGround.revalidate();
        jPNBackGround.repaint();  
    }//GEN-LAST:event_refreshRepairAdminActionPerformed

    private void jFormattedTelophoneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTelophoneFocusGained
        // TODO add your handling code here:
        if (jFormattedTelophone.getText().equals("090xxxxxxx")) {
            jFormattedTelophone.setText("");
            jFormattedTelophone.setForeground(new java.awt.Color(255, 255, 255));
        }
        jFormattedTelophone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(19, 175, 248)));
    }//GEN-LAST:event_jFormattedTelophoneFocusGained

    private void jFormattedTelophoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTelophoneFocusLost
        // TODO add your handling code here:
        if (jFormattedTelophone.getText().equals("") || jFormattedTelophone.getText().length() == 0) {
            jFormattedTelophone.setForeground(new Color(153, 153, 153, 150));
            jFormattedTelophone.setText("090xxxxxxx");
        }
        jFormattedTelophone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_jFormattedTelophoneFocusLost

    private void itemCPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_itemCPFocusGained
        if (itemCP.getText().equals("Count")) {
            itemCP.setForeground(new Color(255, 255, 255));
            itemCP.setText("");
        }
    }//GEN-LAST:event_itemCPFocusGained

    private void itemCPFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_itemCPFocusLost
        if (itemCP.getText().equals("") || itemCP.getText().length() == 0) {
            itemCP.setForeground(new Color(153, 153, 153, 150));
            itemCP.setText("Count");
        }
    }//GEN-LAST:event_itemCPFocusLost

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
            java.util.logging.Logger.getLogger(GreenSociety.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GreenSociety.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GreenSociety.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GreenSociety.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GreenSociety().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel Background1;
    private javax.swing.JLabel Backgroung2;
    private javax.swing.JLabel GreenSociety;
    private javax.swing.JPanel History;
    private javax.swing.JLabel actionHis;
    private javax.swing.JLabel arrowDownIcon;
    private javax.swing.JLabel arrowDownIcon1;
    private javax.swing.JPanel backAdd;
    private javax.swing.JPanel backBarIcon;
    private javax.swing.JPanel backBarIcon1;
    private javax.swing.JPanel backBarUser;
    private javax.swing.JPanel backBarUser1;
    private javax.swing.JPanel backDelete;
    private javax.swing.JPanel backEdit;
    private javax.swing.JLabel backLogin;
    private javax.swing.JPanel backMenuBar;
    private javax.swing.JPanel backMenuBar1;
    private javax.swing.JLabel backSignin;
    private javax.swing.JLabel backSigninRegis;
    private javax.swing.JButton backStep1;
    private javax.swing.JPanel barIcon;
    private javax.swing.JPanel barIcon1;
    private javax.swing.JPanel barNoti;
    private javax.swing.JPanel barNoti1;
    private javax.swing.JPanel barSearch;
    private javax.swing.JPanel barSearch1;
    private javax.swing.JPanel barTitle;
    private javax.swing.JPanel barTitle1;
    private javax.swing.JPanel barUser;
    private javax.swing.JPanel barUser1;
    private javax.swing.JLabel bikeRepair;
    private javax.swing.JLabel bikeRepair1;
    private javax.swing.JLabel bikeSharing;
    private javax.swing.JLabel bikeSharing1;
    private javax.swing.JPanel binding;
    private javax.swing.JFormattedTextField birthProfile;
    private javax.swing.JFormattedTextField birthProfile1;
    private javax.swing.JPanel bodySupport;
    private javax.swing.JPanel bodySupport1;
    private javax.swing.JButton borrowBut;
    private javax.swing.ButtonGroup buttonGroupGender;
    private javax.swing.JLabel canCounter;
    private javax.swing.JLabel canCounter1;
    private javax.swing.JButton cancleAdminBut;
    private javax.swing.JButton cancleBut;
    private javax.swing.JButton cancleButSharing;
    private javax.swing.JButton cancleButtonNews;
    private javax.swing.JButton cancleInsertSup;
    private javax.swing.JButton cancleReturn;
    private javax.swing.JButton cancleUpdateNews;
    private javax.swing.JButton chooseImgItem;
    private javax.swing.JButton chooseImgNews;
    private javax.swing.JButton chooseImgProfileBut;
    private javax.swing.JButton chooseImgProfileBut1;
    private javax.swing.JLabel circleCp;
    private javax.swing.JLabel circleMini;
    private javax.swing.JLabel circleMini1;
    private javax.swing.JLabel circleNoti;
    private javax.swing.JLabel closeBut;
    private javax.swing.JButton closeNews;
    private javax.swing.JButton closeNews1;
    private javax.swing.JButton closeUserDetail;
    private javax.swing.JTextField congenProfile;
    private javax.swing.JTextField congenProfile1;
    private javax.swing.JScrollPane contactScroll;
    private javax.swing.JScrollPane contactScroll1;
    private javax.swing.JTextField countOfCan;
    private javax.swing.JPanel cpPage;
    private javax.swing.JPanel cpPage1;
    private javax.swing.JLabel cpUse;
    private javax.swing.JLabel cpUser;
    private javax.swing.JLabel departProfile;
    private javax.swing.JLabel departProfile1;
    private javax.swing.JScrollPane describeNews;
    private javax.swing.JTextArea describeNewsText;
    private javax.swing.JPanel detailBox;
    private javax.swing.JPanel detailBoxHistory;
    private javax.swing.JLabel detailData;
    private javax.swing.JButton editProfileBut;
    private javax.swing.JButton editProfileBut1;
    private javax.swing.JTextField email;
    private javax.swing.JTextField emailProfile;
    private javax.swing.JTextField emailProfile1;
    private javax.swing.JLabel endTime;
    private javax.swing.JLabel endTime1;
    private javax.swing.JLabel errorForgot;
    private javax.swing.JTextField forgotEmail;
    private javax.swing.JLabel forgotPass;
    private javax.swing.JPanel forgotPassPage;
    private javax.swing.JTextField genderProfile;
    private javax.swing.JTextField genderProfile1;
    private javax.swing.JLabel history;
    private javax.swing.JLabel history1;
    private javax.swing.JPanel historyPage;
    private javax.swing.JPanel historyPage1;
    private javax.swing.JLabel iconAction;
    private javax.swing.JLabel iconBike;
    private javax.swing.JLabel iconBike1;
    private javax.swing.JLabel iconClockBig;
    private javax.swing.JLabel iconClockBig1;
    private javax.swing.JLabel iconEmail;
    private javax.swing.JLabel iconItem;
    private javax.swing.JLabel iconKey;
    private javax.swing.JLabel iconMenu;
    private javax.swing.JLabel iconMenu1;
    private javax.swing.JLabel iconNotHis;
    private javax.swing.JLabel iconNoti1;
    private javax.swing.JLabel iconProfile;
    private javax.swing.JLabel iconProfile1;
    private javax.swing.JLabel iconSearch;
    private javax.swing.JLabel iconSearch1;
    private javax.swing.JLabel iconStart;
    private javax.swing.JLabel iconend;
    private javax.swing.JLabel imageProfile;
    private javax.swing.JLabel imageProfile1;
    private javax.swing.JLabel imgNews;
    private javax.swing.JPanel inputForgot;
    private javax.swing.JTextArea insertContentSup;
    private javax.swing.JScrollPane insertDetailScroll;
    private javax.swing.JTextField insertSupporTitle;
    private javax.swing.JScrollPane insertSupportContentArea;
    private javax.swing.JPanel insertSupportPanel;
    private javax.swing.JLabel isCp;
    private javax.swing.JTextField itemAmount;
    private javax.swing.JTextField itemCP;
    private javax.swing.JTextField itemId;
    private javax.swing.JButton itemList;
    private javax.swing.JPanel itemListPage;
    private javax.swing.JScrollPane itemListScroll;
    private javax.swing.JPanel itemListShow;
    private javax.swing.JPanel itemListShowInsidee;
    private javax.swing.JTextField itemName;
    private javax.swing.JButton jBTBackToRepairShowTime;
    private javax.swing.JButton jBTNextToPanelNotSuccess;
    private javax.swing.JButton jBTbackToJPanelRepairAdmin;
    private javax.swing.JButton jBTnextToShowTime;
    private javax.swing.JButton jBTnextTojPanelRepairing;
    private javax.swing.JButton jBtBackRepairUserSentToAdmin;
    private javax.swing.JButton jBtToFollowingRepair;
    private javax.swing.JButton jButtonBackAdmindetailUser;
    private javax.swing.JButton jButtonBackToAdminUserDetail;
    private javax.swing.JButton jButtonChooseFileForUpload;
    private javax.swing.JButton jButtonFollowingRepair;
    private javax.swing.JButton jButtonInsert;
    private javax.swing.JButton jButtonRefreshRepairNotSuccess;
    private javax.swing.JButton jButtonRepairForNextToPageNotsuccess;
    private javax.swing.JButton jButtonSignUp;
    private javax.swing.JButton jButtonToRepairAdmin;
    private javax.swing.JComboBox<String> jComboBoxPosition;
    private javax.swing.JComboBox<String> jComboBoxStatusBike;
    private com.toedter.calendar.JDateChooser jDateChooserBirthDate;
    private javax.swing.JFormattedTextField jFormatTextFieldForId;
    private javax.swing.JFormattedTextField jFormattedTelophone;
    private javax.swing.JLabel jLBShowingResult;
    private javax.swing.JLabel jLBShowingResult1;
    private javax.swing.JLabel jLBWhatsearch;
    private javax.swing.JLabel jLBWhatsearch1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAskingText;
    private javax.swing.JLabel jLabelAskingTextInJPanelAsking;
    private javax.swing.JLabel jLabelBikeRepairing;
    private javax.swing.JLabel jLabelBikeRepairingForRepairingAdmin;
    private javax.swing.JLabel jLabelBrandBike;
    private javax.swing.JLabel jLabelBrandBike1;
    private javax.swing.JLabel jLabelBrandBike2;
    private javax.swing.JLabel jLabelDetail;
    private javax.swing.JLabel jLabelEmailNotCorrect;
    private javax.swing.JLabel jLabelHistoryText;
    private javax.swing.JLabel jLabelHistoryText2;
    private javax.swing.JLabel jLabelIconBikeInRecieving;
    private javax.swing.JLabel jLabelInsert;
    private javax.swing.JLabel jLabelNoticBirthDate;
    private javax.swing.JLabel jLabelNoticGender;
    private javax.swing.JLabel jLabelNoticName;
    private javax.swing.JLabel jLabelNoticNewPass;
    private javax.swing.JLabel jLabelNoticOldPass;
    private javax.swing.JLabel jLabelNoticPosition;
    private javax.swing.JLabel jLabelNoticSurname;
    private javax.swing.JLabel jLabelNoticTel;
    private javax.swing.JLabel jLabelPartPictureUserUpload;
    private javax.swing.JLabel jLabelPictureProfileRegister;
    private javax.swing.JLabel jLabelProblem;
    private javax.swing.JLabel jLabelRecieving;
    private javax.swing.JLabel jLabelRecievingText;
    private javax.swing.JLabel jLabelRepairing;
    private javax.swing.JLabel jLabelRepairingText;
    private javax.swing.JLabel jLabelShowTopicAfterClick;
    private javax.swing.JLabel jLabelStarLN;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelStatusBikeUserSentTOadmin;
    private javax.swing.JLabel jLabelStatusRepairIcon;
    private javax.swing.JLabel jLabelTime;
    private javax.swing.JLabel jLabelWarningUserInputColorBike;
    private javax.swing.JLabel jLabelWarningUserInputModelBike;
    private javax.swing.JLabel jLabelWarningUserInputWhyRepair;
    private javax.swing.JLabel jLabelifLengthId;
    private javax.swing.JLabel jLbConfirmPassWord;
    private javax.swing.JLabel jLbCongenitialDisease;
    private javax.swing.JLabel jLbDateOfBirth;
    private javax.swing.JLabel jLbEmail;
    private javax.swing.JLabel jLbFirstName;
    private javax.swing.JLabel jLbGender;
    private javax.swing.JLabel jLbId;
    private javax.swing.JLabel jLbLastName;
    private javax.swing.JLabel jLbPassWord;
    private javax.swing.JLabel jLbPosition;
    private javax.swing.JLabel jLbSignUp;
    private javax.swing.JLabel jLbStarBirthDay;
    private javax.swing.JLabel jLbStarConfirmPass;
    private javax.swing.JLabel jLbStarEmail;
    private javax.swing.JLabel jLbStarFirstName;
    private javax.swing.JLabel jLbStarGender;
    private javax.swing.JLabel jLbStarId;
    private javax.swing.JLabel jLbStarPassword;
    private javax.swing.JLabel jLbStarPosition;
    private javax.swing.JLabel jLbStarTelophone;
    private javax.swing.JLabel jLbTelophone;
    private javax.swing.JPanel jPNBackGround;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanelAsking;
    private javax.swing.JPanel jPanelBackgroundInsertLabel;
    private javax.swing.JPanel jPanelBikeRepairNotSuccess;
    private javax.swing.JPanel jPanelHeadBikeRepairForRepairForAdmin;
    private javax.swing.JPanel jPanelHeadBikeRepairing;
    private javax.swing.JPanel jPanelHeadHistory;
    private javax.swing.JPanel jPanelHeadHistory2;
    private javax.swing.JPanel jPanelHistory;
    private javax.swing.JPanel jPanelInsertNews;
    private javax.swing.JPanel jPanelRecieving;
    private javax.swing.JPanel jPanelRepairAdminDetailUser;
    private javax.swing.JPanel jPanelRepairUserFollowRepairing;
    private javax.swing.JPanel jPanelRepairUserSentToAdmin;
    private javax.swing.JPanel jPanelRepairing;
    private javax.swing.JPanel jPanelRepairingAdmin;
    private javax.swing.JPanel jPanelRepairingNotSuccess;
    private javax.swing.JPanel jPanelRepairingSetRepair;
    private javax.swing.JPanel jPanelSetDetailRepairForAdmin;
    private javax.swing.JPanel jPanelShoeUserSentToAdminForRepair;
    private javax.swing.JPanel jPanelShowGraph;
    private javax.swing.JPanel jPanelShowNewsAfterClick;
    private javax.swing.JPanel jPanelShowRepair;
    private javax.swing.JPanel jPanelSignUp;
    private javax.swing.JPanel jPanelTableHistory;
    private javax.swing.JPasswordField jPasswordConfirmPassSignup;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordSignUp;
    private javax.swing.JPanel jPnTimeDetail;
    private javax.swing.JRadioButton jRadioButtonGenderFemale;
    private javax.swing.JRadioButton jRadioButtonGenderMale;
    private javax.swing.JScrollPane jScrollPaneBikeRepairingNotSuccess;
    private javax.swing.JScrollPane jScrollPaneHistory;
    private javax.swing.JScrollPane jScrollPaneInTextAreaNewsDetail;
    private javax.swing.JScrollPane jScrollPaneShowDetail;
    private javax.swing.JScrollPane jScrollPaneShowDetailUserSentToRepair;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JSeparator jSeparatorUnderDetail;
    private javax.swing.JSeparator jSeparatorUnderProblem;
    private javax.swing.JTextArea jTAContact;
    private javax.swing.JTextArea jTAContact1;
    private javax.swing.JTextArea jTAShowDetaiUserSentRepirlForAdmin;
    private javax.swing.JTextArea jTAShowDetail;
    private javax.swing.JTextArea jTAShowyouSearch;
    private javax.swing.JTextArea jTAShowyouSearch1;
    private javax.swing.JTextField jTFColor;
    private javax.swing.JTextField jTFDetail;
    private javax.swing.JTextField jTFProblem;
    private javax.swing.JTextField jTFWhat;
    private javax.swing.JTextField jTFbike;
    private javax.swing.JTextArea jTextAreaShowDetailNewsAfterClick;
    private javax.swing.JTextField jTextFieldConDisease;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldSurname;
    private javax.swing.JTextField jTfFirstName;
    private javax.swing.JPanel layerAddItem;
    private javax.swing.JPanel listNewsPage;
    private javax.swing.JScrollPane listNewsScroll;
    private javax.swing.JLabel listShow;
    private javax.swing.JLabel listTimeup;
    private javax.swing.JScrollPane listUserBorrowing;
    private javax.swing.JPanel login;
    private javax.swing.JPanel mainAdmin;
    private javax.swing.JPanel mainUser;
    private javax.swing.JLabel menu;
    private javax.swing.JLabel menu1;
    private javax.swing.JButton menuAdd;
    private javax.swing.JPanel menuBar;
    private javax.swing.JPanel menuBar1;
    private javax.swing.JButton menuDelete;
    private javax.swing.JButton menuEdit;
    private javax.swing.JPanel menuSharing;
    private javax.swing.JLabel menuSignup;
    private javax.swing.JLabel name;
    private javax.swing.JLabel name1;
    private javax.swing.JTextField nameOfUser;
    private javax.swing.JTextField nameProfile;
    private javax.swing.JTextField nameProfile1;
    private javax.swing.JButton newManual;
    private javax.swing.JLabel news;
    private javax.swing.JLabel news1;
    private javax.swing.JScrollPane newsList;
    private javax.swing.JPanel newsPage;
    private javax.swing.JPanel newsPage1;
    private javax.swing.JButton nextStep;
    private javax.swing.JLabel notiSharing;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel pathImgItem;
    private javax.swing.JLabel pathImgNews;
    private javax.swing.JLabel pathImgProfile;
    private javax.swing.JLabel pathImgProfile1;
    private javax.swing.JLabel pic;
    private javax.swing.JLabel pic1;
    private javax.swing.JLabel poinsHis;
    private javax.swing.JLabel point1;
    private javax.swing.JLabel point2;
    private javax.swing.JLabel point3;
    private javax.swing.JLabel point4;
    private javax.swing.JLabel pointRemain;
    private javax.swing.JLabel position;
    private javax.swing.JLabel profile;
    private javax.swing.JLabel profile1;
    private javax.swing.JRadioButton radioEquip;
    private javax.swing.JRadioButton radioType;
    private javax.swing.JButton refreshRepairAdmin;
    private javax.swing.JPanel registerPage;
    private javax.swing.JPanel repairPage;
    private javax.swing.JPanel repairPage1;
    private javax.swing.JScrollPane resultScroll;
    private javax.swing.JScrollPane resultScroll1;
    private javax.swing.JLabel returnDateBorrowing;
    private javax.swing.JLabel s1;
    private javax.swing.JSeparator s10;
    private javax.swing.JSeparator s11;
    private javax.swing.JSeparator s3;
    private javax.swing.JSeparator s4;
    private javax.swing.JSeparator s5;
    private javax.swing.JSeparator s6;
    private javax.swing.JSeparator s7;
    private javax.swing.JSeparator s8;
    private javax.swing.JLabel s9;
    private javax.swing.JLabel sEmailB;
    private javax.swing.JLabel sEmailG;
    private javax.swing.JLabel sPassB;
    private javax.swing.JLabel sPassG;
    private javax.swing.JTextField search;
    private javax.swing.JTextField search1;
    private javax.swing.JPanel selectItemReturn;
    private javax.swing.JScrollPane sentRepairScroll;
    private javax.swing.JPanel sharingPage;
    private javax.swing.JPanel sharingPageTab;
    private javax.swing.JScrollPane sharingScroll;
    private javax.swing.JPanel sharingStep1;
    private javax.swing.JPanel sharingStep2;
    private javax.swing.JLabel showCp;
    private javax.swing.JPanel showNews;
    private javax.swing.JLabel showPass;
    private javax.swing.JPanel showlistNews;
    private javax.swing.JLabel showtitleInsertNews;
    private javax.swing.JButton signin;
    private javax.swing.JButton signout;
    private javax.swing.JButton signout1;
    private javax.swing.JLabel star1;
    private javax.swing.JLabel star2;
    private javax.swing.JLabel star3;
    private javax.swing.JButton submitBut;
    private javax.swing.JButton submitButSharing;
    private javax.swing.JButton submitButtonNews;
    private javax.swing.JButton submitPass;
    private javax.swing.JButton submitProfile;
    private javax.swing.JButton submitProfile1;
    private javax.swing.JButton submitSupport;
    private javax.swing.JButton submitUpdateNews;
    private javax.swing.JLabel support;
    private javax.swing.JLabel support1;
    private javax.swing.JPanel supportPage;
    private javax.swing.JPanel supportPage1;
    private javax.swing.JTextField surnameOfUser;
    private javax.swing.JTextField telProfile;
    private javax.swing.JTextField telProfile1;
    private javax.swing.JLabel textAsking;
    private javax.swing.JLabel textCpUse;
    private javax.swing.JLabel textNotHis;
    private javax.swing.JLabel textRecieving;
    private javax.swing.JLabel textRemain;
    private javax.swing.JLabel textRepair;
    private javax.swing.JLabel textYourCP;
    private javax.swing.JLabel timeLeftShow;
    private javax.swing.JLabel timeLeftShow1;
    private javax.swing.JPanel timePageF;
    private javax.swing.JPanel timePageT;
    private javax.swing.JLabel timer;
    private javax.swing.JLabel timer1;
    private javax.swing.JPanel timerPage;
    private javax.swing.JPanel timeupInside;
    private javax.swing.JPanel timeupPage;
    private javax.swing.JPanel timewatch;
    private javax.swing.JPanel timewatch1;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel titleAbout;
    private javax.swing.JLabel titleAbout1;
    private javax.swing.JLabel titleBackRepairUser;
    private javax.swing.JLabel titleCancleInsertSup;
    private javax.swing.JLabel titleConPass;
    private javax.swing.JLabel titleCongenProfile;
    private javax.swing.JLabel titleCongenProfile1;
    private javax.swing.JLabel titleContact;
    private javax.swing.JLabel titleContact1;
    private javax.swing.JLabel titleContactSupport;
    private javax.swing.JLabel titleContactSupport1;
    private javax.swing.JLabel titleContent;
    private javax.swing.JLabel titleCountCan;
    private javax.swing.JLabel titleDepartProfile;
    private javax.swing.JLabel titleDepartProfile1;
    private javax.swing.JLabel titleEmail;
    private javax.swing.JLabel titleEmailProfile;
    private javax.swing.JLabel titleEmailProfile1;
    private javax.swing.JLabel titleForgotPass;
    private javax.swing.JLabel titleGenderProfile;
    private javax.swing.JLabel titleGenderProfile1;
    private javax.swing.JLabel titleHistory;
    private javax.swing.JLabel titleInsertSupport;
    private javax.swing.JLabel titleItemCP;
    private javax.swing.JLabel titleItemCount;
    private javax.swing.JLabel titleItemId;
    private javax.swing.JLabel titleItemList;
    private javax.swing.JLabel titleItemListShow;
    private javax.swing.JLabel titleItemName;
    private javax.swing.JLabel titleItemPic;
    private javax.swing.JLabel titleItemTimeUp;
    private javax.swing.JLabel titleLastname;
    private javax.swing.JLabel titleNameProfile;
    private javax.swing.JLabel titleNameProfile1;
    private javax.swing.JLabel titleNameUser;
    private javax.swing.JLabel titleNewPass;
    private javax.swing.JLabel titleNews;
    private javax.swing.JLabel titleNewsPageUser;
    private javax.swing.JLabel titleNow;
    private javax.swing.JLabel titleNow1;
    private javax.swing.JLabel titleSetPloblem;
    private javax.swing.JLabel titleSignout;
    private javax.swing.JLabel titleSignout1;
    private javax.swing.JLabel titleSignup;
    private javax.swing.JLabel titleSupport;
    private javax.swing.JLabel titleSupport1;
    private javax.swing.JLabel titleTelProfile;
    private javax.swing.JLabel titleTelProfile1;
    private javax.swing.JLabel titleUserId;
    private javax.swing.JLabel titleUserId1;
    private javax.swing.JLabel titleWhat;
    private javax.swing.JLabel titlebBirthProfile;
    private javax.swing.JLabel titlebBirthProfile1;
    private javax.swing.JLabel titlenextFollingRepairUser;
    private javax.swing.JLabel titletopicNews;
    private javax.swing.JTextField topicNews;
    private javax.swing.JTextArea txtAreaNewsDetail;
    private javax.swing.JLabel txtDescription;
    private javax.swing.JLabel txtDescription1;
    private javax.swing.JTextField txtFieldNewsDescription;
    private javax.swing.JPanel userDetail;
    private javax.swing.JLabel userID;
    private javax.swing.JLabel userID1;
    private javax.swing.JPanel userProfilePage;
    private javax.swing.JPanel userProfilePage1;
    private javax.swing.JLabel waringInsertSupport;
    private javax.swing.JLabel warning;
    private javax.swing.JLabel warningProfile;
    private javax.swing.JLabel warningProfile1;
    // End of variables declaration//GEN-END:variables
}
