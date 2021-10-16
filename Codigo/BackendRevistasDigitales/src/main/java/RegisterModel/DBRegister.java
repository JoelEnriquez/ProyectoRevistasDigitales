/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegisterModel;

import ConexionDB.ConexionDB;
import Personas.Editor;
import Personas.Persona;
import Personas.PersonaEnum;
import Personas.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joel
 */
public class DBRegister {

    private final String insertPersonaQuery = "INSERT INTO Persona VALUES (?,?,?,?,?,?,?)";
    private final String existenciaUsuario = "SELECT COUNT(*) FROM Persona WHERE user_name = ?";
    
    private Connection conexion = ConexionDB.getConexion();

    public void insertEditor(Editor editor) throws SQLException {
        try ( PreparedStatement ps = conexion.prepareStatement(insertPersonaQuery)) {
            ps.setString(1, editor.getUserName());
            ps.setString(2, editor.getTipo().toString());
            ps.setString(3, editor.getNombre());
            ps.setString(4, editor.getPassword());
            ps.setBlob(5, editor.getFoto());
            ps.setString(6, editor.getHobbies());
            ps.setString(7, editor.getDescripcion());

            ps.execute();
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 1062:
                    throw new SQLException("Ya existe una persona, con dicho nombre de usuario");
                case 1406:
                    throw new SQLException("Se ha sobrepasado la cantidad de caracteres permitidos");
                default:
                    throw new SQLException(e.getMessage());
            }
        }

    }
    
    public boolean insertarUsuario(Usuario usuario) throws SQLException {
        try ( PreparedStatement ps = conexion.prepareStatement(insertPersonaQuery)) {
            ps.setString(1, usuario.getUserName());
            ps.setString(2, usuario.getTipo().toString());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getPassword());
            ps.setBlob(5, usuario.getFoto());
            ps.setString(6, usuario.getHobbies());
            ps.setString(7, usuario.getDescripcion());

            return ps.execute();
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 1062:
                    throw new SQLException("Ya existe una persona, con dicho nombre de usuario");
                case 1406:
                    throw new SQLException("Se ha sobrepasado la cantidad de caracteres permitidos");
                default:
                    throw new SQLException(e.getMessage());
            }
        }

    }
    
    public Boolean existenciaUsuario(String userName){
        try (PreparedStatement ps = conexion.prepareStatement(existenciaUsuario)){
            ps.setString(1, userName);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getInt(1)==1;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }
}
