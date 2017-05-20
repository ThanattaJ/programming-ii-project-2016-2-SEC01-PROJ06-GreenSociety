package bike;

import java.io.*;
import java.security.*;
import java.sql.*;
import java.sql.Timestamp;
import java.util.Date;

public class Authenication {

    public Authenication() {

    }

    public boolean login(String username, String psw) {
        boolean accept = false;
        String passFromDatabase = "";
        Connection con = null;
        if (username != null && psw != null) {
            try {
                con = Database.connectDb("ja", "jaja036");
                Statement st = con.createStatement();
                String sql = "SELECT * From User Where email='" + username + "'";
                ResultSet rs = st.executeQuery(sql);
                if (rs.first()) {
                    rs.beforeFirst();
                    while (rs.next()) {
                        passFromDatabase = rs.getString("password");
                    }

                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(psw.getBytes());
                    byte[] pass = new byte[32];
                    pass = md.digest();
                    StringBuffer sb = new StringBuffer();

                    for (int i = 0; i < pass.length; i++) {
                        sb.append(Integer.toString((pass[i] & 0xff) + 0x100, 16).substring(1));
                    }

                    if (sb.toString().equals(passFromDatabase)) {
                        accept = true;
                        rs.beforeFirst();
                        while (rs.next()) {
                            Date dt = rs.getTimestamp("birthDate");
                            User.setUserId(rs.getInt("userID"));
                            User.setFirstName(rs.getString("firstName"));
                            User.setLastName(rs.getString("lastName"));
                            User.setGender(rs.getString("gender"));
                            User.setBirthDate((dt.getYear() + 1900) + "-" + (dt.getMonth() + 1) + "-" + dt.getDate());
                            User.setCongenitialDisease(rs.getString("congenitialDisease"));
                            User.setEmail(rs.getString("email"));
                            User.setTel(rs.getString("tel"));
                            User.setDeptId(rs.getString("deptID"));
                            User.setImgPath(rs.getString("img"));
                        }

                        String tmp = User.getDeptId().substring(0, 2);
                        sql = "SELECT * FROM Department WHERE deptID='" + tmp + "'";
                        rs = st.executeQuery(sql);
                        if (rs.next()) {
                            User.setDept(rs.getString("deptName"));
                        }

                        sql = "SELECT * FROM Officer WHERE userID='" + User.getUserId() + "'";
                        rs = st.executeQuery(sql);
                        if (rs.next()) {
                            User.setOfficeId(rs.getString("officerID"));
                            User.setPositon(rs.getString("position"));
                        } else {
                            User.setOfficeId("-");
                            User.setPositon("-");
                        }

                        return accept;
                    } else {
                        return accept;
                    }
                } else {
                    return accept;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return accept;
    }

    public void insertDataUser(String firstName, String lastName, String gender, Date birthday, String disease, String email,
            String tel, String deptId, StringBuffer pass, String position,String pathImg) {
        Connection con = null;
        String newPath="";
        String sql = "";
        int id = 0;
        deptId = deptId.substring(2,6);
        Timestamp birthDate = new Timestamp(birthday.getTime());
        String congenitialDisease;
        if (disease == null) {
            congenitialDisease = "-";
        } else {
            congenitialDisease = disease;
        }
        
        if(pathImg.equalsIgnoreCase("default")){
            newPath = "/bike_gui/userProfile/default.png";
        }else{
            String typeFile = pathImg.substring(pathImg.indexOf("."),pathImg.length());
            newPath = "/src/bike_gui/userProfile/"+User.getUserId()+typeFile;
            copyFileImg(pathImg,newPath);
        }
        try {
            con = Database.connectDb("ja", "jaja036");
            Statement s = con.createStatement();

            sql = "SELECT MAX(userID) AS id FROM User";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("id");
            }

            id++;
            sql = "INSERT INTO User VALUES('" + id + "','" + firstName + "','" + lastName + "','" + gender
                    + "','" + birthDate + "','" + congenitialDisease + "','" + email + "','" + tel + "','" + deptId + "','0','" + pass.toString() + "','"+newPath+"')";
            s.executeUpdate(sql);
            if (position.equalsIgnoreCase("Technician") || position.equalsIgnoreCase("Officer")) {
                insertOfficer(position, id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertOfficer(String position, long idUser) {
        Connection con = null;
        String sql = "";
        int idOfficer = 0;
        try {
            con = Database.connectDb("ja", "jaja036");
            Statement s = con.createStatement();

            sql = "SELECT MAX(Officer) AS id FROM Officer";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                idOfficer = rs.getInt("id");
            }

            idOfficer++;
            sql = "INSERT INTO User VALUES('" + idOfficer + "','" + idUser + "','" + position + "')";
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateProfile(String name,String gender,String birth,String congen,String email,String tel,String path) {
        Connection con = null;
        String sql = "";
        String newPath = "";
        int i = name.indexOf(" ");
        String firstname = name.substring(0,i);
        String lastname = name.substring(i+1,name.length());
        
        if(!path.equalsIgnoreCase(User.getImgPath())){
            String typeFile = path.substring(path.length(),path.length());
            newPath = "/bike_gui/userProfile/"+User.getUserId()+typeFile;
            copyFileImg(path,newPath);
        }else{
            newPath = User.getImgPath();
        }
        
        try {
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            sql = "UPDATE User SET firstName='"+firstname+"',lastName='"+lastname+"',gender='"+gender+"',birthDate='"+birth+"',congenitialDisease='"+congen+"',email='"+email+"',tel='"+tel+"',img='"+newPath+"' WHERE userID='"+User.getUserId()+"'";
            s.executeUpdate(sql);
            setUserData();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            con.close();
        } catch (Exception e) {
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
    
    public void setUserData(){
        Connection con = null;
        String sql = "";
        try {
            con = Database.connectDb("ja", "jaja036");
            Statement s = con.createStatement();

            sql = "SELECT * FROM User Where userID='"+User.getUserId()+"'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                Date dt = rs.getTimestamp("birthDate");
                User.setUserId(rs.getInt("userID"));
                User.setFirstName(rs.getString("firstName"));
                User.setLastName(rs.getString("lastName"));
                User.setGender(rs.getString("gender"));
                User.setBirthDate((dt.getYear() + 1900) + "-" + (dt.getMonth() + 1) + "-" + dt.getDate());
                User.setCongenitialDisease(rs.getString("congenitialDisease"));
                User.setEmail(rs.getString("email"));
                User.setTel(rs.getString("tel"));
                User.setImgPath(rs.getString("img"));
            }
            
        }catch(SQLException sqe){
            sqe.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void forgetPass(String email,String psw) {
        Connection con = null;
        try {
            
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(psw.getBytes());
            byte[] pass = new byte[32];
            pass = md.digest();
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < pass.length; i++) {
                sb.append(Integer.toString((pass[i] & 0xff) + 0x100, 16).substring(1));
            }
            con = Database.connectDb("ja","jaja036");
            Statement s = con.createStatement();
            String sql = "UPDATE User SET password='"+sb.toString()+"' WHERE email='"+email+"'";
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
