/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresInicio;

import Convertidores.ErrorBackendModelConverter;
import EntidadesInicio.RespuestaRegistro;
import ModelosAPI.ErrorBackendModel;
import Personas.Editor;
import RegisterModel.RegistrarEditor;
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
@WebServlet(name = "RegisterControl", urlPatterns = {"/RegisterControl"})
public class RegisterEditControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RegistrarEditor re = new RegistrarEditor(request.getReader());
            Editor nuevoEditor = re.registrarEditor();
            response.getWriter().append(re.editorRegistrado(nuevoEditor));
        } catch (IOException | SQLException e) {
            ErrorBackendModelConverter errorBackendModelConverter = new ErrorBackendModelConverter(ErrorBackendModel.class);
            ErrorBackendModel ebm = new ErrorBackendModel(e.getMessage());
            response.getWriter().append(errorBackendModelConverter.toJson(ebm));
        }

    }

}
