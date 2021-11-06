/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportesEditor;

import EntidadesJasper.RevistaReaccion;
import EntidadesJasper.RevistaSuscripcion;
import EntidadesRevista.MeGusta;
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
public class ReporteReacciones {
    private final Connection conexion = ConexionDB.ConexionDB.getConexion();
    private final String obtenerListadoMeGustaPorNombre = "select * from Suscripcion WHERE nombre_revista = ? ";
    private final String obtenerListadoMeGustaPorNombreEIntervalo = obtenerListadoMeGustaPorNombre + " AND fecha_suscripcion between ? AND ?";
    private final String obtenerListadoRevistas = "select r.* from Revista r INNER JOIN Me_Gusta mg on r.nombre = mg.nombre_revista WHERE r.user_name = ? GROUP BY r.nombre ORDER BY count(mg.nombre_revista) DESC";
    private final String obtenerListadoRevistasIntervalo = "select r.* from Revista r INNER JOIN Me_Gusta mg on r.nombre = mg.nombre_revista WHERE r.user_name = ? AND mg.fecha_reaccion BETWEEN ? AND ? GROUP BY r.nombre ORDER BY count(mg.nombre_revista) DESC";
    
    public ArrayList<RevistaReaccion> getListadoRevistasMeGusta(Date fecha1, Date fecha2, boolean intervalo, String userNameEditor) {
        ArrayList<RevistaReaccion> listadoRevistasMeGusta = new ArrayList<>();
        ArrayList<Revista> listadoRevistas = getRevistasPorMeGusta(fecha1, fecha2, intervalo, userNameEditor);
        for (Revista revista : listadoRevistas) {
            listadoRevistasMeGusta.add(new RevistaReaccion(revista, getListadoMeGustaPorRevista(fecha1, fecha2, intervalo, revista.getNombre())));
        }
        return listadoRevistasMeGusta;
    }

    /**
     * Obtenemos el listado de las revistas que tengan comentario
     *
     * @param fecha1
     * @param fecha2
     * @param intervalo
     * @param userNameEditor
     * @return
     */
    public ArrayList<Revista> getRevistasPorMeGusta(Date fecha1, Date fecha2, boolean intervalo, String userNameEditor) {
        ArrayList<Revista> listadoRevistas = new ArrayList<>();
        String query = obtenerListadoRevistas;
        if (intervalo) {
            query = obtenerListadoRevistasIntervalo; //Without intervalo
        }        
        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, userNameEditor);
            if (intervalo) {
                ps.setDate(2, fecha1);
                ps.setDate(3, fecha2);
            }
            try ( ResultSet rs = ps.executeQuery()) {
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
     *
     * @param fecha1
     * @param fecha2
     * @param intervalo
     * @param nombreRevista
     * @return
     */
    public ArrayList<MeGusta> getListadoMeGustaPorRevista(Date fecha1, Date fecha2, boolean intervalo, String nombreRevista) {
        ArrayList<MeGusta> listadoMeGusta = new ArrayList<>();
        String query = obtenerListadoMeGustaPorNombre;
        if (intervalo) {
            query = obtenerListadoMeGustaPorNombreEIntervalo;
        }        
        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, nombreRevista);
            if (intervalo) {
                ps.setDate(2, fecha1);
                ps.setDate(3, fecha2);
            }
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listadoMeGusta.add(new MeGusta(rs.getString(1), rs.getString(2), rs.getDate(3)));
                }
            }
        } catch (Exception e) {
        }
        return listadoMeGusta;        
    }
}
