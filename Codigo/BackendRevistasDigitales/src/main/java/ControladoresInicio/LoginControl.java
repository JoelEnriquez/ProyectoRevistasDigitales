/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresInicio;

import ErrorAPI.ErrorResponse;
import LoginModel.ComprobarCredenciales;
import LoginModel.DBLogin;
import Personas.Editor;
import Personas.Persona;
import Personas.PersonaEnum;
import Personas.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
@WebServlet(name = "LoginControl", urlPatterns = {"/LoginControl"})
public class LoginControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ComprobarCredenciales cc = new ComprobarCredenciales(request.getReader());
            Persona persona = cc.verificarPersona();
            response.getWriter().append(cc.personaVerificada(persona));
        } catch (IOException e) {
            ErrorResponse.mostrarError(response, e.getMessage());
        } catch (Exception ex) {
            ErrorResponse.mostrarError(response, ex.getMessage());
        }
    }

}
