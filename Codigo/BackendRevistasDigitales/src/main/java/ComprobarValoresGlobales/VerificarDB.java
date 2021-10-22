/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComprobarValoresGlobales;

import EntidadesApoyo.Encriptar;
import EntidadesApoyo.ValoresGlobales;
import Personas.Admin;
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
public class VerificarDB {
    
    private final String insertarValoresGlobales = "INSERT INTO Valores_Global VALUES (?,?,?,?)";
    private final String comprobarValores = "select count(*) from Valores_Global";
    private final String primerosInserts = "INSERT INTO Persona (user_name, tipo_usuario, nombre, password) VALUES (?,?,?,?)";
    private final Connection conexion = ConexionDB.ConexionDB.getConexion();
    
    public void insertarValoresGlobales(ValoresGlobales valoresGlobales) throws SQLException{
        try (PreparedStatement ps = conexion.prepareStatement(insertarValoresGlobales)){
            ps.setInt(1, valoresGlobales.getPorcentajeGanancia());
            ps.setDouble(2, valoresGlobales.getCostoAnuncioTexto());
            ps.setDouble(3, valoresGlobales.getCostoAnuncioImagen());
            ps.setDouble(4, valoresGlobales.getCostoAnuncioVideo());
            ps.execute();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
    
    public int existenciaValores(){
        try (PreparedStatement ps = conexion.prepareStatement(comprobarValores);
                ResultSet rs = ps.executeQuery()){
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    public void insertarPersonas(Persona persona){
        try (PreparedStatement ps = conexion.prepareStatement(primerosInserts)){
            ps.setString(1, persona.getUserName());
            ps.setString(2, persona.getTipo().toString());
            ps.setString(3, persona.getNombre());
            ps.setString(4, persona.getPassword());
            ps.execute();
        } catch (Exception e) {
        }
    }
    
    /**
     * Crear Primeras personas en sistema
     * @throws Exception 
     */
    public void crearPersonas() throws Exception{
        Encriptar encriptar = new Encriptar();
        insertarPersonas(new Editor("editor",encriptar.encriptar("editor"), "PrimerEditor", PersonaEnum.EDITOR));
        insertarPersonas(new Admin("admin",encriptar.encriptar("admin"), "PrimerAdmin", PersonaEnum.ADMIN));
        insertarPersonas(new Usuario("usuario",encriptar.encriptar("usuario"), "PrimerUsuario", PersonaEnum.USUARIO));
        insertarPersonas(new Editor("Gerson98",encriptar.encriptar("123"), "Gerson", PersonaEnum.EDITOR));
        insertarPersonas(new Admin("LuciaStar",encriptar.encriptar("123"), "Lucia", PersonaEnum.ADMIN));
        insertarPersonas(new Usuario("RobertPg",encriptar.encriptar("123"), "Roberto", PersonaEnum.USUARIO));
    }
}
