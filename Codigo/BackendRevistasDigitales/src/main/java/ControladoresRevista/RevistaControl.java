/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresRevista;

import ErrorAPI.ErrorResponse;
import RevistasModel.ActualizarDatosRevista;
import RevistasModel.InfoRevista;
import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "RevistaControl", urlPatterns = {"/RevistaControl"})
@MultipartConfig
public class RevistaControl extends HttpServlet {

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "revistas_propias":
                String userNameEditor = request.getParameter("editor");
                response.getWriter().append(new InfoRevista().getlistadoRevistasFiltro(userNameEditor,action));
                break;
            case "etiquetas_revista":
                String nombreRevista = request.getParameter("revista");
                response.getWriter().append(new InfoRevista().getListadoEtiquetasRevista(nombreRevista));
                break;
            case "estadistica":
                String revista = request.getParameter("revista");
                String estadistica = request.getParameter("estadistica");
                response.getWriter().append(new InfoRevista().cantidadEstadisticaRevista(revista,estadistica));
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
        String revista = request.getParameter("revista");
        String etiquetas = request.getParameter("etiquetas");
        try {
            new ActualizarDatosRevista().actualizarDatosRevista(revista, etiquetas);
        } catch (SQLException e) {
            ErrorResponse.mostrarError(response, e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response){
        String nombreRevista = request.getParameter("nombreRevista");
        String statusNuevo = request.getParameter("statusNuevo");
        String campoModificar = request.getParameter("campoModificar");
        new InfoRevista().modificarCampoRevista(nombreRevista, Boolean.valueOf(statusNuevo), campoModificar);
    }

}
