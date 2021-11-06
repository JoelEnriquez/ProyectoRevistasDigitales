/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RevistasModel;

import ConexionDB.ConexionDB;
import EntidadesRevista.MeGusta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author joel
 */
public class DBEstadisticasRevista {

    private final String reaccionUsuarioRevista = "select * from Me_Gusta where nombre_revista = ? AND user_name = ?";
    private final String numeroReaccionesQuery = "SELECT COUNT(*) FROM Me_Gusta WHERE nombre_revista = ?";
    private final String numeroComentariosQuery = "select count(*) from Revista r inner join Comentario c on r.nombre = c.nombre_revista where c.nombre_revista = ?";
    private final String numeroSuscripcionesQuery = "select count(*) from Revista r inner join Suscripcion s on r.nombre = s.nombre_revista where s.nombre_revista = ? AND s.suscripcion_activa=true";
    private final Connection conexion = ConexionDB.getConexion();

    public Integer cantidadEstadisticaRevista(String nombreRevista, String estadisticaQuery) {
        switch (estadisticaQuery) {
            case "comentarios":
                estadisticaQuery = numeroComentariosQuery;
                break;
            case "likes":
                estadisticaQuery = numeroReaccionesQuery;
                break;
            case "suscripciones":
                estadisticaQuery = numeroSuscripcionesQuery;
                break;
            default:
                throw new AssertionError();
        }
        try ( PreparedStatement ps = conexion.prepareStatement(estadisticaQuery)) {
            ps.setString(1, nombreRevista);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    public MeGusta getReaccionUser(String nombreRevista, String nombreUsuario){
        try (PreparedStatement ps = conexion.prepareStatement(reaccionUsuarioRevista)){
            ps.setString(1, nombreRevista);
            ps.setString(2, nombreUsuario);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return new MeGusta(rs.getString(1), rs.getString(2), rs.getDate(3));
                }
            }
        } catch (Exception e) {
            
        }
        return new MeGusta("", "","");
    }

}
