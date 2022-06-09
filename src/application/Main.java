package application;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Stack;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root =FXMLLoader.load(getClass().getResource("blue login.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            primaryStage.setResizable(false);
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("LOGO PROJET1 (1).png")));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    static Connection con;
    static PreparedStatement pst;
    static ResultSet rs;


    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    static LocalDateTime now = LocalDateTime.now();
    static String TandD = dtf.format(now);



    public static String gettimeanddate() {
        return TandD;
    }
    static String id;
     public static String getMyVariable() {

            System.out.println("my variable is"+id);
            return id;
        }

        public static void setMyVariable(String myVariable) {
            Main.id = myVariable;
            System.out.println("the variable is set to "+id);
        }
        static public String Drn;
        static public String[] go = {"acc1","acc2","acc3"};

        public static  String Code="12345" ;

        //decalaration des filliére et des matiére
        static public String F1;
        static public String F2;
        static public String F3;
        static public String M1;
        static public String M2;
        static public String M3;

        static public String AF;
        static public String AM;

        public static void get_acc() throws ClassNotFoundException, SQLException {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/superpos","root","");

        	pst =con.prepareStatement("select * from prof where id =? ");
        	pst.setString(1,id);
			rs =pst.executeQuery();
			if(rs.next()) {
				go[0]=rs.getString("acc1");
				go[1]=rs.getString("acc2");
				go[2]=rs.getString("acc3");
			}

        }



        public static void update_acc(String str) throws ClassNotFoundException, SQLException {
        	System.out.println(str);
        	System.out.println(go[0]);
        	System.out.println(go[1]);
        	System.out.println(go[2]);
        	Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/superpos","root","");
        	pst =con.prepareStatement("update prof set acc1=? , acc2=? , acc3=? where id =? ");

			pst.setString(1,str);
			pst.setString(2,go[0]);
			pst.setString(3,go[1]);
			pst.setString(4,id);
			pst.executeUpdate();

			get_acc();
        }

        private static Stage stage;
		 private static Scene scene;
		 private Parent root;
		public static boolean stop =true;
		
		 

    public static void main(String[] args) {
        launch(args);
    }


}
