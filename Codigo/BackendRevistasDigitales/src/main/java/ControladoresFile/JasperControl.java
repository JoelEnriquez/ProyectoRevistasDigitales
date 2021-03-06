/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresFile;

import Convertidores.ConvertidorRevistaComentarioList;
import Convertidores.ConvertidorRevistaSuscripcionList;
import EntidadesApoyo.RutasEnum;
import EntidadesJasper.GananciaEditor;
import EntidadesJasper.RevistaComentario;
import EntidadesJasper.RevistaReaccion;
import EntidadesJasper.RevistaSuscripcion;
import EntidadesRevista.Comentario;
import EntidadesRevista.Suscripcion;
import ErrorAPI.ErrorResponse;
import JasperModel.JasperService;
import ReporteModel.ValidadorParametros;
import ReportesAdmin.ReporteRevistasComentadas;
import ReportesAdmin.ReporteRevistasPopulares;
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
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author joel
 */
@WebServlet(name = "JasperControl", urlPatterns = {"/JasperControl"})
public class JasperControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=Reporte.pdf");
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
                    printEditorReport(request, response, fechaIngresada, date1, date2, nombreUsuario, filtro);
                    break;
                case "ADMIN":
                    printAdminReport(request, response, fechaIngresada, date1, date2);
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void printEditorReport(HttpServletRequest request, HttpServletResponse response, boolean fechaIngresada, Date date1, Date date2, String usuarioEditor, String filtro) throws IOException, JRException {
        JasperService js = new JasperService();
        String subPath = RutasEnum.RUTA_TO_EDITOR_REPORTS.getRuta();
        String pathReport = "";
        JRDataSource source;
        switch (request.getParameter("action")) {
            case "comentarios":
                List<Comentario> listadoComentarios = new ReporteComentarios().listadoComentarios(date1, date2, filtro, fechaIngresada, usuarioEditor);
                source = new JRBeanCollectionDataSource(listadoComentarios);
                pathReport = subPath + "ReporteComentarios.jasper";
                js.imprimirReporteBeans(response.getOutputStream(), pathReport, source);
                break;
            case "suscripciones_revista":
                List<Suscripcion> listadoSuscripciones = new ReporteSuscripciones().listadoSuscripciones(date1, date2, filtro, fechaIngresada, usuarioEditor);
                source = new JRBeanCollectionDataSource(listadoSuscripciones);
                pathReport = subPath + "ReporteSuscripciones.jasper";
                js.imprimirReporteBeans(response.getOutputStream(), pathReport, source);
                break;
            case "mas_gustadas":
                List<RevistaReaccion> listadoMeGusta = new ReporteReacciones().getListadoRevistasMeGusta(date1, date2, filtro, fechaIngresada, usuarioEditor);
                source = new JRBeanCollectionDataSource(listadoMeGusta);
                pathReport = subPath + "ReporteReacciones.jasper";
                js.imprimirReporteBeans(response.getOutputStream(), pathReport, source);
                break;
            case "ganancias_editor":
                List<GananciaEditor> listadoGanancia = new ReporteGananciasEditor().gananciasEditor(date1, date2, filtro, fechaIngresada, usuarioEditor);
                source = new JRBeanCollectionDataSource(listadoGanancia);
                pathReport = subPath + "GananciasEditor.jasper";
                js.imprimirReporteBeans(response.getOutputStream(), pathReport, source);
                break;
        }
    }

    /**
     * Print a report for admiun
     *
     * @param request
     * @param response
     * @param validDates
     * @param date1
     * @param date2
     * @throws IOException
     * @throws JRException
     */
    private void printAdminReport(HttpServletRequest request, HttpServletResponse response, boolean fechaIngresada, Date date1, Date date2) throws IOException, JRException {
        JasperService js = new JasperService();
        String subPath = RutasEnum.RUTA_TO_ADMIN_REPORTS.getRuta();
        String pathReport = "";
        JRDataSource source;
        switch (request.getParameter("action")) {
            case "revistas_populares":
                List<RevistaSuscripcion> listadoRevistaSuscripciones = new ReporteRevistasPopulares().getListadoRevistasConSuscripciones(date1, date2, fechaIngresada);
                source = new JRBeanCollectionDataSource(listadoRevistaSuscripciones);
                pathReport = subPath + "ReporteRevistasPopulares.jasper";
                js.imprimirReporteBeans(response.getOutputStream(), pathReport, source);
                break;
            case "revistas_comentadas":
                List<RevistaComentario> listadoRevistasComentarios = new ReporteRevistasComentadas().getListadoRevistasConComentarios(date1, date2, fechaIngresada);
                source = new JRBeanCollectionDataSource(listadoRevistasComentarios);
                pathReport = subPath + "ReporteRevistasComentadas.jasper";
                js.imprimirReporteBeans(response.getOutputStream(), pathReport, source);
                break;
        }
    }
}
