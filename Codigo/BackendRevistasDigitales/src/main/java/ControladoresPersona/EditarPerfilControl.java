/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresPersona;

import PersonaModel.EditarPerfil;
import PersonaModel.InfoPerfil;
import Personas.Editor;
import Personas.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
@WebServlet(name = "EditarPerfilControl", urlPatterns = {"/EditarPerfilControl"})
public class EditarPerfilControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipoPersona = request.getParameter("tipo");
        String userName = request.getParameter("userName");
        InfoPerfil infoPerfil = new InfoPerfil();
        switch (tipoPersona) {
            case "editor":
                String editor = infoPerfil.obtenerInfoEditor(userName);
                response.getWriter().append(editor);
                break;
            case "usuario":
                String usuario = infoPerfil.obtenerInfoUsuario(userName);
                response.getWriter().append(usuario);
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
