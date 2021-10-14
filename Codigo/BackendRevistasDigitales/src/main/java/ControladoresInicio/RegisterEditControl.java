/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresInicio;

import Convertidores.ErrorBackendModelConverter;
import EntidadesInicio.RespuestaRegistro;
import ErrorAPI.ErrorBackendModel;
import Personas.Editor;
import RegisterModel.RegistrarEditor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "RegisterEditControl", urlPatterns = {"/RegisterEditControl"})
@MultipartConfig
public class RegisterEditControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Part imagen = request.getPart("image");
            String persona = request.getParameter("persona");
            RegistrarEditor re = new RegistrarEditor();
            Editor nuevoEditor = re.registrarEditor(persona, imagen);
            response.getWriter().append(re.editorRegistrado(nuevoEditor));
        } catch (IOException | SQLException e) {
            response.getWriter().append(new ErrorBackendModelConverter(ErrorBackendModel.class).toJson(new ErrorBackendModel(e.getMessage())));
        }

    }

}
