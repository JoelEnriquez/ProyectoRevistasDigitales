/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RevistasModel;

import ConexionDB.ConexionDB;
import EntidadesRevista.Pago;
import EntidadesRevista.Suscripcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joel
 */
public class DBSuscripcion {
    private final Connection conexion = ConexionDB.getConexion();
    private final String anularSuscripcion = "UPDATE Suscripcion SET suscripcion_activa = 0 WHERE nombre_revista=? AND user_name=?";
    private final String registrarPagoQuery = "INSERT INTO Pago (monto, porcentaje_ganancia, fecha_pago, id_suscripcion) VALUES (?,?,?,?)";
    private final String verificarSuscripcionQuery = "select count(*) from Suscripcion WHERE nombre_revista = ? AND user_name = ? AND suscripcion_activa = 1";
    private final String verificarSuscripcionPagoQuery = verificarSuscripcionQuery + "and date(now()) < fecha_caducidad";
    private final String registrarSuscripcionQuery = "insert into Suscripcion (fecha_suscripcion, suscripcion_activa, nombre_revista, user_name) values (?,?,?,?)";
    private final String registrarSuscripcionPagoQuery = "insert into Suscripcion (fecha_suscripcion,fecha_caducidad, suscripcion_activa, nombre_revista, user_name) values (?,?,?,?,?)";
    
    public Boolean suscripcionActiva(String nombreRevista, String userNameLector){
        try (PreparedStatement ps = conexion.prepareStatement(verificarSuscripcionQuery)){
            ps.setString(1, nombreRevista);
            ps.setString(2, userNameLector);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getInt(1)==1;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }
    
    public Boolean suscripcionActivaPago(String nombreRevista, String userNameLector){
        try (PreparedStatement ps = conexion.prepareStatement(verificarSuscripcionPagoQuery)){
            ps.setString(1, nombreRevista);
            ps.setString(2, userNameLector);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getInt(1)==1;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }
    
    public void anularSuscripcion(String nombreRevista, String userNameLector){
        try (PreparedStatement ps = conexion.prepareStatement(anularSuscripcion)){
            ps.setString(1, nombreRevista);
            ps.setString(2, userNameLector);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void registrarNuevaSuscripcion(Suscripcion suscripcion) throws SQLException{
        try (PreparedStatement ps = conexion.prepareStatement(registrarSuscripcionQuery)){
            ps.setDate(1, suscripcion.getFechaSuscripcionDate());
            ps.setBoolean(2, suscripcion.isSuscripcionActiva());
            ps.setString(3, suscripcion.getNombreRevista());
            ps.setString(4, suscripcion.getUserName());
            ps.execute();
        } catch (SQLException e) {
            throw new SQLException("Error en el formato de la fecha");
        }
    }
    
    public int registrarNuevaSuscripcionPago(Suscripcion suscripcion) throws SQLException{
        try (PreparedStatement ps = conexion.prepareStatement(registrarSuscripcionPagoQuery,PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setDate(1, suscripcion.getFechaSuscripcionDate());
            ps.setDate(2, suscripcion.getFechaCaducidadDate());
            ps.setBoolean(3, suscripcion.isSuscripcionActiva());
            ps.setString(4, suscripcion.getNombreRevista());
            ps.setString(5, suscripcion.getUserName());
            if (ps.executeUpdate() == 1) {
                try ( ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error en el formato de la fecha");
        }
        return 0;
    }
    
    public void agregarPagoRevista(Pago pago) throws SQLException{
        try (PreparedStatement ps = conexion.prepareStatement(registrarPagoQuery)){
            ps.setDouble(1, pago.getMontoPagar());
            ps.setInt(2, pago.getPorcentajeGanancia());
            ps.setDate(3, pago.getDatePago());
            ps.setInt(4, pago.getIdSuscripcion());
            ps.execute();
        } catch (SQLException e) {
            throw new SQLException("Error en el formato de la fecha");
        }
    }
    
}
