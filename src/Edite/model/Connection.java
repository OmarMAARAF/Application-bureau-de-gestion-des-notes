package Edite.model;



import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

public static 	Connection connection =null ;

	public static Connection getConnection() throws SQLException
	{

			String dbUrl="jdbc:mysql://localhost:3306/najah";
			String userName="root";
			String password ="";

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection connection =(Connection) DriverManager.getConnection(dbUrl,userName,password);
			System.out.println("connexion reussi ");

		return connection;
	}
}
