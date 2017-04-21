/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Support;

import ConnectDB.ConnectDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author January
 */
public class Support {
    private String search;
    private String output=""; 
    
    public void searchSupport(String search){
        String t1 = search;
        String t2 = "\'%"+t1+"%\'";
        try{
            ConnectDatabase cndb = new ConnectDatabase();
            Connection connect = ConnectDatabase.connectDb("jan", "jan042");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Database connecting");
        
            Statement st = connect.createStatement(); 
            String temp = "SELECT * FROM Manual WHERE manualDescription LIKE "+t2;
            ResultSet rs = st.executeQuery(temp);
    
            while(rs.next()){
                output+=("manualID: " + rs.getString("manualID"))+"\n";
                output+=("manualDescription: " + rs.getString("manualDescription"))+"\n";
                output+=("manualDetails: " + rs.getString("manualDetails")+"\n");
                output+=("----------------------------------------------");
//                System.out.println(output);
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
        System.out.println("");
    }
    
    public String contact(){
        String detail="";
        detail+="Address:\n"
                +"Keas 69 Str.\n"
                +"12345, Chalandri \n"
                +"Athens\n"
                +"Greece\n"
                +"Phone number: 012-3456789\n"
                +"Line: @GreenSociety\n"
                +"Facebook: GreenSocietyKMUTT";
        return detail;
    }

    
    public Support() {
    }

    public Support(String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
    
    

    @Override
    public String toString() {
        return "Support{" + "search=" + search + '}';
    }
    
    
}
