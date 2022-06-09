package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Edite.model.programme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class admin_tempsController implements Initializable{


	@FXML
	private HBox note;
	@FXML
	private Button emploi;
	@FXML
	private Button minimizeButton;
	@FXML private javafx.scene.control.Button closeButton;
	@FXML
	private Label l1;
	@FXML
	private Label l2;
	@FXML
	private Label l3;
	@FXML
	private void closeButtonAction(){
	    // get a handle to the stage
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	    // do what you have to do
	    stage.close();
	}
	@FXML
	private void min(MouseEvent event) {
		Stage s= (Stage) ((Node)event.getSource()).getScene().getWindow();
		s.setIconified(true);
	}
	@FXML
	private void borderpane_dragged(MouseEvent event) {
	    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	    stage.setY(event.getScreenY() - y);
	    stage.setX(event.getScreenX() - x);
	}
	@FXML
	private void borderpane_pressed(MouseEvent event) {
	    x = event.getSceneX();
	    y = event.getSceneY();
	}

	Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private double x = 0;
    private double y = 0;



    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize(URL arg0, ResourceBundle arg1){
		// TODO Auto-generated method stub

    	try {
    		if(home_controller.n1.equals("Emploi du temps") || home_controller.n2.equals("Emploi du temps") || home_controller.n3.equals("Emploi du temps")) {

    		}
    		else {
			Main.update_acc("Emploi du temps");
    		}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	try {



			Class.forName("com.mysql.cj.jdbc.Driver");
			 connection =DriverManager.getConnection(dbUrl,userName,password);


			//System.out.println(select("lundi","firstHour"));

			ResultSet result1,result2,result3,result4 ;

			String jours[] ={"lundi","mardi","mercredi","jeudi","vendredi","samedi"};


			for(int i = 0 ;i<6 ;++i){


		 result1 = select(jours[i],"firstHour").executeQuery();
		 result2 = select(jours[i],"secondHour").executeQuery();
		 result3 = select(jours[i],"thirdHour").executeQuery();
		 result4 = select(jours[i],"forthHour").executeQuery();


if(result1.next()){

}
if(result2.next()){
}
if(result3.next()){
}
if(result4.next()){
}



	programme.add(new programme(result1.getString(1),result1.getString(4)+"/"+result1.getString(5),result2.getString(4)+"/"+result2.getString(5),result3.getString(4)+"/"+result3.getString(5),result4.getString(4)+"/"+result4.getString(5) ));





    	jour.setCellValueFactory(new PropertyValueFactory<programme,String>("jour"));
    	_8_10Column.setCellValueFactory(new PropertyValueFactory<programme,String>("_8_10"));
    	_10_12Column.setCellValueFactory(new PropertyValueFactory<programme,String>("_10_12"));
    	_14_16Column.setCellValueFactory(new PropertyValueFactory<programme,String>("_14_16"));
    	_16_18Column.setCellValueFactory(new PropertyValueFactory<programme,String>("_16_18"));

    	Table.setItems(programme);
			}// end for



		} // end try



    	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}







    }
    public void minimize(ActionEvent event) throws IOException {
    Stage stage = (Stage) minimizeButton.getScene().getWindow();
    stage.setIconified(true);
    }
    public void switchToEmploi(ActionEvent event) throws IOException {
    	  Parent root = FXMLLoader.load(getClass().getResource("admin_temps.fxml"));
    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	  scene = new Scene(root);
    	  stage.setScene(scene);
    	  stage.show();
    	 }
    public void switchToHome(ActionEvent event) throws IOException {
  	  Parent root = FXMLLoader.load(getClass().getResource("lastone.fxml"));
  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	  scene = new Scene(root);
  	  stage.setScene(scene);
  	  stage.show();
  	 }
    public void switchToList(ActionEvent event) throws IOException {
  	  Parent root = FXMLLoader.load(getClass().getResource("admin_list.fxml"));
  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	  scene = new Scene(root);
  	  stage.setScene(scene);
  	  stage.show();
  	 }
    public void switchToProfil(ActionEvent event) throws IOException {
  	  Parent root = FXMLLoader.load(getClass().getResource("admin_profil.fxml"));
  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	  scene = new Scene(root);
  	  stage.setScene(scene);
  	  stage.show();
  	 }
    public void switchToParametre(ActionEvent event) throws IOException {
  	  Parent root = FXMLLoader.load(getClass().getResource("admin_parametre.fxml"));
  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	  scene = new Scene(root);
  	  stage.setScene(scene);
  	  stage.show();
  	 }
    public void switchToNote(ActionEvent event) throws IOException {
    	  Parent root = FXMLLoader.load(getClass().getResource("admin_note.fxml"));
    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	  scene = new Scene(root);
    	  stage.setScene(scene);
    	  stage.show();
    	 }
    public void switchToLogin(ActionEvent event) throws IOException {
  	  Parent root = FXMLLoader.load(getClass().getResource("blue login.fxml"));
  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	  scene = new Scene(root);
  	  stage.setScene(scene);
  	  stage.show();
  	 }





    @FXML
	public void cancel(ActionEvent actionEvent){


		Scene scene ;
		Parent root ;
		Stage stage ;
		try{
			FXMLLoader	loader = new FXMLLoader(getClass().getResource("Setting.fxml"));
		root = loader.load();

		stage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
		catch(IOException e){
			e.printStackTrace();
		}


	}


    @FXML
    private TableView<programme> Table;

    @FXML
    private TableColumn<programme, String> jour;

    @FXML
    private TableColumn<programme, String> _8_10Column;

    @FXML
    private TableColumn<programme, String> _10_12Column;

    @FXML
    private TableColumn<programme, String> _14_16Column;

    @FXML
    private TableColumn<programme, String> _16_18Column;



    // Connectoin to DataBase

 // pour la connection au DB
    private Connection connection ;
 	private PreparedStatement statement;
 	private ResultSet result ;



 	String dbUrl="jdbc:mysql://localhost:3306/superpos";
	String userName="root";
	String password ="";

    ObservableList<programme> programme= FXCollections.observableArrayList();


    // fonction: faire la selection de la BD en fonction du jours et de l'heure

    public PreparedStatement select(String jour , String heure){

    	String query = "Select p.jour, p.heure , ens.nom ,m.nom AS matiere, cl.classe from programme p , enseignant ens , matiere m, classes cl where p.id_matiere= m.id AND p.id_enseignant =ens.id AND p.id_etudiant=cl.id AND ens.id=? AND p.jour = ? AND p.heure= ?;" ;


    	try{
    		statement = connection.prepareStatement(query);
    		statement.setString(1,Main.id);
    		statement.setString(2,jour);
    		statement.setString(3,heure);



    	}catch(SQLException e){
    		e.printStackTrace();
    	}

    	return statement ;


    }



	}





