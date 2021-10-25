/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ValoresGlobalesModel;

import ConexionDB.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author joel
 */
public class DBValoresGlobales {
    
    private Connection conexion = ConexionDB.getConexion();
    private String obtenerPorcentajeGananciaQuery = "SELECT porcentaje_ganancia FROM Valores_Global";
    
    public int getPorcentajeGanancia(){
        try (PreparedStatement ps = conexion.prepareStatement(obtenerPorcentajeGananciaQuery); ResultSet rs = ps.executeQuery()){
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
}
