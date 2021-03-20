/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonbookMT;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Antonius Aldo 
 */
public class Database  {
    
    final private String database= "jdbc:sqlite:PersonDatabase.db";
    

    public static void setupDatabase() throws ClassNotFoundException, SQLException{
            Class.forName("org.sqlite.JDBC");
            
            //create connection
            Connection conn = DriverManager.getConnection("jdbc:sqlite:PersonDatabase.db");

            //create statement
            Statement st = conn.createStatement();

            // Create Person Table, with id, name, species, colour, and owner fields
            String createQuery = "CREATE TABLE IF NOT EXISTS Person ("
                    + "ID int PPRIMARY KEY"
                    + ", FName String NOT NULL"
                    + ", LName String NOT NULL"
                    + ", BD int (1,31)"
                    + ", BM int (1,12)"
                    + ", BY String "
                    + ", PersonalReason Boolean"
                    + ", BusinessReason Boolean"
                    + ", CN String )";
           
          
             // Close connections and statements
            st.execute(createQuery);
            
            
            
            st.close();
            conn.close();
            
            System.out.println("Database Connected.");

    }
    
    
    public static void printAll() throws SQLException{
		//create connection
		Connection conn = DriverManager.getConnection("JDBC:sqlite:PersonDatabase.db");

		//create statement
		Statement st = conn.createStatement ();

		//write the SQL query and the java code to retrieve and print out all Data
        
		System.out.println("People in Database");

		String selectQuery = "SELECT * FROM Person ";

		ResultSet rs = st.executeQuery(selectQuery);

		while(rs.next()){
			System.out.println(rs.getString(1) + ", " 
			+ rs.getString(2) + ", " 
			+ rs.getString(3) + ", " 
                        + rs.getString(4) + ", "
                        + rs.getString(5) + ", "   
                        + rs.getString(6) + ", "  
                        + rs.getString(7) + ", " 
                        + rs.getString(8) + ", "        
			+ rs.getString(9));
		}
		
		st.close();
		conn.close();
    }
        
        public static ObservableList<Person> getPerson() throws SQLException {
        
        // Get ResultSet of all people that exist in the database
        Connection conn = DriverManager.getConnection("jdbc:sqlite:PersonDatabase.db");
        Statement st = conn.createStatement();
        String query = "SELECT * FROM Person";
        ResultSet rs = st.executeQuery(query);
               
        ObservableList<Person> PersonList =  FXCollections.observableArrayList();

        // Add each row in the ResultSet to the datalist
        while(rs.next()){

           
            PersonList.add(new Person(rs.getInt(1),
                    rs.getString(2), 
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5), 
                    rs.getString(6),
                    rs.getBoolean(7),
                    rs.getBoolean(8), 
                    rs.getString(9)));
        }     
  
        // Close 
        st.close();
        conn.close();
        return PersonList;
    
    }

   //      @FXML
//    public static void TABLEDelete ()throws IOException, SQLException{
//        
//       
//         //create connection
//        Connection conn = DriverManager.getConnection("jdbc:sqlite:PersonDatabase.db");
//        //create statement	
//        Statement st = conn.createStatement();
//        //write the SQL query and the java code to insert all four pets
//        PreparedStatement pSt = conn.prepareStatement("DROP TABLE Person "
//        );
//       
//       pSt.executeUpdate();
//
//       st.close();
//       conn.close();
//
//
//       System.out.println("TABLE DELETEEE");
//        
//        
//        
//    }
    
}