/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RevistasModel;

import ConexionDB.ConexionDB;
import EntidadesRevista.Etiqueta;
import EntidadesRevista.Revista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class DBEtiqueta {

    private final String insertEtiqueta = "INSERT INTO Etiqueta VALUES (?)";
    private final String listadoEtiquetasRevistaQuery = "SELECT nombre_etiqueta FROM Etiquetas_Revista WHERE nombre_revista = ?";
    private final Connection conexion = ConexionDB.getConexion();

    public void insertEtiqueta(Etiqueta etiqueta) throws SQLException {
        try ( PreparedStatement ps = conexion.prepareStatement(insertEtiqueta)) {
            ps.setString(1, etiqueta.getNombre());
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
    
    public ArrayList<Etiqueta> getEtiquetasRevista(String nombreRevista){
        ArrayList<Etiqueta> etiquetasRevista = new ArrayList<>();
        
        try (PreparedStatement ps = conexion.prepareStatement(listadoEtiquetasRevistaQuery)){
            ps.setString(1, nombreRevista);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    etiquetasRevista.add(new Etiqueta(rs.getString(1)));
                }
            }
        } catch (Exception e) {
        }
        return etiquetasRevista;
    }
}
