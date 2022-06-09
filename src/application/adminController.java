package application;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
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

public class adminController implements Initializable{

	@FXML
    private Button logout;

	 @FXML
	 Label namelabel;
	 @FXML
	 Label heur;

	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 private String name;
	 private String password;
	 private volatile boolean stop =false;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Timenow();

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
		namelabel.setText(uname);
	}

	private void Timenow() {
		Thread thread =new Thread(() -> {
			SimpleDateFormat sdf =new SimpleDateFormat("hh:mm:ss a");
			while(!stop) {
				try {
					Thread.sleep(1000);
				}catch(Exception e) {

			}
				final String timenow=sdf.format(new Date());
				Platform.runLater(()->{
					heur.setText(timenow);

				});
			}
		});
		thread.start();
	}

}
