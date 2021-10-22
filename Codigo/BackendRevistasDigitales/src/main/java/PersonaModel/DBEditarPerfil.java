/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonaModel;

import ConexionDB.ConexionDB;
import Personas.Editor;
import Personas.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author joel
 */
public class DBEditarPerfil {
    
    private final String updateInfoPersona = "UPDATE Persona SET nombre = ?, password = ?, foto = ?, hobbies = ?, descripcion = ? WHERE user_name = ?";
    private Connection conexion = ConexionDB.getConexion();
    
    public void actualizarInfoEditor(Editor editor) throws SQLException{
        try (PreparedStatement ps = conexion.prepareStatement(updateInfoPersona)){
            ps.setString(1, editor.getNombre());
            ps.setString(2, editor.getPassword());
            ps.setBlob(3, editor.getFotoPerfil());
            ps.setString(4, editor.getHobbies());
            ps.setString(5, editor.getDescripcion());
            ps.setString(6, editor.getUserName());
            ps.executeUpdate();
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 1136:
                    throw new SQLException("Los datos no han sido provistos de manera correcta");
                case 1406:
                    throw new SQLException("Se ha sobrepasado la cantidad de caracteres permitidos");
                default:
                    throw new SQLException(e.getMessage());
            }
        }
    }
    
    public void actualizarInfoUsuario(Usuario usuario) throws SQLException{
        try (PreparedStatement ps = conexion.prepareStatement(updateInfoPersona)){
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getPassword());
            ps.setBlob(3, usuario.getFotoPerfil());
            ps.setString(4, usuario.getHobbies());
            ps.setString(5, usuario.getDescripcion());
            ps.executeUpdate();
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 1136:
                    throw new SQLException("Los datos no han sido provistos de manera correcta");
                case 1406:
                    throw new SQLException("Se ha sobrepasado la cantidad de caracteres permitidos");
                default:
                    throw new SQLException(e.getMessage());
            }
        }
    }
}
