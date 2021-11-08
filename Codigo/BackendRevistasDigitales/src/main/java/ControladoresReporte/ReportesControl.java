/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresReporte;

import Convertidores.ConvertidorComentarioList;
import Convertidores.ConvertidorRevistaReaccionList;
import EntidadesJasper.GananciaEditor;
import EntidadesJasper.RevistaReaccion;
import EntidadesRevista.Comentario;
import EntidadesRevista.Suscripcion;
import ErrorAPI.ErrorResponse;
import ReporteModel.ValidadorParametros;
import ReportesEditor.ReporteComentarios;
import ReportesEditor.ReporteGananciasEditor;
import ReportesEditor.ReporteReacciones;
import ReportesEditor.ReporteSuscripciones;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author joel
 */
@WebServlet(name = "ReportesControl", urlPatterns = {"/ReportesControl"})
public class ReportesControl extends HttpServlet {
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/pdf");
        ValidadorParametros vp = new ValidadorParametros();

        String fechaInicio = request.getParameter("fecha_inicio");
        String fechaFin = request.getParameter("fecha_fin");
        String nombreUsuario = request.getParameter("user_name");
        String filtro = request.getParameter("filtro");

        Date date1 = null;
        Date date2 = null;
        try {
            boolean fechaIngresada = vp.unaFechaAlMenos(fechaInicio, fechaFin);
            if (fechaIngresada) {
                boolean fechasValidas = vp.ambasFechasIngresadas(fechaFin, fechaFin);
                if (fechasValidas) {
                    date1 = Date.valueOf(fechaInicio);
                    date2 = Date.valueOf(fechaFin);
                }
            }
            switch (request.getParameter("tipo")) {
                case "EDITOR":
                    obtenerReporteEditor(request, response, fechaIngresada, date1, date2, nombreUsuario, filtro);
                    break;
                case "ADMIN":
                    //printAdminReport(request, response, fechaIngresada, date1, date2);
                    break;
            }
        } catch (Exception e) {
            ErrorResponse.mostrarError(response, e.getMessage());
        }

    }
    
    private void obtenerReporteEditor(HttpServletRequest request, HttpServletResponse response, boolean fechaIngresada, Date date1, Date date2, String usuarioEditor, String filtro) throws IOException, JRException {
        switch (request.getParameter("action")) {
            case "comentarios":
                List<Comentario> listadoComentarios = new ReporteComentarios().listadoComentarios(date1, date2, filtro, fechaIngresada, usuarioEditor);
                response.getWriter().append(new ConvertidorComentarioList((Class<List<Comentario>>) listadoComentarios.getClass()).toJson(listadoComentarios));
                break;

            case "suscripciones_revista":
                List<Suscripcion> listadoSuscripciones = new ReporteSuscripciones().listadoSuscripciones(date1, date2, filtro, fechaIngresada, usuarioEditor);
                
                break;
            case "mas_gustadas":
                List<RevistaReaccion> listadoMeGusta = new ReporteReacciones().getListadoRevistasMeGusta(date1, date2,filtro, fechaIngresada, usuarioEditor);
                response.getWriter().append(new ConvertidorRevistaReaccionList((Class<List<RevistaReaccion>>) listadoMeGusta.getClass()).toJson(listadoMeGusta));
                break;
            case "ganancias_editor":
                List<GananciaEditor> listadoGanancia = new ReporteGananciasEditor().gananciasEditor(date1, date2, filtro, fechaIngresada, usuarioEditor);
                break;
        }
    }
}
