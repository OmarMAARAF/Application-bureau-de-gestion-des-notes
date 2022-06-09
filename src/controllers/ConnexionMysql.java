package controllers;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionMysql {
     public Connection cn=null;
     public static Connection connexionDB()
     {
    	 try {
    		 Class.forName("com.mysql.cj.jdbc.Driver");
    		 Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/superpos", "root", "");
    		 System.out.println("cnx reussite");
    		 return cn;
    	 }catch (ClassNotFoundException | SQLException e) {
    		 System.out.println("erreur");
    		 e.printStackTrace();
    		 return null;
    	 }

     }
}
