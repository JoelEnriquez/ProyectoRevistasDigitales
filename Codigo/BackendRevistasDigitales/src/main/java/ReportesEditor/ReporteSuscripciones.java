/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportesEditor;

import ConexionDB.ConexionDB;
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
public class ReporteSuscripciones {
    private Connection conexion = ConexionDB.getConexion();
    private final String obtenerSuscripcionesQuery = "SELECT S.* FROM Suscripcion S INNER JOIN Revista R ON S.nombre_revista = R.nombre WHERE R.user_name=?"; 
    private final String obtenerSuscripcionesPorNombre = obtenerSuscripcionesQuery+" AND nombre_revista = ?"; //NumQuery => 1
    private final String obtenerSuscripcionesPorFecha = obtenerSuscripcionesQuery+" AND fecha_suscripcion between ? AND ?"; //NumQuery => 2
    private final String obtenerSuscripcionesPorFechaYNombre = obtenerSuscripcionesQuery+" AND fecha_suscripcion between ? AND ? AND nombre_revista = ?"; //NumQuery => 3
    
    public ArrayList<Suscripcion> listadoSuscripciones(Date fecha1, Date fecha2, String nombreRevista, boolean intervalo, String userName){
        String queryTemp = obtenerSuscripcionesQuery;
        int numQuery = 0;
        if (intervalo) {
            queryTemp = obtenerSuscripcionesPorFecha; numQuery = 2;
            if (!nombreRevista.equals("null")) {
                queryTemp = obtenerSuscripcionesPorFechaYNombre; numQuery = 3;
            }
        } else if (!nombreRevista.equals("null")) {
            queryTemp = obtenerSuscripcionesPorNombre; numQuery = 1;
        }
        ArrayList<Suscripcion> listadoSuscripciones = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(queryTemp)){
            ps.setString(1, userName);
            switch (numQuery) {
                case 1:
                    ps.setString(2, nombreRevista);
                    break;
                case 2:
                    ps.setDate(3, fecha1);
                    ps.setDate(3, fecha2);
                    break;
                case 3:
                    ps.setDate(2, fecha1);
                    ps.setDate(3, fecha2);
                    ps.setString(4, nombreRevista);
                    break;
            }
            
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    listadoSuscripciones.add(
                            new Suscripcion(rs.getInt(1),
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
