import java.sql.*;
import java.util.Date;
public class Sharing {
    private History his = new History();
    private Timer time = new Timer(); //เวลา
    private boolean isReturn = true;
    private String timeDetail = ""; //รายละเอียด
    private String type = ""; //ชนิด
    private String equip = ""; //อุปกรณ์
    private int[] numBikeUser; //จำนวนที่ user ยืมแต่ละอุปกรณ์
    private static int[] availableItem; //จำนวนไอเทมที่สามารถใช้ได้
    private int statToBorrow[];//เก็บสถิติการยืม
    private int countType;
    private int countEquip;
    private CanCounter cp; //ซีพี
    
    public Sharing() { //constructors
        cp = new CanCounter();
    }
    
    public void amountOfItem(){
        int temp = 0,i = 0;
        Connection con = null;
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            String sql = "SELECT * FROM Items";
            
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                temp++;
            }
            numBikeUser = new int[temp];
            availableItem = new int[temp];
            statToBorrow = new int[temp];
            
            sql = "SELECT availableNumber FROM Items WHERE itemID LIKE 'B%'";
            rs = s.executeQuery(sql);
            while(rs.next()){
                availableItem[i] = rs.getInt("availableNumber");
                i++;
            }
            countType = i;
            countEquip = temp-i;

            sql = "SELECT availableNumber FROM Items WHERE itemID LIKE 'E%'";
            rs = s.executeQuery(sql);
            while(rs.next()){
                availableItem[i] = rs.getInt("availableNumber");
                i++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        try{
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void borrowItem(){
        Timestamp borrowDate = new Timestamp(time.getBorrowTime().getTime());
        Timestamp returnDate = new Timestamp(time.getReturnTime().getTime());
        String sql;
        Connection con = null;
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            for (int i = 0; i < countType; i++) {
                
                sql = "UPDATE Items SET availableNumber='"+availableItem[i]+"' WHERE itemID LIKE 'B%"+(i+1)+"'";
                s.execute(sql);
                if(numBikeUser[i] != 0){
                    his.HistoryByAdmin("B0"+(i+1),borrowDate,returnDate,"Borrow");
                }
            }

            for (int i = 1; i <= countEquip; i++) {
                sql = "UPDATE Items SET availableNumber='"+availableItem[countType-1+i]+"' WHERE itemID LIKE 'E%"+i+"'";
                s.execute(sql);
                if(numBikeUser[countType-1+i] != 0){
                    his.HistoryByAdmin("E0"+i,borrowDate,returnDate,"Borrow");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try{
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void returnItems(){
        Date nd = new Date();
        Timestamp nowDate = new Timestamp(nd.getTime());
        Timestamp returnDate = new Timestamp(time.getReturnTime().getTime());
        String sql;
        Connection con = null;
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            for (int i = 0; i < countType; i++) {
                sql = "UPDATE Items SET availableNumber='"+(availableItem[i]+numBikeUser[i])+"' WHERE itemID LIKE 'B%"+(i+1)+"'";
                s.execute(sql);
                if(numBikeUser[i] != 0){
                    his.HistoryByAdmin("B0"+(i+1),nowDate,returnDate,"Return");
                }
                numBikeUser[i] = 0;
            }

            for (int i = 1; i <= countEquip; i++) {
                sql = "UPDATE Items SET availableNumber='"+(availableItem[countType-1+i]+numBikeUser[countType-1+i])+"' WHERE itemID LIKE 'E%"+i+"'";
                s.execute(sql);
                if(numBikeUser[countType-1+i] != 0){
                    his.HistoryByAdmin("E0"+i,nowDate,returnDate,"Return");
                }
                numBikeUser[countType-1+i] = 0;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try{
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setNumBikeUser(int[] numBikeUser) {
        this.numBikeUser = numBikeUser;
    }

    public void borrowStep(){ //ระบุจำนวนยืมจักรยานแต่ละชนิด
        for (int i = 0; i < numBikeUser.length ; i++) {
                statToBorrow[i]+= numBikeUser[i];
                availableItem[i] -= numBikeUser[i];
        }
    }
    
    public void editStep(){
        for (int i = 0; i < statToBorrow.length; i++) {
            statToBorrow[i] -= numBikeUser[i];
            availableItem[i] += numBikeUser[i];
        }
    }
    public void returnStep(){ //ระบุจำนวนยืมจักรยานแต่ละชนิด
        for (int i = 0; i < numBikeUser.length ; i++) {
            availableItem[i] += numBikeUser[i];
        }
    }

    
    public String showStatBorrow() {
        String b = "";
        String e = "";
        int max = 0;
        int temp = 0;

        for (int i = 0; i < statToBorrow.length - 2; i++) {
            if (max < statToBorrow[i]) {
                max = statToBorrow[i];
                temp = i;
            } else if (max == statToBorrow[i] && max != 0) {
                switch (temp) {
                    case 0:b += "Utility Bike : " + max; break;
                    case 1:b += "Crusier Bike : " + max; break;
                    case 2:b += "Touring Bike : " + max; break;
                }
                b += "\n";
                temp = i;
            }
        }
        switch (temp) {
            case 0:b += "Utility Bike : " + max; break;
            case 1:b += "Crusier Bike : " + max; break;
            case 2:b += "Touring Bike : " + max; break;
        }

        max = 0;
        for (int i = 3; i < statToBorrow.length; i++) {
            if (max < statToBorrow[i]) {
                max = statToBorrow[i];
                temp = i;
            } else if (max == statToBorrow[i] && max != 0) {
                switch (temp) {
                    case 3:e += "Bicycle Helmets : " + max; break;
                    case 4:e += "Knee : " + max; break;
                }
                e += "\n";
                temp = i;
            }
        }

        switch (temp) {
            case 3:e += "Bicycle Helmets : " + max; break;
            case 4:e += "Knee : " + max; break;
        }

        return "The type of bicycle the most borrow of you >>> " + b
                + "\nThe equipment the most borrow of you >>> " + e;
    }
    
    public void startBorrow() throws InterruptedException { //เริ่มยืม
        cp.decreseCp();
        isReturn = false;
        borrowItem();
        time.start();
        if(time.getTotalHour() == 0 && time.getTotalMin() == 0 && time.getTotalSeconds() == 0 && isReturn == false){
            cp.setCpUse(cp.getCpUse()*2);
            increaseTime(1, 0, 0);
            startBorrow();
        }
    }

    public void stopBorrow() { //หยุดยืม(นำของมาคืน)
        time.stop();
        timeDetail = time.showDetail();
        isReturn = true;
        cp.setCpUse(0);
        returnStep();
        returnItems();
    }   

    public void enterTime(int userDate, int userMonth, int userYear, int userHr, int userMin, int userSec) { //ระบุวันเวลาที่จะคืน
        time = new Timer(userDate, userMonth, userYear, userHr, userMin, userSec);
        time.differentTime();
        timeDetail = time.showDetail();
    }

    public void increaseTime(int hr, int min, int sec) { //เพิ่มเวลาในการยืม
        time.increaseTime(hr, min, sec);
        timeDetail = time.showDetail();
    }
    
    public String getTimeDetail() { //แสดงเวลาที่เหลือ
        timeDetail = time.showDetail();
        return timeDetail;
    }
    public void setPointUse(){
        cp.countCpBorrow(numBikeUser);
    }
    public Timer getTime() {
        return time;
    }

    public static int[] getAvailableItem() {
        return availableItem;
    }

    public CanCounter getCp() {
        return cp;
    }
}
