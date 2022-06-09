package application;


import ch.makery.address.model.StudentListe;

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

public class AdminListedetaille implements Initializable{


    @FXML
    private TableView<StudentListe> table;

    @FXML
    private TableColumn<StudentListe, String> CNEColumn;

    @FXML
    private TableColumn<StudentListe,String> NomColumn;

    @FXML
    private TableColumn<StudentListe,String> PrenomColumn;

    @FXML
    private TableColumn<StudentListe,String> DateNaissanceColumn;

    @FXML
    private TableColumn<StudentListe,String> CodeApogeColumn;



    @FXML
    private TextField txt_CNE;

    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_codeApoge;


    @FXML
    private TextField txt_DateNaissance;


    @FXML
    private TextField filterField;

    @FXML
    private Button back;

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

    ObservableList<StudentListe> listM;
    ObservableList<StudentListe> dataList;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;




    public  ObservableList<StudentListe> getDatanotes() throws ClassNotFoundException, SQLException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/superpos","root","");
	     listM = FXCollections.observableArrayList();

		try{
			 PreparedStatement ps = conn.prepareStatement("Select cne, nom,prenom,code_apogé,date_naissance from etudiant where matiere= ? and filiere=? ");
			 ps.setString(1, Main.AM);
	    	 ps.setString(2,  Main.AF);
			 ResultSet rs = ps.executeQuery();

			 while (rs.next()){
				 listM.add(new StudentListe(rs.getString("cne"),rs.getString("nom"),rs.getString("prenom"),rs.getString("code_apogé"),rs.getString("date_naissance")));
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
	    	 String sql = "insert into  etudiant (cne,nom ,prenom,code_apogé,date_naissance,filiere,matiere )values(?,?,?,?,?,?,?) ";
	     try{
	    	 pst = conn.prepareStatement(sql);
	    	 pst.setString(1, txt_CNE.getText());
	    	 pst.setString(2, txt_nom.getText());
	    	 pst.setString(3, txt_prenom.getText());
	    	 pst.setString(4, txt_codeApoge.getText());
	    	pst.setString(5, txt_DateNaissance.getText());

	    	pst.setString(6, Main.AF);
	    	 pst.setString(7,  Main.AM);

	    	 pst.execute();

	    	 JOptionPane.showMessageDialog(null, "Etudiant  ajouté avec succès !");
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

    	txt_CNE.setText(CNEColumn.getCellData(index).toString());
    	txt_nom.setText(NomColumn.getCellData(index).toString());
    	txt_prenom.setText(PrenomColumn.getCellData(index).toString());
    	txt_codeApoge.setText(CodeApogeColumn.getCellData(index).toString());
    	txt_DateNaissance.setText(DateNaissanceColumn.getCellData(index).toString());


      }


    @FXML
    public void Edit(ActionEvent event) throws ClassNotFoundException {

    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		conn = DriverManager.getConnection("jdbc:mysql://localhost/superpos","root","");
    		String value1 = txt_CNE.getText();
    		String value2 = txt_nom.getText();
    		String value3 = txt_prenom.getText();
    		String value4 = txt_codeApoge.getText();
    		String value5 = txt_DateNaissance.getText();


    		pst = conn.prepareStatement("update etudiant set cne='"+value1+"' , nom='"+value2+"',prenom='"+value3+"' ,code_apogé='"+value4+"' ,date_naissance='"+value5+"' where cne='"+value1+"' and filiere= ? and matiere=? ");
    		pst.setString(1, Main.AF);
	    	 pst.setString(2,  Main.AM);
    		pst.execute();

    		JOptionPane.showMessageDialog(null, "Mis à jour avec succés !");
    		UpdateTable();
    		searchStudent();

    	} catch (SQLException e){
    		JOptionPane.showMessageDialog(null,e);

    	}

    }


   public void Delete(ActionEvent event) throws ClassNotFoundException, SQLException {
	   Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/superpos","root","");
    	String sql = "Delete from etudiant where code_apogé = ?";
    		try {
    			pst = conn.prepareStatement(sql);
    			pst.setString(1, txt_codeApoge.getText());
    			pst.execute();
    			JOptionPane.showMessageDialog(null ,  "Supprimé avec succès !");
    			UpdateTable();
    			searchStudent();

    		}catch (SQLException e) {
    			JOptionPane.showMessageDialog(null, e);
    		}
    }

   public void UpdateTable() throws ClassNotFoundException, SQLException{

    	CNEColumn.setCellValueFactory(new PropertyValueFactory<StudentListe , String>("CNE"));
    	NomColumn.setCellValueFactory(new PropertyValueFactory<StudentListe , String>("Nom"));
    	PrenomColumn.setCellValueFactory(new PropertyValueFactory<StudentListe , String>("Prenom"));
    	CodeApogeColumn.setCellValueFactory(new PropertyValueFactory<StudentListe , String>("CodeApoge"));
    	DateNaissanceColumn.setCellValueFactory(new PropertyValueFactory<StudentListe , String>("DateNaissance"));

    	listM = getDatanotes();
    	table.setItems(listM);


    }



    @FXML
   public  void searchStudent() throws ClassNotFoundException, SQLException{

    	CNEColumn.setCellValueFactory(new PropertyValueFactory<StudentListe,String>("CNE"));
    	NomColumn.setCellValueFactory(new PropertyValueFactory<StudentListe,String>("Nom"));
    	PrenomColumn.setCellValueFactory(new PropertyValueFactory<StudentListe,String>("Prenom"));
    	CodeApogeColumn.setCellValueFactory(new PropertyValueFactory<StudentListe,String>("CodeApoge"));
    	DateNaissanceColumn.setCellValueFactory(new PropertyValueFactory<StudentListe,String>("DateNaissance"));


    	dataList = getDatanotes();
    	table.setItems(dataList);
    	FilteredList<StudentListe> filteredData = new FilteredList<>(dataList, b->true);
    	filterField.textProperty().addListener((observable, oldValue, newValue) -> {
    		filteredData.setPredicate(StudentListe -> {
    					if (newValue == null || newValue.isEmpty()){
    						return true;
    					}

    					String lowerCaseFilter = newValue.toLowerCase();

    					if (StudentListe.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
    						return true;
    					}

    					else if (StudentListe.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1){
    						return true;
    					}

    					else if (StudentListe.getCNE().toLowerCase().indexOf(lowerCaseFilter) != -1){
    						return true;
    					}
    					else if (StudentListe.getCodeApoge().toLowerCase().indexOf(lowerCaseFilter) != -1){
    						return true;
    					}
    					else if (StudentListe.getDateNaissance().toLowerCase().indexOf(lowerCaseFilter) != -1){
    						return true;
    					}

    					else
    						return false;
    					});
    	});

    SortedList<StudentListe> sortedData = new SortedList<>(filteredData);
    sortedData.comparatorProperty().bind(table.comparatorProperty());
    table.setItems(sortedData);
}

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void back(ActionEvent event) throws IOException {
    	  Parent root = FXMLLoader.load(getClass().getResource("admin_list.fxml"));
    	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	  scene = new Scene(root);
    	  stage.setScene(scene);
    	  stage.show();
    	 }

    @Override
	public void initialize(URL url, ResourceBundle resources) {
    	info.setText("Liste des Etudiants de "+Main.AM+" pour "+Main.AF);

     try {
		UpdateTable();
		searchStudent();
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}



    }



}