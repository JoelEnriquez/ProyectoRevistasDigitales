/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegisterModel;

import ConexionDB.ConexionDB;
import EntidadesRevista.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class DBEscogerCategorias {

    private final Connection conexion = ConexionDB.getConexion();
    private final String mostrarCategoriasQuery = "SELECT * FROM Categoria";
    private final String insertCategoriasUsuarioQuery = "INSERT INTO Categorias_Usuario VALUES (?,?)";

    public ArrayList<Categoria> getListadoCategorias() {
        ArrayList<Categoria> listadoCategorias = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(mostrarCategoriasQuery);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listadoCategorias.add(
                        new Categoria(rs.getString(1))
                );
            }
        } catch (Exception e) {
        }
        return listadoCategorias;
    }
    
    public void agregarCategoriasUsuario(String userName, Categoria categoria){
        try ( PreparedStatement ps = conexion.prepareStatement(insertCategoriasUsuarioQuery)){
            ps.setString(1, userName);
            ps.setString(2, categoria.getNombre());
            ps.execute();
        } catch (Exception e) {
        }
    }

}
