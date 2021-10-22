/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonaModel;

import ConexionDB.ConexionDB;
import Personas.Editor;
import Personas.Usuario;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author joel
 */
public class DBEditarPerfil {
    private final String changeFotoQuery = "UPDATE Persona SET foto = ? WHERE user_name = ?";
    private final String updateInfoPersona = "UPDATE Persona SET nombre = ?, password = ?, hobbies = ?, descripcion = ? WHERE user_name = ?";
    private Connection conexion = ConexionDB.getConexion();
    
    public void actualizarInfoEditor(Editor editor) throws SQLException{
        try (PreparedStatement ps = conexion.prepareStatement(updateInfoPersona)){
            ps.setString(1, editor.getNombre());
            ps.setString(2, editor.getPassword());
            ps.setString(3, editor.getHobbies());
            ps.setString(4, editor.getDescripcion());
            ps.setString(5, editor.getUserName());
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
            ps.setString(3, usuario.getHobbies());
            ps.setString(4, usuario.getDescripcion());
            ps.setString(5, usuario.getUserName());
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
    
    public void cambiarFotoPerfil(InputStream foto, String userName){
        try (PreparedStatement ps = conexion.prepareStatement(changeFotoQuery)){
            ps.setBlob(1, foto);
            ps.setString(2, userName);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
