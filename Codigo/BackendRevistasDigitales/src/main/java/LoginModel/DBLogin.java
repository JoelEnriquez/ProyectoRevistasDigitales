/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginModel;

import ConexionDB.ConexionDB;
import Personas.Editor;
import Personas.Persona;
import Personas.PersonaEnum;
import Personas.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author joel
 */
public class DBLogin {
    private final String setDescripcionAndHobbiesQuery = "SELECT hobbies, descripcion FROM Persona WHERE user_name =?";
    private final String getTipoPersonaQuery = "SELECT * FROM Persona WHERE user_name = ? AND password = ?";
    private Connection conexion = ConexionDB.getConexion();
    
    public Persona getTipoPersona(Persona persona){
        try(PreparedStatement ps = conexion.prepareStatement(getTipoPersonaQuery)){
            ps.setString(1, persona.getUserName());
            ps.setString(2, persona.getPassword());
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    persona.setTipo(PersonaEnum.valueOf(rs.getString("tipo_usuario"))); //Set tipo de usuario
                    persona.setNombre(rs.getString("nombre"));
                }
            }
        } catch(Exception e) {
            e.printStackTrace(System.out);
        }
        return persona;
    }
    
    
}
