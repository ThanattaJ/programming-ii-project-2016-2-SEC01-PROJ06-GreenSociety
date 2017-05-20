package bike;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Repair{
    private int countID;
    private String askingUser="";
    private String repairingUser="";
    private String statusUser="";
    private String timerUser;
    private long perpairId;
    private String problem;//ปัญหาของการซ่อมจักรยาน เช่น ยางแตก ช่างกรอก Asking
    private String detail;//รายละเอียด เช่น ต้องเปลี่ยนยาง ช่างกรอก Repairing
    private String bike="";//รับจาก GUI ให้ user กรอก
    private String whyRepair="";//ให้ user กรอกว่าทำไมถึงต้องส่งซ่อม
    private String color="";
    private long countTransId;
    private long repairStateId;
    private String status="";
    private String askingAdminNotSuccess ;
    private String repairingAdminNotSuccess ;
    private long transIDAdminNotSuccess ;
    private int userID;
    private int itemId;
    private long userIDprepair;
    public Repair() {
        
    }
    
    public ArrayList<String> connectDBforListUserSentToRepair(){ //เอา prepairID,name,surname,userID เพื่อเอาไปใช้ใน listUserRepair
        ArrayList<String> list = new ArrayList<String>();
        String format="";
        try{
            Connection connect = Database.connectDb("jan", "jan042");
            Statement st = connect.createStatement(); 
            String temp = "SELECT Prepair_Desctiption.id,User.firstName,User.lastName,User.userID FROM Green_Society.Prepair_Desctiption " +
                            "JOIN Transaction ON Prepair_Desctiption.transID=Transaction.transID " +
                            "INNER JOIN User ON Transaction.userID=User.userID " +
                            "LEFT JOIN Green_Society.Repair_State ON Prepair_Desctiption.id = Repair_State.item_id " +
                            "WHERE Repair_State.item_id IS NULL";
            ResultSet rs = st.executeQuery(temp);
            while(rs.next()){
                int idPrepair = rs.getInt("id");
                String name = rs.getString("firstName");
                String surname = rs.getString("lastName");
                userIDprepair = rs.getInt("userID");
                format = idPrepair+"    |     Name: "+name+"    |   Surname: "+surname+"    |   ID: "+userIDprepair;
                list.add(format);
            }
            
            if(connect != null){
                    connect.close();
		}
		}catch (SQLException e){
                    e.printStackTrace();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return list;
    }

    public long getUserIDprepair() {
        return userIDprepair;
    }
    
    
    
    public void connectDBForChangeToSuccessFromPerpair(long perpairID){ //รับ perpairID เพื่อเปลี่ยนเป็น Repair_State.Recieving -> success
         try{
            Connection connect = Database.connectDb("jan", "jan042");
            Statement st = connect.createStatement(); 
            String temp = "UPDATE Repair_State JOIN Prepair_Desctiption ON Repair_State.item_id = Prepair_Desctiption.id "
                    + "SET Repair_State.Recieving = 'Success' WHERE Prepair_Desctiption.id = "+perpairID;
            st.executeUpdate(temp);
            
            if(connect != null){
                    connect.close();
		}
		}catch (SQLException e){
                    e.printStackTrace();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    public void connectDBForChangeToSuccessFormRepairState(long idRepairState){ //รับ repairID เพื่อเปลี่ยนเป็น Repair_State.Recieving -> success
         try{
            Connection connect = Database.connectDb("jan", "jan042");
            Statement st = connect.createStatement(); 
            String temp = "UPDATE Repair_State SET Repair_State.Recieving = 'Success' WHERE id= "+idRepairState;
            
            st.executeUpdate(temp);
            
            if(connect != null){
                    connect.close();
		}
		}catch (SQLException e){
                    e.printStackTrace();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public ArrayList<String> connectDBForCheckRepairNotSucceess(){ //เอาไปใช้ในเพื่อดูว่าอันไหนที่ not success
        ArrayList<String> notSuccess = new ArrayList<String>();
        String name;
        String action;
        Timestamp remaining;
        String format;
        int id;
         try{
            Connection connect = Database.connectDb("jan", "jan042");
            Statement st = connect.createStatement(); 
            String temp = "SELECT Repair_State.id,User.firstName,Repair_State.userID,Repair_State.Repairing,Transaction.dateTime,Repair_State.item_id FROM Green_Society.User " +
                            "JOIN Repair_State ON User.userID = Repair_State.userID " +
                            "INNER JOIN Prepair_Desctiption ON Repair_State.item_id=Prepair_Desctiption.id " +
                            "INNER JOIN Transaction ON Prepair_Desctiption.transID=Transaction.transID " +
                            "WHERE Repair_State.Recieving = 'Not Success!'";
            ResultSet rs = st.executeQuery(temp);
            while(rs.next()){
                id = rs.getInt("id");
                name=rs.getString("firstName");
                userID = rs.getInt("userID"); // userID ของคนที่ส่งซ่อมมา 
                action=rs.getString("Repairing");
                remaining=rs.getTimestamp("dateTime");
                itemId = rs.getInt("item_id");
                format=id+"   |   "+name+"   |   "+action+"       "+remaining;
                notSuccess.add(format);
            }
            if(connect != null){
                    connect.close();
		}
		}catch (SQLException e){
                    e.printStackTrace();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
         return notSuccess;
    }

    public int getUserID() {
        return userID;
    }

    public int getItemId() {
        return itemId;
    }

    public long getCountTransId() {
        return countTransId;
    }
    
    public void connectDBFromAdminToUser(long itemId,long user){//กรอกรายละเอียดการซ่อมให้ user
         try{
            Connection connect = Database.connectDb("jan", "jan042");
            Statement st = connect.createStatement(); 
            //ดึงเอา id ที่มาที่สุดออกมา เพื่อให้มันสามารถ insert ลง table ให้ไม่ซ้ำกันได้
            String temp = "SELECT MAX(id) AS countId FROM Repair_State ";
            ResultSet rs = st.executeQuery(temp);
            int count=0;
            while(rs.next()){
                count = rs.getInt("countId");
            }
            repairStateId = ++count;
            //------------------------------------------------------------------
            //เอาข้อมูลลง DB Repair_State
            String sqlid = "\'"+repairStateId+"\'";
            String sqlAsking = "\'"+problem+"\'";
            String sulRepairing = "\'"+detail+"\'";
            String sqlRecieving = "\'"+Status(false)+"\'";
            String sqlItemId = "\'"+itemId+"\'";
            
            
            String temp2 ="INSERT INTO Repair_State "
                    + "VALUES"+" ("+sqlid+","
                    + sqlAsking +","
                    + sulRepairing + ","
                    + sqlRecieving + ","
                    + sqlItemId + ","
                    + user+")";
            
            st.executeUpdate(temp2);
            //---------------------------------------------------------------------------------------
            if(connect != null){
                    connect.close();
		}
		}catch (SQLException e){
                    e.printStackTrace();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public int connectDBFormAdminSelectTransID(long repairID){
        int transID=0;
        try{
            Connection connect = Database.connectDb("jan", "jan042");
            Statement st = connect.createStatement(); 
            
            String temp = "SELECT transID FROM Prepair_Desctiption JOIN Repair_State ON Prepair_Desctiption.id = Repair_State.item_id WHERE Repair_State.item_id =  "+repairID;
            ResultSet rs = st.executeQuery(temp);
            
            while(rs.next()){
                transID = rs.getInt("transID");
            }
            
            if(connect != null){
                    connect.close();
		}
		}catch (SQLException e){
                    e.printStackTrace();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return transID;
    }
    
    public void connectDBForAdminUpdateTime(long transId,Timestamp endTime ){ //อัปเดตเฉพาะ returnDate ใน transaction
        try{
            Connection connect = Database.connectDb("jan", "jan042");
            Statement st = connect.createStatement(); 
            
            String temp = "update `Transaction` set return_dateTime = '"+endTime+"' WHERE transID = "+transId;
            st.executeUpdate(temp);
            //---------------------------------------------------------------------------------------
            if(connect != null){
                    connect.close();
		}
		}catch (SQLException e){
                    e.printStackTrace();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public void connectDBForAdminUpdateTime(long transId,Timestamp startTime,Timestamp endTime){
        try{
            Connection connect = Database.connectDb("jan", "jan042");
            Statement st = connect.createStatement(); 
            
            String temp = "update `Transaction` set return_dateTime = '"+endTime
                    + "' ,dateTime = '" + startTime
                    + "' WHERE transID ="+transId;
            st.executeUpdate(temp);
            //---------------------------------------------------------------------------------------
            if(connect != null){
                    connect.close();
		}
		}catch (SQLException e){
                    e.printStackTrace();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
        
    public void connectDBFromUserToAdmin(String asking,String band,String color,long userID){ //รับจาก parameter จาก user เพื่อเก็บลง prepair_desctioption
        Timer t = new Timer();
        Timestamp startDate = new Timestamp(t.getBorrowTime().getTime());
        Timestamp returnDate = new Timestamp(t.getReturnTime().getTime());
        try{
            Connection connect = Database.connectDb("jan", "jan042");
            Statement st = connect.createStatement(); 
            //ดึงเอา id ที่มาที่สุดออกมา เพื่อให้มันสามารถ insert ลง table ให้ไม่ซ้ำกันได้
            String temp = "SELECT MAX(id) AS countId FROM Prepair_Desctiption ";
            ResultSet rs = st.executeQuery(temp);
            int count=0;
            while(rs.next()){
                count = rs.getInt("countId");
            }
            perpairId = ++count;
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
            String sqlid = "\'"+this.perpairId+"\'";
            String brand = "\'"+band+"\'";
            String sqlcolor = "\'"+color+"\'";
            String sqlWhy = "\'"+asking+"\'";
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
            
            String temp3 ="INSERT INTO Transaction(transID,dateTime,return_dateTime,itemID,amount,userID,action)  VALUES " //set ค่าให้กับ Database
                    + "("+this.countTransId+","
                    +"'"+startDate+"','"
                     +returnDate+"',"
                      +0+ ","
                      +0+" ,"
                      +userId+","
                      +action+")";
             
            st.executeUpdate(temp3);
            //------------------------------------------------------------------
            if(connect != null){
                    connect.close();
		}
		}catch (SQLException e){
                    e.printStackTrace();
        }
        catch(Exception ex){
            System.out.println(ex);
        }

    }
        
    public void connectDBShowRepairForUserFollowing(long userID){//เพื่อที่ User จะสามารถดูและติดตามดารซ่อมของตัวเองได้
        Timestamp startTime=null;
        Timestamp endTime=null;
        try{
            Connection connect = Database.connectDb("jan", "jan042");
            
            Statement st = connect.createStatement(); 
            //ดึงเอา id ที่มาที่สุดออกมา เพื่อให้มันสามารถ insert ลง table ให้ไม่ซ้ำกันได้
            String temp = "SELECT Asking,Repairing,Recieving FROM Repair_State where userID= "+userID;
            ResultSet rs = st.executeQuery(temp);
            int count=0;
            while(rs.next()){
                askingUser += "- " + rs.getString("Asking")+"<br>";
                repairingUser += "- "+rs.getString("Repairing") +"<br>";
                statusUser += "- "+rs.getString("Recieving") + "<br>";
            }
            
            String temp2 = "SELECT dateTime FROM `Transaction` WHERE action LIKE 'Repair' AND userID =  "+userID;
            ResultSet rs2 = st.executeQuery(temp2);
            
            while(rs2.next()){
                startTime = rs2.getTimestamp("dateTime");
                endTime = rs2.getTimestamp("return_dateTime");
            }
            
            if(askingUser==null){
                askingUser = "";
            }
            
            if(repairingUser==null){
                repairingUser = "";
            }
            
            if(statusUser==null){
                statusUser = "";
            }
            
            if(startTime == null){
                timerUser = "<html>Start: - </html>";
            }else{
                timerUser = "<html>Start: "+startTime+"</html>";
            }
            //---------------------------------------------------------------------------------------
            if(connect != null){
                    connect.close();
		}
		}catch (SQLException e){
                    e.printStackTrace();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
       public String connectDBForRepairAdminDetail(long clickDetailUser){ //แสดงสิ่งที่ user ส่งมาซ่อม
        String detail="";
        try{
            Connection connect = Database.connectDb("jan", "jan042");
      
            Statement st = connect.createStatement(); 
            //show detail user ส่งซ่อมจักรยาน
            String temp = "SELECT * FROM `Prepair_Desctiption` where id ="+clickDetailUser;
            ResultSet rs = st.executeQuery(temp);
            
            while(rs.next()){
                detail+="Id: "+rs.getInt("id")+"\n";
                detail+="Brand: "+rs.getString("brand")+"\n";
                detail+="Color: "+rs.getString("color")+"\n";
                detail+="Why Repair: "+rs.getString("other");
                int transID = rs.getInt("transID");
            }
            
            //------------------------------------------------------------------
            if(connect != null){
                    connect.close();
		}
		}catch (SQLException e){
                    e.printStackTrace();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return detail;
    }
       
    public void connectDBForRepairAdmin(){ //for jPanelRepairingAdmin
        try{
            Connection connect = Database.connectDb("jan", "jan042");
            
            Statement st = connect.createStatement(); 
            //ดึงเอา id ที่มาที่สุดออกมา เพื่อให้มันสามารถ insert ลง table ให้ไม่ซ้ำกันได้
            String temp = "SELECT MAX(id) AS countId FROM Prepair_Desctiption ";
            ResultSet rs = st.executeQuery(temp);

            while(rs.next()){
                this.countID = rs.getInt("countId");
            }
        }   
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    public String Status(boolean tem){ // รับมาจากปุ่มกด ถ้าเสร็จเป็น true ไม่เสร็จเป็น false
        if(tem==false){
            status="Not success!";
        }else if(tem==true){
            status="Success!";
        }
        return status;  
    }
    
    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    public String getBike() {
        return bike;
    }

    public void setBike(String bike) {
        this.bike = bike;
    }
    
    public String getStatus() {
        return status;
    }

    public String getTimerUser() {
        return timerUser;
    }
    
    public Repair(String problem, String detail) {
        this.problem = problem;
        this.detail = detail;
    }

    public String getWhyRepair() {
        return whyRepair;
    }

    public void setWhyRepair(String whyRepair) {
        this.whyRepair = whyRepair;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getRepairStateId() {
        return repairStateId;
    }

    public String getAskingUser() {
        return askingUser;
    }

    public String getRepairingUser() {
        return repairingUser;
    }

    public String getStatusUser() {
        return statusUser;
    }

    public long getTransIDAdminNotSuccess() {
        return transIDAdminNotSuccess ;
    }

    public String getAskingAdminNotSuccess() {
        return askingAdminNotSuccess ;
    }

    public String getRepairingAdminNotSuccess() {
        return repairingAdminNotSuccess ;
    }

    @Override
    public String toString() {
        return "Problem: " + problem 
                + "\nDetail: " + detail+"\n";
    }
    
}
