package SuperProf;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    
    Connection cn;
    public Connect(String datab, String userx, String passx){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/"+datab,userx,passx);
            System.out.println("Connection Etablie");
        }catch(Exception e){
            System.out.println("Erreur de connection");
            e.printStackTrace();
        }
        
    }
    public Connection maConnection(){
        return cn;
    }
    
}
