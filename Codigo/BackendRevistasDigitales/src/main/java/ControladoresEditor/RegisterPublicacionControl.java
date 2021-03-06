/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresEditor;

import ConexionDB.ConexionDB;
import Convertidores.ErrorBackendModelConverter;
import ErrorAPI.ErrorBackendModel;
import ErrorAPI.ErrorResponse;
import RevistasModel.RegistrarPublicacion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author joel
 */
@WebServlet(name = "RegisterPublicacionControl", urlPatterns = {"/RegisterPublicacionControl"})
@MultipartConfig
public class RegisterPublicacionControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String publicacion = request.getParameter("publicacion");
        Part file = request.getPart("file");
        Connection conexion = ConexionDB.getConexion();
        RegistrarPublicacion rp = new RegistrarPublicacion(file, publicacion, request.getServletContext().getRealPath("")+"../");
        try {
            conexion.setAutoCommit(false);
            rp.insertarPublicacion();
            rp.guardarArchivoEnServer();
            rp.guardarRutaEnDB();
            conexion.commit();
        } catch (SQLException | IOException ex) {
            ErrorResponse.mostrarError(response, ex.getMessage());
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
            }
        } catch (Exception ex) {
            ErrorResponse.mostrarError(response, ex.getMessage());
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
            }
        } finally {
            try {
                conexion.setAutoCommit(true);
            } catch (SQLException ex) {
            } 
        }
    }
}
