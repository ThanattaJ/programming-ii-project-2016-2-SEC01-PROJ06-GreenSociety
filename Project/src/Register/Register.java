/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Register;

import ConnectDB.ConnectDatabase;
import Timer.Timer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;
import java.util.Scanner;

/**
 *
 * @author January
 */
public class Register {
    private String name;
    private String surname;
    private String gender;
    private Date birthDate;
    private String conDisease;
    private String email;
    private String tel;
    private String deptID;
    private String password;
    private StringBuffer sb;
    private String position;
    
    public Register() {
    }
    
    public boolean connectDBforCheckEmail(String email){
        boolean check=false;
        int countUserId = 0;
        String []emailDb;
       
         try{
            
            ConnectDatabase cndb = new ConnectDatabase();
            Connection connect = ConnectDatabase.connectDb("jan", "jan042");
            Class.forName("com.mysql.jdbc.Driver");
            
            Statement st = connect.createStatement(); 
            
            String maxRow = "SELECT COUNT(userId) AS countUserId FROM User";
            ResultSet rsMaxRow = st.executeQuery(maxRow);
            
            while(rsMaxRow.next()){
                countUserId = rsMaxRow.getInt("countUserId");
            }
            emailDb = new String[countUserId];
            
            
            String temp = "SELECT email,deptID FROM `User`";
            ResultSet rs = st.executeQuery(temp);
            
            while(rs.next()){
                for (int i=0; i<emailDb.length ;i++) {
                    emailDb[i]=rs.getString("email");
                }
                
            }
            
             for (int i = 0; i < emailDb.length; i++) {
                if(emailDb[i].equalsIgnoreCase(email))
                    check=false;
                else
                    check=true;
                
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
        return false; 
     }
    
    public boolean connectDBforCheckId(String id){
        boolean check=false;
        int countUserId = 0;
        String []idDb;
        
         try{
            
            ConnectDatabase cndb = new ConnectDatabase();
            Connection connect = ConnectDatabase.connectDb("jan", "jan042");
            Class.forName("com.mysql.jdbc.Driver");
            
            Statement st = connect.createStatement(); 
            
            String maxRow = "SELECT COUNT(userId) AS countUserId FROM User";
            ResultSet rsMaxRow = st.executeQuery(maxRow);
            
            while(rsMaxRow.next()){
                countUserId = rsMaxRow.getInt("countUserId");
            }
            idDb = new String[countUserId];
            
            String temp = "SELECT email,deptID FROM `User`";
            ResultSet rs = st.executeQuery(temp);
            
            while(rs.next()){
                for(int j=0;j<idDb.length;j++){
                    idDb[j] = rs.getString("deptID");
                }
            }
            
            for(int j=0; j < idDb.length; j++){
                if(idDb[j].equalsIgnoreCase(id))
                    check=false;
                else
                    check=true;
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
        return false; 
     }

    public Register(String name, String surname, String gender, Date birthDate, 
            String conDisease, String email, String tel, String deptID, 
            String position) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.conDisease = conDisease;
        this.email = email;
        this.tel = tel;
        this.deptID = deptID;
        this.position = position;
    }

    
    public void position(String position){
        switch(position){
            case "Technician": this.position="Technician" ;
            case "Personal" : this.position="Personal";
            case "Professor" : this.position="Professor";
            case "Officer" : this.position="Officer";
        }
    }
    
    
   public StringBuffer encocdMd5(String input){ //เอา Password มาแปลงเป็น md5
        StringBuffer pass = null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[]mdhash = new byte[32];
            mdhash = md.digest();
            
            String hash = DatatypeConverter.printHexBinary(mdhash).toLowerCase();
            System.out.println("Datatype Converter: "+hash);
            
            pass =new StringBuffer();
            for(int i=0;i<mdhash.length;i++){
                pass.append(Integer.toString((mdhash[i] & 0xff)+0x100,16).substring(1));
            }
            System.out.println("String Buffer toString: "+ pass.toString());
        }
        
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
        return pass;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getConDisease() {
        return conDisease;
    }

    public void setConDisease(String conDisease) {
        this.conDisease = conDisease;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDeptID() {
        return deptID;
    }

    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StringBuffer getSb() {
        return sb;
    }

    public void setSb(StringBuffer sb) {
        this.sb = sb;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

   
    
}
