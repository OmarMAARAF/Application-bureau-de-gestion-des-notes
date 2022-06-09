package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.ElementListener;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.HostServices;
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

public class admin_profilController implements Initializable{


	@FXML
	private HBox note;
	@FXML
	private Label L1;
	@FXML
	private Label L2;
	@FXML
	private Label L3;
	@FXML
	private Label L4;
	@FXML
	private Label L5;
	@FXML
	private Label L6;
	@FXML
	private Label L7;
	@FXML
	private Label L8;
	@FXML
	private Label L9;
	@FXML
	private Label L10;
	@FXML
	private Label L11;
	@FXML
	private Label L12;
	@FXML
	private Label L13;
	@FXML
	private Label L14;
	@FXML
	private Label L15;
	@FXML
	private Label L16;
	@FXML
	private Label L17;
	@FXML
	private Label L18;
	@FXML
	private Label L19;
	@FXML
	private Label L100;
	@FXML
	private Label L200;

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

    public String _L1;
    public String _L2;
    public String _L3;
    public String _L4;
    public String _L5;
    public 	String _L6;
	public String _L7;
	public 	String _L8;
	public String _L9;
	public String _L10;
	public String _L11;
	public String _L12;
	public String _L13;
	public String _L14;
	public String _L15;
	public String _L16;
	public String _L17;
	public String _L18;
	public String _L19;


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize(URL arg0, ResourceBundle arg1){
		// TODO Auto-generated method stub

    	try {
			getinformation(Main.id);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
    		if(home_controller.n1.equals("Profil") || home_controller.n2.equals("Profil") || home_controller.n3.equals("Profil")) {

    		}
    		else {
			Main.update_acc("Profil");
    		}
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


    public void getinformation(String id) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost/superpos","root","");
			pst =con.prepareStatement("select * from prof where id=?");
			pst.setString(1,id);
			rs =pst.executeQuery();

			if(rs.next()) {
				_L1=rs.getString("nom");
				_L2=rs.getString("prenom");
				_L3=rs.getString("Date");
				_L4=rs.getString("Ville");
				_L5=rs.getString("Téléphone");
				_L6=rs.getString("Fax");
				_L7=rs.getString("Email");
				_L8=rs.getString("Profession");
				_L9=rs.getString("Spécialité");
				_L10=rs.getString("Département");
				_L11=rs.getString("Equipe");
				_L12=rs.getString("Laboratoire");
				_L13=rs.getString("Phd");
				_L14=rs.getString("Idet");
				_L15=rs.getString("F1");
				 _L16=rs.getString("F2");
				 _L17=rs.getString("F3");
				 _L18=rs.getString("cne");
				 _L19=rs.getString("adresse");
				 L100.setText(rs.getString("nom"));
				 L200.setText(rs.getString("prenom"));
				L1.setText(_L1);
				L2.setText(_L2);
				L3.setText(_L3);
				L4.setText(_L4);
				L5.setText(_L18);
				L6.setText(_L5);
				L7.setText(_L6);
				L8.setText(_L7);
				L9.setText(_L19);
				L10.setText(_L8);
				L11.setText(_L9);
				L12.setText(_L10);
				L13.setText(_L11);
				L14.setText(_L12);
				L15.setText(_L13);
				L16.setText(_L14);
				L17.setText(_L15);
				L18.setText(_L16);
				L19.setText(_L17);


			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public void generatepdf(){
    	try{
            Document document=new Document(PageSize.A4);


            PdfWriter.getInstance(document,new FileOutputStream(""+L1.getText()+" "+L2.getText()+".pdf"));
            document.open();
            /*document.add(new Paragraph("Nom : \t \t "+_L1+"\nprénom: "+_L2+
            		"\nDate de Naissance : \t \t "+_L3+"\nVille de Naissance : \t \t "+_L4+
            		"\nCIN : \t \t "+_L18+"\nTéléphone : \t \t "+_L5+"\nFax : \t \t "+_L6+"\nEmail : \t \t "+_L7+
            		"\nAdresse : \t \t "+_L19+"\nProfession : \t \t "+_L8+"\nSpécialité : \t \t "+_L9+"\nDépartement : \t \t "+_L10+
            		"\nEquipe de recherche : \t \t "+_L11+"\nLaboratoire de recherche : \t \t "+_L12+
            		"\nPhd : \t \t "+_L13+"\nIdentifiant : \t \t "+_L14+"\nFilliere : \t \t "+_L15+"."+_L16+"."+_L17+""));*/
            Image image1 = Image.getInstance(".//src//application//LOGO.png");
            image1.setAlignment(Element.ALIGN_CENTER);
			image1.scaleAbsolute(200, 200);

			document.add(image1);

			Font redFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, new CMYKColor(100, 30, 0, 17));
            Paragraph paragraph = new Paragraph("Fiche Personnelle",redFont);

			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);
            PdfPTable table = new PdfPTable(2);
            document.add(new Paragraph("\n \n "));
            table.getDefaultCell().setBorder(0);
                table.addCell("Nom");
                table.addCell(""+L1.getText());
                table.addCell("prénom");
                table.addCell(""+L2.getText());


                table.addCell("Date de Naissance");
                table.addCell(L3.getText());
                table.addCell("Ville de Naissance");
                table.addCell(L4.getText());
                table.addCell("Cin");
                table.addCell(L5.getText());
               table.addCell("Téléphone");
                table.addCell(""+L6.getText());
                 table.addCell("Fax");
                table.addCell(""+L7.getText());
                 table.addCell("Email");
                table.addCell(""+L8.getText());
                table.addCell("Adresse");
                table.addCell(""+L9.getText());
                table.addCell("Profession");
                table.addCell(""+L10.getText());
                table.addCell("Spécialité");
                table.addCell(""+L11.getText());
                table.addCell("Département");
                table.addCell(""+L12.getText());
                table.addCell("Equipe de recherche");
                table.addCell(""+L13.getText());
                table.addCell("Laboratoire de recherche :");

                table.addCell(""+L14.getText());
                table.addCell("Phd");
                table.addCell(""+L15.getText());
                table.addCell("Identifiant");
                table.addCell(""+L16.getText());
               table.addCell("Filliére 1");
                table.addCell(""+L17.getText());
                table.addCell("Filliére 2");
                table.addCell(""+L18.getText());
                 table.addCell("Filliére 2");
                table.addCell(""+L19.getText());



            // Adding Table to document
            document.add(table);

            document.close();



        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println("itext PDF program executed");

    }
	}









