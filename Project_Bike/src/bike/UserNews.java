/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bike;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import bike.database.Database;
/**
 *
 * @author user
 */
public class UserNews {
    private String nameNews;
    private String descrip;
//    private UserProfile up = new UserProfile();
    public UserNews(String nameNews, String descrip) {
        this.nameNews = nameNews;
        this.descrip = descrip;
    }
    
    public UserNews(){
        
    }

    public String getNameNews() {
        return nameNews;
    }

    public void setNameNews(String nameNews) {
        this.nameNews = nameNews;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
    public void keepNews(){
        try{
            Database cndb = new Database();
            Connection connect = Database.connectDb("win", "win016");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Database connecting");
        
            Statement st = connect.createStatement(); 
            String temp = "SELECT * FROM News";
            
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
        System.out.println("");
    }
    public void getNews(int newsID){
        try{
            Database cndb = new Database();
            Connection connect = Database.connectDb("win", "win016");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Database connecting");
        
            Statement st = connect.createStatement(); 
            String temp = "SELECT * FROM News WHERE newsID="+newsID;
            ResultSet rs = st.executeQuery(temp);
            
            st = connect.createStatement();
            String temp2 = "INSERT into News_Reader (newsID, userID) VALUES (12345,12345)";
            st.executeUpdate(temp2);
    
            while(rs.next()){
                System.out.println("newsID: " + rs.getInt("newsID"));
                System.out.println("newsDescription: " + rs.getString("newsDescription"));
                System.out.println("newslDetails: " + rs.getString("newsDetails"));
                System.out.println("----------------------------------------------");
            }
            
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
    public void showNews(){
        try{
            Database cndb = new Database();
            Connection connect = Database.connectDb("win", "win016");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Database connecting");
        
            Statement st = connect.createStatement(); 
            String temp = "SELECT * FROM GreenSociety.News";
            ResultSet rs = st.executeQuery(temp);
    
            while(rs.next()){
                System.out.println("newsID: " + rs.getInt("newsID"));
                System.out.println("newsDescription: " + rs.getString("newsDescription"));
                System.out.println("newslDetails: " + rs.getString("manualDetails"));
                System.out.println("----------------------------------------------");
            }
            
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
        System.out.println("");
                                 
    }
    public void insertData() {
        Connection c = null;
        try {
            c = Database.connectDb("win", "win016");
            Statement s = c.createStatement();
            String sql = "INSERT into News_Reader VALUES userID,newsID='";
            s.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();    //โชว์ข้อผิดพลาดทั้งหมด
        }
        try {
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
