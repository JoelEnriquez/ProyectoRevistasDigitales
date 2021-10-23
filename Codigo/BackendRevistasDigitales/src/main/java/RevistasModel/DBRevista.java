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

    private final String numeroReaccionesQuery = "SELECT COUNT(*) FROM Me_Gusta WHERE nombre_revista = ?";
    private final String obtenerRevistasSinCostoDiarioQuery = "SELECT * FROM Revista WHERE costo_dia IS NULL";
    private final String obtenerRevistasPropiasQuery = "SELECT * FROM Revista WHERE user_name = ?";
    private final String revistasPorCategoriaQuery = "SELECT * FROM Revista WHERE nombre_categoria = ?";
    private final String revistasPorCategoriaQueryInicio = revistasPorCategoriaQuery+"ORDER BY RAND () LIMIT 5";
    private final Connection conexion = ConexionDB.getConexion();

    /**
     * Obtiene un listado de revistas en base a un parametro solicitado
     * @param parameter
     * @param query
     * @return 
     */
    public ArrayList<Revista> obtenerRevistasParametro(String parameter, String query) {
        switch (query) {
            case "revistasPropias":
                query = obtenerRevistasPropiasQuery;
                break;
            case "revistasCategoria":
                query = revistasPorCategoriaQuery;
                break;
            case "revistasCategoriaInicio":
                query = revistasPorCategoriaQueryInicio;
                break;    
        }
        ArrayList<Revista> listadoRevitas = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, parameter);
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
    
    public Integer numeroReaccionesRevista(String nombreRevista){
        try (PreparedStatement ps = conexion.prepareStatement(numeroReaccionesQuery)){
            ps.setString(1, nombreRevista);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            
        } catch (Exception e) {
        }
        return 0;
    }
    
    
    
    
    
    
}
