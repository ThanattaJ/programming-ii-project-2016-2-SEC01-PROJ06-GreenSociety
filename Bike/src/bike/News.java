package bike;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class News {
    private ArrayList<Integer>  idCanShow = new ArrayList<Integer>();
    
    
    public News() {
        
    }
    
    public ArrayList<Integer> getIdCanShow() {
        return idCanShow;
    }
    
    public String[] selectTopicNews(){
        String[] allTopicNews = null;
        Connection c = null;
        idCanShow.clear();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = Database.connectDb("win", "win016");
            Statement s = c.createStatement();
            String sql;
            sql = "SELECT COUNT(newsID) AS num FROM News WHERE newsDescription IS NOT NULL";
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            int count = rs.getInt("num");
            allTopicNews = new String[count];
            
            sql = "SELECT * FROM `News` WHERE newsDescription IS NOT NULL";
            rs = s.executeQuery(sql);
            for (int i = 0; i < allTopicNews.length; i++) {
                if(rs.next()){
                    idCanShow.add(rs.getInt("newsID"));
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
    
    //method สำหรับ หน้าแรกของ user
    public String[] selectTopicNewsForUser(){
        String[] allTopicNews = null;
        Connection c = null;
        idCanShow.clear();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = Database.connectDb("win", "win016");
            Statement s = c.createStatement();
            String sql;
            sql = "SELECT COUNT(newsID) AS num FROM News WHERE newsDescription IS NOT NULL";
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            int count = rs.getInt("num");
            allTopicNews = new String[count];
            
            sql = "SELECT * FROM `News` WHERE newsDescription IS NOT NULL";
            rs = s.executeQuery(sql);
            for (int i = 0; i < allTopicNews.length; i++) {
                if(rs.next()){
                    idCanShow.add(rs.getInt("newsID"));
                    allTopicNews[i] = rs.getString("newsDescription");
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
    
    public String[] selectImgNewsForUser(){
        String[] allPathNews = null;
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = Database.connectDb("win", "win016");
            Statement s = c.createStatement();
            String sql;
            sql = "SELECT COUNT(newsID) AS num FROM News WHERE newsDescription IS NOT NULL";
            ResultSet rs = s.executeQuery(sql);
            rs.first();
            int count = rs.getInt("num");
            allPathNews = new String[count];
            
            sql = "SELECT * FROM `News` WHERE newsDescription IS NOT NULL";
            rs = s.executeQuery(sql);
            for (int i = 0; i < allPathNews.length; i++) {
                if(rs.next()){
                    allPathNews[i] = rs.getString("img");
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
        return allPathNews;
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
    public String topicNewsSelect(int selectTopic){
        String showTopic="";
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = Database.connectDb("win", "win016");
            Statement s = c.createStatement();
            String sql;
            sql = "SELECT * FROM News WHERE newsID="+selectTopic;
            
            ResultSet rs = s.executeQuery(sql);
            
            while(rs.next()){
                showTopic=rs.getString("newsDescription");
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
        return showTopic;
    }
    public String detailNewsSelect(int selectNews){
        String showNews="";
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = Database.connectDb("win", "win016");
            Statement s = c.createStatement();
            String sql;
            sql = "SELECT newsDetails FROM News where newsID= "+selectNews;
            ResultSet rs = s.executeQuery(sql);
            
            while(rs.next()){
                showNews=rs.getString("newsDetails");
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
        return showNews;
    }
    
    public void insertNews(String head, String detail,String path) {
        Connection c = null;
        String typeFile = null,newPath=null;
        int newsId = 0;
        Date date= new Date();
        if(!path.equalsIgnoreCase("No Select File.")){
            typeFile = path.substring(path.length()-4,path.length());
        }
        try {
            c = Database.connectDb("win", "win016");
            Statement s = c.createStatement();
            String sql = "select MAX(newsID) as id From News";
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                newsId = rs.getInt("id");
            }
            newsId++;
            if(!path.equalsIgnoreCase("No Select File.")){
                newPath = "/bike_gui/newsPic/"+newsId+typeFile;
                copyFileImg(path,newPath);
            }else{
                newPath = "No Picture.";
            }
            sql = "insert into News VALUES ('"+newsId+"','"+head+"','"+detail+"','"+User.getUserId()+"','"+new Timestamp(date.getTime())+"','"+newPath+"')";
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

    public void deleteNews(long tempNewsId){
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = Database.connectDb("win", "win016");
            Statement s = c.createStatement();
            String sql = "UPDATE News SET newsDescription = null WHERE newsID='"+tempNewsId+"'";
            
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
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = Database.connectDb("win", "win016");
            Statement s = c.createStatement();
            String sql = "update News SET newsDescription='"+head+"',newsDetails='"+detail+"'WHERE newsId='"+newsId+"'";
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
    
        
    public void copyFileImg(String sourceFile,String targetFile){
        try{
            FileInputStream fis = new FileInputStream(sourceFile);
            FileOutputStream fos = new FileOutputStream(targetFile);
            
            byte[] data = new byte[1024];
            int numFile;
            while((numFile=fis.read(data))!=-1){
                fos.write(data, 0, numFile);
            }
            fis.close();
            fos.close();
        }
        catch(FileNotFoundException fnfe){
            System.out.println(fnfe);
        }
        catch(IOException ioe){
            System.out.println(ioe);
        }
    }
    
}
