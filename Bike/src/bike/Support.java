package bike;

import java.sql.*;
public class Support {
    private String search;
    private String output=""; 

    public Support() {
    }
    
    public Support(String search) {
        this.search = search;
    }
    
    public void searchSupport(String search){
        output = "";
        try{
            Connection connect = Database.connectDb("jan", "jan042");
            Class.forName("com.mysql.jdbc.Driver");
        
            Statement st = connect.createStatement(); 
            String temp = "SELECT * FROM Manual WHERE manualDescription LIKE '%"+search+"%' OR manualDetails LIKE '%"+search+"%'";
            ResultSet rs = st.executeQuery(temp);
    
            while(rs.next()){
                output+=("manualID : " + rs.getString("manualID"))+"\n";
                output+=("manualDescription : \n" + rs.getString("manualDescription"))+"\n";
                output+=("manualDetails : " + rs.getString("manualDetails")+"\n");
                output+=("----------------------------------------------\n");
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

    public void insertSupport(String title,String content){
        int id = 0;
        String insertId = null;
        try{
            Connection connect = Database.connectDb("jan", "jan042");
            Class.forName("com.mysql.jdbc.Driver");
        
            Statement st = connect.createStatement(); 
            String sql = "SELECT MAX(manualID) AS id FROM Manual";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
               id = rs.getInt("id");
            }
            
            id++;
            String temp = id+"";
            if(temp.length()==1){
                insertId = "00"+id;
            }else if(temp.length()==2){
                insertId = "0"+id;
            }else{
                insertId = id+"";
            }
            
            sql = "INSERT INTO Manual VALUES('"+insertId+"','"+title+"','"+content+"')";
            st.executeUpdate(sql);
            
            try {
		if(connect != null){
                    connect.close();
		}
		}catch (SQLException e){
                    e.printStackTrace();
		}
        }catch(ClassNotFoundException cfe){
            System.out.println(cfe);
        }catch(Exception ex){
            System.out.println(ex);
        }
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
