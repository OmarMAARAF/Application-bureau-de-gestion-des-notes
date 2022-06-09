package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import interfac.etudiant;
import interfac.etudiant1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class ProfilController implements Initializable {
	Connection cnx;
	public PreparedStatement st;
	public ResultSet result;

	 @FXML
	    private TableView<etudiant> table;

	    @FXML
	    private TableColumn<etudiant, String> nom;

	    @FXML
	    private TableColumn<etudiant, String> prenom;

	    @FXML
	    private TableColumn<etudiant, String> date_naissance;

	    @FXML
	    private TableColumn<etudiant, String> lieu_naissance;

	    @FXML
	    private TableView<etudiant1> table1;

	    @FXML
	    private TableColumn<etudiant1, String> code_apogé;

	    @FXML
	    private TableColumn<etudiant1, String> cne;

	    @FXML
	    private TableColumn<etudiant1, String> filiere;

	    public ObservableList<etudiant> data=FXCollections.observableArrayList();

	    public ObservableList<etudiant1> data1=FXCollections.observableArrayList();

	    public void showInfoEtudiantt() {
	    	String sql="select code_apogé,cne,filiere from etudiant where code_apogé=?";
	    	try {
	    		st=cnx.prepareStatement(sql);
	    		st.setString(1,Main.Code);
	    		result=st.executeQuery();
	    		while(result.next())
	    		{
	    			data1.add(new etudiant1( result.getString("code_apogé"),result.getString("cne"),result.getString("filiere")));
	    		}
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	code_apogé.setCellValueFactory(new PropertyValueFactory<etudiant1,String>("code_apogé"));
	    	cne.setCellValueFactory(new PropertyValueFactory<etudiant1,String>("cne"));
	    	filiere.setCellValueFactory(new PropertyValueFactory<etudiant1,String>("filiere"));
	        table1.setItems(data1);
		}

	    public void showInfoEtudiant() {
	    	String sql="select nom,prenom,lieu_naissance,date_naissance from etudiant where code_apogé=?";
	    	try {
	    		st=cnx.prepareStatement(sql);
	    		st.setString(1,Main.Code);
	    		result=st.executeQuery();
	    		while(result.next())
	    		{
	    			data.add(new etudiant( result.getString("nom"),result.getString("prenom"),result.getString("date_naissance"),result.getString("lieu_naissance")));
	    		}
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	nom.setCellValueFactory(new PropertyValueFactory<etudiant,String>("nom"));
		    prenom.setCellValueFactory(new PropertyValueFactory<etudiant,String>("prenom"));
		    date_naissance.setCellValueFactory(new PropertyValueFactory<etudiant,String>("date_naissance"));
		    lieu_naissance.setCellValueFactory(new PropertyValueFactory<etudiant,String>("lieu_naissance"));
	        table.setItems(data);
		}


		 private Stage stage;
		 private Scene scene;
		 private Parent root;

	    public void retour(ActionEvent event) throws IOException {
			  root = FXMLLoader.load(getClass().getResource("/interfac/accueil1.fxml"));
			  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			  scene = new Scene(root);
			  stage.setScene(scene);
			  stage.show();
			 }

	    public void switchToLogin(ActionEvent event) throws IOException {
		  	  Parent root = FXMLLoader.load(getClass().getResource("/application/blue login.fxml"));
		  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  	  scene = new Scene(root);
		  	  stage.setScene(scene);
		  	  stage.show();
		  	 }



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		 cnx = ConnexionMysql.connexionDB();
		showInfoEtudiant();
		showInfoEtudiantt();

	}
}
