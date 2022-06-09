package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modification.model.ConnectionClass;

public class emploisController implements Initializable{

    @FXML
    private TableView<Cours> coursTable;

    @FXML
    private TableColumn<Cours, String> joursColumn;

    @FXML
    private TableColumn<Cours, String> firstColumn;

    @FXML
    private TableColumn<Cours, String> secondColumn;

    @FXML
    private TableColumn<Cours, String> thirdColumn;

    @FXML
    private TableColumn<Cours, String> forthColumn;




    // Connectoin to DataBase

 // pour la connection au DB
    private Connection connection ;
 	private PreparedStatement statement;
 	private ResultSet result ;



 	String dbUrl="jdbc:mysql://localhost:3306/superpos";
	String userName="root";
	String password ="";




		/*private String  firstHour ="08h:00-10h:00" ;
		private String  secondHour ="10h:00-12h:00" ;
		private String  thirdHour ="14h:00-16h:00" ;
		private String  fourthHour ="16h:00-18h:00" ; */



    ObservableList<Cours> cours= FXCollections.observableArrayList(
    		/*new Cours("lundi","DevWeb","DevWeb","Uml","xml"),
    		new Cours("mardi","UML","XML","microprocceseur","microprocceseur"),
    		new Cours("mercredi","Analyse Donnees","Analyse Numerique","Resau informatique","Resau informatique"),
    		new Cours("jeudi","Resau informatique","Resau informatique","IHM Java","IHM Java"),
    		new Cours("vendredi","SE","Compilation","Compilation","SE"),
    		new Cours("samedi","DevWeb","DevWeb","","") */
    		);


    // fonction: faire la selection de la BD en fonction du jours et de l'heure

    public PreparedStatement select(String jour , String heure){

    	String query = "Select c.jour,c.heure ,prf.nom , prf.prenom AS prof ,m.nom AS matiere from cours c ,etudiant e,prof prf , matiere m"
    			+ " where c.id_matiere=m.id "
    			+ "AND c.id_etudiant=e.id  "
    			+ "AND c.cne_prof = prf.cne "
    			+ "AND c.jour = ? "
    			+ "AND c.heure= ? "
    			+ "AND e.code_apogé=? ";

    	try{
    		statement = connection.prepareStatement(query);

    		statement.setString(1,jour);
    		statement.setString(2,heure);
    		statement.setString(3,Main.Code);


    	}catch(SQLException e){
    		e.printStackTrace();
    	}

    	return statement ;


    }

    @Override
    public void initialize(URL url ,ResourceBundle resourceBundle) {

    	try {



			Class.forName("com.mysql.cj.jdbc.Driver");
			 connection =DriverManager.getConnection(dbUrl,userName,password);




			ResultSet result1,result2,result3,result4 ;

			String jours[] ={"lundi","mardi","mercredi","jeudi","vendredi","samedi"};

			for(int i = 0 ;i<6 ;++i){


		 result1 = select(jours[i],"firstHour").executeQuery();
		 result2 = select(jours[i],"secondHour").executeQuery();
		 result3 = select(jours[i],"thirdHour").executeQuery();
		 result4 = select(jours[i],"forthHour").executeQuery();






		while(result1.next() && result2.next() && result3.next() && result4.next()){
			cours.add(new Cours(result1.getString(1),
					result1.getString(5)+"\n"+result1.getString(3)+" "+result1.getString(4)
					,result2.getString(5)+"\n"+result2.getString(3)+" "+result2.getString(4)
					,result3.getString(5)+"\n"+result3.getString(3)+" "+result3.getString(4)
					,result4.getString(5) +"\n"+result4.getString(3)+" "+result4.getString(4)));

			}




    	joursColumn.setCellValueFactory(new PropertyValueFactory<Cours,String>("jour"));
    	firstColumn.setCellValueFactory(new PropertyValueFactory<Cours,String>("firstHour"));
    	secondColumn.setCellValueFactory(new PropertyValueFactory<Cours,String>("secondHour"));
    	thirdColumn.setCellValueFactory(new PropertyValueFactory<Cours,String>("thirdHour"));
    	forthColumn.setCellValueFactory(new PropertyValueFactory<Cours,String>("forthHour"));

    	coursTable.setItems(cours);

			}// end for



		} // end try

    	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


    	} // end initialize


    // la fonction pour fermer la scene d' emplois

	public void retourBtn(ActionEvent actionEvent){

		Scene scene ;
		Parent root ;
		Stage stage ;
		try{
			FXMLLoader	loader = new FXMLLoader(getClass().getResource("/interfac/accueil1.fxml"));
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

}
