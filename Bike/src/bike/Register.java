package bike;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;

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
        boolean check = false;
        int countUserId = 0;
        ArrayList<String> listEmail= new ArrayList<String>();
        
         try{
            
            Connection connect = Database.connectDb("jan", "jan042");
            Class.forName("com.mysql.jdbc.Driver");
            
            Statement st = connect.createStatement(); 
            
            String temp = "SELECT email FROM `User`";
            ResultSet rs = st.executeQuery(temp);
            
            while(rs.next()){
                listEmail.add(rs.getString("email"));
            }
            
            for(int i=0;i<listEmail.size();i++){
                if(listEmail.get(i).equals(email)==false){
                    check=true;
                }else{
                    check=false;
                }
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
        return check; 
     }
    
    public boolean connectDBforCheckId(String id){
        boolean check=false;
        int countUserId = 0;
        ArrayList<String> listId= new ArrayList<String>();
        
         try{
            
            Connection connect = Database.connectDb("jan", "jan042");
            Class.forName("com.mysql.jdbc.Driver");
            
            Statement st = connect.createStatement(); 
            
            String temp = "SELECT deptID FROM `User`";
            ResultSet rs = st.executeQuery(temp);
                        
            while(rs.next()){
                listId.add(rs.getString("deptID"));
            }
            
            for(int i=0;i<listId.size();i++){
                if(listId.get(i).equals(id)==false){
                    check=true;
                }else{
                    check=false;
                }
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
        return check; 
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
            
            pass =new StringBuffer();
            for(int i=0;i<mdhash.length;i++){
                pass.append(Integer.toString((mdhash[i] & 0xff)+0x100,16).substring(1));
            }
        }
        
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
        return pass;
    }
    
}
