package application;

import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;

public class userController implements Initializable{

	@FXML
    private Button logout;

	 @FXML
	 Label namelabel;

	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 private String name;
	 private String password;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void logout(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("TRYING.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();

		  name="";
		  password="";
	}
	public void getinformation(String uname,String upassword) {
		name=uname;
		password=upassword;
		System.out.println(name);
		System.out.println(password);

	}
	public void displayname(String uname) {
		namelabel.setText("Hi, "+uname);
	}

}
