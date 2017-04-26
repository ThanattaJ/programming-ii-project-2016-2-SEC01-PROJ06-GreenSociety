package bike;

import java.sql.*;

public class CanCounter {
    private int cp = 0;
    private int cpUse = 0;
    private String action;
    private int count;

    public CanCounter() {

    }

    public void selectCP(){
     Connection con = null;
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            String sql = "SELECT can_point FROM User WHERE userID='12345'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                cp = rs.getInt("can_point");
            }else{
                cp = 0;
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

    public void selectTrans(){
        Connection con = null;
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            String sql = "SELECT * FROM CP_Transaction WHERE user_id='"+User.getUserId()+"'ORDER BY transactionID DESC LIMIT 1";
            ResultSet rs = s.executeQuery(sql);
            if(rs.last()){
                if(rs.getInt("withdraw")!= 0){
                    action = "Borrow";
                    count = rs.getInt("withdraw");
                }else if(rs.getInt("deposit")!= 0){
                    count = rs.getInt("deposit");
                    action = (count/50)+" Cans";
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

    public String updateCP(String name,String surname,long nCp){
        Connection con = null;
        String sql = "";
        int id = 0;
        int point = 0;
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            sql = "SELECT userID,can_point From User WHERE firstName='"+name+"' and lastName='"+surname+"'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.first()){
                rs.beforeFirst();
                while(rs.next()){
                    id = rs.getInt("userID");
                    point = rs.getInt("can_point");
                }
                point+=nCp;
                sql = "UPDATE User SET can_point='"+point+"' WHERE userID='"+id+"'";
                s.executeUpdate(sql);
            }else{
                return "Error";
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
        return null;
    }

    public void updateCP(long point){
        Connection con = null;
        String sql = "";
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            sql = "UPDATE User SET can_point='"+point+"' WHERE userID='12345'";
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

    public void insertTransDeposite(String name,String surname,long dep){
        Connection con = null;
        String sql = "";
        int userId = 0;
        int transId = 0;
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            sql = "SELECT userID From User WHERE firstName='"+name+"' and lastName='"+surname+"'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.first()){
                rs.beforeFirst();
                while(rs.next()){
                    userId = rs.getInt("userID");
                }
                sql = "SELECT MAX(transactionID) AS id FROM CP_Transaction";
                rs = s.executeQuery(sql);
                while(rs.next()){
                    transId = rs.getInt("id");
                }
                transId ++;
                sql = "INSERT INTO CP_Transaction VALUES ('"+transId+"','"+userId+"','"+dep+"',0)";
                s.executeUpdate(sql);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        try{
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void insertTransWithdraw(long wit){
        Connection con = null;
        String sql ="";
        int id = 0;
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();

            sql = "SELECT MAX(transactionID) AS id FROM CP_Transaction";
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                id = rs.getInt("id");
            }
            id += 1;
            sql = "INSERT INTO CP_Transaction VALUES ('"+id+"','12345',0,'"+wit+"')";
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

    public String getAction() {
        return action;
    }

    public int getCount() {
        return count;
    }


    public int getCp() { //แสดงค่า Cp
        return cp;
    }

    public void setCpUse(int cpUse) {
        this.cpUse = cpUse;
    }

    public int getCpUse() {
        return cpUse;
    }
    
    public void checkName(String userName,String userSurname, int nCp) throws SelectMemberNameException{
        String tmp = updateCP(userName,userSurname,nCp);
        if(tmp != null && tmp.equals("Error")){
            SelectMemberNameException smn = new SelectMemberNameException();
            throw smn;
        }
    }
    
    public void increseCp(String userName,String userSurname,int c) throws SelectMemberNameException{ //เพิ่มCpเอากระป๋องมาแลก
        int nCp = c * 50;
        checkName(userName, userSurname, nCp);
        insertTransDeposite(userName,userSurname,nCp);
    }

    public void decreseCp() { //ลดCpตอนเริ่มยืม
        cp -= cpUse;
        insertTransWithdraw(cpUse);
        updateCP(cp);
    }

    public void checkCp() throws CanCountException { //check Cpของuserกับcpทั้งหมดที่ต้องนำมาใช้แลกเพื่อยืม
        if (cpUse>cp){
            CanCountException cce = new CanCountException();
            throw cce;
        }
    }

    public void countCpBorrow(int[] numBike) { //คำนวนCp ที่จะต้องใช้แลกยืม
        int temp = 0;
        if (numBike[0] > 0) {
            temp += numBike[0] * 100;
        }
        if (numBike[1] > 0) {
            temp += numBike[1] * 125;
        }
        if (numBike[2] > 0) {
            temp += numBike[2] * 150;
        }

        if (numBike[3] > 0) {
            temp += numBike[3] * 60;
        }
        if (numBike[4] > 0) {
            temp += numBike[4] * 80;
        }
        cpUse = temp;
    }
}