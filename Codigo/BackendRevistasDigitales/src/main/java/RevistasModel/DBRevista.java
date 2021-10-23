/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RevistasModel;

import ConexionDB.ConexionDB;
import EntidadesRevista.Revista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class DBRevista {

    private final String obtenerRevistasSinCostoDiarioQuery = "SELECT * FROM Revista WHERE costo_dia IS NULL";
    private final String obtenerRevistasPropiasQuery = "SELECT * FROM Revista WHERE user_name = ?";
    private final String revistasPorCategoriaQuery = "SELECT * FROM Revista WHERE nombre_categoria = ?";
    private final Connection conexion = ConexionDB.getConexion();

    public ArrayList<Revista> obtenerRevistasPropias(String userNameEditor) {
        ArrayList<Revista> listadoRevitas = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(obtenerRevistasPropiasQuery)) {
            ps.setString(1, userNameEditor);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listadoRevitas.add(
                            new Revista(rs.getString(1),
                                    rs.getString(2),
                                    rs.getBoolean(3),
                                    rs.getBoolean(4),
                                    rs.getBoolean(5),
                                    rs.getBoolean(6),
                                    rs.getDouble(7),
                                    rs.getDouble(8),
                                    rs.getString(9),
                                    rs.getString(10)));
                }
            }

        } catch (Exception e) {
        }
        return listadoRevitas;
    }

    public ArrayList<Revista> obtenerRevistasSinCostoDiario() {
        ArrayList<Revista> listadoRevitas = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(obtenerRevistasSinCostoDiarioQuery); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listadoRevitas.add(
                        new Revista(rs.getString(1),
                                rs.getString(2),
                                rs.getBoolean(3),
                                rs.getBoolean(4),
                                rs.getBoolean(5),
                                rs.getBoolean(6),
                                rs.getDouble(7),
                                rs.getDouble(8),
                                rs.getString(9),
                                rs.getString(10)));
            }

        } catch (Exception e) {
        }
        return listadoRevitas;
    }
    
    public ArrayList<Revista> obtenerRevistasPorCategoria(String nombreCategoria) {
        ArrayList<Revista> listadoRevitas = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(revistasPorCategoriaQuery)) {
            ps.setString(1, nombreCategoria);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listadoRevitas.add(
                            new Revista(rs.getString(1),
                                    rs.getString(2),
                                    rs.getBoolean(3),
                                    rs.getBoolean(4),
                                    rs.getBoolean(5),
                                    rs.getBoolean(6),
                                    rs.getDouble(7),
                                    rs.getDouble(8),
                                    rs.getString(9),
                                    rs.getString(10)));
                }
            }

        } catch (Exception e) {
        }
        return listadoRevitas;
    }
    
    
    
    
}
