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
public class DBRegisterRevista {

    private final String asignarCostoDiarioQuery = "UPDATE Revista SET costo_dia=? WHERE nombre = ?";
    private final String asigarEtiquetaRevistaQuery = "INSERT INTO Etiquetas_Revista VALUES (?,?)";
    private final String crearRevistaQuery = "INSERT INTO Revista (nombre, descripcion,suscribir,comentar,reaccionar,pago,costo_suscripcion,nombre_categoria,user_name) VALUES (?,?,?,?,?,?,?,?,?)";
    private final Connection conexion = ConexionDB.getConexion();

    public void insertarRevista(Revista revista) throws SQLException {
        try ( PreparedStatement ps = conexion.prepareStatement(crearRevistaQuery)) {
            ps.setString(1, revista.getNombre());
            ps.setString(2, revista.getDescripcion());
            ps.setBoolean(3, revista.isSuscribir());
            ps.setBoolean(4, revista.isComentar());
            ps.setBoolean(5, revista.isReaccionar());
            ps.setBoolean(6, revista.isPago());
            ps.setObject(7, revista.getCostoSuscripcion(), java.sql.Types.DOUBLE);
            ps.setString(8, revista.getNombreCategoria());
            ps.setString(9, revista.getUserName());
            ps.execute();
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 1062:
                    throw new SQLException("Ya existe una revista con dicho nombre");
                case 1406:
                    throw new SQLException("Se ha sobrepasado la cantidad de caracteres permitidos en uno de los campos");
                default:
                    throw new SQLException(e.getMessage());
            }
        }
    }
    
    public void asignarCostoDiario(Double costoDiario, String nombreRevista){
        try (PreparedStatement ps = conexion.prepareStatement(asignarCostoDiarioQuery)){
            ps.setDouble(1, costoDiario);
            ps.setString(2, nombreRevista);
            ps.execute();    
        } catch (Exception e) {
        }
    }
    
    public void asociarEtiqueta(String nombreRevista, Etiqueta etiqueta){
        try (PreparedStatement ps = conexion.prepareStatement(asigarEtiquetaRevistaQuery)){
            ps.setString(1, nombreRevista);
            ps.setString(2, etiqueta.getNombre());
            ps.execute();
        } catch (Exception e) {
        }
    }
    
    
}
