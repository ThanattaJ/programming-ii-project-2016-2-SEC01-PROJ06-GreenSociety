package bike_gui;

import bike.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
public class BikeUser extends javax.swing.JFrame {

    private Sharing sh = new Sharing();
    private Notification nf = new Notification();
    private int countBorrow[];
    private int detailTime[] = new int[6];

    public BikeUser() {
        initComponents();
        sh.amountOfItem();
        settingAvailableItem();
        setSpinnerCount();
        startSetVisible();
        setLocationRelativeTo(null);
        notificationNews();
    }

    public void startSetVisible() {
        cpPage.setVisible(false);
        titleSignout.setVisible(false);
        sharingStep1.setVisible(false);
        sharingStep2.setVisible(false);
        sharingStep3.setVisible(false);
        timePageT.setVisible(false);
        timePageF.setVisible(false);
        increaseTimePage.setVisible(false);
        textNotHis.setVisible(false);
        iconNotHis.setVisible(false);
        numNoti.setVisible(false);
        circleMini.setVisible(false);
        circleNoti.setVisible(false);
    }

    public void notificationNews() {
        int notiNew = nf.notiNews();
        if (nf.notiNews() > 0) {
            numNoti.setText(notiNew + "");
            numNoti.setVisible(true);
            circleMini.setVisible(true);
            circleNoti.setVisible(true);
        } else {
            numNoti.setVisible(false);
            circleMini.setVisible(false);
            circleNoti.setVisible(false);
        }
    }

    public int[] notiTime() {
        int increase[] = {0, 0, 0};
        int ans = JOptionPane.showConfirmDialog(this, "Time is running out. Do you want to add time?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (ans == JOptionPane.YES_OPTION) {
            String[] number = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
            JComboBox cbMin = new JComboBox(number);
            JComboBox cbSec = new JComboBox(number);
            JTextField textHour = new JTextField();
            textHour.setText("0");
            textHour.setFont(new java.awt.Font("Leelawadee", 0, 18));
            cbMin.setFont(new java.awt.Font("Leelawadee", 0, 18));
            cbSec.setFont(new java.awt.Font("Leelawadee", 0, 18));

            JPanel popup = new JPanel(new GridLayout(0, 1));
            popup.add(new JLabel("Hour"));
            popup.add(textHour);
            popup.add(new JLabel("Minutes"));
            popup.add(cbMin);
            popup.add(new JLabel("Secound"));
            popup.add(cbSec);
            int result = JOptionPane.showConfirmDialog(null, popup, "How much time you want to add ?", JOptionPane.PLAIN_MESSAGE);
            increase[0] = Integer.parseInt(textHour.getText());
            increase[1] = Integer.parseInt(cbMin.getSelectedItem().toString());
            increase[2] = Integer.parseInt(cbSec.getSelectedItem().toString());
            sh.decreseCPNoti();
        }
        return increase;
    }

    public void settingAvailableItem() {
        int[] tmp = sh.getAvailableItem();
        countBorrow = new int[tmp.length];
        availableUtility.setText("Available : " + tmp[0]);
        availableCrusier.setText("Available : " + tmp[1]);
        availableTouring.setText("Available : " + tmp[2]);
        availableHelmet.setText("Available : " + tmp[3]);
        availableKnee.setText("Available : " + tmp[4]);
    }

    public void setSpinnerCount() {
        int[] tmp = sh.getAvailableItem();
        numUtility.setModel(new SpinnerNumberModel(0, 0, tmp[0], 1));
        numCrusier.setModel(new SpinnerNumberModel(0, 0, tmp[1], 1));
        numTouring.setModel(new SpinnerNumberModel(0, 0, tmp[2], 1));
        numHelmet.setModel(new SpinnerNumberModel(0, 0, tmp[3], 1));
        numKnee.setModel(new SpinnerNumberModel(0, 0, tmp[4], 1));
    }

    public void insertBorrow() throws SelectItemException {
        int num[] = new int[countBorrow.length];
        num[0] = (int) numUtility.getValue();
        num[1] = (int) numCrusier.getValue();
        num[2] = (int) numTouring.getValue();
        num[3] = (int) numHelmet.getValue();
        num[4] = (int) numKnee.getValue();
        if (num[0] == 0 && num[1] == 0 && num[2] == 0 && num[3] == 0 && num[4] == 0) {
            SelectItemException slt = new SelectItemException();
            throw slt;
        }
        for (int i = 0; i < countBorrow.length; i++) {
            countBorrow[i] = num[i];
        }
        sh.setNumBikeUser(countBorrow);
    }

    public void setComboBoxDate() {
        Date dt = new Date();
        this.year.addItem(dt.getYear() + 1900 + "");
        this.year.addItem(dt.getYear() + 1901 + "");
        int month = dt.getMonth() + 1;
        int date = dt.getDate();
        int m[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        for (int i : m) {
            this.month.addItem(i + "");
        }
        this.month.setSelectedItem(month + "");
        this.date.setSelectedItem(date + "");
        this.hour.setSelectedItem(dt.getHours() + "");
        this.min.setSelectedItem(dt.getMinutes() + "");
        this.sec.setSelectedItem(dt.getSeconds() + "");
    }

    public void setBorrowDetail() {
        String tmp = "";
        for (int i = 0; i < countBorrow.length; i++) {
            if (countBorrow[i] != 0) {
                switch (i) {
                    case 0:
                        tmp += "- Utility Bike : " + countBorrow[i] + "<br>";
                        break;
                    case 1:
                        tmp += "- Cruiser Bike : " + countBorrow[i] + "<br>";
                        break;
                    case 2:
                        tmp += "- Touring Bike : " + countBorrow[i] + "<br>";
                        break;
                    case 3:
                        tmp += "- Bycycle Helmet : " + countBorrow[i] + "<br>";
                        break;
                    case 4:
                        tmp += "- Knee : " + countBorrow[i] + "<br>";
                        break;
                }
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
        settingAvailableItem();
        setSpinnerCount();
        for (int i = 0; i < countBorrow.length; i++) {
            countBorrow[i] = 0;
        }
        for (int i = 0; i < detailTime.length; i++) {
            detailTime[i] = 0;
        }
        sh.enterTime(detailTime[0], detailTime[1], detailTime[2], detailTime[3], detailTime[4], detailTime[5]);
        detailData.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainUser = new javax.swing.JPanel();
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
        timer = new javax.swing.JLabel();
        support = new javax.swing.JLabel();
        menu = new javax.swing.JLabel();
        arrowDownIcon = new javax.swing.JLabel();
        circleMini = new javax.swing.JLabel();
        titleSignout = new javax.swing.JLabel();
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
        jSeparator1 = new javax.swing.JSeparator();
        numNoti = new javax.swing.JLabel();
        circleNoti = new javax.swing.JLabel();
        iconNoti = new javax.swing.JLabel();
        sharingStep3 = new javax.swing.JPanel();
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
        returnBut = new javax.swing.JButton();
        s3 = new javax.swing.JLabel();
        increaseTimePage = new javax.swing.JPanel();
        timeCon1 = new javax.swing.JPanel();
        iconClock1 = new javax.swing.JLabel();
        min1 = new javax.swing.JComboBox<>();
        sec1 = new javax.swing.JComboBox<>();
        titleMin = new javax.swing.JLabel();
        titleHour = new javax.swing.JLabel();
        titleSec = new javax.swing.JLabel();
        hour1 = new javax.swing.JTextField();
        titleTime4 = new javax.swing.JLabel();
        submitIncrease = new javax.swing.JButton();
        cancleIncrease = new javax.swing.JButton();
        backIncrease = new javax.swing.JButton();
        timePageF = new javax.swing.JPanel();
        timePageT = new javax.swing.JPanel();
        nowBorrowTime = new javax.swing.JPanel();
        titleNow = new javax.swing.JLabel();
        timeLeftShow = new javax.swing.JLabel();
        borrowTime = new javax.swing.JLabel();
        iconClockBig = new javax.swing.JLabel();
        increaseBut = new javax.swing.JButton();
        textNotHis = new javax.swing.JLabel();
        hisBorrow1 = new javax.swing.JPanel();
        borrowTimeHis1 = new javax.swing.JLabel();
        iconClockHis = new javax.swing.JLabel();
        hisBorrow2 = new javax.swing.JPanel();
        borrowTime2 = new javax.swing.JLabel();
        iconClockHis1 = new javax.swing.JLabel();
        iconNotHis = new javax.swing.JLabel();
        sharingStep1 = new javax.swing.JPanel();
        nextStep = new javax.swing.JButton();
        s1 = new javax.swing.JLabel();
        isCp = new javax.swing.JLabel();
        crusierCon = new javax.swing.JPanel();
        picCrusier = new javax.swing.JLabel();
        numCrusier = new javax.swing.JSpinner();
        availableCrusier = new javax.swing.JLabel();
        touringCon = new javax.swing.JPanel();
        picTouring = new javax.swing.JLabel();
        numTouring = new javax.swing.JSpinner();
        availableTouring = new javax.swing.JLabel();
        helmetCon = new javax.swing.JPanel();
        picHelmet = new javax.swing.JLabel();
        numHelmet = new javax.swing.JSpinner();
        availableHelmet = new javax.swing.JLabel();
        utilityCon = new javax.swing.JPanel();
        picUtility = new javax.swing.JLabel();
        numUtility = new javax.swing.JSpinner();
        availableUtility = new javax.swing.JLabel();
        kneeCon = new javax.swing.JPanel();
        picKnee = new javax.swing.JLabel();
        numKnee = new javax.swing.JSpinner();
        availableKnee = new javax.swing.JLabel();
        sharingStep2 = new javax.swing.JPanel();
        nextStep1 = new javax.swing.JButton();
        backStep = new javax.swing.JButton();
        timeCon = new javax.swing.JPanel();
        iconClock = new javax.swing.JLabel();
        titleTime = new javax.swing.JLabel();
        slash03 = new javax.swing.JLabel();
        slash04 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        hour = new javax.swing.JComboBox<>();
        min = new javax.swing.JComboBox<>();
        sec = new javax.swing.JComboBox<>();
        dateCon = new javax.swing.JPanel();
        iconCalendar = new javax.swing.JLabel();
        titleReturn = new javax.swing.JLabel();
        slash01 = new javax.swing.JLabel();
        slash02 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        date = new javax.swing.JComboBox<>();
        month = new javax.swing.JComboBox<>();
        year = new javax.swing.JComboBox<>();
        s2 = new javax.swing.JLabel();
        cpPage = new javax.swing.JPanel();
        showCp = new javax.swing.JLabel();
        circleCp = new javax.swing.JLabel();
        History = new javax.swing.JPanel();
        detailBoxHistory = new javax.swing.JPanel();
        poinsHis = new javax.swing.JLabel();
        actionHis = new javax.swing.JLabel();
        titleHistory = new javax.swing.JLabel();
        point4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Green Society");
        setAutoRequestFocus(false);
        setFocusable(false);
        setLocation(new java.awt.Point(0, 0));
        setSize(new java.awt.Dimension(980, 540));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        barSearch.setBackground(new java.awt.Color(13, 24, 35));
        barSearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        barSearch.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, 120, 40));

        iconSearch.setFont(new java.awt.Font("Leelawadee UI", 0, 20)); // NOI18N
        iconSearch.setForeground(new java.awt.Color(255, 255, 255));
        iconSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/magnifying-glass.png"))); // NOI18N
        iconSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconSearchMouseClicked(evt);
            }
        });
        barSearch.add(iconSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 50, 50));

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
        search.setBorder(null);
        search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFocusLost(evt);
            }
        });
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        barSearch.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 120, 30));

        mainUser.add(barSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 900, 60));

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
        menuBar.add(news, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 140, 20));

        bikeRepair.setBackground(new java.awt.Color(0, 0, 0));
        bikeRepair.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        bikeRepair.setForeground(new java.awt.Color(255, 255, 255));
        bikeRepair.setText("     Bike Repairing");
        bikeRepair.setToolTipText("");
        bikeRepair.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        menuBar.add(bikeRepair, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 140, 20));

        canCounter.setBackground(new java.awt.Color(0, 0, 0));
        canCounter.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        canCounter.setForeground(new java.awt.Color(255, 255, 255));
        canCounter.setText("     Can Counter");
        canCounter.setToolTipText("");
        canCounter.setVerticalAlignment(javax.swing.SwingConstants.TOP);
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
        menuBar.add(history, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 140, 20));

        profile.setBackground(new java.awt.Color(0, 0, 0));
        profile.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        profile.setForeground(new java.awt.Color(255, 255, 255));
        profile.setText("     PROFILE");
        profile.setToolTipText("");
        menuBar.add(profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 140, 30));

        timer.setBackground(new java.awt.Color(0, 0, 0));
        timer.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        timer.setForeground(new java.awt.Color(255, 255, 255));
        timer.setText("     Timer");
        timer.setToolTipText("");
        timer.setVerticalAlignment(javax.swing.SwingConstants.TOP);
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
        menuBar.add(support, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 140, 20));

        menu.setBackground(new java.awt.Color(0, 0, 0));
        menu.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        menu.setForeground(new java.awt.Color(255, 255, 255));
        menu.setText("MENU");
        menu.setToolTipText("");
        menuBar.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 50, 30));

        arrowDownIcon.setBackground(new java.awt.Color(0, 0, 0));
        arrowDownIcon.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        arrowDownIcon.setForeground(new java.awt.Color(255, 255, 255));
        arrowDownIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        arrowDownIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/chevron-arrow-down.png"))); // NOI18N
        arrowDownIcon.setToolTipText("");
        menuBar.add(arrowDownIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 40, 30));

        circleMini.setBackground(new java.awt.Color(0, 0, 0));
        circleMini.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        circleMini.setForeground(new java.awt.Color(255, 255, 255));
        circleMini.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/circle_mini.png"))); // NOI18N
        circleMini.setToolTipText("");
        menuBar.add(circleMini, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 40, 20));

        titleSignout.setBackground(new java.awt.Color(0, 0, 0));
        titleSignout.setFont(new java.awt.Font("Leelawadee", 0, 13)); // NOI18N
        titleSignout.setForeground(new java.awt.Color(255, 255, 255));
        titleSignout.setText("     SIGN OUT");
        titleSignout.setToolTipText("");
        menuBar.add(titleSignout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 140, 30));

        backMenuBar.add(menuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 480));

        mainUser.add(backMenuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 150, 480));

        barUser.setBackground(new java.awt.Color(19, 175, 248));
        barUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/man.png"))); // NOI18N
        barUser.add(pic, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        name.setBackground(new java.awt.Color(0, 0, 0));
        name.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        name.setForeground(new java.awt.Color(13, 24, 35));
        name.setText("NAME  SURNAME");
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

        mainUser.add(barUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 200, 60));

        backBarUser.setBackground(new java.awt.Color(55, 200, 255));
        backBarUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        mainUser.add(backBarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 210, 60));

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

        mainUser.add(backBarIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 540));

        barNoti.setBackground(new java.awt.Color(13, 24, 35));
        barNoti.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setBackground(new java.awt.Color(55, 200, 255));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        barNoti.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 10, 60));

        numNoti.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        numNoti.setForeground(new java.awt.Color(255, 255, 255));
        numNoti.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numNoti.setText("1");
        barNoti.add(numNoti, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 40, 30));

        circleNoti.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        circleNoti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/circle.png"))); // NOI18N
        barNoti.add(circleNoti, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 40, 30));

        iconNoti.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconNoti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/notifications-button.png"))); // NOI18N
        barNoti.add(iconNoti, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        mainUser.add(barNoti, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 770, 60));

        sharingStep3.setBackground(new java.awt.Color(25, 41, 65));
        sharingStep3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textYourCP.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        textYourCP.setForeground(new java.awt.Color(255, 255, 255));
        textYourCP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textYourCP.setText("Your CP :");
        sharingStep3.add(textYourCP, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 80, 20));

        cpUser.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        cpUser.setForeground(new java.awt.Color(255, 255, 255));
        cpUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cpUser.setText("1000");
        sharingStep3.add(cpUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 110, 20));
        sharingStep3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 110, 10));

        textRemain.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        textRemain.setForeground(new java.awt.Color(255, 255, 255));
        textRemain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textRemain.setText("Remaining Points : ");
        sharingStep3.add(textRemain, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 170, 40));

        point1.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        point1.setForeground(new java.awt.Color(255, 255, 255));
        point1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        point1.setText("Points");
        sharingStep3.add(point1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 80, 20));

        textCpUse.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        textCpUse.setForeground(new java.awt.Color(255, 255, 255));
        textCpUse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textCpUse.setText("CP Use :");
        sharingStep3.add(textCpUse, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 80, 20));
        sharingStep3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 110, 10));

        cpUse.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        cpUse.setForeground(new java.awt.Color(255, 255, 255));
        cpUse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cpUse.setText("160");
        sharingStep3.add(cpUse, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 110, 20));

        point2.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        point2.setForeground(new java.awt.Color(255, 255, 255));
        point2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        point2.setText("Points");
        sharingStep3.add(point2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, 80, 20));

        pointRemain.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        pointRemain.setForeground(new java.awt.Color(255, 255, 255));
        pointRemain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pointRemain.setText("840");
        pointRemain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        sharingStep3.add(pointRemain, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 60, 40));

        point3.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        point3.setForeground(new java.awt.Color(255, 255, 255));
        point3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        point3.setText("Points");
        sharingStep3.add(point3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 100, 40));

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

        sharingStep3.add(detailBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 460, 240));

        backStep1.setBackground(new java.awt.Color(25, 41, 65));
        backStep1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/left arrow.png"))); // NOI18N
        backStep1.setContentAreaFilled(false);
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
        sharingStep3.add(backStep1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 60, 60));

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
        sharingStep3.add(cancleBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, 60, 50));

        borrowBut.setBackground(new java.awt.Color(25, 41, 65));
        borrowBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/BorrowBut.png"))); // NOI18N
        borrowBut.setContentAreaFilled(false);
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
        sharingStep3.add(borrowBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 280, 140, 60));

        returnBut.setBackground(new java.awt.Color(25, 41, 65));
        returnBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/ReturnBut.png"))); // NOI18N
        returnBut.setContentAreaFilled(false);
        returnBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                returnButMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                returnButMouseExited(evt);
            }
        });
        returnBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButActionPerformed(evt);
            }
        });
        sharingStep3.add(returnBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 340, 140, 60));

        s3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        s3.setForeground(new java.awt.Color(255, 255, 255));
        s3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        s3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/Step3.png"))); // NOI18N
        sharingStep3.add(s3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 40));

        mainUser.add(sharingStep3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 421));

        increaseTimePage.setBackground(new java.awt.Color(25, 41, 65));
        increaseTimePage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        timeCon1.setBackground(new java.awt.Color(65, 68, 71));
        timeCon1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "How much time you want to add ?", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Leelawadee", 0, 24), new java.awt.Color(55, 200, 255))); // NOI18N
        timeCon1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconClock1.setFont(new java.awt.Font("Leelawadee UI", 0, 36)); // NOI18N
        iconClock1.setForeground(new java.awt.Color(255, 255, 255));
        iconClock1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconClock1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/hourglass.png"))); // NOI18N
        timeCon1.add(iconClock1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 170, 200));

        min1.setBackground(new java.awt.Color(19, 175, 248));
        min1.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        min1.setForeground(new java.awt.Color(255, 255, 255));
        min1.setMaximumRowCount(5);
        min1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        min1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        min1.setNextFocusableComponent(sec);
        timeCon1.add(min1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, -1, 30));

        sec1.setBackground(new java.awt.Color(19, 175, 248));
        sec1.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        sec1.setForeground(new java.awt.Color(255, 255, 255));
        sec1.setMaximumRowCount(6);
        sec1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        sec1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        timeCon1.add(sec1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, -1, -1));

        titleMin.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        titleMin.setForeground(new java.awt.Color(55, 200, 255));
        titleMin.setText("MINUTES : ");
        timeCon1.add(titleMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 120, 70));

        titleHour.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        titleHour.setForeground(new java.awt.Color(55, 200, 255));
        titleHour.setText("HOUR :");
        timeCon1.add(titleHour, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 100, 60));

        titleSec.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        titleSec.setForeground(new java.awt.Color(55, 200, 255));
        titleSec.setText("SECOUNDS : ");
        timeCon1.add(titleSec, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 160, 50));

        hour1.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        hour1.setForeground(new java.awt.Color(204, 204, 204));
        hour1.setText("0");
        hour1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hour1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hour1FocusLost(evt);
            }
        });
        timeCon1.add(hour1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 110, 40));

        increaseTimePage.add(timeCon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 450, 250));

        titleTime4.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        titleTime4.setForeground(new java.awt.Color(255, 255, 255));
        titleTime4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleTime4.setText("INCREASE TIME");
        increaseTimePage.add(titleTime4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 220, 40));

        submitIncrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submit.png"))); // NOI18N
        submitIncrease.setContentAreaFilled(false);
        submitIncrease.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitIncreaseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitIncreaseMouseExited(evt);
            }
        });
        submitIncrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitIncreaseActionPerformed(evt);
            }
        });
        increaseTimePage.add(submitIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 355, 140, 50));

        cancleIncrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleBut.png"))); // NOI18N
        cancleIncrease.setContentAreaFilled(false);
        cancleIncrease.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancleIncreaseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancleIncreaseMouseExited(evt);
            }
        });
        cancleIncrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancleIncreaseActionPerformed(evt);
            }
        });
        increaseTimePage.add(cancleIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 350, 140, 60));

        backIncrease.setBackground(new java.awt.Color(25, 41, 65));
        backIncrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/left arrow.png"))); // NOI18N
        backIncrease.setContentAreaFilled(false);
        backIncrease.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backIncreaseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backIncreaseMouseExited(evt);
            }
        });
        backIncrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backIncreaseActionPerformed(evt);
            }
        });
        increaseTimePage.add(backIncrease, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 60, 60));

        mainUser.add(increaseTimePage, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 421));

        timePageF.setBackground(new java.awt.Color(25, 41, 65));
        timePageF.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        mainUser.add(timePageF, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 420));

        timePageT.setBackground(new java.awt.Color(25, 41, 65));
        timePageT.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nowBorrowTime.setEnabled(false);
        nowBorrowTime.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleNow.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        titleNow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleNow.setText("Now:");
        nowBorrowTime.add(titleNow, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 70, 40));

        timeLeftShow.setFont(new java.awt.Font("Leelawadee", 0, 30)); // NOI18N
        timeLeftShow.setForeground(new java.awt.Color(255, 51, 51));
        timeLeftShow.setText("1 Hour 31 Minutes 30 Seconds");
        nowBorrowTime.add(timeLeftShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 450, 40));

        borrowTime.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        borrowTime.setText("Start:    25 / 2 / 2017  15 : 00     End:    25 / 2 / 2017  17 : 00");
        nowBorrowTime.add(borrowTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 490, -1));

        iconClockBig.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconClockBig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/clockBig.png"))); // NOI18N
        nowBorrowTime.add(iconClockBig, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 130));

        timePageT.add(nowBorrowTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 660, 130));

        increaseBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/increaseTime.png"))); // NOI18N
        increaseBut.setContentAreaFilled(false);
        increaseBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                increaseButMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                increaseButMouseExited(evt);
            }
        });
        increaseBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseButActionPerformed(evt);
            }
        });
        timePageT.add(increaseBut, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 340, 100, 80));

        textNotHis.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N
        textNotHis.setForeground(new java.awt.Color(255, 255, 255));
        textNotHis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textNotHis.setText("ยังไม่มีประวัติการยืม");
        timePageT.add(textNotHis, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 180, 40));

        hisBorrow1.setBackground(new java.awt.Color(102, 102, 102));
        hisBorrow1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        borrowTimeHis1.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        borrowTimeHis1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        borrowTimeHis1.setText("Start:    25 / 2 / 2017  15 : 00     End:    25 / 2 / 2017  17 : 00");
        hisBorrow1.add(borrowTimeHis1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 490, 50));

        iconClockHis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconClockHis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/clock.png"))); // NOI18N
        hisBorrow1.add(iconClockHis, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 50, 50));

        timePageT.add(hisBorrow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 660, 50));

        hisBorrow2.setBackground(new java.awt.Color(102, 102, 102));
        hisBorrow2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        borrowTime2.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        borrowTime2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        borrowTime2.setText("Start:    25 / 2 / 2017  15 : 00     End:    25 / 2 / 2017  17 : 00");
        hisBorrow2.add(borrowTime2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 490, 50));

        iconClockHis1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconClockHis1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/clock.png"))); // NOI18N
        hisBorrow2.add(iconClockHis1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 50, 50));

        timePageT.add(hisBorrow2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 660, 50));

        iconNotHis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/notHis.png"))); // NOI18N
        timePageT.add(iconNotHis, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        mainUser.add(timePageT, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 421));

        sharingStep1.setBackground(new java.awt.Color(25, 41, 65));
        sharingStep1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nextStep.setBackground(new java.awt.Color(25, 41, 65));
        nextStep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/right arrow.png"))); // NOI18N
        nextStep.setContentAreaFilled(false);
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
        sharingStep1.add(nextStep, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 360, 60, 60));

        s1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        s1.setForeground(new java.awt.Color(255, 255, 255));
        s1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        s1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/Step1.png"))); // NOI18N
        sharingStep1.add(s1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 40));

        isCp.setFont(new java.awt.Font("Leelawadee", 0, 16)); // NOI18N
        isCp.setForeground(new java.awt.Color(255, 255, 255));
        isCp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        isCp.setText("Your CP : 1000 Points ");
        sharingStep1.add(isCp, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 180, 30));

        crusierCon.setBackground(new java.awt.Color(13, 24, 35));
        crusierCon.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Crusier Bike (125 CP)", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Leelawadee", 0, 18), new java.awt.Color(19, 175, 248))); // NOI18N
        crusierCon.setToolTipText("");
        crusierCon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        picCrusier.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picCrusier.setIcon(new javax.swing.ImageIcon("D:\\SIT\\Than\\INT105\\Work\\Project\\Bike\\src\\bike_gui\\picture\\crusier bike.png")); // NOI18N
        crusierCon.add(picCrusier, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 25, 140, 140));

        numCrusier.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        numCrusier.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        numCrusier.setBorder(null);
        numCrusier.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        numCrusier.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        numCrusier.setPreferredSize(new java.awt.Dimension(60, 25));
        crusierCon.add(numCrusier, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

        availableCrusier.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        availableCrusier.setForeground(new java.awt.Color(102, 204, 255));
        availableCrusier.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        availableCrusier.setText("Available : 100");
        crusierCon.add(availableCrusier, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 145, 100, 20));

        sharingStep1.add(crusierCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 220, 170));

        touringCon.setBackground(new java.awt.Color(13, 24, 35));
        touringCon.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Touring Bike (150 CP)", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Leelawadee", 0, 18), new java.awt.Color(19, 175, 248))); // NOI18N
        touringCon.setToolTipText("");
        touringCon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        picTouring.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picTouring.setIcon(new javax.swing.ImageIcon("D:\\SIT\\Than\\INT105\\Work\\Project\\Bike\\src\\bike_gui\\picture\\touring bike.PNG")); // NOI18N
        touringCon.add(picTouring, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 25, 140, 140));

        numTouring.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        numTouring.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        numTouring.setBorder(null);
        numTouring.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        numTouring.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        numTouring.setName(""); // NOI18N
        numTouring.setPreferredSize(new java.awt.Dimension(60, 25));
        touringCon.add(numTouring, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

        availableTouring.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        availableTouring.setForeground(new java.awt.Color(102, 204, 255));
        availableTouring.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        availableTouring.setText("Available : 100");
        touringCon.add(availableTouring, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 145, 100, 20));

        sharingStep1.add(touringCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 220, 170));

        helmetCon.setBackground(new java.awt.Color(13, 24, 35));
        helmetCon.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Bicycle Helmets (60 CP)", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Leelawadee", 0, 18), new java.awt.Color(19, 175, 248))); // NOI18N
        helmetCon.setToolTipText("");
        helmetCon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        picHelmet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picHelmet.setIcon(new javax.swing.ImageIcon("D:\\SIT\\Than\\INT105\\Work\\Project\\Bike\\src\\bike_gui\\picture\\bicycle helmets.png")); // NOI18N
        helmetCon.add(picHelmet, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 25, 140, 140));

        numHelmet.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        numHelmet.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        numHelmet.setBorder(null);
        numHelmet.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        numHelmet.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        numHelmet.setPreferredSize(new java.awt.Dimension(60, 25));
        helmetCon.add(numHelmet, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

        availableHelmet.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        availableHelmet.setForeground(new java.awt.Color(102, 204, 255));
        availableHelmet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        availableHelmet.setText("Available : 100");
        helmetCon.add(availableHelmet, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 145, 100, 20));

        sharingStep1.add(helmetCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 220, 170));

        utilityCon.setBackground(new java.awt.Color(13, 24, 35));
        utilityCon.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Utility Bike (100 CP)", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Leelawadee", 0, 18), new java.awt.Color(19, 175, 248))); // NOI18N
        utilityCon.setToolTipText("");
        utilityCon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        picUtility.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picUtility.setIcon(new javax.swing.ImageIcon("D:\\SIT\\Than\\INT105\\Work\\Project\\Bike\\src\\bike_gui\\picture\\utility bike.png")); // NOI18N
        utilityCon.add(picUtility, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 25, 140, 140));

        numUtility.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        numUtility.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        numUtility.setBorder(null);
        numUtility.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        numUtility.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        numUtility.setPreferredSize(new java.awt.Dimension(60, 25));
        utilityCon.add(numUtility, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

        availableUtility.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        availableUtility.setForeground(new java.awt.Color(102, 204, 255));
        availableUtility.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        availableUtility.setText("Available : 10");
        utilityCon.add(availableUtility, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 145, 100, 20));

        sharingStep1.add(utilityCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 220, 170));

        kneeCon.setBackground(new java.awt.Color(13, 24, 35));
        kneeCon.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true), "Knee (80 CP)", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Leelawadee", 0, 18), new java.awt.Color(19, 175, 248))); // NOI18N
        kneeCon.setToolTipText("");
        kneeCon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        picKnee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picKnee.setIcon(new javax.swing.ImageIcon("D:\\SIT\\Than\\INT105\\Work\\Project\\Bike\\src\\bike_gui\\picture\\knee.png")); // NOI18N
        kneeCon.add(picKnee, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 25, 140, 140));

        numKnee.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        numKnee.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        numKnee.setBorder(null);
        numKnee.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        numKnee.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        numKnee.setPreferredSize(new java.awt.Dimension(60, 25));
        kneeCon.add(numKnee, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

        availableKnee.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        availableKnee.setForeground(new java.awt.Color(102, 204, 255));
        availableKnee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        availableKnee.setText("Available : 100");
        kneeCon.add(availableKnee, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 145, 100, 20));

        sharingStep1.add(kneeCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 240, 220, 170));

        mainUser.add(sharingStep1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 421));

        sharingStep2.setBackground(new java.awt.Color(25, 41, 65));
        sharingStep2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nextStep1.setBackground(new java.awt.Color(25, 41, 65));
        nextStep1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/right arrow.png"))); // NOI18N
        nextStep1.setContentAreaFilled(false);
        nextStep1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nextStep1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nextStep1MouseExited(evt);
            }
        });
        nextStep1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextStep1ActionPerformed(evt);
            }
        });
        sharingStep2.add(nextStep1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 360, 60, 60));

        backStep.setBackground(new java.awt.Color(25, 41, 65));
        backStep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/left arrow.png"))); // NOI18N
        backStep.setContentAreaFilled(false);
        backStep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backStepMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backStepMouseExited(evt);
            }
        });
        backStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backStepActionPerformed(evt);
            }
        });
        sharingStep2.add(backStep, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 60, 60));

        timeCon.setBackground(new java.awt.Color(65, 68, 71));
        timeCon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconClock.setFont(new java.awt.Font("Leelawadee UI", 0, 36)); // NOI18N
        iconClock.setForeground(new java.awt.Color(255, 255, 255));
        iconClock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconClock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/alarm-clock.png"))); // NOI18N
        timeCon.add(iconClock, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 145, 130));

        titleTime.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        titleTime.setForeground(new java.awt.Color(55, 200, 255));
        titleTime.setText("Time :");
        timeCon.add(titleTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 11, 70, -1));

        slash03.setFont(new java.awt.Font("Leelawadee", 0, 48)); // NOI18N
        slash03.setForeground(new java.awt.Color(255, 255, 255));
        slash03.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slash03.setText(":");
        timeCon.add(slash03, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 50, 120));

        slash04.setFont(new java.awt.Font("Leelawadee", 0, 48)); // NOI18N
        slash04.setForeground(new java.awt.Color(255, 255, 255));
        slash04.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slash04.setText(":");
        timeCon.add(slash04, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 50, 120));

        jSeparator4.setBackground(new java.awt.Color(55, 200, 255));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSeparator4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        timeCon.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(143, 0, -1, 130));

        hour.setBackground(new java.awt.Color(19, 175, 248));
        hour.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        hour.setForeground(new java.awt.Color(255, 255, 255));
        hour.setMaximumRowCount(5);
        hour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        hour.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        hour.setNextFocusableComponent(min);
        timeCon.add(hour, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, 30));

        min.setBackground(new java.awt.Color(19, 175, 248));
        min.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        min.setForeground(new java.awt.Color(255, 255, 255));
        min.setMaximumRowCount(5);
        min.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        min.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        min.setNextFocusableComponent(sec);
        timeCon.add(min, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, 30));

        sec.setBackground(new java.awt.Color(19, 175, 248));
        sec.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        sec.setForeground(new java.awt.Color(255, 255, 255));
        sec.setMaximumRowCount(6);
        sec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        sec.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        timeCon.add(sec, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, -1, -1));

        sharingStep2.add(timeCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 670, 130));

        dateCon.setBackground(new java.awt.Color(65, 68, 71));
        dateCon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconCalendar.setFont(new java.awt.Font("Leelawadee UI", 0, 36)); // NOI18N
        iconCalendar.setForeground(new java.awt.Color(255, 255, 255));
        iconCalendar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconCalendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/calendar.png"))); // NOI18N
        dateCon.add(iconCalendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 130));

        titleReturn.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        titleReturn.setForeground(new java.awt.Color(55, 200, 255));
        titleReturn.setText("Return Date :  ");
        dateCon.add(titleReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 11, 329, -1));

        slash01.setFont(new java.awt.Font("Leelawadee", 0, 54)); // NOI18N
        slash01.setForeground(new java.awt.Color(255, 255, 255));
        slash01.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slash01.setText("/");
        dateCon.add(slash01, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 2, 50, 130));

        slash02.setFont(new java.awt.Font("Leelawadee", 0, 54)); // NOI18N
        slash02.setForeground(new java.awt.Color(255, 255, 255));
        slash02.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slash02.setText("/");
        dateCon.add(slash02, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 50, 130));

        jSeparator3.setBackground(new java.awt.Color(55, 200, 255));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSeparator3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dateCon.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 0, -1, 139));

        date.setBackground(new java.awt.Color(19, 175, 248));
        date.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        date.setForeground(new java.awt.Color(255, 255, 255));
        date.setMaximumRowCount(5);
        date.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        date.setNextFocusableComponent(month);
        dateCon.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, 30));

        month.setBackground(new java.awt.Color(19, 175, 248));
        month.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        month.setForeground(new java.awt.Color(255, 255, 255));
        month.setMaximumRowCount(5);
        month.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        month.setNextFocusableComponent(year);
        month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthActionPerformed(evt);
            }
        });
        dateCon.add(month, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, -1, 30));

        year.setBackground(new java.awt.Color(19, 175, 248));
        year.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        year.setForeground(new java.awt.Color(255, 255, 255));
        year.setMaximumRowCount(12);
        year.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        year.setName(""); // NOI18N
        year.setNextFocusableComponent(hour);
        dateCon.add(year, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, -1, -1));

        sharingStep2.add(dateCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 670, 130));

        s2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        s2.setForeground(new java.awt.Color(255, 255, 255));
        s2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        s2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/Step2.png"))); // NOI18N
        sharingStep2.add(s2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 40));

        mainUser.add(sharingStep2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 421));

        cpPage.setBackground(new java.awt.Color(25, 41, 65));
        cpPage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        showCp.setFont(new java.awt.Font("Leelawadee", 0, 80)); // NOI18N
        showCp.setForeground(new java.awt.Color(255, 255, 255));
        showCp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showCp.setText("200");
        cpPage.add(showCp, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 310, 260));

        circleCp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        circleCp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/oval.png"))); // NOI18N
        cpPage.add(circleCp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 370, 350));

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

        cpPage.add(History, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 270, 110));

        point4.setFont(new java.awt.Font("Leelawadee", 0, 40)); // NOI18N
        point4.setForeground(new java.awt.Color(255, 255, 255));
        point4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        point4.setText("Points");
        cpPage.add(point4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 310, 210));

        mainUser.add(cpPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 770, 421));

        getContentPane().add(mainUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextStepActionPerformed
        try {
            insertBorrow();
            sh.borrowStep();
            sh.setPointUse();
            sh.getCp().selectCP();
            (sh.getCp()).checkCp();
            setComboBoxDate();
            sharingStep1.setVisible(false);
            sharingStep2.setVisible(true);
        } catch (CanCountException cce) {
            JOptionPane.showMessageDialog(this, cce.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SelectItemException s) {
            JOptionPane.showMessageDialog(this, s.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_nextStepActionPerformed

    private void monthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthActionPerformed
        int m = Integer.parseInt(month.getSelectedItem().toString());
        int y = Integer.parseInt(year.getSelectedItem().toString());
        if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
            int d[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
            date.removeAllItems();
            for (int i : d) {
                date.addItem(i + "");
            }
        } else if (m == 2) {
            if ((y % 4 == 0) && y % 100 != 0) {
                int d[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29};
                date.removeAllItems();
                for (int i : d) {
                    date.addItem(i + "");
                }
            } else {
                int d[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28};
                date.removeAllItems();
                for (int i : d) {
                    date.addItem(i + "");
                }
            }
        } else if (m == 4 || m == 6 || m == 9 || m == 11) {
            int d[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
            date.removeAllItems();
            for (int i : d) {
                date.addItem(i + "");
            }
        }
    }//GEN-LAST:event_monthActionPerformed

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

    private void iconSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconSearchMouseClicked
        search.requestFocusInWindow();
    }//GEN-LAST:event_iconSearchMouseClicked

    private void backStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backStepActionPerformed
        sh.editStep();
        settingAvailableItem();
        sharingStep1.setVisible(true);
        sharingStep2.setVisible(false);
        for (int i = 0; i < countBorrow.length; i++) {
            countBorrow[i] = 0;
        }
    }//GEN-LAST:event_backStepActionPerformed

    private void nextStepMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextStepMouseEntered
        nextStep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/right arrow click.png")));
    }//GEN-LAST:event_nextStepMouseEntered

    private void nextStepMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextStepMouseExited
        nextStep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/right arrow.png")));
    }//GEN-LAST:event_nextStepMouseExited

    private void nextStep1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextStep1MouseEntered
        nextStep1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/right arrow click.png")));
    }//GEN-LAST:event_nextStep1MouseEntered

    private void nextStep1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextStep1MouseExited
        nextStep1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/right arrow.png")));
    }//GEN-LAST:event_nextStep1MouseExited

    private void backStepMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backStepMouseEntered
        backStep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/left arrow click.png")));
    }//GEN-LAST:event_backStepMouseEntered

    private void backStep1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backStep1MouseEntered
        backStep1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/left arrow click.png")));
    }//GEN-LAST:event_backStep1MouseEntered

    private void backStep1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backStep1MouseExited
        backStep1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/left arrow.png")));
    }//GEN-LAST:event_backStep1MouseExited

    private void backStep1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backStep1ActionPerformed
        sharingStep2.setVisible(true);
        sharingStep3.setVisible(false);
        for (int i = 0; i < detailTime.length; i++) {
            detailTime[i] = 0;
        }
        sh.enterTime(detailTime[0], detailTime[1], detailTime[2], detailTime[3], detailTime[4], detailTime[5]);
        detailData.setText("");
    }//GEN-LAST:event_backStep1ActionPerformed

    private void cancleButMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleButMouseEntered
        cancleBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleClick.png")));
        cancleBut.getToolTipText();
    }//GEN-LAST:event_cancleButMouseEntered

    private void cancleButMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleButMouseExited
        cancleBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancle.png")));
    }//GEN-LAST:event_cancleButMouseExited
    private void cancleButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleButActionPerformed
        sharingStep1.setVisible(true);
        sharingStep2.setVisible(false);
        sharingStep3.setVisible(false);
        for (int i = 0; i < detailTime.length; i++) {
            detailTime[i] = 0;
        }
        sh.enterTime(detailTime[0], detailTime[1], detailTime[2], detailTime[3], detailTime[4], detailTime[5]);
        sh.editStep();
        settingAvailableItem();
        for (int i = 0; i < countBorrow.length; i++) {
            countBorrow[i] = 0;
        }
        setSpinnerCount();
        detailData.setText("");
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
            returnBut.setEnabled(true);
            borrowBut.setEnabled(false);
            backStep1.setEnabled(false);
            try {
                sh.startBorrow();
            } catch (InterruptedException ie) {
                JOptionPane.showMessageDialog(this, ie.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_borrowButActionPerformed

    private void nextStep1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextStep1ActionPerformed
        sharingStep2.setVisible(false);
        sharingStep3.setVisible(true);
        detailTime[0] = Integer.parseInt(date.getSelectedItem().toString());
        detailTime[1] = Integer.parseInt(month.getSelectedItem().toString());
        detailTime[2] = Integer.parseInt(year.getSelectedItem().toString());
        detailTime[3] = Integer.parseInt(hour.getSelectedItem().toString());
        detailTime[4] = Integer.parseInt(min.getSelectedItem().toString());
        detailTime[5] = Integer.parseInt(sec.getSelectedItem().toString());
        sh.enterTime(detailTime[0], detailTime[1], detailTime[2], detailTime[3], detailTime[4], detailTime[5]);

        setBorrowDetail();
        int point = (sh.getCp()).getCp();
        cpUser.setText(point + "");
        int pointUse = (sh.getCp()).getCpUse();
        cpUse.setText(pointUse + "");
        pointRemain.setText((point - pointUse) + "");
        borrowBut.setEnabled(true);
        returnBut.setEnabled(false);

    }//GEN-LAST:event_nextStep1ActionPerformed

    private void backStepMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backStepMouseExited
        backStep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/left arrow.png")));
    }//GEN-LAST:event_backStepMouseExited

    private void returnButMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnButMouseEntered
        returnBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/ReturnButClick.png")));
    }//GEN-LAST:event_returnButMouseEntered

    private void returnButMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnButMouseExited
        returnBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/ReturnBut.png")));
    }//GEN-LAST:event_returnButMouseExited

    private void returnButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButActionPerformed
        int ans = JOptionPane.showConfirmDialog(this, "Do you want to Return ?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (ans == JOptionPane.YES_OPTION) {
            borrowBut.setEnabled(true);
            returnBut.setEnabled(false);
            backStep1.setEnabled(true);
            sh.stopBorrow();
            for (int i = 0; i < countBorrow.length; i++) {
                countBorrow[i] = 0;
            }
            for (int i = 0; i < detailTime.length; i++) {
                detailTime[i] = 0;
            }
            setSpinnerCount();
            detailData.setText("");
            cpUse.setText(0 + "");
            sharingStep1.setVisible(true);
            sharingStep2.setVisible(false);
            sharingStep3.setVisible(false);
            sh.getCp().selectCP();
            isCp.setText("Your CP : " + sh.getCp().getCp() + " Points");
        }
    }//GEN-LAST:event_returnButActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
        if (search.getText().equals("") || search.getText().length() == 0) {
            search.setText("Search");
        }
    }//GEN-LAST:event_searchActionPerformed

    private void canCounterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canCounterMouseClicked
        canCounter.setForeground(new java.awt.Color(19, 175, 248));
        bikeSharing.setForeground(new java.awt.Color(240, 240, 240));
        timer.setForeground(new java.awt.Color(240, 240, 240));
        cpPage.setVisible(true);
        sharingStep1.setVisible(false);
        sharingStep2.setVisible(false);
        sharingStep3.setVisible(false);
        timePageT.setVisible(false);
        timePageF.setVisible(false);
        increaseTimePage.setVisible(false);
        sh.getCp().selectCP();
        showCp.setText(sh.getCp().getCp() + "");
        (sh.getCp()).selectTrans();
        actionHis.setText(sh.getCp().getAction());
        poinsHis.setText(sh.getCp().getCount() + " Points");
    }//GEN-LAST:event_canCounterMouseClicked

    private void timerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timerMouseClicked
        timer.setForeground(new java.awt.Color(19, 175, 248));
        bikeSharing.setForeground(new java.awt.Color(240, 240, 240));
        canCounter.setForeground(new java.awt.Color(240, 240, 240));
        cpPage.setVisible(false);
        sharingStep1.setVisible(false);
        sharingStep2.setVisible(false);
        sharingStep3.setVisible(false);
        increaseTimePage.setVisible(false);
        hisBorrow1.setVisible(false);
        hisBorrow2.setVisible(false);
        int num = sh.countHis();

        if (sh.isIsReturn() == false) {
            timePageT.setVisible(true);
            nowBorrowTime.setVisible(true);
            String history[] = sh.showHis();
            borrowTime.setText(history[0]);

            if (history[1] != null) {
                hisBorrow1.setVisible(true);
                borrowTimeHis1.setText(history[1]);
            }
            if (history[2] != null) {
                hisBorrow2.setVisible(true);
                borrowTime2.setText(history[2]);
            }
            timeLeftShow.setText(sh.getTimeDetail());

            increaseBut.setVisible(true);
        } else if (sh.isIsReturn() == true) {
            timePageF.setVisible(true);
            nowBorrowTime.setVisible(false);
            increaseBut.setVisible(false);
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
    }//GEN-LAST:event_timerMouseClicked

    private void bikeSharingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bikeSharingMouseClicked
        bikeSharing.setForeground(new java.awt.Color(19, 175, 248));
        canCounter.setForeground(new java.awt.Color(240, 240, 240));
        timer.setForeground(new java.awt.Color(240, 240, 240));
        if (sh.isIsReturn()) {
            clearingSharing();
            sharingStep1.setVisible(true);
            sharingStep2.setVisible(false);
            sharingStep3.setVisible(false);
            cpPage.setVisible(false);
            timePageT.setVisible(false);
            timePageF.setVisible(false);
            increaseTimePage.setVisible(false);
            sh.getCp().selectCP();
            isCp.setText("Your CP : " + sh.getCp().getCp() + " Points");
        } else {
            sharingStep3.setVisible(true);
            sharingStep1.setVisible(false);
            sharingStep2.setVisible(false);
            cpPage.setVisible(false);
            timePageT.setVisible(false);
            increaseTimePage.setVisible(false);
        }

    }//GEN-LAST:event_bikeSharingMouseClicked

    private void increaseButMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseButMouseEntered
        increaseBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/increaseTimeClick.png")));
    }//GEN-LAST:event_increaseButMouseEntered

    private void increaseButMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseButMouseExited
        increaseBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/increaseTime.png")));
    }//GEN-LAST:event_increaseButMouseExited

    private void increaseButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseButActionPerformed
        timePageT.setVisible(false);
        increaseTimePage.setVisible(true);
        backIncrease.setVisible(true);
    }//GEN-LAST:event_increaseButActionPerformed

    private void cancleIncreaseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleIncreaseMouseEntered
        cancleIncrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleButClick.png")));
    }//GEN-LAST:event_cancleIncreaseMouseEntered

    private void cancleIncreaseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleIncreaseMouseExited
        cancleIncrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/cancleBut.png")));
    }//GEN-LAST:event_cancleIncreaseMouseExited

    private void cancleIncreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleIncreaseActionPerformed
        hour1.setForeground(new Color(204, 204, 204));
        hour1.setText("0");
        min1.setSelectedItem("0");
        sec1.setSelectedItem("0");
    }//GEN-LAST:event_cancleIncreaseActionPerformed

    private void hour1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hour1FocusGained
        if (hour1.getText().equals("0")) {
            hour1.setForeground(Color.black);
            hour1.setText("");

        }
    }//GEN-LAST:event_hour1FocusGained

    private void hour1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hour1FocusLost
        if (hour1.getText().equals("") || hour1.getText().length() == 0) {
            hour1.setForeground(new Color(204, 204, 204));
            hour1.setText("0");
        }
    }//GEN-LAST:event_hour1FocusLost

    private void backIncreaseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backIncreaseMouseEntered
        backIncrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/left arrow click.png")));
    }//GEN-LAST:event_backIncreaseMouseEntered

    private void backIncreaseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backIncreaseMouseExited
        backIncrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/left arrow.png")));
    }//GEN-LAST:event_backIncreaseMouseExited

    private void backIncreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backIncreaseActionPerformed
        timePageT.setVisible(true);
        increaseTimePage.setVisible(false);
    }//GEN-LAST:event_backIncreaseActionPerformed

    private void submitIncreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitIncreaseActionPerformed
        int h = Integer.parseInt(hour1.getText());
        int m = Integer.parseInt(min1.getSelectedItem().toString());
        int s = Integer.parseInt(sec1.getSelectedItem().toString());
        sh.increaseTime(h, m, s);
        timePageT.setVisible(true);
        increaseTimePage.setVisible(false);
        int num = sh.countHis();
        nowBorrowTime.setVisible(true);
        String history[] = sh.showHis();
        borrowTime.setText(history[0]);

        if (history[1] != null) {
            hisBorrow1.setVisible(true);
            borrowTimeHis1.setText(history[1]);
        }
        if (history[2] != null) {
            hisBorrow2.setVisible(true);
            borrowTime2.setText(history[2]);
        }
        timeLeftShow.setText(sh.getTimeDetail());
        increaseBut.setVisible(true);
    }//GEN-LAST:event_submitIncreaseActionPerformed

    private void submitIncreaseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitIncreaseMouseExited
        submitIncrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submit.png")));
    }//GEN-LAST:event_submitIncreaseMouseExited

    private void submitIncreaseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitIncreaseMouseEntered
        submitIncrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bike_gui/picture/submitClick.png")));
    }//GEN-LAST:event_submitIncreaseMouseEntered

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
        }
    }//GEN-LAST:event_signoutActionPerformed

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
            java.util.logging.Logger.getLogger(BikeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BikeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BikeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BikeUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BikeUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel History;
    private javax.swing.JLabel actionHis;
    private javax.swing.JLabel arrowDownIcon;
    private javax.swing.JLabel availableCrusier;
    private javax.swing.JLabel availableHelmet;
    private javax.swing.JLabel availableKnee;
    private javax.swing.JLabel availableTouring;
    private javax.swing.JLabel availableUtility;
    private javax.swing.JPanel backBarIcon;
    private javax.swing.JPanel backBarUser;
    private javax.swing.JButton backIncrease;
    private javax.swing.JPanel backMenuBar;
    private javax.swing.JButton backStep;
    private javax.swing.JButton backStep1;
    private javax.swing.JPanel barIcon;
    private javax.swing.JPanel barNoti;
    private javax.swing.JPanel barSearch;
    private javax.swing.JPanel barUser;
    private javax.swing.JLabel bikeRepair;
    private javax.swing.JLabel bikeSharing;
    private javax.swing.JButton borrowBut;
    private javax.swing.JLabel borrowTime;
    private javax.swing.JLabel borrowTime2;
    private javax.swing.JLabel borrowTimeHis1;
    private javax.swing.JLabel canCounter;
    private javax.swing.JButton cancleBut;
    private javax.swing.JButton cancleIncrease;
    private javax.swing.JLabel circleCp;
    private javax.swing.JLabel circleMini;
    private javax.swing.JLabel circleNoti;
    private javax.swing.JPanel cpPage;
    private javax.swing.JLabel cpUse;
    private javax.swing.JLabel cpUser;
    private javax.swing.JPanel crusierCon;
    private javax.swing.JComboBox<String> date;
    private javax.swing.JPanel dateCon;
    private javax.swing.JPanel detailBox;
    private javax.swing.JPanel detailBoxHistory;
    private javax.swing.JLabel detailData;
    private javax.swing.JPanel helmetCon;
    private javax.swing.JPanel hisBorrow1;
    private javax.swing.JPanel hisBorrow2;
    private javax.swing.JLabel history;
    private javax.swing.JComboBox<String> hour;
    private javax.swing.JTextField hour1;
    private javax.swing.JLabel iconBike;
    private javax.swing.JLabel iconCalendar;
    private javax.swing.JLabel iconClock;
    private javax.swing.JLabel iconClock1;
    private javax.swing.JLabel iconClockBig;
    private javax.swing.JLabel iconClockHis;
    private javax.swing.JLabel iconClockHis1;
    private javax.swing.JLabel iconMenu;
    private javax.swing.JLabel iconNotHis;
    private javax.swing.JLabel iconNoti;
    private javax.swing.JLabel iconProfile;
    private javax.swing.JLabel iconSearch;
    private javax.swing.JButton increaseBut;
    private javax.swing.JPanel increaseTimePage;
    private javax.swing.JLabel isCp;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JPanel kneeCon;
    private javax.swing.JPanel mainUser;
    private javax.swing.JLabel menu;
    private javax.swing.JPanel menuBar;
    private javax.swing.JComboBox<String> min;
    private javax.swing.JComboBox<String> min1;
    private javax.swing.JComboBox<String> month;
    private javax.swing.JLabel name;
    private javax.swing.JLabel news;
    private javax.swing.JButton nextStep;
    private javax.swing.JButton nextStep1;
    private javax.swing.JPanel nowBorrowTime;
    private javax.swing.JSpinner numCrusier;
    private javax.swing.JSpinner numHelmet;
    private javax.swing.JSpinner numKnee;
    private javax.swing.JLabel numNoti;
    private javax.swing.JSpinner numTouring;
    private javax.swing.JSpinner numUtility;
    private javax.swing.JLabel pic;
    private javax.swing.JLabel picCrusier;
    private javax.swing.JLabel picHelmet;
    private javax.swing.JLabel picKnee;
    private javax.swing.JLabel picTouring;
    private javax.swing.JLabel picUtility;
    private javax.swing.JLabel poinsHis;
    private javax.swing.JLabel point1;
    private javax.swing.JLabel point2;
    private javax.swing.JLabel point3;
    private javax.swing.JLabel point4;
    private javax.swing.JLabel pointRemain;
    private javax.swing.JLabel position;
    private javax.swing.JLabel profile;
    private javax.swing.JButton returnBut;
    private javax.swing.JLabel s1;
    private javax.swing.JLabel s2;
    private javax.swing.JLabel s3;
    private javax.swing.JTextField search;
    private javax.swing.JComboBox<String> sec;
    private javax.swing.JComboBox<String> sec1;
    private javax.swing.JPanel sharingStep1;
    private javax.swing.JPanel sharingStep2;
    private javax.swing.JPanel sharingStep3;
    private javax.swing.JLabel showCp;
    private javax.swing.JButton signout;
    private javax.swing.JLabel slash01;
    private javax.swing.JLabel slash02;
    private javax.swing.JLabel slash03;
    private javax.swing.JLabel slash04;
    private javax.swing.JButton submitIncrease;
    private javax.swing.JLabel support;
    private javax.swing.JLabel textCpUse;
    private javax.swing.JLabel textNotHis;
    private javax.swing.JLabel textRemain;
    private javax.swing.JLabel textYourCP;
    private javax.swing.JPanel timeCon;
    private javax.swing.JPanel timeCon1;
    private javax.swing.JLabel timeLeftShow;
    private javax.swing.JPanel timePageF;
    private javax.swing.JPanel timePageT;
    private javax.swing.JLabel timer;
    private javax.swing.JLabel title;
    private javax.swing.JLabel titleHistory;
    private javax.swing.JLabel titleHour;
    private javax.swing.JLabel titleMin;
    private javax.swing.JLabel titleNow;
    private javax.swing.JLabel titleReturn;
    private javax.swing.JLabel titleSec;
    private javax.swing.JLabel titleSignout;
    private javax.swing.JLabel titleTime;
    private javax.swing.JLabel titleTime4;
    private javax.swing.JPanel touringCon;
    private javax.swing.JPanel utilityCon;
    private javax.swing.JComboBox<String> year;
    // End of variables declaration//GEN-END:variables
}
