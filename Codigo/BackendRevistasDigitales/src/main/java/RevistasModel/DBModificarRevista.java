/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RevistasModel;

import ConexionDB.ConexionDB;
import EntidadesRevista.Etiqueta;
import EntidadesRevista.Revista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author joel
 */
public class DBModificarRevista {

    private final String eliminarEtiquetasAsociadasQuery = "DELETE FROM Etiquetas_Revista WHERE nombre_revista = ?";
    private final String actualizarDatosRevistaQuery = "UPDATE Revista SET descripcion = ?,suscribir = ?,comentar = ?,reaccionar = ?,pago = ?,costo_suscripcion = ?,nombre_categoria = ? WHERE nombre = ?";
    private final String actualizarSuscripcionQuery = "UPDATE Revista SET suscribir = ? WHERE nombre = ?";
    private final String actualizarComentarQuery = "UPDATE Revista SET comentar = ? WHERE nombre = ?";
    private final String actualizarReaccionQuery = "UPDATE Revista SET reaccionar = ? WHERE nombre = ?";
    private final Connection conexion = ConexionDB.getConexion();

    
    public void actualizarStatusSuscripcion(String nombreRevista, boolean nuevoValor) throws SQLException{
        try (PreparedStatement ps = conexion.prepareStatement(actualizarSuscripcionQuery)){
            ps.setBoolean(1, nuevoValor);
            ps.setString(2, nombreRevista);
            ps.executeUpdate();     
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
    
    public void actualizarStatusComentar(String nombreRevista, boolean nuevoValor) throws SQLException{
        try (PreparedStatement ps = conexion.prepareStatement(actualizarComentarQuery)){
            ps.setBoolean(1, nuevoValor);
            ps.setString(2, nombreRevista);
            ps.executeUpdate();     
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
    
    public void actualizarStatusReaccion(String nombreRevista, boolean nuevoValor) throws SQLException{
        try (PreparedStatement ps = conexion.prepareStatement(actualizarReaccionQuery)){
            ps.setBoolean(1, nuevoValor);
            ps.setString(2, nombreRevista);
            ps.executeUpdate();     
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
    
    public void actualizarDatosDeRevista(Revista revistaActualizar) throws SQLException{
        try (PreparedStatement ps = conexion.prepareStatement(actualizarDatosRevistaQuery)){
            ps.setString(1, revistaActualizar.getDescripcion());
            ps.setBoolean(2, revistaActualizar.isSuscribir());
            ps.setBoolean(3, revistaActualizar.isComentar());
            ps.setBoolean(4, revistaActualizar.isReaccionar());
            ps.setBoolean(5, revistaActualizar.isPago());
            ps.setObject(6, revistaActualizar.getCostoSuscripcion(), java.sql.Types.DOUBLE);
            ps.setString(7, revistaActualizar.getNombreCategoria());
            ps.setString(8, revistaActualizar.getNombre());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 1406:
                    throw new SQLException("Se ha sobrepasado la cantidad de caracteres permitidos en uno de los campos");
                default:
                    throw new SQLException(e.getMessage());
            }
        }
    }
    
    public void eliminarEtiquetasAsociadas(String nombreRevista){
        try (PreparedStatement ps = conexion.prepareStatement(eliminarEtiquetasAsociadasQuery)){
            ps.setString(1, nombreRevista);
            ps.execute();
        } catch (Exception e) {
        }
    }
    
    
}
