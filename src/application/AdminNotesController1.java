package application;

import ch.makery.address.model.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

public class AdminNotesController1 implements Initializable{


    @FXML
    private TableView<Student> table;

    @FXML
    private TableColumn<Student, String> IdColumn;

    @FXML
    private TableColumn<Student,String> NomColumn;

    @FXML
    private TableColumn<Student,String> PrenomColumn;

    @FXML
    private TableColumn<Student,Double> noteColumn;

    @FXML
    private TableColumn<Student,String> resultatColumn;

    @FXML
    private Button back;




    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_prenom;

    @FXML
    private TextField txt_note;


    @FXML
    private TextField txt_res;


    @FXML
    private TextField filterField;

    @FXML
    private Label info;

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

	 public void minimize(ActionEvent event) throws IOException {
		    Stage stage = (Stage) minimizeButton.getScene().getWindow();
		    stage.setIconified(true);
		    }
	private double x = 0;
    private double y = 0;

    ObservableList<Student> listM;
    ObservableList<Student> dataList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    private Stage stage;
    private Scene scene;
    private Parent root;





    public  ObservableList<Student> getDatanotes() throws ClassNotFoundException, SQLException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/superpos","root","");
	     listM = FXCollections.observableArrayList();

		try{
			 PreparedStatement ps = conn.prepareStatement("Select code_apogé, nom,prenom,notes, resultat from etudiant where matiere= ? and filiere=? ");
			 ps.setString(1, Main.AM);
	    	 ps.setString(2, Main.AF);
			 ResultSet rs = ps.executeQuery();

			 while (rs.next()){
				 listM.add(new Student(rs.getString("code_apogé"),rs.getString("nom"),rs.getString("prenom"),Double.parseDouble(rs.getString("notes")),rs.getString("resultat")));
			 }

		 } catch(SQLException e){
			JOptionPane.showMessageDialog(null, e);
			return null;

		 }

		return listM;

	}


	   @FXML
	    void Add_notes(ActionEvent event) throws ClassNotFoundException, SQLException {
		   Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/superpos","root","");
	    	 String sql = "insert into  etudiant (code_apogé,nom ,prenom,notes,resultat,filiere,matiere )values(?,?,?,?,?,?,?) ";
	     try{
	    	 pst = conn.prepareStatement(sql);
	    	 pst.setString(1, txt_id.getText());
	    	 pst.setString(2, txt_nom.getText());
	    	 pst.setString(3, txt_prenom.getText());
	    	 pst.setDouble(4, Double.valueOf(txt_note.getText()));
	    	 pst.setString(5, txt_res.getText());
	    	 pst.setString(6, Main.AF);
	    	 pst.setString(7, Main.AM);

	    	 pst.execute();

	    	 JOptionPane.showMessageDialog(null, "Les notes sont ajoutés avec succès !");
	    	 UpdateTable();
	    	 searchStudent();

	 	}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

	     }




    @FXML
    void getSelected(MouseEvent event) {
    	index = table.getSelectionModel().getSelectedIndex();
    	if(index <= -1){

    		return;
    	}

    	txt_id.setText(IdColumn.getCellData(index).toString());
    	txt_nom.setText(NomColumn.getCellData(index).toString());
    	txt_prenom.setText(PrenomColumn.getCellData(index).toString());
    	txt_note.setText(noteColumn.getCellData(index).toString());
    	txt_res.setText(resultatColumn.getCellData(index).toString());


      }


    @FXML
    public void Edit(ActionEvent event) throws ClassNotFoundException {

    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		conn = DriverManager.getConnection("jdbc:mysql://localhost/superpos","root","");
    		String value1 = txt_id.getText();
    		String value2 = txt_nom.getText();
    		String value3 = txt_prenom.getText();
    		String value4 = txt_note.getText();
    		String value5 = txt_res.getText();

    		pst = conn.prepareStatement("update etudiant set code_apogé='"+value1+"' , nom='"+value2+"',prenom='"+value3+"' ,notes='"+value4+"' ,resultat='"+value5+"' where code_apogé='"+value1+"' and filiere=? and matiere=? ");
    		pst.setString(1, Main.AF);
	    	 pst.setString(2, Main.AM);
    		pst.execute();

    		JOptionPane.showMessageDialog(null, "Mis à jour avec succés !");
    		UpdateTable();
    		searchStudent();

    	} catch (SQLException e){
    		JOptionPane.showMessageDialog(null,e);

    	}

    }


   public void Delete(ActionEvent event) throws ClassNotFoundException, SQLException {

    	String sql = "Delete from etudiant where code_apogé = ?";
    		try {
    			pst = conn.prepareStatement(sql);
    			pst.setString(1, txt_id.getText());
    			pst.execute();
    			JOptionPane.showMessageDialog(null ,  "Supprimé avec succès !");
    			UpdateTable();
    			searchStudent();

    		}catch (SQLException e) {
    			JOptionPane.showMessageDialog(null, e);
    		}
    }

   public void UpdateTable() throws ClassNotFoundException, SQLException{

    	IdColumn.setCellValueFactory(new PropertyValueFactory<Student , String>("Id"));
    	NomColumn.setCellValueFactory(new PropertyValueFactory<Student , String>("Nom"));
    	PrenomColumn.setCellValueFactory(new PropertyValueFactory<Student , String>("Prenom"));
    	noteColumn.setCellValueFactory(new PropertyValueFactory<Student , Double>("Note"));
    	resultatColumn.setCellValueFactory(new PropertyValueFactory<Student , String>("Resultat"));

    	listM = getDatanotes();
    	table.setItems(listM);


    }



    @FXML
   public  void searchStudent() throws ClassNotFoundException, SQLException{

    	IdColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("Id"));
    	NomColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("Nom"));
    	PrenomColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("Prenom"));
    	noteColumn.setCellValueFactory(new PropertyValueFactory<Student,Double>("Note"));
    	resultatColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("Resultat"));


    	dataList = getDatanotes();
    	table.setItems(dataList);
    	FilteredList<Student> filteredData = new FilteredList<>(dataList, b->true);
    	filterField.textProperty().addListener((observable, oldValue, newValue) -> {
    		filteredData.setPredicate(student -> {
    					if (newValue == null || newValue.isEmpty()){
    						return true;
    					}

    					String lowerCaseFilter = newValue.toLowerCase();

    					if (student.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
    						return true;
    					}

    					else if (student.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1){
    						return true;
    					}

    					else if (student.getId().toLowerCase().indexOf(lowerCaseFilter) != -1){
    						return true;
    					}

    					else
    						return false;
    					});
    	});

    SortedList<Student> sortedData = new SortedList<>(filteredData);
    sortedData.comparatorProperty().bind(table.comparatorProperty());
    table.setItems(sortedData);
}

    public void back(ActionEvent event) throws IOException {
  	  Parent root = FXMLLoader.load(getClass().getResource("admin_note.fxml"));
  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	  scene = new Scene(root);
  	  stage.setScene(scene);
  	  stage.show();
  	 }


    @Override
	public void initialize(URL url, ResourceBundle resources) {

    	info.setText("Gestion des Notes de "+Main.AM+" pour "+Main.AF);
     try {
		UpdateTable();
		searchStudent();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}



    }



}