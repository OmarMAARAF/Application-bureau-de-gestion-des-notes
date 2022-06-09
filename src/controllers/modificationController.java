package controllers;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;


public class modificationController {



	@FXML
	private PasswordField oldPasswordField ;
	@FXML
	private PasswordField newPasswordField ;
	@FXML
	private PasswordField confirmPasswordField ;


	// pour la connection au DB
	private Connection connection ;
	private PreparedStatement statement ,stat ;
	private ResultSet result ;


	String dbUrl="jdbc:mysql://localhost:3306/superpos";
	String userName="root";
	String password ="";


	// save button

	@FXML
	public void SaveButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException{






		try{


			String newPassword = newPasswordField.getText();
			String confirmPassword = confirmPasswordField.getText();
			String oldPassword = oldPasswordField.getText();

			// si l' un des champs est vide
			if(oldPassword.equals("") || newPassword.equals("") || confirmPassword.equals("")){
				Alert alert = new Alert(AlertType.WARNING);

	            alert.setTitle("error");
	            alert.setHeaderText("tous les champs doit etre remplies ");


	            alert.showAndWait();

			}

	            // si le nvmdp est le confirm mdp ont la mm valeur

	            else if(newPassword.equals(confirmPassword)){




			// se connecter au base de donnees

			Class.forName("com.mysql.cj.jdbc.Driver");
			 connection =DriverManager.getConnection(dbUrl,userName,password);







				// create a statement
		String sqlOldPass="SELECT password FROM login  WHERE password=? and code_apogé=?";




		statement = connection.prepareStatement(sqlOldPass);


		statement.setString(1,oldPassword);
		statement.setString(2,Main.Code);

		 result = statement.executeQuery();



		if( result.next()){



			String sqlUpdate = "UPDATE login l,etudiant e SET l.password ='"+newPassword+"' WHERE l.password ='"+oldPassword+"' and e.code_apogé=?" ;
			stat = connection.prepareStatement(sqlUpdate);
			stat.setString(1,Main.Code);


			int st = stat.executeUpdate(sqlUpdate);





			 //fermer la connection au base de donnees
			 Alert alert = new Alert(AlertType.WARNING);
			 	oldPasswordField.setText("");
				newPasswordField.setText("");
				confirmPasswordField.setText("");
	            alert.setTitle("SUCCES");
	            alert.setHeaderText("le mot de passe a été modifier ");


	            alert.showAndWait();



		}
		else{
			 Alert alert = new Alert(AlertType.WARNING);
			 oldPasswordField.setText("");
				newPasswordField.setText("");
				confirmPasswordField.setText("");

	            alert.setTitle("ERREUR");
	            alert.setHeaderText("l'ancien mot de passe est inccorect ");


	            alert.showAndWait();
		}
		}

		// si le nouveau mdp et le confirmer  sont differents
		else{


            Alert alert = new Alert(AlertType.WARNING);

            alert.setTitle("ERREUR");

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
}