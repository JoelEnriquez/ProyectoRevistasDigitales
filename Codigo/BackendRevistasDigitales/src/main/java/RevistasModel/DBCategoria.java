/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RevistasModel;

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
public class DBCategoria {
    
    private final Connection conexion = ConexionDB.getConexion();
    private final String obtenerListastadoCategoriasQuery = "SELECT * FROM Categoria";

    public ArrayList<Categoria> getListadoCategorias() {
        ArrayList<Categoria> listadoCategorias = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(obtenerListastadoCategoriasQuery);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listadoCategorias.add(
                        new Categoria(rs.getString(1))
                );
            }
        } catch (Exception e) {
        }
        return listadoCategorias;
    }
}
