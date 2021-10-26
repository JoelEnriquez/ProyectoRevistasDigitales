/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresFile;

import ErrorAPI.ErrorResponse;
import FileModel.FileModel;
import java.io.IOException;
import java.io.InputStream;
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
@WebServlet(name = "FileControl", urlPatterns = {"/FileControl"})
public class FileControl extends HttpServlet {

    

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
        FileModel fileModel = new FileModel();
        switch (action) {
            case "image":
                String userName = request.getParameter("userName");
                InputStream foto = fileModel.getFotoPerfil(userName);
                if (foto!=null) {
                    try {
                        fileModel.mostrarFoto(foto,response);
                    } catch (IOException e) {
                        ErrorResponse.mostrarError(response, e.getMessage());
                    }
                } else {
                    fileModel.mostrarFotoNeutral(response);
                }
                break;
            case "pdf":
                String pathPDF = request.getParameter("ruta_pdf");
                if (pathPDF!=null) {
                    try {
                        fileModel.mostrarPDF(response,pathPDF);
                    } catch (IOException e) {
                        ErrorResponse.mostrarError(response, e.getMessage());
                    }
                }
                break;
            case "descargar":
                String path = request.getParameter("ruta_pdf");
                if (path!=null) {
                    try {
                        fileModel.descargarPDF(response,path);
                    } catch (IOException e) {
                        ErrorResponse.mostrarError(response, e.getMessage());
                    }
                }
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
