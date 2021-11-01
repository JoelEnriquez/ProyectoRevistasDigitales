/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportesEditor;

import ConexionDB.ConexionDB;
import EntidadesJasper.GananciaEditor;
import EntidadesRevista.Revista;
import EntidadesRevista.Suscripcion;
import RevistasModel.DBRevista;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class ReporteGananciasEditor {

    private Connection conexion = ConexionDB.getConexion();
    private final String queryGananciasSuscripcion = "Select S.*,(P.monto*(1-(P.porcentaje_ganancia/100))) from Suscripcion S INNER JOIN Pago P ON S.id = P.id_suscripcion WHERE S.nombre_revista = ?";
    private final String obtenerSuscripcionesPorFecha = queryGananciasSuscripcion + " AND S.fecha_suscripcion between ? AND ?"; //NumQuery => 2

    public ArrayList<GananciaEditor> gananciasEditor(Date fecha1, Date fecha2, String nombreRevista, boolean intervalo, String userName) {
        boolean consultaParticular = false;
        String queryTemp = queryGananciasSuscripcion;
        int numQuery = 0;
        if (intervalo) {
            queryTemp = obtenerSuscripcionesPorFecha;
            numQuery = 1;
            if (!nombreRevista.equals("null")) {
                consultaParticular = true;
            }
        } else if (!nombreRevista.equals("null")) {
            consultaParticular = true;
        }
        ArrayList<GananciaEditor> listadoGanancias = new ArrayList<>();
        ArrayList<Revista> listadoRevistas;
        if (consultaParticular) {
            listadoRevistas = new DBRevista().obtenerRevistasParametro(nombreRevista, "info_revista");
        } else {
            listadoRevistas = new DBRevista().obtenerRevistasParametro(userName, "revistas_propias");
        }
        
        for (Revista revista : listadoRevistas) {
            if (numQuery==0) {
                //Sin parametros
                listadoGanancias.add(new GananciaEditor(revista, listadoSuscripciones(queryTemp, revista.getNombre())));
            } else {
                //Con parametros
                listadoGanancias.add(new GananciaEditor(revista, listadoSuscripcionesDate(queryTemp, fecha1, fecha2, revista.getNombre())));
            }
        }   
        return listadoGanancias;
    }
    
    private ArrayList<Suscripcion> listadoSuscripcionesDate(String query, Date fecha1, Date fecha2, String nombreRevista){
        ArrayList<Suscripcion> listadoSuscripciones = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setString(1, nombreRevista);
            ps.setDate(2, fecha1);
            ps.setDate(3, fecha2);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    listadoSuscripciones.add(new Suscripcion(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getBoolean(4), rs.getString(6), rs.getDouble(7)));
                }
            }
            
        } catch (Exception e) {
        }
        return listadoSuscripciones;
    }
    
    private ArrayList<Suscripcion> listadoSuscripciones(String query, String nombreRevista){
        ArrayList<Suscripcion> listadoSuscripciones = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setString(1, nombreRevista);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    listadoSuscripciones.add(new Suscripcion(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getBoolean(4), rs.getString(6), rs.getDouble(7)));
                }
            }
            
        } catch (Exception e) {
        }
        return listadoSuscripciones;
    }

}
