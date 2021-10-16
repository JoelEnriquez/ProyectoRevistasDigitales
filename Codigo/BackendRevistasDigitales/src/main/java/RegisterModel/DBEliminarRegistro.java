/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegisterModel;

import ConexionDB.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author joel
 */
public class DBEliminarRegistro {
    private final String eliminarUsuarioQuery = "DELETE FROM Persona WHERE user_name = ?";
    private final Connection conexion = ConexionDB.getConexion();
    
    public void eliminarUsuario(String userName){
        try (PreparedStatement ps = conexion.prepareStatement(eliminarUsuarioQuery)){
            ps.setString(1, userName);
            ps.execute();  
        } catch (Exception e) {
        }
    }
}
