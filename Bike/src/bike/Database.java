package bike;

import java.sql.*;
import java.util.Date;

public class Database {

    public Database() {
    }

    public static Connection connectDb(String user,String pass) {
        String url = "jdbc:mysql://10.4.53.34:3306/Green_Society";
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url,user,pass);
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Database db = null;
        String a = "aa";
        Connection connect = null;
        Statement s;
        try {
//           INSERT INTO
//            connect = Database.connectDb("ja","jaja036");
//            s = connect.createStatement();
//            String sql = "INSERT INTO Green_Society.`User` (userID, firstName, lastName, gender, birthDate, congenitialDisease, email, tel, deptID, can_point, password) \n" +
//"VALUES (1, 'T', 'O', 'M', '1998-03-06', 'Eiei', 'winnerzaza@gmail.com', '0819999999', '1234', 190, 'green2559')";
//            s.execute(sql);

            //Select
            connect = Database.connectDb("ja","jaja036");
            s = connect.createStatement();
            String sql = "SELECT dateTime FROM Transaction Where transID='1'";
            ResultSet rec = s.executeQuery(sql);
            while (rec.next()) {
                  Date d = rec.getTimestamp("dateTime");
                  System.out.println(d.getDate()+"/"+(d.getMonth()+1)+"/"+(d.getYear()+1900 )+"   "+d.getHours()+" : "+d.getMinutes()+" : "+d.getSeconds());
            }
//            System.out.println(d.getDate()+"/"+d.getMonth()+"/"+d.getYear()+"   "+d.getHours()+" : "+d.getMinutes()+" : "+d.getSeconds());
//            String sql = "SELECT MAX(transactionID) AS id FROM CP_Transaction";
//            ResultSet rs = s.executeQuery(sql);
//            while(rs.next()){
//                int id = rs.getInt("id");
//                System.out.println(id);
//            }

            //UPDATE
//            connect = Database.connectDb();
//            s = connect.createStatement();
//            String sql = "UPDATE bosz.test_bosz SET t2='100' WHERE t1 = 'aa'";
//            s.execute(sql);

            //DELETE
//            connect = Database.connectDb("ja","jaja036");
//            s = connect.createStatement();
//            String sql = "DELETE FROM bosz.test_bosz WHERE t1 = 'aa'";
//            s.execute(sql);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
