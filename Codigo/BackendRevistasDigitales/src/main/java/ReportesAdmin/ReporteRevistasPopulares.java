/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportesAdmin;

import EntidadesJasper.RevistaComentario;
import EntidadesJasper.RevistaSuscripcion;
import EntidadesRevista.Comentario;
import EntidadesRevista.Revista;
import EntidadesRevista.Suscripcion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class ReporteRevistasPopulares {

    private final Connection conexion = ConexionDB.ConexionDB.getConexion();
    private final String obtenerListadoSuscripcionesPorNombre = "select * from Suscripcion WHERE nombre_revista = ? ";
    private final String obtenerListadoSuscripcionesPorNombreEIntervalo = obtenerListadoSuscripcionesPorNombre + " AND fecha_suscripcion between ? AND ?";
    private final String obtenerListadoRevistas = "select r.*,count(*) as numero_suscripciones from Revista r INNER JOIN Suscripcion s on r.nombre = s.nombre_revista GROUP BY r.nombre ORDER BY count(s.nombre_revista) DESC LIMIT 5";
    private final String obtenerListadoRevistasIntervalo = "select r.*,count(*) as numero_suscripciones from Revista r INNER JOIN Suscripcion s on r.nombre = s.nombre_revista WHERE s.fecha_suscripcion between ? AND ? GROUP BY r.nombre ORDER BY count(s.nombre_revista) DESC LIMIT 5";
    
    public ArrayList<RevistaSuscripcion> getListadoRevistasConSuscripciones(Date fecha1, Date fecha2, boolean intervalo) {
        ArrayList<RevistaSuscripcion> listadoRevistasSuscripciones = new ArrayList<>();
        ArrayList<Revista> listadoRevistas = getRevistasPorSuscripcion(fecha1, fecha2, intervalo);
        for (Revista revista : listadoRevistas) {
            listadoRevistasSuscripciones.add(new RevistaSuscripcion(revista, getListadoSuscripcionesPorRevista(fecha1, fecha2, intervalo, revista.getNombre())));
        }
        return listadoRevistasSuscripciones;
    }

    /**
     * Obtenemos el listado de las revistas que tengan comentario
     *
     * @param fecha1
     * @param fecha2
     * @param intervalo
     * @return
     */
    public ArrayList<Revista> getRevistasPorSuscripcion(Date fecha1, Date fecha2, boolean intervalo) {
        ArrayList<Revista> listadoRevistas = new ArrayList<>();
        String query = obtenerListadoRevistas;
        if (intervalo) {
            query = obtenerListadoRevistasIntervalo;
        }        
        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            if (intervalo) {
                ps.setDate(1, fecha1);
                ps.setDate(2, fecha2);
            }
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listadoRevistas.add(new Revista(rs.getString(1),
                            rs.getString(2),
                            rs.getBoolean(6),
                            rs.getDouble(7),
                            rs.getDouble(8),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getInt(11)));
                }
            }
        } catch (Exception e) {
        }
        return listadoRevistas;
    }

    /**
     * Obtenemos los comentarios en base a la revista
     *
     * @param fecha1
     * @param fecha2
     * @param intervalo
     * @param nombreRevista
     * @return
     */
    public ArrayList<Suscripcion> getListadoSuscripcionesPorRevista(Date fecha1, Date fecha2, boolean intervalo, String nombreRevista) {
        ArrayList<Suscripcion> listadoSuscripciones = new ArrayList<>();
        String query = obtenerListadoSuscripcionesPorNombre;
        if (intervalo) {
            query = obtenerListadoSuscripcionesPorNombreEIntervalo;
        }        
        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, nombreRevista);
            if (intervalo) {
                ps.setDate(2, fecha1);
                ps.setDate(3, fecha2);
            }
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listadoSuscripciones.add(new Suscripcion(rs.getInt(1),
                                    rs.getDate(2),
                                    rs.getDate(3),
                                    rs.getBoolean(4),
                                    rs.getString(5),
                                    rs.getString(6)));
                }
            }
        } catch (Exception e) {
        }
        return listadoSuscripciones;        
    }
}
