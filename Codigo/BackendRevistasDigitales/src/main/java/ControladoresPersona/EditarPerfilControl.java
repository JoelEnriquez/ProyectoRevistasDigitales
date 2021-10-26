/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresPersona;

import ErrorAPI.ErrorResponse;
import PersonaModel.EditarPerfil;
import FileModel.FileModel;
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
@WebServlet(name = "EditarPerfilControl", urlPatterns = {"/EditarPerfilControl"})
@MultipartConfig
public class EditarPerfilControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipoPersona = request.getParameter("tipo");
        String userName = request.getParameter("userName");
        FileModel infoPerfil = new FileModel();
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
        String tipoPersona = request.getParameter("tipo_update");
        Part part = request.getPart("image");
        String persona = request.getParameter("persona");
        String cambioImagen = request.getParameter("cambio_imagen");
        EditarPerfil editar = new EditarPerfil(persona, part,cambioImagen);
        switch (tipoPersona) {
            case "EDITOR": {
                try {
                    String editorActualizado = editar.actualizarInfoEditor();
                    response.getWriter().append(editorActualizado);
                } catch (SQLException ex) {
                    ErrorResponse.mostrarError(response, ex.getMessage());
                }
            }
            break;
            case "USUARIO":
                try {
                    String categorias = request.getParameter("list_categories");
                    String etiquetas = request.getParameter("list_etiquetas");
                    String usuarioActualizado = editar.actualizarInfoUsuario(categorias, etiquetas);
                    response.getWriter().append(usuarioActualizado);
                } catch (SQLException ex) {
                    ErrorResponse.mostrarError(response, ex.getMessage());
                }
                break;
            default:
                throw new AssertionError();
        }
    }

}
