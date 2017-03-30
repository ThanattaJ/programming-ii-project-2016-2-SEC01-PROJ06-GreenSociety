import java.sql.*;

public class Database {

    public static Connection connectDb(String user,String pass) {
        String url = "jdbc:mysql://139.59.231.113:3306/test";
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
}
