/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RevistasModel;

import ConexionDB.ConexionDB;
import EntidadesRevista.Categoria;
import EntidadesRevista.Etiqueta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class DBEscogerEtiquetas {
    
    private final Connection conexion = ConexionDB.getConexion();
    private final String mostrarEtiquetasAsignadasQuery = "SELECT nombre_etiqueta FROM Preferencias_Usuario WHERE user_name = ?";
    
    public ArrayList<Etiqueta> getListadoEtiquetas(String userName) {
        ArrayList<Etiqueta> listadoEtiquetas = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(mostrarEtiquetasAsignadasQuery)) {
            ps.setString(1, userName);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listadoEtiquetas.add(
                            new Etiqueta(rs.getString(1))
                    );
                }
            }
        } catch (Exception e) {
        }
        return listadoEtiquetas;
    }
}
