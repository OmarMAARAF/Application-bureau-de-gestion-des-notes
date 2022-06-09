package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class admin_listController implements Initializable{

	@FXML
	private Label l1;
	@FXML
	private Label l2;
	@FXML
	private Label l3;
	@FXML
	private Button liste1;
	@FXML
	private Button liste2;
	@FXML
	private Button liste3;
	@FXML
	private HBox note;
	@FXML
	private Button emploi;
	@FXML
	private Button minimizeButton;
	@FXML private javafx.scene.control.Button closeButton;

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
    	l1.setText(Main.F1);
    	l2.setText(Main.F2);
    	l3.setText(Main.F3);

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
    public void switchTof1(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

    	Main.AF=Main.F1;
    	Main.AM=Main.M1;
    	Main.update_acc("Liste de "+Main.F1+"");
    	Parent root = FXMLLoader.load(getClass().getResource("Admin_Liste_detaille.fxml"));
    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	  scene = new Scene(root);
    	  stage.setScene(scene);
    	  stage.show();

    }
    public void switchTof2(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

    	Main.AF=Main.F2;
    	Main.AM=Main.M2;
    	Main.update_acc("Liste de "+Main.F2+"");
    	Parent root = FXMLLoader.load(getClass().getResource("Admin_Liste_detaille.fxml"));
    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	  scene = new Scene(root);
    	  stage.setScene(scene);
    	  stage.show();
    }
    public void switchTof3(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

    	Main.AF=Main.F3;
    	Main.AM=Main.M3;
    	Main.update_acc("Liste de "+Main.F3+"");
    	Parent root = FXMLLoader.load(getClass().getResource("Admin_Liste_detaille.fxml"));
  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	  scene = new Scene(root);
  	  stage.setScene(scene);
  	  stage.show();
    }




	}





