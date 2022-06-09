package controllers;

import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import controllers.etudiant;

public class Gestion_notes implements Initializable{
	   @FXML
	    private TableView<etudiant> table;

	    @FXML
	    private TableColumn<etudiant,Integer> id;

	    @FXML
	    private TableColumn<etudiant,String> Matiéres;

	    @FXML
	    private TableColumn<etudiant, Integer> Notes;

	    @FXML
	    private TableColumn<etudiant, String> Résultat;

	    @FXML
	    private Button retour;

	    public ObservableList <etudiant> data =FXCollections.observableArrayList();

	    @FXML
	   public void actualiserNote(){
	    	try{
	    		Connection con = dbConnexion.connexionDB();
	    		String sql = "SELECT matiere,notes,resultat FROM etudiant where code_apogé=?";
	    		PreparedStatement stat = con.prepareStatement(sql);
	    		stat.setString(1,Main.Code);
	    		ResultSet rs = stat.executeQuery();
	    		while(rs.next()){
	    			data.add(new etudiant(rs.getString("matiere"),rs.getInt("notes"),rs.getString("resultat")));
	    		}
	    		con.close();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	Matiéres.setCellValueFactory(new PropertyValueFactory<etudiant,String>("Matiéres"));
	    	Notes.setCellValueFactory(new PropertyValueFactory<etudiant,Integer>("Notes"));
	    	Résultat.setCellValueFactory(new PropertyValueFactory<etudiant,String>("Résultat"));
	    	table.setItems(data);
	    }
	    	  @FXML
	    		  void retourBtn(ActionEvent actionEvent) {
		    		    Scene scene;
		  	    		Parent root;
		  	    		Stage stage;
		  	    	try{
			    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfac/accueil1.fxml"));
			    		root = loader.load();
			    		stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
			    		scene = new Scene(root);
			    		stage.setScene(scene);
			    		stage.show();
			    	}
			    	catch(Exception e){
			    		e.printStackTrace();
			    	}


		  	  	/*Matiéres.setCellValueFactory(new PropertyValueFactory<etudiant,String>("Matiéres"));
		    	Notes.setCellValueFactory(new PropertyValueFactory<etudiant,Integer>("Notes"));
		    	Résultat.setCellValueFactory(new PropertyValueFactory<etudiant,String>("Résultat"));
		    	table.setItems(data);*/
	    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
