package PersonbookMT;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.JDBCType.NULL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ThreadLocalRandom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class PersonbookController {


    @FXML
    private ListView<Person>  MasterSideMenu;
    
    @FXML
    private Label lblFName;
    
    @FXML
    private Label lblLName;
    
    @FXML
    private TextField txtFName;
    
    @FXML
    private TextField txtLName;
    
    @FXML
    private TextField Bdate;
    
    @FXML
    private TextField Bmonth;
     
    @FXML
    private TextField Byear;
      
    @FXML
    private CheckBox PR;
    
    @FXML
    private CheckBox BR;
    
    @FXML
    private TextArea CN;
    
    @FXML
    private Label progress;
    
    @FXML
    public static boolean BRselected;
    
    @FXML
    public static boolean PRselected;
    
    int newBD;
    int newBM;
    String newBY;
    
    
    @FXML
    public void initialize() throws ClassNotFoundException, SQLException, IOException {
        
        
//        Database.TABLEDelete();
        Database.printAll();
        Database.setupDatabase();
        
        MasterSideMenu.getItems().addAll(Database.getPerson());
 
    }
    
    
    @FXML
    public void UserChangeSelection(){
        Person selectedPerson = MasterSideMenu.getSelectionModel().getSelectedItem();
        lblFName.setText(selectedPerson.getFName());
        lblLName.setText(selectedPerson.getLName());
        
        txtFName.setText(selectedPerson.getFName());
        txtLName.setText(selectedPerson.getLName());
        
        Bdate.setText(Integer.toString(selectedPerson.getBD()));
        Bmonth.setText(Integer.toString(selectedPerson.getBM()));
        Byear.setText(selectedPerson.getBY());
        
        PR.setSelected(selectedPerson.getPersonalReason());
        BR.setSelected(selectedPerson.getBusinessReasons());
        
        CN.setText(selectedPerson.getCN());
        
        progress.setText("");
      
        
    }
    
    
    @FXML
    public void UserClickDelete ()throws IOException, SQLException{
           
        Person selectedPerson = MasterSideMenu.getSelectionModel().getSelectedItem();;
        boolean BRselected = BR.isSelected();
        if (BRselected) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        
        boolean PRselected = PR.isSelected();
        if (PRselected) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        
        String newCN = CN.getText();
        
         //create connection
        Connection conn = DriverManager.getConnection("jdbc:sqlite:PersonDatabase.db");
        //create statement	
        Statement st = conn.createStatement();
        //write the SQL query and the java code to insert all four pets
        PreparedStatement pSt = conn.prepareStatement("DELETE FROM Person WHERE ID = ? "
        );
        

        pSt.setInt(1, selectedPerson.getID());
        pSt.executeUpdate();

        st.close();
        conn.close();


        System.out.println("Entry Deleted");

        progress.setText("Data Deleted");

        MasterSideMenu.getItems().clear();

        MasterSideMenu.getItems().addAll(Database.getPerson());

        Database.printAll();


    }
    
    
    
    
    public void updatePerson() throws SQLException{
        
        if (Bdate.getText().length() >= 3 | Bmonth.getText().length() >= 3 
                | Byear.getText().length() >= 5 
                | Integer.parseInt(Bdate.getText()) >= 32 
                | Integer.parseInt(Bdate.getText()) < 1
                | Integer.parseInt(Bmonth.getText()) >= 13
                | Integer.parseInt(Bmonth.getText()) < 1
                | txtFName.getText().isEmpty()
                | Byear.getText().length() >= 5
                | txtLName.getText().isEmpty()){
            
            
          progress.setText("Invalid Data");
        } else {
      
            Person selectedPerson = MasterSideMenu.getSelectionModel().getSelectedItem();
            String newFName = txtFName.getText();
            String newLName = txtLName.getText();

            lblFName.setText(txtFName.getText());
            lblLName.setText(txtLName.getText());

            newBD = Integer.parseInt((Bdate.getText()));
            newBM = Integer.parseInt((Bmonth.getText()));
            
            
             if  (Byear.getText().isEmpty()){
                 newBY = "Nil";
            } else {
                 newBY = Byear.getText();
            }
            

            boolean BRselected = BR.isSelected();
            if (BRselected) {
                System.out.println(true);
            } else {
                System.out.println(false);
            }

            boolean PRselected = PR.isSelected();
            if (PRselected) {
                System.out.println(true);
            } else {
                System.out.println(false);
            }

            String newCN = CN.getText();


          
             //create connection
            Connection conn = DriverManager.getConnection("jdbc:sqlite:PersonDatabase.db");
            //create statement	
            Statement st = conn.createStatement();
            String query = "UPDATE Person SET FName = ?, LName = ?, BD= ?, BM= ?,"
                    + " BY= ?, PersonalReason= ?, BusinessReaso= ?, CN= ? WHERE ID = ?";
            //write the SQL query and the java code to insert all four pets
            PreparedStatement pSt = conn.prepareStatement("UPDATE Person SET FName = ?,"
                    + " LName = ?, BD= ?, BM= ?, BY= ?, PersonalReason= ?, "
                    + "BusinessReason= ?, CN= ? WHERE ID = '" +  selectedPerson.getID() + "'"
            );


           pSt.setString(1, newFName);
           pSt.setString(2, newLName);
           pSt.setInt(3, newBD);
           pSt.setInt(4, newBM);
           pSt.setString(5, newBY);
           pSt.setBoolean(6, PRselected);
           pSt.setBoolean(7, BRselected);
           pSt.setString(8, newCN);
           pSt.executeUpdate();

           st.close();
           conn.close();

           progress.setText("Entry Edited");


           System.out.println("Data Updated");
           
           MasterSideMenu.getItems().clear();
        
           MasterSideMenu.getItems().addAll(Database.getPerson());
           
            Database.printAll();
           
        }
    }

    
    public void insertPerson() throws SQLException{
        
        if (Bdate.getText().length() >= 3 | Bmonth.getText().length() >= 3 
                | Byear.getText().length() >= 5 
                | Integer.parseInt(Bdate.getText()) >= 32 
                | Integer.parseInt(Bdate.getText()) < 1
                | Integer.parseInt(Bmonth.getText()) >= 13
                | Integer.parseInt(Bmonth.getText()) < 1
                | txtFName.getText().isEmpty()
                | txtFName.getText().isEmpty()
                | Byear.getText().length() >= 5
                | txtLName.getText().isEmpty()){ 
            
            progress.setText("Invalid Input");

        }else {
            
            Person selectedPerson = MasterSideMenu.getSelectionModel().getSelectedItem();
            String newFName = txtFName.getText();
            String newLName = txtLName.getText();
            
            newBD = Integer.parseInt((Bdate.getText()));
            newBM = Integer.parseInt((Bmonth.getText()));
             
             
            if  (Byear.getText().isEmpty()){
                 newBY = "Nil";
            } else {
                 newBY = Byear.getText();
            }

            BRselected = BR.isSelected();
            if (BRselected) {
                System.out.println(true);
            } else {
                System.out.println(false);
            }

            PRselected = PR.isSelected();
            if (PRselected) {
                System.out.println(true);
            } else {
                System.out.println(false);
            }

            String newCN = CN.getText();

            int randomNum = ThreadLocalRandom.current().nextInt(1,999999999);

            Person newPerson= new Person(randomNum,newFName, newLName, newBD, 
                    newBM, newBY, PRselected, BRselected, newCN);
            
            MasterSideMenu.getItems().add(newPerson);


            //create connection
            Connection conn = DriverManager.getConnection("jdbc:sqlite:PersonDatabase.db");
            //create statement	
            Statement st = conn.createStatement();

            //write the SQL query and the java code to insert data
            PreparedStatement pSt = conn.prepareStatement(
                "INSERT OR IGNORE INTO Person (ID, FName, LName, BD, BM, BY, "
                        + "PersonalReason, BusinessReason, CN) "
                        + "VALUES (?,?,?,?,?,?,?,?,?)"
            );


            pSt.setInt(1,randomNum);
            pSt.setString(2, newFName);
            pSt.setString(3, newLName);
            pSt.setInt(4, newBD);
            pSt.setInt(5, newBM);
            pSt.setString(6, newBY);
            pSt.setBoolean(7, PRselected);
            pSt.setBoolean(8, BRselected);
            pSt.setString(9, newCN);
            pSt.execute();


            System.out.println("Entry Inserted.");

            progress.setText("Person Inserted!");

            st.close();
            conn.close(); 
             
            Database.printAll();
             
             
        }
        
        
    }
    
    
    
}
