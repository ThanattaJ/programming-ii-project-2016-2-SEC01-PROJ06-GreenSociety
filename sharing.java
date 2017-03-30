package bike;

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
    private long cp; //ซีพี
    private long cpUse = 0; //ซีพีที่userใช้
    
    public Sharing() { //constructors
        this.cp = 0;
        amountOfItem();
    }
    
    private void amountOfItem(){
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
            
            sql = "SELECT * FROM Items WHERE itemID LIKE 'B%'";
            rs = s.executeQuery(sql);
            while(rs.next()){
                availableItem[i] = rs.getInt("amount");
                i++;
            }
            countType = i;
            countEquip = temp-i;

            sql = "SELECT * FROM Items WHERE itemID LIKE 'E%'";
            rs = s.executeQuery(sql);
            while(rs.next()){
                availableItem[i] = rs.getInt("amount");
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
        Timestamp borrowDate = new Timestamp(time.getNowTime().getTime());
        String sql;
        Connection con = null;
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            for (int i = 0; i < countType; i++) {
                
                sql = "UPDATE Items SET availableNumber='"+availableItem[i]+"' WHERE itemID LIKE 'B%"+(i+1)+"'";
                s.execute(sql);
                if(numBikeUser[i] != 0){
                    his.HistoryByAdmin("B0"+(i+1),borrowDate,"Borrow");
                }
            }

            for (int i = 1; i <= countEquip; i++) {
                sql = "UPDATE Items SET availableNumber='"+availableItem[countType-1+i]+"' WHERE itemID LIKE 'E%"+i+"'";
                s.execute(sql);
                if(numBikeUser[countType-1+i] != 0){
                    his.HistoryByAdmin("E0"+i,borrowDate,"Borrow");
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
        Date rt = new Date();
        Timestamp returnDate = new Timestamp(rt.getTime());
        String sql;
        Connection con = null;
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            for (int i = 0; i < countType; i++) {
                sql = "UPDATE Items SET availableNumber='"+(availableItem[i]+numBikeUser[i])+"' WHERE itemID LIKE 'B%"+(i+1)+"'";
                s.execute(sql);
                if(numBikeUser[i] != 0){
                    his.HistoryByAdmin("B0"+(i+1),returnDate,"Return");
                }
                numBikeUser[i] = 0;
            }

            for (int i = 1; i <= countEquip; i++) {
                sql = "UPDATE Items SET availableNumber='"+(availableItem[countType-1+i]+numBikeUser[countType-1+i])+"' WHERE itemID LIKE 'E%"+i+"'";
                s.execute(sql);
                if(numBikeUser[countType-1+i] != 0){
                    his.HistoryByAdmin("E0"+i,returnDate,"Return");
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
    
    public String showType() { //แสดงประเภทจักรยานที่ให้ยืม
       
        return "Type : \n"
                + "1.Utility Bike (100CP)\n Remaining : " + availableItem[availableItem.length-5] + "\n"
                + "2.Cruiser Bike (125CP)\n Remaining : " + availableItem[availableItem.length-4] + "\n"
                + "3.Touring Bike (150CP)\n Remaining : " + availableItem[availableItem.length-3] + "\n"
                + "4.null";
    }

    public String showEquip() { //แสดงอุปกรณ์ที่ให้ปริการ
        
        return "Equipment : \n"
                + "1.Bicycle helmets (60CP)\n Remaining : " + availableItem[availableItem.length-2] + "\n"
                + "2.Knee (80CP)\n Remaining : " + availableItem[availableItem.length-1] + "\n"
                + "3.null";
    }

    public void borrowTypeStep(int t, int count) throws ItemAmountException{ //ระบุจำนวนยืมจักรยานแต่ละชนิด
        switch (t) {
            case 1:
                if(availableItem[availableItem.length-5] >= count){
                    numBikeUser[numBikeUser.length-5] += count;
                    availableItem[availableItem.length-5] -= count;
                    statToBorrow[statToBorrow.length-5]+=count;
                }else{
                    ItemAmountException iae = new ItemAmountException();
                    throw iae;
                }
                    break;
            case 2:
                if(availableItem[availableItem.length-4] >= count){
                    numBikeUser[numBikeUser.length-4] += count;
                    availableItem[availableItem.length-4] -= count;
                    statToBorrow[statToBorrow.length-4]+=count;
                }else{
                    ItemAmountException iae = new ItemAmountException();
                    throw iae;
                }
                break;
            case 3:
                if(availableItem[availableItem.length-3] >= count){
                    numBikeUser[numBikeUser.length-3] += count;
                    availableItem[availableItem.length-3] -= count;
                    statToBorrow[statToBorrow.length-3]+=count;
                }else{
                    ItemAmountException iae = new ItemAmountException();
                    throw iae;
                }
                break;
            case 4:
                break;
            default:
                type = "Error";
                break;
        }
    }

    public void borrowEquipStep(int e, int count) throws ItemAmountException { //ระบุจำนวนยืมอุปกรณ์
        switch (e) {
            case 1:
                if(availableItem[availableItem.length-2] >= count){
                    numBikeUser[numBikeUser.length-2] += count;
                    availableItem[availableItem.length-2] -= count;
                    statToBorrow[statToBorrow.length-2]+=count;
                }else{
                    ItemAmountException iae = new ItemAmountException();
                    throw iae;
                }
                break;
            case 2:
                if(availableItem[availableItem.length-1] >= count){
                    numBikeUser[numBikeUser.length-1] += count;
                    availableItem[availableItem.length-1] -= count;
                    statToBorrow[statToBorrow.length-1]+=count;
                }else{
                    ItemAmountException iae = new ItemAmountException();
                    throw iae;
                }
                break;
            case 3:
                break;
            default:
                equip = "Error";
                break;
        }
    }

    public void editStep(int num, int count) throws ItemAmountException{ //แก้ไขสิ่งของที่ยืมรวมถึงเวลา
        switch (num) {
            case 1:
                if (numBikeUser[numBikeUser.length - 5] > 0) {
                    availableItem[availableItem.length-5] += numBikeUser[numBikeUser.length - 5];
                    statToBorrow[statToBorrow.length-5] -= numBikeUser[numBikeUser.length - 5];
                    borrowTypeStep(1,count);
                } else {
                    borrowTypeStep(1,count);
                }
                break;

            case 2:
                if (numBikeUser[numBikeUser.length - 4] > 0) {
                    availableItem[availableItem.length-4] += numBikeUser[numBikeUser.length - 4];
                    statToBorrow[statToBorrow.length-4] -= numBikeUser[numBikeUser.length - 4];
                    borrowTypeStep(2,count);
                } else {
                    borrowTypeStep(2,count);
                }
                break;

            case 3:
                if (numBikeUser[numBikeUser.length - 3] > 0) {
                    availableItem[availableItem.length-3] += numBikeUser[numBikeUser.length - 3];
                    statToBorrow[statToBorrow.length-3] -= numBikeUser[numBikeUser.length - 3];
                    borrowTypeStep(3,count);
                } else {
                    borrowTypeStep(3,count);
                }
                break;

            case 4:
                if (numBikeUser[numBikeUser.length - 2] > 0) {
                    availableItem[availableItem.length-2] += numBikeUser[numBikeUser.length - 2];
                    statToBorrow[statToBorrow.length-2] -= numBikeUser[numBikeUser.length - 2];
                    borrowEquipStep(1,count);
                } else {
                    borrowEquipStep(1,count);
                }
                break;

            case 5:
                if (numBikeUser[numBikeUser.length - 1] > 0) {
                    availableItem[availableItem.length-1] += numBikeUser[numBikeUser.length - 1];
                    statToBorrow[statToBorrow.length-1] -= numBikeUser[numBikeUser.length - 1];
                    borrowEquipStep(2,count);
                } else {
                    borrowEquipStep(2,count);
                }
                break;

            default:
                System.out.println("Number Error, Please Enter Again.");
                break;
        }
    }

    public String allDetail() { //แสดงข้อมูลทั้งหมด
        countCpBorrow();
        boolean check = checkCp(cpUse);
        if(type.equalsIgnoreCase("Error") || equip.equalsIgnoreCase("Error")){
            return "Your select Bicycle or Equipments incorrect,Plese Select again.";
        }else if (check == true){
            type = "- Utility Bike : " + numBikeUser[numBikeUser.length-5] + "\n";
            type += "- Cruiser Bike : " + numBikeUser[numBikeUser.length-4] + "\n";
            type += "- Touring Bike : " + numBikeUser[numBikeUser.length-3];
            equip = "- Bicycle helmets : " + numBikeUser[numBikeUser.length-2] + "\n";
            equip += "- Knee : " + numBikeUser[numBikeUser.length-1];

            return "Your CP : " + cp + "\n"
                    + "CP use : " + cpUse + "\n"
                    + "Borrow Detail : \n"
                    + "Bicycle Type : \n" + type + "\n"
                    + "Equipments : \n" + equip + "\n"
                    + "Time Detail : \n" + timeDetail;
        } else {

            return "Your CP : " + cp + "\n"
                    + "CP use : " + cpUse + "\n"
                    + "YOUR CP IS NOT ENOUGH. PlEASE SELECT AGAIN";
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
    
    public void submitTrans(int ans)throws InterruptedException{
        switch(ans){
            case 1: System.out.println("--SUBMIT--"); startBorrow(); break;
            case 2: System.out.println("--CANCLE--"); break;
            default: System.out.println(">> ERROR <<"); break;
        }
    }
    
    public void startBorrow() throws InterruptedException { //เริ่มยืม
        decreseCp(cpUse);
        isReturn = false;
        borrowItem();
        time.start();
        if(time.getTotalHour() == 0 && time.getTotalMin() == 0 && time.getTotalSeconds() == 0 && isReturn == false){
            cpUse = cpUse*2;
//            increaseTime(1, 0, 0);
//            startBorrow();
        }
        
    }

    public void stopBorrow() { //หยุดยืม(นำของมาคืน)
        type = "";
        equip = "";
        time.stop();
        timeDetail = time.showDetail();
        isReturn = true;
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
    
    public String getTimeDetail() { //แสดงข้อมูลวันทั้งวันยืมและคืและเวลาที่เหลือ
        timeDetail = time.showDetail();
        return timeDetail;
    }

    public long getCp() { //แสดงค่า Cp
        return cp;
    }
    
    public void increseCp(long c) { //เพิ่มCp
        long nCp = c * 50;
        cp += nCp;
    }

    public void decreseCp(long c) { //ลดCp
        cp -= c;
    }

    public boolean checkCp(long c) { //check Cpของuserกับcpทั้งหมดที่ต้องนำมาใช้แลกเพื่อยืม
        boolean temp = false;
        if (c > cp) {
            temp = false;
        } else if (c <= cp) {
            temp = true;
        }
        return temp;
    }
    
    public void countCpBorrow() { //คำนวนCp ที่จะต้องใช้แลกยืม
        int temp = 0;
        if (numBikeUser[0] > 0) {
            temp += numBikeUser[0] * 100;
        }
        if (numBikeUser[1] > 0) {
            temp += numBikeUser[1] * 125;
        }
        if (numBikeUser[2] > 0) {
            temp += numBikeUser[2] * 150;
        }

        if (numBikeUser[3] > 0) {
            temp += numBikeUser[3] * 60;
        }
        if (numBikeUser[4] > 0) {
            temp += numBikeUser[4] * 80;
        }
        cpUse = temp;
    }
}

