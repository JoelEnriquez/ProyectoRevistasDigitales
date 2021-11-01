/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportesEditor;

import ConexionDB.ConexionDB;
import EntidadesRevista.Comentario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class ReporteComentarios {
    
    private final String obtenerComentariosQuery = "SELECT C.* FROM Comentario C INNER JOIN Revista R ON C.nombre_revista = R.nombre WHERE R.user_name=?";
    private final String obtenerComentariosPorRevista = obtenerComentariosQuery + " AND nombre_revista = ?"; //NumQuery => 1
    private final String obtenerComentariosPorFecha = obtenerComentariosQuery+" AND fecha_comentario between ? AND ?"; //NumQuery => 2
    private final String obtenerComentariosPorFechaNombreRevista = obtenerComentariosPorFecha+" AND nombre_revista = ?"; //NumQuery => 3
    private Connection conexion = ConexionDB.getConexion();
    
    public ArrayList<Comentario> listadoComentarios(Date fecha1, Date fecha2, String nombreRevista, boolean intervalo, String userName){
        String queryTemp = obtenerComentariosQuery;
        int numQuery = 0;
        if (intervalo) {
            queryTemp = obtenerComentariosPorFecha; numQuery = 2;
            if (!nombreRevista.equals("null")) {
                queryTemp = obtenerComentariosPorFechaNombreRevista; numQuery = 3;
            }
        } 
        if(!nombreRevista.equals("null")) {
            queryTemp = obtenerComentariosPorRevista; numQuery = 1;
        }
        queryTemp += " ORDER BY C.fecha_comentario";
        ArrayList<Comentario> listadoComentarios = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(queryTemp)){
            ps.setString(1, userName);
            switch (numQuery) {
                case 1:
                    ps.setString(2, nombreRevista);
                    break;
                case 2:
                    ps.setDate(2, fecha1);
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
