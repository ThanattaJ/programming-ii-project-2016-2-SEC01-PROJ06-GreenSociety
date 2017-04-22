package bike;

import java.sql.*;
import java.util.Date;
public class Sharing {
    private History his = new History();
    private Timer time = new Timer(); //เวลา
    private boolean isReturn = true;
    private String timeDetail = ""; //รายละเอียด
    private int[] numBikeUser; //จำนวนที่ user ยืมแต่ละอุปกรณ์
    private static int[] availableItem; //จำนวนไอเทมที่สามารถใช้ได้
    private String itemID[];
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
                sql = "UPDATE Items SET availableNumber='"+availableItem[i]+"' WHERE itemID LIKE 'B%"+(i+1)+"'";
                s.execute(sql);
                if(numBikeUser[i] != 0){
                    his.HistoryByAdmin("B0"+(i+1),nowDate,returnDate,"Return");
                }
                numBikeUser[i] = 0;
            }

            for (int i = 1; i <= countEquip; i++) {
                sql = "UPDATE Items SET availableNumber='"+availableItem[countType-1+i]+"' WHERE itemID LIKE 'E%"+i+"'";
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
                availableItem[i] -= numBikeUser[i];
        }
    }
    
    public void editStep(){
        for (int i = 0; i < numBikeUser.length; i++) {
            availableItem[i] += numBikeUser[i];
        }
    }
    
    public void returnStep(){ //ระบุจำนวนยืมจักรยานแต่ละชนิด
        for (int i = 0; i < numBikeUser.length ; i++) {
            availableItem[i] += numBikeUser[i];
        }
    }
    public void decreseCPNoti(){
        cp.decreseCp();
    }
    public void startBorrow() throws InterruptedException{ //เริ่มยืม
        cp.decreseCp();
        isReturn = false;
        borrowItem();
        time.start(this);
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

    public boolean isIsReturn() {
        return isReturn;
    }

    public void enterTime(int userDate, int userMonth, int userYear, int userHr, int userMin, int userSec) { //ระบุวันเวลาที่จะคืน
        time = new Timer(userDate, userMonth, userYear, userHr, userMin, userSec);
        time.differentTime();
        timeDetail = time.showDetail();
    }

    public void increaseTime(int hr, int min, int sec) { //เพิ่มเวลาในการยืม
        time.increaseTime(hr, min, sec);
        timeDetail = time.showDetail();
        cp.decreseCp();
        borrowItem();
    }
    
    public int countHis(){
        return time.showStartAndEndTime();
    }
    
    public String[] showHis(){
        return time.getHisBorrow();
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

    public CanCounter getCp(){
        return cp;
    }
    
    public void selectAllItemID(){
        String[] allItem = null;
        Connection con = null;
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();

            String sql = "SELECT COUNT(itemID) As num FROM Items";
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            int count = rs.getInt("num");
            itemID = new String[count];
            sql = "SELECT * FROM Items ORDER BY itemID ASC";
            rs = s.executeQuery(sql);
            for (int i = 0; i < itemID.length; i++) {
                if(rs.next()){
                    itemID[i] = rs.getString("itemID");
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
    
    public String selectItemIdBike(){
        String bikeId="B0";
        Connection con = null;
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            String sql = "SELECT RIGHT (itemID, 2) AS count FROM Items WHERE itemID LIKE 'B%' ORDER BY itemID DESC";
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            int tmp = Integer.parseInt(rs.getString("count"));
            tmp++;
            if(tmp < 10){
                bikeId = "B0"+tmp;
            }else if(tmp >= 10){
                bikeId = "B"+tmp;
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
        return bikeId;
    }
    
    public String selectItemIdEquip(){
        String equipId="E0";
        Connection con = null;
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            String sql = "SELECT RIGHT (itemID, 2) AS count FROM Items WHERE itemID LIKE 'E%' ORDER BY itemID DESC";
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            int tmp = Integer.parseInt(rs.getString("count"));
            tmp++;
            if(tmp < 10){
                equipId = "E0"+tmp;
            }else if(tmp >= 10){
                equipId = "E"+tmp;
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
        return equipId;
    }
        
    public void addItem(String id,String name,int count){
        Connection con = null;
        int available=0;
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            String sql = "INSERT INTO Items VALUES ('"+id+"','"+name+"','"+count+"','"+count+"')";
            s.executeUpdate(sql);
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
    
    public void editItem(String id,String name,int count){
        Connection con = null;
        int available=0;
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            String sql = "SELECT * FROM Items Where itemID='"+id+"'";
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            available = rs.getInt("amount")-rs.getInt("availableNumber");
            sql = "UPDATE Items SET name='"+name+"',amount='"+count+"',availableNumber='"+(count-available)+"' WHERE itemID='"+id+"'";
            s.executeUpdate(sql);
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
        
    public void deleteItem(String id){
        Connection con = null;
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            String sql = "DELETE FROM Items WHERE itemID='"+id+"'";
            s.executeUpdate(sql);
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
    
    public String[] selectAllAdmin(){
        String[] allItem = null;
        Connection con = null;
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            
            String sql = "SELECT COUNT(itemID) As num FROM Items";
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            int count = rs.getInt("num");
            allItem = new String[count];
            sql = "SELECT * FROM Items ORDER BY itemID ASC";
            rs = s.executeQuery(sql);
            for (int i = 0; i < allItem.length; i++) {
                if(rs.next()){
                    allItem[i] = "Item ID : "+rs.getString("itemID")+"  Name : "+rs.getString("itemname")+"  Amount : "+rs.getInt("amount")+"  Available : "+rs.getInt("availableNumber");
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
        return allItem;
    }

    public String[] getItemID() {
        return itemID;
    }
    
}