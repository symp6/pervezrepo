
package Controller;

import Beans.Actor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Crud {
    
   public String checkunique(String actorname) throws ClassNotFoundException, SQLException{
       String validity="true";
      Connection con;
      ResultSet rs;
      Class.forName("oracle.jdbc.OracleDriver");
       con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr","hr");
      Statement st = con.createStatement();
      rs=st.executeQuery("select * from actor");
     while(rs.next()){
      if(actorname.equalsIgnoreCase(rs.getString(2))) {
          validity="false";
      } 
      else{
        validity="true";  
      }
     } 
    return validity;  
   } 
    
  public Actor SeeAll(String id) throws ClassNotFoundException, SQLException{
     
     Connection con;
     ResultSet rs;
     Class.forName("oracle.jdbc.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
        Statement st = con.createStatement();
       rs = st.executeQuery("select * from actor");
     
        Actor as = new Actor();
       while(rs.next()){
    
     if(id.equalsIgnoreCase(rs.getString(2))){
     as.setActor_id(rs.getInt(1));
     as.setActorname(rs.getString(2));
     as.setHome(rs.getString(3));
     as.setBestfilm(rs.getString(4));
     }
     
      }
     
     return as;
  } 
   
  public ArrayList<Actor> allrecord() throws SQLException, ClassNotFoundException{
      
     ArrayList<Actor> list = new ArrayList<>();
      Connection con;
     ResultSet rs;
     Class.forName("oracle.jdbc.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr","hr");
        Statement st = con.createStatement();
       rs = st.executeQuery("select * from actor");
     while(rs.next()){
        Actor a1 = new Actor(); 
        a1.setActor_id(rs.getInt(1));
        a1.setActorname(rs.getString(2));
        a1.setBestfilm(rs.getString(3));
        a1.setHome(rs.getString(4));
        list.add(a1);
     }
    return list;  
  } 
   
   
}
