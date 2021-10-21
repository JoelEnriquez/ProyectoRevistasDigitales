/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonaModel;

import ConexionDB.ConexionDB;
import Personas.Editor;
import Personas.PersonaEnum;
import Personas.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author joel
 */
public class DBEditarPerfil {
    
    private final String getInfoPersonaQuery = "SELECT * FROM Persona WHERE user_name = ?";
    private final Connection conexion = ConexionDB.getConexion();
    
    public Editor getInfoEditor(String userName){
        try (PreparedStatement ps = conexion.prepareStatement(getInfoPersonaQuery)){
            ps.setString(1, userName);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return new Editor(
                            userName,
                            rs.getString("password"),
                            rs.getString("nombre"),
                            PersonaEnum.valueOf(rs.getString("tipo_usuario")),
                            rs.getString("hobbies"),
                            rs.getString("descripcion"));
                }
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public Usuario getInfoUsuario(String userName){
        try (PreparedStatement ps = conexion.prepareStatement(getInfoPersonaQuery)){
            ps.setString(1, userName);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return new Usuario(
                            rs.getString("hobbies"),
                            rs.getString("descripcion"),
                            userName,
                            rs.getString("password"),
                            rs.getString("nombre"),
                            PersonaEnum.valueOf(rs.getString("tipo_usuario")));
                }
            }
        } catch (Exception e) {
        }
        return null;
    }
}
