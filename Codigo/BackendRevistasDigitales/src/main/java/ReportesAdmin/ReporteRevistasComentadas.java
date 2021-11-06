/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportesAdmin;

import EntidadesJasper.RevistaComentario;
import EntidadesRevista.Comentario;
import EntidadesRevista.Revista;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class ReporteRevistasComentadas {
    private final Connection conexion = ConexionDB.ConexionDB.getConexion();
    private final String obtenerListadoComentariosPorNombre = "select * from Comentario WHERE nombre_revista = ? ";
    private final String obtenerListadoComentariosPorNombreEIntervalo = obtenerListadoComentariosPorNombre+" AND fecha_comentario between ? AND ?";
    private final String obtenerListadoRevistas = "select r.* from Revista r INNER JOIN Comentario c on r.nombre = c.nombre_revista GROUP BY r.nombre ORDER BY count(c.nombre_revista) DESC LIMIT 5";
    private final String obtenerListadoRevistasIntervalo = "select r.* from Revista r INNER JOIN Comentario c on r.nombre = c.nombre_revista where c.fecha_comentario between ? AND ? GROUP BY r.nombre ORDER BY count(c.nombre_revista) DESC LIMIT 5";

    
    public ArrayList<RevistaComentario> getListadoRevistasConComentarios(Date fecha1, Date fecha2, boolean intervalo){
        ArrayList<RevistaComentario> listadoRevistasConComentario = new ArrayList<>();
        ArrayList<Revista> listadoRevistas = getRevistasPorComentario(fecha1, fecha2, intervalo);
        for (Revista revista : listadoRevistas) {
            listadoRevistasConComentario.add(new RevistaComentario(revista, getListadoComentariosPorRevista(fecha1, fecha2, intervalo, revista.getNombre())));
        }
        return listadoRevistasConComentario;
    }
    
    /**
     * Obtenemos el listado de las revistas que tengan comentario
     * @param fecha1
     * @param fecha2
     * @param intervalo
     * @return 
     */
    public ArrayList<Revista> getRevistasPorComentario(Date fecha1, Date fecha2, boolean intervalo){
        ArrayList<Revista> listadoRevistas = new ArrayList<>();
        String query = obtenerListadoRevistas;
        if (intervalo) {
            query = obtenerListadoRevistasIntervalo;
        } 
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            if (intervalo) {
                ps.setDate(1, fecha1);
                ps.setDate(2, fecha2);
            }
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    listadoRevistas.add(new Revista(rs.getString(1), 
                            rs.getString(2),
                            rs.getBoolean(6),
                            rs.getDouble(7),
                            rs.getDouble(8),
                            rs.getString(9),
                            rs.getString(10)));
                }
            }
        } catch (Exception e) {
        }
        return listadoRevistas;
    }
    
    /**
     * Obtenemos los comentarios en base a la revista
     * @param fecha1
     * @param fecha2
     * @param intervalo
     * @param nombreRevista
     * @return 
     */
    public ArrayList<Comentario> getListadoComentariosPorRevista(Date fecha1, Date fecha2, boolean intervalo,String nombreRevista){
        ArrayList<Comentario> listadoComentarios = new ArrayList<>();
        String query = obtenerListadoComentariosPorNombre;
        if (intervalo) {
            query = obtenerListadoComentariosPorNombreEIntervalo;
        } 
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setString(1, nombreRevista);
            if (intervalo) {
                ps.setDate(2, fecha1);
                ps.setDate(3, fecha2);
            }
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    listadoComentarios.add(new Comentario(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getDate(3),
                            rs.getString(4),
                            rs.getString(5)));
                }
            }
        } catch (Exception e) {
        }
        return listadoComentarios;     
    }

}
