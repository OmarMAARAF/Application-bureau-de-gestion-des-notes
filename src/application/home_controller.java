package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class home_controller implements Initializable{
	@FXML
	private Label Drn_cnx;
	@FXML
	private Label h1;
	@FXML
	private Label h2;
	@FXML
	private Label h3;
	@FXML
	private Label m1;
	@FXML
	private Label m2;
	@FXML
	private Label m3;
	@FXML
	private Label g1;
	@FXML
	private Label g2;
	@FXML
	private Label g3;
	@FXML
	private Button emploi;
	@FXML
	private Button r1;
	@FXML
	private Button r2;
	@FXML
	private Button r3;
	@FXML
	private Label lr1;
	@FXML
	private Label lr2;
	@FXML
	private Label lr3;
	@FXML
	private Label time;
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



    static String n1;
    static String n2;
    static String n3;



    public void initialize(URL arg0, ResourceBundle arg1){
		// TODO Auto-generated method stub
    	lr1.setText(Main.go[0]);
    	lr2.setText(Main.go[1]);
    	lr3.setText(Main.go[2]);
    	n1=lr1.getText();
    	n2=lr2.getText();
    	n3=lr3.getText();
    	try {
			getinformation(Main.id,Main.Drn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Timenow();
    	try {
			settime1();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
  	FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_profil.fxml"));
  	admin_profilController admin_profilController=loader.getController();
	root = loader.load();stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
  	 };


	public void getinformation(String id,String drn) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost/superpos","root","");
			lr1.setText(Main.go[0]);
			lr2.setText(Main.go[1]);
			lr3.setText(Main.go[2]);
			pst =con.prepareStatement("select * from prof where id =?");
			pst.setString(1,id);
			rs =pst.executeQuery();

			if(rs.next()) {
			Main.Drn=drn;
			Drn_cnx.setText(drn);


			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void directed1(ActionEvent event) throws IOException {
		System.out.println("here");
	  	  if(lr1.getText().equals("Profil")) {
	  		System.out.println("here");
	  		Parent root = FXMLLoader.load(getClass().getResource("admin_profil.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();
	  	  }
	  	  else if(lr1.getText().equals("Emploi du temps")){
	  		Parent root = FXMLLoader.load(getClass().getResource("admin_temps.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }
	  	else if(lr1.getText().equals("Note de "+Main.F1+"")){
	  		Main.AF=Main.F1;
	    	Main.AM=Main.M1;
	    	Parent root = FXMLLoader.load(getClass().getResource("AdminNotes1.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }
	  	else if(lr1.equals("Note de "+Main.F2+"")){
	  		Main.AF=Main.F2;
	    	Main.AM=Main.M2;
	    	Parent root = FXMLLoader.load(getClass().getResource("AdminNotes1.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }
	  	else if(lr1.equals("Note de "+Main.F3+"")){
	  		Main.AF=Main.F3;
	    	Main.AM=Main.M3;
	    	Parent root = FXMLLoader.load(getClass().getResource("AdminNotes1.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }

	  	else if(lr1.getText().equals("Liste de "+Main.F1+"")){
	  		Main.AF=Main.F1;
	    	Main.AM=Main.M1;
	    	Parent root = FXMLLoader.load(getClass().getResource("AdminNotes1.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }
	  	else if(lr1.getText().equals("Liste de "+Main.F2+"")){
	  		Main.AF=Main.F2;
	    	Main.AM=Main.M2;
	    	Parent root = FXMLLoader.load(getClass().getResource("AdminNotes1.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }
	  	else if(lr1.getText().equals("Liste de "+Main.F3+"")){
	  		Main.AF=Main.F3;
	    	Main.AM=Main.M3;
	    	Parent root = FXMLLoader.load(getClass().getResource("AdminNotes1.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }
	  	else if(lr1.getText().equals("Parametre")){
	  	Parent root = FXMLLoader.load(getClass().getResource("admin_parametre.fxml"));
	  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  	  scene = new Scene(root);
	  	  stage.setScene(scene);
	  	  stage.show();
	  	}

	  	 }

	public void directed2(ActionEvent event) throws IOException {
	  	  if(lr2.getText().equals("Profil")) {
	  		Parent root = FXMLLoader.load(getClass().getResource("admin_profil.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();
	  	  }
	  	  else if(lr2.getText().equals("Emploi du temps")){
	  		Parent root = FXMLLoader.load(getClass().getResource("admin_temps.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }
	  	else if(lr2.getText().equals("Note de "+Main.F1+"")){
	  		Main.AF=Main.F1;
	    	Main.AM=Main.M1;
	    	Parent root = FXMLLoader.load(getClass().getResource("AdminNotes1.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }
	  	else if(lr2.getText().equals("Note de "+Main.F2+"")){
	  		Main.AF=Main.F2;
	    	Main.AM=Main.M2;
	    	Parent root = FXMLLoader.load(getClass().getResource("AdminNotes1.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }
	  	else if(lr2.getText().equals("Parametre")){
		  	Parent root = FXMLLoader.load(getClass().getResource("admin_parametre.fxml"));
		  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  	  scene = new Scene(root);
		  	  stage.setScene(scene);
		  	  stage.show();
		  	}
	  	else if(lr2.getText().equals("Note de "+Main.F3+"")){
	  		Main.AF=Main.F3;
	    	Main.AM=Main.M3;
	    	Parent root = FXMLLoader.load(getClass().getResource("AdminNotes1.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }

	  	else if(lr2.getText().equals("Liste de "+Main.F1+"")){
	  		Main.AF=Main.F1;
	    	Main.AM=Main.M1;
	    	Parent root = FXMLLoader.load(getClass().getResource("AdminNotes1.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }
	  	else if(lr2.getText().equals("Liste de "+Main.F2+"")){
	  		Main.AF=Main.F2;
	    	Main.AM=Main.M2;
	    	Parent root = FXMLLoader.load(getClass().getResource("AdminNotes1.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }
	  	else if(lr2.getText().equals("Liste de "+Main.F3+"")){
	  		Main.AF=Main.F3;
	    	Main.AM=Main.M3;
	    	Parent root = FXMLLoader.load(getClass().getResource("AdminNotes1.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }

	  	 }

	public void directed3(ActionEvent event) throws IOException {
	  	  if(lr3.getText().equals("Profil")) {
	  		Parent root = FXMLLoader.load(getClass().getResource("admin_profil.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();
	  	  }
	  	  else if(lr3.getText().equals("Emploi du temps")){
	  		Parent root = FXMLLoader.load(getClass().getResource("admin_temps.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }
	  	else if(lr3.getText().equals("Parametre")){
		  	Parent root = FXMLLoader.load(getClass().getResource("admin_parametre.fxml"));
		  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  	  scene = new Scene(root);
		  	  stage.setScene(scene);
		  	  stage.show();
		  	}
	  	else if(lr3.getText().equals("Note de "+Main.F1+"")){
	  		Main.AF=Main.F1;
	    	Main.AM=Main.M1;
	    	Parent root = FXMLLoader.load(getClass().getResource("AdminNotes1.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }
	  	else if(lr3.getText().equals("Note de "+Main.F2+"")){
	  		Main.AF=Main.F2;
	    	Main.AM=Main.M2;
	    	Parent root = FXMLLoader.load(getClass().getResource("AdminNotes1.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }
	  	else if(lr3.getText().equals("Note de "+Main.F3+"")){
	  		Main.AF=Main.F3;
	    	Main.AM=Main.M3;
	    	Parent root = FXMLLoader.load(getClass().getResource("AdminNotes1.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }

	  	else if(lr3.getText().equals("Liste de "+Main.F1+"")){
	  		Main.AF=Main.F1;
	    	Main.AM=Main.M1;
	    	Parent root = FXMLLoader.load(getClass().getResource("AdminNotes1.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }
	  	else if(lr3.getText().equals("Liste de "+Main.F2+"")){
	  		Main.AF=Main.F2;
	    	Main.AM=Main.M2;
	    	Parent root = FXMLLoader.load(getClass().getResource("AdminNotes1.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }
	  	else if(lr3.getText().equals("Liste de "+Main.F3+"")){
	  		Main.AF=Main.F3;
	    	Main.AM=Main.M3;
	    	Parent root = FXMLLoader.load(getClass().getResource("AdminNotes1.fxml"));
	    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	  scene = new Scene(root);
	    	  stage.setScene(scene);
	    	  stage.show();

	  	  }

	  	 }



	private void Timenow(){
	    Thread thread = new Thread(() -> {
	    	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");

	        while(Main.stop =true){
	            try{
	                Thread.sleep(1000);
	            }catch(Exception e){
	                System.out.println(e);
	            }
	            final String timenow = sdf.format(new Date());

	            Platform.runLater(() -> {
	                	    time.setText(timenow); // This is the label

	            });
	    }
	    });
	    thread.start();



	}

	private void settime1() throws SQLException, ClassNotFoundException {

		Calendar cal = Calendar.getInstance();
		//creating a constructor of the Format class
		//displaying full-day name
		Format f = new SimpleDateFormat("EEEE");
		String str = f.format(new Date());
		//prints day name
		System.out.println("Day Name: "+str);

		Class.forName("com.mysql.cj.jdbc.Driver");

		con = DriverManager.getConnection("jdbc:mysql://localhost/superpos","root","");



		if(str.equals("dimanche")) {
			System.out.println("here");
			pst =con.prepareStatement("select  m.nom AS matiere, classes.classe AS class from programme p,matiere m ,classes where p.id_enseignant =? and p.jour=?  and p.id_enseignant=m.id and p.heure='firstHour' and p.id_etudiant=classes.id");
			pst.setString(1,Main.id);
			pst.setString(2,"lundi");
			rs =pst.executeQuery();
			if(rs.next()) {
				h1.setText("8:00");
				m1.setText(rs.getString("matiere"));
				g1.setText(rs.getString("class"));


		}

			pst =con.prepareStatement("select  m.nom AS matiere, classes.classe AS class from programme p,matiere m ,classes where p.id_enseignant =? and p.jour=?  and p.id_enseignant=m.id and p.heure='secondHour' and p.id_etudiant=classes.id");
			pst.setString(1,Main.id);
			pst.setString(2,"lundi");
			rs =pst.executeQuery();
			if(rs.next()) {
				h2.setText("10:00");
				m2.setText(rs.getString("matiere"));
				g2.setText(rs.getString("class"));

			}
			pst =con.prepareStatement("select  m.nom AS matiere, classes.classe AS class from programme p,matiere m ,classes where p.id_enseignant =? and p.jour=?  and p.id_enseignant=m.id and p.heure='thirdHour' and p.id_etudiant=classes.id");
			pst.setString(1,Main.id);
			pst.setString(2,"lundi");
			rs =pst.executeQuery();
			if(rs.next()) {
				h3.setText("14:00");
				m3.setText(rs.getString("matiere"));
				g3.setText(rs.getString("class"));

			}

		}
		else {
			System.out.println("here");
			pst =con.prepareStatement("select  m.nom AS matiere, classes.classe AS class from programme p,matiere m ,classes where p.id_enseignant =? and p.jour=?  and p.id_enseignant=m.id and p.heure='firstHour' and p.id_etudiant=classes.id");
			pst.setString(1,Main.id);
			pst.setString(2,str);
			rs =pst.executeQuery();
			if(rs.next()) {
				h1.setText("8:00");
				m1.setText(rs.getString("matiere"));
				g1.setText(rs.getString("class"));


		}

			pst =con.prepareStatement("select  m.nom AS matiere, classes.classe AS class from programme p,matiere m ,classes where p.id_enseignant =? and p.jour=?  and p.id_enseignant=m.id and p.heure='secondHour' and p.id_etudiant=classes.id");
			pst.setString(1,Main.id);
			pst.setString(2,str);
			rs =pst.executeQuery();
			if(rs.next()) {
				h2.setText("10:00");
				m2.setText(rs.getString("matiere"));
				g2.setText(rs.getString("class"));

			}
			pst =con.prepareStatement("select  m.nom AS matiere, classes.classe AS class from programme p,matiere m ,classes where p.id_enseignant =? and p.jour=?  and p.id_enseignant=m.id and p.heure='thirdHour' and p.id_etudiant=classes.id");
			pst.setString(1,Main.id);
			pst.setString(2,str);
			rs =pst.executeQuery();
			if(rs.next()) {
				h3.setText("14:00");
				m3.setText(rs.getString("matiere"));
				g3.setText(rs.getString("class"));

			}
}
}
}




