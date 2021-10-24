/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresRevista;

import EntidadesApoyo.FiltroEnum;
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
                response.getWriter().append(new InfoRevista().getlistadoRevistasFiltro(categoria,action));
                break;
            case "revistas_etiqueta":
                String etiqueta = request.getParameter("etiqueta");
                response.getWriter().append(new InfoRevista().getlistadoRevistasFiltro(etiqueta,action));
                break;
            case "filtro":
                String busqueda = request.getParameter("busqueda");
                String filtro = request.getParameter("filtro");
                if (filtro.equals(FiltroEnum.CATEGORIA.getFiltro())) {
                    response.getWriter().append(new InfoRevista().getlistadoRevistasFiltro(busqueda,filtro));
                } else if (filtro.equals(FiltroEnum.ETIQUETA.getFiltro())) {
                    response.getWriter().append(new InfoRevista().getlistadoRevistasFiltro(busqueda,filtro));
                }
                break;
            case "info_revista":
                String nombreRevista = request.getParameter("nombre");
                response.getWriter().append(new InfoRevista().getlistadoRevistasFiltro(nombreRevista,action));
                break;
            default:
                throw new AssertionError();
        }
    }
}
