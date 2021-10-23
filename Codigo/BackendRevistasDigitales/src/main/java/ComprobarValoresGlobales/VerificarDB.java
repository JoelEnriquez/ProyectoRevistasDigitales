/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComprobarValoresGlobales;

import EntidadesApoyo.Encriptar;
import EntidadesApoyo.ValoresGlobales;
import EntidadesRevista.Categoria;
import EntidadesRevista.Revista;
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
    
    private final String crearRevistaQuery = "INSERT INTO Revista VALUES (?,?,?,?,?,?,?,?,?,?)";
    private final String insertarValoresGlobales = "INSERT INTO Valores_Global VALUES (?,?,?,?)";
    private final String comprobarValores = "select count(*) from Valores_Global";
    private final String insertCategoriasUsuarioQuery = "INSERT INTO Categorias_Usuario VALUES (?,?)";
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
    
    public void agregarCategoriasUsuario(String userName, Categoria categoria) {
        try ( PreparedStatement ps = conexion.prepareStatement(insertCategoriasUsuarioQuery)) {
            ps.setString(1, userName);
            ps.setString(2, categoria.getNombre());
            ps.execute();
        } catch (Exception e) {
        }
    }
    
     public void insertarRevista(Revista revista) throws SQLException {
        try ( PreparedStatement ps = conexion.prepareStatement(crearRevistaQuery)) {
            ps.setString(1, revista.getNombre());
            ps.setString(2, revista.getDescripcion());
            ps.setBoolean(3, revista.isSuscribir());
            ps.setBoolean(4, revista.isComentar());
            ps.setBoolean(5, revista.isReaccionar());
            ps.setBoolean(6, revista.isPago());
            ps.setObject(7, revista.getCostoSuscripcion(), java.sql.Types.DOUBLE);
            ps.setDouble(8, revista.getCostoDia());
            ps.setString(9, revista.getNombreCategoria());
            ps.setString(10, revista.getUserName());
            ps.execute();
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 1062:
                    throw new SQLException("Ya existe una revista con dicho nombre");
                case 1406:
                    throw new SQLException("Se ha sobrepasado la cantidad de caracteres permitidos en uno de los campos");
                default:
                    throw new SQLException(e.getMessage());
            }
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
        agregarCategoriasUsuario("PrimerUsuario",new Categoria("Drama"));
        agregarCategoriasUsuario("PrimerUsuario",new Categoria("Misterio"));
        agregarCategoriasUsuario("PrimerUsuario",new Categoria("Belleza"));
        agregarCategoriasUsuario("PrimerUsuario",new Categoria("Deportes"));
        agregarCategoriasUsuario("PrimerUsuario",new Categoria("Finanzas"));
        agregarCategoriasUsuario("Roberto",new Categoria("Drama"));
        agregarCategoriasUsuario("Roberto",new Categoria("Comedy|Romance"));
        agregarCategoriasUsuario("Roberto",new Categoria("Thriller"));
        agregarCategoriasUsuario("Roberto",new Categoria("Crime|Mystery"));
        agregarCategoriasUsuario("Roberto",new Categoria("Finanzas"));
        
        insertarRevista(new Revista("Omnicom Inc","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, false, null, 38.0, "Thriller", "Gerson98"));
        insertarRevista(new Revista("Revista1","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, false, null, 25.0, "Drama", "Gerson98"));
        insertarRevista(new Revista("Revista2","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, false, null, 27.0, "Deportes", "Gerson98"));
        insertarRevista(new Revista("Revista3","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, false, null, 28.0, "Drama", "Gerson98"));
        insertarRevista(new Revista("Revista4","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, false, null, 29.0, "Drama", "Gerson98"));
        insertarRevista(new Revista("Revista5","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, false, null, 20.0, "Drama", "Gerson98"));
        insertarRevista(new Revista("Revista6","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, false, null, 21.0, "Drama", "Gerson98"));
        insertarRevista(new Revista("Revista7","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, false, null, 34.0, "Drama", "Gerson98"));
        insertarRevista(new Revista("Revista8","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, false, null, 12.0, "Comedia", "Gerson98"));
        insertarRevista(new Revista("Revista9","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, false, null, 25.0, "Terror", "Gerson98"));
        insertarRevista(new Revista("Revista10","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, false, null, 27.0, "Poemas", "editor"));
        insertarRevista(new Revista("Revista11","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, false, null, 27.0, "Comedia", "editor"));
        insertarRevista(new Revista("Revista12","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, true, 65.0, 27.0, "Musica", "editor"));
        insertarRevista(new Revista("Revista13","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, true, 85.0, 28.0, "Belleza", "editor"));
        insertarRevista(new Revista("Revista14","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, true, 55.0, 29.0, "Deportes", "editor"));
        insertarRevista(new Revista("Revista15","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, true, 105.0, 20.0, "Cine", "editor"));
        insertarRevista(new Revista("Revista16","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, true, 95.0, 21.0, "Ciencia", "editor"));
        insertarRevista(new Revista("Revista17","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, false, null, 34.0, "Comedia", "editor"));
        insertarRevista(new Revista("Revista18","justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit", true, true, true, false, null, 12.0, "Comedia", "editor"));
       
        
        
    }
}
