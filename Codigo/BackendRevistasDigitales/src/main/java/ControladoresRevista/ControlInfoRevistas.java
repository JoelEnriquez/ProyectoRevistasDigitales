/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresRevista;

import RevistasModel.InfoRevista;
import java.io.IOException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
@WebServlet(name = "ControlInfoRevistas", urlPatterns = {"/ControlInfoRevistas"})
@MultipartConfig
public class ControlInfoRevistas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "listado_categorias":
                String userName = request.getParameter("user_name");
                response.getWriter().append(new InfoRevista().getListadoCategoriasPreferencia(userName));
                break;
            case "revistas_categoria":
                String categoria = request.getParameter("categoria");
                response.getWriter().append(new InfoRevista().getListadoRevistasPorCategoria(categoria));
                response.getWriter();
                break;
            default:
                throw new AssertionError();
        }
    }
}
