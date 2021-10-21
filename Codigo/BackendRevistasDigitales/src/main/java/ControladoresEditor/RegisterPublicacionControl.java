/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresEditor;

import Convertidores.Convertidor;
import Convertidores.ErrorBackendModelConverter;
import EntidadesRevista.Publicacion;
import ErrorAPI.ErrorBackendModel;
import RevistasModel.RegistrarPublicacion;
import java.io.IOException;
import java.io.PrintWriter;
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
        RegistrarPublicacion rp = new RegistrarPublicacion(file, publicacion);  
        try {
            rp.insertarPublicacion();
            rp.guardarArchivoEnServer();
            rp.guardarRutaEnDB();
        } catch (SQLException | IOException ex) {
            response.getWriter().append(new ErrorBackendModelConverter(ErrorBackendModel.class).toJson(new ErrorBackendModel(ex.getMessage())));
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
        } catch (Exception ex) {
            response.getWriter().append(new ErrorBackendModelConverter(ErrorBackendModel.class).toJson(new ErrorBackendModel(ex.getMessage())));
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }     
    } 
}
