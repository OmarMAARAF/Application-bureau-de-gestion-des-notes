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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class admin_parametreController implements Initializable {

	@FXML
	private HBox note;
	@FXML
	private Button emploi;
	@FXML
	private Button minimizeButton;
	@FXML
	private javafx.scene.control.Button closeButton;

	@FXML
	private void closeButtonAction() {
		// get a handle to the stage
		Stage stage = (Stage) closeButton.getScene().getWindow();
		// do what you have to do
		stage.close();
	}

	@FXML
	private void min(MouseEvent event) {
		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		s.setIconified(true);
	}

	@FXML
	private void borderpane_dragged(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

	public void initialize(URL arg0, ResourceBundle arg1) {
		if(home_controller.n1.equals("Parametre") || home_controller.n2.equals("Parametre") || home_controller.n3.equals("Parametre")) {

		}
		else {
		try {
			Main.update_acc("Parametre");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	public void minimize(ActionEvent event) throws IOException {
		Stage stage = (Stage) minimizeButton.getScene().getWindow();
		stage.setIconified(true);
	}

	public void switchToEmploi(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("admin_temps.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToHome(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("lastone.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToList(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("admin_list.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToProfil(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("admin_profil.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToParametre(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("admin_parametre.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToNote(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("admin_note.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("blue login.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void test(String id) {
		System.out.println("dd" + id);
	}

	@FXML
	private PasswordField oldPasswordField;
	@FXML
	private PasswordField newPasswordField;
	@FXML
	private PasswordField confirmPasswordField;

	Connection connection;
	PreparedStatement statement;
	ResultSet result;
	PreparedStatement stat;

	@FXML
	public void SaveButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

		try {

			String newPassword = newPasswordField.getText();
			String confirmPassword = confirmPasswordField.getText();
			String oldPassword = oldPasswordField.getText();

			// si l' un des champs est vide
			if (oldPassword.equals("") || newPassword.equals("") || confirmPassword.equals("")) {
				Alert alert = new Alert(AlertType.WARNING);

				alert.setTitle("error");
				alert.setHeaderText("tous les champs doivent etre remplies ");

				alert.showAndWait();

			}

			// si le nvmdp est le confirm mdp ont la mm valeur

			else if (newPassword.equals(confirmPassword)) {

				// se connecter au base de donnees

				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/superpos", "root", "");

				// create a statement
				String sqlOldPass = "SELECT password FROM login WHERE password=? and id=?";

				statement = connection.prepareStatement(sqlOldPass);

				statement.setString(1, oldPassword);
				statement.setString(2, Main.id);
				result = statement.executeQuery();

				if (result.next()) {

					String sqlUpdate = "UPDATE login SET password ='" + newPassword + "' WHERE password ='"
							+ oldPassword + "' and id =?";
					stat = connection.prepareStatement(sqlUpdate);
					stat.setString(1, Main.id);

					stat.executeUpdate();

					// fermer la connection au base de donnees
					Alert alert = new Alert(AlertType.WARNING);
					oldPasswordField.setText("");
					newPasswordField.setText("");
					confirmPasswordField.setText("");
					alert.setTitle("SUCCES");
					alert.setHeaderText("le mot de passe a été modifier ");

					alert.showAndWait();

				} else {
					Alert alert = new Alert(AlertType.WARNING);
					oldPasswordField.setText("");
					newPasswordField.setText("");
					confirmPasswordField.setText("");

					alert.setTitle("ERREUR");
					alert.setHeaderText("l'ancien mot de passe est inccorect ");

					alert.showAndWait();
				}
			}

			// si le nouveau mdp et le confirmer sont differents
			else {

				Alert alert = new Alert(AlertType.WARNING);

				alert.setTitle("ERREUR");

				alert.setHeaderText("le nouveau mdp et le confirmer  sont differents .");

				alert.showAndWait();
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();

		}

	}

}
