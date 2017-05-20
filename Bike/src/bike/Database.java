package bike;

import java.sql.*;

public class Database {

    public Database() {
    }

    public static Connection connectDb(String user,String pass) {
        String url = "jdbc:mysql://10.4.53.34:3306/Green_Society?useUnicode=true&characterEncoding=utf8";
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
