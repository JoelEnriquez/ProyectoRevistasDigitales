/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresInicio;

import Convertidores.ErrorBackendModelConverter;
import ErrorAPI.ErrorBackendModel;
import ErrorAPI.ErrorResponse;
import Personas.Editor;
import Personas.Usuario;
import RegisterModel.DBEscogerCategorias;
import RegisterModel.RegistrarEditor;
import RegisterModel.RegistrarUsuario;
import RegisterModel.VerificarUsuario;
import RevistasModel.DBCategoria;
import com.google.gson.Gson;
import java.io.IOException;
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
@WebServlet(name = "RegisterControl", urlPatterns = {"/RegisterControl"})
@MultipartConfig
public class RegisterControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "checkuser":
                try {
                String usuarioComprobar = request.getParameter("persona");
                Boolean usuarioExistente = new VerificarUsuario().verificarUsuario(usuarioComprobar);
                response.getWriter().append(usuarioExistente.toString());
                } catch (IOException | SQLException e) {
                response.getWriter().append(new ErrorBackendModelConverter(ErrorBackendModel.class).toJson(new ErrorBackendModel(e.getMessage())));
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                }   

            break;
            case "list_categories":
                String userName = request.getParameter("userName");
                if (userName!=null) {
                    response.getWriter().append(new Gson().toJson(new DBEscogerCategorias().getListadoCategorias(userName)));
                }else {
                response.getWriter().append(new Gson().toJson(new DBCategoria().getListadoCategorias()));
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Part imagen = request.getPart("image");
            String persona = request.getParameter("persona");
            if (request.getParameter("tipo_registro").equals("USUARIO")) {
                String listadoCategorias = request.getParameter("list_categories");
                RegistrarUsuario ru = new RegistrarUsuario();
                Usuario nuevoUsuario = ru.registrarUsuario(persona, imagen);
                ru.registrarListadoCategorias(listadoCategorias, nuevoUsuario.getUserName()); //Registrar gustos de categoria
                response.getWriter().append(ru.editorRegistrado(nuevoUsuario));
            } else {
                RegistrarEditor re = new RegistrarEditor();
                Editor nuevoEditor = re.registrarEditor(persona, imagen);
                response.getWriter().append(re.editorRegistrado(nuevoEditor));
            }
        } catch (IOException | SQLException e) {
            ErrorResponse.mostrarError(response, e.getMessage());
        } catch (Exception ex) {
            ErrorResponse.mostrarError(response, ex.getMessage());
        }

    }

}
