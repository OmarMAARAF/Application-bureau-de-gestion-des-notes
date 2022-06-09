package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController implements Initializable{

	 @FXML
	    private AnchorPane VBox;


	 private Stage stage;
	 private Scene scene;
	 private Parent root;

	 public void openprofil(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("profil.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 public void openparam(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("Edite.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 public void  openemploi(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("emplois.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 public void  openliste(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("win1.fxml"));
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
		TranslateTransition t = new TranslateTransition();
		t.setToX(VBox.getLayoutX() * 5.5);
		t.play();
	}

}
