/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bike;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import bike.database.Database;
/**
 *
 * @author user
 */
public class AdminNews {
    private String header;
    private String description;
    private String author;
    private String dateUser;
    private static long newsId;
    private Database db = new Database();
    private Date date = new Date();
    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//    public String numOfNews[];
    
    public AdminNews(String header, String description) {
        this.header = header;
        this.description = description;
    }
    
    public AdminNews() {
        
    }
    
    public String showNews(){
        return "----"+header +"----\nContent : "+description;
    }
    
    public String[] selectTopicNews(){
        String[] allTopicNews = null;
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = Database.connectDb("win", "win016");
            Statement s = c.createStatement();
            String sql;
            sql = "SELECT COUNT(newsID) AS num FROM News";
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            int count = rs.getInt("num");
            allTopicNews = new String[count];
            
            sql = "SELECT newsID,newsDescription FROM News";
            rs = s.executeQuery(sql);
            for (int i = 0; i < allTopicNews.length; i++) {
                if(rs.next()){
                    allTopicNews[i] = "NumOfNews : "+rs.getString("newsID")+"   Description : "
                            +rs.getString("newsDescription");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();    //โชว์ข้อผิดพลาดทั้งหมด
        }
        try{
            c.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return allTopicNews;
    }
    public String[] selectDetailNews(){
        String[] detailNews = null;
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = Database.connectDb("win", "win016");
            Statement s = c.createStatement();
            String sql;
            sql = "SELECT COUNT(newsID) AS num FROM News";
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            int count = rs.getInt("num");
            detailNews = new String[count];
            
            sql = "SELECT newsDetails FROM News";
            rs = s.executeQuery(sql);
            for (int i = 0; i < detailNews.length; i++) {
                if(rs.next()){
                    detailNews[i] = rs.getString("detailNews");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();    //โชว์ข้อผิดพลาดทั้งหมด
        }
        try{
            c.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return detailNews;
    }
    
    public void insertNews(String head, String detail) {
        Connection c = null;
//        date = new Date();
        Date date= new Date();
        System.out.println(new Timestamp(date.getTime()));
        dateUser = df.format(date);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = Database.connectDb("win", "win016");
            Statement s = c.createStatement();
            newsId++;
            String sql = "insert into News VALUES ('"+newsId+"','"+head+"','"+detail+"','1111','"+new Timestamp(date.getTime())+"')";
            s.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();    //โชว์ข้อผิดพลาดทั้งหมด
        }
        try{
            c.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteNews(long newsId){
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = Database.connectDb("win", "win016");
            Statement s = c.createStatement();
//            newsId++;
            String sql = "DELETE FROM News WHERE newsId='"+newsId+"'";
            s.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();    //โชว์ข้อผิดพลาดทั้งหมด
        }
        try{
            c.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void editNews(String head, String detail, long newsId) {
        Connection c = null;
//        user = new Date();
//        date = df.format(user);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = Database.connectDb("win", "win016");
            Statement s = c.createStatement();
//            newsId++;
            String sql = "update GreenSociety.News SET newsDescription='"+head+"',newsDetails='"+detail+"'WHERE newsId='"+newsId+"'";
            s.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();    //โชว์ข้อผิดพลาดทั้งหมด
        }
        try{
            c.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void submitButton(int yesNo) {
        switch (yesNo) {
            case 1:
                System.out.println("Submit");
                insertNews(header, description);
            case 2:
                System.out.println("Close");
                header = "";
                description = "";
            //ทุกค่าเป็นค่าเริ่มต้น     
        }
    }
}
