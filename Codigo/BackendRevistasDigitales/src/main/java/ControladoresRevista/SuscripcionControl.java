/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresRevista;

import ErrorAPI.ErrorResponse;
import RevistasModel.SuscripcionModel;
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

/**
 *
 * @author joel
 */
@WebServlet(name = "SuscripcionControl", urlPatterns = {"/SuscripcionControl"})
@MultipartConfig
public class SuscripcionControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre_revista");
        String usuario = request.getParameter("usuario");
        response.getWriter().append(new SuscripcionModel().verificarSuscripcionActiva(nombre,usuario));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String suscripcion = request.getParameter("suscripcion");
            new SuscripcionModel().registrarSuscripcion(suscripcion);
        } catch (SQLException ex) {
            ErrorResponse.mostrarError(response, ex.getMessage());
        }
    }

}
