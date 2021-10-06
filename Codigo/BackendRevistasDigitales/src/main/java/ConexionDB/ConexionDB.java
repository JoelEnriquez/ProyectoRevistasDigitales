/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author joel
 */
public class ConexionDB {
      private static final String URL="jdbc:mysql://localhost:3306/ProyectoRevistas";
    private String user="admin_revistas";
    private String password="Proyecto_Revistas_2021";
    private static Connection conexion=null;

    public static Connection getConexion(){
        if (conexion==null) {
            new ConexionDB();
        }
        return conexion;
    }
    
     private ConexionDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, user, password);
            System.out.println("Conexion establecida");
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("No se ha podido generar la conexion");
        }
    }
}
