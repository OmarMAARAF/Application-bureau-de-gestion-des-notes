package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import java.lang.reflect.Parameter;

import javafx.application.Application.Parameters;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {


	 @FXML
	    private PasswordField password;

	    @FXML
	    private TextField username;
	    @FXML
	    private Button login;
	    @FXML
	    private Button signup;
	    @FXML
	    private Button Forget;

	    private Stage stage;
		 private Scene scene;
		 private Parent root;

		 @FXML
		 final Tooltip t_user = new Tooltip();


	    Connection con;
	    PreparedStatement pst;
	    ResultSet rs;

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

		private double x = 0;
	    private double y = 0;


	    @FXML
	    private ImageView myImage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub





	}


	public void loginAction(ActionEvent e) throws IOException {
		String uname= username.getText();
		String pssword =password.getText();

		if(uname.equals("") && pssword.equals("")) {
			JOptionPane.showMessageDialog(null,"UserName or password Blank");


		}else {
			try {
				//Class.forName("com.mysql.jdbc.Driver");
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost/superpos","root","");


				pst =con.prepareStatement("select * from login where username =? and password=?");
				pst.setString(1,uname);
				pst.setString(2,pssword);
				rs =pst.executeQuery();
				if(rs.next()) {
					//JOptionPane.showMessageDialog(null,"Login success");
					String utype;
					utype=rs.getString("usertype");
					String id;
					id=rs.getString("id");
					Main.Drn=rs.getString("drn_connexion");
					Main.setMyVariable(id);
					System.out.println(utype);
					Main.Code=rs.getString("code_apogé");

					if(utype.equals("user"))  {

						FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfac/accueil1.fxml"));
						root = loader.load();
						  stage = (Stage)((Node)e.getSource()).getScene().getWindow();

						  scene = new Scene(root);
						  stage.setScene(scene);
						  stage.show();

					}

					else if(utype.equals("admin")){
						Main.get_acc();
						pst =con.prepareStatement("update login set drn_connexion=? where id =? ");
						pst.setString(1,Main.gettimeanddate());
						pst.setString(2,Main.id);
						pst.executeUpdate();

						pst =con.prepareStatement("select * from prof where id =? ");
			        	pst.setString(1,Main.id);
						rs =pst.executeQuery();
						if(rs.next()) {
							Main.F1=rs.getString("F1");
							Main.F2=rs.getString("F2");
							Main.F3=rs.getString("F3");
							Main.M1=rs.getString("M1");
							Main.M2=rs.getString("M2");
							Main.M3=rs.getString("M3");
						}
						FXMLLoader loader = new FXMLLoader(getClass().getResource("lastone.fxml"));
						root = loader.load();
						//adminController adminController=loader.getController();
						//adminController.getinformation(uname,pssword);
						//adminController.displayname(uname);
						//root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
						stage = (Stage)((Node)e.getSource()).getScene().getWindow();

						scene = new Scene(root);
						stage.setScene(scene);
						stage.show();


					}
				}
				else {
					JOptionPane.showMessageDialog(null,"Mot de passe ou identifiant incorrect");
					username.setText("");
					password.setText("");
					username.requestFocus();



				}

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	public void minimize(ActionEvent event) throws IOException {
	    Stage stage = (Stage) minimizeButton.getScene().getWindow();
	    stage.setIconified(true);
	    }

	private Parameters getParameters() {
		// TODO Auto-generated method stub
		return null;
	}





}
