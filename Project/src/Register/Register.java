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
    
    public void password(String oldPass,String newPass){//พาสเวิร์ดที่ user ใส่มาสองรอบต้องเป็นตัวเดียวกัน
        Scanner sc =new Scanner(System.in);
        if(oldPass==newPass){
           password = newPass;
        }else{
            while(oldPass!=newPass){
                System.out.print("Password: ");
                oldPass = sc.next();
                System.out.print("Confirm Password: ");
                newPass = sc.next();
            }
            password = newPass;
        }
    }
    
    public void encocdMd5(){ //เอา Password มาแปลงเป็น md5
        String input = password;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[]mdhash = new byte[32];
            mdhash = md.digest();
            
            String hash = DatatypeConverter.printHexBinary(mdhash).toLowerCase();
            System.out.println("Datatype Converter: "+hash);
            
            sb =new StringBuffer();
            for(int i=0;i<mdhash.length;i++){
                sb.append(Integer.toString((mdhash[i] & 0xff)+0x100,16).substring(1));
            }
            System.out.println("String Buffer toString: "+ sb.toString());
        }
        
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
    
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
