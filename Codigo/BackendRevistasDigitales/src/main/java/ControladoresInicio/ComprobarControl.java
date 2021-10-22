/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresInicio;

import ComprobarValoresGlobales.VerificarValores;
import ErrorAPI.ErrorResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
@WebServlet(name = "ComprobarControl", urlPatterns = {"/ComprobarControl"})
public class ComprobarControl extends HttpServlet {

    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VerificarValores verificarValores = new VerificarValores(request.getReader());
        response.getWriter().append(verificarValores.verificarValores());
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VerificarValores verificarValores = new VerificarValores(request.getReader());
        try {
            verificarValores.insertarValores();
        } catch (SQLException|IOException e) {
            ErrorResponse.mostrarError(response, e.getMessage());
        } catch (Exception ex){
            ErrorResponse.mostrarError(response, ex.getMessage());
        }
    }

    

}
