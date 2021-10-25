/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RevistasModel;

import ConexionDB.ConexionDB;
import EntidadesRevista.Publicacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author joel
 */
public class DBRegistrarPublicacion {

    private final String guardarPathDBQuery = "UPDATE Publicacion SET path_publicacion = ? WHERE id=?";
    private final String insertarPublicacionQuery = "INSERT INTO Publicacion (nombre_publicacion, path_publicacion, fecha_publicacion, nombre_revista) VALUES (?,?,?,?)";
    private final Connection conexion = ConexionDB.getConexion();

    public int insertarPublicacion(Publicacion publicacion) throws SQLException {
        try ( PreparedStatement ps = conexion.prepareStatement(insertarPublicacionQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, publicacion.getNombrePublicacion());
            ps.setString(2, publicacion.getPathArchivo());
            ps.setDate(3, publicacion.getFechaFinalDate());
            ps.setString(4, publicacion.getNombreRevista());

            if (ps.executeUpdate() == 1) {
                try ( ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 1064:
                    throw new SQLException("La fecha viene en formato incorrecto");
                case 1406:
                    throw new SQLException("Se ha sobrepasado la cantidad de caracteres permitidos en uno de los campos");
                default:
                    throw new SQLException(e.getMessage());
            }
        }
        return 0;
    }

    public void guardarRuta(String path, int id) {
        try ( PreparedStatement ps = conexion.prepareStatement(guardarPathDBQuery)) {
            ps.setString(1, path);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
