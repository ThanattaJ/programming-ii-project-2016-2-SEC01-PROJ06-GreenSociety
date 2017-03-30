import java.security.*;
import java.sql.*;
import java.sql.Timestamp;
import java.util.Date;

public class Authenication {

    private long id = 0;

    public void login(String username, String password) {
        String passFromDatabase = "";
        Connection con = null;
        try {
            con = Database.connectDb("ja", "jaja36");
            Statement st = con.createStatement();
            String sql = "SELECT password From User Where email='" + username + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                while (rs.next()) {
                    passFromDatabase = rs.getString("password");
                }

                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                byte[] pass = new byte[32];
                pass = md.digest();
                StringBuffer sb = new StringBuffer();

                for (int i = 0; i < pass.length; i++) {
                    sb.append(Integer.toString((pass[i] & 0xff) + 0x100, 16).substring(1));
                }
                if (sb.toString().equals(passFromDatabase)) {
                    System.out.println("Login Success");
                } else {
                    System.out.println("Username or Password is incorrect");
                }
            } else {
                System.out.println("Username or Password is incorrect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try{
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void insertData(String firstName, String lastName, String gender, Date birthday, String disease, String email, String tel, String deptId,StringBuffer pass) {
        Connection con = null;
        Timestamp birthDate = new Timestamp(birthday.getTime());
        String congenitialDisease;
        if (disease == null) {
            congenitialDisease = "-";
        }else{
            congenitialDisease = disease;
        }
        
        try {
            con = Database.connectDb("ja", "jaja036");
            Statement st = con.createStatement();
            id++;
            String sql = "INSERT INTO User VALUES('" + id + "','" + firstName + "','" + lastName + "','" + gender + "'," + birthDate + ",'" + congenitialDisease + "','" + email + "','" + tel + "','" + deptId +"','"+pass.toString()+"')";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try{
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
