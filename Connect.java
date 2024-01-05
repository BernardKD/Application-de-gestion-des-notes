
package Etudiant;

import java.sql.Connection;
import java.sql.DriverManager;


public class Connect {
    Connection cn;
    public Connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/livre","bernard","Passer@123");
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

