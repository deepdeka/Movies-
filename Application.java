import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Application {
   static final String DB_URL = "jdbc:mysql://localhost/Movies";
   static final String USER = "guest";
   static final String PASS = "guest123";
   static final String QUERY = "SELECT Name, Actor, Actress, director, Year FROM MOVIES";

   public static void main(String[] args) {
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(QUERY);
      ) {		      
          String sql = "CREATE TABLE MOVIES " +
                   "(
      		   " Name VARCHAR(255), " + 
                   " Actor VARCHAR(255), " + 
                   " Actress VARCHAR(225), " + 
                   " director VARCHAR(2255), " +
		   " Year INTEGER" )"; 

         stmt.executeUpdate(sql);
         System.out.println("Created table in given database...");  

	System.out.println("Inserting records into the table...");          
         String sql = "INSERT INTO MOVIES VALUES ('A', 'B', 'C', 'D',2010)";
         stmt.executeUpdate(sql);
	String sql = "INSERT INTO MOVIES VALUES ('E', 'F', 'G', 'H',2011)";
         stmt.executeUpdate(sql);
	String sql = "INSERT INTO MOVIES VALUES ('I', 'B', 'K', 'L',2015)";
         stmt.executeUpdate(sql);
	String sql = "INSERT INTO MOVIES VALUES ('M', 'N', 'O', 'P',2010)";
         stmt.executeUpdate(sql);
         System.out.println("Inserted records into the table..."); 

	while(rs.next()){
            System.out.print("Name: " + rs.getString("Name"));
            System.out.print(", Actor: " + rs.getString("Actor"));
            System.out.print(", Actress: " + rs.getString("Actress"));
            System.out.println(", Director: " + rs.getString("director"));
	    System.out.println(", Year of release: " + rs.getInt("Year"));
         }

	System.out.println("Fetching records with condition...");
         String sql = "SELECT Name, Actor FROM Registration" +
            " WHERE Actor == 'B' ";
         rs = stmt.executeQuery(sql);

         while(rs.next()){
            System.out.print("Name: " + rs.getString("Name"));
         }
         rs.close();
	  
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}