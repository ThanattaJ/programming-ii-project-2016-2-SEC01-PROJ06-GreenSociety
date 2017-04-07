package bike;

import java.sql.*;

public class CanCounter {
    private int cp = 1000;
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
            String sql = "SELECT can_point WHERE user_id='12345'";
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
            String sql = "SELECT * WHERE user_id='12345'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.last()){
                if(rs.getInt("deposite")!= 0){
                    action = "Borrow";
                    count = rs.getInt("deposite");
                }else if(rs.getInt("withdraw")!= 0){
                    count = rs.getInt("withdraw");
                    action = (count/50)+"";
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
    
    public void updateCP(long point){
        Connection con = null;
        try{
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            String sql = "UPDATE User SET can_point='"+point+"' WHERE userID='12345'";
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
    
    public void insertTransDeposite(long dep){
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
            sql = "INSERT INTO CP_Transaction VALUES ('"+id+"','111','"+dep+"',0)";
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
            sql = "INSERT INTO CP_Transaction VALUES ('"+id+"','111',0,'"+wit+"')";
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
    
    public void increseCp(int c) { //เพิ่มCpเอากระป๋องมาแลก
        int nCp = c * 50;
        cp += nCp;
        insertTransDeposite(nCp);
        updateCP(cp);
    }

    public void decreseCp() { //ลดCpตอนเริ่มยืม
        cp -= cpUse;
        insertTransWithdraw(cpUse);
        updateCP(cp);
    }
    
    public boolean checkCp() throws CanCountException { //check Cpของuserกับcpทั้งหมดที่ต้องนำมาใช้แลกเพื่อยืม
        boolean temp = false;
        if (cpUse <= cp) {
            temp = true;
        }else{
            CanCountException cce = new CanCountException();
            throw cce;
        }
        return temp;
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
