package bike;

import java.sql.*;
import java.util.Scanner;
public class History {
    private long historyId;

    public History(){
    }
    
    public void HistoryByAdmin(String itemId,Timestamp nowDate,Timestamp returnDate,String input){
        int i = 0;
        try{
            Connection connect = Database.connectDb("jan", "jan042");
            Class.forName("com.mysql.jdbc.Driver");
            
            Statement st = connect.createStatement(); 
            
            String temp3 = "SELECT MAX(transID) AS id FROM Transaction";
            ResultSet rs1 = st.executeQuery(temp3);
            
            while(rs1.next()){
                i = rs1.getInt("id"); 
            }
            i++;
            String transId = "\'"+i+"\'";
            itemId = "\'"+itemId+"\'";
            String userId = "'12345'";
            String action = "\'"+input+"\'";
            String officerId = "'100'";
        
            
            String temp ="INSERT INTO Transaction VALUES " //set เธ�เน�เธฒเน�เธซเน�เธ�เธฑเธ� Database
                    + "("+transId+","
                    +"'"+nowDate+"','"
                    +returnDate+"',"
                    +itemId + ","
                    +userId+","
                    +action+"," 
                    +officerId+")";

            st.executeUpdate(temp);
                   
          try {
		if(connect != null){
                    connect.close();
		}
		}catch (SQLException e){
                    e.printStackTrace();
		}                
        }
        
        catch(ClassNotFoundException cfe){
            System.out.println(cfe);
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        
    }
    
    public String showActionUser(long id){ //user เน�เธชเน�เน�เธญเธ”เธตเธ•เธฑเธงเน€เธญเธ�เธ—เธตเน�เธ•เน�เธญเธ�เธ�เธฒเธฃเธฃเธนเน�เธ�เธฃเธฐเธงเธฑเธ•เธดเธ�เธฒเธฃเน�เธ�เน�เธ�เธฒเธ�เธ�เธญเธ�เธ•เธฑเธงเน€เธญเธ�
        String output="";
        int statUser=0;
        try{
            Connection connect = Database.connectDb("jan", "jan042");
            Class.forName("com.mysql.jdbc.Driver");
            
            Statement st = connect.createStatement();
            String temp4 = "select * from `Transaction` where UserId LIKE "+id;
            ResultSet rs4 = st.executeQuery(temp4);
            while(rs4.next()){
                output+=("UserId: "+id+"\n");
                output+=("dateTime: " + rs4.getTimestamp("dateTime")+"\n");
                output+=("itemID: " + rs4.getString("itemID")+"\n");
                output+=("action: "+ rs4.getString("action")+"\n");
                statUser++;
                output+=("----------------------------------------------\n");
            }
            output+=("userID: "+id+"\n"+"The stat of user: "+statUser+"\n");
            
            try {
		if(connect != null){
                    connect.close();
		}
		}catch (SQLException e){
                    e.printStackTrace();
		}      
        }
        catch(ClassNotFoundException cfe){
            System.out.println(cfe);
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return output;
    }
    
    public String statGreensociaty(){
        String output="";
        int statRepair=0;
        int statBorrow=0;
        int statReturn=0;
        try{
            Connection connect = Database.connectDb("jan", "jan042");
            Class.forName("com.mysql.jdbc.Driver");
        
            Statement st = connect.createStatement();
            
            String temp5 = "select * from `Transaction` where action='repair'";
            ResultSet rs5 = st.executeQuery(temp5);
            output+=("-------------------STATUSER---------------------\n");
            while(rs5.next()){
                statRepair++;
            }
            output+=("The stat of repir: "+statRepair+"\n");
            output+=("-----------------------------------------------\n");
            
            String temp6 = "select * from `Transaction` where action='barrow'";
            ResultSet rs6 = st.executeQuery(temp6);
            while(rs6.next()){
                statBorrow++;
            }
            output+=("The stat of borrow: "+statBorrow+"\n");
            output+=("-----------------------------------------------\n");
            
            String temp7 = "select * from `Transaction` where action='barrow'";
            ResultSet rs7 = st.executeQuery(temp6);
            while(rs7.next()){
                statReturn++;
            }
            output+=("The stat of return: "+statReturn+"\n");
            output+=("-----------------------------------------------");
            
            try {
		if(connect != null){
                    connect.close();
		}
		}catch (SQLException e){
                    e.printStackTrace();
		}      
        }
        catch(ClassNotFoundException cfe){
            System.out.println(cfe);
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return output;
    }

    public long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(long historyId) {
        this.historyId = historyId;
    }

    
    public String toString(){
        String output = "";
        output+="historyId: "+historyId;
        return output;
    }
    
    
}
