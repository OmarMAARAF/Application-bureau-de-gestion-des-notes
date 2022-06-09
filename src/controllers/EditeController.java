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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;


public class EditeController implements Initializable  {



	@FXML
	private PasswordField oldPasswordField ;
	@FXML
	private PasswordField newPasswordField ;
	@FXML
	private PasswordField confirmPasswordField ;



	private Connection connection ;
	private PreparedStatement statement ,stat ;
	private ResultSet result ;


	String Url="jdbc:mysql://localhost:3306/superpos";
	String userName="root";
	String password ="";



	@FXML
	public void SaveButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException{

		try{


			String newPassword = newPasswordField.getText();
			String confirmPassword = confirmPasswordField.getText();
			String oldPassword = oldPasswordField.getText();

			if(oldPassword.equals("") || newPassword.equals("") || confirmPassword.equals("")){
				Alert alert = new Alert(AlertType.WARNING);

	            alert.setTitle("Error");
	            alert.setHeaderText("tous les champs doit etre remplies ");
	            alert.showAndWait();
			}else if(newPassword.equals(confirmPassword)){

			System.out.println("old "+oldPassword);
			System.out.println("confirm "+confirmPassword);
			System.out.println("new "+newPassword);



			Class.forName("com.mysql.cj.jdbc.Driver");
			 connection = DriverManager.getConnection(Url,userName,password);
			System.out.println("connecte");


		String sqlOldPass="SELECT password FROM login WHERE password=? and code_apogé=?";
		statement = connection.prepareStatement(sqlOldPass);
		statement.setString(1,oldPassword);
		statement.setString(2,application.Main.Code);

		 result = statement.executeQuery();

		System.out.println("SELECTION DONE ");

		if(result.next()){
			int st =0 ;

			System.out.println(" I AM IN RESULT IF  ");

			String sqlUpdate = "UPDATE login "+"SET password ='" + newPassword + "' WHERE password ='"
					+ oldPassword + "' and code_apogé =?";
			stat = connection.prepareStatement("UPDATE login "+"SET password ='" + newPassword + "' WHERE password ='"
					+ oldPassword + "' and usertype='user'");
			//stat.setString(1, Main.Code);
			System.out.println("here");
			stat.executeUpdate();
			System.out.println(" STATEMENT PREPARED  " +stat);


			 System.out.println("UDPATE DONE ");


		}
		else{
			 Alert alert = new Alert(AlertType.WARNING);

	            alert.setTitle("erreur");
	            alert.setHeaderText("l'ancien mot de passe est inccorecte ");


	            alert.showAndWait();
		}
		}

		// si le nouveau mdp et le confirmer  sont differents
		else{


            Alert alert = new Alert(AlertType.WARNING);

            alert.setTitle("erreur");

            alert.setHeaderText("le nouveau mdp et le confirmer  sont differents .");

            alert.showAndWait();
		}


		}
		catch(SQLException e ){
			e.printStackTrace();

		}

		catch(ClassNotFoundException e ){
			e.printStackTrace();

		}

	}

	@FXML
	public void cancelButton(ActionEvent actionEvent){
		oldPasswordField.setText("");
		newPasswordField.setText("");
		confirmPasswordField.setText("");

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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}