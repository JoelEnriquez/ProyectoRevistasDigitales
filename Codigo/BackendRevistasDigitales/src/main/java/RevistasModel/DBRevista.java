/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RevistasModel;

import ConexionDB.ConexionDB;
import EntidadesRevista.Comentario;
import EntidadesRevista.Publicacion;
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

    private final String listadoComentariosQuery = "SELECT * FROM Comentario WHERE nombre_revista = ?";
    private final String listadoPublicacionesQuery = "SELECT * FROM Publicacion WHERE nombre_revista = ?";
    private final String precioSuscripcionRevista = "SELECT costo_suscripcion FROM Revista WHERE nombre = ?";
    private final String revistaPorNombre = "SELECT * FROM Revista WHERE nombre = ?";
    private final String obtenerRevistasSinCostoDiarioQuery = "SELECT * FROM Revista WHERE costo_dia IS NULL";
    private final String obtenerRevistasPropiasQuery = "SELECT * FROM Revista WHERE user_name = ?";
    private final String revistasPorCategoriaQuery = "SELECT * FROM Revista WHERE nombre_categoria = ?";
    private final String revistasPorCategoriaQueryInicio = revistasPorCategoriaQuery + " ORDER BY RAND () LIMIT 5";
    private final String revistasPorEtiquetaQuery = "select r.* from Revista r inner join Etiquetas_Revista er on r.nombre = er.nombre_revista where er.nombre_etiqueta = ?";
    private final String revistasPorEtiquetaQueryInicio = revistasPorEtiquetaQuery + " ORDER BY RAND () LIMIT 5";
    private final String revistasSuscritoQuery = "SELECT R.* FROM Revista R INNER JOIN Suscripcion S ON R.nombre = S.nombre_revista WHERE S.user_name = ? AND ((S.suscripcion_activa=1 AND isnull(S.fecha_caducidad)) OR (S.suscripcion_activa = 1 AND date(now()) < fecha_caducidad))";
    private final Connection conexion = ConexionDB.getConexion();

    /**
     * Obtiene un listado de revistas en base a un parametro solicitado
     *
     * @param parameter
     * @param query
     * @return
     */
    public ArrayList<Revista> obtenerRevistasParametro(String parameter, String query) {
        switch (query) {
            case "revistas_propias":
                query = obtenerRevistasPropiasQuery;
                break;
            case "categoria":
                query = revistasPorCategoriaQuery;
                break;
            case "revistas_categoria":
                query = revistasPorCategoriaQueryInicio;
                break;
            case "etiqueta":
                query = revistasPorEtiquetaQuery;
                break;
            case "revistas_etiqueta":
                query = revistasPorEtiquetaQueryInicio;
                break;
            case "info_revista":
                query = revistaPorNombre;
                break;
            case "revistas_suscrito":
                query = revistasSuscritoQuery;
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
        try ( PreparedStatement ps = conexion.prepareStatement(obtenerRevistasSinCostoDiarioQuery);  ResultSet rs = ps.executeQuery()) {
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
    
    public Double getCostoSuscripcion(String nombreRevista){
        try (PreparedStatement ps = conexion.prepareStatement(precioSuscripcionRevista)){
            ps.setString(1, nombreRevista);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            }
        } catch (Exception e) {
        }
        return 0.0;
    }

    public ArrayList<Publicacion> getListadoPublicaciones(String nombreRevista){
        ArrayList<Publicacion> listadoPublicaciones = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(listadoPublicacionesQuery)){
            ps.setString(1, nombreRevista);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    listadoPublicaciones.add(
                    new Publicacion(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toString(),
                            rs.getString(5)));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
        return listadoPublicaciones;
    }
    
     public ArrayList<Comentario> getListadoComentarios(String nombreRevista){
        ArrayList<Comentario> listadoComentarios = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(listadoComentariosQuery)){
            ps.setString(1, nombreRevista);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    listadoComentarios.add(
                    new Comentario(rs.getInt(1),
                            rs.getString(2),
                            rs.getDate(3).toString(),
                            rs.getString(4),
                            rs.getString(5)));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
        return listadoComentarios;
    }
    
    

}
