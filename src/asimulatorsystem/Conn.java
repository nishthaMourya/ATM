
package ASimulatorSystem;

import java.sql.*;  

public class Conn{
    Connection c;
    Statement s;
    public Conn(){  
        try{  //MYSQL is an external entity there is a chanses of error, that come at run time so we use exeption handling
            Class.forName("com.mysql.cj.jdbc.Driver");  
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem", "root", "2021ca069");
            s =c.createStatement(); 
           
          
            
        }catch(Exception e){ 
            System.out.println(e);
        }  
    }  
}  
